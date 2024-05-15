package app.example.myapplication.view;

import java.util.List;

import app.example.myapplication.db.Record;

public interface RecordsUserView {
    void errorMsg(String msg);
    void getRecords(List<Record> data);
}
