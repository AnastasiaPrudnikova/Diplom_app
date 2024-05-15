package app.example.myapplication;


import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

public class ApplicationLoader extends MultiDexApplication {
    public static ApplicationLoader instance;
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // disable dark theme
        instance = this;
    }
    public static ApplicationLoader getInstance() {
        return instance;
    }

}
