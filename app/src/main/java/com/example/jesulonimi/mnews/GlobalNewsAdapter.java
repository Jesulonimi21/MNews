package com.example.jesulonimi.mnews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GlobalNewsAdapter extends RecyclerView.Adapter<GlobalNewsAdapter.GlobalViewHolder> {



    List<model> nigerList=new ArrayList<>();

    Context c;
    public GlobalNewsAdapter(Context con) {
        c=con;
    }

    public void addGlobalArticles(List<model> lm){

        if(lm!=null) {
            Log.d("niger", "also adapter list added" + lm.size());

            nigerList.addAll(lm);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public GlobalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.heldviews,parent,false);

        return new GlobalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final GlobalViewHolder holder, int position) {

        final model m=nigerList.get(position);
        holder.title.setText(Html.fromHtml("<b>"+m.getTitle()+"</b>"));
        holder.date.setText(Html.fromHtml("<i>"+m.getPublishedAt()+"</i>"));
        Log.d("imageUrl",m.getImageUrl());
        //Picasso.with(c).load(m.getImageUrl()).placeholder(R.drawable.ic_launcher_background).resize(100,100).centerCrop().into(holder.techImage);

        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.drawable.loadingimg);
        requestOptions.error(R.drawable.spi);
        requestOptions.centerCrop();
        Glide.with(c).applyDefaultRequestOptions(requestOptions)
                .load(m.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.techImage);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(c,NewsDetails.class);
                i.putExtra("url",m.getDetailsUrl());
                c.startActivity(i);
            }
        });
        Log.d("niger","onbindVIEWHOLDER");
    }

    @Override
    public int getItemCount() {
        Log.d("nigernews",nigerList.size()+"");
        return nigerList.size();
    }


public class GlobalViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    ImageView techImage;
    CardView cardView;
    TextView date;
    public GlobalViewHolder(View itemView) {
        super(itemView);
        title=(itemView.findViewById(R.id.newsTitle));
        techImage=(ImageView)  itemView.findViewById(R.id.scienceImage);
        cardView=(CardView) itemView.findViewById(R.id.cardview);
        date=(TextView) itemView.findViewById(R.id.date);
    }
}

}
