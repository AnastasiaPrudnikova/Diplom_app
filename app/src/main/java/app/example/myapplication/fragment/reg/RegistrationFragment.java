package app.example.myapplication.fragment.reg;

import android.os.Bundle;

import app.example.myapplication.fragment.BaseFragment;

public class RegistrationFragment extends BaseFragment {
    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }
    @Override
    protected int layoutId() {
        return 0;
    }
}
