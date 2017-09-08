package example.com.mycustomview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.viewActivity.complexActivity.DrawMatrixBitmapActivity;
import example.com.mycustomview.viewActivity.complexActivity.DrawSearchViewPathActivity;
import example.com.mycustomview.viewActivity.complexActivity.DrawWaveActivity;
import example.com.mycustomview.viewActivity.complexActivity.GuesterLockActivity;
import example.com.mycustomview.viewActivity.complexActivity.ColorActivity;
import example.com.mycustomview.viewActivity.complexActivity.ZoomImageViewActivity;

/**
 * Created by wanghao on 2017/5/24.
 */

public class ComplexCostomActivity extends BaseActivity implements View.OnClickListener {

    Button btn_waveview;
    Button btn_searchview;
    Button btn_matrixbitmapview;
    Button btn_zoomview;
    Button btn_guesterlock;
    Button btn_colormatrix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complexview);
        init();


    }

    private void init() {
        btn_waveview = (Button) findViewById(R.id.btn_waveview);
        btn_searchview = (Button) findViewById(R.id.btn_searchview);
        btn_matrixbitmapview = (Button) findViewById(R.id.btn_matrixbitmapview);
        btn_zoomview = (Button) findViewById(R.id.btn_zoomview);
        btn_guesterlock = (Button) findViewById(R.id.btn_guesterlock);
        btn_colormatrix = (Button) findViewById(R.id.btn_colormatrix);

        btn_colormatrix.setOnClickListener(this);
        btn_waveview.setOnClickListener(this);
        btn_searchview.setOnClickListener(this);
        btn_matrixbitmapview.setOnClickListener(this);
        btn_zoomview.setOnClickListener(this);
        btn_guesterlock.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_waveview:
                gotoActivity(DrawWaveActivity.class, false);
                break;
            case R.id.btn_searchview:
                gotoActivity(DrawSearchViewPathActivity.class, false);
                break;
            case R.id.btn_matrixbitmapview:
                gotoActivity(DrawMatrixBitmapActivity.class, false);
                break;
            case R.id.btn_zoomview:
                gotoActivity(ZoomImageViewActivity.class, false);
                break;
            case R.id.btn_guesterlock:
                gotoActivity(GuesterLockActivity.class, false);
                break;
            case R.id.btn_colormatrix:
                gotoActivity(ColorActivity.class, false);
                break;
            default:
                break;
        }
    }
}