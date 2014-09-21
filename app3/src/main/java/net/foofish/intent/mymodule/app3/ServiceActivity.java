package net.foofish.intent.mymodule.app3;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnClickListener;

/**
 * Created by liuzhijun on 2014/9/21.
 */
public class ServiceActivity extends Activity implements OnClickListener {
    private Button btnStartService;
    private Button btnStopService;
    private Button btnBindService;
    private Button btnUnBindService;
    private MyService.MyBinder myBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder)service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.btnStopService:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.btnBindService:
                Intent intent = new Intent(this, MyService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(serviceConnection);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnStartService = (Button)findViewById(R.id.btnStartService);
        btnStopService = (Button)findViewById(R.id.btnStopService);

        btnBindService = (Button)findViewById(R.id.btnBindService);
        btnUnBindService = (Button)findViewById(R.id.btnUnbindService);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);

        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);

        Log.d("MyService","activity thread id is :"+Thread.currentThread().getId());
    }
}
