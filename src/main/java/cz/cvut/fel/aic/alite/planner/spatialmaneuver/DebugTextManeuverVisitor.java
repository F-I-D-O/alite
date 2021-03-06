/* 
 * Copyright (C) 2019 Czech Technical University in Prague.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package cz.cvut.fel.aic.alite.planner.spatialmaneuver;

import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.ManeuverVisitor;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.PitchManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.PitchToLevelManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.SmoothManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.SpiralManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.StartManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.StraightManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.ToEndLevelManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.ToEndManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.ToManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.TurnManeuver;
import cz.cvut.fel.aic.alite.planner.spatialmaneuver.maneuver.TurnPitchManeuver;
import java.io.PrintStream;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


public class DebugTextManeuverVisitor implements ManeuverVisitor {

	private final static double DISCRETIZATION_LENGTH = 0.2;

	private PrintStream out;

	public DebugTextManeuverVisitor(PrintStream planOut) {
		out = planOut;
	}

	@Override
	public void visit(PitchManeuver maneuver) {
		//throw new Error("Not implemented yet!");
	}

	@Override
	public void visit(PitchToLevelManeuver maneuver) {
		visit((PitchManeuver) maneuver);
	}

	@Override
	public void visit(SmoothManeuver maneuver) {
		if (maneuver.isVerticalEnd()) {
			maneuver.getEndPitch().accept(this);
		}
		maneuver.getToManeuver().accept(this);
		if (maneuver.isVerticalStart()) {
			maneuver.getPitch().accept(this);
		}
	}

	@Override
	public void visit(SpiralManeuver maneuver) {
		throw new Error("Not implemented yet!");
	}

	@Override
	public void visit(StartManeuver maneuver) {
		// start maneuver is identical with end of the previous maneuver
	}

	@Override
	public void visit(StraightManeuver maneuver) {
		double yaw = Math.atan2(maneuver.getEndDirection().y, maneuver.getEndDirection().x);


		int steps = (int) (maneuver.getLength() / DISCRETIZATION_LENGTH) + 1;
		double stepLength = maneuver.getLength() / steps;
		for (double i = steps; i > 0; i--) {
			Point3d endPoint = new Point3d(maneuver.getStartDirection());
			endPoint.scale(stepLength * i);
			endPoint.add(maneuver.getStart());

			out.print(endPoint.x + ", " + endPoint.y + ", " + endPoint.z);
			out.println(", 0, 0, " + yaw);
		}

	}

	@Override
	public void visit(ToEndLevelManeuver maneuver) {
		maneuver.getEndPitch().accept(this);
		maneuver.getStraight().accept(this);
	}

	@Override
	public void visit(ToEndManeuver maneuver) {
		visit((ToManeuver) maneuver);
	}

	@Override
	public void visit(ToManeuver maneuver) {
		if (!maneuver.isVerticalCorrected()) {
			maneuver.getEndTurn().accept(this);
		} else {
			maneuver.getToManeuver().accept(this);
		}
		if (maneuver.isVertical()) {
			maneuver.getEndPitch().accept(this);
		}
		if (maneuver.isVerticalSpiral()) {
			maneuver.getEndStraight().accept(this);
			maneuver.getSpiral().accept(this);
		}
		maneuver.getStraight().accept(this);
		if (maneuver.isVertical()) {
			maneuver.getPitch().accept(this);
		}
		maneuver.getTurn().accept(this);
	}

	@Override
	public void visit(TurnManeuver maneuver) {
		if (maneuver.getAngle() == 0) {
			return;
		}

		int steps = (int) (maneuver.getAngle() / DISCRETIZATION_LENGTH * Math.abs(maneuver.getRadius())) + 1;
		double stepAngle = maneuver.getAngle() / steps;

		Vector3d u = new Vector3d(maneuver.getStartDirection());
		u.set(-u.y, u.x, 0.0);
		u.normalize();
		u.scale(maneuver.getRadius());

		Point3d center = new Point3d(maneuver.getStart());
		center.sub(u);

		Vector3d v = new Vector3d(maneuver.getStartDirection());
		v.z = 0;
		v.normalize();
		v.scale(Math.abs(maneuver.getRadius()));

		double tmpAngle = stepAngle * steps;
		double yaw = Math.atan2(maneuver.getEndDirection().y, maneuver.getEndDirection().x);
		for (int i = 1; i <= steps; i++, tmpAngle -= stepAngle) {
			double sinAlpha = Math.sin(tmpAngle);
			double cosAlpha = Math.cos(tmpAngle);

			Point3d q = new Point3d();
			q.scaleAdd(cosAlpha, u, center);
			q.scaleAdd(sinAlpha, v, q);
			q.z = maneuver.getEnd().z;

			out.print(q.x + ", " + q.y + ", " + q.z);
			out.println(", 0, 0, " + yaw);

			yaw += Math.signum(maneuver.getRadius()) * stepAngle;
		}
	}

	@Override
	public void visit(TurnPitchManeuver maneuver) {
		maneuver.getPitch().accept(this);
		maneuver.getTurn().accept(this);
	}

}
