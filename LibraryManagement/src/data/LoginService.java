package data;

import model.Employee;
import model.Reader;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService extends JDBCutil{
    public Employee EmployeeLogin(String username, String pass) throws NoSuchAlgorithmException {
        Employee account = null;
        try{
            System.out.println(username + " " +pass);
            String encrypted = Encrypt(pass);
            String sql = "SELECT * FROM Employee WHERE EmpID=? AND EmpPass=? AND EmpStatus in('Active', 'probation','Trainning')";
            PreparedStatement preStatement = connectJDBC().prepareStatement(sql);
            preStatement.setString(1, username);
            preStatement.setString(2,encrypted);
            ResultSet result = preStatement.executeQuery();
            if (result.next()){
                account = new Employee();
                account.setEmpID(result.getString(1));
                account.setEmpPass(result.getString(2));
            }
            result.close();
            preStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    //===================== Reader Login ================================
    public Reader ReaderLogin(String username, String password){
        Reader reader = null;
        try{
            System.out.println(username + " " + password);

            String encrypted = Encrypt(password);
            String sql = "SELECT ReaderPass FROM Reader WHERE ReaderID = ? and ReaderPass = ?";
            PreparedStatement preStatment = connectJDBC().prepareStatement(sql);
            preStatment.setString(1, username);
            preStatment.setString(2,encrypted);
            ResultSet resultSet = preStatment.executeQuery();
            if(resultSet.next()){
                reader = new Reader();
                reader.setReaderPass(resultSet.getString(1));
            }
            resultSet.close();
            preStatment.close();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return reader;
    }

    //  SH-256 Encrypt
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
}
