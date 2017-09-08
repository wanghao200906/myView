package example.com.mycustomview.viewActivity.customControlActivity.SlideDrawLayout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by wanghao on 2017/8/22.
 */

public class SlidingDrawLayout extends FrameLayout {
    private static final String TAG = "SlidingMenu";
    private View menuView, mainView;
    private int menuWidth;
    private int mainWidth;


    private Status status = Status.Close;
    private Status preStatus = Status.Close; // 前一次保持的状态
    private ViewDragHelper mdDragHelper;

    private OnStatusListener listener;

    //    如果是true ，slid拦截事件
    public static boolean ENABLE_TOUCH = true;

    public interface OnStatusListener {

        void statusChanged(Status status);

    }

    public void setOnStatusListener(OnStatusListener listener) {
        this.listener = listener;
    }

    public enum Status {
        Open, Close
    }

    public SlidingDrawLayout(Context context) {
        this(context, null);
    }

    public SlidingDrawLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingDrawLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mdDragHelper = ViewDragHelper.create(this, callback);
    }

    Callback callback = new Callback() {

        @Override
        public boolean tryCaptureView(View view, int arg1) {
            return true;
        }

        public int getViewHorizontalDragRange(View child) {
            return menuWidth;
        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == mainView) {
                if (left < 0)
                    return 0;
                else if (left > menuWidth)
                    return menuWidth;
                else
                    return left;
            } else if (child == menuView) {
                if (left > 0)
                    return 0;
                else if (left > menuWidth)
                    return menuWidth;
                else
                    return left;
            }
            return 0;
        }

        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {
            Log.d(TAG, "onViewPositionChanged dx: " + dx);

            if (changedView == mainView)
                menuView.offsetLeftAndRight(dx);
            else
                mainView.offsetLeftAndRight(dx);
            invalidate();
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            Log.d(TAG, "onViewReleased xvel: " + xvel);
            Log.d(TAG, "onViewReleased yvel: " + yvel);
            Log.d(TAG, "onViewReleased mainView.getLeft(): " + mainView.getLeft());
            Log.d(TAG, "onViewReleased menuWidth / 2.0f: " + menuWidth / 2.0f);


            if (releasedChild == mainView) {
                if (status == Status.Open
                        && Math.abs(mainView.getLeft()) < menuWidth / 2.0f) {
                    close();
                    return;
                }
                if (xvel == 0
                        && Math.abs(mainView.getLeft()) > menuWidth / 2.0f) {
                    open();
                } else if (xvel > 0) {
                    open();
                } else {
                    close();
                }
            } else {
                if (xvel == 0
                        && Math.abs(mainView.getLeft()) > menuWidth / 2.0f) {
                    open();
                } else if (xvel > 0) {
                    open();
                } else {
                    close();
                }
            }
        }

    };

    /**
     * 打开菜单
     */
    public void open() {
        if (mdDragHelper.smoothSlideViewTo(mainView, menuWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        preStatus = status;
        status = Status.Open;
        if (listener != null && preStatus == Status.Close) {
            listener.statusChanged(status);
        }
    }

    /**
     * 关闭菜单
     */
    public void close() {
        if (mdDragHelper.smoothSlideViewTo(mainView, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        preStatus = status;
        status = Status.Close;
        if (listener != null && preStatus == Status.Open) {
            listener.statusChanged(status);
        }
    }

    /**
     * 切换菜单状态
     */
    public void toggle() {
        if (status == Status.Close) {
            open();
        } else {
            close();
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 开始执行动画
        if (mdDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new IllegalArgumentException("子view的数量必须为2个");
        }
        menuView = getChildAt(0);
        mainView = getChildAt(1);
        Log.d(TAG, "onFinishInflate : ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mainWidth = mainView.getMeasuredWidth();


        ViewGroup.LayoutParams leftParams = menuView.getLayoutParams();
        leftParams.width = (int) (mainWidth * 0.8);
        menuView.setLayoutParams(leftParams);
        menuWidth = menuView.getMeasuredWidth();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        Log.d(TAG, "onLayout: ");

        menuView.layout(-menuWidth, 0, 0, menuView.getMeasuredHeight());

        mainView.layout(0, 0, right, bottom);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ENABLE_TOUCH)
            return mdDragHelper.shouldInterceptTouchEvent(ev);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mdDragHelper.processTouchEvent(event);
        return true;
    }

}
