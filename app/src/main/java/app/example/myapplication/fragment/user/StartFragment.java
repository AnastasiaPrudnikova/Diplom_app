package app.example.myapplication.fragment.user;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.adapter.PromoAdapter;
import app.example.myapplication.adapter.SpecialistAdapter;
import app.example.myapplication.db.Specialist;
import app.example.myapplication.dialog.InfoAboutWorkerBottomDialog;
import app.example.myapplication.fragment.BaseFragment;
import app.example.myapplication.presenter.SpecialistPresenter;
import app.example.myapplication.view.SpecialistView;
import butterknife.BindView;

public class StartFragment extends BaseFragment implements SpecialistAdapter.OnClickItem, SpecialistView {
    @BindView(R.id.rv_card) RecyclerView rvCard;
    @BindView(R.id.rv_spec) RecyclerView rvSpec;

    private SpecialistPresenter presenter;
    private PromoAdapter adapter;
    private SpecialistAdapter adapterSpecialist;
    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Override
    protected void initViews() {
        super.initViews();
        presenter = new SpecialistPresenter(this);

        adapter = new PromoAdapter(getContext());
        rvCard.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCard.setAdapter(adapter);

        presenter.getSpecialist();
    }

    @Override
    protected int layoutId() {
        return R.layout.start_fragment;
    }

    @Override
    public void onClick(String id) {
        InfoAboutWorkerBottomDialog dialog = new InfoAboutWorkerBottomDialog(id);
        dialog.show(getChildFragmentManager(), "lol");
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSpecialist(List<Specialist> data) {
        if(data!=null){
            adapterSpecialist = new SpecialistAdapter(getContext(), data, click->{
                InfoAboutWorkerBottomDialog dialog = new InfoAboutWorkerBottomDialog(click);
                dialog.show(getChildFragmentManager(), "kek");
            });
            rvSpec.setAdapter(adapterSpecialist);
            rvSpec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }
}
