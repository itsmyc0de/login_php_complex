package sudheesh16mb.com.kec_db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Search extends Activity {

    Spinner sp;
    InputStream inputStream=null;
    String result=null;
    String line=null;
    AutoCompleteTextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        /*sp= (Spinner) findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(),sp.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try{
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://sudheesh.16mb.com/st_db/auto_complete.php");
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();
            inputStream=entity.getContent();
            Log.e("pass 1","Connection Sucess");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Invalid IP",Toast.LENGTH_SHORT).show();

        }

        try
        {
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
            StringBuilder sb=new StringBuilder();
            while ((line =reader.readLine()) != null){
                sb.append(line + "\n");
            }
            inputStream.close();
            result=sb.toString();
            Log.e("Pass 2","Connction Sucess");
        }
        catch (Exception e){
        Log.e("fail 2", e.toString());
        }
        try{
            JSONArray jsonArray=new JSONArray(result);
            JSONObject jsonObject=null;
            final String[] str1=new String[jsonArray.length()];

            for(int i=0;i<jsonArray.length();i++){
                jsonObject=jsonArray.getJSONObject(i);
                str1[i]=jsonObject.getString("name");
            }
            final AutoCompleteTextView textView= (AutoCompleteTextView) findViewById(R.id.roll);
            final List<String> list =new ArrayList<String>();
            for(int i=0;i<str1.length;i++)
            {
                list.add(str1[i]);
            }
            Collections.sort(list);
            ArrayAdapter<String> dataadapter =new ArrayAdapter<String>
                    (getApplicationContext(),android.R.layout.simple_spinner_item,list);
            dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            textView.setThreshold(1);
            textView.setAdapter(dataadapter);
            textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext(),list.get(position).toString(),Toast.LENGTH_SHORT).show();
                }
            });


        }catch (Exception e)
        {

        }
        Button b= (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        */

        final String str[]={"Abirami S","Abirami E","Saran T","14cer002","14cer003","15cer170"};

        t1 = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,str);

        t1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String s = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                Button b= (Button) findViewById(R.id.button);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(s.equals("Abirami A") || s.equals("14cer003")) {
                            Intent intent = new Intent(Search.this, roll1.class);
                            startActivity(intent);

                        }
                        if(s.equals("Saran T") || s.equals("15cer170")) {
                            Intent intent = new Intent(Search.this, roll2.class);
                            startActivity(intent);

                        }
                        if(s.equals("Abirami S") || s.equals("14cer002")) {
                            Intent intent = new Intent(Search.this, roll3.class);
                            startActivity(intent);

                        }
                    }
                });
            }
        });
        t1.setThreshold(1);
        t1.setAdapter(adp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
