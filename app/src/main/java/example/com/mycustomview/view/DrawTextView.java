package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import example.com.mycustomview.R;


/**
 * 创建日期: 15/11/26 上午9:34
 * 作者:wanghao
 * 描述:
 */
public class DrawTextView extends View implements Runnable {
    TextPaint tPaint;
    Paint.FontMetrics fontMetrics;
    int degree;
    int linewidth = 5;

    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        tPaint.setColor(Color.BLACK);
        tPaint.setStrokeWidth(10);
        tPaint.setTextSize(40);

        text = getResources().getString(R.string.drawtext);
        text2 = getResources().getString(R.string.drawtext2);


    }

    String text;
    String text2;
    int height;
    int width;
    float textHeight;
    float textWidth;
    float transHeight;//y周方向的偏移量

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        height = canvas.getHeight();
        width = canvas.getWidth();


        Rect bounds = new Rect();
        tPaint.getTextBounds(text, 0, text.length(), bounds);
        textHeight = bounds.height();//字体高度
        textWidth = tPaint.measureText(text);//字体宽度


        canvas.save();
        tPaint.setTextSize(90);
        tPaint.setColor(Color.BLACK);
        canvas.translate(0, textHeight);
        canvas.drawText(text, 0, 0, tPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, textHeight * 3);
        tPaint.setColor(0xff00ff00);//设置字体为绿色
        canvas.drawText(text, 0, 0, tPaint);
        canvas.restore();


        canvas.save();
        tPaint.setColor(Color.BLACK);
        canvas.translate(width / 2, textHeight * 4);
        tPaint.setTextAlign(Paint.Align.LEFT);//左对齐
        canvas.drawText("左对齐", 0, 0, tPaint);
        canvas.restore();

        canvas.save();
        tPaint.setColor(Color.BLACK);
        canvas.translate(width / 2, textHeight * 5);
        tPaint.setTextAlign(Paint.Align.RIGHT);//右对齐
        canvas.drawText("右对齐", 0, 0, tPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(width / 2, textHeight * 6);
        tPaint.setTextAlign(Paint.Align.CENTER);//中对其对齐
        canvas.drawText("中间对其", 0, 0, tPaint);
        canvas.restore();


//       下 划线
        canvas.save();
        tPaint.setUnderlineText(true);
        tPaint.setTextAlign(Paint.Align.LEFT);//重新设置为左对齐
        canvas.translate(0, textHeight * 7);
        canvas.drawText("下划线", 0, 0, tPaint);
        tPaint.setUnderlineText(false);

        canvas.restore();


        //绘制加粗文字 文字倾斜

        canvas.save();
        tPaint.setFakeBoldText(true);//将画笔设置为粗体
        tPaint.setTextSkewX(-0.9f);
        canvas.translate(0, textHeight * 8);
        canvas.drawText("加粗倾斜", 0, 0, tPaint);
        tPaint.setFakeBoldText(false);//将画笔设置为粗体
        tPaint.setTextSkewX(0f);
        canvas.restore();


        //文字旋转

        canvas.save();
        canvas.translate(width / 2, textHeight * 9);
        canvas.rotate(degree);
        canvas.drawText("文字旋转", 0, 0, tPaint);
        canvas.restore();
        //根据path写文字

        canvas.save();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(70);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(width / 2, textHeight * 14);

        RectF oval = new RectF(0, 0, 500, 400);
        Path path = new Path();
        path.addArc(oval, 60, 180);//一个弧线的路径
        canvas.drawPath(path, paint);
//        写的文字，path，距离path开始位置的距离，- 在线下面，+在上面  数值为距离
        canvas.drawTextOnPath("根据path写文字", path, 150, -30, paint);
        canvas.restore();

        //自动换行

        canvas.save();
        canvas.translate(0, textHeight * 11);
        // 实例化画笔
        TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);
        StaticLayout staticLayout = new StaticLayout(text2, mTextPaint, linewidth,
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        staticLayout.draw(canvas);
        canvas.restore();
//


    }


    @Override
    public void run() {
        while (true) {
            if (degree > 360) {
                degree = 0;

            } else {
                degree++;
                SystemClock.sleep(20);
            }
            if (linewidth > width) {
                linewidth = 5;

            } else {
                linewidth++;
                SystemClock.sleep(20);
            }
            postInvalidate();
        }
    }
}
