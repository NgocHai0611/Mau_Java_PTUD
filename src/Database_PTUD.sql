Create Database DB_PTUD
go 
use DB_PTUD



create table NhaXuatBan(
	maNXB varchar(6) PRIMARY KEY,
	tenNXB varchar(15),
	diaChi varchar(50)

)

create table TheLoai(
	maTheLoai varchar(20)PRIMARY KEY,
	tenTheLoai varchar(15) not null,
)

create table TacGia(
	maTacGia varchar(5)PRIMARY KEY,
	tenTacGia varchar(15) not null,
)

CREATE TABLE KhachHang (
    maKH varchar(5) PRIMARY KEY,
	tenKH varchar(15) not NULL,
	ngaySinh DateTime not NULL,
	diaChi varchar(50) not null,
	sdt varchar(12) NOT NULL,
	uuDai float ,
);

CREATE TABLE NhanVien (
    maNV varchar(5) PRIMARY KEY,
	hoTenNV varchar(50) NOT  NULL,
	ngaySinh DateTime NOT NULL,
	diaChi varchar(50) NOT NULL,
	sdt varchar(12) NOT NULL,
	chucVu varchar(15) NOT NULL,
	gioiTinh bit NOT NULL,
	CCCD int NOT NULL,
	tinhTrang bit NOT NULL,
	lyDoNghiViec varchar(15) ,
);


CREATE TABLE TaiKhoan (
   maNV varchar(5) not null,
   matKhau varchar(6) NOT NULL DEFAULT '1111',
   constraint FK_MaNV Foreign key(maNV) references NhanVien(maNV)
)

GO

alter table TaiKhoan
add primary key (maNV)

go
CREATE TABLE NhaCungCap (
    maNCC varchar(6) PRIMARY KEY,
    tenNCC varchar(15) NOT NULL,
    sdt varchar(12) NOT NULL,
    diaChi varchar(50) NOT NULL,
	tinhTrang bit not null,  
);


CREATE TABLE Sach (
    
    maSach varchar(5) PRIMARY KEY,
    tenSach varchar(50) NOT NULL,
    maNCC varchar(6) NOT NULL,
    donGiaNhap Money NOT NULL,
	soLuong int not null,
	TheLoai varchar(20) not null,
	maNXB varchar(6) not null,
	maTacGia varchar(5) not null,
	hinhAnh varchar(30) not null
	

	 Foreign key(maNCC) references NhaCungCap(maNCC),
	 Foreign key(TheLoai) references TheLoai(maTheLoai),
	 Foreign key(maNXB) references NhaXuatBan(maNXB),
	 Foreign key(maTacGia) references TacGia(maTacGia),
   
);


CREATE TABLE HoaDon (
    ID INT IDENTITY(1,1),
    maHD AS 'HD' + '00' +CAST(ID AS VARCHAR(5)) PERSISTED,
	maNV varchar(5),
	maKH varchar(5) ,
    ngayLap DateTime NOT NULL,
    
	VAT float not null,
	maQuay varchar(3) not null,
	
	uuDai float not null,
	tongTien float not null,

	Primary key(ID),
	FOREIGN KEY(maNV) REFERENCES NhanVien(maNV),
	FOREIGN KEY(maKH) REFERENCES KhachHang(maKH),
);



CREATE TABLE CTHoaDon(
	ID INT,
    maHD AS 'HD' + '00' +CAST(ID AS VARCHAR(5)) PERSISTED,
   maSach varchar(5) NOT NULL ,
   tenSach varchar(50) NOT NULL,
   soLuong INT NOT NULL,
   donGia money not null,
   VAT float not null,
   uuDai float not null,
   thanhTien float not null,
   Foreign key(ID) references HoaDon(ID),
   Foreign key(maSach) references Sach(maSach),
) 




go
create trigger create_account
on NhanVien After insert 
as 
begin
	Declare @maNhanVien nvarchar(6)
	Set @maNhanVien = (SELECT maNV from inserted)
	insert into TaiKhoan(maNV)
	Values (@maNhanVien)
end

--create trigger create_id_orderDetails
--on HoaDon After insert 
--as 
--begin
--	Declare @maHD int
--	Set @maHD = (SELECT ID from inserted)
--	insert into CTHoaDon(ID)
--	Values (@maHD)
--end


insert NhanVien(maNV, hoTenNV , ngaySinh , diaChi , sdt , chucVu , gioiTinh,CCCD , tinhTrang)
values('NV001' , 'Ngoc Hai' , '2023/11/06' , 'HCM' , '096123' , 'NhanVien' , 'true' , '046123' ,'false')


insert KhachHang(maKH , tenKH , ngaySinh , diaChi , sdt , uuDai)
values('KH001' , 'Nhan' , '2003/11/06' , 'HN' , '096123' , 0.3)


Select * from NhanVien
Select * from TaiKhoan
Select * from KhachHang

SELECT * FROM HoaDon
Select * from CTHoaDon


Select * from TheLoai
Select * from TacGia
Select * from NhaCungCap
Select * From NhaXuatBan
Select * from Sach

insert TheLoai(maTheLoai , tenTheLoai)
values ('VanHoc' , 'VanHoc')

insert TacGia(maTacGia , tenTacGia)
values ('TG001' , 'Hai')

insert NhaCungCap(maNCC , tenNCC , sdt , diaChi , tinhTrang)
values ('NCC001' , 'Fahasa' , '096123' , 'Quang Ngai' , 'true')

insert NhaXuatBan(maNXB , tenNXB , diaChi)
values('NXB001' , 'Kim Dong' , '12 Nguyen Van Bao')

Insert Sach(maSach ,tenSach , maNCC , donGiaNhap , soLuong , TheLoai , maNXB ,  maTacGia , hinhAnh)
values('MS001' , 'Doraemon' , 'NCC001' , 300 , 4 , 'VanHoc' , 'NXB001' , 'TG001' , 'hinh1.png'  )

Insert Sach(maSach ,tenSach , maNCC , donGiaNhap , soLuong , TheLoai , maNXB ,  maTacGia , hinhAnh)
values('MS002' , 'Conan' , 'NCC001' , 300 , 4 , 'VanHoc' , 'NXB001' , 'TG001' , 'hinh2.png'  )

Insert HoaDon(maNV, maKH , ngayLap , VAT , maQuay , uuDai , tongTien)
values ('NV001' , 'KH001' , '2023/11/9' , 0.2 , 'Q1' , 0.3 ,400)

Insert HoaDon(maNV, maKH , ngayLap , VAT , maQuay , uuDai , tongTien)
values ('NV001' , 'KH001' , '2023/11/11' , 0.2 , 'Q3' , 0.3 ,400)

select * from HoaDon
Select * from CTHoaDon

Insert CTHoaDon(ID , maSach , tenSach , soLuong , donGia , VAT , uuDai , thanhTien)
values(1 , 'MS001' , 'Doraemon' , 3 ,400 , 0.3 , 0.5 , 500)

Insert CTHoaDon(ID, maSach , tenSach , soLuong , donGia , VAT , uuDai , thanhTien)
values (1,'MS002' , 'Conan' , 3 ,400 , 0.3 , 0.5 , 500)


Insert CTHoaDon(ID, maSach , tenSach , soLuong , donGia , VAT , uuDai , thanhTien)
values (3,'MS002' , 'Conan' , 3 ,400 , 0.3 , 0.5 , 500)