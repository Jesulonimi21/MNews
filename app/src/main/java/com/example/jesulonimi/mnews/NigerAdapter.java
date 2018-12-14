package com.example.jesulonimi.mnews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class NigerAdapter extends RecyclerView.Adapter<NigerAdapter.NigerViewHolder> {
List<model> nigerList=new ArrayList<>();

Context c;
    public NigerAdapter(Context con) {
        c=con;
    }

    public void addNigerArticles(List<model> lm){
if(lm!=null) {
    nigerList.addAll(lm);
    notifyDataSetChanged();
}
    }


    @NonNull
    @Override
    public NigerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.heldviews,parent,false);

        return new NigerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NigerViewHolder holder, int position) {

        final model m=nigerList.get(position);
        holder.title.setText(Html.fromHtml("<b>"+m.getTitle()+"</b>"));
        Log.d("imageUrl",m.getImageUrl());
        holder.date.setText(Html.fromHtml("<i>"+m.getPublishedAt()+"</i>"));
       // Picasso.with(c).load(m.getImageUrl()).placeholder(R.drawable.ic_launcher_background).resize(100,100).centerCrop().into(holder.techImage);
//

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
                Intent i= new Intent(c,NewsDetails.class);
                i.putExtra("url",m.getDetailsUrl());
                c.startActivity(i);
            }
        });
        Log.d("niger","onbindVIEWHOLDER");
    }

    @Override
    public int getItemCount() {

        return nigerList.size();
    }

    public class NigerViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView techImage;
        CardView cardView;
        TextView date;
        DrawableImageViewTarget drawableImageViewTarget;
        public NigerViewHolder(View itemView) {
            super(itemView);
            title=(itemView.findViewById(R.id.newsTitle));
            techImage=(ImageView)  itemView.findViewById(R.id.scienceImage);
            cardView=(CardView) itemView.findViewById(R.id.cardview);
            date=(TextView) itemView.findViewById(R.id.date);
        drawableImageViewTarget=new DrawableImageViewTarget(techImage);
        }
    }

}
