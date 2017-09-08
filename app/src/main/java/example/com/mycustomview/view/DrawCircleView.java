package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建日期: 15/12/9 下午10:34
 * 作者:wanghao
 * 描述:
 */
public class DrawCircleView extends View {
    public DrawCircleView(Context context) {
        this(context, null);
    }

    public DrawCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }
    Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.FILL);//默认绘图为填充模式

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int halfCanvasWidth = canvasWidth / 2;

        int r= 50;
        //绘制圆
        canvas.translate(0, canvasHeight/6 );
        canvas.drawCircle(halfCanvasWidth, r, r, paint);

        //通过绘制两个圆形成圆环
        //1. 首先绘制大圆
        canvas.translate(0, canvasHeight/4 );
        canvas.drawCircle(halfCanvasWidth, r, r, paint);
        //2. 然后绘制小圆，让小圆覆盖大圆，形成圆环效果
        int r1 = (int)(r * 0.75);
        paint.setColor(0xffffffff);//将画笔设置为白色，画小圆
        canvas.drawCircle(halfCanvasWidth, r, r1, paint);

//        //通过线条绘图模式绘制圆环
        canvas.translate(0,canvasHeight/4);
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.STROKE);//绘图为线条模式
        float strokeWidth = (float)(r* 0.25);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(halfCanvasWidth, r, r, paint);




    }
}
