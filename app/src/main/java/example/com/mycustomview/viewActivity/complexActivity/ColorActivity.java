package example.com.mycustomview.viewActivity.complexActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.viewActivity.complexActivity.matrixColor.ColorMatrixActivity;
import example.com.mycustomview.viewActivity.complexActivity.matrixColor.PixelsEffect;
import example.com.mycustomview.viewActivity.complexActivity.matrixColor.PrimaryActivity;

/**
 * Created by wanghao on 2017/7/21.
 */

public class ColorActivity extends BaseActivity implements View.OnClickListener {
    Button btn_colormatrix;
    Button btn_primary;
    Button btn_pixelseffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_color);


        btn_primary = (Button) findViewById(R.id.btn_primary);
        btn_colormatrix = (Button) findViewById(R.id.btn_colormatrix);
        btn_pixelseffect = (Button) findViewById(R.id.btn_pixelseffect);

        btn_primary.setOnClickListener(this);
        btn_colormatrix.setOnClickListener(this);
        btn_pixelseffect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_primary:
                gotoActivity(PrimaryActivity.class, true);
                break;
            case R.id.btn_colormatrix:
                gotoActivity(ColorMatrixActivity.class, true);
                break;
            case R.id.btn_pixelseffect:
                gotoActivity(PixelsEffect.class, true);
                break;
        }
    }
}
