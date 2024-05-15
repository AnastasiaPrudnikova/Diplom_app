package app.example.myapplication.fragment.user;

import android.os.Bundle;
import android.widget.TextView;

import app.example.myapplication.R;
import app.example.myapplication.fragment.BaseFragment;
import app.example.myapplication.util.SPHelper;
import butterknife.BindView;

public class ProfileFragment extends BaseFragment {
    @BindView(R.id.name_user) TextView name;
    @BindView(R.id.email) TextView email;
    @BindView(R.id.phone) TextView phone;

    @Override
    protected void initViews() {
        super.initViews();

        name.setText(SPHelper.PersonInfo.getName()
        + " " + SPHelper.PersonInfo.getSurname());
        phone.setText(SPHelper.PersonInfo.getPhone());
        email.setText(SPHelper.PersonInfo.getLogin());
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    @Override
    protected int layoutId() {
        return R.layout.profile_frgament;
    }
}
