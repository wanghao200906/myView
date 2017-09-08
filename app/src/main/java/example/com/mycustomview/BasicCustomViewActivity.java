package example.com.mycustomview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.viewActivity.basicActivity.BasicCustomView1;
import example.com.mycustomview.viewActivity.basicActivity.BasicCustomView2;
import example.com.mycustomview.viewActivity.basicActivity.DragViewHelperActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawArcActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawArgbActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawBitmapActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawCircleActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawLineActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawOvalActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawPath2Activity;
import example.com.mycustomview.viewActivity.basicActivity.DrawPathActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawPathBezierActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawPathMeasureActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawPointActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawRectActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawRegionActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawRotateActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawScaleActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawSkewActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawTextViewActivity;
import example.com.mycustomview.viewActivity.basicActivity.DrawcoordinateActivity;
import example.com.mycustomview.viewActivity.basicActivity.SurfaceViewActivity;

/**
 * 创建日期: 15/11/17 下午10:12
 * 作者:wanghao
 * 描述: 自定义view 基础部分
 */
public class BasicCustomViewActivity extends BaseActivity implements View.OnClickListener {
    Button btn_axisview;
    Button btn_argbview;
    Button btn_textview;
    Button btn_pointview;
    Button btn_lineview;
    Button btn_rectview;
    Button btn_circletview;
    Button btn_ovaltview;
    Button btn_arctview;
    Button btn_pathview;
    Button btn_bitmapview;
    Button btn_scaleview;
    Button btn_rotateview;
    Button btn_skewview;
    Button btn_pathbezierview1;
    Button btn_pathview2;
    Button btn_pathmeasure;
    Button btn_regionview;
    Button btn_basic_customview2;
    Button btn_basic_customview1;
    Button btn_surfaceview;
    Button btn_dragviewhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basicview);
        init();


    }

    private void init() {


        btn_basic_customview1 = (Button) findViewById(R.id.btn_basic_customview1);
        btn_basic_customview2 = (Button) findViewById(R.id.btn_basic_customview2);

        btn_axisview = (Button) findViewById(R.id.btn_axisview);
        btn_argbview = (Button) findViewById(R.id.btn_argbview);
        btn_textview = (Button) findViewById(R.id.btn_textview);
        btn_pointview = (Button) findViewById(R.id.btn_pointview);
        btn_lineview = (Button) findViewById(R.id.btn_lineview);
        btn_rectview = (Button) findViewById(R.id.btn_rectview);
        btn_circletview = (Button) findViewById(R.id.btn_circletview);
        btn_ovaltview = (Button) findViewById(R.id.btn_ovaltview);
        btn_arctview = (Button) findViewById(R.id.btn_arctview);
        btn_pathview = (Button) findViewById(R.id.btn_pathview);
        btn_bitmapview = (Button) findViewById(R.id.btn_bitmapview);
        btn_scaleview = (Button) findViewById(R.id.btn_scaleview);
        btn_rotateview = (Button) findViewById(R.id.btn_rotateview);
        btn_skewview = (Button) findViewById(R.id.btn_skewview);
        btn_pathbezierview1 = (Button) findViewById(R.id.btn_pathbezierview1);
        btn_pathview2 = (Button) findViewById(R.id.btn_pathview2);
        btn_pathmeasure = (Button) findViewById(R.id.btn_pathmeasure);
        btn_regionview = (Button) findViewById(R.id.btn_regionview);
        btn_surfaceview = (Button) findViewById(R.id.btn_surfaceview);
        btn_dragviewhelper = (Button) findViewById(R.id.btn_dragviewhelper);
        btn_axisview.setOnClickListener(this);
        btn_argbview.setOnClickListener(this);
        btn_textview.setOnClickListener(this);
        btn_pointview.setOnClickListener(this);
        btn_lineview.setOnClickListener(this);
        btn_rectview.setOnClickListener(this);
        btn_circletview.setOnClickListener(this);
        btn_ovaltview.setOnClickListener(this);
        btn_arctview.setOnClickListener(this);
        btn_pathview.setOnClickListener(this);
        btn_bitmapview.setOnClickListener(this);
        btn_scaleview.setOnClickListener(this);
        btn_rotateview.setOnClickListener(this);
        btn_skewview.setOnClickListener(this);
        btn_pathbezierview1.setOnClickListener(this);
        btn_pathview2.setOnClickListener(this);
        btn_pathmeasure.setOnClickListener(this);
        btn_regionview.setOnClickListener(this);
        btn_basic_customview2.setOnClickListener(this);
        btn_basic_customview1.setOnClickListener(this);
        btn_surfaceview.setOnClickListener(this);
        btn_dragviewhelper.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_basic_customview:
                gotoActivity(BasicCustomView1.class, false);
                break;
            case R.id.btn_basic_customview2:
                gotoActivity(BasicCustomView2.class, false);
                break;
            case R.id.btn_axisview:
                gotoActivity(DrawcoordinateActivity.class, false);
                break;
//
            case R.id.btn_argbview:
                gotoActivity(DrawArgbActivity.class, false);
                break;
            case R.id.btn_textview:
                gotoActivity(DrawTextViewActivity.class, false);
                break;
            case R.id.btn_pointview:
                gotoActivity(DrawPointActivity.class, false);
                break;
            case R.id.btn_lineview:
                gotoActivity(DrawLineActivity.class, false);
                break;
            case R.id.btn_rectview:
                gotoActivity(DrawRectActivity.class, false);
                break;
            case R.id.btn_circletview:
                gotoActivity(DrawCircleActivity.class, false);
                break;
            case R.id.btn_ovaltview:
                gotoActivity(DrawOvalActivity.class, false);
                break;
            case R.id.btn_arctview:
                gotoActivity(DrawArcActivity.class, false);
                break;
            case R.id.btn_pathview:
                gotoActivity(DrawPathActivity.class, false);
                break;
            case R.id.btn_bitmapview:
                gotoActivity(DrawBitmapActivity.class, false);
                break;
            case R.id.btn_scaleview:
                gotoActivity(DrawScaleActivity.class, false);
                break;
            case R.id.btn_rotateview:
                gotoActivity(DrawRotateActivity.class, false);
                break;
            case R.id.btn_skewview:
                gotoActivity(DrawSkewActivity.class, false);
                break;
            case R.id.btn_pathbezierview1:
                gotoActivity(DrawPathBezierActivity.class, false);
                break;
            case R.id.btn_pathview2:
                gotoActivity(DrawPath2Activity.class, false);
                break;
            case R.id.btn_pathmeasure:
                gotoActivity(DrawPathMeasureActivity.class, false);
                break;
            case R.id.btn_regionview:
                gotoActivity(DrawRegionActivity.class, false);
                break;
            case R.id.btn_surfaceview:
                gotoActivity(SurfaceViewActivity.class, false);
                break;
            case R.id.btn_dragviewhelper:
                gotoActivity(DragViewHelperActivity.class, false);
                break;

            default:
                break;
        }
    }


}
