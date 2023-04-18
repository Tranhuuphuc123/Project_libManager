CREATE DATABASE LibraryManagement CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use LibraryManagement;

-- =========================================== 
 CREATE TABLE BookShelf(
  BoShelfID varchar(8) not null primary key,
  BoShelfTitle varchar(80) not null,
  IsDeleted int default 0
);

INSERT INTO BookShelf(BoShelfID, BoShelfTitle) VALUES
('BOSH01','A100'),
('BOSH02','A200'),
('BOSH03','A300'),
('BOSH04','A400'),
('BOSH05','A500'),
('BOSH06','A600'),
('BOSH07','A700'),
('BOSH08','A800'),
('BOSH09','A900'),
('BOSH10','B100'),('BOSH11','B200'),('BOSH12','B300'),('BOSH13','B400'),
('BOSH14','B500'),('BOSH15','B600'),('BOSH16','B700'),('BOSH17','B800'),('BOSH18','B900'),
('BOSH19','C100'),('BOSH20','C200'),('BOSH21','C300'),('BOSH22','C400'),('BOSH23','C500'),
('BOSH24','C600'),('BOSH25','C700'),('BOSH26','C800'),('BOSH27','C800'),('BOSH28','C900'),
('BOSH29','D100'),('BOSH30','D200'),('BOSH31','D300'),('BOSH32','D400'),('BOSH33','D500'),
('BOSH34','D600'),('BOSH35','D700'),('BOSH36','D800'),('BOSH37','D900'),('BOSH38','E100'),
('BOSH39','E200'),('BOSH40','E300'),('BOSH41','E400'),('BOSH42','E500'),('BOSH43','E600'),
('BOSH44','E700'),('BOSH45','E800'),('BOSH46','E900');
select * from Bookshelf;

-- ==========================================  
CREATE TABLE CatalogOfBooks(
   CatalogID varchar(8) not null,
   CatalogTitle varchar(80) not null,
   BoShelfID varchar(8) not null,
   IsDeleted int default 0,
   primary key(CatalogID, BoShelfID)
);
ALTER TABLE catalogofbooks ADD CONSTRAINT FKCatalog FOREIGN KEY (BoShelfID) REFERENCES BookShelf(BoShelfID);
SELECT * FROM catalogofbooks;
INSERT INTO CatalogOfBooks(CatalogID, CatalogTitle, BoShelfID) VALUES
('CA01','Information Technology','BOSH01'),
('CA01','Information Technology','BOSH02'),
('CA01','Information Technology','BOSH03'),
('CA01','Information Technology','BOSH04'),
('CA01','Information Technology','BOSH05'),
('CA02','Computer Science','BOSH06'),
('CA02','Computer Science','BOSH07'),
('CA02','Computer Science','BOSH08'),
('CA02','Computer Science','BOSH09'),
('CA03','History','BOSH10'),
('CA03','History','BOSH11'),
('CA04','Psychology','BOSH12'),
('CA04','Psychology','BOSH13'),
('CA05','Information','BOSH14'),
('CA05','Information','BOSH15'),
('CA05','Information','BOSH16'),
('CA05','Information','BOSH17'),
('CA05','Information','BOSH18'),
('CA06','Language','BOSH19'),
('CA06','Language','BOSH20'),
('CA06','Language','BOSH21'),
('CA06','Language','BOSH22'),
('CA06','Language','BOSH23'),
('CA07','Agriculture','BOSH24'),
('CA07','Agriculture','BOSH25'),
('CA07','Agriculture','BOSH26'),
('CA07','Agriculture','BOSH27'),
('CA08','Software Technical','BOSH28'),
('CA08','Software Technical','BOSH29'),
('CA08','Software Technical','BOSH30'),
('CA08','Software Technical','BOSH31'),
('CA09','Physics','BOSH32'),
('CA09','Physics','BOSH33'),
('CA09','Physics','BOSH34'),
('CA10','Literature','BOSH35'),
('CA10','Literature','BOSH36'),
('CA10','Literature','BOSH37'),
('CA10','Literature','BOSH38'),
('CA11','Philosophy and Life','BOSH39'),
('CA11','Philosophy and Life','BOSH40'),
('CA11','Philosophy and Life','BOSH41');

