package vn.poly.sotaythucung.model;

public class ThuCung {
    private String idThuCung, tenThuCung, loaiThuCung, dacDiemThuCung, khac;
    private int imageThuCung;

    public ThuCung(String idThuCung, String tenThuCung, String loaiThuCung, String dacDiemThuCung, String khac, int imageThuCung) {
        this.idThuCung = idThuCung;
        this.tenThuCung = tenThuCung;
        this.loaiThuCung = loaiThuCung;
        this.dacDiemThuCung = dacDiemThuCung;
        this.khac = khac;
        this.imageThuCung = imageThuCung;
    }

    public ThuCung() {
    }

    public String getIdThuCung() {
        return idThuCung;
    }

    public void setIdThuCung(String idThuCung) {
        this.idThuCung = idThuCung;
    }

    public String getTenThuCung() {
        return tenThuCung;
    }

    public void setTenThuCung(String tenThuCung) {
        this.tenThuCung = tenThuCung;
    }

    public String getLoaiThuCung() {
        return loaiThuCung;
    }

    public void setLoaiThuCung(String loaiThuCung) {
        this.loaiThuCung = loaiThuCung;
    }

    public String getDacDiemThuCung() {
        return dacDiemThuCung;
    }

    public void setDacDiemThuCung(String dacDiemThuCung) {
        this.dacDiemThuCung = dacDiemThuCung;
    }

    public String getKhac() {
        return khac;
    }

    public void setKhac(String khac) {
        this.khac = khac;
    }

    public int getImageThuCung() {
        return imageThuCung;
    }

    public void setImageThuCung(int imageThuCung) {
        this.imageThuCung = imageThuCung;
    }
}
