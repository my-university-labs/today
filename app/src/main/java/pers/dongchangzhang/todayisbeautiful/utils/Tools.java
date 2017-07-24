package pers.dongchangzhang.todayisbeautiful.utils;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.dao.AssetsDatabaseManager;
import pers.dongchangzhang.todayisbeautiful.entity.CityBean;

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
    public static List<CityBean> getCityInfoFromDB(String id, String parent_id) {
        List<CityBean> list = new ArrayList<CityBean>();
        String TAG = "getCityInfoFromDB";
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();
        SQLiteDatabase city = mg.getDatabase("city.db");
        Cursor cursor;
        // this
        try {
            if (!parent_id.equals("0")
                    || (parent_id.equals("0")
                    && (id.equals("1") || id.equals("2") || id.equals("3") || id.equals("4") || id.equals("33")))) {
                cursor = city.rawQuery("select id, parent_id, name from district where id=" + id, null);
                while (cursor.moveToNext()) {
                    String name = cursor.getString(2);
                    list.add(new CityBean("-", name, "-"));
                }
            }
        }catch (Exception e) {

        }
        boolean have = false;
        // next
        try {
            cursor = city.rawQuery("select id, parent_id, name from district where parent_id=" + id, null);
            while (cursor.moveToNext()) {
                have = true;
                String i = cursor.getString(0);
                String pid = cursor.getString(1);
                String name = cursor.getString(2);
                list.add(new CityBean(i, name, pid));
                Log.d(TAG, "result: id=" + i + " name=" + name + " parent_id=" + pid);
            }
        }catch (Exception e) {

        }
        if (!have) {
            list.clear();
        }
//        cursor.close();
//        city.close();
        return list;
    }
}
