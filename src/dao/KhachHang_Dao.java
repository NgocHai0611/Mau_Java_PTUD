package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;


public class KhachHang_Dao {
	public List<KhachHang> getAllKhachHang(){
//	Tạo	một danh sách dsKhachHang được để lưu trữ thông tin của khách hàng từ cơ sở dữ liệu.
		List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		
//		Tạo một kết nối cơ sở dữ liệu bằng cách gọi phương thức getInstance() của lớp ConnectDB 
//		và sau đó gọi getConnection(). Điều này thiết lập kết nối với cơ sở dữ liệu. 
//		Kết nối này sẽ được sử dụng để thực hiện truy vấn SQL.		
		Connection con = ConnectDB.getInstance().getConnection();
		
		try {
//		 Định nghĩa một chuỗi SQL chứa truy vấn để lấy tất cả các cột từ bảng "KhachHang".
			String sql = "Select * from KhachHang";

 
/*
 * Tạo một đối tượng Statementlà một đối tượng để thực hiện các truy vấn SQL. 
Đối tượng này được tạo bằng cách gọi phương thức createStatement() trên đối tượng kết nối con.
Phương thức createStatement() là một phương thức của đối tượng ConnectionĐược sử dụng để tạo một đối tượng Statement,
là một đối tượng được sử dụng để thực hiện các truy vấn SQL đến cơ sở dữ liệu.
 */
			Statement stament = con.createStatement();
			


/**Thực hiện truy vấn SQL bằng cách gọi phương thức executeQuery(sql) trên đối tượng Statement với câu lệnh SQL đã xác định
Kết quả trả về từ truy vấn là một đối tượng ResultSet (rs) chứa các bản ghi từ bảng "KhachHang".
			
Đối tượng rs trong đoạn mã Java đó được sử dụng để lưu trữ kết quả của truy vấn SQL được thực hiện đến cơ sở dữ liệu. 
rs là một đối tượng của lớp ResultSet
 * 
 */
			ResultSet rs = stament.executeQuery(sql);

/*Sử dụng vòng lặp while để duyệt qua từng bản ghi trong ResultSet rs. 
 * Mỗi lần lặp, thông tin của một khách hàng được trích xuất từ rs 
 * Bằng cách sử dụng các phương thức rs.getString() và rs.getInt() để lấy giá trị từ các cột tương ứng. 
 * Sau đó, đối tượng KhachHang mới được tạo và thêm vào danh sách dsKhachHang với thông tin từ bản ghi hiện tại.
 * 			
 */
			while(rs.next()) {
				dsKhachHang.add( new KhachHang(
						rs.getString("Ma_Khach_Hang"),
						rs.getString("Ten_Khach_Hang"),
						rs.getString("DiaChi"), 
						rs.getString("ThanhPho"),
						rs.getString("QuocGia"), 
						rs.getString("SoDienThoai"),
						rs.getString("GioiTinh"),
						rs.getInt("Tuoi"), 
						rs.getString("SoCCCD")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		Trả về danh sách khách hàng sau khi lấy từ database
		return dsKhachHang;
	}
	
//	Phương thức này dùng để thêm 1 khách hàng vào csdl
	public void addKhachHang(KhachHang kh) {
//		Tạo 1 đối tượng con để kết nối đến csdl getIns dùng để tạo yêu cầu và getConnect Dùng để lấy kết nối CSDL
		Connection con  = ConnectDB.getInstance().getConnection();
		
/*Tạo 1 đối tượng PrepareStatement là một loại đối tượng được sử dụng để thực hiện truy vấn SQL đã được chuẩn bị trước 
 * với các tham số.
 * 
 * Trong trường hợp này, câu lệnh SQL INSERT đã được chuẩn bị trước với các tham số, và stmt là đối tượng để thực hiện truy vấn này.		
 */
		PreparedStatement stmt = null;
		try {
			
//Dòng lệnh dưới sẽ Chuẩn bị câu lệnh SQL INSERT với các tham số, mỗi tham số tương ứng với một cột trong bảng "KhachHang." 
//Câu lệnh này đã được chuẩn bị trước và sẽ được sử dụng để thêm một bản ghi mới vào bảng.
			stmt = con.prepareStatement("insert into KhachHang values(?,?,?,?,?,?,?,?,?)");
			
//Đặt giá trị của tham số 1 trong câu lệnh SQL INSERT bằng giá trị từ thuộc tính kh.getMaKH() của đối tượng KhachHang.
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(3, kh.getDiaChi());
			stmt.setString(4, kh.getThanhPho());
			stmt.setString(5, kh.getQuocGia());
			stmt.setString(6, kh.getsDT());
			stmt.setString(7, kh.getGioiTinh());
			stmt.setInt(8, kh.getTuoi());
			stmt.setString(9, kh.getSoCMND());
/**
 * Thực hiện câu lệnh SQL INSERT đã được chuẩn bị bằng cách gọi executeUpdate(). 
 * Điều này thêm một bản ghi mới vào bảng "KhachHang" với thông tin từ đối tượng KhachHang kh.
 */
			stmt.executeUpdate();
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}finally {
/*
 *  phương thức close(stmt) được gọi để đảm bảo đối tượng PreparedStatement stmt được đóng sau khi sử dụng, 
 *  để giải phóng tài nguyên và tránh rò rỉ tài nguyên.
 */
			close(stmt);
		}
	}
	
	
	
	public void xoa(String maKH) {
//		Tạo 1 kết nối csdl
		Connection con = ConnectDB.getInstance().getConnection();
//		Tạo 1 đối tượng để thực hiện chuẩn bị thực hiện câu lệnh
		PreparedStatement stmt = null;
		String sql = "delete from KhachHang where Ma_Khach_Hang = ?";

		try {
//			Chuẩn bị câu lệnh sql
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maKH);
			
//			Thực hiện câu lệnh sql
			stmt.executeUpdate();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
	}


	public void sua(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update KhachHang " 
				+ "set Ten_Khach_Hang =?, " 
				+ "DiaChi =?, " 
				+ "ThanhPho =?, " 
				+ "QuocGia =?, " 
				+ "SoDienThoai =?, " 
				+ "GioiTinh =?, " 
				+ "Tuoi =?, " 
				+ "SoCCCD =? " 
				+ "where Ma_Khach_Hang =?";
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getDiaChi());
			stmt.setString(3, kh.getThanhPho());
			stmt.setString(4, kh.getQuocGia());
			stmt.setString(5, kh.getsDT());
			stmt.setString(6, kh.getGioiTinh());
			stmt.setInt(7, kh.getTuoi());
			stmt.setString(8, kh.getSoCMND());
			stmt.setString(9, kh.getMaKH());
//			Thuc Hien Update
			stmt.executeUpdate();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
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
