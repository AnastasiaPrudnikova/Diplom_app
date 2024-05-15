package app.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.example.myapplication.R;
import app.example.myapplication.db.Specialist;

public class SpecialistAdminAdapter extends RecyclerView.Adapter<SpecialistAdminAdapter.LabelHolder> {
    private Context context;
    private List<Specialist> data;
    private OnClickRemoveSpecialist clickRemoveSpecialist;
    private OnClickSpecialist onClickSpecialist;

    public SpecialistAdminAdapter(Context context, List<Specialist> data, OnClickRemoveSpecialist clickRemoveSpecialist, OnClickSpecialist onClickSpecialist) {
        this.context = context;
        this.data = data;
        this.clickRemoveSpecialist = clickRemoveSpecialist;
        this.onClickSpecialist = onClickSpecialist;
    }

    @NonNull
    @Override
    public SpecialistAdminAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.specialist_admin_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialistAdminAdapter.LabelHolder holder, int position) {
        holder.close.setOnClickListener(l->{
            clickRemoveSpecialist.clickSpecialist(data.get(position).getEmail());
        });
        holder.itemView.setOnClickListener(l->{
            onClickSpecialist.clickInfoSpecialist(data.get(position).getEmail());
        });
        holder.name.setText(data.get(position).getName());
        holder.type.setText(data.get(position).getType());
        Picasso.get().load(data.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        TextView name, type;
        ImageView close, image;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            close = itemView.findViewById(R.id.close);
            image = itemView.findViewById(R.id.image);
        }
    }

    public interface OnClickRemoveSpecialist{
        void clickSpecialist(String email);
    }

    public interface OnClickSpecialist{
        void clickInfoSpecialist(String email);
    }
}
