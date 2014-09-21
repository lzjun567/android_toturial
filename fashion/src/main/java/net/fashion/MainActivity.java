package net.fashion;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listClock);
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("itemTitle", "this is title");
            map.put("itemText", "this is text");
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list,
                R.layout.list_clock_item, new String[]{"itemTitle", "itemText"}
                , new int[]{R.id.itemTitle, R.id.itemText});

        listView.setAdapter(adapter);


    }


}
