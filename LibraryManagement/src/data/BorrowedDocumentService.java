package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BorrowAndReturnBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowedDocumentService extends JDBCutil{
    public ObservableList<BorrowAndReturnBook> getBorrowedDocument(String readerID){
        ObservableList<BorrowAndReturnBook> borrowList = FXCollections.observableArrayList();
        System.out.println("Gia tri cua readerID: " + readerID);
        try{
            String sql = "SELECT bk.BookTitle, bk.BookBarCode, brr.BorrStartDate, brr.RetDate, brr.BorrStatus" +
                    " FROM Reader re join BorrowedAndReturnedBook brr" +
                    " on re.ReaderID=brr.ReaderID join Books bk on brr.BookID = bk.BookID" +
                    " WHERE re.ReaderID=? AND BorrStatus IN('Borrowed', 'Renewed')";
            PreparedStatement preStatement = connectJDBC().prepareStatement(sql);
            preStatement.setString(1, readerID);
            ResultSet resultSet = preStatement.executeQuery();
            while(resultSet.next()){
                BorrowAndReturnBook obj = new BorrowAndReturnBook();
                obj.setBookID(resultSet.getString(1));
                obj.setReaderID(resultSet.getString(2));
                obj.setStartDate(resultSet.getDate(3));
                obj.setRetdate(resultSet.getDate(4));
                obj.setBorrStatus(resultSet.getString(5));
                borrowList.add(obj);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return borrowList;
    }
}
