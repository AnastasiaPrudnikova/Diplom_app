package app.example.myapplication.fragment.admin;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.adapter.RecordsUsersAdapter;
import app.example.myapplication.db.Record;
import app.example.myapplication.fragment.BaseFragment;
import app.example.myapplication.presenter.RecordsUserPresenter;
import app.example.myapplication.view.RecordsUserView;
import butterknife.BindView;

public class RecordsUserFragment extends BaseFragment implements RecordsUserView, RecordsUsersAdapter.OnClickRemoveRecords {
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.rv)
    RecyclerView rv;
    private RecordsUsersAdapter adapter;
    private RecordsUserPresenter presenter;

    public static RecordsUserFragment newInstance() {
        return new RecordsUserFragment();
    }
    @Override
    protected void initViews() {
        super.initViews();
        presenter = new RecordsUserPresenter(this);
        presenter.getRecords();
    }

    @Override
    protected int layoutId() {
        return R.layout.recoeds_user_fragment;
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRecords(List<Record> data) {
        if(data!=null){
            adapter = new RecordsUsersAdapter(getContext(), data, this);
            rv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            rv.setAdapter(adapter);
        }
    }

    @Override
    public void clickRecord(String email, Record record) {
        presenter.deleteRecord(email, record);
        adapter.notifyDataSetChanged();
    }
}
