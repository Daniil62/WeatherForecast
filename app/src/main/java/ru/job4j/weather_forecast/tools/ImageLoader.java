package ru.job4j.weather_forecast.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ImageLoader {
    public List<Bitmap> loadImages(List<String> paths) {
        return loadImageList(paths);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Bitmap> loadImageList(List<String> paths) {
        return paths.stream().map(ImageLoader::loadImage).collect(Collectors.toList());
    }
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
