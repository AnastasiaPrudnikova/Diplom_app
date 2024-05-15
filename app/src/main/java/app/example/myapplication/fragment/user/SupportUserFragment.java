package app.example.myapplication.fragment.user;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.adapter.MessageAdapter;
import app.example.myapplication.db.Message;
import app.example.myapplication.fragment.BaseFragment;
import app.example.myapplication.presenter.SupportPresenter;
import app.example.myapplication.view.SupportView;
import butterknife.BindView;

public class SupportUserFragment extends BaseFragment implements SupportView {

    private MessageAdapter adapter;
    private SupportPresenter presenter;
    @BindView(R.id.text)
    EditText text;
    @BindView(R.id.send)
    ImageView send;
    @BindView(R.id.rv)
    RecyclerView rv;

    public static SupportUserFragment newInstance() {
        return new SupportUserFragment();
    }

    @Override
    protected void initViews() {
        super.initViews();
        presenter = new SupportPresenter(this);
        presenter.getMessages();

        send.setOnClickListener(l->{
            if(!text.getText().equals("") && text.getText() !=null){
                presenter.sendMessages(text.getText().toString());
                presenter.getMessages();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.support_user_fragment;
    }

    @Override
    public void getMessages(List<Message> messages) {
        adapter = new MessageAdapter(getContext(), messages);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    @Override
    public void errorMsg(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void successSend() {
        adapter.notifyDataSetChanged();
        text.setText("");
    }
}
