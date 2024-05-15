package app.example.myapplication.dialog;

import android.widget.Button;
import android.widget.EditText;

import app.example.myapplication.R;
import app.example.myapplication.db.Specialist;
import butterknife.BindView;

public class AddNewSpecialistBottomDialog extends BaseBottomDialog{
    @BindView(R.id.name) EditText name;
    @BindView(R.id.photo) EditText image;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.type) EditText type;
    @BindView(R.id.stars) EditText stars;
    @BindView(R.id.add) Button add;

    private OnClickAddSpecialist onClickAddSpecialist;

    public AddNewSpecialistBottomDialog(OnClickAddSpecialist onClickAddSpecialist) {
        this.onClickAddSpecialist = onClickAddSpecialist;
    }

    @Override
    protected void initViews() {
        super.initViews();

        add.setOnClickListener(l->{
            if(!name.getText().equals("") && !image.getText().equals("") &&
                    !email.getText().equals("") && !type.getText().equals("") && !stars.getText().equals("") ){
                Specialist specialist = new Specialist(name.getText().toString(), stars.getText().toString(), type.getText().toString(),
                        image.getText().toString(), email.getText().toString());
                onClickAddSpecialist.addSpecialist(specialist);
                dismiss();
            }

        });
    }

    @Override
    protected int layoutId() {
        return R.layout.add_new_specialist_bottom_dialog;
    }

    public interface OnClickAddSpecialist{
        void addSpecialist(Specialist specialist);
    }
}
