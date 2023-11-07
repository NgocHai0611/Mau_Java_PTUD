package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DonDatTour;
import entity.KhachHang;
import entity.MycombomaKH;

;

public class DonTour_Dao {
	public List<DonDatTour> getAllDonTour(){
		List<DonDatTour> dsDon = new ArrayList<DonDatTour>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			String sql = "Select * from Hoa_Don_Tour";
			Statement stament = con.createStatement();
			ResultSet rs = stament.executeQuery(sql);
			while(rs.next()) {
				dsDon.add( new DonDatTour(
						rs.getString("Ma_Hoa_Don"),
						rs.getString("Ma_Khach_Hang"),
						rs.getString("Ngay_Dat_Tour"),  
						rs.getInt("So_Luong_Nguoi_Trong_Tour")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDon;
	}
	
	
	public void addDonTOur(DonDatTour donTour) {
		Connection con  = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
		stmt = con.prepareStatement("insert into Hoa_Don_Tour values(?,?,?,?)");
			stmt.setString(1, donTour.getMaHD());
			stmt.setString(2, donTour.getMaKH());
//			stmt.setString(3, donTour.getMaNV());
			stmt.setString(3, donTour.getNgayDatTour());
			stmt.setInt(4, donTour.getSoLuong());
			stmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	
	public void xoa(String maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from Hoa_Don_Tour where Ma_Hoa_Don = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
	}


	public void sua(DonDatTour donTour) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update Hoa_Don_Tour " 
				+ "set Ma_Khach_Hang =?, " 
				+ "Ngay_Dat_Tour =?, " 
				+ "So_Luong_Nguoi_Trong_Tour =? " 
				+ "where Ma_Hoa_Don =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, donTour.getMaKH());
//			stmt.setString(2, donTour.getMaNV());
			stmt.setString(2, donTour.getNgayDatTour());
			stmt.setInt(3, donTour.getSoLuong());
			stmt.setString(4, donTour.getMaHD());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	
	public List<MycombomaKH> getmaKH(){
		List<MycombomaKH> dsma = new ArrayList<MycombomaKH>();
		Connection con = ConnectDB.getInstance().getConnection();
	
		String sql = "Select * from KhachHang";
		Statement stament;
		try {
			stament = con.createStatement();
			ResultSet rs = stament.executeQuery(sql);
			while (rs.next()) {
				dsma.add(new MycombomaKH(rs.getString("Ma_Khach_Hang")));
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();

		}
		return dsma;
	}
	
	
	
	public List<MycombomaKH> getmaNV(){
		List<MycombomaKH> dsma = new ArrayList<MycombomaKH>();
		Connection con = ConnectDB.getInstance().getConnection();
	
		String sql = "Select * from NhanVien";
		Statement stament;
		try {
			stament = con.createStatement();
			ResultSet rs = stament.executeQuery(sql);
			while (rs.next()) {
				dsma.add(new MycombomaKH(rs.getString("Ma_Nhan_vien")));
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();

		}
		return dsma;
	}
	private void close(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
	}
	
}
