package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import vn.poly.sotaythucung.model.BenhVien;

import java.util.ArrayList;
import java.util.List;

public class BenhVienDAO {
    private SQLiteDB sqLiteDB;

    public BenhVienDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }



    public void xoaBenhVien(String id) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("benhVien", "benhVienID=?", new String[]{id});
    }

    public void themBenhVien(BenhVien benhVien) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("benhVienID", benhVien.getIdBenhVien());
        contentValues.put("tenBenhVien", benhVien.getTenBenhVien());
        contentValues.put("anhBenhVien", benhVien.getResouceImages());
        contentValues.put("diaChiBenhVien", benhVien.getDiaChiBenhVien());
        contentValues.put("kinhDoBenhVien", benhVien.getKinhDo());
        contentValues.put("viDoBenhVien", benhVien.getViDo());
        contentValues.put("danhGiaBenhVien", benhVien.getDanhGiaBenhVien());
        sqLiteDatabase.insert("benhVien", null, contentValues);

    }

    public List<BenhVien> getAllBenhVien() {
        List<BenhVien> benhVienList = new ArrayList<>();
        String truyVan = "SELECT * FROM benhVien";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenBenhVien = cursor.getString(1);
                int anhBenhVien = cursor.getInt(2);
                String diaChiBenhVien = cursor.getString(3);

                BenhVien benhVien = new BenhVien();
                benhVien.setTenBenhVien(tenBenhVien);
                benhVien.setDiaChiBenhVien(diaChiBenhVien);
                benhVien.setResouceImages(anhBenhVien);
                benhVienList.add(benhVien);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return benhVienList;
    }

    public List<BenhVien>getMap() {
        List<BenhVien> benhVienList = new ArrayList<>();
        String truyVan = "SELECT * FROM benhVien";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(truyVan, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String kinhDo = cursor.getString(4);
                String viDo = cursor.getString(5);
                String rate = cursor.getString(6);
                BenhVien benhVien = new BenhVien();
                benhVien.setKinhDo(Double.parseDouble(kinhDo));
                benhVien.setViDo(Double.parseDouble(viDo));
                benhVien.setDanhGiaBenhVien(Integer.parseInt(rate));
                benhVienList.add(benhVien);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return benhVienList;
    }
}
