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

public class NigerNewsAsyncTask extends AsyncTask<String,Void,List<model>> {

List<model>  mL=new ArrayList<>();

NigerAdapter nigerAdapter;
    public NigerNewsAsyncTask(NigerAdapter nigerAdapter) {
        this.nigerAdapter=nigerAdapter;

    }

    @Override
    protected List<model> doInBackground(String... strings) {


        return getNigerList(strings[0]);
    }


    @Override
    protected void onPostExecute(List<model> list) {
        super.onPostExecute(list);

        nigerAdapter.addNigerArticles(list);

    }

    public List<model> getNigerList(String myUrl){

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

            return mL;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("exception","malformed url");
                return null;
            } catch (IOException e) {
                Log.d("exception","io exception");
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                Log.d("exception","json exception");
                e.printStackTrace();
                return null;

        }

    }
}
