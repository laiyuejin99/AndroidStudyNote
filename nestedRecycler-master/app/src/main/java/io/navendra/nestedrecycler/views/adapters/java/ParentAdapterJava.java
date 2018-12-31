package io.navendra.nestedrecycler.views.adapters.java;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.navendra.nestedrecycler.R;
import io.navendra.nestedrecycler.models.ParentModel;
import io.navendra.nestedrecycler.views.adapters.ChildAdapter;

public class ParentAdapterJava extends RecyclerView.Adapter<ParentAdapterJava.ViewHolder> {

    private List<ParentModel> data;
    RecyclerView.RecycledViewPool recycledViewPool;

    public ParentAdapterJava(List<ParentModel> data) {
        this.data = data;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParentModel item = data.get(position);
        holder.textView.setText(item.getTitle());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.recyclerView
                .getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(new ChildAdapter(item.getChildren()));
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rv_textView);
            recyclerView = itemView.findViewById(R.id.rv_child_recyclerview);
        }
    }
}