-- ==========================================  
CREATE TABLE Author(
    AuthorID varchar(8) not null primary key,
    AuthorName varchar(50) not null,
    AuthorSubject varchar(80) not null,
    Description varchar(150),
    IsDeleted int default 0
 );
 
 INSERT INTO Author(AuthorID, AuthorName, AuthorSubject,Description) VALUES
 ('ATH01', 'The Lu', 'Literture',null),
 ('ATH02', 'Dave Thomas', 'Computer Science',null),
 ('ATH03', 'Andrew Hunt ', 'Information Technology',null),
 ('ATH04', 'Robert Cecil Martin', 'Information Technology',null),
 ('ATH05', 'Steven C. McConnell ', 'Software Technical',null),
 ('ATH06', 'Nam Cao', 'Literature',null),
 ('ATH07', 'To Hoai', 'Literature',null),
 ('ATH08', 'Napoleon Hill', 'Philosophy and Life',null);
 
 
-- ==========================================  
CREATE TABLE Publisher(
   PublisherID varchar(8) not null primary key,
   PublisherName varchar(80) not null,
   PublisherPhone varchar(20) ,
   PublisherAddress varchar(150) not null,
   IsDeleted int default 0
);
select * from Publisher;

INSERT INTO Publisher VALUES
('PL01','Addison-Wesley','','Boston, USA',0),
('PL02','Prentice Hall','330-672-7000','1425 Petrarca Drive Kent, Ohio 44242',0),
('PL03','American Society for Quality','425-638-7777','Milwaukee, Wisconsin, USA',0),
('PL04','Random House','','Worldwide',0),
('PL05','Rob Miles','','England, UK',0),
('PL06','NXB Kim Dong','093 287 07 40','Đường Nguyễn Văn Thảnh, Khóm Thành Quới, Bình Tân, Vĩnh Long',0),
('PL07','NXB Tong Hop TP HCM ','(028) 38 256 804','62 Nguyễn Thị Minh Khai, Phường Đa Kao, Quận 1, TP.HCM',0);

-- ==========================================  
create table Books(
   BookID varchar(8) not null primary key,
   CatalogID varchar(8) not null,
   AuthorID varchar(8) not null,
   PublisherID varchar(8) not null,
   BookTitle varchar(100) not null,
   BookPrice varchar(25) not null,
   BookAmount int not null,
   BookBarCode varchar(12) unique not null,
   BookImg varchar(500)
);
alter table Books add constraint FKB01 foreign key(AuthorID) references Author(AuthorID);
alter table Books add constraint FKB02 foreign key(CatalogID) references CatalogOfBooks(CatalogID);
alter table Books add constraint FKB03 foreign key(PublisherID) references Publisher(PublisherID);
select * from Books;
 

-- table View support Books and ManagesBook
create view Book_view
as
select b.BookID, CatalogID,AuthorID,PublisherID,BookTitle,BookPrice,BookAmount,BookBarCode,BookImg,EmpID,DateAdded,Activity
from Books b join ManagesBook mb on b.BookID = mb.BookID;

select * from  Book_view;

-- make procedure to insert data on View Table_view
DELIMITER $$
CREATE PROCEDURE insert_Book_view(
IN book_id VARCHAR(8), 
IN catalog_id VARCHAR(8), 
IN author_id VARCHAR(8), 
IN publisher_id VARCHAR(8), 
IN title VARCHAR(100),
IN price VARCHAR(25), 
IN amount INT, 
IN barcode VARCHAR(12), 
IN image VARCHAR(500), 
IN emp_id VARCHAR(8), 
IN added_date DATE, 
IN activity VARCHAR(25))
BEGIN
  INSERT INTO Books(BookID, CatalogID, AuthorID, PublisherID, BookTitle, BookPrice, BookAmount,BookBarCode,BookImg)
         VALUES(book_id, catalog_id, author_id, publisher_id, title, price, amount,barcode,image);
  INSERT INTO ManagesBook(EmpID,BookID,DateAdded,Activity)
		 VALUES(emp_id,book_id, added_date, activity);
