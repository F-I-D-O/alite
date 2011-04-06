package incubator.visprotocol.vis.layer.example;

import incubator.visprotocol.creator.TestCreator.ExampleEnvironment;
import incubator.visprotocol.structure.key.struct.Align;
import incubator.visprotocol.vis.layer.AbstractLayer;
import incubator.visprotocol.vis.layer.FilterStorage;
import incubator.visprotocol.vis.layer.element.PointElement;
import incubator.visprotocol.vis.layer.element.TextElement;

import java.awt.Color;
import java.awt.Font;

import javax.vecmath.Point3d;

/**
 * Green zombie from the environment
 * 
 * @author Ondrej Milenovsky
 * */
public class PersonLayer extends AbstractLayer {

    private final ExampleEnvironment env;

    public PersonLayer(ExampleEnvironment env, FilterStorage filter) {
        super(filter);
        this.env = env;
    }

    // @Override
    // public Structure pull() {
    // Structure struct = new Structure(CommonKeys.STRUCT_PART);
    // Folder f = struct.getRoot("World").getFolder("Persons");
    // if (hasType(PointKeys.TYPE)) {
    // Element e = f.getElement(env.getPersonName(), PointKeys.TYPE);
    // Point3d pos = new Point3d(env.getPersonPosition().x, env.getPersonPosition().y, 0);
    // setParameter(e, PointKeys.CENTER, pos);
    // setParameter(e, PointKeys.SIZE, 60.0);
    // setParameter(e, PointKeys.COLOR, new Color(0, Math.min(255, env.getPersonHealth()), 0));
    // setParameter(e, PointKeys.CONSTANT_SIZE, false);
    // }
    // if (hasType(TextKeys.TYPE)) {
    // Element e = f.getElement(env.getPersonName() + "-text", TextKeys.TYPE);
    // Point3d pos = new Point3d(env.getPersonPosition().x, env.getPersonPosition().y - 50, 0);
    // setParameter(e, TextKeys.CENTER, pos);
    // setParameter(e, TextKeys.ALIGN_ON_SCREEN, Align.NONE);
    // setParameter(e, TextKeys.FONT, new Font("GothicE", Font.PLAIN, 40));
    // setParameter(e, TextKeys.CONSTANT_SIZE, false);
    // setParameter(e, TextKeys.COLOR, Color.YELLOW);
    // setParameter(e, TextKeys.TEXT, "Person1");
    //
    // e = f.getElement(env.getPersonName() + "-text2", TextKeys.TYPE);
    // pos = new Point3d(env.getPersonPosition().x, env.getPersonPosition().y + 50, 0);
    // setParameter(e, TextKeys.POS, pos);
    // setParameter(e, TextKeys.FONT, new Font("Arial", Font.ITALIC, 14));
    // setParameter(e, TextKeys.COLOR, new Color(160, 80, 0));
    // setParameter(e, TextKeys.CONSTANT_SIZE, true);
    // setParameter(e, TextKeys.TEXT, "Random...");
    // }
    // return struct;
    // }

    @Override
    protected void generateFrame() {
        changeFolder("World", "Persons");
        Point3d pos = new Point3d(env.getPersonPosition().x, env.getPersonPosition().y, 0);
        addElement(env.getPersonName(), new PointElement(pos, new Color(0, Math.min(255, env
                .getPersonHealth()), 0), 60, false));
        pos = new Point3d(env.getPersonPosition().x, env.getPersonPosition().y - 50, 0);
        addElement("-text1", new TextElement(env.getPersonName(), pos, Color.YELLOW, false,
                Align.NONE, new Font("GothicE", Font.PLAIN, 40)));
        pos = new Point3d(env.getPersonPosition().x, env.getPersonPosition().y + 50, 0);
        addElement("-text2", new TextElement("Random...", new Color(160, 80, 0), true, Align.NONE,
                pos, new Font("Arial", Font.ITALIC, 14)));
    }
}
