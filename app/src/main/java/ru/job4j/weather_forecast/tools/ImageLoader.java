package ru.job4j.weather_forecast.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ImageLoader {

    private static Bitmap loadImage(String url) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void setIcon(ImageView view, String path) {
        if (view != null && path != null) {
            final Bitmap[] bitmap = {null};
            Thread t = new Thread(() -> {
                bitmap[0] = loadImage(path);
                view.post(() -> view.setImageBitmap(bitmap[0]));
            });
            t.start();
        }
    }
}
