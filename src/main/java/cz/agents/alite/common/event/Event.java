package cz.agents.alite.common.event;


/**
 * An event is an abstract description of anything which can happen.
 *
 * The event is described by a time in which the event will happen, a type
 * which can be arbitrarily redefined by the needs of the application, a content
 * carrying the payload of what have happened (i.e. parametrization of the event),
 * and recipient, who will be affected by the event (the owner is an
 * deprecated version of the recipient).
 *
 * The events are {@link Comparable} by its time.
 *
 * @author Antonin Komenda
 */
public final class Event implements Comparable<Event> {

    private long time;
    private EventType type;
    private String owner;
    private Object content;
    private EventHandler recipient;

    Event(long time, EventType type, EventHandler recipient, String owner, Object content) {
        this.time = time;
        this.recipient = recipient;
        this.type = type;
        this.owner = owner;
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public String getOwner() {
        return owner;
    }

    public EventType getType() {
        return type;
    }

    public long getTime() {
        return time;
    }

    public EventHandler getRecipient() {
        return recipient;
    }

    public boolean isType(EventType eventType) {
        return type.equals(eventType);
    }

    @Override
    public int compareTo(Event event) {
        return (time < event.time ? -1 : (time == event.time ? 0 : 1));
    }

    @Override
    public String toString() {
        return time + ": " + type + "(" + owner + "): " + content;
    }

}