END $$
DELIMITER ;



-- make procedure update
DELIMITER $$
CREATE PROCEDURE update_Book_view(
IN book_id VARCHAR(8), 
IN catalog_id VARCHAR(8), 
IN author_id VARCHAR(8), 
IN publisher_id VARCHAR(8), 
IN title VARCHAR(100),
IN price VARCHAR(25), 
IN amount INT, 
IN barcode VARCHAR(12), 
IN image VARCHAR(500), 
IN emp_id VARCHAR(8), 
IN added_date DATE, 
IN activity VARCHAR(25))
BEGIN
  UPDATE Books
  SET CatalogID = catalog_id,
      AuthorID = author_id,
      PublisherID = publisher_id,
      BookTitle = title,
      BookPrice = price,
      BookAmount = amount,
      BookBarCode = barcode,
      BookImg = image
  WHERE BookID = book_id;

  UPDATE ManagesBook
  SET DateAdded = added_date,
      Activity = activity
  WHERE BookID = book_id;
END $$
DELIMITER ;


-- insert data insert_Book_view(procedure)
call insert_Book_view('B025','CA10','ATH07','PL02','Harry Poster hau truyen','200.000VND',20,'MOL024','0','EP02','2023-04-06','Availble');
-- update data update_Book_view (procedure)
call update_Book_view('B025','CA10','ATH07','PL02','Harry Poster hau truyen','200.000VND',
11,'MOL024','..','EP02','2023-04-06','Availble');


SELECT * FROM Books;
INSERT INTO Books VALUES
('B001', 'CA01','ATH03','PL01','The Pragmatic Programmer','114.000VND',10,'MOL.001',0),
('B002', 'CA02','ATH03','PL01','Agile Web Development with Rails: A Pragmatic Guide','80.000VND',10,'MOL.002',0),
('B003', 'CA02','ATH03','PL01','Programming Elixir: Functional |> Concurrent |> Pragmatic |> Fun','114.000VND',10,'MOL.003',0),
('B004', 'CA02','ATH03','PL01','Programming Rust: Fast, Safe Systems Development','79.000VND',10,'MOL.004',0),
('B005', 'CA02','ATH03','PL01','The Pragmatic Programmer, 20th Anniversary Edition','69.000VND',10,'MOL.005',0),
('B006', 'CA08','ATH04','PL01','Clean Code: A Handbook of Agile Software Craftsmanship','108.000VND',10,'MOL.006',0),
('B007', 'CA08','ATH04','PL01','Agile Software Development, Principles, Patterns, and Practices','74.000VND',10,'MOL.007',0),
('B008', 'CA02','ATH03','PL01',"Programming Ruby: The Pragmatic Programmer's Guide",'114.000VND',10,'MOL.008',0),
('B009', 'CA08','ATH04','PL01',"Clean Architecture: A Craftsman's Guide to Software Structure and Design",'99.000VND',10,'MOL.009',0),
('B010', 'CA08','ATH04','PL01','The Clean Coder: A Code of Conduct for Professional Programmers','125.500VND',10,'MOL.010',0),
('B014', 'CA08','ATH04','PL02','Designing Object-Oriented C++ Applications Using the Booch Method','125.500VND',10,'MOL.011',0),
('B011', 'CA08','ATH04','PL02','Agile Principles, Patterns, and Practices in C#','128.00VND',10,'MOL.012',0),
('B012', 'CA08','ATH04','PL02','Agile Software Development: Principles, Patterns, and Practices','67.500VND',10,'MOL.013',0),
('B013', 'CA08','ATH04','PL02','Clean Code: A Handbook of Agile Software Craftsmanship','115.000VND',10,'MOL.014',0),
('B024', 'CA10','ATH06','PL02','Chi Pheo (tieu thuyet)','55.000VND',10,'MOL.015',0),
('B015', 'CA10','ATH06','PL02','Doi Thua','67.000VND',10,'MOL.016',0),
('B016', 'CA10','ATH07','PL02','De Men Phieu Luu Ky ','88.000VND',10,'MOL.017',0),
('B017', 'CA10','ATH06','PL02','Song Mon','46.000VND',10,'MOL.018',0),
('B018', 'CA10','ATH06','PL02','Mot Bua No','46.000VND',10,'MOL.019',0),
('B019', 'CA10','ATH07','PL02','Vo Chong A Phu','48.000VND',10,'MOL.020',0),
('B020', 'CA10','ATH07','PL02','Ky Uc Dong Duong','49.000VND',10,'MOL.021',0),
('B021', 'CA11','ATH08','PL07','Nghi Giau va Lam Giau','72.000VND',10,'MOL.022',0),
('B022', 'CA11','ATH08','PL07','Duong Den Thanh Cong (Road To Success)','87.000VND',10,'MOL.023',0),
('B023', 'CA11','ATH08','PL07','De The Gioi Biet Ban La Ai','79.000VND',10,'MOL.024',0);
 

