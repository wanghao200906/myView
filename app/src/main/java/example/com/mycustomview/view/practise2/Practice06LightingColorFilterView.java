package example.com.mycustomview.view.practise2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import example.com.mycustomview.R;


public class Practice06LightingColorFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public Practice06LightingColorFilterView(Context context) {
        super(context);
    }

    public Practice06LightingColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice06LightingColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setColorFilter() 来设置 LightingColorFilter
        ColorFilter colorFilter = new LightingColorFilter(0x00ffff, 0x000000);//第一个参数 减弱颜色，第二个参数 增强某种颜色
        paint.setColorFilter(colorFilter);
        // 第一个 LightingColorFilter：去掉红色部分
        canvas.drawBitmap(bitmap, 0, 0, paint);


        // 第二个 LightingColorFilter：增强绿色部分

        ColorFilter colorFilter2 = new LightingColorFilter(0xffffff, 0x003000);
        paint.setColorFilter(colorFilter2);
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 100, 0, paint);

        // 第二个 LightingColorFilter：增强绿色部分

        ColorFilter colorFilter3 = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
        paint.setColorFilter(colorFilter3);
        canvas.drawBitmap(bitmap, bitmap.getWidth() * 2 + 100, 0, paint);
    }
}
