package example.com.mycustomview.viewActivity.basicActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;


/**
 * 创建日期: 15/11/22 下午8:43
 * 作者:wanghao
 * 描述:
 */
public class DrawArgbActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.argb_view);

    }
}