-- ============= DEPARTMENT TABLE ============================== 
CREATE TABLE Department(
	DepID varchar(8) primary key not null,
    DepName varchar(80) not null,
    DepStatus varchar(25) not null
);

insert into Department
values('DP01','Board Of Manager','Active'),
      ('DP02','Accounting Department','Active'),
      ('DP03','Information Service','Active'),
      ('DP04','Technical Department','Active'),
      ('DP05','Security Team','Active'),
      ('DP06','Digital Conversion','Pending');

-- ============================================
create table Employee(
   EmpID varchar(8) not null primary key,
   EmpName varchar(50) not null,
   EmpPhone varchar(20) not null, 
   DateOfBirth date not null,
   EmpGender varchar(6) not null,
   EmpAddress varchar(150) not null,
   DepID varchar(8) not null,
   EmpPosition varchar(25) not null,
   EmpSalary varchar(15) not null,
   EmpPass varchar(20) unique not null,
   EmpBarCode varchar(12) unique not null,
   EmpImg varchar(500),
   EmpStatus varchar(25) not null
);
-- update EmpPass varchar(20) -> varchar(255);
alter table Employee modify EmpPass varchar(255) unique not null;
SELECT * FROM Employee;

INSERT INTO Employee VALUES
('EP01','Trần Lê Yến Ngọc','0962428155','1980-09-12','Female','3/2, Hưng Lợi, Ninh Kiều, Cần Thơ', 
								'DP02','Chief Accountant','10.000.000','Yenngoc123@','BA001','..','Active'),
                                
('EP02','Trần Thị Bảo Duy','096242859','1995-09-23','Female','30/4, Xuân Khánh, Ninh Kiều, Cần Thơ', 
								'DP03','Librarian','8.000.000','Baoduy123@','BA002','..','Active'), -- Librarian
                                
('EP03','Lý Hoàng Nam','096242555','1993-10-23','Male','30/4, Xuân Khánh, Ninh Kiều, Cần Thơ', 
								'DP04','Network Administrator','10.000.000','Hoangnam123@','BA003','..','Active'),  
                                
('EP04','Dương Xuân Việt','0939052119','1985-10-29','Male','Nguyễn Trãi, An khánh, Ninh Kiều, Cần Thơ', 
								'DP01','Managing Director','20.000.000','Admin11@','BA004','..','Active'),  -- Director
                                
('EP05','Trần Hoàng Nam','096245759','1995-09-23','Male','30/4, Xuân Khánh, Ninh Kiều, Cần Thơ', 
								'DP03','Librarian','8.000.000','12345@','BA005','..','Active'), -- Librarian
                                
('EP06','Lê Tô Hà','096236859','1995-09-23','male','30/4, Xuân Khánh, Ninh Kiều, Cần Thơ', 
								'DP03','Librarian','8.000.000','12346@','BA006','..','Active'),  -- Librarian
                                
('EP07','Lý Nhiên','096862859','1995-09-23','Male','30/4, Xuân Khánh, Ninh Kiều, Cần Thơ', 
								'DP03','Librarian','8.000.000','12347@','BA007','..','Trainning');  -- Librarian
 

 -- ==========================================                               
 create table ManagesBook(
    EmpID varchar(8) not null,
    BookID varchar(8) not null,
    DateAdded date not null,
    Activity varchar(25) not null,
    primary key(BookID, EmpID)
 );
 

