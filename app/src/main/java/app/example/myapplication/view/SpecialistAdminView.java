package app.example.myapplication.view;

import java.util.List;

import app.example.myapplication.db.Specialist;

public interface SpecialistAdminView {
    void errorMsg(String msg);
    void getSpecialist(List<Specialist> data);
    void successMsg(String msg);
}
