package app.example.myapplication.db;

public class Users {
    public String name;
    public String surname;
    public String login;
    public String pol;
    public String phone;
    public int type;

    public Users() {}

    public Users(String name, String surname, String login, String pol, String phone, int type) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.pol = pol;
        this.phone = phone;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
