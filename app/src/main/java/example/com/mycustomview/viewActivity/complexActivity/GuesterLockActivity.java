package example.com.mycustomview.viewActivity.complexActivity;

import android.os.Bundle;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.view.GuesterLockView;

/**
 * Created by wanghao on 2017/6/1.
 */

public class GuesterLockActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.guesterlock_view);
        final GuesterLockView guesterLockView = (GuesterLockView) findViewById(R.id.guester_lock_view);
        guesterLockView.setOnPatternChangeListener(new GuesterLockView.onPatternChangeListener() {
            @Override
            public void onPatternChange(int[] passwordstr) {
                for(int i=0;i<passwordstr.length;i++){

                    System.out.println("passwordstr:" + passwordstr[i]);
                }
//                guesterLockView.setErrorState(true);
            }
        });
    }
}