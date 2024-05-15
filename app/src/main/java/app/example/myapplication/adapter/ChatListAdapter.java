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
import app.example.myapplication.db.Chats;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.LabelHolder> {
    private Context context;
    private List<Chats> chats;
    private OnClickChats clickChats;

    public ChatListAdapter(Context context, List<Chats> chats, OnClickChats clickChats) {
        this.context = context;
        this.chats = chats;
        this.clickChats = clickChats;
    }

    public void setFilterData(List<Chats> filterdName) {
        this.chats = filterdName;
        notifyDataSetChanged();
    }

    public interface OnClickChats{
        void onClickChats(Chats chat);
    }

    @NonNull
    @Override
    public ChatListAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chats_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.LabelHolder holder, int position) {
        holder.itemView.setOnClickListener(l->{
            clickChats.onClickChats(chats.get(position));
        });
        holder.name.setText(chats.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView name;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}

