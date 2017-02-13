package sudheesh16mb.com.kec_db;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Login extends Activity {

    EditText e1,e2;
    Button b;
    InputStream inputStream =null;
    String result=null;
    String line=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1= (EditText) findViewById(R.id.ed1);
        e2= (EditText) findViewById(R.id.pass);
        b= (Button) findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code, psd;
                code = e1.getText().toString();
                psd = e2.getText().toString();
                //Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                String type = "Login";
                BackgroundWorker bw=new BackgroundWorker(Login.this);
                bw.execute(type, code, psd);

            }
        });

    }
/*

    private void login(String code, String psd) {

        class LoginAsync extends AsyncTask<String, Void,String>{

            private Dialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog= ProgressDialog.show(Login.this,"Please Wait","Loading....");
            }

            @Override
            protected String doInBackground(String... params) {
               String code=params[0];
                String psd=params[1];
                ArrayList<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("code", code));
                nameValuePairs.add(new BasicNameValuePair("psd",psd));

                try {
                    HttpClient httpClient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://sudheesh.16mb.com/st_db/login.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response=httpClient.execute(httpPost);
                    HttpEntity entity=response.getEntity();
                    inputStream=entity.getContent();
                    Log.e("pass 1", "connection ok");
                                  } catch (Exception e){
                    Log.e("fail 1",e.toString());
                    Toast.makeText(getApplicationContext(),"sorry",Toast.LENGTH_SHORT).show();
                }

                try{


                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream,"iso-8859-1"),8);
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    inputStream.close();
                    result = sb.toString();
                    Log.e("pass 2", "connection success ");
                }catch (Exception e){

                    Log.e("Fail 2", e.toString());
                }

                try{
                    JSONObject jsonObject=new JSONObject(result);
                    code=(jsonObject.getString("code"));
                    Toast.makeText(getBaseContext(),"Name:"+code,Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Log.e("Fail 3 ",e.toString());
                }



               return result;
            }

            @Override
            protected void onPostExecute(String s) {
                String ss=s.trim();
                dialog.dismiss();
                if (s.equalsIgnoreCase("sucess")){
                    Intent intent=new Intent(Login.this,Search.class);
                    //intent.putExtra(USER_NAME,username);
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Invalid Username",Toast.LENGTH_SHORT).show();
                }


            }
        }
        LoginAsync loginAsync=new LoginAsync();
        loginAsync.execute(
                code,
                psd
        );
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog dialog=new AlertDialog.Builder(Login.this).create();
            dialog.setTitle("Developers:");
            dialog.setMessage("SUDHEESH S 13CSR185 \nTHILAK S 13CER200");
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
