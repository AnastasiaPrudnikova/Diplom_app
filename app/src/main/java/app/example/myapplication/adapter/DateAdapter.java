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

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.LabelHolader> {
    private Context context;
    private List<String> date;
    private OnClickDate onClickDate;
    private int selectedItem = -1;
    public DateAdapter(Context context, List<String> date, OnClickDate onClickDate) {
        this.context = context;
        this.date = date;
        this.onClickDate = onClickDate;
    }

    public interface OnClickDate{
        void onClick(String date, int id);
    }
    @NonNull
    @Override
    public DateAdapter.LabelHolader onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.date_item, parent, false);
        return new LabelHolader(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.LabelHolader holder, int position) {
        holder.date.setText(date.get(position));
        if (position == selectedItem) {
            holder.cardView.setCardBackgroundColor(Color.RED);
        } else {
            holder.cardView.setCardBackgroundColor(Color.WHITE); // Измените на цвет по умолчанию
        }

        holder.itemView.setOnClickListener(l->{
            onClickDate.onClick(date.get(position), position);
            int previousSelectedItem = selectedItem;
            selectedItem = position;
            notifyItemChanged(previousSelectedItem);
            notifyItemChanged(selectedItem);
            SPHelper.ZapisInfo.setDate(date.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class LabelHolader extends RecyclerView.ViewHolder {
        TextView date;
        CardView cardView;
        public LabelHolader(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
