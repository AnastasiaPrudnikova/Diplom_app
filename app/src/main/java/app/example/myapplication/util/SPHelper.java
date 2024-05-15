package app.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import app.example.myapplication.ApplicationLoader;

public class SPHelper {
    public static final String FILE_NAME = "vkr";

    private static SharedPreferences getPrefs() {
        return ApplicationLoader.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEdit() {
        return getPrefs().edit();
    }
    public static class ZapisInfo {
        public static final String DATE = "date";
        public static final String TIME = "time";
        public static final String TYPE_ZAPIS = "type_zapis";
        public static void setTime(String name) {
            getEdit().putString(TIME, name).commit();
        }

        public static String getTime() {
            return getPrefs().getString(TIME, "");
        }

        public static String getDate() {
            return getPrefs().getString(DATE, "");
        }

        public static void setDate(String name) {
            getEdit().putString(DATE, name).commit();
        }
        public static void setTypeZapis(String zapis){getEdit().putString(TYPE_ZAPIS, zapis).commit();}
        public static String getTypeZapis(){return getPrefs().getString(TYPE_ZAPIS,"");}
    }

    public static class PersonInfo {
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String PHONE = "PHONE";

        public static final String LOGIN = "login";
        public static final String TYPE = "type";

        public static final String LAT = "lat";
        public static final String LON = "lon";

        public static void setLat(float lat) {
            getEdit().putFloat(LAT, lat).commit();
        }

        public static float getLat() {
            return getPrefs().getFloat(LAT, 0);
        }
        public static void setLon(float lat) {
            getEdit().putFloat(LON, lat).commit();
        }

        public static float getLon() {
            return getPrefs().getFloat(LON, 0);
        }

        public static void setName(String name) {
            getEdit().putString(NAME, name).commit();
        }

        public static String getName() {
            return getPrefs().getString(NAME, "");
        }

        public static String getSurname() {
            return getPrefs().getString(SURNAME, "");
        }

        public static void setSurname(String name) {
            getEdit().putString(SURNAME, name).commit();
        }

        public static void setPhone(String phone) {
            getEdit().putString(PHONE, phone).commit();
        }

        public static String getPhone() {
            return getPrefs().getString(PHONE, "");
        }

        public static void setLogin(String phone) {
            getEdit().putString(LOGIN, phone).commit();
        }

        public static String getLogin() {
            return getPrefs().getString(LOGIN, "");
        }


        public static void setType(int type) {
            getEdit().putInt(TYPE, type).commit();
        }

        public static int getType() {
            return getPrefs().getInt(TYPE, 0);
        }
    }
}
