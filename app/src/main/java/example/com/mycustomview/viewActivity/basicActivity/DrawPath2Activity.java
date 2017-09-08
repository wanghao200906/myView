package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;

public class DrawPath2Activity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_view2);

//        DrawPathView2 drawpath = (DrawPathView) findViewById(R.id.drawpath);
//        new Thread(drawpath).start();
    }
}