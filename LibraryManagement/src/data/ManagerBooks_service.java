package data;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import model.ManagesBook;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerBooks_service extends JDBCutil {
    public ObservableList<String> loadCombobox_EmpID(){
        ObservableList<String>listData = FXCollections.observableArrayList();
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "select BookID from ManagesBook";
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                listData.add(rs.getString("BookID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    public ObservableList<String> loadCombobox_BookID(){
        ObservableList<String>listData = FXCollections.observableArrayList();
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "select BookID from ManagesBook";
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                listData.add(rs.getString("BookID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    public ObservableList<ManagesBook>getAllmanagerbook(){
        ObservableList<ManagesBook> listData = FXCollections.observableArrayList();
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from ManagesBook";
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                ManagesBook managerbook = new ManagesBook();
                managerbook.setBookID(rs.getString("EmpID"));
                managerbook.setBookID(rs.getString("BookID"));
                managerbook.setDateAdd(rs.getDate("DateAdded"));
                managerbook.setActivity(rs.getString("Activity"));

                listData.add(managerbook);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    //method load data ontable has coditional
    public  ObservableList<ManagesBook> getAllmanagerbook_condition(){
        ObservableList<ManagesBook> listData = FXCollections.observableArrayList();
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from ManagesBook where EmpID )";
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                ManagesBook managerbook = new ManagesBook();
                managerbook.setBookID(rs.getString("EmpID"));
                managerbook.setBookID(rs.getString("BookID"));
                managerbook.setDateAdd(rs.getDate("DateAdded"));
                managerbook.setActivity(rs.getString("Activity"));

                listData.add(managerbook);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }




    //----------------------------------------------------------------------------------------------------
    //method insert EmployeePage
    public void Insert_Managerbook(ManagesBook managerbook){
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "insert into ManagesBook(EmpID,BookID,DateAdded,Activity) values(?,?,?,?)";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1,managerbook.getEmpID());
            prepare.setString(2,managerbook.getEmpID());
            prepare.setString(3, String.valueOf(managerbook.getDateAdd()));
            prepare.setString(4,managerbook.getActivity());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //method Upodate
    public void Update(ManagesBook managerbook){
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "update Department set BookID =?, DateAdded =?, Activity =?" +
                    "where EmpID =?";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(4,managerbook.getEmpID());
            prepare.setString(1,managerbook.getEmpID());
            prepare.setString(2, String.valueOf(managerbook.getDateAdd()));
            prepare.setString(3,managerbook.getActivity());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //emthod Search
    public ObservableList<ManagesBook> Search(String maSearch) {
        ObservableList<ManagesBook> listData = FXCollections.observableArrayList();
        Connection connection = JDBCutil.connectJDBC();
        try {
            String sql = "SELECT * FROM ManagesBook WHERE EmpID like? ";
            PreparedStatement prepare = connection.prepareStatement(sql);
            prepare.setString(1, "%" + maSearch + "%");
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                ManagesBook managerbook = new ManagesBook();

                managerbook.setEmpID(rs.getString("DepID"));
                managerbook.setBookID(rs.getString("BookID"));
                managerbook.setDateAdd(rs.getDate("DateAdded"));
                managerbook.setActivity(rs.getString("Activity"));

                listData.add(managerbook);

            }
            return listData;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // boor sung
    }
}


