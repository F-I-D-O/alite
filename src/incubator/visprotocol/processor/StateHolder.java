package incubator.visprotocol.processor;

import incubator.visprotocol.structure.Structure;

/**
 * @author Ondrej Milenovsky
 * */
public interface StateHolder extends StructProcessor {
    public Structure getState();
}
