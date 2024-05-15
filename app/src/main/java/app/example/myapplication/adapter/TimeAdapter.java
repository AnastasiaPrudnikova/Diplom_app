package app.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.util.SPHelper;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.LabelHolder> {
    private Context context;
    private List<String> times;

    private OnClickTime onClickTime;
    private int selectedItem = -1;
    public TimeAdapter(Context context, List<String> times, OnClickTime onClickTime) {
        this.context = context;
        this.times = times;
        this.onClickTime = onClickTime;
    }

    public interface OnClickTime{
        void onClick(String date, String time);
    }
    @NonNull
    @Override
    public TimeAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.time_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.LabelHolder holder, int position) {
        holder.time.setText(times.get(position));
        if (position == selectedItem) {
            holder.background.setCardBackgroundColor(Color.RED);
        } else {
            holder.background.setCardBackgroundColor(Color.WHITE); // Измените на цвет по умолчанию
        }

        holder.itemView.setOnClickListener(l->{
                int previousSelectedItem = selectedItem;
                selectedItem = position;
                notifyItemChanged(previousSelectedItem);
                notifyItemChanged(selectedItem);
                SPHelper.ZapisInfo.setTime(times.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView time;
        CardView background;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            background = itemView.findViewById(R.id.background);
        }
    }
}
