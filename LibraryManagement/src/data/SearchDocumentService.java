package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Books;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchDocumentService extends JDBCutil{

    public ObservableList<Books> searchBook(String content){
        ObservableList<Books> resultList = FXCollections.observableArrayList();
        String addContent = '%'+content+'%';
        try{
            String sql = "SELECT BookBarCode, BookTitle, BookAmount FROM Books WHERE BookTitle like ?";
            PreparedStatement preState = connectJDBC().prepareStatement(sql);
            preState.setString(1, addContent);
            ResultSet rel = preState.executeQuery();

            while(rel.next()){
                Books books = new Books();
                books.setBookBarCode(rel.getString(1));
                books.setBookTitle(rel.getString(2));
                books.setBookAmount(rel.getInt(3));
                resultList.add(books);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return resultList;
    }
}
