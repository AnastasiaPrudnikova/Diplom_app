package app.example.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.example.myapplication.db.Users;
import app.example.myapplication.util.Const;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.LoginView;

public class LoginPresenter {
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void getCreate(){

        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        Users userData = new Users(
                "Лилия",
                "Петрова",
                "aristvodolaz@mail.ru",
                "женский",
                "+79208325254",
                2
        );
        String login = "aristvodolaz@mail.ru";
        String modifiedLogin = login.replace(".", "_"); // Заменяем "." на "_"

        DatabaseReference usersRef = databaseReference.child("Users");
        DatabaseReference userLoginRef = usersRef.child(modifiedLogin);
        userLoginRef.setValue(userData);


    }

    public void getUserInfo(String login){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        String modiferLogin = login.replace(".","_");

        DatabaseReference userLoginRef = databaseReference.child("Users").child(modiferLogin);

        userLoginRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Users userDB = snapshot.getValue(Users.class);
                    if(userDB!=null){
                        SPHelper.PersonInfo.setLogin(userDB.getLogin());
                        SPHelper.PersonInfo.setName(userDB.getName());
                        SPHelper.PersonInfo.setSurname(userDB.getSurname());
                        SPHelper.PersonInfo.setType(userDB.getType());
                        SPHelper.PersonInfo.setPhone(userDB.getPhone());
                        view.successMsg();
                    }
                } else view.errorMsg("Пользователь не зарегистрирован!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.errorMsg(error.getMessage());
            }
        });
    }
}
