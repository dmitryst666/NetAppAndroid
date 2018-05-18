package localhost.shadow.netappclient;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import javax.net.ssl.HttpsURLConnection;

public class ProgressFragment extends Fragment{

    TextView contentView, txt;
    String contentText = null;
    Fragment frag = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        contentView = (TextView) view.findViewById(R.id.content);
        txt = (TextView) view.findViewById(R.id.dataView);

        // если данные ранее были загружены
        if(contentText!=null){
            contentView.setText(contentText);
           // webView.loadData(contentText, "text/html; charset=utf-8", "utf-8");
        }

        Button btnFetch = (Button)view.findViewById(R.id.downloadBtn);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(contentText==null){
                    contentView.setText("Загрузка...");
                    new ProgressTask().execute("http://192.168.88.2:8088/");
                }
            }
        });
        return view;
    }

    private class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {

            String content;
            try{
                content = getContent(path[0]);
            }
            catch (IOException ex){
                content = ex.getMessage();
            }

            return content;
        }
        @Override
        protected void onPostExecute(String content) {

            contentText=content;
           // contentView.setText(content);
           /// contentView.setVisibility(View.INVISIBLE);
            contentView.setText("Данные загружены, но не обработаны....");

            setContent(content);

            ///webView.loadData(content, "text/html; charset=utf-8", "utf-8");
            Toast.makeText(getActivity(), "Данные загружены", Toast.LENGTH_SHORT)
                    .show();
        }

        private String getContent(String path) throws IOException {
            BufferedReader reader=null;
            try {
                URL url=new URL(path);
            //    HttpsURLConnection c=(HttpsURLConnection)url.openConnection();
                HttpURLConnection c=(HttpURLConnection)url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(10000);
                c.connect();
                reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder buf=new StringBuilder();
                String line=null;
                while ((line=reader.readLine()) != null) {
                    buf.append(line + "\n");
                }
                return(buf.toString());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }

    public void setContent(String content){

        JSONArray json = null;
        try {
             json = new JSONArray(content);

             for (int i=0; i<json.length(); i++){
                 JSONObject obj = json.getJSONObject(i);
                 String nameStr = obj.getString("name");
                 Log.d("JSON name=", nameStr);
                 String accStr = obj.getString("account");
                 Log.d("JSON acc=", accStr);
                    }

                } catch (JSONException ex) {
            Log.e("JSONException", ex.toString());
                        }


           // txt.setText(content);
    }


}