ALTER TABLE ManagesBook ADD CONSTRAINT FKM01 FOREIGN KEY(EmpID) REFERENCES Employee(EmpID);
ALTER TABLE ManagesBook ADD CONSTRAINT FKM02 FOREIGN KEY(BookID) REFERENCES Books(BookID);
select * from ManagesBook;

insert into  ManagesBook
values('EP02','B001','2023-04-01','Available'),
      ('EP02','B002','2023-03-22', 'Available'),
      ('EP02','B003','2023-03-22', 'Available'),
      ('EP02','B004','2023-03-22', 'Available'),
      ('EP02','B005','2023-03-22', 'Available'),
      ('EP05','B006','2023-03-22', 'Withdrawn'),
      ('EP05','B007','2023-03-22', 'Available'),
      ('EP05','B008','2023-03-22', 'Available'),
      ('EP05','B009','2023-03-22', 'Available'),
      ('EP06','B010','2023-03-22', 'Lost'),
      ('EP07','B011','2023-03-22', 'Available'),
      ('EP06','B012','2023-03-22', 'Available'),
      ('EP07','B013','2023-03-22', 'Available'),
      ('EP02','B014','2023-03-22', 'Available'),
      ('EP01','B015','2023-04-01','Borrowed'),
      ('EP07','B016','2023-03-23', 'Lost'),
      ('EP07','B017','2023-03-25', 'Lost'),
      ('EP06','B018','2023-03-22', 'Lost'),
      ('EP07','B019','2023-03-22', 'Damaged'),
      ('EP05','B020','2023-04-01', 'Damaged');

-- insert ManagesBook
insert into ManagesBook
values('EP03','B021','2023-04-07','Lost'),
      ('EP05','B022','2023-04-07','Available'),
      ('EP03','B023','2023-04-07','Available');

  -- =============== READER TABLE ===========================   
CREATE TABLE Reader(
   ReaderID varchar(8) not null primary key,
   ReaderName varchar(50) not null,
   DateOfBirth date not null,
   ReaderGender varchar(6) not null,
   ReaderAddress varchar(150) not null,
   ReaderPhone varchar(20) not null,
   ReaderBarCode varchar(12) unique not null,
   DateCreated date not null,
   OutOfDate date not null,
   ReaderPass varchar(20) unique not null,
   ReaderImage varchar(500)
 );
-- add field ReaderStatus
ALTER TABLE Reader MODIFY COLUMN ReaderPass VARCHAR(255) UNIQUE NOT NULL;

alter table Reader  add ReaderStatus varchar(100);
SELECT * FROM Reader where ReaderStatus = 'Active';
update Reader set ReaderImage = ".." where ReaderID = 'Re01'; 

delete from Reader where ReaderID ='Re09';

INSERT INTO Reader VALUES
('Re01','Nguyễn Hồng Như','2002-02-01','Female','Nguyen Trai Noi Dai, Le Binh, Cai Rang, Can Tho','0988906789','BAR001',
                                            '2023-02-22','2023-08-22','Hongnhu11@','..'),
('Re02','Nguyễn Lê Bình','1995-02-25','Female','Nguyen Trai Noi Dai, Le Binh, Cai Rang, Can Tho','0962428167','BAR002',
                                            '2023-02-22','2023-08-22','Lebinh11@','..'),
('Re03','Lý Hoàng Nam','1990-02-15','Male','Nguyen Trai Noi Dai, Le Binh, Cai Rang, Can Tho','0988906789','BAR003',
                                            '2023-02-22','2023-08-22','HoangNnam11@','..'),
('Re04','Nguyễn Nhã Linh Đang','1998-05-21','Female','Nguyen Trai Noi Dai, Le Binh, Cai Rang, Can Tho','0967906789','BAR005',
                                            '2023-02-22','2023-08-22','1234@','..'),
('Re05','Trần Cao Ngọc Ly','1997-03-18','Female','Nguyen Trai Noi Dai, Le Binh, Cai Rang, Can Tho','0982506789','BAR004',
                                            '2023-02-22','2023-08-22','1345@','..');

  
