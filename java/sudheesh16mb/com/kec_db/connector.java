package sudheesh16mb.com.kec_db;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by KUTTAN on 18-08-2016.
 */
public class connector {
    public static Object connect(String urlAddress)
    {
        try
        {
            URL url=new URL(urlAddress);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            //SET CON PROPERTIES
            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);
            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }
    }
}
