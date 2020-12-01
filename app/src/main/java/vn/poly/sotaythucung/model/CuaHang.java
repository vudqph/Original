package vn.poly.sotaythucung.model;

public class CuaHang {
    private String idCuaHang, tenCuaHang, dichVuCuaHang, diaChiCuaHang;
    private int anhCuaHang;
    private double kinhDoCuaHang, viDoCuaHang;

    public CuaHang() {
    }


    public CuaHang(String idCuaHang, String tenCuaHang, String dichVuCuaHang, String diaChiCuaHang, int anhCuaHang, double kinhDoCuaHang, double viDoCuaHang) {
        this.idCuaHang = idCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.dichVuCuaHang = dichVuCuaHang;
        this.diaChiCuaHang = diaChiCuaHang;
        this.anhCuaHang = anhCuaHang;
        this.kinhDoCuaHang = kinhDoCuaHang;
        this.viDoCuaHang = viDoCuaHang;
    }

    public String getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(String idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getDichVuCuaHang() {
        return dichVuCuaHang;
    }

    public void setDichVuCuaHang(String dichVuCuaHang) {
        this.dichVuCuaHang = dichVuCuaHang;
    }

    public String getDiaChiCuaHang() {
        return diaChiCuaHang;
    }

    public void setDiaChiCuaHang(String diaChiCuaHang) {
        this.diaChiCuaHang = diaChiCuaHang;
    }

    public int getAnhCuaHang() {
        return anhCuaHang;
    }

    public void setAnhCuaHang(int anhCuaHang) {
        this.anhCuaHang = anhCuaHang;
    }

    public double getKinhDoCuaHang() {
        return kinhDoCuaHang;
    }

    public void setKinhDoCuaHang(double kinhDoCuaHang) {
        this.kinhDoCuaHang = kinhDoCuaHang;
    }

    public double getViDoCuaHang() {
        return viDoCuaHang;
    }

    public void setViDoCuaHang(double viDoCuaHang) {
        this.viDoCuaHang = viDoCuaHang;
    }
}