package pers.dongchangzhang.todayisbeautiful.dao;

/**
 * Created by cc on 17-7-23.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private final String PLANINFORMATION = "create table PlanInformations ("
            + "id integer primary key autoincrement, "
            + "checked int, "
            + "time text, "
            + "title text, "
            + "content text)";
    private final String EVENT = "create table Events ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "description text, "
            + "location text, "
            + "color integer, "
            + "startTime text, "
            + "endTime text, "
            + "allDay text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(PLANINFORMATION);
            db.execSQL(EVENT);

            Toast.makeText(mContext, "Database created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists PlanInformations");
            db.execSQL("drop table if exists Events");
            onCreate(db);
        } catch (Exception e) {
            ;
        }
    }
}
