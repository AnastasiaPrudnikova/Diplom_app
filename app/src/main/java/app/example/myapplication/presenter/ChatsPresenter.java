package app.example.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.example.myapplication.db.Chats;
import app.example.myapplication.db.Message;
import app.example.myapplication.util.Const;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.ChatsView;

public class ChatsPresenter {
    private ChatsView view;

    public ChatsPresenter(ChatsView view) {
        this.view = view;
    }

    public void getChats(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        String modiferLogin = "admin_admin@mail.ru".replace(".","_");

        DatabaseReference userLoginRef = databaseReference.child("Chats").child(modiferLogin);
        List<Chats> chats = new ArrayList<>();

        userLoginRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chats.clear();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    Chats db = childSnapshot.getValue(Chats.class);
                    if(db!=null)
                        chats.add(db);
                }
                view.getChats(chats);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.errorMsg(error.getMessage());
            }
        });
    }

    public void createMessages(){

        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        Chats userData = new Chats(
                "perov@mem.ru",
                "Петров Петр",
                null
        );
        String login = "admin_admin@mail.ru";
        String login_receiv = "perov@mem.ru";
        String modifiedLogin = login.replace(".", "_"); // Заменяем "." на "_"
        String receiver = login_receiv.replace(".", "_");

        Message message = new Message("admin_admin@mail.ru",
                "perov@mem.ru", "Привет?", "13/25");

        DatabaseReference usersRef = databaseReference.child("Chats").child(modifiedLogin);
        DatabaseReference receiverLogin =  usersRef.child(receiver);
        DatabaseReference messageRef = receiverLogin.child("Messages");
        // Генерируем уникальный ключ для каждого сообщения
        String messageId = messageRef.push().getKey();
        DatabaseReference sendMsg = messageRef.child(messageId);

        receiverLogin.setValue(userData);
        sendMsg.setValue(message);


    }
    public void sendMessage(){

        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        Chats userData = new Chats(
                "admin_admin@mem.ru",
                "Макаров Петр",
                null
        );
        String login = "admin_admin@mail.ru";
        String login_receiv = "perov@mem.ru";
        String modifiedLogin = login.replace(".", "_"); // Заменяем "." на "_"
        String receiver = login_receiv.replace(".", "_");

        Message message = new Message("admin_admin@mail.ru",
                "perov@mem.ru", "Привет?", "13/25");

        DatabaseReference usersRef = databaseReference.child("Chats").child(modifiedLogin);
        DatabaseReference receiverLogin =  usersRef.child(receiver);
        DatabaseReference messageRef = receiverLogin.child("Messages");

        String messageId = messageRef.push().getKey();
        DatabaseReference sendMsg = messageRef.child(messageId);

        sendMsg.setValue(message);


    }
}
