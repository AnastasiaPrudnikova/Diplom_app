package app.example.myapplication.view;

import java.util.List;

import app.example.myapplication.db.Message;

public interface MessageView {
    void getMessages(List<Message> messages);

    void errorMsg(String message);
    void successSend();
}
