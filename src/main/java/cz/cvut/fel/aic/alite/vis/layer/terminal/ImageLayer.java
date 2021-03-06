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
package cz.cvut.fel.aic.alite.vis.layer.terminal;

import cz.cvut.fel.aic.alite.vis.element.aggregation.ImageElements;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

public abstract class ImageLayer extends TerminalLayer {

	private final ImageElements imageElements;

	protected ImageLayer(ImageElements imageElements) {
		this.imageElements = imageElements;
	}

	protected ImageElements getImageElements() {
		return imageElements;
	}

	public static BufferedImage loadImage(File file) {
		BufferedImage img = null;
		try {
			img = loadImage(file.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return img;
	}

	public static BufferedImage loadImage(URL file) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img;
	}

}
