package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BorrowAndReturnBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RenewDocumentService extends JDBCutil{
    public ObservableList<BorrowAndReturnBook> getRenewalDocument(String readerID) {

        ObservableList<BorrowAndReturnBook> renewList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT bk.BookBarCode, bk.BookTitle, brr.BorrStartDate, brr.RetDate" +
                    " FROM Books bk join BorrowedAndReturnedBook brr on bk.BookID = brr.BookID" +
                    " WHERE brr.ReaderID = ? AND brr.BorrStatus = 'Borrowed';";
            PreparedStatement preStatement = connectJDBC().prepareStatement(sql);
            preStatement.setString(1,readerID);
            ResultSet resultSet = preStatement.executeQuery();

            while (resultSet.next()){
                BorrowAndReturnBook obj = new BorrowAndReturnBook();
                obj.setBookID(resultSet.getString(1));
                obj.setReaderID(resultSet.getString(2));
                obj.setStartDate(resultSet.getDate(3));
                obj.setRetdate(resultSet.getDate(4));
                renewList.add(obj);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return renewList;
    }

    public void updateRenewal(String readerID, String bookID){

        String sql = "UPDATE BorrowedAndReturnedBook SET RetDate = DATE_ADD(RetDate, INTERVAL 7 DAY)" +
                     " WHERE ReaderID = ? AND BookID = (SELECT BookID  FROM Books Where BookBarCode= ?)";

        try{
            PreparedStatement preStatement = connectJDBC().prepareStatement(sql);
            preStatement.setString(1, readerID);
            preStatement.setString(2,bookID);
            System.out.println("UPDATE THANH CONG! => " + preStatement.executeUpdate());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
