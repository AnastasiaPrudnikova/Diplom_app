package app.example.myapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import app.example.myapplication.R;
import app.example.myapplication.util.SPHelper;

public class MainActivity extends BaseActivity {

    @Override
    protected void initViews(@Nullable Bundle saveInstanceState) {
            if (SPHelper.PersonInfo.getType()==0) startActivity(new Intent(MainActivity.this, NonActiveUserActivity.class));
           else if (SPHelper.PersonInfo.getType() == 1)
            startActivity(new Intent(MainActivity.this, UserActivity.class));
           else if (SPHelper.PersonInfo.getType() == 2)startActivity(new Intent(MainActivity.this, AdminActivity.class));
    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int titleResId() {
        return 0;
    }
}