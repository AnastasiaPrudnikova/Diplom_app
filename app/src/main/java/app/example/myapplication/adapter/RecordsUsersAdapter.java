package app.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.db.Record;

public class RecordsUsersAdapter extends RecyclerView.Adapter<RecordsUsersAdapter.LabelHolder> {
    private Context context;
    private List<Record> data;
    private OnClickRemoveRecords onClickRemoveRecords;

    public RecordsUsersAdapter(Context context, List<Record> data, OnClickRemoveRecords onClickRemoveRecords) {
        this.context = context;
        this.data = data;
        this.onClickRemoveRecords = onClickRemoveRecords;
    }

    @NonNull
    @Override
    public RecordsUsersAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.records_users_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsUsersAdapter.LabelHolder holder, int position) {
        holder.close.setOnClickListener(l->{
            onClickRemoveRecords.clickRecord(data.get(position).getEmail(), data.get(position));
        });
        holder.name.setText(data.get(position).getEmail());
        holder.time.setText(data.get(position).getTime() + " | " + data.get(position).getDate());
        holder.type.setText(data.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView name, time, type;
        ImageView close;

        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            type = itemView.findViewById(R.id.type);
            close = itemView.findViewById(R.id.close);
        }
    }

    public interface OnClickRemoveRecords{
        void clickRecord(String email, Record record);
    }
}
