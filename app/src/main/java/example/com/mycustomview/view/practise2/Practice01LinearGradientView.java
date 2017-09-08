package example.com.mycustomview.view.practise2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice01LinearGradientView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice01LinearGradientView(Context context) {
        super(context);
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 用 Paint.setShader(shader) 设置一个 LinearGradient
        // LinearGradient 的参数：坐标：(100, 100) 到 (500, 500) ；颜色：#E91E63 到 #2196F3
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int width = getWidth();
        int height = getHeight();

        canvas.save();
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 500);

        Shader shader1 = new LinearGradient(0, 0, width / 4, 0, Color.RED, Color.BLUE, Shader.TileMode.REPEAT);
        paint.setShader(shader1);
        RectF f = new RectF(0, 0, width, 100);
        canvas.drawRect(f, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 720);

        Shader shader2 = new LinearGradient(0, 0, width / 4, 0, Color.RED, Color.BLUE, Shader.TileMode.MIRROR);
        paint.setShader(shader2);
        RectF f2 = new RectF(0, 0, width, 100);
        canvas.drawRect(f2, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 820);

        Shader shader3 = new LinearGradient(0, 0, width / 4, 0, Color.RED, Color.BLUE, Shader.TileMode.MIRROR);
        paint.setShader(shader3);
        paint.setTextSize(40);
        canvas.drawText("我爱北京天安门我爱北京天安门我爱北京天安门我爱北京天安门", 0, 100, paint);
        canvas.restore();


    }
}

