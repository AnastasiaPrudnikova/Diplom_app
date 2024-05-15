package app.example.myapplication.db;

public class Specialist {
    public String name;
    public String stars;
    public String type;
    public String image;
    public String email;

    public Specialist() {
    }

    public Specialist(String name, String stars, String type, String image, String email) {
        this.name = name;
        this.stars = stars;
        this.type = type;
        this.image = image;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }
}
