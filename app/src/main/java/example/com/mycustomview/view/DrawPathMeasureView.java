package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import example.com.mycustomview.R;

/**
 * Created by wanghao on 2017/5/27.
 */

public class DrawPathMeasureView extends View {
    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    public DrawPathMeasureView(Context context) {
        super(context);
        init(context);
    }

    public DrawPathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawPathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arraw, options);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int mViewWidth = getWidth();
        int mViewHeight = getHeight();
        Paint mDeafultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDeafultPaint.setColor(Color.RED);
        mDeafultPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);      // 平移坐标系



//让 图片根据path 移动 方法1 getPosTan（）方法

        Path path = new Path();                                 // 创建 Path
//        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
//        path.addRect(0, 0, 200,200, Path.Direction.CW);           // 添加一个圆形
        path.lineTo(200, 800);
        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势

        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mDeafultPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mDeafultPaint);                     // 绘制箭头

        invalidate();                                                           // 重绘页面

//
////让 图片根据path 移动 方法2 getMatrix（） 方法
//        Path path2 = new Path();                                 // 创建 Path
//        mMatrix.reset();
//
////        path2.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
//        path2.lineTo(200, 800);
//
//        PathMeasure measure2 = new PathMeasure(path2, false);     // 创建 PathMeasure
//
//        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
//        if (currentValue >= 1) {
//            currentValue = 0;
//        }
//
//// 获取当前位置的坐标以及趋势的矩阵
//        measure2.getMatrix(measure2.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
//        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)
//
//        canvas.drawPath(path2, mDeafultPaint);                                   // 绘制 Path
//        canvas.drawBitmap(mBitmap, mMatrix, mDeafultPaint);                     // 绘制箭头
//
//        invalidate();                                                           // 重绘页面
    }
}
