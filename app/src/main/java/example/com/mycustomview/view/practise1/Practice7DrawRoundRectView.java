package example.com.mycustomview.view.practise1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice7DrawRoundRectView extends View {
    private int width;//view宽

    private int height;//view高


    private Paint paint;

    public Practice7DrawRoundRectView(Context context) {
        super(context);
        init();
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形


        width = getWidth();
        height = getHeight();
        paint.setStyle(Paint.Style.FILL);

        canvas.save();
        canvas.translate(width / 2, height / 2);
        RectF f = new RectF(-300, -200, 300, 200);
        canvas.drawRoundRect(f, 30, 30, paint);
        canvas.restore();
    }
}
