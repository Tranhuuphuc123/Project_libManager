package controller;

import com.jfoenix.controls.JFXBadge;
import data.AdminService;
import data.JDBCutil;

import javafx.event.Event;
import javafx.geometry.Insets;
import model.*;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.DepartmentPage;
import view.EmployeePage;


//import static sun.security.util.KnownOIDs.Data;

//==========================================Create Object=====================================================================

public class AdminController implements Initializable {

    // anchopane tong
    @FXML
    private AnchorPane Pant_Main;

    //subPane_Header tên cu giao diện khi ấn plus (+) sẽ chuyển qua
    @FXML
    private AnchorPane subPane_Header;

    // khởi tạo tên id cho smenu nguon
    @FXML
    private AnchorPane subMenu_Option;

    // khởi tạo  tên id cho sub pane HomePage,adminPage
    @FXML
    private AnchorPane Pane_Home;
    @FXML
    private JFXButton btn_EmpPage,btn_Depart;


    // khởi tạo tên id cho submenu_Student
    @FXML
    private AnchorPane Pane_Student;
    //khởi tạo tên cho Pane_menuBook
    @FXML
    private AnchorPane Pane_BookManager;

    @FXML
    private AnchorPane Pane_BookInformation;

    @FXML
    private AnchorPane Pane_BorrowReturn;

    @FXML
    private AnchorPane Pane_DiscussionRoom;


    //btn_add chính là tên code của nút "+" khi ấn chuyển giao diện
    @FXML
    private JFXButton btn_add;

    // tạo nút chuyển quay lại
    @FXML
    private JFXButton btn_back;



    // thiết lập tên id nút nguồn
    @FXML
     private JFXButton btn_Option,btn_option_back,btn_HomePage,btn_Student;

    @FXML
    private JFXButton btn_Book;

    @FXML
    private JFXButton btn_Borr_Return;

    @FXML
    private JFXButton btn_DisscusionRoom;

    // nút logut của menu option
    @FXML
    private JFXButton btn_LogOut,btn_Restart, btn_Login,btn_AdminLogout;
    @FXML
    private Button btn_adminLogin;
    @FXML
    private TextField field_AdminUserName;
    @FXML
    private PasswordField field_AdminPass;
    @FXML
    private AnchorPane Pane_LoginManager,Pane_Admin;


    // định nghi các nút button trong giao diện student manager
    @FXML
    private Button btn_ReaderLogin;

    @FXML
    private Button btn_ReaderTable;

    @FXML
    private AnchorPane subPane_Reader;

    @FXML
    private AnchorPane subPane_ReaderLogin;


    /*tableview javafx*/


    //table Reader
    @FXML
    private TableView<Reader> table_reader;

    @FXML
    private TableColumn<Reader,String> column_ID;
    @FXML
    private TableColumn<Reader,String> column_Name;
    @FXML
    private TableColumn<Reader,String> column_Gender;
    @FXML
    private TableColumn<Reader, Date> column_Birth;
    @FXML
    private TableColumn<Reader,String> column_Address;
    @FXML
    private TableColumn<Reader, String> column_Phone;
    @FXML
    private TableColumn<Reader,String> column_Barcode;
    @FXML
    private TableColumn<Reader,Date> column_dateCreate;
    @FXML
    private TableColumn<Reader,Date> column_endDate;
    @FXML
    private TableColumn<Reader,String> column_pass;


    // combobox
    @FXML
    private ComboBox<String> combobox_Gender;
    @FXML
    private ComboBox<String> combobox_Status;


    // định nghĩa tên  datePicker
    @FXML
    private DatePicker datePicker_Reader;
    @FXML
    private DatePicker datePicker_Reader2;
    @FXML
    private DatePicker datePicker_Reader3;


    @FXML
    private TextField field_Address;
    @FXML
    private TextField field_Barcode;
    @FXML
    private TextField field_Phone;
    @FXML
    private  TextField field_ReaderID;
    @FXML
    private TextField field_ReaderName;
    @FXML
    private PasswordField field_ReaderPass;


    private Image image;

    @FXML
    private ImageView addImage_Reader;

    // nut import
    @FXML
    private JFXButton btn_Import;
    @FXML
    private JFXButton btn_ShowAll;


    // khởi tạo object cho Anchopane BookManager
    @FXML
    private AnchorPane pane_Book;
    @FXML
    private AnchorPane Pane_Author;
    @FXML
    private AnchorPane Pane_BookShelf;
    @FXML
    private AnchorPane Pane_CatalogOfBooks;
    @FXML
    private AnchorPane Pane_Publisher;

    @FXML
    private Button btn_Author,btn_BookShelf,btn_Catalog,btn_Publisher,btn_bookList;

    // tableBookShelf
    @FXML
    private JFXButton btn_InsertReader;
    @FXML
    private JFXButton btn_ClearReader;
    @FXML
    private JFXButton btn_DeleteBookInfo;
    @FXML
    private  JFXButton btn_UpdateReader;
    @FXML
    private JFXButton btn_Refesh;

    @FXML
    private TableView<BookShelf> table_bookShelf;

    @FXML
    private  TableColumn<BookShelf,String> column_BookShelfID;
    @FXML
    private  TableColumn<BookShelf, String> column_BookShelfTittle;

    @FXML
    private TextField field_bookShelfID, field_bookShelfTittle;

    // pane Catalog->BookManager
    @FXML
    private TextField field_CatalogID,field_CatalogTitle;
    @FXML
    private ComboBox combobox_BoshelfID;
    @FXML
    private TableView<CatalogOfBooks> table_Catalog;
    @FXML
    private TableColumn<CatalogOfBooks, String> column_CatalogID;
    @FXML
    private TableColumn<CatalogOfBooks,String> column_CatalogTitle;
    @FXML
     private TableColumn<CatalogOfBooks, BookShelf> column_CatalogBoshelfID;


    // pane Catatalog og BookManager
    @FXML
    private TextField field_PubID,field_PubName,field_PubPhone,field_PubAddress;
    @FXML
    private TableView<Publisher> table_Publisher;
    @FXML
    private TableColumn<Publisher, String> column_PubID;
    @FXML
    private TableColumn<Publisher, String> column_PubName;
    @FXML
    private TableColumn<Publisher, String> column_PubPhone;
    @FXML
    private TableColumn<Publisher, String> column_PubAddress;


    // pane Author of BookManager
    @FXML
    private TableView<Author> table_Author;
    @FXML
    private TableColumn<Author, String> column_AuthorID,column_AuthorName,column_AuthorSubject,column_Description;
    @FXML
    private TextField field_AuhtorID, field_AuthorName, field_AuthorSubject;
    @FXML
    private  TextArea textArea_Description;

    // pane Book of Book Manager
    @FXML
    private TableView<Book_view> table_Book;
    @FXML
    private TableColumn<Book_view, String>column_BookID,column_Book_CatID,column_Book_AuID,column_Book_PubID,column_BookTitle,column_BookPrice,
            column_BookAmount,column_BookBarcode,column_Book_EmpID,column_BookDateAdd,column_BookActivity;

    @FXML
    private JFXButton btn_ShowAllBook,btn_ImportBook,btn_SearchBooks;
    @FXML
    private TextField field_BookID,field_BookTitle,field_BookPrice,field_BookAmount,field_Book_BarCode,field_SearchBooks;
    @FXML
    private DatePicker datePicker_Book;
    @FXML
    private ComboBox combobox_Book_CatalogID,combobox_Book_AuthorID,combobox_Book_PubID,combobox_Book_EmpID,combobox_BookActivity;
    @FXML
    private  ImageView add_BookImg;


    // pane DiscussionRoom
    @FXML
    private Button btn_RegisterRoom,btn_DiscussionRoom;
    @FXML
    private AnchorPane pane_RegistersBorrowed,pane_DiscussionRoom;
    @FXML
    private TextField field_DisID,field_DisName,field_Location,field_DisStatus;
    @FXML
    private  TextArea text_Desciption;
    @FXML
    private TableView<DiscussionRoom>table_DiscussionRoom;
    @FXML
    private TableColumn<DiscussionRoom,String>column_DisID,column_DisName,column_DisLocation,column_DisStatus,column_DisDsciption;



    //pane RetristerBorrowed
    @FXML
    private TableView<RetristersBorrowed>table_RegisterBorrowed;
    @FXML
    private TableColumn<RetristersBorrowed,String>column_RB_DisID,column_RB_RID,
            column_RB_DisDate,column_RB_Description,column_RB_Activity;

    @FXML
     private TableColumn<RetristersBorrowed, Time>column_Timefrom, column_Timeto;

    @FXML
     private TextArea textArrea_RB_Description;
    @FXML
    private ComboBox combobox_RB_DisID,combobox_RB_RID,combobox_RB_Activity,combobox_RBByShift;
    @FXML
    private DatePicker datepicker_RB_DisDate;
    @FXML
    private JFXButton btn_RBShowAll;



    //pane Borrowed/return Books
    @FXML
    private TableView <Book_view> table_BRBooks;
    @FXML
    private TableColumn<Book_view,String>column_BRBookName;
    @FXML
    private TableView <BorrowAndReturnBook> table_BR;
    @FXML
    private TableColumn<BorrowAndReturnBook,String>column_BRBookID,column_BRReaderID,column_BRStatus;
    @FXML
    private TableColumn<BorrowAndReturnBook,Date>column_BRStartDate,column_BREndDate;

    @FXML
    private TextField field_BRBookID,field_BRBookTitle,field_BRBookPrice,field_BRBookNum;
    @FXML
    private ComboBox combobox_BRBookID,combobox_BRReaderID,combobox_BRStatus;
    @FXML
    private DatePicker datepicker_BRStartdate,datepicker_BREndDate;

    @FXML
    private JFXButton btn_BRSelect,btn_BRShowAll;
    @FXML
    private ImageView addImage_BR;


