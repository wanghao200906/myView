package example.com.mycustomview.viewActivity.complexActivity.matrixColor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import example.com.mycustomview.R;
import example.com.mycustomview.base.BaseActivity;
import example.com.mycustomview.utils.ImageHelper;

/**
 * Created by wanghao on 2017/7/24.
 */

public class PixelsEffect extends BaseActivity {
    private ImageView imageview1, imageview2, imageview3, imageview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixels_effect);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        imageview3 = (ImageView) findViewById(R.id.imageview3);
        imageview4 = (ImageView) findViewById(R.id.imageview4);

        imageview1.setImageBitmap(bitmap);
        imageview2.setImageBitmap(ImageHelper.handlerImageNegative(bitmap));
        imageview3.setImageBitmap(ImageHelper.handlerImageOldPhoto(bitmap));
        imageview4.setImageBitmap(ImageHelper.handlerImagePixelsRelief(bitmap));
    }
}