-- ==========================================   
CREATE TABLE ManagesReader(
	EmpID varchar(8) not null,
	ReaderID varchar(8) not null,
    Activity varchar(25) not null,
    primary key(ReaderID, EmpID)
);
 ALTER TABLE ManagesReader ADD CONSTRAINT FKR01 FOREIGN KEY(ReaderID) REFERENCES Reader(ReaderID);
 ALTER TABLE ManagesReader ADD CONSTRAINT FKR02 FOREIGN KEY(EmpID) REFERENCES Employee(EmpID);

INSERT INTO ManagesReader VALUES
('EP02','Re01','Active'),  
('EP02','Re02','Active'),
('EP02','Re03','Active'),
('EP05','Re04','Expired'),
('EP06','Re05','Banned');

-- ==========================================  
create table BorrowedAndReturnedBook(
    BookID varchar(8) not null,
    ReaderID varchar(8) not null,
    BorrStartDate date not null,
    RetDate date not null,
    BorrStatus varchar(25) not null,
    primary key(BookID, ReaderID)
 );
ALTER TABLE BorrowedAndReturnedBook ADD CONSTRAINT FKBR01 FOREIGN KEY(BookID) REFERENCES Books(BookID);
ALTER TABLE BorrowedAndReturnedBook ADD CONSTRAINT FKBR02 FOREIGN KEY(ReaderID) REFERENCES Reader(ReaderID);
select * from BorrowedAndReturnedBook;

INSERT INTO BorrowedAndReturnedBook VALUES
('B001','Re01', '2023-02-22','2023-02-12','Returned'),
('B002','Re01', '2023-02-22','2023-02-12','Borrowed'),
('B003','Re01', '2023-02-22','2023-02-12','Borrowed'),
('B004','Re03', '2023-03-03','2023-03-13','Borrowed'),
('B005','Re02', '2023-03-25','2023-04-05','Borrowed'),
('B006','Re02', '2023-03-25','2023-04-05','Borrowed'),
('B007','Re02', '2023-03-25','2023-04-05','Returned'),
('B008','Re01', '2023-04-12','2023-04-22','Returned'),
('B009','Re01', '2023-04-12','2023-04-22','Returned'),
('B012','Re06', '2023-02-02','2023-02-12','Returned'),
('B020','Re06', '2023-02-02','2023-02-12','Returned'),
('B001','Re06', '2023-03-02','2023-03-12','Borrowed'),
('B010','Re06', '2023-03-02','2023-03-12','Borrowed'),
('B015','Re06', '2023-03-02','2023-03-12','Borrowed');

-- ==========================================  
CREATE TABLE DiscussionRoom(
  DisID varchar(8) not null primary key,
  DisName varchar(80) not null,
  DisLocation varchar(50) not null,
  DisStatus varchar(25) not null,
  Description varchar(250) not null
);
select * from DiscussionRoom;

INSERT INTO DiscussionRoom VALUES
('ROOM01', 'Discussion Room A', '1st floor', 'Active',''),
('ROOM02', 'Discussion Room B', '1st floor', 'Active',''),
('ROOM03', 'Discussion Room C', '1st floor', 'Active',''),
('ROOM04', 'Discussion Room D', '2st floor', 'Active',''),
('ROOM05', 'Discussion Room E', '2st floor', 'Active',''),
('ROOM06', 'Discussion Room F', '2st floor', 'Active',''),
('ROOM07', 'Discussion Room G', '3st floor', 'Active',''),
('ROOM08', 'Discussion Room H', '3st floor', 'Active',''),
('ROOM09', 'Discussion Room I', '1st floor', 'Closed',''),
('ROOM010', 'Discussion Room II', '1st floor', 'Restricted',''),
('ROOM011', 'Discussion Room III', '1st floor', 'Pending','');

-- RetristerBorrowed
CREATE TABLE RetristersBorrowed(
  DisID varchar(8) not null, 
  ReaderID varchar(8) not null,
  BorrowedByShift varchar(150) not null,
  DisDate date not null,
  SendingTime timestamp default current_timestamp() not null,
  Description varchar(250) not null,
  Activity varchar(20) not null,
  primary key(DisID, ReaderID)
);

