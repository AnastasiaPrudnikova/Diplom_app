package app.example.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.example.myapplication.db.Message;
import app.example.myapplication.util.Const;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.SupportView;

public class SupportPresenter {
    private SupportView view;

    public SupportPresenter(SupportView view) {
        this.view = view;
    }

    public void getMessages(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        String modiferLogin = "admin_admin@mail.ru".replace(".","_");
        String modiferRecerv = SPHelper.PersonInfo.getLogin().replace(".","_");

        DatabaseReference userLoginRef = databaseReference.child("Chats").child(modiferLogin).child(modiferRecerv).child("Messages");
        List<Message> messages = new ArrayList<>();

        userLoginRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    Message db = childSnapshot.getValue(Message.class);
                    if(db!=null)
                        messages.add(db);
                }
                view.getMessages(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.errorMsg(error.getMessage());
            }
        });
    }
    private String getTrueType(String text) {
        return text.replace(".", "_");
    }

    private String getTime(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");
        return dateFormat.format(currentDate);
    }
    public void sendMessages(String msg){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();

        String modifiedLogin = "admin_admin@mail.ru".replace(".", "_");
        String receiver = SPHelper.PersonInfo.getLogin().replace(".", "_");

        Message message = new Message(SPHelper.PersonInfo.getLogin(),
                "admin_admin@mail.ru", msg, getTime());

        DatabaseReference usersRef = databaseReference.child("Chats").child(modifiedLogin);
        DatabaseReference receiverLogin =  usersRef.child(receiver);
        DatabaseReference messageRef = receiverLogin.child("Messages");

        String messageId = messageRef.push().getKey();
        DatabaseReference sendMsg = messageRef.child(messageId);

        sendMsg.setValue(message);

        view.successSend();

    }

}
