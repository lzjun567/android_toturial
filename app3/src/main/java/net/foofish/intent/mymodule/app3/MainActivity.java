package net.foofish.intent.mymodule.app3;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnToast = null;
    private Button btnSendNotify = null;
    private Button btnCancelNotify = null;
    private int notificationId = 001;
    private NotificationManager manager;
    private Button btnService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvent();
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private void initEvent() {
        btnToast = (Button) findViewById(R.id.btnToast1);
        btnSendNotify = (Button) findViewById(R.id.btnSendNotifi);
        btnCancelNotify = (Button)findViewById(R.id.btnCancelNotifi);
        btnToast.setOnClickListener(this);
        btnSendNotify.setOnClickListener(this);
        btnCancelNotify.setOnClickListener(this);
        btnService = (Button)findViewById(R.id.btnService);
        btnService.setOnClickListener(this);
    }

    private void showToast() {
        Toast toast = Toast.makeText(this, "再按一次退出程序a", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void sendNotify(){
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setSmallIcon(R.drawable.ic_launcher);
        nBuilder.setContentTitle("天气乱报");
        nBuilder.setContentText("明天阴转暴雨，记得带伞，别忘了哦");
        nBuilder.setTicker("新消息");

        Intent resultIntent = new Intent(this, ResultActivity.class);
        PendingIntent resultPendinTent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT );
        nBuilder.setContentIntent(resultPendinTent);

        Notification notification = nBuilder.build();
        manager.notify(notificationId, notification);
    }

    private void cancelNotify(){
        manager.cancel(notificationId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnService:
                Intent intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.btnToast1:
                System.out.println("toast");
                showToast();
                break;
            case R.id.btnSendNotifi:
                System.out.println("send");
                sendNotify();
                break;
            case R.id.btnCancelNotifi:
                System.out.println("cancel");
                cancelNotify();
                break;
        }

    }
}
