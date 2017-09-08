package example.com.mycustomview.viewActivity.basicActivity.bezierActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.view.DrawPathBezier3Order;

/**
 * Created by wanghao on 2017/5/24.
 */

public class DrawPathBezier3OrderActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathbezier3order_view);
        final DrawPathBezier3Order drawPathBezier3Order = (DrawPathBezier3Order) findViewById(R.id.drawpathbezier3order);
        drawPathBezier3Order.setMode(true);

        final RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton2.setChecked(false);
                radioButton.setChecked(true);
                drawPathBezier3Order.setMode(true);
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton.setChecked(false);
                radioButton2.setChecked(true);

                drawPathBezier3Order.setMode(false);
            }
        });



    }
}
