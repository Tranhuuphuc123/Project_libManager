package data;

import model.Reader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileService extends JDBCutil{

    public Reader inforReader(String readerID){
        Reader reader = null;
        try{
            String sql = " SELECT ReaderName, ReaderID, DateOfBirth, ReaderGender, DateCreated, OutOfDate, " +
                    "ReaderAddress, ReaderPhone FROM Reader WHERE ReaderID = ? ";
            PreparedStatement preState = connectJDBC().prepareStatement(sql);
            preState.setString(1, readerID);
            ResultSet resultSet = preState.executeQuery();

            if (resultSet.next()){
                reader = new Reader();
                reader.setReaderName(resultSet.getString(1));
                reader.setReaderID(resultSet.getString(2));
                reader.setBirth(resultSet.getDate(3));
                reader.setReaderGender(resultSet.getString(4));
                reader.setDateCreated(resultSet.getDate(5));
                reader.setOutOfDate(resultSet.getDate(6));
                reader.setReaderAddress(resultSet.getString(7));
                reader.setReaderPhone(resultSet.getString(8));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return reader;
    }
}
