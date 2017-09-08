package example.com.mycustomview.viewActivity.complexActivity.matrixColor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.utils.ImageHelper;

/**
 * Created by wanghao on 2017/7/21.
 */

public class PrimaryActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView imageView;
    private SeekBar seekBarhue;
    private SeekBar seekBarSaturation;
    private SeekBar seekBarlum;

    public static int MAX_VALUE = 255;
    public static int MID_VALUE = 127;


    private float mHue, mSaturaion, mLum;

    private Bitmap mBitmap;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_color);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        imageView = (ImageView) findViewById(R.id.imageview);
        seekBarhue = (SeekBar) findViewById(R.id.seekbarHue);
        seekBarSaturation = (SeekBar) findViewById(R.id.seekbarStaturation);
        seekBarlum = (SeekBar) findViewById(R.id.seekbarLum);

        seekBarhue.setOnSeekBarChangeListener(this);
        seekBarSaturation.setOnSeekBarChangeListener(this);
        seekBarlum.setOnSeekBarChangeListener(this);

        seekBarhue.setMax(MAX_VALUE);
        seekBarSaturation.setMax(MAX_VALUE);
        seekBarlum.setMax(MAX_VALUE);
        seekBarhue.setProgress(MID_VALUE);
        seekBarSaturation.setProgress(MID_VALUE);
        seekBarlum.setProgress(MID_VALUE);

        imageView.setImageBitmap(mBitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()) {
            case R.id.seekbarHue:
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seekbarStaturation:
                mSaturaion = progress * 1.0f / MID_VALUE;

                break;
            case R.id.seekbarLum:
                mLum = progress * 1.0f / MID_VALUE;

                break;

        }
        Bitmap bitmap = ImageHelper.handleImageEffect(mBitmap, mHue, mSaturaion, mLum);
        imageView.setImageBitmap(bitmap);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
