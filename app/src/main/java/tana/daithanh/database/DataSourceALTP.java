package tana.daithanh.database;

/**
 * Created by Administrator on 01/02/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;


public class DataSourceALTP {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {
            "question",
            "_id",
            "level",
            "casea",
            "caseb",
            "casec",
            "cased",
            "truecase"};

    public DataSourceALTP(Context context) {
        dbHelper = new MySQLiteHelper(context);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dbHelper.openDataBase();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }




    // Lấy tất cả dữ liệu
    public ArrayList<Object> getAllNews() {

        ArrayList<Object> news = new ArrayList<Object>();

        Cursor cursor = database.query("Question", allColumns,
                null, null, null, null, "_id");


        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Question n = cursorToNews(cursor);
            news.add(n);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return news;
    }


    private Question cursorToNews(Cursor cursor) {
        Question news = new Question();

        news.setQuestion(cursor.getString(0));

        news.set_id(cursor.getString(1));
        news.setLevel(cursor.getString(2));
        news.setCasea(cursor.getString(3));
        news.setCaseb(cursor.getString(4));
        news.setCasec(cursor.getString(5));
        news.setCased(cursor.getString(6));
        news.setTruecase(cursor.getString(7));



        return news;
    }

}
