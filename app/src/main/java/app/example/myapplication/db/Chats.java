package app.example.myapplication.db;

import java.util.List;

public class Chats {
    public String login;
    public String name;
    public List<Message> message;
    public Chats() {}

    public Chats(String login, String name, List<Message> message) {
        this.login = login;
        this.name = name;
        this.message = message;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
