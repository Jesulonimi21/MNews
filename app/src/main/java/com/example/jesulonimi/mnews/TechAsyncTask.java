package com.example.jesulonimi.mnews;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TechAsyncTask extends AsyncTask<String,Void,List<model>> {
    ScienceAdapterClass scienceAdapterClass;
    List<model>  list=new ArrayList<>();


    public TechAsyncTask(ScienceAdapterClass scienceAdapterClass) {
        this.scienceAdapterClass = scienceAdapterClass;
    }

    @Override
    protected List<model> doInBackground(String... strings) {
     return    processXml(strings[0]);
    }


    @Override
    protected void onPostExecute(List<model> models) {
        super.onPostExecute(models);
        scienceAdapterClass.addArticles(models);
        }

    public List<model> processXml(String myUrl){


        try {

            URL url=new URL(myUrl);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=urlConnection.getInputStream();

            Scanner scanner=new Scanner(inputStream);
            scanner.useDelimiter("\\a");
            String dom=null;
            if(scanner.hasNext()){
                dom=scanner.next();
            }

            JSONObject jsonObject=new JSONObject(dom);
            JSONArray myArray=jsonObject.getJSONArray("articles");

            for(int i=0;i<(myArray.length());i++){
                JSONObject currentItem=myArray.getJSONObject(i);
                String date=currentItem.getString("publishedAt");
                String title=currentItem.getString("title");
                String imageUrl=currentItem.getString("urlToImage");
                String detailsUrl=currentItem.getString("url");

                date=date.replaceAll("T","  ");
                date=date.replaceAll("Z","  ");
                list.add(new model(title,imageUrl,detailsUrl,date));
            }
// *************************HOW I KNOW THE PROBLEM IS WITH THE ENDPOINT BAD SERVIC PROVODERS**************//////////////
//            JSONObject jsonObject=new JSONObject(dom);
//            JSONArray myArray=jsonObject.getJSONArray("items");
//            for(int i=0;i<myArray.length();i++){
//                JSONObject item=myArray.getJSONObject(i);
//                String title=item.getString("title");
//                String date =item.getString("date_posted");
//                String oburl="null";
//                list.add(new model(title,oburl,date,"content url"));
//            }

            return list;
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
