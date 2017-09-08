package example.com.mycustomview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * 创建日期: 16/2/28 下午10:08
 * 作者:wanghao
 * 描述: edittext在失去焦点的时候 滑动
 *
 */
public class MyEditText extends android.support.v7.widget.AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }
}
