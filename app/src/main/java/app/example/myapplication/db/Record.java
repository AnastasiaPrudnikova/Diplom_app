package app.example.myapplication.db;

public class Record {
    private String date;
    private String time;
    private String type;
    private String worker;
    private String email;

    public Record() {
    }

    public Record(String date, String time, String type, String worker, String email) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.worker = worker;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

}

