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

import app.example.myapplication.db.Specialist;
import app.example.myapplication.db.SpecialistIfoZapis;
import app.example.myapplication.util.Const;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.InfoAboutWorkerView;

public class InfoAboutWorkerPresenter {
    private InfoAboutWorkerView view;

    public InfoAboutWorkerPresenter(InfoAboutWorkerView view) {
        this.view = view;
    }

    public void getInfoAboutWorker(String login) {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference().child("SpecialistZapis");

        String modifiedLogin = login.replace(".", "_");

        DatabaseReference kapRef = databaseReference.child(modifiedLogin);
        kapRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> specialistData = new HashMap<>();

                    for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                        Map<String, Object> dateMap = (Map<String, Object>) dateSnapshot.getValue();
                        if (dateMap != null) {
                            String timeDate = (String) dateMap.get("time_date");
                            List<String> timesList = (List<String>) dateMap.get("times");

                            if (timeDate != null && timesList != null) {
                                specialistData.put(timeDate, dateMap);
                            }
                        }
                    }
                    view.getInfoZapis(specialistData);
                } else {
                    view.errorMsg("No data available");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.errorMsg(databaseError.getMessage());
            }
        });
    }

    public void removeTime(String date, String time, String login){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference().child("SpecialistZapis");

        String modifiedLogin = login.replace(".", "_");

        DatabaseReference kapRef = databaseReference.child(modifiedLogin);
        kapRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Map<String, Object> data = (Map<String, Object>) snapshot.getValue();

                        // Проверяем совпадение даты
                        String dateTime = (String) data.get("time_date");
                        if (dateTime != null && dateTime.equals(date)) {
                            // Находим список временных слотов и удаляем из него указанное время
                            List<String> timesList = (List<String>) data.get("times");
                            if (timesList != null && timesList.contains(time)) {
                                timesList.remove(time);
                                // Обновляем список временных слотов в базе данных
                                snapshot.getRef().child("times").setValue(timesList);
                                System.out.println("Time removed: " + time + " for date: " + date + " and specialist: " + login);
                                return; // Выходим из метода после удаления времени
                            }
                        }
                    }
                } else {
                    view.errorMsg("No data available");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.errorMsg(databaseError.getMessage());
            }
        });
    }


    public void createZapis(String date, String time, String login) {
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference specialistZapisRef = database.getReference().child("SpecialistZapis");
        String modifiedLogin = login.replace(".", "_");

        specialistZapisRef.child(modifiedLogin).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean dateExists = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> data = (Map<String, Object>) snapshot.getValue();
                    String existingDate = (String) data.get("time_date");
                    if (existingDate.equals(date)) {
                        DatabaseReference existingDateRef = specialistZapisRef.child(modifiedLogin).child(snapshot.getKey());
                        List<String> timesList = (List<String>) data.get("times");
                        timesList.add(time);
                        existingDateRef.child("times").setValue(timesList);
                        dateExists = true;
                        break;
                    }
                }

                if (!dateExists) {
                    DatabaseReference newDateRef = specialistZapisRef.child(modifiedLogin).push();
                    Map<String, Object> dateMap = new HashMap<>();
                    dateMap.put("time_date", date);
                    List<String> timesList = new ArrayList<>();
                    timesList.add(time);
                    dateMap.put("times", timesList);
                    newDateRef.setValue(dateMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибок, если необходимо
            }
        });
    }

    public void getSpecialistForId(String login){
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

                for(int i = 0 ; i< specialist.size(); i++){
                    if(specialist.get(i).getEmail().equals(login))
                        view.getInfoAboutSpecialist(specialist.get(i));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.errorMsg(error.getMessage());
            }
        });
    }

    public void setRecordUser(String date, String time, String type, String worker){
        FirebaseDatabase database = FirebaseDatabase.getInstance(Const.DB_URL);
        DatabaseReference databaseReference = database.getReference().child("UserRecords");

        String email = SPHelper.PersonInfo.getLogin();
        String modifiedEmail = email.replace(".", "_");

        DatabaseReference userRef = databaseReference.child(modifiedEmail);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Запись существует, обновляем данные
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("date", date);
                    userMap.put("time", time);
                    userMap.put("type", type);
                    userMap.put("worker", worker);
                    userMap.put("email", SPHelper.PersonInfo.getLogin());

                    userRef.push().setValue(userMap);
                } else {
                    // Запись не существует, создаем новую запись
                    Map<String, Object> userMap1 = new HashMap<>();
                    userMap1.put("date", date);
                    userMap1.put("time", time);
                    userMap1.put("type", type);
                    userMap1.put("worker", worker);
                    userMap1.put("email", SPHelper.PersonInfo.getLogin());

                    userRef.setValue(userMap1);
                }
                view.getSuccessAdd("Запись успешно добавлена");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                view.getSuccessAdd("Произошла ошибка добавления");

            }
        });

    }
}
