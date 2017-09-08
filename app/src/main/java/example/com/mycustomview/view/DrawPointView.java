package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建日期: 15/12/7 下午9:29
 * 作者:wanghao
 * 描述:
 */
public class DrawPointView extends View {

    Paint paint;

    public DrawPointView(Context context) {
        this(context, null);
    }

    public DrawPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int x = canvasWidth / 2;
        int deltaY = canvasHeight / 3;
        int y = deltaY / 2;
        int density = 2;
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStrokeWidth(50 * density);//设置线宽，如果不设置线宽，无法绘制点

    /*
     * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
     *
     * 画笔样式分三种：
     * 1.Paint.Style.STROKE：描边
     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
     * 3.Paint.Style.FILL：填充
     */
        paint.setStyle(Paint.Style.STROKE);


        //绘制Cap为BUTT的点
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(x, y, paint);

        //绘制Cap为ROUND的点
        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(x, y, paint);

        //绘制Cap为SQUARE的点
        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(x, y, paint);


        canvas.drawPoint(200, 200, paint);     //在坐标(200,200)位置绘制一个点
        paint.setStrokeWidth(40);
        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
                500,100,
                500,200,
                500,300
        },paint);
    }
}
