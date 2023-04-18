package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// xay dựng các method load table và insert, update cho forrm chủ Admin.fxml
public class AdminService extends JDBCutil{

    /*==============================*Method login Admin====================================*/
    public Employee AdminLogin(String username, String pass) throws NoSuchAlgorithmException{
        Employee account = null;
        try{
            String sql = "SELECT * FROM Employee WHERE EmpID=? AND EmpPass=? AND DepID ='DP01'";
            PreparedStatement preStatement = connectJDBC().prepareStatement(sql);
            System.out.println(username + " " + pass);
            String encrypted = Encrypt((pass));
            preStatement.setString(1, username);
            preStatement.setString(2,encrypted);
            ResultSet result = preStatement.executeQuery();
            if (result.next()){
                account = new Employee();
                account.setEmpID(result.getString(1));
                account.setEmpPass(result.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }


    public String Encrypt(String userPass) {
        MessageDigest sha = null;
        String hashedPassword = null;

        try {
            // Create MessageDigest object with SHA-256 algorithm
            sha = MessageDigest.getInstance("SHA-256");
            sha.update(userPass.getBytes());  // Provide user pass for MessageDigest and Hash it
            byte[] byteData = sha.digest();

            // Convert Hash string to Hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(String.format("%02x", b));  // ensure every byte have 2 character if it not enough which it will add.
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }



    /*===================================table reader=======================================================================*/

    // load table reader with condition
    public ObservableList<Reader> getActiveReaders(){
        ObservableList<Reader> ListData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Reader where ReaderStatus ='Active'";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while(rs.next()){
                Reader reader = new Reader();
                reader.setReaderID(rs.getString("ReaderID"));
                reader.setReaderName(rs.getString("ReaderName"));
                reader.setBirth(rs.getDate("DateOfBirth"));
                reader.setReaderGender(rs.getString("ReaderGender"));
                reader.setReaderAddress(rs.getString("ReaderAddress"));
                reader.setReaderPhone(rs.getString("ReaderPhone"));
                reader.setReaderBarcode(rs.getString("ReaderBarCode"));
                reader.setDateCreated(rs.getDate("DateCreated"));
                reader.setOutOfDate(rs.getDate("OutOfDate"));
                reader.setReaderPass(rs.getString("ReaderPass"));
                reader.setReaderImg(rs.getString("ReaderImage"));
                reader.setReaderStatus(rs.getString("ReaderStatus"));

                ListData.add(reader);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListData;
    }
//load table reader all
    public ObservableList<Reader> getAllReaders(){
        ObservableList<Reader> ListData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Reader";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while(rs.next()){
                Reader reader = new Reader();
                reader.setReaderID(rs.getString("ReaderID"));
                reader.setReaderName(rs.getString("ReaderName"));
                reader.setBirth(rs.getDate("DateOfBirth"));
                reader.setReaderGender(rs.getString("ReaderGender"));
                reader.setReaderAddress(rs.getString("ReaderAddress"));
                reader.setReaderPhone(rs.getString("ReaderPhone"));
                reader.setReaderBarcode(rs.getString("ReaderBarCode"));
                reader.setDateCreated(rs.getDate("DateCreated"));
                reader.setOutOfDate(rs.getDate("OutOfDate"));
                reader.setReaderPass(rs.getString("ReaderPass"));
                reader.setReaderImg(rs.getString("ReaderImage"));
                reader.setReaderStatus(rs.getString("ReaderStatus"));

                ListData.add(reader);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListData;
    }



    // insert table Reader
      public void Insert_Reader(Reader reader){
        Connection connection = JDBCutil.connectJDBC();
        try{
              String sql = "insert into Reader(ReaderID,ReaderName,DateOfBirth," +
                                "ReaderGender,ReaderAddress, ReaderPhone, ReaderBarCode," +
                      "DateCreated, OutOfDate,ReaderPass, ReaderImage, ReaderStatus) values(?,?,?,?,?,?,?,?,?,SHA2(?,256),?,?)";

                PreparedStatement prepare = connection.prepareStatement(sql);
                prepare.setString(1,reader.getReaderID());
                prepare.setString(2,reader.getReaderName());
                prepare.setDate(3,reader.getBirth());
                prepare.setString(4, reader.getReaderGender());
                prepare.setString(5, reader.getReaderAddress());
                prepare.setString(6,reader.getReaderPhone());
                prepare.setString(7,reader.getReaderBarcode());
                prepare.setDate(8,reader.getDateCreated());
                prepare.setDate(9,reader.getOutOfDate());
                prepare.setString(10, reader.getReaderPass());
                prepare.setString(11, reader.getReaderImg());
                prepare.setString(12,reader.getReaderStatus());

                int rs = prepare.executeUpdate();
                System.out.println(rs);

        }catch (Exception e){
            e.printStackTrace();
        }
      }

    // Update Table reader
      public void Update_Reader(Reader reader){
          Connection connection = JDBCutil.connectJDBC();
          try{
              String sql = "update Reader set ReaderName = ?, DateOfBirth = ?, ReaderGender = ?, ReaderAddress = ?, ReaderPhone = ?, ReaderBarCode = ?," +
                        " DateCreated = ?, OutOfDate = ?, ReaderPass= SHA2( ? ,256), ReaderImage = ?, ReaderStatus = ? WHERE ReaderID = ?";
              PreparedStatement prepare = connection.prepareStatement(sql);
              prepare.setString(1, reader.getReaderName());
              prepare.setDate(2, reader.getBirth());
              prepare.setString(3,reader.getReaderGender());
              prepare.setString(4,reader.getReaderAddress());
              prepare.setString(5,reader.getReaderPhone());
              prepare.setString(6,reader.getReaderBarcode());
              prepare.setDate(7, reader.getDateCreated());
              prepare.setDate(8,reader.getOutOfDate());
              prepare.setString(9, reader.getReaderPass());
              prepare.setString(10, reader.getReaderImg());
              prepare.setString(11,reader.getReaderStatus());
              prepare.setString(12,reader.getReaderID());

              int rs = prepare.executeUpdate();
              System.out.println(rs);
          }catch (Exception e){
              e.printStackTrace();
          }
      }

    /*======================================================Pane BookManage===============================================================*/
     /* sub pane BookShelf*/
      // load table_bookShelf
    public ObservableList<BookShelf> getAllBookShelf(){
        ObservableList<BookShelf> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from BookShelf";
            PreparedStatement prepare =conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                BookShelf bookShelf = new BookShelf();
                bookShelf.setBoShelfID(rs.getString("BoShelfID"));
                bookShelf.setBoShelfTitle(rs.getString("BoShelfTitle"));

                listData.add(bookShelf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    //insert table_BookShelf
    public void Inert_BookShelf(BookShelf bookShelf){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql ="insert into BookShelf(BoShelfID,BoShelfTitle) values(?,?)";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1,bookShelf.getBoShelfID());
            prepare.setString(2,bookShelf.getBoShelfTitle());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //method update bookshelf
    public void Update_BookShelf(BookShelf bookShelf){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "update BookShelf set BoShelfTitle =? where BoShelfID =?";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1,bookShelf.getBoShelfTitle());
            prepare.setString(2,bookShelf.getBoShelfID());
            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


/*==================================================pane cataLog of BookManager=========================================================*/
  //load tableView table_Catalog
    public ObservableList<CatalogOfBooks> getAllCatalog(){
        ObservableList<CatalogOfBooks> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from CatalogOfBooks";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                CatalogOfBooks catalogOfBooks = new CatalogOfBooks();
                catalogOfBooks.setCatalogID(rs.getString("CatalogID"));
                catalogOfBooks.setCatalogTitle(rs.getString("CatalogTitle"));
                catalogOfBooks.setBoShelfID(rs.getString("BoShelfID"));

                listData.add(catalogOfBooks);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }



    //load comboxbox for combobox_BoshelfID
    public ObservableList<String> loadComboboxCatalog(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
          String sql = "select BoShelfID from BookShelf ";
          PreparedStatement prepare = conect.prepareStatement(sql);
          ResultSet rs = prepare.executeQuery();
          while(rs.next()){
              listData.add(rs.getString("BoShelfID"));
          }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }


    //method Insert_Catalog
    public void Insert_Catalog(CatalogOfBooks catalog){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "insert into CatalogOfBooks( CatalogID,CatalogTitle, BoShelfID) values(?,?,?)";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1, catalog.getCatalogID());
            prepare.setString(2,catalog.getCatalogTitle());
            prepare.setString(3,catalog.getBoShelfID());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //method update catatlog
//    public void Update_Catalog(CatalogOfBooks catalog){
//        Connection connection = JDBCutil.connectJDBC();
//        try{
//            String sqp ="update CatalogOfBooks set ";
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }



/*===========================================Pane Puhlisher=======================================================================*/
  // method show data on tableview
    public ObservableList<Publisher> getAllPublisher(){
        ObservableList<Publisher> listPublisher = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Publisher";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Publisher publisher = new Publisher();
                publisher.setPublisherID(rs.getString("PublisherID"));
                publisher.setPublisherName(rs.getString("PublisherName"));
                publisher.setPublisherPhone(rs.getString("PublisherPhone"));
                publisher.setPublisherAddress(rs.getString("PublisherAddress"));

                listPublisher.add(publisher);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listPublisher;
    }

    //method insert_Publisher
   public void Insert_Publisher(Publisher publisher){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "insert into Publisher(PublisherID,PublisherName,PublisherPhone,PublisherAddress)" +
                    "values(?,?,?,?)";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1,publisher.getPublisherID());
            prepare.setString(2,publisher.getPublisherName());
            prepare.setString(3,publisher.getPublisherPhone());
            prepare.setString(4,publisher.getPublisherAddress());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
   }

   //method update_publisher
    public void Update_Publisher(Publisher publisher){
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "Update Publisher set PublisherName =?, PublisherPhone =?, PublisherAddress=?" +
                    "where PublisherID =?";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1,publisher.getPublisherName());
            prepare.setString(2,publisher.getPublisherPhone());
            prepare.setString(3,publisher.getPublisherAddress());
            prepare.setString(4,publisher.getPublisherID());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


/*======================================================pane Author==========================================*/

     // method load show data on table_Author
    public ObservableList<Author> getAllAuthor(){
        ObservableList<Author> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Author";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Author author = new Author();
                author.setAuthorID(rs.getString("AuthorID"));
                author.setAuthorName(rs.getString("AuthorName"));
                author.setAuthorSubject(rs.getString("AuthorSubject"));
                author.setDescription(rs.getString("Description"));

                listData.add(author);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    //method Insert_Author
    public void Insert_Author(Author author){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "insert into Author(AuthorID,AuthorName,AuthorSubject,Description)" +
                    "values(?,?,?,?)";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1,author.getAuthorID());
            prepare.setString(2,author.getAuthorName());
            prepare.setString(3,author.getAuthorSubject());
            prepare.setString(4,author.getDescription());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //method Update_Author
    public void Update_Author(Author author){
        Connection connection = JDBCutil.connectJDBC();
        try{
            String sql = "update Author set AuthorName =?, AuthorSubject =?, Description =?" +
                    "where AuthorID =? ";
            PreparedStatement prepare = connection.prepareStatement(sql);
            prepare.setString(1,author.getAuthorName());
            prepare.setString(2,author.getAuthorSubject());
            prepare.setString(3, author.getDescription());
            prepare.setString(4,author.getAuthorID());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



/*===========================================================Pane Book==================================================*/
    //method show data on table_View book
    public ObservableList<Book_view> getAllBooks_Condition(){
        ObservableList<Book_view> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Book_view where Activity in ('Available','Borrowed')";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Book_view book_view = new Book_view();
                book_view.setBook_ID(rs.getString("BookID"));
                book_view.setCatalog_ID(rs.getString("CatalogID"));
                book_view.setAuthor_ID(rs.getString("AuthorID"));
                book_view.setPublisher_ID(rs.getString("PublisherID"));
                book_view.setBook_Title(rs.getString("BookTitle"));
                book_view.setBook_Price(rs.getString("BookPrice"));
                book_view.setBook_Amount(rs.getInt("BookAmount"));
                book_view.setBook_BarCode(rs.getString("BookBarCode"));
                book_view.setBook_Img(rs.getString("BookImg"));
                book_view.setEmp_ID(rs.getString("EmpID"));
                book_view.setDate_Add(rs.getDate("DateAdded"));
                book_view.setAcTivity(rs.getString("Activity"));

                listData.add(book_view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }


    //method show data on table_Book for btn_ShowAllBook
    public ObservableList<Book_view> getAllBooks(){
        ObservableList<Book_view> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Book_view";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Book_view book_view = new Book_view();
                book_view.setBook_ID(rs.getString("BookID"));
                book_view.setCatalog_ID(rs.getString("CatalogID"));
                book_view.setAuthor_ID(rs.getString("AuthorID"));
                book_view.setPublisher_ID(rs.getString("PublisherID"));
                book_view.setBook_Title(rs.getString("BookTitle"));
                book_view.setBook_Price(rs.getString("BookPrice"));
                book_view.setBook_Amount(rs.getInt("BookAmount"));
                book_view.setBook_BarCode(rs.getString("BookBarCode"));
                book_view.setBook_Img(rs.getString("BookImg"));
                book_view.setEmp_ID(rs.getString("EmpID"));
                book_view.setDate_Add(rs.getDate("DateAdded"));
                book_view.setAcTivity(rs.getString("Activity"));

                listData.add(book_view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }






    // alls method actiont event load for combobox of pane book
    public ObservableList<String> loadCombobox_CatalogID(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select CatalogID from CatalogOfBooks";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs  = prepare.executeQuery();
            while (rs.next()){
                listData.add(rs.getString("CatalogID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }


    public ObservableList<String> loadCombobox_AuthorID(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select AuthorID from Author";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                listData.add(rs.getString("AuthorID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }


    public ObservableList<String> loadCombobox_PubID(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select PublisherID from Publisher";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                listData.add(rs.getString("PublisherID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<String> loadCombobox_EmpID(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select EmpID from Employee";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                listData.add(rs.getString("EmpID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }



    //method insert_Books
    public void Insert_Books(Book_view book_view){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "call insert_Book_view(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepare = conect.prepareStatement(sql);

            prepare.setString(1,book_view.getBook_ID());
            prepare.setString(2,book_view.getCatalog_ID());
            prepare.setString(3,book_view.getAuthor_ID());
            prepare.setString(4,book_view.getPublisher_ID());
            prepare.setString(5,book_view.getBook_Title());
            prepare.setString(6,book_view.getBook_Price());
            prepare.setInt(7,book_view.getBook_Amount());
            prepare.setString(8,book_view.getBook_BarCode());
            prepare.setString(9,book_view.getBook_Img());
            prepare.setString(10,book_view.getEmp_ID());
            prepare.setDate(11,book_view.getDate_Add());
            prepare.setString(12,book_view.getAcTivity());


            int rs = prepare.executeUpdate();
            System.out.println(rs);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    //method update Books
    public void Update_Books(Book_view book_view){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "call update_Book_view(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepare = conect.prepareStatement(sql);

            prepare.setString(1,book_view.getBook_ID());
            prepare.setString(2,book_view.getCatalog_ID());
            prepare.setString(3,book_view.getAuthor_ID());
            prepare.setString(4,book_view.getPublisher_ID());
            prepare.setString(5,book_view.getBook_Title());
            prepare.setString(6,book_view.getBook_Price());
            prepare.setInt(7,book_view.getBook_Amount());
            prepare.setString(8,book_view.getBook_BarCode());
            prepare.setString(9,book_view.getBook_Img());
            prepare.setString(10,book_view.getEmp_ID());
            prepare.setDate(11,book_view.getDate_Add());
            prepare.setString(12,book_view.getAcTivity());


            int rs = prepare.executeUpdate();
            System.out.println(rs);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

/*==========================================Pane DiscussionRoom===========================================================*/
  //method load data on table_DiscussionRoom
    public ObservableList<DiscussionRoom>getAllDisRoom(){
        ObservableList<DiscussionRoom>listData = FXCollections.observableArrayList();
        Connection connection = JDBCutil.connectJDBC();
        try{
            String sql = "select * from DiscussionRoom";
            PreparedStatement prepare = connection.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                DiscussionRoom disRoom = new DiscussionRoom();
                disRoom.setDisID(rs.getString("DisID"));
                disRoom.setDisName(rs.getString("DisName"));
                disRoom.setDisLocation(rs.getString("DisLocation"));
                disRoom.setDisStatus(rs.getString("DisStatus"));
                disRoom.setDescription(rs.getString("Description"));

                listData.add(disRoom);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }




/*=================================================Pane_RegisterBorrowed=============================*/
    //method load data on table_RegisterBorrowed has conditional
    public ObservableList<RetristersBorrowed> getAllRetristerBorrowed_Coditional(){
        ObservableList<RetristersBorrowed> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from RetristersBorrowed where Activity in ('Pending', 'Processed')";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while(rs.next()){
                RetristersBorrowed rb = new RetristersBorrowed();
                rb.setDisID(rs.getString("DisID"));
                rb.setReaderID(rs.getString("ReaderID"));
                rb.setBorrowedByShift(rs.getString("BorrowedByShift"));
                rb.setDisDate(rs.getDate("DisDate"));
                rb.setDescription(rs.getString("Description"));
                rb.setActivity(rs.getString("Activity"));

                listData.add(rb);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return  listData;
    }

    //method load data on table_RegisterBorrowed no conditional
    public ObservableList<RetristersBorrowed> getAllRetristerBorrowed(){
        ObservableList<RetristersBorrowed> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from RetristersBorrowed";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while(rs.next()){
                RetristersBorrowed rb = new RetristersBorrowed();
                rb.setDisID(rs.getString("DisID"));
                rb.setReaderID(rs.getString("ReaderID"));
                rb.setBorrowedByShift(rs.getString("BorrowedByShift"));
                rb.setDisDate(rs.getDate("DisDate"));
                rb.setDescription(rs.getString("Description"));
                rb.setActivity(rs.getString("Activity"));

                listData.add(rb);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }


    /*all method load combobox*/
    //method load combobox_RB_DisID
    public ObservableList<String> loadCombobox_RB_DisID(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select DisID from DiscussionRoom order by DisID";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                RetristersBorrowed RBorrow = new RetristersBorrowed();
                listData.add(rs.getString("DisID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }


    //method Update_RB(
    public void Update_RB(RetristersBorrowed RBorrow){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "update RetristersBorrowed set Description =?, Activity=? where DisID =?" ;
            PreparedStatement prepare = conect.prepareStatement(sql);

            prepare.setString(1,RBorrow.getDescription());
            prepare.setString(2,RBorrow.getActivity());
            prepare.setString(3,RBorrow.getDisID());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //method Update(Grant Premission-Recall)



/*================================================pane Borrow/Return=====================================================*/

    //method load combobox_BookID
    public ObservableList<String> loadCombobox_BookID(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select BookID from Books order by BookID";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Books books = new Books();
                listData.add(rs.getString("BookID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }

    //method load combobox_ReaderID
    public ObservableList<String> loadCombobox_ReaderID(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select ReaderID from Reader order by ReaderID";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Reader reader = new Reader();
                listData.add(rs.getString("ReaderID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  listData;
    }



    //method show data on table_BR (table of pane Borrow and Return Books) has codition
     public ObservableList<BorrowAndReturnBook> getAllBorrowAndReturnBook_Condition(){
        ObservableList<BorrowAndReturnBook> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from BorrowedAndReturnedBook where BorrStatus IN ('Borrowed', 'Renewal')";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                BorrowAndReturnBook br = new BorrowAndReturnBook();
                br.setBookID(rs.getString("BookID"));
                br.setReaderID(rs.getString("ReaderID"));
                br.setStartDate(rs.getDate("BorrStartDate"));
                br.setRetdate(rs.getDate("RetDate"));
                br.setBorrStatus(rs.getString("BorrStatus"));

                listData.add(br);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
     }


    //method show data on table_BR (table of pane Borrow and Return Books) no codition
    public ObservableList<BorrowAndReturnBook> getAllBorrowAndReturnBook(){
        ObservableList<BorrowAndReturnBook> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from BorrowedAndReturnedBook";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                BorrowAndReturnBook br = new BorrowAndReturnBook();
                br.setBookID(rs.getString("BookID"));
                br.setReaderID(rs.getString("ReaderID"));
                br.setStartDate(rs.getDate("BorrStartDate"));
                br.setRetdate(rs.getDate("RetDate"));
                br.setBorrStatus(rs.getString("BorrStatus"));

                listData.add(br);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    //method Insert BRBooks
    public void Insert_BRBooks(BorrowAndReturnBook BRBooks){
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "insert into BorrowedAndReturnedBook(BookID,ReaderID,BorrStartDate,RetDate,BorrStatus)" +
                    "values(?,?,?,?,?)";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1,BRBooks.getBookID());
            prepare.setString(2,BRBooks.getReaderID());
            prepare.setDate(3, (Date) BRBooks.getStartDate());
            prepare.setDate(4,(Date) BRBooks.getRetdate());
            prepare.setString(5,BRBooks.getBorrStatus());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //method Update_BRBooks
    public void Update_BRBooks(BorrowAndReturnBook BRBooks){
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "update BorrowedAndReturnedBook set RetDate =?, BorrStatus = ? where BookID=? and  ReaderID =?";

            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(3,BRBooks.getBookID());
            prepare.setString(4,BRBooks.getReaderID());
            prepare.setDate(1,(Date) BRBooks.getRetdate());
            prepare.setString(2,BRBooks.getBorrStatus());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




/*======================================================Method Search==============================================*/
  // method search_Reader

    public ObservableList<Reader> Search_Reader(String maSearch) {
        ObservableList<Reader> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try {
            String sql = "SELECT * FROM Reader WHERE ReaderID like ?";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1, "%" + maSearch + "%");
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                Reader reader = new Reader();

                reader.setReaderID(rs.getString("ReaderID"));
                reader.setReaderName(rs.getString("ReaderName"));
                reader.setBirth(rs.getDate("DateOfBirth"));
                reader.setReaderGender(rs.getString("ReaderGender"));
                reader.setReaderAddress(rs.getString("ReaderAddress"));
                reader.setReaderPhone(rs.getString("ReaderPhone"));
                reader.setReaderBarcode(rs.getString("ReaderBarCode"));
                reader.setDateCreated(rs.getDate("DateCreated"));
                reader.setOutOfDate(rs.getDate("OutOfDate"));
                reader.setReaderPass(rs.getString("ReaderPass"));
                reader.setReaderImg(rs.getString("ReaderImage"));
                reader.setReaderStatus(rs.getString("ReaderStatus"));

                listData.add(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }


    //method  Search_PaneBook
    public ObservableList<Book_view> Search_Books(String maSearch) {
        ObservableList<Book_view> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try {
            String sql = "SELECT * FROM Book_view WHERE BookID like ?";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1, "%" + maSearch + "%");
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                Book_view book_view = new Book_view();

                book_view.setBook_ID(rs.getString("BookID"));
                book_view.setCatalog_ID(rs.getString("CatalogID"));
                book_view.setAuthor_ID(rs.getString("AuthorID"));
                book_view.setPublisher_ID(rs.getString("PublisherID"));
                book_view.setBook_Title(rs.getString("BookTitle"));
                book_view.setBook_Price(rs.getString("BookPrice"));
                book_view.setBook_Amount(rs.getInt("BookAmount"));
                book_view.setBook_BarCode(rs.getString("BookBarCode"));
                book_view.setBook_Img(rs.getString("BookImg"));
                book_view.setEmp_ID(rs.getString("EmpID"));
                book_view.setDate_Add(rs.getDate("DateAdded"));
                book_view.setAcTivity(rs.getString("Activity"));

                listData.add(book_view);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }




// end adminservice
}
