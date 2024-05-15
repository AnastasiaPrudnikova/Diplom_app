package app.example.myapplication.presenter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.example.myapplication.db.Specialist;
import app.example.myapplication.util.Const;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.SpecialistView;

public class SpecialistPresenter {
    private SpecialistView view;

    public SpecialistPresenter(SpecialistView view) {
        this.view = view;
    }

    public void getCreate(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference().child("SpecialistZapis");

        Map<String, Object> dateMap = new HashMap<>();
        dateMap.put("time_date", "14.04.2024");

        List<String> timesList = new ArrayList<>();
        timesList.add("18:30");
        timesList.add("20:30");
        timesList.add("8:30");
        timesList.add("16:10");
        dateMap.put("times", timesList);

        String login = "petrova.e@mail.ru";
        String modifiedLogin = login.replace(".", "_");

        DatabaseReference kapRef = databaseReference.child(modifiedLogin).push();
        kapRef.setValue(dateMap);


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
}
