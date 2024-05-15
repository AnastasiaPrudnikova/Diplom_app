package app.example.myapplication.presenter;

import android.util.Log;

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

import app.example.myapplication.db.Record;
import app.example.myapplication.util.Const;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.RecordView;

public class RecordPresenter {
    private RecordView view;

    public RecordPresenter(RecordView view) {
        this.view = view;
    }


    public void getRecordUsers(String login){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference().child("UserRecords");
        String modifiedEmail = login.replace(".", "_");

        List<Record> userRecords = new ArrayList<>();

        databaseReference.child(modifiedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Record userRecord = snapshot.getValue(Record.class);
                    userRecords.add(userRecord);
                }

                view.getRecords(userRecords);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TAG", "Failed to read value.", databaseError.toException());
            }
        });


    }
}
