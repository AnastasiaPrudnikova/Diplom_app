package app.example.myapplication.view;

import java.util.List;

import app.example.myapplication.db.Chats;

public interface ChatsView {
    void getChats(List<Chats> chats);
    void errorMsg(String msg);
}
