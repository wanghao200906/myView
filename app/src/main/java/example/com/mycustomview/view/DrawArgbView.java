package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * 创建日期: 15/11/22 下午8:44
 * 作者:wanghao
 * 描述:
 */
public class DrawArgbView extends View {
    public DrawArgbView(Context context) {
        this(context, null);
    }

    public DrawArgbView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawArgbView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Paint mPaint;

    Bitmap bitmap1;
    Bitmap bitmap2;
    Bitmap bitmap3;

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
//        生成三个画布，
        bitmap1 = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        bitmap2 = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        bitmap3 = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.GRAY);

        Canvas c = new Canvas(bitmap1);
        c.drawARGB(200, 223, 123, 123);
        c.drawLine(0, 600, 100, 0, mPaint);
        canvas.drawBitmap(bitmap1, 0, 0, null);

        Canvas c2 = new Canvas(bitmap2);
        c2.drawColor(Color.GREEN);
        canvas.drawBitmap(bitmap2, 0, 400, null);


        Canvas c3 = new Canvas(bitmap3);
        c3.drawRGB(223, 123, 123);
        canvas.drawBitmap(bitmap3, 0, 900, null);


//        canvas,c,c2,c3 就是四个画布了可以在这四个画布上分别话不同的东西
    }
}
