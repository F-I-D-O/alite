package incubator.visprotocol.structprocessor;

import incubator.visprotocol.structure.Structure;

/**
 * Pulles structures from all subprocessors and pushes to output, then pulls structure from the
 * output.
 * 
 * Takes: any structure (not useful)
 * 
 * Creates: product of output, which is composed from inputs
 * 
 * Forward: fills the output
 * 
 * @author Ondrej Milenovsky
 * */
public class LightPullMux extends MultipleProcessor implements Forwarder {

    private StructProcessor output;

    public LightPullMux() {
    }
    
    public LightPullMux(StructProcessor output) {
        this.output = output;
    }

    public void setOutput(StructProcessor output) {
        this.output = output;
    }

    /** this might not be useful */
    @Override
    public void push(Structure newPart) {
        output.push(newPart);
    }

    /** pull from all subprocessors, push to output, return output.pull() */
    @Override
    public Structure pull() {
        forward();
        return output.pull();
    }

    /** pushes from subprocessors to the output */
    @Override
    public void forward() {
        for (StructProcessor pr : getProcessors()) {
            output.push(pr.pull());
        }
    }

}
