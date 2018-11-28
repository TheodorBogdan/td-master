package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Column
    private String content;

    public Message(String content, int id, Chat chat,User sender) {
        this.content = content;
        this.id = id;
        this.chat = chat;
        this.sender = sender;
    }

    public Message() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    //@JoinColumn(name = "id", table = "user")
    private Chat chat;

    @ManyToOne
    //@JoinColumn(name = "id", table = "user")
    private User sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChatId(Chat chat) {
        this.chat = chat;
    }
}