ALTER TABLE RetristersBorrowed ADD CONSTRAINT FKRB02 FOREIGN KEY(DisID) REFERENCES DiscussionRoom(DisID);
ALTER TABLE RetristersBorrowed ADD CONSTRAINT FKRB01 FOREIGN KEY(ReaderID) REFERENCES Reader(ReaderID);
select * from RetristersBorrowed;
-- insert values RetristerBorrowed
INSERT INTO RetristersBorrowed (DisID, ReaderID, BorrowedByShift, DisDate, Description, Activity) VALUES
('ROOM02', 'Re02', 'Shift 1: 7:30AM - 9:30AM','2023-04-10', 'Borrowed room for studying','Pending'),
('ROOM03', 'Re03', 'Shift 2: 9:40AM - 11:40AM','2023-04-10','Borrowed room for studying','Pending'),
('ROOM04', 'Re04', 'Shift 3: 11:50AM - 13h:50PM','2023-04-10','Borrowed room for studying','Cancelled'),
('ROOM01', 'Re01', 'Shift 4: 14:00PM - 16:00PM','2023-04-10', 'Borrowed room for studying','Processed');


CREATE TABLE FeedBack(
	FeedID int primary key auto_increment not null,
    TypeOfFeed varchar(20) not null,
    ContentOfFeed varchar(200) not null,
    ContentDetaildOfFeed varchar(500) not null,
    SendingTime timestamp default current_timestamp() not null,
    FeedStatus varchar(25) not null default 'Pending'
);

ALTER TABLE FeedBack MODIFY FeedStatus varchar(25) default 'Pending' not null;
INSERT INTO FeedBack (TypeOfFeed, ContentOfFeed, ContentDetaildOfFeed, FeedStatus) VALUES 
('Complaint', 'The book is damaged', 'Page 54 is ripped off', 'Pending'),
('Suggestion', 'Add book', 'Please add some books by To Hoai', 'Processed'),
('Problem', 'Faulty water vending machine', 'It is not possible to buy mineral water in the machine.', 'Processed');

INSERT INTO FeedBack (TypeOfFeed, ContentOfFeed, ContentDetaildOfFeed) VALUES 
('Praise', 'Service', 'Good!');

select * from FeedBack;

-- *******************Update Pass mã hóa MP5******************
UPDATE Reader
	SET ReaderPass = SHA2('Hongnhu11@',256)
	WHERE ReaderID = 'Re01';  

	UPDATE Reader
	SET ReaderPass = SHA2('Lebinh11@',256)
	WHERE ReaderID = 'Re02';  

	UPDATE Reader
	SET ReaderPass = SHA2('HoangNnam11@',256)
	WHERE ReaderID = 'Re03'; 

	UPDATE Reader
	SET ReaderPass = SHA2('1234@',256)
	WHERE ReaderID = 'Re04'; 

	UPDATE Reader
	SET ReaderPass = SHA2('1345@',256)
	WHERE ReaderID = 'Re05'; 

	UPDATE Reader
	SET ReaderPass = SHA2('hquan123@',256)
	WHERE ReaderID = 'Re06' AND ReaderPass =  SHA2('hongquan123@', 256);
    
    select * from Reader;
    
UPDATE Employee
SET EmpPass = SHA2('Yenngoc123@',256)
WHERE EmpID = 'EP01';  

UPDATE Employee
SET EmpPass = SHA2('Baoduy123@',256)
WHERE EmpID = 'EP02';   

UPDATE Employee
SET EmpPass = SHA2('Hoangnam123@',256)
WHERE EmpID = 'EP03';   

UPDATE Employee
SET EmpPass = SHA2('Admin11@',256)
WHERE EmpID = 'EP04';   

UPDATE Employee
SET EmpPass = SHA2('12345@',256)
WHERE EmpID = 'EP05';   

UPDATE Employee
SET EmpPass = SHA2('12346@',256)
WHERE EmpID = 'EP06';   

UPDATE Employee
SET EmpPass = SHA2('12346@',256)
WHERE EmpID = 'EP06';   

UPDATE Employee
SET EmpPass = SHA2('12347@',256)
WHERE EmpID = 'EP07'; 

select * from Employee;  
