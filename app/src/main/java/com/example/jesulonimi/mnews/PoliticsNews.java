package com.example.jesulonimi.mnews;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoliticsNews extends Fragment {
RecyclerView recyclerView;
NigerAdapter adapter;
NigerNewsAsyncTask nigerNewsAsyncTask;
ProgressBar NigerProgressBar;

    List<String> url= Arrays.asList(
            "https://newsapi.org/v2/everything?domains=vanguardngr.com&pagesize=100&apiKey=afe071b650244baeb1a32a20a1c05da1"
  );

List<NigerNewsAsyncTask> tasks=new ArrayList<>();
    public PoliticsNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_politics_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=(RecyclerView) view.findViewById(R.id.NigerRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new NigerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        NigerProgressBar=(ProgressBar) view.findViewById(R.id.nigerProgress);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(adapter.getItemCount()>0){
                    NigerProgressBar.setVisibility(View.INVISIBLE);
                }else{
                    NigerProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });


startDownload();

    }
    public void startDownload(){

            for (NigerNewsAsyncTask N : tasks)
                N.cancel(true);

            for (String urls : url) {
                nigerNewsAsyncTask = new NigerNewsAsyncTask(adapter);
                nigerNewsAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urls);
                tasks.add(nigerNewsAsyncTask);
            }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(NigerNewsAsyncTask T:tasks){
            T.cancel(true);
        }
    }




    boolean internet_connection(){
        //Check if connected to internet, output accordingly
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
