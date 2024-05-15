package app.example.myapplication.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.example.myapplication.db.Record;
import app.example.myapplication.util.Const;
import app.example.myapplication.view.RecordsUserView;

public class RecordsUserPresenter {
    private RecordsUserView view;

    public RecordsUserPresenter(RecordsUserView view) {
        this.view = view;
    }

    public void getRecords(){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference().child("UserRecords");

        List<Record> userRecords = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot emailSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot recordSnapshot : emailSnapshot.getChildren()) {
                        Record userRecord = recordSnapshot.getValue(Record.class);
                        userRecords.add(userRecord);
                    }
                }

                Log.d("KKKKKKKK", userRecords.get(0).toString());
                view.getRecords(userRecords);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TAG", "Failed to read value.", databaseError.toException());
                view.errorMsg(databaseError.getMessage());
            }
        });


    }

    public void deleteRecord(String login, Record record) {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference().child("UserRecords");
        String modifiedEmail = login.replace(".", "_");

        databaseReference.child(modifiedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Record userRecord = snapshot.getValue(Record.class);
                    if (userRecord.equals(record)) {
                        snapshot.getRef().removeValue();
                        view.errorMsg("Успешно");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TAG", "Failed to read value.", databaseError.toException());
                view.errorMsg(databaseError.getMessage());
            }
        });
    }

}
