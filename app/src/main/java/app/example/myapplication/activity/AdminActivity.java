package app.example.myapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.example.myapplication.R;
import app.example.myapplication.fragment.admin.RecordsUserFragment;
import app.example.myapplication.fragment.admin.SpecialistFragment;
import app.example.myapplication.fragment.admin.chats.ChatFragment;
import app.example.myapplication.fragment.user.ProfileFragment;
import app.example.myapplication.fragment.user.RecordFragment;
import app.example.myapplication.fragment.user.StartFragment;
import app.example.myapplication.fragment.user.SupportUserFragment;
import butterknife.BindView;

public class AdminActivity extends BaseActivity {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void initViews(@Nullable Bundle saveInstanceState) {
        bottomNavigationView.setOnNavigationItemSelectedListener(l -> {
            switch (l.getItemId()) {
                case R.id.item_1:
                    replaceFragment(SpecialistFragment.newInstance(), false);
                    break;
                case R.id.item_2:
                    replaceFragment(RecordsUserFragment.newInstance(), false);
                    break;
                case R.id.item_3:
                    replaceFragment(ChatFragment.newInstance(), false);
                    break;
            }
            return true;
        });
        bottomNavigationView.getMenu().findItem(R.id.item_1).setChecked(true);
        replaceFragment(SpecialistFragment.newInstance(), false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    protected int layoutResId() {
        return R.layout.admin_activity;
    }

    @Override
    protected int titleResId() {
        return 0;
    }
}
