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
public class GameNews extends Fragment {

    private RecyclerView recyclerView;
    private GlobalNewsAdapter adapter;
    private GlobalNewsTask globalNewsTask;


    List<String> url = Arrays.asList(
            "https://newsapi.org/v2/everything?sources=bbc-news&pagesize=100&apiKey=569416487cba480c9e19db3b18f6604c"



    );
    List<GlobalNewsTask> globalNewsTasks = new ArrayList<>();
    ProgressBar globalProgress;

    public GameNews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_news, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.GlobalRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GlobalNewsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        globalProgress = (ProgressBar) view.findViewById(R.id.GlobalProgress);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapter.getItemCount() > 0) {
                    globalProgress.setVisibility(View.INVISIBLE);
                } else {
                    globalProgress.setVisibility(View.VISIBLE);
                }
            }
        });

       startDownload();

    }

    public void startDownload() {


//        if (internet_connection()){
//            // Execute DownloadJSON AsyncTa
//        }else{
//            Toast.makeText(getContext(),"no internet connection, check your connection settings",Toast.LENGTH_LONG).show();
//        }
        for (GlobalNewsTask gTask : globalNewsTasks) {
            gTask.cancel(true);

        }

        for (String urls : url) {

            globalNewsTask = new GlobalNewsTask(adapter);
            globalNewsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urls);
            globalNewsTasks.add(globalNewsTask);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (GlobalNewsTask T : globalNewsTasks) {
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