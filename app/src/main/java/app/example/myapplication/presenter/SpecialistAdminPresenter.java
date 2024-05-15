package app.example.myapplication.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.example.myapplication.db.Specialist;
import app.example.myapplication.util.Const;
import app.example.myapplication.view.SpecialistAdminView;

public class SpecialistAdminPresenter {
    private SpecialistAdminView view;

    public SpecialistAdminPresenter(SpecialistAdminView view) {
        this.view = view;
    }
    public void getCreate(String name, String image, String email, String stars, String type){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        Specialist specialist = new Specialist(
                name, stars, type, image, email
        );

        DatabaseReference usersRef = databaseReference.child("Specialist");
        DatabaseReference userLoginRef = usersRef.push();
        userLoginRef.setValue(specialist);
    }

    public void getSpecialist(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference();
        DatabaseReference userLoginRef = databaseReference.child("Specialist");
        List<Specialist> specialist = new ArrayList<>();

        userLoginRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                specialist.clear();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    Specialist db = childSnapshot.getValue(Specialist.class);
                    if(db!=null)
                        specialist.add(db);
                }
                view.getSpecialist(specialist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.errorMsg(error.getMessage());
            }
        });
    }

    public void deleteSpecialist(String email){

        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference("Specialist");

        Query query = databaseReference.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    dataSnapshot1.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error while deleting specialist", databaseError.toException());
            }
        });
    }

}
