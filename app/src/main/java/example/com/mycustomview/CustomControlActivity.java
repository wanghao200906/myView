package example.com.mycustomview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.viewActivity.customControlActivity.DrawLayout.DrawlayoutActivity;
import example.com.mycustomview.viewActivity.customControlActivity.SlideDrawLayout.SlidDrawlayoutActivity;

/**
 * Created by wanghao on 2017/8/22.
 */

public class CustomControlActivity extends BaseActivity implements View.OnClickListener {

    Button btn_drawlayout;
    Button btn_sliddrawlayoutactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_control);
        init();
    }

    private void init() {
        btn_drawlayout = (Button) findViewById(R.id.btn_drawlayout);
        btn_sliddrawlayoutactivity = (Button) findViewById(R.id.btn_sliddrawlayoutactivity);


        btn_drawlayout.setOnClickListener(this);
        btn_sliddrawlayoutactivity.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_drawlayout:
                gotoActivity(DrawlayoutActivity.class, false);
                break;
            case R.id.btn_sliddrawlayoutactivity:
                gotoActivity(SlidDrawlayoutActivity.class, false);
                break;

            default:
                break;
        }
    }
}