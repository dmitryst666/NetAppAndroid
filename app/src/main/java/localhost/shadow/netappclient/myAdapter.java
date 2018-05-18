package localhost.shadow.netappclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/// http://startandroid.ru/ru/uroki/vse-uroki-spiskom/113-urok-54-kastomizatsija-spiska-sozdaem-svoj-adapter.html
public class myAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<HashMap<String, String>> objects;

    myAdapter(Context context, ArrayList<HashMap<String, String>> objList){
        ctx = context;
        objects = objList;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.listview_item, parent, false);
        }

        HashMap<String,String> p = objects.get(position);
        ((TextView) view.findViewById(R.id.headerText)).setText(p.get("name"));
        ((TextView) view.findViewById(R.id.smallText)).setText(p.get("account"));
        ((TextView) view.findViewById(R.id.addressText)).setText(p.get("address"));

        return view;
    }
}
