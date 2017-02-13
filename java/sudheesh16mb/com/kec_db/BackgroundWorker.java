package sudheesh16mb.com.kec_db;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
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
 * Created by KUTTAN on 18-08-2016.
 */
    public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Dialog dialog;

    Context cnt;
    public BackgroundWorker(Context ctx) {
        // TODO Auto-generated constructor stub
        cnt=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        String type=params[0];
        String lurl="http://sudheesh.16mb.com/st_db/login.php";
        if(type.equals("Login"))
        {
            try
            {
                String code=params[1];
                String psd=params[2];
                URL url=new URL(lurl);
                HttpURLConnection htcon=(HttpURLConnection) url.openConnection();
                htcon.setRequestMethod("POST");
                htcon.setDoInput(true);
                htcon.setDoOutput(true);
                OutputStream ostream=htcon.getOutputStream();
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(ostream, "UTF-8"));
                String post_data= URLEncoder.encode("code", "UTF-8")+"="+URLEncoder.encode(code,"UTF-8")+"&"
                        +URLEncoder.encode("psd","UTF-8")+"="+URLEncoder.encode(psd,"UTF-8");
                bw.write(post_data);
                bw.flush();
                bw.close();
                InputStream istream=htcon.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(istream,"iso-8859-1"));
                String results="";
                String line="";
                while((line=br.readLine())!=null)
                {
                    results+=line;
                }
                br.close();
                istream.close();
                htcon.disconnect();
                return results;
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        dialog.dismiss();
        if(result.equals("success")) {
            Toast.makeText(cnt, "Login Sucess", Toast.LENGTH_SHORT).show();
            cnt.startActivity(new Intent(cnt,Search.class));
        }else
        {
            Toast.makeText(cnt,"Please Check The Login Credentials :(",Toast.LENGTH_SHORT).show();
        }
    //    dialog=ProgressDialog.show(cnt,""+result+"","ss");
    }


    @Override
    protected void onPreExecute() {
       super.onPreExecute();

        dialog=ProgressDialog.show(cnt,"Please Wait:","Loading....");

    }



    protected void onProgressUpdate(String... text) {


    }



}
