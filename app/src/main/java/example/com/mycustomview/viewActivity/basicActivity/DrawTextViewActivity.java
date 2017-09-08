package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.view.DrawTextView;

public class DrawTextViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
        DrawTextView drawtv = (DrawTextView) findViewById(R.id.drawtv);

        new Thread(drawtv).start();
    }
}
