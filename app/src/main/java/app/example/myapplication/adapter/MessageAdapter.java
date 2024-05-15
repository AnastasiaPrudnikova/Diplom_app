package app.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.db.Message;
import app.example.myapplication.util.SPHelper;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.LabelHolder> {
    private Context context;
    private List<Message> data;

    public MessageAdapter(Context context, List<Message> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MessageAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.messages_item, parent, false);
        return new LabelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.LabelHolder holder, int position) {
        if(data.get(position).sender.equals(SPHelper.PersonInfo.getLogin())) holder.name.setText("Вы");
        else holder.name.setText(data.get(position).sender);

        holder.text.setText(data.get(position).message);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView name, text;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            text = itemView.findViewById(R.id.text);
        }
    }
}