package app.example.myapplication.dialog;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.example.myapplication.R;
import app.example.myapplication.adapter.DateAdapter;
import app.example.myapplication.adapter.TimeAdapter;
import app.example.myapplication.db.Specialist;
import app.example.myapplication.presenter.InfoAboutWorkerPresenter;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.InfoAboutWorkerView;
import butterknife.BindView;

public class InfoAboutWorkerBottomDialog extends BaseBottomDialog implements InfoAboutWorkerView, DateAdapter.OnClickDate, TimeAdapter.OnClickTime {
    private String login;
    @BindView(R.id.date) RecyclerView dateRV;
    @BindView(R.id.time) RecyclerView timeRV;
    @BindView(R.id.image) ImageView image;
    @BindView(R.id.text_name) TextView name;
    @BindView(R.id.btn) Button btn;
    @BindView(R.id.add_btn) Button addBtn;
    @BindView(R.id.text_show) LinearLayout textShow;
    @BindView(R.id.text_edit) LinearLayout textEdit;
    @BindView(R.id.time_et) EditText timeEt;
    @BindView(R.id.date_et) EditText dateEt;
    private InfoAboutWorkerPresenter presenter;
    private DateAdapter dateAdapter;
    private TimeAdapter timeAdapter;
    List<List<String>> date_times;
    private boolean checker = true;
    @Override
    protected void initViews() {
        super.initViews();
        presenter = new InfoAboutWorkerPresenter( this);
        presenter.getSpecialistForId(login);
        presenter.getInfoAboutWorker(login);

        SPHelper.PersonInfo.setType(1);
        btn.setVisibility(SPHelper.PersonInfo.getType() == 1? View.VISIBLE : View.GONE);
        addBtn.setVisibility(SPHelper.PersonInfo.getType() == 2? View.VISIBLE : View.GONE);
        textShow.setVisibility(View.VISIBLE);
        btn.setOnClickListener(l->{
            if (SPHelper.PersonInfo.getType()==0) loginDialog();
            presenter.setRecordUser(SPHelper.ZapisInfo.getDate(),SPHelper.ZapisInfo.getTime(),login, SPHelper.ZapisInfo.getTypeZapis());
        });

        addBtn.setOnClickListener(ll->{
            if(checker) {
                textShow.setVisibility(View.GONE);
                textEdit.setVisibility(View.VISIBLE);
                addBtn.setText("Добавить");
                checker = false;
            } else {
                if(dateEt.getText().toString()!=null && !dateEt.getText().equals("") && timeEt.getText().toString()!=null && !timeEt.getText().equals("")){
                    presenter.createZapis(dateEt.getText().toString(), timeEt.getText().toString(), login);
                }
            }
        });
    }
    public void loginDialog(){
        LoginBottomDialog dialog = new LoginBottomDialog();
        dialog.setCancelable(true);
        dialog.show(getChildFragmentManager(),"login");
    }
    public InfoAboutWorkerBottomDialog(String login) {
        this.login = login;
    }

    public static InfoAboutWorkerBottomDialog newInstance(String id) {
        return  new InfoAboutWorkerBottomDialog(id);
    }
    @Override
    protected int layoutId() {
        return R.layout.info_about_wotker_bottom_dialog;
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getInfoZapis(Map<String, Object> date) {
        List<String> date_name = new ArrayList<>();
        date_times = new ArrayList<>();

        for (Map.Entry<String, Object> entry : date.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            date_name.add(key);

            if (value instanceof Map) {
                Map<String, Object> dateMap = (Map<String, Object>) value;
                List<String> timesList = (List<String>) dateMap.get("times");
                date_times.add(timesList);
            }
        }



        dateAdapter = new DateAdapter(getContext(), date_name, this::onClick);
        dateRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        dateRV.setAdapter(dateAdapter);

    }

    @Override
    public void getInfoAboutSpecialist(Specialist specialist) {
        name.setText(specialist.getName());
        Picasso.get().load(specialist.getImage()).into(image);
        SPHelper.ZapisInfo.setTypeZapis(specialist.getType());
    }

    @Override
    public void getSuccessAdd(String name) {
        Toast.makeText(getContext(),name, Toast.LENGTH_SHORT).show();
        presenter.removeTime(SPHelper.ZapisInfo.getDate(), SPHelper.ZapisInfo.getTime(),login);
//        dismiss();
    }


    @Override
    public void onClick(String date, int id) {
        List<String> time_on_date = new ArrayList<>(date_times.get(id));
        Log.d("KKKKKKK", time_on_date.toString());

        timeAdapter = new TimeAdapter(getContext(), time_on_date, this::onClick);
        timeRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        timeRV.setAdapter(timeAdapter);

    }

    @Override
    public void onClick(String date, String time) {

    }
}
