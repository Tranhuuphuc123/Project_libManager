package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BorrowAndReturnBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnedDocumentService extends JDBCutil{

    public ObservableList<BorrowAndReturnBook> getReturnedDocument(String readerID){
        ObservableList<BorrowAndReturnBook> returnList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT bk.BookBarCode, bk.BookTitle, brr.BorrStartDate, brr.RetDate" +
                    " FROM Reader re join BorrowedAndReturnedBook brr" +
                    " on re.ReaderID=brr.ReaderID join Books bk on brr.BookID = bk.BookID" +
                    "  WHERE re.ReaderID = ? AND BorrStatus = 'Returned' ;";
            PreparedStatement preStatement = connectJDBC().prepareStatement(sql);
            preStatement.setString(1,readerID);
            ResultSet resultSet = preStatement.executeQuery();

            while (resultSet.next()){
                BorrowAndReturnBook brr = new BorrowAndReturnBook();
                brr.setBookID(resultSet.getString(1)); //barcode book
                brr.setReaderID(resultSet.getString(2)); // title book
                brr.setStartDate(resultSet.getDate(3));
                brr.setRetdate(resultSet.getDate(4));
                returnList.add(brr);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return returnList;
    }
}
