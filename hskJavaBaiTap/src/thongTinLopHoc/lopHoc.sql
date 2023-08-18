create database qlsv1
go

use qlsv
go

create table LopHoc(
	maLop varchar(30) primary key,
	tenLop nvarchar(100) unique not null,
	giaoVienCN nvarchar(100)
)
go

create table SinhVien(
	maSV varchar(20) primary key,
	hoTen nvarchar(100) not null,
	diaChi nvarchar(100),
	email varchar(100),
	maLop varchar(30) foreign key references LopHoc(maLop)
)
go

use qlsv
go
select *from LopHoc

