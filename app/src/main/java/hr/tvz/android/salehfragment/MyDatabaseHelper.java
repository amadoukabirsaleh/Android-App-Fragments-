package hr.tvz.android.salehfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
   public Context context;
public static final String DATABASE_NAME="Products.db";
public static final int DATABASE_VERSİON = 1;

public static  final String TABLE_NAME = "my_products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MAKE = "make";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_OWNERNAME = "ownerName";
    private static final String COLUMN_OWNERTEL = "ownerTel";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSİON);
       this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_MAKE + " TEXT, " +
             COLUMN_MODEL + " TEXT, " +
             COLUMN_OWNERNAME + " TEXT, " +
             COLUMN_OWNERTEL + " TEXT) ;";
     db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
          onCreate(db);
    }

    void addBook (String make, String model, String ownerName, String ownerTel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MAKE, make);
        cv.put(COLUMN_MODEL, model);
        cv.put(COLUMN_OWNERNAME, ownerName);
        cv.put(COLUMN_OWNERTEL, ownerTel);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }

    }

        Cursor readAllData(){
          String query = "SELECT * FROM " + TABLE_NAME;
          SQLiteDatabase db = this.getReadableDatabase();

          Cursor cursor = null;
          if(db!=null){
              cursor = db.rawQuery(query, null);

          }
          return cursor;
    }


}