    /*declare component for method Search all Pane*/
      // component ò pane Reader
       @FXML
       private TextField field_SearchReader;
       @FXML
       private JFXButton btn_SearchReader;

    /*=============================================call model====================================================*/
    Reader reader = new Reader();
    AdminService adminService = new AdminService();
    BookShelf bookShelf = new BookShelf();
    CatalogOfBooks catalogOfBooks = new CatalogOfBooks();
    Publisher publisher = new Publisher();
    Author author = new Author();
    Book_view book_view = new Book_view();
    DiscussionRoom disRoom = new DiscussionRoom();
    Employee employee = new Employee();
    BorrowAndReturnBook BRBooks = new BorrowAndReturnBook();
    RetristersBorrowed RBorrow = new RetristersBorrowed();


//====================================================Mehtod interface switch header===============================================================================

    // xây dựng method xử lý ẩn hiện các sub menu nguồn, menu header...chuyển đổi qua lại các giao diện
    @FXML
    void chuyenGiaoDien_Header(ActionEvent event) {
        if(event.getSource() == btn_add){
            // khi ấn nút giao diện submenu sẽ chạy
            subPane_Header.setVisible(true);
            // toFront gigiuspchuyeenr sang giao diện ở phía trước
             subPane_Header.toFront();
        }else if(event.getSource() == btn_back){
            subPane_Header.setVisible(false);
            /*Main_View.toFront();*/
        }else if(event.getSource() == btn_Option){
            if(subMenu_Option.visibleProperty().getValue() == true){
                subMenu_Option.setVisible(false);
            }else {
                subMenu_Option.setVisible(true);
            }

            /*giải thích về thuộc tính VisibleProperty:
            *1/Thuộc tính "visible" trong Java là một thuộc tính của một đối tượng (object)
            *2/Thuộc tính này quy định trạng thái hiển thị của đối tượng đó trên giao diện người dùng.(true: hiển thị false thì ẩn)
            *3/ Trong đoạn code trên, khi "subMenu_Option.visibleProperty().getValue() == true", nghĩa là thuộc tính "visible" của đối
            *  tượng "subMenu_Option" đang được đặt là "true", tức là đối tượng đang hiển thị trên giao diện người dùng. Do đó, nó sẽ
            * thực hiện lệnh "subMenu_Option.setVisible(false);" để ẩn menu con đó đi
            *
            * 4/ Tương tự, nếu thuộc tính "visible" của đối tượng "subMenu_Option" đang được đặt là "false", nghĩa là đối tượng đang bị
            *  ẩn trên giao diện người dùng. Khi đó, nó sẽ thực hiện lệnh "subMenu_Option.setVisible(true);" để đặt thuộc tính "visible"
            * của đối tượng này về "true", từ đó hiển thị đối tượng trở lại trên giao diện người dùng.
            *  */


            // thực hiện tiếp chức năng chuyển sub_menu: homepage và Student
        }else if(event.getSource()==btn_HomePage){
            // code ntn để lúc nào chạy ctrinh đầu tiên cũng sẽ load Anchopane HomePage trc tiên
            Pane_Home.toFront();
        }else if(event.getSource() == btn_Student){
            Pane_Student.setVisible(true);
            Pane_Student.toFront();
        }else if(event.getSource() == btn_Book){
            Pane_BookManager.setVisible(true);
            Pane_BookManager.toFront();
        }else if(event.getSource()==btn_Borr_Return){
            Pane_BorrowReturn.setVisible(true);
            Pane_BorrowReturn.toFront();
        }else if(event.getSource()==btn_DisscusionRoom){
            Pane_DiscussionRoom.setVisible(true);
            Pane_DiscussionRoom.toFront();
        }
    }

//==================================================Mehtod Option Menu===========================================================================

