package cz.agents.alite.vis.layer.common;

import java.awt.Color;
import java.awt.Graphics2D;

import cz.agents.alite.vis.Vis;
import cz.agents.alite.vis.layer.AbstractLayer;
import cz.agents.alite.vis.layer.VisLayer;

// TODO: the color should be requested
public class ColorLayer extends AbstractLayer {

    private Color color;

    protected ColorLayer(final Color color) {
        this.color = color;
    }

    @Override
    public void paint(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.fillRect(0, 0, Vis.getDrawingDimension().width, Vis.getDrawingDimension().height);
    }

    @Override
    public String getLayerDescription() {
        String description = "Layer fills the view with color.";
        return buildLayersDescription(description);
    }

    public static VisLayer create(Color color) {
        return new ColorLayer(color);
    }

}
