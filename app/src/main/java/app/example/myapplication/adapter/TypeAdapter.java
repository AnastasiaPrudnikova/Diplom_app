package app.example.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.LabelHolder> {
    @NonNull
    @Override
    public TypeAdapter.LabelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeAdapter.LabelHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LabelHolder extends RecyclerView.ViewHolder {
        public LabelHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
