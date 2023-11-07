package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
//	 Khởi tạo biến con với giá trị null chuẩn bị cho việc quản lý kết nối cơ sở dữ liệu trong mã Java.
	public static Connection con=null;
	
//	Tạo 1 đối tượng yêu cầu connect database
	public static ConnectDB instance=new ConnectDB();
	
//	Hàm này dùng để kết nối cơ sở dữ liệu mà  muốn chia sẻ giữa nhiều phần của ứng dụng(tạo 1 thể hiện để kết nối đến csdl)
	public static ConnectDB getInstance() {
		return instance;
	}
	
	
//	Phương thức Connect() trong mã của bạn dùng để thiết lập kết nối đến một cơ sở dữ liệu SQL Server thông qua JDBC 
	public void Connect() throws SQLException{
//		 Đây là một URL kết nối đến SQL Server chạy trên máy localhost, cổng 1433, và cơ sở dữ liệu có tên "my_db_on_tap_ck_swing".
		String url="jdbc:sqlserver://localhost:1433;databasename=my_db_on_tap_ck_swing";
		String user="sa";
		String password="haipro456";
		
//		Dòng này sử dụng lớp DriverManager để thiết lập kết nối đến cơ sở dữ liệu. 
//		Hàm getConnection() của DriverManager được gọi với ba đối số: URL, tên đăng nhập và mật khẩu. 
//		Nó thực hiện việc kết nối đến cơ sở dữ liệu được chỉ định trong URL bằng sử dụng tên đăng nhập và mật khẩu cung cấp.
//		Kết quả sẽ được lưu vào biến con nếu kết nối csdl thất bại biến con = null;
		con=DriverManager.getConnection(url, user, password);
	}
	
//	 kiểm tra xem biến con đã được khởi tạo (không bằng null) hay chưa. 
//	Nếu biến con khác null, điều này ngụ ý rằng đã có một kết nối đến
	public void disconnect() {
		if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
//					Hiển thị ra lỗi cụ thể
					e.printStackTrace();
				}
		
	}
	
	
//	Phương thức getConnection() này  được sử dụng để lấy thể hiện của kết nối cơ sở dữ liệu từ bất kỳ đâu trong mã nguồn 
//	nơi bạn cần sử dụng nó. 
	
//	Nó giúp đảm bảo rằng bạn có thể truy cập cùng một kết nối  , từ nhiều phần của ứng dụng mà không cần tạo nhiều kết nối
	
//	phương thức getConnection() sẽ trả về thể hiện đó để bạn có thể sử dụng nó cho các tương tác với cơ sở dữ liệu.
	public static Connection getConnection() {
		return con;
	}

}
