package example.com.mycustomview.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class ViewUitl {


    public static int getSize(int SizeInfoMeasureSpec, int limitSize) {

        int mode = View.MeasureSpec.getMode(SizeInfoMeasureSpec);
        int size = limitSize;
        switch (mode) {
            case View.MeasureSpec.EXACTLY: {
                size = View.MeasureSpec.getSize(SizeInfoMeasureSpec);
            }
            break;
            case View.MeasureSpec.AT_MOST: {
                size = Math.min(size, View.MeasureSpec.getSize(SizeInfoMeasureSpec));
            }
            break;
            default: {
                size = limitSize;
            }
            break;
        }
        return size;
    }

    /**
     * 获取角度的方法
     *
     * @param
     * @return 角度
     */
    public float getDegrees(Point pointA, Point pointB) {
        return (float) Math.toDegrees(Math.atan2(pointB.y - pointA.y, pointB.x - pointA.x));
    }

    public static double distance(Point a, Point b) {
        //x轴差的平方加上y轴差的平方，相加开方
        return Math.sqrt(Math.abs(a.x - b.x) * Math.abs(a.x - b.x) + Math.abs(a.y - b.y) * Math.abs(a.y - b.y));
    }

    public static int dip2px(Context context, float dpValue) {

        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);

    }

    public static int px2dip(Context context, float pxValue) {

        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }


}