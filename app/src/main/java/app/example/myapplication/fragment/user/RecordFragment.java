package app.example.myapplication.fragment.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.prefs.AbstractPreferences;

import app.example.myapplication.R;
import app.example.myapplication.adapter.RecordAdapter;
import app.example.myapplication.db.Record;
import app.example.myapplication.fragment.BaseFragment;
import app.example.myapplication.presenter.RecordPresenter;
import app.example.myapplication.util.SPHelper;
import app.example.myapplication.view.RecordView;
import butterknife.BindView;

public class RecordFragment extends BaseFragment implements RecordView {
    @BindView(R.id.rv)
    RecyclerView rv;
    private RecordAdapter adapter;
    private RecordPresenter presenter;
    public static RecordFragment newInstance() {
        return new RecordFragment();
    }

    @Override
    protected void initViews() {
        super.initViews();
        presenter = new RecordPresenter(this);
        SPHelper.PersonInfo.setLogin("kap.moral@mail.ru");
        presenter.getRecordUsers(SPHelper.PersonInfo.getLogin());
    }

    @Override
    protected int layoutId() {
        return R.layout.record_fragment;
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRecords(List<Record> data) {
        if(data!=null){
            Log.d("KEKKKK", data.get(0).getDate());
            adapter = new RecordAdapter(getContext(), data);
            rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, true));
            rv.setAdapter(adapter);
        }
    }
}
