package mymodule.app2;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListViewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        String[] data = {"Java", "Python", "iOS"};
        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, data ));
        ListView listView = getListView();
        listView.setItemsCanFocus(true);
        listView.setSelection(2);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);*/

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String,Object> zhangsan = new HashMap<String, Object>();
        zhangsan.put("name", "zhangsan");
        zhangsan.put("age", 20);
        zhangsan.put("gender", "female");

        Map<String,Object> lisi = new HashMap<String, Object>();
        lisi.put("name", "li");
        lisi.put("age", 24);
        lisi.put("gender", "male");

        data.add(zhangsan);
        data.add(lisi);

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.my_list_vew,
        new String[]{"name", "age", "gender"},
        new int[]{R.id.txtName, R.id.txtAge, R.id.txtGender});

        setListAdapter(adapter);

    }
}
