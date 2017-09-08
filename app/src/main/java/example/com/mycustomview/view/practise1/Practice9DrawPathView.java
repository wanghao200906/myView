package example.com.mycustomview.view.practise1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private int width;//view宽

    private int height;//view高

    private Path pathLeft;//左心

    private Path pathRight;//有心

    private Paint paint;

    public Practice9DrawPathView(Context context) {
        super(context);
        init();
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        pathRight= new Path();
        pathLeft = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形

        width = getWidth();
        height = getHeight();

        int heartWidth = width / 3;


        paint.setStyle(Paint.Style.FILL);

        canvas.save();

        canvas.translate(width / 2, height / 2);
        RectF f1 = new RectF(-heartWidth / 2, -heartWidth / 4, 0, heartWidth / 4);
        pathLeft.arcTo(f1, 0, -220);
        pathLeft.lineTo(0, heartWidth * 0.65f);
        canvas.drawPath(pathLeft, paint);


        RectF f2 = new RectF(0, -heartWidth / 4, heartWidth / 2, heartWidth / 4);
        pathRight.arcTo(f2, 180, 220);
        pathRight.lineTo(0, heartWidth * 0.65f);
        canvas.drawPath(pathRight, paint);

        canvas.restore();


    }
}
