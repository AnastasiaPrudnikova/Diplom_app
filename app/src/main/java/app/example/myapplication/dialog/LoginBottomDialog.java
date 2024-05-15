package app.example.myapplication.dialog;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import app.example.myapplication.R;
import app.example.myapplication.activity.NonActiveUserActivity;
import app.example.myapplication.activity.UserActivity;
import app.example.myapplication.fragment.reg.LosePasswordFragment;
import app.example.myapplication.fragment.reg.RegistrationFragment;
import app.example.myapplication.presenter.LoginPresenter;
import app.example.myapplication.view.LoginView;
import butterknife.BindView;

public class LoginBottomDialog extends BaseBottomDialog implements LoginView {
    @BindView(R.id.password) EditText password;
    @BindView(R.id.login) EditText login;
    @BindView(R.id.btn) Button btn;
    @BindView(R.id.lose_pass) TextView losePass;
    @BindView(R.id.reg) TextView regist;
    private LoginPresenter presenter;
    private FirebaseAuth mAuth;
    @Override
    protected void initViews() {
        super.initViews();
        presenter = new LoginPresenter(this);

        btn.setOnClickListener(l->{
            if(!login.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                auth(login.getText().toString(), password.getText().toString());
            } else Toast.makeText(getContext(), "Введите логин/пароль!", Toast.LENGTH_SHORT).show();
        });

        regist.setOnClickListener(l->{
            Log.d("YourTag", "Button clicked");
            dismiss();
            RegistrationBottomDialog dialog = new RegistrationBottomDialog();
            dialog.show(getFragmentManager(), "kek");
        });
        losePass.setOnClickListener(l->{
            ((NonActiveUserActivity)getActivity()).replaceFragment(LosePasswordFragment.newInstance(), false);
            dismiss();
        });
    }

    private void auth(String login, String pass) {
        mAuth = FirebaseAuth.getInstance();
        if(login!=null && !login.isEmpty() && pass!=null && !pass.isEmpty()){
            mAuth.signInWithEmailAndPassword(login, pass)
                    .addOnCompleteListener(l->{
                        if(l.isSuccessful()){
                            presenter.getUserInfo(login);
                        } else Toast.makeText(getContext(), "Ошибка аунтефикации!", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.login_bottom_dialog;
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMsg() {
        dismiss();
        startActivity(new Intent(getActivity(), UserActivity.class));
    }
}
