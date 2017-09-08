package example.com.mycustomview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import example.com.mycustomview.application.BaseApplication;
import example.com.mycustomview.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Button btn_basic_customview;
    Button btn_complex_customview;
    Button btn_custom_control;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_basic_customview = (Button) findViewById(R.id.btn_basic_customview);
        btn_complex_customview = (Button) findViewById(R.id.btn_complex_customview);
        btn_custom_control = (Button) findViewById(R.id.btn_custom_control);

        btn_basic_customview.setOnClickListener(this);
        btn_complex_customview.setOnClickListener(this);
        btn_custom_control.setOnClickListener(this);

        Log.d("MainActivity11111111", "adsf");
//        String url = "/storage/emulated/0/Android/data/example.com.mycustomview/files/hahaha";
        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(BaseApplication.getApplication(), "");
        Log.d("MainActivity11111111", externalFilesDirs.length + "");

//        for (File externalFilesDir : externalFilesDirs) {
//            Log.d("MainActivity", externalFilesDir.getPath());
//        }

        String url1 = externalFilesDirs[0].getPath() + "/" + "hahak123kk";
        File file2 = new File(url1);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File file = new File(file2, "123llj.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            String info = "I am a chinanese!";
            fos.write(info.getBytes());
            fos.close();
            Log.d("MainActivity11111111", "写入成功：");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

//            File file1 = new File(externalFilesDirs[0].getPath(), "abc.txt");

            FileInputStream is = new FileInputStream(file);
            byte[] b = new byte[100000];
            is.read(b);
            String result = new String(b);
            Log.d("MainActivity11111111", "读取成功：" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_basic_customview:


                gotoActivity(BasicCustomViewActivity.class, false);


                break;
            case R.id.btn_complex_customview:
                gotoActivity(ComplexCostomActivity.class, false);


                break;
            case R.id.btn_custom_control:
                gotoActivity(CustomControlActivity.class, false);

                break;

            default:
                break;
        }
    }

}
