package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.viewActivity.basicActivity.bezierActivity.DrawPathBezier2OrderActivity;
import example.com.mycustomview.viewActivity.basicActivity.bezierActivity.DrawPathBezier3OrderActivity;
import example.com.mycustomview.viewActivity.basicActivity.bezierActivity.DrawPathBezierCircleHeartActivity;
import example.com.mycustomview.viewActivity.basicActivity.bezierActivity.DrawPathLineBezierActivity;

/**
 * Created by wanghao on 2017/5/24.
 */

public class DrawPathBezierActivity extends BaseActivity implements View.OnClickListener {
    Button btn_bezier2order;
    Button btn_bezier3order;
    Button btn_bezier_circle_heart;
    Button btn_bezier_line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezierlayout);

        init();


    }

    private void init() {
        btn_bezier2order = (Button) findViewById(R.id.btn_bezier2order);
        btn_bezier3order = (Button) findViewById(R.id.btn_bezier3order);
        btn_bezier_circle_heart = (Button) findViewById(R.id.btn_bezier_circle_heart);
        btn_bezier_line = (Button) findViewById(R.id.btn_bezier_line);

        btn_bezier2order.setOnClickListener(this);
        btn_bezier3order.setOnClickListener(this);
        btn_bezier_circle_heart.setOnClickListener(this);
        btn_bezier_line.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bezier2order:
                gotoActivity(DrawPathBezier2OrderActivity.class, false);
                break;
//
            case R.id.btn_bezier3order:
                gotoActivity(DrawPathBezier3OrderActivity.class, false);
                break;
            case R.id.btn_bezier_circle_heart:
                gotoActivity(DrawPathBezierCircleHeartActivity.class, false);
                break;
            case R.id.btn_bezier_line:
                gotoActivity(DrawPathLineBezierActivity.class, false);
                break;


            default:
                break;
        }
    }
}