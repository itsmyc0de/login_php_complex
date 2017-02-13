package sudheesh16mb.com.kec_db;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by KUTTAN on 18-08-2016.
 */
public class downloader extends AsyncTask<String,Void,String>{
    Context c;
    String urlAddess;
    ListView lv;
    ProgressDialog pd;
    public downloader(Context c, String urlAddess, ListView lv) {
        this.c = c;
        this.urlAddess = urlAddess;
        this.lv = lv;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving..Please wait");
        pd.show();
    }
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }
    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        pd.dismiss();
        if(jsonData.startsWith("Error"))
        {
            Toast.makeText(c, "Unsuccessful " + jsonData, Toast.LENGTH_SHORT).show();
        }else
        {
            //PARSE
            new DataParser(c,jsonData,lv).execute();
        }
    }
    private String downloadData()
    {
        Object connection=connector.connect(urlAddess);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }
        try {
            HttpURLConnection con= (HttpURLConnection) connection;
            InputStream is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer jsonData=new StringBuffer();
            while ((line=br.readLine()) != null)
            {
                jsonData.append(line+"\n");
            }
            br.close();
            is.close();
            return jsonData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }
    }
}
