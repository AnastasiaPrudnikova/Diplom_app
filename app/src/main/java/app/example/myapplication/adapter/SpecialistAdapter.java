package app.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.db.Specialist;

public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistAdapter.LabelHolder> {
    private Context context;
    private List<Specialist> data;
    private OnClickItem onClickItem;

    public interface OnClickItem{
        void onClick(String id);
    }

    public SpecialistAdapter(Context context, List<Specialist> data, OnClickItem onClickItem) {
        this.context = context;
        this.data = data;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public SpecialistAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.specialist_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialistAdapter.LabelHolder holder, int position) {
        holder.item.setOnClickListener(l->{
                onClickItem.onClick(data.get(position).getEmail());
        });
        holder.name.setText(data.get(position).getName());
        Picasso.get().load(data.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        CardView item;
        TextView name;
        ImageView image;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
             item = itemView.findViewById(R.id.item);
             name = itemView.findViewById(R.id.name);
             image = itemView.findViewById(R.id.image);
        }
    }
}
