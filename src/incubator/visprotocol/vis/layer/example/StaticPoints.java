package incubator.visprotocol.vis.layer.example;

import incubator.visprotocol.vis.layer.AbstractLayer;
import incubator.visprotocol.vis.layer.element.PointElement;

import java.awt.Color;
import java.util.ArrayList;

import javax.vecmath.Point3d;

/**
 * Random static points.
 * 
 * @author Ondrej Milenovsky
 * */
public class StaticPoints extends AbstractLayer {

    private final ArrayList<Point3d> points;

    public StaticPoints(int n, int size) {
        super("Static", true);
        points = new ArrayList<Point3d>(n);
        for (int i = 0; i < n; i++) {
            points.add(new Point3d(Math.random() * size, Math.random() * size, 0));
        }
    }

    @Override
    protected void generateFrame() {
        for (int i = 0; i < points.size(); i++) {
            addElement("p" + i, new PointElement(points.get(i), new Color(255, 160, 160, 30), 4,
                    true));
        }
    }

}
