package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;

public class DrawPointActivity  extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_view);

    }
}