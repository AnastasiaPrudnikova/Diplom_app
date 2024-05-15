package app.example.myapplication.fragment.admin.chats;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.activity.AdminActivity;
import app.example.myapplication.adapter.ChatListAdapter;
import app.example.myapplication.db.Chats;
import app.example.myapplication.fragment.BaseFragment;
import app.example.myapplication.presenter.ChatsPresenter;
import app.example.myapplication.view.ChatsView;
import butterknife.BindView;

public class ChatFragment extends BaseFragment implements ChatsView {
    private ChatListAdapter adapter;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.filter)
    EditText filter;
    @BindView(R.id.add)
    ImageView add;
    private List<Chats> originalData;
    private ChatsPresenter presenter;

    @Override
    protected void initViews() {
        super.initViews();
        presenter = new ChatsPresenter(this);
        presenter.getChats();

//        presenter.getChats();
//        add.setOnClickListener(l->{
//            UsersContactBottomDialog dialog = new UsersContactBottomDialog();
//            dialog.setCancelable(true);
//            dialog.show(getChildFragmentManager(), "users");
//        });

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {filter(editable.toString());}
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.chats_fragment;
    }
    private void filter(String text) {
        List<Chats> filterdName = new ArrayList<>();
        if(originalData!=null && !originalData.isEmpty()) {
            for (Chats data : originalData)
                if (data.getName().toLowerCase().contains(text.toLowerCase()) || data.getName().toLowerCase().contains(text.toLowerCase())) filterdName.add(data);
            adapter.setFilterData(filterdName);
        }
    }


    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void getChats(List<Chats> chats) {
        originalData = new ArrayList<>(chats);
        adapter = new ChatListAdapter(getContext(), chats, click->{
            ((AdminActivity)getActivity()).replaceFragment(MessagerAdminFragment.newInstance(click.login), true);
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    @Override
    public void errorMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void clickUsers() {
//        presenter.getChats();
//    }
}
