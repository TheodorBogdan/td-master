package model;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.EAGER)
    //@JoinColumn(name = "id",table = "message")
    private Set<Message> messageList;

    @OneToMany(fetch = FetchType.EAGER)
    //@JoinColumn(name = "id",table = "message")
    private Set<User> members;

    public Chat() {

    }

    public Chat(int id, Set<Message> messageList, Set<User> members) {
        this.id = id;
        this.messageList = messageList;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(Set<Message> messageList) {
        this.messageList = messageList;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
