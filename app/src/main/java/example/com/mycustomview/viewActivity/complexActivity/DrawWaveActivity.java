package example.com.mycustomview.viewActivity.complexActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.view.DrawWaveView;

/**
 * Created by wanghao on 2017/5/25.
 */

public class DrawWaveActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wave_view);

        DrawWaveView wave_view = (DrawWaveView) findViewById(R.id.wave_view);

    }
}