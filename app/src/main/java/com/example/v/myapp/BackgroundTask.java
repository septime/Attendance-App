package com.example.v.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by V on 13/12/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String>{
    Context context;
    AlertDialog alertDialog;

    BackgroundTask(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String username = params[1];
        String login_url = "http://attendance-proj.000webhostapp.com/php/appLogin.php";
        if (type.equals("login")){
            try {
                URL url = new URL(login_url);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                writer.write(postData);
                writer.flush();
                writer.close();
                outputStream.close();
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                inputStream.close();
                conn.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        alertDialog.setMessage(result);
        alertDialog.show();

        alertDialog.setMessage(result);

/* go to another activity upon authorization
        if(result.contentEquals("Login successful")) {

            context.startActivity(new Intent(context, AfterLogin.class));

        }else
        {
            Toast.makeText(context, "Wrong Username or Password!", Toast.LENGTH_SHORT).show();
        }*/

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
