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
public class TechNews extends Fragment {
TechAsyncTask task;
    ScienceAdapterClass sca;
    ProgressBar techProgressBar;
List<TechAsyncTask> techAsyncTasks=new ArrayList<>();

    List<String> url= Arrays.asList(
//"http://hnapp.com/json?q=score>80"
            "https://newsapi.org/v2/everything?sources=the-verge&pagesize=100&apiKey=1ad3951982024cd39004376309fe4f97"

    );

    public TechNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tech_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.techRecycler);
        techProgressBar=(ProgressBar) view.findViewById(R.id.techProgress);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       sca=new ScienceAdapterClass(getContext());

        recyclerView.setAdapter(sca);

        sca.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(sca.getItemCount()>0){
                techProgressBar.setVisibility(View.INVISIBLE);}
                else{techProgressBar.setVisibility(View.INVISIBLE);}
            }
        });

        startDownload();



    }


    public void startDownload(){
//        if (internet_connection()){
//            // Execute DownloadJSON AsyncTask
//        }else{
//            Toast.makeText(getContext(),"no internet connection, check your connection settings",Toast.LENGTH_LONG).show();
//        }

            for(TechAsyncTask t:techAsyncTasks){
                t.cancel(true);
            }
            for(String u:url) {
                task = new TechAsyncTask(sca);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,u);
                techAsyncTasks.add(task);
            }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(TechAsyncTask T:techAsyncTasks){
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
