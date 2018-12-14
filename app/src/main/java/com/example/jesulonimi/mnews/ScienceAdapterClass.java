package com.example.jesulonimi.mnews;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ShareCompat;
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
import com.squareup.picasso.Picasso;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

public class ScienceAdapterClass extends RecyclerView.Adapter<ScienceAdapterClass.scienceViewHolder>{
private List<model> scienceList =new ArrayList<>();
Context c;

    public ScienceAdapterClass(Context c) {
        this.c = c;
    }

    public void addArticles(List<model> list){
if(list!=null) {
    scienceList.addAll(list);
    notifyDataSetChanged();
}
}
    @NonNull
    @Override
    public scienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.heldviews,parent,false);

        return new scienceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull scienceViewHolder holder, int position) {
        final model m=scienceList.get(position);
        holder.title.setText(Html.fromHtml("<b>"+m.getTitle()+"</b>"));
        Log.d("imageUrl",m.getImageUrl());
        holder.addInfo.setText(Html.fromHtml("<i>"+m.getPublishedAt()+"</i>"));
       // Picasso.with(c).load(m.getImageUrl()).placeholder(R.drawable.ic_launcher_background).resize(100,100).centerCrop().into(holder.techImage);

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



        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(c);
                builder.setTitle("share your news");
                CharSequence[] options =new CharSequence[]{"share title and link"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    if(which==0){
                        String mimeType="text/plain";
                        String title="choose who to share news with";
                        String text=m.getTitle()+"/n"+m.getDetailsUrl();
                        ShareCompat.IntentBuilder.from((Activity) c).
                                setChooserTitle(title).setType(mimeType).setText(text).startChooser();
                    }
                    }
                });
                builder.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return scienceList.size();
    }

    public class scienceViewHolder extends RecyclerView.ViewHolder{
TextView title;
ImageView techImage;
CardView cardView;
TextView addInfo;
        public scienceViewHolder(View itemView) {
            super(itemView);
            title=(itemView.findViewById(R.id.newsTitle));
            techImage=(ImageView)  itemView.findViewById(R.id.scienceImage);
            cardView=(CardView) itemView.findViewById(R.id.cardview);
           addInfo=(TextView) itemView.findViewById(R.id.date);
        }
    }
}
