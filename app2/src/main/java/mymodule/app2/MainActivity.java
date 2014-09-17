package mymodule.app2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, ServiceConnection {

    private Button btnAty1;
    private TextView txtMain;

    private Button btnStartService;
    private Button btnStopService;

    private Button btnBindService;
    private Button btnUnbindService;

    private Intent intent;

    private MyService service;

    private Button btnService;

    private Button btnBroadcast;

    private Button btnRegBroadcast;
    private Button btnUnregBroadcast;
    private final MyReceiver receiver = new MyReceiver();

    private Button btnImplictIntent;

    private Button btnListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAty1 = (Button)findViewById(R.id.btnAty1);
        txtMain = (TextView)findViewById(R.id.txtMain);

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null,null,null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            txtMain.append(name);
        }

        btnStartService = (Button)findViewById(R.id.btnStartService);
        btnStopService = (Button)findViewById(R.id.btnStopService);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);

        btnBindService = (Button)findViewById(R.id.btnBindService);
        btnUnbindService = (Button)findViewById(R.id.btnUnBindServie);

        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);

        btnService = (Button)findViewById(R.id.btnService);
        btnService.setOnClickListener(this);

        btnBroadcast = (Button)findViewById(R.id.btnBroadcast);

        btnRegBroadcast = (Button)findViewById(R.id.btnRegBroadcast);
        btnUnregBroadcast = (Button)findViewById(R.id.btnUnregBroadcast);

        btnImplictIntent = (Button)findViewById(R.id.btnImplicitIntent);

        btnImplictIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.foofish.intent.ACTION_VIEW");
                startActivity(intent);
            }
        });

        btnListView = (Button)findViewById(R.id.btnListView);
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        intent = new Intent(this, MyService.class);

        btnAty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Aty2.class);
                intent.putExtra("username","liuzhijun");
//                startActivity(intent);
                startActivityForResult(intent, 0);
            }
        });

        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
//                intent.setClass(MainActivity.this, MyReceiver.class);
                intent.setAction(MyReceiver.Action);
                sendBroadcast(intent);
            }
        });


        btnRegBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(receiver, new IntentFilter(MyReceiver.Action));
            }
        });

        btnUnregBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(receiver);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode);
        System.out.println(resultCode);
        String school = data.getExtras().getBundle("school").getString("school");
        txtMain.setText(school);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("stop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("restart");
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnStartService:
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnBindServie:
                unbindService(this);
                service = null;
                break;
            case R.id.btnService:
                if (service!=null){
                    int i = service.getI();
                    System.out.println("当前值是："+i);
//                    txtMain.setText(i);
                    txtMain.setText(new Integer(i).toString());
                }
        }

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        System.out.println(name.toString());
        System.out.println("service connected");
        service = ((MyService.MyServiceBinder)binder).getService();

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("service disconnect");
    }
}
