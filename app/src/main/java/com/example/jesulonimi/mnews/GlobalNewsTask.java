package com.example.jesulonimi.mnews;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GlobalNewsTask extends AsyncTask<String,Void,List<model>> {
private List<model> mL=new ArrayList<>();
GlobalNewsAdapter globalNewsAdapter;
    @Override
    protected List<model> doInBackground(String... strings) {
        return getGlobalList(strings[0]);
    }

        public GlobalNewsTask(GlobalNewsAdapter globalAdapter) {
        this.globalNewsAdapter=globalAdapter;

    }


    @Override
    protected void onPostExecute(List<model> list) {
        super.onPostExecute(list);
        super.onPostExecute(list);
        globalNewsAdapter.addGlobalArticles(list);
    }

    public List<model> getGlobalList(String myUrl){
        try {
            URL url=new URL(myUrl);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=urlConnection.getInputStream();
            Scanner scanner=new Scanner(inputStream);
            scanner.useDelimiter("\\a");
            String theContent=null;
            if(scanner.hasNext()){
                theContent=scanner.next();
            }
            JSONObject jsonObject=new JSONObject(theContent);
            JSONArray array=jsonObject.getJSONArray("articles");
            for(int i=0;i<array.length();i++){
                JSONObject myObj=array.getJSONObject(i);
                String title=myObj.getString("title");
                String ImageUrl=myObj.getString("urlToImage");
                String detailsUrl=myObj.getString("url");
                String date=myObj.getString("publishedAt");

                date=date.replaceAll("T","  ");
                date=date.replaceAll("Z","  ");

                mL.add(new model(title,ImageUrl,detailsUrl,date));
            }
            Log.d("niger","niger news list full"+mL.size());
            return mL;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    }


