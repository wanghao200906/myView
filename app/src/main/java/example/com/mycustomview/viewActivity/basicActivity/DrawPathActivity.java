package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.view.DrawPathView;

public class DrawPathActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_view);

        DrawPathView drawpath = (DrawPathView) findViewById(R.id.drawpath);
        new Thread(drawpath).start();
    }
}