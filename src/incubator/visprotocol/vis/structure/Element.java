package incubator.visprotocol.vis.structure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Leaf element in structure. Type is used to determine painter. Change flag is
 * used to create or delete associated vis object. Must have some type.
 * 
 * @author Ondrej Milenovsky
 * */
public class Element implements Serializable {

    private static final long serialVersionUID = -2263640210556341841L;

    private final String id;
    private final String type;
    private final Map<String, Object> parameters;

    public Element(String id, String type) {
        this.id = id;
        this.type = type;
        parameters = new HashMap<String, Object>(2);
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean containsParameter(String id) {
        return parameters.containsKey(id);
    }

    public Object getParameter(String id) {
        return parameters.get(id);
    }

    /** returns parameter, if not contains, returns null */
    @SuppressWarnings("unchecked")
    public <C extends Object> C getParameter(String id, Class<C> clazz) {
        return (C) parameters.get(id);
    }

    public void setParameter(String id, Object value) {
        parameters.put(id, value);
    }

    public Collection<String> getParamIds() {
        return parameters.keySet();
    }

    public Collection<Object> getParameters() {
        return parameters.values();
    }

    /**
     * elements must equal ! taken all parameters and change flag and updated
     * current state
     */
    public void update(Element e) {
        if (!equals(e)) {
            throw new RuntimeException("Merging " + getId() + " and " + e.getId() + " different elements");
        }
        parameters.putAll(e.parameters);
    }

    public boolean isEmpty() {
        return parameters.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Element e2 = (Element) obj;
        return id.equals(e2.getId()) && type.equals(e2.getType());
    }

}
