package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 创建日期: 15/12/12 下午10:41
 * 作者:wanghao
 * 描述:
 */
public class DrawArcView extends View implements Runnable {


    public DrawArcView(Context context) {
        this
                (context, null);
    }

    public DrawArcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    Paint paint;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = canvas.getWidth();
        int h = canvas.getHeight();

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);//默认设置画笔为填充模式


        RectF f = new RectF(0, 0, w, 150);//画的图形的 上下左右 范围
        canvas.drawArc(f,0,degree,true,paint);


        canvas.translate(0, 200);
        canvas.drawArc(f, 0, degree, false, paint);// 1,范围,2 起始角度(0为 3点钟方向),3 起始角度 旋转的读书,4,true:起始点和终止点 都连接到原点.fale :起始点终点 直接相连,5 画笔

        canvas.translate(0, 200);
        canvas.drawArc(f, 0, 90, true, paint);



        canvas.translate(0, 200);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(f, 0, 90, true, paint);

//        描边
        canvas.translate(0, 200);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(f, 0, degree, true, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawArc(f,0,degree,true,paint);

    }

int degree=0;
    @Override
    public void run() {

        while(true){

            if(degree>360){
                degree=0;
            }else{
                degree++;
            }
            SystemClock.sleep(50);
            postInvalidate();
        }


    }
}
