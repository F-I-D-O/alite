package incubator.visprotocol.vis.layer.example;

import incubator.visprotocol.structure.Element;
import incubator.visprotocol.structure.Folder;
import incubator.visprotocol.structure.Structure;
import incubator.visprotocol.structure.key.CommonKeys;
import incubator.visprotocol.structure.key.PointKeys;
import incubator.visprotocol.vis.layer.FilterStorage;
import incubator.visprotocol.vis.layer.TypedLayer;

import java.awt.Color;
import java.util.ArrayList;

import javax.vecmath.Point3d;

/**
 * Random dynamic points changing color and appearing/disappearing.
 * 
 * @author Ondrej Milenovsky
 * */
public class DynamicPointsLayer extends TypedLayer {

    private final ArrayList<Point3d> points;
    private double prGenerate = 0.9;

    public DynamicPointsLayer(int n, int size, FilterStorage filter) {
        super(filter);
        points = new ArrayList<Point3d>(n);
        for (int i = 0; i < n; i++) {
            points.add(new Point3d(Math.random() * size, Math.random() * size, 0));
        }
    }

    @Override
    public Structure pull() {
        Structure struct = new Structure(CommonKeys.STRUCT_PART);
        if (hasType(PointKeys.TYPE)) {
            Folder f = struct.getRoot("World").getFolder("Dynamic");
            boolean first = true;
            for (int i = 0; i < points.size(); i++) {
                if (Math.random() > prGenerate) {
                    continue;
                }
                Element e = f.getElement("l" + i, PointKeys.TYPE);
                setParameter(e, PointKeys.CENTER, points.get(i));
                setParameter(e, PointKeys.COLOR, new Color(0, 0, (int) (Math.random() * 256)));
                if (first) {
                    setParameter(e, PointKeys.SIZE, 30.0);
                    setParameter(e, PointKeys.CONSTANT_SIZE, false);
                    first = false;
                }
            }
        }
        return struct;
    }

}
