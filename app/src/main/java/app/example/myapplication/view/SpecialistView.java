package app.example.myapplication.view;

import java.util.List;

import app.example.myapplication.db.Specialist;

public interface SpecialistView {
    void errorMsg(String msg);
    void getSpecialist(List<Specialist> data);
}
