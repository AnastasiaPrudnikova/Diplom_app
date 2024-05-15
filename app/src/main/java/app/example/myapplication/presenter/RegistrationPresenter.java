package app.example.myapplication.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import app.example.myapplication.db.Users;
import app.example.myapplication.util.Const;
import app.example.myapplication.view.RegistrationView;

public class RegistrationPresenter {
    private RegistrationView view;

    public RegistrationPresenter(RegistrationView view) {
        this.view = view;
    }

    public void createAccount(Users users){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        String modifiedLogin = users.getLogin().replace(".", "_");

        DatabaseReference usersRef = databaseReference.child("Users");
        DatabaseReference userLoginRef = usersRef.child(modifiedLogin);
        userLoginRef.setValue(users);

    }
}
