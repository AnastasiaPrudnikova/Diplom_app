package app.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import app.example.myapplication.R;
import app.example.myapplication.util.StringHelper;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.LabelHolder> {

    private Context context;

    public PromoAdapter(Context context) {
        this.context = context;
    }

    private String[] image_array = {"https://bronk.club/uploads/posts/2023-02/1677058848_bronk-club-p-pozdravleniya-salonu-krasoti-2-goda-krasiv-13.jpg",
            "https://pm-great.com/wp-content/uploads/2021/02/35ebb6a4-8b93-4ac9-bf53-87208b6956e3.jpg",
            "https://asuvorova.ru/template/img/banners/devi2.jpg"};
    @NonNull
    @Override
    public PromoAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new LabelHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.LabelHolder holder, int position) {
        Picasso.get().load(image_array[position]).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return image_array.length;
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
