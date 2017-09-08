package example.com.mycustomview.viewActivity.complexActivity.matrixColor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;

/**
 * Created by wanghao on 2017/7/21.
 */

public class ColorMatrixActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView;
    private GridLayout gridLayout;
    private Bitmap mBitmap;


    private int mEtWidth, mEtHeight;

    private EditText[] ets = new EditText[20];
    private float[] matrixColor = new float[20];
    public static int MAX_VALUE = 255;
    public static int MID_VALUE = 127;


    private float mHue, mSaturaion, mLum;

    private Button btn_change, btn_reset;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matrix);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        imageView = (ImageView) findViewById(R.id.imageview);
        gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        btn_change = (Button) findViewById(R.id.btn_change);
        btn_reset = (Button) findViewById(R.id.btn_reset);

        btn_reset.setOnClickListener(this);
        btn_change.setOnClickListener(this);

        imageView.setImageBitmap(mBitmap);

        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = gridLayout.getWidth() / 5;
                mEtHeight = gridLayout.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(ColorMatrixActivity.this);
            ets[i] = editText;
            gridLayout.addView(editText, mEtWidth, mEtHeight);
        }


    }

    private void getMatrixColor() {
        for (int i = 0; i < 20; i++) {
            matrixColor[i] = Float.valueOf(ets[i].getText().toString());

        }
    }

    private void setImageColorMatrix() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(matrixColor);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        canvas.drawBitmap(mBitmap, 0, 0, paint);
        imageView.setImageBitmap(bmp);
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                ets[i].setText(String.valueOf(1));
            } else {
                ets[i].setText(String.valueOf(0));
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change:
                getMatrixColor();
                setImageColorMatrix();
                break;
            case R.id.btn_reset:
                initMatrix();
                getMatrixColor();
                setImageColorMatrix();
                break;
        }
    }
}
