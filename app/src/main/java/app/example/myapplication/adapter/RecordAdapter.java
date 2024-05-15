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
import app.example.myapplication.db.Record;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.LabelHolder> {
    private Context context;
    private List<Record>data;

    public RecordAdapter(Context context, List<Record> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecordAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.record_item, parent , false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.LabelHolder holder, int position) {
        holder.date.setText(data.get(position).getTime() + " | " + data.get(position).getDate());
        holder.type.setText(data.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView date, type;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.data_time);
            type = itemView.findViewById(R.id.type);
        }
    }
}
