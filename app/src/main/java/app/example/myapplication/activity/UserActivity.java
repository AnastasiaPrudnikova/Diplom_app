package app.example.myapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.example.myapplication.R;
import app.example.myapplication.fragment.user.SupportUserFragment;
import app.example.myapplication.fragment.user.ProfileFragment;
import app.example.myapplication.fragment.user.RecordFragment;
import app.example.myapplication.fragment.user.StartFragment;
import butterknife.BindView;

public class UserActivity extends BaseActivity {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @Override
    protected void initViews(@Nullable Bundle saveInstanceState) {
        bottomNavigationView.setOnNavigationItemSelectedListener(l->{
            switch (l.getItemId()){
                case R.id.item_1:
                    replaceFragment(StartFragment.newInstance(), false);
                    break;
                case R.id.item_2:
                    replaceFragment(RecordFragment.newInstance(), false);
                    break;
                case R.id.item_3:
                    replaceFragment(SupportUserFragment.newInstance(), false);
                    break;
                case R.id.item_4:
                    replaceFragment(ProfileFragment.newInstance(), false);
                    break;
            }
            return true;
        });
        bottomNavigationView.getMenu().findItem(R.id.item_1).setChecked(true);
        replaceFragment(StartFragment.newInstance(), false);
    }

    @Override
    protected int layoutResId() {
        return R.layout.user_activity;
    }

    @Override
    protected int titleResId() {
        return 0;
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }
}