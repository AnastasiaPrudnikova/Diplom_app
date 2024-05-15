package app.example.myapplication.fragment.admin.chats;

import android.os.Bundle;
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
import app.example.myapplication.presenter.MessagePresenter;
import app.example.myapplication.view.MessageView;
import butterknife.BindView;

public class MessagerAdminFragment extends BaseFragment implements MessageView {

    private String login;
    private MessageAdapter adapter;
    private MessagePresenter presenter;
    @BindView(R.id.text)
    EditText text;
    @BindView(R.id.send)
    ImageView send;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initViews() {
        super.initViews();
        presenter = new MessagePresenter(this);
        presenter.getMessages(login);

        send.setOnClickListener(l->{
            if(!text.getText().equals("") && text.getText() !=null){
                presenter.sendMessages(login, text.getText().toString());
                presenter.getMessages(login);
            }
        });
    }

    public MessagerAdminFragment(String login) {
        this.login = login;
    }

    public static MessagerAdminFragment newInstance(String login) {
        return new MessagerAdminFragment(login);
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

    @Override
    protected int layoutId() {
        return R.layout.message_admin_fragment;
    }
}