    // method xử lý các nut của menu Option
    @FXML
    void LogoutAction(ActionEvent event) {
        if (event.getSource() == btn_LogOut) {
            //login her
                try {
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Log_In.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        }else if(event.getSource()==btn_Restart){
            try {
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Admin.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
               System.err.println(e.getMessage());
            }

        }else if(event.getSource()==btn_Login){
            try {
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Log_In.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }


    //>>>>>>>>>>>>>>>>method login Amin<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
   public void handleAdminLogin(ActionEvent event){
        if(event.getSource()==btn_adminLogin){
            try {
                employee = adminService.AdminLogin(field_AdminUserName.getText(),field_AdminPass.getText());
                if(employee !=null){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Successful!");
                    alert.setHeaderText(null);
                    alert.setContentText("Login is successful!");
                    alert.showAndWait();

                    Pane_Admin.setVisible(true);
                    Pane_LoginManager.setVisible(false);

                    //clear information of 2 field login and pass
                    field_AdminUserName.setText("");
                    field_AdminPass.setText("");
                }else if(employee == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fail Login");
                    alert.setHeaderText(null);
                    alert.setContentText("Please do not leave it blank");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fail Login");
                    alert.setHeaderText(null);
                    alert.setContentText("Login is fails!");
                    alert.showAndWait();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        }else if(event.getSource()==btn_AdminLogout){
            Pane_Admin.setVisible(false);
            Pane_LoginManager.setVisible(true);
        }else if(event.getSource()==btn_EmpPage){
            //open Employee Page
            Stage stage = new Stage();
            EmployeePage empPage = new EmployeePage();
            empPage.start(stage);
        }else if(event.getSource()==btn_Depart){
            Stage stage = new Stage();
            DepartmentPage depPage = new DepartmentPage();
            depPage.start(stage);
        }
   }


//===============================================Method  Chage Anchopane, subPane==============================================================================
  // method change supane Book Manager
    @FXML
    public void handleChangeBookManager(ActionEvent event){
        if(event.getSource()==btn_bookList){
            pane_Book.setVisible(true);
            Pane_BookShelf.setVisible(false);
            Pane_CatalogOfBooks.setVisible(false);
            Pane_Publisher.setVisible(false);
            Pane_Author.setVisible(false);
        }else if(event.getSource()==btn_BookShelf){
            pane_Book.setVisible(false);
            Pane_BookShelf.setVisible(true);
            Pane_CatalogOfBooks.setVisible(false);
            Pane_Publisher.setVisible(false);
            Pane_Author.setVisible(false);
        }else if(event.getSource()==btn_Catalog){
            pane_Book.setVisible(false);
            Pane_BookShelf.setVisible(false);
            Pane_CatalogOfBooks.setVisible(true);
            Pane_Publisher.setVisible(false);
            Pane_Author.setVisible(false);
        }else if(event.getSource()==btn_Publisher){
            pane_Book.setVisible(false);
            Pane_BookShelf.setVisible(false);
            Pane_CatalogOfBooks.setVisible(false);
            Pane_Publisher.setVisible(true);
            Pane_Author.setVisible(false);
        }else if(event.getSource()==btn_Author){
            pane_Book.setVisible(false);
            Pane_BookShelf.setVisible(false);
            Pane_CatalogOfBooks.setVisible(false);
            Pane_Publisher.setVisible(false);
            Pane_Author.setVisible(true);
        }
    }

    public void handleDiscussionRoom(ActionEvent event){
        if(event.getSource()==btn_RegisterRoom){
            pane_RegistersBorrowed.setVisible(true);
            pane_DiscussionRoom.setVisible(false);
        }else if(event.getSource()==btn_DiscussionRoom){
            pane_RegistersBorrowed.setVisible(false);
            pane_DiscussionRoom.setVisible(true);
        }
    }
//=========================================================Method Load Data on Table view===============================================================

    /* xây dựng method load sql table Reader trong sql lên giao diện javafx*/


      // load dl từ sql đưa lên table view
    private ObservableList<Reader> listShowActiveData;

   public void showReaderTable(){
       // lấy dữ liệu từ sql và gán vào ListshowData
       listShowActiveData =  adminService.getActiveReaders();

       column_ID.setCellValueFactory(new PropertyValueFactory<>("readerID"));
       column_Name.setCellValueFactory(new PropertyValueFactory<>("readerName"));
       column_Birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
       column_Gender.setCellValueFactory(new PropertyValueFactory<>("readerGender"));
       column_Address.setCellValueFactory(new PropertyValueFactory<>("readerAddress"));
       column_Phone.setCellValueFactory(new PropertyValueFactory<>("readerPhone"));
       column_Barcode.setCellValueFactory(new PropertyValueFactory<>("readerBarcode"));
       column_dateCreate.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
       column_endDate.setCellValueFactory(new PropertyValueFactory<>("outOfDate"));
       column_pass.setCellValueFactory(new PropertyValueFactory<>("readerPass"));

       table_reader.setItems(listShowActiveData);
   }
    /* kết thúc method load sql table Reader*/


    /*load table view BookShelt trong BookInformation Manager*/
    private ObservableList<BookShelf> listTableBookShelf;
      public void showBookShelf(){
            listTableBookShelf = adminService.getAllBookShelf();
            column_BookShelfID.setCellValueFactory(new PropertyValueFactory<>("boShelfID"));
            column_BookShelfTittle.setCellValueFactory(new PropertyValueFactory<>("boShelfTitle"));

            table_bookShelf.setItems(listTableBookShelf);
      }
    /*end load Table BookShelf*/



    /*show table CatalogOfBook on Tablecatalog*/
    private ObservableList<CatalogOfBooks> listTableCatalog;
    public void showCatalog(){
        listTableCatalog = adminService.getAllCatalog();
        column_CatalogID.setCellValueFactory(new PropertyValueFactory<>("catalogID"));
        column_CatalogTitle.setCellValueFactory(new PropertyValueFactory<>("catalogTitle"));
        column_CatalogBoshelfID.setCellValueFactory(new PropertyValueFactory<>("boShelfID"));
        
        table_Catalog.setItems(listTableCatalog);
    }


    // action event show data Publisher on table_Publisher
    ObservableList<Publisher> listTablePublisher;
    public void showPublisher(){
        listTablePublisher = adminService.getAllPublisher();
        column_PubID.setCellValueFactory(new PropertyValueFactory<>("publisherID"));
        column_PubName.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        column_PubPhone.setCellValueFactory(new PropertyValueFactory<>("publisherPhone"));
        column_PubAddress.setCellValueFactory(new PropertyValueFactory<>("publisherAddress"));

        table_Publisher.setItems(listTablePublisher);
    }


    //action event show all data on table_Auhtor
    ObservableList<Author> listTable_Author;
    public void showAuthor(){
        listTable_Author = adminService.getAllAuthor();
        column_AuthorID.setCellValueFactory(new PropertyValueFactory<>("authorID"));
        column_AuthorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        column_AuthorSubject.setCellValueFactory(new PropertyValueFactory<>("authorSubject"));
        column_Description.setCellValueFactory(new PropertyValueFactory<>("description"));

        table_Author.setItems(listTable_Author);
    }


    //action event showAllBooks
    ObservableList<Book_view> listTableBooks;
    public void showBook(){
        listTableBooks = adminService.getAllBooks_Condition();
        column_BookID.setCellValueFactory(new PropertyValueFactory<>("book_ID"));
        column_Book_CatID.setCellValueFactory(new PropertyValueFactory<>("catalog_ID"));
        column_Book_AuID.setCellValueFactory(new PropertyValueFactory<>("author_ID"));
        column_Book_PubID.setCellValueFactory(new PropertyValueFactory<>("publisher_ID"));
        column_BookTitle.setCellValueFactory(new PropertyValueFactory<>("book_Title"));
        column_BookPrice.setCellValueFactory(new PropertyValueFactory<>("book_Price"));
        column_BookAmount.setCellValueFactory(new PropertyValueFactory<>("book_Amount"));
        column_BookBarcode.setCellValueFactory(new PropertyValueFactory<>("book_BarCode"));
        column_Book_EmpID.setCellValueFactory(new PropertyValueFactory<>("emp_ID"));
        column_BookDateAdd.setCellValueFactory(new PropertyValueFactory<>("date_Add"));
        column_BookActivity.setCellValueFactory(new PropertyValueFactory<>("acTivity"));

        table_Book.setItems(listTableBooks);
    }

    //method show data on table_DisRoom
    ObservableList<DiscussionRoom> listTableDisRoom;
    public void showDiscussionRoom(){
        listTableDisRoom = adminService.getAllDisRoom();
        column_DisID.setCellValueFactory(new PropertyValueFactory<>("disID"));
        column_DisName.setCellValueFactory(new PropertyValueFactory<>("disName"));
        column_DisLocation.setCellValueFactory(new PropertyValueFactory<>("disLocation"));
        column_DisStatus.setCellValueFactory(new PropertyValueFactory<>("disStatus"));
        column_DisDsciption.setCellValueFactory(new PropertyValueFactory<>("description"));

        table_DiscussionRoom.setItems(listTableDisRoom);
    }


    //method load data name Books on table_BRBooks
    public void showBRBooks(){
        listTableBooks = adminService.getAllBooks();
        column_BRBookName.setCellValueFactory(new PropertyValueFactory<>("book_Title"));

        table_BRBooks.setItems(listTableBooks);
    }



    //method load data on Table_BR
    ObservableList<BorrowAndReturnBook> listTableBR;
    public void showBorrowAndReturnBook(){
        listTableBR = adminService.getAllBorrowAndReturnBook_Condition();
        column_BRBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        column_BRReaderID.setCellValueFactory(new PropertyValueFactory<>("readerID"));
        column_BRStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        column_BREndDate.setCellValueFactory(new PropertyValueFactory<>("retdate"));
        column_BRStatus.setCellValueFactory(new PropertyValueFactory<>("borrStatus"));

        table_BR.setItems(listTableBR);
    }



    //method load Data show Table_RetristersBorrowed
    ObservableList<RetristersBorrowed> listTableRB;
    public void showRetristersBorrowed(){
        listTableRB = adminService.getAllRetristerBorrowed_Coditional();

        column_RB_DisID.setCellValueFactory(new PropertyValueFactory<>("disID"));
        column_RB_RID.setCellValueFactory(new PropertyValueFactory<>("readerID"));
        column_Timefrom.setCellValueFactory(new PropertyValueFactory<>("borrowedByShift"));
        column_RB_DisDate.setCellValueFactory(new PropertyValueFactory<>("disDate"));
        column_RB_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        column_RB_Activity.setCellValueFactory(new PropertyValueFactory<>("activity"));

        table_RegisterBorrowed.setItems(listTableRB);
    }

 //========================================event Button show all load table with condition (ex: status('not Active'))===================================
    //btn_showAll of pane Student Manager
    @FXML
     void handleShowAll(ActionEvent event) {
            if(event.getSource()==btn_ShowAll){
                listShowActiveData =  adminService.getAllReaders();

                column_ID.setCellValueFactory(new PropertyValueFactory<>("readerID"));
                column_Name.setCellValueFactory(new PropertyValueFactory<>("readerName"));
                column_Birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
                column_Gender.setCellValueFactory(new PropertyValueFactory<>("readerGender"));
                column_Address.setCellValueFactory(new PropertyValueFactory<>("readerAddress"));
                column_Phone.setCellValueFactory(new PropertyValueFactory<>("readerPhone"));
                column_Barcode.setCellValueFactory(new PropertyValueFactory<>("readerBarcode"));
                column_dateCreate.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
                column_endDate.setCellValueFactory(new PropertyValueFactory<>("outOfDate"));
                column_pass.setCellValueFactory(new PropertyValueFactory<>("readerPass"));
                table_reader.setItems(listShowActiveData);
            }
     }


     // btn_ShowAllBook of Pane Book(Book Manager)
    @FXML
    public void handleShowAll_btnBook(ActionEvent event){
        if(event.getSource()==btn_ShowAllBook){
            listTableBooks = adminService.getAllBooks();
            column_BookID.setCellValueFactory(new PropertyValueFactory<>("book_ID"));
            column_Book_CatID.setCellValueFactory(new PropertyValueFactory<>("catalog_ID"));
            column_Book_AuID.setCellValueFactory(new PropertyValueFactory<>("author_ID"));
            column_Book_PubID.setCellValueFactory(new PropertyValueFactory<>("publisher_ID"));
            column_BookTitle.setCellValueFactory(new PropertyValueFactory<>("book_Title"));
            column_BookPrice.setCellValueFactory(new PropertyValueFactory<>("book_Price"));
            column_BookAmount.setCellValueFactory(new PropertyValueFactory<>("book_Amount"));
            column_BookBarcode.setCellValueFactory(new PropertyValueFactory<>("book_BarCode"));
            column_Book_EmpID.setCellValueFactory(new PropertyValueFactory<>("emp_ID"));
            column_BookDateAdd.setCellValueFactory(new PropertyValueFactory<>("date_Add"));
            column_BookActivity.setCellValueFactory(new PropertyValueFactory<>("acTivity"));

            table_Book.setItems(listTableBooks);
        }
    }

    //method SHowAll_BRBooks for btn_BRShowAll
    @FXML
    public void handleShowAll_BRBook(ActionEvent event){
        if(event.getSource()==btn_BRShowAll){
            listTableBR = adminService.getAllBorrowAndReturnBook();
            column_BRBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            column_BRReaderID.setCellValueFactory(new PropertyValueFactory<>("readerID"));
            column_BRStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            column_BREndDate.setCellValueFactory(new PropertyValueFactory<>("retdate"));
            column_BRStatus.setCellValueFactory(new PropertyValueFactory<>("borrStatus"));

            table_BR.setItems(listTableBR);
        }
    }

    //method Actiont event btn_BRSelect of Pane BRBOOKs
    public void handleSelect_BRBooks(ActionEvent event){
        if(event.getSource()==btn_BRSelect){
            //Display image
            String uri = "file:" + book_view.getBook_Img();
            image = new Image(uri,117,138,false,true);
            addImage_BR.setImage(image);
            System.out.println(uri);

            //combobox_BR_BookID
            String BRBookID= String.valueOf(book_view.getBook_ID());
            for(Object itemBookID: combobox_BRBookID.getItems()){
                if(itemBookID.equals(BRBookID)){
                    combobox_BRBookID.setValue(itemBookID);
                    break;
                }
            }
        }
    }


    //method btn_RBShowAll
    public void handleShowAll_RB(ActionEvent event){
        if(event.getSource()==btn_RBShowAll){
            listTableRB=adminService.getAllRetristerBorrowed();

            column_RB_DisID.setCellValueFactory(new PropertyValueFactory<>("disID"));
            column_RB_RID.setCellValueFactory(new PropertyValueFactory<>("readerID"));
            column_Timefrom.setCellValueFactory(new PropertyValueFactory<>("borrowedByShift"));
            column_RB_DisDate.setCellValueFactory(new PropertyValueFactory<>("disDate"));
            column_RB_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
            column_RB_Activity.setCellValueFactory(new PropertyValueFactory<>("activity"));

            table_RegisterBorrowed.setItems(listTableRB);
        }
    }


    /*==================================================================action event combobox for all=================================================================*/

//===============================================================SelectItem, Import======================================================================

    /*xây dựng method selctItem_Reader -> load khi ấn chọn data hiển thị lên các textfiled...
    * đây là method xủa lý sự kiện event Mouse clicked cuủa table view reader
    * */

     public void selectItem_Reader() {
        Reader reader = table_reader.getSelectionModel().getSelectedItem();
        int num = table_reader.getSelectionModel().getSelectedIndex();

        //kiểm tra xem người dùng có đang chọn bản ghi trên bảng dữ liệu hay không.
        if((num-1) < -1){
            return;
        }

        field_ReaderID.setText(reader.getReaderID());
        field_ReaderName.setText(reader.getReaderName());
         // thiết lập giá trị cho date Birth
         String dateBirth = String.valueOf(reader.getBirth());
         LocalDate localDate = LocalDate.parse(dateBirth);
         datePicker_Reader.setValue(localDate);

        // Thiết lập giá trị cho ComboBox giới tính
        String gender = String.valueOf(reader.getReaderGender());
        if (gender.equals("Male")) {
            combobox_Gender.setValue("Male");
        } else if (gender.equals("Female")) {
            combobox_Gender.setValue("Female");
        }

        field_Address.setText((reader.getReaderAddress()));
        field_Phone.setText(reader.getReaderPhone());
        field_Barcode.setText(reader.getReaderBarcode());

         // thiết lập giá trị cho date Create
         String dateCreate = String.valueOf(reader.getDateCreated());
         LocalDate localDate2 = LocalDate.parse(dateCreate);
         datePicker_Reader2.setValue(localDate2);

         // thiết lập giá trị cho date Enddate
         String enDate = String.valueOf(reader.getOutOfDate());
         LocalDate localDate3  = LocalDate.parse(enDate);
         datePicker_Reader3.setValue(localDate3);

         field_ReaderPass.setText(reader.getReaderPass());

        // tuy chinh select image trong kho du lieu
         String uri = "file:" + reader.getReaderImg();
         /*
          * image = new Image(uri, 117, 138, false, true); được sử dụng để tạo một đối tượng hình ảnh từ đường dẫn (URI)
          * và kích thước của ảnh. 117 và 138 là chiều rộng và chiều cao của hình ảnh. Các tham số cuối cùng là booleans
          * để chỉ định xem ảnh có được tải bất đồng bộ hay đồng bộ không
          * */
         image = new Image(uri,117,138,false,true);
         addImage_Reader.setImage(image);
         System.out.println(uri);

         // Thiết lập giá trị cho ComboBox status
         String gender2 = String.valueOf(reader.getReaderStatus());
         if (gender2.equals("Active")) {
             combobox_Status.setValue("Active");
         } else if (gender2.equals("stop working")) {
             combobox_Status.setValue("stop working");
         }

     }
    /*end method selectItem_Reader*/


    /*event mouseclick selectItem_bookShelf*/
    public void selectItem_bookShelf(){
        BookShelf bookShelf = table_bookShelf.getSelectionModel().getSelectedItem();
        int num = table_bookShelf.getSelectionModel().getSelectedIndex();

        //text for user can join table or no?
        if((num-1)<-1){
            return;
        }
        field_bookShelfID.setText(bookShelf.getBoShelfID());
        field_bookShelfTittle.setText(bookShelf.getBoShelfTitle());
    }



    /*selectItem_Catalog */
    public void selectItem_Catalog(){
        CatalogOfBooks catalogOfBooks = table_Catalog.getSelectionModel().getSelectedItem();
        int num = table_Catalog.getSelectionModel().getSelectedIndex();

        //text for user can join row on table or no?
        if((num - 1)<-1){
            return;
        }
        field_CatalogID.setText(catalogOfBooks.getCatalogID());
        field_CatalogTitle.setText(catalogOfBooks.getCatalogTitle());

        //handle for combobox, when selecting data will display text on combobox
        String boShelfID = String.valueOf(catalogOfBooks.getBoShelfID());
        for(Object item: combobox_BoshelfID.getItems()){
            if(item.equals(boShelfID)){
                combobox_BoshelfID.setValue(item);
                break;
            }
        }

    }



    /*xây dựng method import ảnh.. load ảnh trong csdl để thay đổi ảnh đại diện cho reader table*/
    public void Import_ReaderTable(){
        FileChooser open = new FileChooser();
        open.setTitle("Open new Image");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image FILE","*png","*jpg"));
        Stage stage = (Stage) Pant_Main.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if(file != null){
            // phương thức replaceAll() để thay thế các ký tự '\' bằng '/': để dẫn đg lưu ảnh ok hơn và chính xác hơn
//            String path = file.getAbsolutePath().replaceAll("\\\\", "/");
            image = new Image(file.toURI().toString(), 127, 138, false, true);
            addImage_Reader.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }



    //method selectItem for Table_Publisher
    public void selectItem_Publisher(){
        publisher = table_Publisher.getSelectionModel().getSelectedItem();
        int num = table_Publisher.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){
            return;
        }

        field_PubID.setText(publisher.getPublisherID());
        field_PubName.setText(publisher.getPublisherName());
        field_PubPhone.setText(publisher.getPublisherPhone());
        field_PubAddress.setText(publisher.getPublisherAddress());
    }



    // methos selectedITem for Author
    public void selectItem_Author(){
        author = table_Author.getSelectionModel().getSelectedItem();
        int num =  table_Author.getSelectionModel().getSelectedIndex();

        if((num -1)<-1){
            return;
        }

        field_AuhtorID.setText(author.getAuthorID());
        field_AuthorName.setText(author.getAuthorName());
        field_AuthorSubject.setText(author.getAuthorSubject());
        textArea_Description.setText(author.getDescription());
    }

    //method selectItem Books
    public void selectItem_Books(){
        book_view = table_Book.getSelectionModel().getSelectedItem();
        int num = table_Book.getSelectionModel().getSelectedIndex();

        if((num-1)<-1){
            return;
        }

        field_BookID.setText(book_view.getBook_ID());

        //combobox_Book_CatalogID
        String CataID = String.valueOf(book_view.getCatalog_ID());
        for(Object itemCataID: combobox_Book_CatalogID.getItems()){
            if(itemCataID.equals(CataID)){
                combobox_Book_CatalogID.setValue(itemCataID);
                break;
            }
        }

        //combobox_Book_AuhtorID
        String AutID = String.valueOf(book_view.getAuthor_ID());
        for(Object itemAutID: combobox_Book_AuthorID.getItems()){
            if(itemAutID.equals(AutID)){
                combobox_Book_AuthorID.setValue(itemAutID);
                break;
            }
        }


        //combobox_Book_PubID
        String PubID = String.valueOf(book_view.getPublisher_ID());
        for(Object itemPubID: combobox_Book_PubID.getItems()){
            if(itemPubID.equals(PubID)){
                combobox_Book_PubID.setValue(itemPubID);
                break;
            }
        }

        field_BookTitle.setText(book_view.getBook_Title());
        field_BookPrice.setText(book_view.getBook_Price());
        field_BookAmount.setText(String.valueOf(book_view.getBook_Amount()));
        field_Book_BarCode.setText(book_view.getBook_BarCode());

        // event select Img
        String uri = "file:" + book_view.getBook_Img();
        image = new Image(uri,117,138,false,true);
        add_BookImg.setImage(image);
        System.out.println(uri);


        //combobox_Book_EmpID
        String bookImg = String.valueOf(book_view.getEmp_ID());
        for(Object itemEmpID: combobox_Book_EmpID.getItems()){
            if(itemEmpID.equals(bookImg)){
                combobox_Book_EmpID.setValue(itemEmpID);
                break;
            }
        }

        //datePicker dateAdded
        String dateAdd = String.valueOf(book_view.getDate_Add());
        LocalDate localDate_dateAdd = LocalDate.parse(dateAdd);
        datePicker_Book.setValue(localDate_dateAdd);

        //combobox_BookActivity
        // Thiết lập giá trị cho ComboBox status
        String Activity = String.valueOf(book_view.getAcTivity());
        if (Activity.equals("Available")) {
            combobox_BookActivity.setValue("Available");
        } else if (Activity.equals("Borrowed")) {
            combobox_BookActivity.setValue("Borrowed");
        }else if(Activity.equals("Overdue")){
            combobox_BookActivity.setValue("Overdue");
        }else if(Activity.equals("Lost")){
            combobox_BookActivity.setValue("Lost");
        }else if(Activity.equals("Damaged")){
            combobox_BookActivity.setValue("Damaged");
        }else if(Activity.equals("Withdrawn")){
            combobox_BookActivity.setValue("Withdrawn");
        }

    }


    //import BookImg for pane Book
    public void Import_BookImg(){
        FileChooser open = new FileChooser();
        open.setTitle("Open new Image");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image FILE","*png","*jpg"));
        Stage stage = (Stage) Pant_Main.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if(file != null){
            image = new Image(file.toURI().toString(), 127, 138, false, true);
            add_BookImg.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }



    //action show select dâta on table_DiSRoom and display on Textfield
    public void selectItem_DisRoom(){
        disRoom = table_DiscussionRoom.getSelectionModel().getSelectedItem();
        int num = table_DiscussionRoom.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){
            return;
        }

        field_DisID.setText(disRoom.getDisID());
        field_DisName.setText(disRoom.getDisName());
        field_Location.setText(disRoom.getDisLocation());
        field_DisStatus.setText(disRoom.getDisStatus());
        textArea_Description.setText(disRoom.getDescription());
    }


    //method selectItem_RetrisgerBorrowed
        public void selectITem_BRBooks(){
          book_view = table_BRBooks.getSelectionModel().getSelectedItem();
          int num = table_BRBooks.getSelectionModel().getSelectedIndex();
          if((num-1)<-1){
              return;
          }

          field_BRBookID.setText(book_view.getBook_ID());
          field_BRBookTitle.setText(book_view.getBook_Title());
          field_BRBookPrice.setText(book_view.getBook_Price());
          field_BRBookNum.setText(String.valueOf(book_view.getBook_Amount()));
        }




    //method  load data on table_BR
    public void selectItem_BR(){
        BRBooks = table_BR.getSelectionModel().getSelectedItem();
        int num = table_BR.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){
            return;
        }

        //combobox_BRBooksID
        String bookID = String.valueOf(BRBooks.getBookID());
        for(Object itemBookID: combobox_BRBookID.getItems()){
            if(itemBookID.equals(bookID)){
                combobox_BRBookID.setValue(itemBookID);
                break;
            }
        }

        //select combobox_BRReaderID
        String readerID = String.valueOf(BRBooks.getReaderID());
        for(Object itemReaderID: combobox_BRReaderID.getItems()){
            if(itemReaderID.equals(readerID)){
                combobox_BRReaderID.setValue(itemReaderID);
                break;
            }
        }

        //select datepicker_BRStartDate
        String startDate = String.valueOf(BRBooks.getStartDate());
        LocalDate localStart_Date = LocalDate.parse(startDate);
        datepicker_BRStartdate.setValue(localStart_Date);

        //seelct datepicker_Enddate
        String endDate = String.valueOf(BRBooks.getRetdate());
        LocalDate localEnd_Date = LocalDate.parse(endDate);
        datepicker_BREndDate.setValue(localEnd_Date);

        //select combobox_BRStatus
        String brStatus = String.valueOf(BRBooks.getBorrStatus());
        for(Object itemStatus: combobox_BRStatus.getItems()){
            if(itemStatus.equals(brStatus)){
                combobox_BRStatus.setValue(itemStatus);
                break;
            }
        }

    }


    //method select_RB()
    public void selectItem_RB(){
        RBorrow=table_RegisterBorrowed.getSelectionModel().getSelectedItem();
        int num = table_RegisterBorrowed.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){
            return;
        }

        //select combobox_RB_DisID hs for loop
        String disID = String.valueOf(RBorrow.getDisID());
        for(Object itemDisID: combobox_RB_DisID.getItems()){
            if(itemDisID.equals(disID)){
                combobox_RB_DisID.setValue(itemDisID);
                break;
            }
        }

        //select combbobox_RB_ReaderID
        String readerID = String.valueOf(RBorrow.getReaderID());
        for(Object itemReadID : combobox_RB_RID.getItems()){
            if(itemReadID.equals(readerID)){
                combobox_RB_RID.setValue(itemReadID);
                break;
            }
        }

        //select combobox_RBByShift has default
        String rbByshift = String.valueOf(RBorrow.getBorrowedByShift());
        if(rbByshift.equals("Shift 1: 7:30AM - 9:30AM")){
            combobox_RBByShift.setValue("Shift 1: 7:30AM - 9:30AM");
        }else if(rbByshift.equals("Shift 2: 9:40AM - 11:40AM")){
            combobox_RBByShift.setValue("Shift 2: 9:40AM - 11:40AM");
        }else if(rbByshift.equals("Shift 3: 11:50AM - 13h:50PM")){
            combobox_RBByShift.setValue("Shift 3: 11:50AM - 13h:50PM");
        }else if(rbByshift.equals("Shift 4: 14:00PM - 16:00PM")){
            combobox_RBByShift.setValue("Shift 4: 14:00PM - 16:00PM");
        }else if(rbByshift.equals("Shift 5: 16:10PM - 18h10PM")){
            combobox_RBByShift.setValue("Shift 5: 16:10PM - 18h10PM");
        }else if(rbByshift.equals("Shift 6: 18h20PM - 20h20PM")){
            combobox_RBByShift.setValue("Shift 6: 18h20PM - 20h20PM");
        }


        //select datepicker_RB
        String disDate = String.valueOf(RBorrow.getDisDate());
        LocalDate localDisDate = LocalDate.parse(disDate);
        datepicker_RB_DisDate.setValue(localDisDate);

        textArrea_RB_Description.setText(RBorrow.getDescription());

        //select bombobox_RBActivity has for loop
        String activity = String.valueOf(RBorrow.getActivity());
        if(activity.equals("Pending")){
            combobox_RB_Activity.setValue("Pending");
        }else if(activity.equals("Cancelled")){
            combobox_RB_Activity.setValue("Cancelled");
        }else if(activity.equals("Processed")){
            combobox_RB_Activity.setValue("Processed");
        }else if(activity.equals("Returned")){
            combobox_RB_Activity.setValue("Returned");
        }
    }


    /*end */
//=====================================================================method Inserts===================================================================
    /*Mehtod insert*/

     //>>>>>method Insert of table Reader<<<<<
         // event for button Insert Tablereader
            public void handleInsertReader(){
                try {
                    if (field_ReaderID.getText().isEmpty() || field_ReaderName.getText().isEmpty() || combobox_Gender.getValue()==null
                            || datePicker_Reader.getValue() == null || field_Address.getText().isEmpty() || field_Phone.getText().isEmpty()
                            || field_Barcode.getText().isEmpty() || datePicker_Reader2.getValue() == null || datePicker_Reader3.getValue() == null
                            || field_ReaderPass.getText().isEmpty()) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        // pt headerText (null) loại bỏ đi tiêu đề mặc định của hộp thoại
                        alert.setHeaderText(null);
                        alert.setContentText("Please don't leave it blank!");
                        // showAndwait dùng để hiển thị v chờ người dùng phàn hồi lỗi
                        alert.showAndWait();

                    } else {
                        reader.setReaderID(field_ReaderID.getText());
                        reader.setReaderName(field_ReaderName.getText());

                        //thiết nhận date cho datepicker
                        LocalDate localDate = datePicker_Reader.getValue();
                        java.sql.Date dateBirth = java.sql.Date.valueOf(localDate);
                        reader.setBirth(dateBirth);


                        // xử lý combobox
                        String select = (String) combobox_Gender.getValue();
                        reader.setReaderGender(select);

                        if(!isValidAddress(field_Address.getText())){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setHeaderText(null);
                            alert.setContentText("wrong address, need to enter enough: street name, ward/commune, district/district, province/city!");
                            alert.showAndWait();
                            return;
                        }else{
                            reader.setReaderAddress(field_Address.getText());
                        }

                        String phonePattern = "\\d{10}"; // chuỗi số điện thoại phải có đúng 10 chữ số
                        String phone = field_Phone.getText();
                        if (!phone.matches(phonePattern)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid phone number. Please enter all 10 numbers!");
                            alert.showAndWait();
                            return;
                        }else{
                            reader.setReaderPhone(field_Phone.getText());
                        }

                        reader.setReaderBarcode(field_Barcode.getText());

                        // thiết nhận date cho dateCreate
                        LocalDate localDate2 = datePicker_Reader2.getValue();
                        java.sql.Date dateCreate = java.sql.Date.valueOf(localDate2);
                        reader.setDateCreated(dateCreate);

                        // thiết nhận date cho outOfdate
                        LocalDate localDate3 = datePicker_Reader3.getValue();
                        java.sql.Date outOfDate = java.sql.Date.valueOf(localDate3);
                        reader.setOutOfDate(outOfDate);

                        if(!isValidPassword(field_ReaderPass.getText())){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error!");
                            alert.setHeaderText(null);
                            alert.setContentText("Password must be at least 6-10 characters, have lowercase letters, and at least 1 special character");
                            alert.showAndWait();
                            return;
                        }else {
                            reader.setReaderPass(field_ReaderPass.getText());
                        }

                        // xử lý Insert ch0 trường image
                        // mục tiêu lấy đg dẫn uri lưu ảnh để tiện thao tác inset image
                        String uri = getData.path;
                        reader.setReaderImg(uri);

                        // readerStatus
                        String selectStatus = (String) combobox_Status.getValue();
                        reader.setReaderStatus(selectStatus);

                        adminService.Insert_Reader(reader);

                        // tạo thông báo là đã insert thành công
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Success");
                        alert.setContentText("You have successfully added!");
                        alert.showAndWait();

                        // khi inssert xong thì tiến hành clear thông tin vừa nhập và show lên tablevie từ hai method đã viết trc đó
                        Clear_ReaderTable();
                        showReaderTable();

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
         //end



        // insert ReaderLogin
        public void handleInsertBookShelf(){
            try{
                if(field_bookShelfID.getText().isEmpty() || field_bookShelfTittle.getText().isEmpty()){
                    Alert alert = new Alert((Alert.AlertType.ERROR));
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in the information, do not leave it blank!");
                    alert.showAndWait();
                }else{
                    bookShelf.setBoShelfID(field_bookShelfID.getText());
                    bookShelf.setBoShelfTitle(field_bookShelfTittle.getText());

                    adminService.Inert_BookShelf(bookShelf);

                    //success Message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Success!");
                    alert.setContentText("You have successfully updated!");
                    alert.showAndWait();


                    //insert none-> clear and show tableview again.
                    Clear_BookShelf();
                    showBookShelf();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
      }
        //end


    //evetn handleInsertcatalog
    public void handleInsertCatalog(){
         try{
            if(field_CatalogID.getText().isEmpty() || field_CatalogTitle.getText().isEmpty() || combobox_BoshelfID.getValue()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("you have successfully added");
                alert.showAndWait();
             }else{
                catalogOfBooks.setCatalogID(field_CatalogID.getText());
                catalogOfBooks.setCatalogTitle(field_CatalogTitle.getText());

                // combobox
                String select = (String) combobox_BoshelfID.getValue();
                catalogOfBooks.setBoShelfID(select);

                adminService.Insert_Catalog(catalogOfBooks);

                //Alert success Infomation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess!");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully added!");
                alert.showAndWait();

                //inert done -> clear and show data ontable again
                Clear_Catalog();
                showCatalog();
            }
         }catch (Exception e){
             e.printStackTrace();
         }
    }



    //actiont event handleInsert_Publisher
    public void handleInsert_Publisher(){
        try{
            if(field_PubID.getText().isEmpty() || field_PubName.getText().isEmpty()
            || field_PubPhone.getText().isEmpty() || field_PubAddress.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the information, do not leave it blank!");
                alert.showAndWait();
            }else {
                publisher.setPublisherID(field_PubID.getText());
                publisher.setPublisherName(field_PubName.getText());
                publisher.setPublisherPhone(field_PubPhone.getText());
                publisher.setPublisherAddress(field_PubAddress.getText());

                adminService.Insert_Publisher(publisher);

                //show alert success!
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully added!");
                alert.showAndWait();

                // clear and show data on table_publisher again
                Clear_Publisher();
                showPublisher();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //method handleInsertAuthor
   public void handleInsertAuthor(){
        try{
            if(field_AuhtorID.getText().isEmpty() || field_AuthorName.getText().isEmpty()
            || field_AuthorSubject.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the information, do not leave it blank!");
                alert.showAndWait();
            }else{
                author.setAuthorID(field_AuhtorID.getText());
                author.setAuthorName(field_AuthorName.getText());
                author.setAuthorSubject(field_AuthorSubject.getText());
                author.setDescription(textArea_Description.getText());

                adminService.Insert_Author(author);

                //alert success!
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully added!");
                alert.showAndWait();

                //clear and show data on tableAuthor
                Clear_Author();
                showAuthor();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
   }


    //handle Insert_Books
    public void handleInsertBooks(){
        try{
            if(combobox_Book_AuthorID.getValue()==null || combobox_Book_PubID.getValue()==null || combobox_Book_CatalogID.getValue()==null
            || field_BookTitle.getText().isEmpty() || field_BookPrice.getText().isEmpty()
            || field_BookAmount.getText().isEmpty() || field_Book_BarCode.getText().isEmpty()
            || combobox_Book_EmpID.getValue() ==null || datePicker_Book.getValue()==null
            || combobox_BookActivity.getValue()==null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the information, do not leave it blank!");
                alert.showAndWait();

            }else{
                book_view.setBook_ID(field_BookID.getText());

                //combobox CatalogID of Books
                String selectcataID = (String)combobox_Book_CatalogID.getValue();
                book_view.setCatalog_ID(selectcataID);

                //combobox AuthorID of Books
                String selectAuID =(String) combobox_Book_AuthorID.getValue();
                book_view.setAuthor_ID(selectAuID);

                //combobox PubID of Books
                String selectPubID = (String) combobox_Book_PubID.getValue();
                book_view.setPublisher_ID(selectPubID);

                book_view.setBook_Title(field_BookTitle.getText());
                book_view.setBook_Price(field_BookPrice.getText());
                book_view.setBook_Amount(Integer.parseInt(field_BookAmount.getText()));
                book_view.setBook_BarCode(field_Book_BarCode.getText());

                //handle update Image of Books
                String uri = getData.path;
                book_view.setBook_Img(uri);

                //combobox EmpID of Books
                String selectEmpID = (String) combobox_Book_EmpID.getValue();
                book_view.setEmp_ID(selectEmpID);

                //datePicker dateAddes of Books
                LocalDate localDate_DateAdd = datePicker_Book.getValue();
                java.sql.Date dateAD = java.sql.Date.valueOf(localDate_DateAdd);
                book_view.setDate_Add(dateAD);

                //combobox Activity
                String selectActivity = (String) combobox_BookActivity.getValue();
                book_view.setAcTivity(selectActivity);

                adminService.Insert_Books(book_view);

                //alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully updated!");
                alert.showAndWait();

                //clear and show data on table_Books again!
                Clear_Books();
                showBook();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    //method Insert BRBooks
     public  void handleInsertBRBooks(){
     try {
         if (combobox_BRBookID.getValue() == null || combobox_BRReaderID.getValue() == null || datepicker_BRStartdate.getValue() == null
                 || datepicker_BREndDate.getValue() == null || combobox_BRStatus.getValue() == null) {

             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error!");
             alert.setHeaderText(null);
             alert.setContentText("Please don't leave it blank!");
             alert.showAndWait();
         } else {
             //insert combobox_BRBookID
             String brBookID = (String) combobox_BRBookID.getValue();
             BRBooks.setBookID(brBookID);

             //insert combobox_BRReaderID
             String brReaderID = (String) combobox_BRReaderID.getValue();
             BRBooks.setReaderID(brReaderID);

             //insertDatepicker_BRStartDate
             LocalDate local_BRstartdate = datepicker_BRStartdate.getValue();
             java.sql.Date BR_StartDate = java.sql.Date.valueOf(local_BRstartdate);
             BRBooks.setStartDate(BR_StartDate);

             //insert datepicker_BREndate
             LocalDate local_BRRetDate = datepicker_BREndDate.getValue();
             java.sql.Date BR_RetDate = java.sql.Date.valueOf(local_BRRetDate);
             BRBooks.setRetdate(BR_RetDate);

             //insert Combobox_BRStatus
             String status = (String) combobox_BRStatus.getValue();
             BRBooks.setBorrStatus(status);

             adminService.Insert_BRBooks(BRBooks);

             //alert
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Success");
             alert.setHeaderText("Success");
             alert.setContentText("you have successfully added!");
             alert.showAndWait();

             //clear and show data again on table_BR
             Clear_BRBooks();
             showBorrowAndReturnBook();
         }
     }catch (Exception e){
         e.printStackTrace();
     }

   }



    /*end method inssert*/

//======================================================Mehtod Updates========================================================================


    /*Method Update*/
       //>>>>Method Update cho Readertable<<<<
    public void handleUpdateReader(){
        try{
            // check if any row is selected in the table?
            if(table_reader.getSelectionModel().getSelectedItem() == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please select the line to update");
                alert.showAndWait();

            }else{
                reader.setReaderID(field_ReaderID.getText());
                reader.setReaderName(field_ReaderName.getText());

                // datepicker _ birth
                LocalDate localDate = datePicker_Reader.getValue();
                java.sql.Date dateBirth = java.sql.Date.valueOf(localDate);
                reader.setBirth(dateBirth);

                //add combobox_gender
                String select = combobox_Gender.getValue();
                reader.setReaderGender(select);

                if(!isValidAddress(field_Address.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("wrong address, need to enter: name\n" +
                            "Road, Ward/Commune, District/District, Province/City");
                    alert.showAndWait();
                    return;
                }else {
                    reader.setReaderAddress(field_Address.getText());
                }

                String phonePattern = "\\d{10}"; // chuỗi số điện thoại phải có đúng 10 chữ số
                String phone = field_Phone.getText();
                if (!phone.matches(phonePattern)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid phone number. Please enter all 10 numbers!");
                    alert.showAndWait();
                    return;
                }else{
                    reader.setReaderPhone(field_Phone.getText());
                }

                reader.setReaderBarcode(field_Barcode.getText());

                //dateCreate and OutOfDate
                LocalDate localDate2 = datePicker_Reader2.getValue();
                java.sql.Date dateCreate = java.sql.Date.valueOf(localDate2);
                reader.setDateCreated(dateCreate);

                LocalDate localDate3 = datePicker_Reader3.getValue();
                java.sql.Date outOfDate = java.sql.Date.valueOf(localDate3);
                reader.setOutOfDate(outOfDate);


                if(!isValidPassword(field_ReaderPass.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Password must be at least 6-10 characters, have lowercase letters, and at least 1 special character");
                    alert.showAndWait();
                    return;
                }else {
                    reader.setReaderPass(field_ReaderPass.getText());
                }

                // update Image
                String imagePath;
                if (getData.path != null) {
                    // kiểm tra xem người dùng chọn ảnh mới chưa nếu rồi thì lây uri(path) đg dẫn đó thay thế path cũ
                    imagePath = getData.path;
                } else {
                    // Lấy đường dẫn ảnh hiện tại củ nếu không chọn đg dẫn ảnh mới để update
                    Connection connection = JDBCutil.connectJDBC();
                    String currentImagePath = null;
                    String readerId = field_ReaderID.getText(); // Lấy mã độc giả đang chỉnh sửa
                    String sql1 = "SELECT ReaderImage FROM Reader WHERE ReaderID = ?";
                     PreparedStatement prepare1 = connection.prepareStatement(sql1);
                    prepare1.setString(1, readerId);
                    ResultSet rs = prepare1.executeQuery();
                    if (rs.next()) {
                        currentImagePath = rs.getString("ReaderImage");

                    }
                    imagePath = currentImagePath;
                }
                reader.setReaderImg(imagePath);

                //combobox status
                String select2 = combobox_Status.getValue();
                reader.setReaderStatus(select2);

                adminService.Update_Reader(reader);

                // hiển thị thông báo thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully updated!");
                alert.showAndWait();

                // xóa dl vừa điền và showw ra tabelview ngay không cần refesh lại
                Clear_ReaderTable();
                showReaderTable();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
       //>>end<<


     //>>Mehhod Update<<//
    public void handleUpdateBookShelf(){
        try{
            // text codition, seen user have selected row or no?
            if(table_bookShelf.getSelectionModel().getSelectedItem() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please don't leave it blank!");
                alert.showAndWait();
            }else{
                bookShelf.setBoShelfID(field_bookShelfID.getText());
                bookShelf.setBoShelfTitle(field_bookShelfTittle.getText());

                adminService.Update_BookShelf(bookShelf);

                //success Message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully updated!");
                alert.showAndWait();

                //update none!-> clear and show tableview again.
                Clear_BookShelf();
                showBookShelf();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // method Update_Publisher

   public void Update_Publisher(){
        try{
            // text codition seen have user slected row or no?
            if(table_Publisher.getSelectionModel().getSelectedItem() == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Please select the line to update!");
                alert.showAndWait();

            }else{
                publisher.setPublisherID(field_PubID.getText());
                publisher.setPublisherName(field_PubName.getText());
                publisher.setPublisherPhone(field_PubPhone.getText());
                publisher.setPublisherAddress(field_PubAddress.getText());

                adminService.Update_Publisher(publisher);

                // Alert messege success!
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully updated!");
                alert.showAndWait();

                // clear and show dataon atble_Publisher again
                Clear_Publisher();
                showPublisher();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
   }


   //method handleUpdateAuthor
   public void handleUpdateAuthor(){
        try{
            if(table_Author.getSelectionModel().getSelectedItem() == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please select the line to update!");
                alert.showAndWait();

            }else{
                author.setAuthorID(field_AuhtorID.getText());
                author.setAuthorName(field_AuthorName.getText());
                author.setAuthorSubject(field_AuthorSubject.getText());
                author.setDescription(textArea_Description.getText());

                adminService.Update_Author(author);

                //success Message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully updated!");
                alert.showAndWait();

                //show and clear data on tableAuthor
                Clear_Author();
                showAuthor();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
   }





    //actiobe event btn_UpdateBooks
    public void handleUpdateBooks(){
        try{
            if(table_Book.getSelectionModel().getSelectedItem() == null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please select the line to update!");
                alert.showAndWait();

            }else{
                book_view.setBook_ID(field_BookID.getText());

                //combobox CatalogID of Books
                String selectcataID = (String)combobox_Book_CatalogID.getValue();
                book_view.setCatalog_ID(selectcataID);

                //combobox AuthorID of Books
                String selectAuID =(String) combobox_Book_AuthorID.getValue();
                book_view.setAuthor_ID(selectAuID);

                //combobox PubID of Books
                String selectPubID = (String) combobox_Book_PubID.getValue();
                book_view.setPublisher_ID(selectPubID);

                book_view.setBook_Title(field_BookTitle.getText());
                book_view.setBook_Price(field_BookPrice.getText());
                book_view.setBook_Amount(Integer.parseInt(field_BookAmount.getText()));
                book_view.setBook_BarCode(field_Book_BarCode.getText());

                //handle update Image of Books
                String imagePath;
                if (getData.path != null) {
                    // kiểm tra xem người dùng chọn ảnh mới chưa nếu rồi thì lây uri(path) đg dẫn đó thay thế path cũ
                    imagePath = getData.path;
                } else {
                    // Lấy đường dẫn ảnh hiện tại củ nếu không chọn đg dẫn ảnh mới để update
                    Connection connection = JDBCutil.connectJDBC();
                    String currentImagePath = null;
                    String imageBookID = field_BookID.getText(); // Lấy mã độc giả đang chỉnh sửa
                    String sql = "SELECT BookImg FROM Book_view WHERE BookID = ?";
                    PreparedStatement prepare = connection.prepareStatement(sql);
                    prepare.setString(1, imageBookID);
                    ResultSet rs = prepare.executeQuery();
                    if (rs.next()) {
                        currentImagePath = rs.getString("BookImg");

                    }
                    imagePath = currentImagePath;
                }
                book_view.setBook_Img(imagePath);


                //combobox EmpID of Books
                String selectEmpID = (String) combobox_Book_EmpID.getValue();
                book_view.setEmp_ID(selectEmpID);

                //datePicker dateAddes of Books
                LocalDate localDate_DateAdd = datePicker_Book.getValue();
                java.sql.Date dateAD = java.sql.Date.valueOf(localDate_DateAdd);
                book_view.setDate_Add(dateAD);

                //combobox Activity
                String selectActivity = (String) combobox_BookActivity.getValue();
                book_view.setAcTivity(selectActivity);

                adminService.Update_Books(book_view);

                //alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully updated!");
                alert.showAndWait();

                //clear and show data on table_Books again!
                Clear_Books();
                showBook();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //method update BRBooks
    public  void handleUpdateBRBooks(){
        try {
            if (table_BR.getSelectionModel().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data you need to update!");
                alert.showAndWait();
            } else {
                //update combobox_BRBookID
                String brBookID = (String) combobox_BRBookID.getValue();
                BRBooks.setBookID(brBookID);

                //update combobox_BRReaderID
                String brReaderID = (String) combobox_BRReaderID.getValue();
                BRBooks.setReaderID(brReaderID);

                //update datepicker_BRRetdate
                LocalDate local_BRRetDate = datepicker_BREndDate.getValue();
                java.sql.Date BR_RetDate = java.sql.Date.valueOf(local_BRRetDate);
                BRBooks.setRetdate(BR_RetDate);

                //update Combobox_BRStatus
                String status = (String) combobox_BRStatus.getValue();
                BRBooks.setBorrStatus(status);

                adminService.Update_BRBooks(BRBooks);

                //alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully recalled");
                alert.showAndWait();

                //clear and show data again on table_BR
                Clear_BRBooks();
                showBorrowAndReturnBook();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //method update
    public void handleUpdate_RB(){
        try{
            if(table_RegisterBorrowed.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please select the line to update!");
                alert.showAndWait();
            }else{
                //combobox_DisID
                String disID = (String) combobox_RB_DisID.getValue();
                RBorrow.setDisID(disID);

                RBorrow.setDescription(textArrea_RB_Description.getText());

                //update combobox_RB_Activity
                String activity = (String) combobox_RB_Activity.getValue();
                RBorrow.setActivity(activity);

                adminService.Update_RB(RBorrow);

                //alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("you have successfully updated!");
                alert.showAndWait();

                //clear and show data again on table_RB
                Clear_RB();
                showRetristersBorrowed();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*end Method Update*/
/*=======================================================Method Search===========================================================================*/
  // actiont event for btn_SearchReader
    public void handleSearchReader(ActionEvent event){
        if(event.getSource()==btn_SearchReader){
            String maSearch = field_SearchReader.getText();
            listShowActiveData = adminService.Search_Reader(maSearch);
            if (listShowActiveData != null && !listShowActiveData.isEmpty()) {
                ObservableList<Reader> data = FXCollections.observableArrayList(listShowActiveData);
                table_reader.setItems(data);
            } else if(listShowActiveData.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your search text!");
                alert.showAndWait();
            }
        }
    }

    //setOnKeyReleased() to handle(xử lý) keypress(nhấn) and key release(thả phim) events for btn_SearchReader
    public void handleSearchReader_forField(){
        String maSearch = field_SearchReader.getText();
        if (maSearch.isEmpty()) {
            listShowActiveData = adminService.getAllReaders();
        }
        ObservableList<Reader> data = FXCollections.observableArrayList(listShowActiveData);
        table_reader.setItems(data);
    }



    //Actionevent for btn_SearchBooks;
    public void handleSearchBooks(ActionEvent event){
        if(event.getSource()==btn_SearchBooks){
            String maSearch = field_SearchBooks.getText();
            listTableBooks = adminService.Search_Books(maSearch);
            if (listTableBooks != null && !listTableBooks.isEmpty()) {
                ObservableList<Book_view> data = FXCollections.observableArrayList(listTableBooks);
                table_Book.setItems(data);
            } else if(listTableBooks.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your search text!");
                alert.showAndWait();
            }
        }
    }

    //setOnKeyReleased() to handle(xử lý) keypress(nhấn) and key release(thả phim) events for btn_SearchBooks
    public void handleSearchBooks_forField(){
        String maSearch = field_SearchBooks.getText();
        if (maSearch.isEmpty()) {
            listTableBooks = adminService.getAllBooks();
        }
        ObservableList<Book_view> data = FXCollections.observableArrayList(listTableBooks);
        table_Book.setItems(data);
    }



    /*=====================================================Method clean, Refeshs===================================================================================*/

    /*Method refesh-clear dl tren giao dien khong xoa di*/

    // method clear, refesh dât of ReaderTable
      public void Clear_ReaderTable(){
          field_ReaderID.setText("");
          field_ReaderName.setText("");
          datePicker_Reader.setValue(null);

          combobox_Gender.getSelectionModel().clearSelection(); // xóa lựa chọn
          combobox_Gender.setPromptText("Choose book code!"); // thiết lập lại promptText

          field_Address.setText("");
          field_Phone.setText("");
          field_Barcode.setText("");
          datePicker_Reader2.setValue(null);
          datePicker_Reader3.setValue(null);
          field_ReaderPass.setText("");
          addImage_Reader.setImage(null);
    }

    // method refesh button Reader
    public void Refesh_readerTable(){
        Clear_ReaderTable();
        showReaderTable();
    }

    // make method clear button btn_clearBookShelf
    public void Clear_BookShelf(){
        field_bookShelfID.setText("");
        field_bookShelfTittle.setText("");
    }
    //method refesh btn_refeshBookShelf
    public void Refesh_BookShelf(){
          Clear_BookShelf();
          showBookShelf();
    }

    //method clear btn_clearCatalog
    public void Clear_Catalog(){
          field_CatalogID.setText("");
          field_CatalogTitle.setText("");
          combobox_BoshelfID.setValue(null);
    }
    //method reffesh catalog
    public void Refesh_Catalog(){
          Clear_Catalog();
          showCatalog();
          combobox_BoshelfID.setItems(adminService.loadComboboxCatalog());
    }

    //method Clear_Publisher
    public void Clear_Publisher(){
          field_PubID.setText("");
          field_PubName.setText("");
          field_PubPhone.setText("");
          field_PubAddress.setText("");
    }

    //method refesh_Publisher
    public void Refesh_Publisher(){
          Clear_Publisher();
          showPublisher();
    }

    //method clear_Atuhor
    public void Clear_Author(){
          field_AuhtorID.setText("");
          field_AuthorName.setText("");
          field_AuthorSubject.setText("");
          textArea_Description.setText("");
    }

    //methode refesh_Author
    public void Refesh_Author(){
          Clear_Author();
          showAuthor();
    }


    //method Clear_Book
    public void Clear_Books(){
          field_BookID.setText("");
          combobox_Book_CatalogID.setValue(null);
          combobox_Book_AuthorID.setValue(null);
          combobox_Book_PubID.setValue(null);
          field_BookTitle.setText("");
          field_BookPrice.setText("");
          field_BookAmount.setText("");
          field_Book_BarCode.setText("");
          add_BookImg.setImage(null);
          combobox_Book_EmpID.setValue(null);
          datePicker_Book.setValue(null);
          combobox_BookActivity.setValue(null);
    }

    //method Refesh_Books
    public void Refesh_Books(){
          Clear_Books();
          showBook();
    }


    //method Clear_BRBooks
    public void Clear_BRBooks(){
          combobox_BRBookID.setValue(null);
          combobox_BRReaderID.setValue(null);
          datepicker_BRStartdate.setValue(null);
          datepicker_BREndDate.setValue(null);
          combobox_BRStatus.setValue(null);
          addImage_BR.setImage(null);
    }

    //Clear books on pane BRBooks
    public void Clear_BRBooks2(){
        field_BRBookID.setText("");
        field_BRBookTitle.setText("");
        field_BRBookPrice.setText("");
        field_BRBookNum.setText("");
        combobox_BRBookID.setValue(null);
        addImage_BR.setImage(null);
    }

    //method Reffesh BRBooks
    public void Refesh_BRBooks(){
          Clear_BRBooks();
          showBorrowAndReturnBook();
    }
    //method Refesh_books of Pane BR_Books
   public void Refesh_BRBooks2(){
        Clear_BRBooks2();
        showBRBooks();
    }

    //method Clear_RB
    public void Clear_RB(){
          combobox_RB_DisID.setValue(null);
          combobox_RB_RID.setValue(null);
          combobox_RBByShift.setValue(null);
          datepicker_RB_DisDate.setValue(null);
          textArrea_RB_Description.setText("");
          combobox_RB_Activity.setValue(null);
    }
    //method Refesh_RB
    public void Refesh_RB(){
          Clear_RB();
          showRetristersBorrowed();
    }

    /*end Method refesh-clear*/

/*=============================================== thiet lap chug combobox, datePicker=========================================*/
    // thiết lập các trường items để sổ ra khi click vào combobox
    ObservableList<String> typeList = FXCollections.observableArrayList("Male", "Female");

    // thiet lap combox cho ReaderStatus
    ObservableList<String> typeList2= FXCollections.observableArrayList("Active","stop working");

    //thiet lap combobox cho Activity ò Pane Books
    ObservableList<String> typeList03 = FXCollections.observableArrayList("Available", "Borrowed", "Overdue", "Lost", "Damaged", "Withdrawn");

    //set combobox for Status of Borrow and Return Books
   ObservableList<String> getTypeList04 = FXCollections.observableArrayList("Borrowed", "Returned", "Overdue", "Renewal");

   //set data combobox_RBByShift
    ObservableList<String> typeList_RBByShift = FXCollections.observableArrayList("Shift 1: 7:30AM - 9:30AM", "Shift 2: 9:40AM - 11:40AM","Shift 3: 11:50AM - 13h:50PM"
   ,"Shift 4: 14:00PM - 16:00PM", "Shift 5: 16:10PM - 18h10PM", "Shift 6: 18h20PM - 20h20PM");

    //set data combobox_RBActivity
    ObservableList<String>typeList_RB_Activity = FXCollections.observableArrayList("Pending","Cancelled","Processed","Returned");

    //thiết lập định dạng cho datepicker
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



    //===========================================Method Initialize off class Initialiable==========================================================================

    /*
     * Trong JavaFX, Initializable là một interface được sử dụng để khởi tạo các thành phần giao diện người dùng sau khi chúng đã được tạo ra.
     *  Interface này chứa một phương thức duy nhất initialize() được gọi khi tất cả các thành phần của giao diện người dùng đã được tạo.Khi một
     * đối tượng của một lớp điều khiển (controller class) được tạo, nó được liên kết với một tệp FXML và được tạo ra bằng cách sử dụng
     * FXMLLoader. Sau khi tất cả các thành phần được tạo ra, phương thức initialize() được gọi. thực hiện các thao tác khởi tạo trước khi cửa sổ hoặc
     *  màn hình được hiển thị cho người dùng. Bên trong phương thức initialize(), bạn có thể thực hiện các thao tác khởi tạo đối tượng, thiết lập dữ liệu
     *  ban đầu, gắn các xử lý sự kiện và các thao tác khởi tạo khác.
     * */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /* Tạo StringConverter để chuyển đổi giá trị DatePicker thành chuỗi theo định dạng yyyy-MM-dd*/
           javafx.util.StringConverter<LocalDate> converter = new  javafx.util.StringConverter<LocalDate>() {
                    @Override
                    public String toString(LocalDate date) {
                        if (date != null) {
                            return dateFormatter.format(date);
                        } else {
                            return "";
                        }
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        if (string != null && !string.isEmpty()) {
                            return LocalDate.parse(string, dateFormatter);
                        } else {
                            return null;
                        }
                    }
         };


        // Đặt StringConverter cho DatePicker
                datePicker_Reader.setConverter(converter);
                datePicker_Reader2.setConverter(converter);
                datePicker_Reader3.setConverter(converter);
                datePicker_Book.setConverter(converter);
                datepicker_BRStartdate.setConverter(converter);
                datepicker_BREndDate.setConverter(converter);
                datepicker_RB_DisDate.setConverter(converter);
        /*kết thúc chuyển đổi datePicker*/


        /*load showReaderTable() đưa dl lên table,,,method initilize() gọi showReaderTable() và thực hiện các thao tác khởi tạo
        trước khi cửa sổ hoặc màn hình được hiển thị cho người dùng*/
        showReaderTable();
        //add method show tableView bookShelf
        showBookShelf();
        //add method showTablecatalog
        showCatalog();
        //add showPublisher
        showPublisher();
        //add method showAuthor
        showAuthor();
        //add method showBooks
        showBook();
        //add show data showDiscussionRoom on table_DisRoom
        showDiscussionRoom();
        //add showBRBooks
        showBRBooks();
        //add showBRBooks
        showBorrowAndReturnBook();
        //add showRetristersBorrowed()
        showRetristersBorrowed();


        /*combobox has for loop*/
        //set show ComboboxCatalog of pane CatalogOfBook
       combobox_BoshelfID.setPromptText("choose book code");
       combobox_BoshelfID.setItems(adminService.loadComboboxCatalog());
       //set show combobox_Book_CatalogID of pane Book
        combobox_Book_CatalogID.setPromptText("Select type code!");
        combobox_Book_CatalogID.setItems(adminService.loadCombobox_CatalogID());
        //set show combobox_Book_AuthorID of pane Book
        combobox_Book_AuthorID.setPromptText("Select author code!");
        combobox_Book_AuthorID.setItems(adminService.loadCombobox_AuthorID());
        //set show combobox_Book_PubID of pane Book
        combobox_Book_PubID.setPromptText("Select Manufacturer!");
        combobox_Book_PubID.setItems(adminService.loadCombobox_PubID());
        //set show combobox_Book_EMpID
        combobox_Book_EmpID.setPromptText("Select employee code!");
        combobox_Book_EmpID.setItems(adminService.loadCombobox_EmpID());
        //set loop combobox_BRBookID
        combobox_BRBookID.setPromptText("choose book code");
        combobox_BRBookID.setItems(adminService.loadCombobox_BookID());
        //set combobox_BRReaderID
        combobox_BRReaderID.setPromptText("Choose Reader code");
        combobox_BRReaderID.setItems(adminService.loadCombobox_ReaderID());
        //set combobox_RB_DiSID
        combobox_RB_DisID.setPromptText("select room code");
        combobox_RB_DisID.setItems(adminService.loadCombobox_RB_DisID());
        //set combobox_RB_ReaderID
        combobox_RB_RID.setPromptText("choose reader code");
        combobox_RB_RID.setItems(adminService.loadCombobox_ReaderID());




        /*combobox default*/
        // add combobox_ReaderGender
        combobox_Gender.setPromptText("Choose Gender");
        combobox_Gender.setItems(typeList);
        //add combox_readerStatus
        combobox_Status.setPromptText("Choose Status");
        combobox_Status.setItems(typeList2);
        //add combobox_BôkActivity
        combobox_BookActivity.setPromptText("Choose Statusi");
        combobox_BookActivity.setItems(typeList03);
        //add combobox_BRStatus
        combobox_BRStatus.setPromptText("Choose Status");
        combobox_BRStatus.setItems(getTypeList04);
        //add combobox_RBByShift
        combobox_RBByShift.setPromptText("choose a time");
        combobox_RBByShift.setItems(typeList_RBByShift);
        //add combobox_RB_Activity
        combobox_RB_Activity.setPromptText("Select Activity!");
        combobox_RB_Activity.setItems(typeList_RB_Activity);



    }
/*=============================================end method initialize()===================================================================*/

/*======================Method COndition support for Alert to method insert, update..when  all method wrong or emty!=====================*/
    //method alert: wrong date
// kiểm tra ngày nhập vào có đúng định dạng yyyy-MM-dd hay không
        //public static boolean isValidDate(String dateStr) {
        //    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //    sdf.setLenient(false);
        //    try {
        //        sdf.parse(dateStr);
        //    } catch (ParseException e) {
        //        return false;
        //    }
        //    return true;
        //}

    //method alert: wrong Address
    public static boolean isValidAddress(String address) {
        String[] addressParts = address.split(",");
        if (addressParts.length <4) {
            return false;
        }
        String street = addressParts[0].trim();
        String ward = addressParts[1].trim();
        String district = addressParts[2].trim();
        String city = addressParts[3].trim();
        if (street.isEmpty() || ward.isEmpty() || district.isEmpty() ||  city.isEmpty()) {
            return false;
        }
        return true;
    }


    //method alert: wrong Pass
    // kiểm tra field pass có đủ ký tự in thường, in hoa và ký tự đặt biệt (@,#) hay không
    public static boolean isValidPassword(String password) {
        // kiểm tra độ dài của password có từ 8 đến 20 ký tự
        if (password.length() < 6 || password.length() > 10) {
            return false;
        }
        // kiểm tra password có chứa ký tự in thường
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        // kiểm tra password có chứa ký tự in hoa
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // kiểm tra password có chứa ký tự đặt biệt (@,#)
        if (!password.matches(".*[@#].*")) {
            return false;
        }
        return true;
    }






}
/*end program*/
