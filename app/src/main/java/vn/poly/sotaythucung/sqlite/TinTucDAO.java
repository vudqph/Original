package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.model.ThuCung;
import vn.poly.sotaythucung.model.TinTuc;

public class TinTucDAO {
    private SQLiteDB sqLiteDB;

    public TinTucDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }

    public void addNews(TinTuc tinTuc) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tinTucID", tinTuc.getIdNews());
        contentValues.put("tenTinTuc", tinTuc.getTitleNews());
        contentValues.put("anhTinTuc", tinTuc.getImgHeaderNews());
        contentValues.put("urlNews", tinTuc.getUrlNews());
        sqLiteDatabase.insert("tinTuc", null, contentValues);
    }

    public List<TinTuc> getAllNews() {
        List<TinTuc> tinTucList = new ArrayList<>();
        String query_getAll = "SELECT * FROM tinTuc";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(query_getAll, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tinTucID = cursor.getString(0);
                String tenTinTuc = cursor.getString(1);
                String anhTinTuc = cursor.getString(2);
                String urlNews = cursor.getString(3);
                TinTuc tinTuc = new TinTuc();
                tinTuc.setIdNews(tinTucID);
                tinTuc.setTitleNews(tenTinTuc);
                tinTuc.setImgHeaderNews(anhTinTuc);
                tinTuc.setUrlNews(urlNews);
                tinTucList.add(tinTuc);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return tinTucList;
    }

    public void deleteNews(String tinTucID) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("tinTuc", "tinTucID=?", new String[]{tinTucID});
    }
}
