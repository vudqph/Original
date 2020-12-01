package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.model.CuaHang;

class CuaHangDAO {
    private SQLiteDB sqLiteDB;

    public CuaHangDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }

    public void addStore(CuaHang cuaHang) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cuaHangID", cuaHang.getIdCuaHang());
        contentValues.put("tenCuaHang", cuaHang.getTenCuaHang());
        contentValues.put("dichVuCuaHang", cuaHang.getDichVuCuaHang());
        contentValues.put("diaDiemCuaHang", cuaHang.getDiaChiCuaHang());
        contentValues.put("kinhDoCuaHang", cuaHang.getKinhDoCuaHang());
        contentValues.put("vidoCuaHang", cuaHang.getViDoCuaHang());
        sqLiteDatabase.insert("cuaHang", null, contentValues);
    }

    public List<CuaHang> getAllStore() {
        List<CuaHang> cuaHangList = new ArrayList<>();
        String query_getAllCuaHang = "SELECT * FROM cuaHang";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(query_getAllCuaHang, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenCuaHang = cursor.getString(1);
                String dichVuCuaHang = cursor.getString(2);
                String diaDiemCuaHang = cursor.getString(3);
                String kinhDoCuaHang = cursor.getString(4);
                String viDoCuaHang = cursor.getString(5);
                String anhCuaHang = cursor.getString(6);
                CuaHang cuaHang = new CuaHang();
                cuaHang.setTenCuaHang(tenCuaHang);
                cuaHang.setDiaChiCuaHang(diaDiemCuaHang);
                cuaHang.setDichVuCuaHang(dichVuCuaHang);
                cuaHang.setKinhDoCuaHang(Double.parseDouble(kinhDoCuaHang));
                cuaHang.setViDoCuaHang(Double.parseDouble(viDoCuaHang));
                cuaHang.setAnhCuaHang(Integer.parseInt(anhCuaHang));
                cuaHangList.add(cuaHang);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return cuaHangList;
    }

    public void xoaCuaHang(String cuaHangID) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("cuaHang", "cuaHangID=?", new String[]{cuaHangID});
    }
}
