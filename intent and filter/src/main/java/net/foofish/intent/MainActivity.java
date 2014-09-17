package net.foofish.intent;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ListActivity {
    private String ACTION_VIEW = "net.foofish.intent.ACTION_VIEW";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this,
                getData(),
                android.R.layout.simple_list_item_1,
                new String[]{"title"},
                new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "broadcast 设置了action",Toast.LENGTH_SHORT ).show();
            }
        }, new IntentFilter(ACTION_VIEW));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = (Intent)getData().get(position).get("intent");
        Boolean isActivity = (Boolean)getData().get(position).get("isActivity");
        if (isActivity){
            startActivity(intent);
        }else{
            sendBroadcast(intent);
        }

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(getItem("空Action的Activity", new Intent(), true));
        data.add(getItem("Action检测--Activity", new Intent(ACTION_VIEW), true));
        data.add(getItem("Action检测--Broadcast", new Intent(ACTION_VIEW), false));
        return data;
    }

    private Map<String, Object> getItem(String title, Intent intent, boolean isActivity){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", title);
        map.put("intent", intent);
        map.put("isActivity", isActivity);
        return map;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
