package vn.poly.sotaythucung.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.sotaythucung.model.CuaHang;
import vn.poly.sotaythucung.model.ThuCung;

public class ThuCungDAO {
    private SQLiteDB sqLiteDB;

    public ThuCungDAO(SQLiteDB sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }

    public void addPets(ThuCung thuCung) {
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("thuCungID", thuCung.getIdThuCung());
        contentValues.put("tenThucung", thuCung.getTenThuCung());
        contentValues.put("dacDiemThuCung", thuCung.getDacDiemThuCung());
        contentValues.put("theLoaiThuCung", thuCung.getLoaiThuCung());
        contentValues.put("anhThuCung", thuCung.getImageThuCung());
        contentValues.put("moTaThuCung", thuCung.getKhac());
        sqLiteDatabase.insert("thuCung", null, contentValues);
    }

    public List<ThuCung> getAllPets() {
        List<ThuCung> thuCungList = new ArrayList<>();
        String query_getAll = "SELECT * FROM thuCung";
        Cursor cursor = sqLiteDB.getWritableDatabase().rawQuery(query_getAll, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String tenThucung = cursor.getString(1);
                String dacDiemThuCung = cursor.getString(3);
                String theLoaiThuCung = cursor.getString(4);
                String anhThuCung = cursor.getString(5);
                String moTaThuCung = cursor.getString(6);
                ThuCung thuCung = new ThuCung();
                thuCung.setTenThuCung(tenThucung);
                thuCung.setDacDiemThuCung(dacDiemThuCung);
                thuCung.setLoaiThuCung(theLoaiThuCung);
                thuCung.setImageThuCung(Integer.parseInt(anhThuCung));
                thuCung.setKhac(moTaThuCung);
                thuCungList.add(thuCung);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return thuCungList;
    }
    public void deletepets(String idThuCung){
        SQLiteDatabase sqLiteDatabase = sqLiteDB.getWritableDatabase();
        sqLiteDatabase.delete("thuCung", "thuCungID=?", new String[]{idThuCung});
    }
}
