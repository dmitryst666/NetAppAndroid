package localhost.shadow.netappclient;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    View lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void setAdapter(ArrayList<HashMap<String,String>> map){

        Fragment frag1 = getFragmentManager().findFragmentById(R.id.contentFragment);
            lv = (ListView)findViewById(R.id.dataView);
//        ListAdapter adapter = new SimpleAdapter(MainActivity.this, map, R.layout.listview_item,
//                new String[] { "name","account", "address" }, new int[] {
//                R.id.headerText,R.id.smallText, R.id.addressText});
//        lv.setAdapter(adapter);
    }

}
