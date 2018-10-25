package com.androidapp.mcs.cakesdisplayapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.mcs.cakesdisplayapp.MainActivity;
import com.androidapp.mcs.cakesdisplayapp.R;
import com.androidapp.mcs.cakesdisplayapp.model.Cakes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Cakes> cakes;
    private Context mContext;

    public MyAdapter(List<Cakes> cakes, Context mContext) {
        this.cakes = cakes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Cakes cakes1 = cakes.get(i);

        viewHolder.cakeTitle.setText(cakes1.getTitle());
        viewHolder.cakeDesc.setText(cakes1.getDesc());

        Picasso.get().load(cakes1.getImage()).fit().placeholder(R.drawable.ic_launcher_background)
                .into((ImageView) viewHolder.cakeImage.findViewById(R.id.imageView1));
    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cakeTitle;
        public TextView cakeDesc;
        public ImageView cakeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cakeTitle = itemView.findViewById(R.id.title_view);
            cakeDesc = itemView.findViewById(R.id.desc_view);
            cakeImage = itemView.findViewById(R.id.imageView1);
        }
    }
}
