package cz.agents.alite.communication;

import java.util.Collection;
import java.util.LinkedList;

import cz.agents.alite.communication.content.Content;

/**
 *
 * @author vokrinek
 */
public final class Message {

    private final long id;
    private final String sender;
    private final Collection<String> receivers = new LinkedList<String>();
    private final Content content;

    Message(String sender, Content content, long id) {

        if (content == null) {
            throw new IllegalArgumentException("null Content is not permitted");
        }

        this.sender = sender;
        this.content = content;
        this.id = id;
    }

    /**
     * Get the value of receiver
     *
     * @return the value of receiver
     */
    public Collection<String> getReceivers() {
        return receivers;
    }

    /**
     * Set the value of receiver
     *
     * @param receiver new value of receiver
     */
    public void addReceivers(Collection<String> newReceivers) {
        receivers.addAll(newReceivers);
    }

    public void addReceiver(String receiver) {
        receivers.add(receiver);
    }

    public Content getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    /**
     * Get the value of senderID
     *
     * @return the value of senderID
     */
    public String getSender() {
        return sender;
    }
}
