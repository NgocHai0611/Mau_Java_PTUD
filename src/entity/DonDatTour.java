package entity;

import java.util.Date;

public class DonDatTour {
	private String maHD,maKH;
//	private KhachHang kh;
	private String ngayDatTour;
	private int soLuong;
	
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	
	public String getNgayDatTour() {
		return ngayDatTour;
	}
	public void setNgayDatTour(String ngayDatTour) {
		this.ngayDatTour = ngayDatTour;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public DonDatTour(String maHD, String maKH, String ngayDatTour, int soLuong) {
		super();
		this.maHD = maHD;
		this.maKH = maKH;
		this.ngayDatTour = ngayDatTour;
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "DonDatTour [maHD=" + maHD + ", maKH=" + maKH + ", ngayDatTour=" + ngayDatTour + ", soLuong=" + soLuong
				+ "]";
	}
	
//	@Override
//	public String toString() {
//		return "DonDatTour [maHD=" + maHD + ", maKH=" + maKH + ", kh=" + kh + ", ngayDatTour=" + ngayDatTour
//				+ ", soLuong=" + soLuong + "]";
//	}

	
	
	
}
