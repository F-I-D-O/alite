package incubator.visprotocol.vis.layer.example;

import incubator.visprotocol.creator.TestCreator.ExampleEnvironment;
import incubator.visprotocol.structprocessor.StructProcessor;
import incubator.visprotocol.structure.Element;
import incubator.visprotocol.structure.Structure;
import incubator.visprotocol.structure.key.PointKeys;
import incubator.visprotocol.vis.layer.proxy.TypedProxyLayer;

import java.awt.Color;
import java.util.Map;
import java.util.Set;

import javax.vecmath.Point2d;

public class ZombieProxyLayer extends TypedProxyLayer {

    private final ExampleEnvironment env;

    public ZombieProxyLayer(ExampleEnvironment env, Map<String, Set<String>> types) {
        super(types);
        this.env = env;
    }

    @Override
    public void fillProcessor(StructProcessor processor) {
        Structure struct = new Structure();
        Element e = struct.getRoot("Undead land").getFolder("Zombies").getElement(
                env.getPersonName(), PointKeys.TYPE);
        Point2d pos = new Point2d(env.getPersonPosition().x, env.getPersonPosition().y);
        e.setParameter(PointKeys.POS, pos);
        e.setParameter(PointKeys.WIDTH, 6.0);
        e.setParameter(PointKeys.COLOR, new Color(0, Math.min(255, env.getPersonHealth()), 0));
        e.setParameter(PointKeys.CONSTANT_SIZE, false);
        processor.push(struct);
    }

}
