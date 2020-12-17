package vn.poly.sotaythucung.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDB extends SQLiteOpenHelper {
    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* ----------------------------------------------*/

    private static final String DATABASE_NAME = "sqlite_thucung.sql";
    private static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "";


    /* ----------------------------------------------*/

    public static final String ONE_TABLE_NAME = "thuCung";
    public static final String COLUMN_PET_ID = "thuCungID";
    public static final String COLUMN_PET_NAME = "tenThucung";
    public static final String COLUMN_PET_CHARACTERISTIC = "dacDiemThuCung";
    public static final String COLUMN_PET_CATEGORY = "theLoaiThuCung";
    public static final String COLUMN_PET_IMAGES = "anhThuCung";
    public static final String COLUMN_PET_DECRIP = "moTaThuCung";

    /* ----------------------------------------------*/


    public static final String TWO_TABLE_NAME = "benhVien";
    public static final String COLUMN_HOSPITAL_ID = "benhVienID";
    public static final String COLUMN_HOSPITAL_NAME = "tenBenhVien";
    public static final String COLUMN_HOSPITAL_IMAGES = "anhBenhVien";
    public static final String COLUMN_HOSPITAL_LOCATION = "diaChiBenhVien";
    public static final String COLUMN_HOSPITAL_MAPLONG = "kinhDoBenhVien";
    public static final String COLUMN_HOSPITAL_MAPLAT = "viDoBenhVien";
    public static final String COLUMN_HOSPITAL_RATES = "danhGiaBenhVien";
    public static final String COLUMN_HOSPITAL_SERVICE = "dichVuBenhVien";
    /* ----------------------------------------------*/


    public static final String THREE_TABLE_NAME = "cuaHang";
    public static final String COLUMN_SHOP_ID = "cuaHangID";
    public static final String COLUMN_SHOP_NAME = "tenCuaHang";
    public static final String COLUMN_SHOP_SERVICE = "dichVuCuaHang";
    public static final String COLUMN_SHOP_LOCATION = "diaDiemCuaHang";
    public static final String COLUMN_SHOP_MAPLONG = "kinhDoCuaHang";
    public static final String COLUMN_SHOP_MAPLAT = "vidoCuaHang";
    public static final String COLUMN_SHOP_IMAGES = "anhCuaHang";


    public static final String FOUR_TABLE_NAME = "tinTuc";
    public static final String COLUMN_NEWS_ID = "tinTucID";
    public static final String COLUMN_NEWS_NAME = "tenTinTuc";
    public static final String COLUMN_NEWS_IMAGES = "anhTinTuc";
    public static final String COLUMN_NEWS_URLNEWS = "urlNews";

    private static final String DATABASE_CREATE_PET = "create table " + ONE_TABLE_NAME + "(" +
            COLUMN_PET_ID + " char(10) primary key not null," +
            COLUMN_PET_NAME + " varchar(100) not null," +
            COLUMN_PET_CHARACTERISTIC + " varchar(300)," +
            COLUMN_PET_CATEGORY + " varchar(100)," +
            COLUMN_PET_IMAGES + " integer," +
            COLUMN_PET_DECRIP + " varchar(300))";

    private static final String DATABASE_CREATE_HOPISTAL = "create table " + TWO_TABLE_NAME + "(" +
            COLUMN_HOSPITAL_ID + " char(10) primary key not null," +
            COLUMN_HOSPITAL_NAME + " varchar(100) not null," +
            COLUMN_HOSPITAL_IMAGES + " integer," +
            COLUMN_HOSPITAL_LOCATION + " varChar(200)," +
            COLUMN_HOSPITAL_MAPLONG + " double not null," +
            COLUMN_HOSPITAL_MAPLAT + " double not null," +
            COLUMN_HOSPITAL_RATES + " integer not null," +
            COLUMN_HOSPITAL_SERVICE + " varchar(500))";

    private static final String DATABASE_CREATE_SHOP = "create table " + THREE_TABLE_NAME + "(" +
            COLUMN_SHOP_ID + " char(10) primary key not null," +
            COLUMN_SHOP_NAME + " varchar(100)," +
            COLUMN_SHOP_SERVICE + " varChar(300)," +
            COLUMN_SHOP_MAPLONG + " double not null," +
            COLUMN_SHOP_MAPLAT + " double not null," +
            COLUMN_SHOP_LOCATION + " varchar(250)," +
            COLUMN_SHOP_IMAGES + " integer)";

    private static final String DATABASE_CREATE_NEWS = "create table " + FOUR_TABLE_NAME + "(" +
            COLUMN_NEWS_ID + " char(10) primary key not null," +
            COLUMN_NEWS_NAME + " varchar(200)," +
            COLUMN_NEWS_IMAGES + " integer," +
            COLUMN_NEWS_URLNEWS + " integer)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_PET);
        sqLiteDatabase.execSQL(DATABASE_CREATE_HOPISTAL);
        sqLiteDatabase.execSQL(DATABASE_CREATE_SHOP);
        sqLiteDatabase.execSQL(DATABASE_CREATE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
