package net.foofish.toastdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.foofish.intent.toastdemo.toastdemo.R;

public class ToastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        initEvent();
    }

    private void initEvent(){
        Button btnToast1 = (Button)findViewById(R.id.btnToast1);
        btnToast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast();
            }
        });
    }

    private void showToast(){
        Toast toast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);
        toast.show();
    }

}
