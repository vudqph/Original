package vn.poly.sotaythucung.model;

public class BenhVien {
    private String idBenhVien, tenBenhVien, DiaChiBenhVien;
    private int resouceImages, danhGiaBenhVien;
    private double kinhDo, viDo;

    public BenhVien() {
    }

    public BenhVien(String idBenhVien, String tenBenhVien, String diaChiBenhVien, int resouceImages, int danhGiaBenhVien, double kinhDo, double viDo) {
        this.idBenhVien = idBenhVien;
        this.tenBenhVien = tenBenhVien;
        DiaChiBenhVien = diaChiBenhVien;
        this.resouceImages = resouceImages;
        this.danhGiaBenhVien = danhGiaBenhVien;
        this.kinhDo = kinhDo;
        this.viDo = viDo;
    }

    public String getIdBenhVien() {
        return idBenhVien;
    }

    public void setIdBenhVien(String idBenhVien) {
        this.idBenhVien = idBenhVien;
    }

    public String getTenBenhVien() {
        return tenBenhVien;
    }

    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public String getDiaChiBenhVien() {
        return DiaChiBenhVien;
    }

    public void setDiaChiBenhVien(String diaChiBenhVien) {
        DiaChiBenhVien = diaChiBenhVien;
    }

    public int getResouceImages() {
        return resouceImages;
    }

    public void setResouceImages(int resouceImages) {
        this.resouceImages = resouceImages;
    }

    public int getDanhGiaBenhVien() {
        return danhGiaBenhVien;
    }

    public void setDanhGiaBenhVien(int danhGiaBenhVien) {
        this.danhGiaBenhVien = danhGiaBenhVien;
    }

    public double getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(double kinhDo) {
        this.kinhDo = kinhDo;
    }

    public double getViDo() {
        return viDo;
    }

    public void setViDo(double viDo) {
        this.viDo = viDo;
    }
}
