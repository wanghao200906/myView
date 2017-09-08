package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.view.DrawBitmapView;

public class DrawBitmapActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bitmap_view);

        DrawBitmapView drawBitmapView = (DrawBitmapView) findViewById(R.id.drawbitmap);

        new Thread(drawBitmapView).start();
    }
}