package mymodule.app2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private final MyServiceBinder serviceBinder= new MyServiceBinder();
    private Timer timer;
    private TimerTask task;
    private int i = 0;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("bind");
        return serviceBinder;
    }

    public int getI(){
        return this.i;
    }

    class MyServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    private void startTask(){
        if (timer==null){
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    i++;
                    System.out.println(i);
                }
            };
            timer.schedule(task, 1000, 1000);
        }
    }

    private void stopTask(){
        if (timer!=null){
            task.cancel();
            timer.cancel();
        }
    }



    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("service create");
        startTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("service destroy");
        stopTask();
    }
}
