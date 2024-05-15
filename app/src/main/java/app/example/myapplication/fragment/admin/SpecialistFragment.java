package app.example.myapplication.fragment.admin;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.adapter.SpecialistAdminAdapter;
import app.example.myapplication.db.Specialist;
import app.example.myapplication.dialog.AddNewSpecialistBottomDialog;
import app.example.myapplication.dialog.InfoAboutWorkerBottomDialog;
import app.example.myapplication.fragment.BaseFragment;
import app.example.myapplication.presenter.SpecialistAdminPresenter;
import app.example.myapplication.view.SpecialistAdminView;
import butterknife.BindView;

public class SpecialistFragment extends BaseFragment implements SpecialistAdminView,
        AddNewSpecialistBottomDialog.OnClickAddSpecialist,SpecialistAdminAdapter.OnClickRemoveSpecialist, SpecialistAdminAdapter.OnClickSpecialist{

    @BindView(R.id.search) EditText search;
    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.add) ImageView add;

    private SpecialistAdminPresenter presenter;
    private SpecialistAdminAdapter adapter;

    public static SpecialistFragment newInstance() {
        return  new SpecialistFragment();
    }
    @Override
    protected void initViews() {
        super.initViews();

        presenter = new SpecialistAdminPresenter(this);
        presenter.getSpecialist();

        add.setOnClickListener(l->{
            AddNewSpecialistBottomDialog dialog = new AddNewSpecialistBottomDialog(this::addSpecialist);
            dialog.show(getChildFragmentManager(), "show");
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.specialis_fragment;
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSpecialist(List<Specialist> data) {
        if(data!=null){
            adapter = new SpecialistAdminAdapter(getContext(), data, this, this);
            rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rv.setAdapter(adapter);
        }
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickSpecialist(String email) {
        presenter.deleteSpecialist(email);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addSpecialist(Specialist specialist) {
        presenter.getCreate(specialist.getName(), specialist.getImage(), specialist.getEmail(), specialist.getStars(), specialist.getType());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clickInfoSpecialist(String email) {
        InfoAboutWorkerBottomDialog dialog = new InfoAboutWorkerBottomDialog(email);
        dialog.show(getChildFragmentManager(), "lll");
    }
}
