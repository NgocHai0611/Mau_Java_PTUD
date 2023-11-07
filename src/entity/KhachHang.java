package entity;

public class KhachHang {
private String maKH,tenKH,diaChi,thanhPho,quocGia,sDT,gioiTinh,soCMND;
private int tuoi;

public String getMaKH() {
	return maKH;
}

public void setMaKH(String maKH) {
	this.maKH = maKH;
}

public String getTenKH() {
	return tenKH;
}

public void setTenKH(String tenKH) {
	this.tenKH = tenKH;
}

public String getDiaChi() {
	return diaChi;
}

public void setDiaChi(String diaChi) {
	this.diaChi = diaChi;
}

public String getThanhPho() {
	return thanhPho;
}

public void setThanhPho(String thanhPho) {
	this.thanhPho = thanhPho;
}

public String getQuocGia() {
	return quocGia;
}

public void setQuocGia(String quocGia) {
	this.quocGia = quocGia;
}

public String getsDT() {
	return sDT;
}

public void setsDT(String sDT) {
	this.sDT = sDT;
}

public String getGioiTinh() {
	return gioiTinh;
}

public void setGioiTinh(String gioiTinh) {
	this.gioiTinh = gioiTinh;
}

public int getTuoi() {
	return tuoi;
}

public void setTuoi(int tuoi) {
	this.tuoi = tuoi;
}

public String getSoCMND() {
	return soCMND;
}

public void setSoCMND(String soCMND) {
	this.soCMND = soCMND;
}

public KhachHang() {
	super();
}

public KhachHang(String maKH, String tenKH, String diaChi, String thanhPho, String quocGia, String sDT, String gioiTinh,
		int tuoi, String soCMND) {
	super();
	this.maKH = maKH;
	this.tenKH = tenKH;
	this.diaChi = diaChi;
	this.thanhPho = thanhPho;
	this.quocGia = quocGia;
	this.sDT = sDT;
	this.gioiTinh = gioiTinh;
	this.tuoi = tuoi;
	this.soCMND = soCMND;
}

@Override
public String toString() {
	return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi + ", thanhPho=" + thanhPho
			+ ", quocGia=" + quocGia + ", sDT=" + sDT + ", gioiTinh=" + gioiTinh + ", tuoi=" + tuoi + ", soCMND="
			+ soCMND + "]";
}

}
