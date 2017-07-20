package pers.dongchangzhang.todayisbeautiful.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

/**
 * Created by cc on 17-7-19.
 */

public class Tools {
    public synchronized static Drawable StringToDrawable(String icon) {
        if (icon == null || icon.length() < 10)
            return null;
        byte[] img = Base64.decode(icon.getBytes(), Base64.DEFAULT);
        Bitmap bitmap;
        if (img != null) {
            bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bitmap);

            return drawable;
        }
        return null;
    }
    public static int getImageByReflect(Context con, String resourceName){
        Resources res= con.getResources();
        int picid = res.getIdentifier(resourceName,"drawable",con.getPackageName());
        return picid;
    }
}
