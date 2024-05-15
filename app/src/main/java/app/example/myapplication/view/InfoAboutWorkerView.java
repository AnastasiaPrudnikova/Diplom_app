package app.example.myapplication.view;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import app.example.myapplication.db.Specialist;

public interface InfoAboutWorkerView {
    void errorMsg(String msg);
    void getInfoZapis(Map<String, Object> date);
    void getInfoAboutSpecialist(Specialist specialist);
    void getSuccessAdd(String name);
}
