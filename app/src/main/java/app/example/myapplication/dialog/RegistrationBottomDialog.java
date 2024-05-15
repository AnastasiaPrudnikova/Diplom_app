package app.example.myapplication.dialog;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.example.myapplication.R;
import app.example.myapplication.db.Users;
import app.example.myapplication.presenter.RegistrationPresenter;
import app.example.myapplication.view.RegistrationView;
import butterknife.BindView;

public class RegistrationBottomDialog extends BaseBottomDialog implements RegistrationView {

    @BindView(R.id.imya) EditText imya;
    @BindView(R.id.familia) EditText familia;
    @BindView(R.id.login) EditText email;
    @BindView(R.id.btn) Button btn;
    @BindView(R.id.phone) EditText phone;
    @BindView(R.id.password) EditText password;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private RegistrationPresenter presenter;

    @Override
    protected void initViews() {
        super.initViews();

        presenter = new RegistrationPresenter(this);
        mAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(l->{
            if(!imya.getText().toString().equals("") && !familia.getText().toString().equals("")
                    && !email.getText().toString().equals("") && !phone.getText().toString().equals("") && !password.getText().toString().equals("")){
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task->{
                    if(task.isSuccessful()){
                        presenter.createAccount(new Users(imya.getText().toString(), familia.getText().toString(), email.getText().toString(), "Женский",
                                phone.getText().toString(), 1));
                        user = mAuth.getCurrentUser();
                        user.sendEmailVerification();
                        Toast.makeText(getContext(), "На ваш e-mail было отправлено письмо с сылкой на подтверждение аккаунта.", Toast.LENGTH_LONG).show();
                        getDialog().dismiss();
                    } else (Toast.makeText(getContext(), "Произошла ошибка! \n Повторите попытку позже", Toast.LENGTH_LONG)).show();
                }).addOnFailureListener(e->Toast.makeText(getContext(),e.getLocalizedMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.registration_bottom_dialog;
    }
}
