package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.view.DrawcoordinateView;


/**
 * 创建日期: 15/11/17 下午10:19
 * 作者:wanghao
 * 描述:
 */
public class DrawcoordinateActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinate_view);

        DrawcoordinateView view_coordinate = (DrawcoordinateView) findViewById(R.id.view_coordinate);
        new Thread(view_coordinate).start();
    }
}
