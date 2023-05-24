package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Department;
import model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// method actiont event of departmentpage
public class DepartService extends JDBCutil{

    //method laod data on table_Depart

    public ObservableList<Department>getAllDepart(){
        ObservableList<Department> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Department";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Department depart = new Department();
                depart.setDepID(rs.getString("DepID"));
                depart.setDepName(rs.getString("DepName"));
                depart.setDepStatus(rs.getString("DepStatus"));

                listData.add(depart);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }
    //method load data ontable has coditional
    public  ObservableList<Department> getAllDepartment_condition(){
        ObservableList<Department> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Department where DepStatus   IN ('Active',' On Pending')";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Department depart = new Department();
                depart.setDepID(rs.getString("DepID"));
                depart.setDepName(rs.getString("DepName"));
                depart.setDepStatus(rs.getString("DepStatus"));

                listData.add(depart);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }




//----------------------------------------------------------------------------------------------------
    //method insert EmployeePage
    public void Insert_Department(Department depart){
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "insert into Department(DepID,DepName,DepStatus) values(?,?,?)";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1,depart.getDepID());
            prepare.setString(2,depart.getDepName());
            prepare.setString(3,depart.getDepStatus());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //method Upodate
    public void Update_Department(Department depart){
        Connection connect = JDBCutil.connectJDBC();
        try{
            String sql = "update Department set DepName =?, DepStatus =?" +
                    "where DepID =?";
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(3,depart.getDepID());
            prepare.setString(2,depart.getDepName());
            prepare.setString(1,depart.getDepName());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //emthod Search
    public ObservableList<Department> Search_Department(String maSearch) {
        ObservableList<Department> listData = FXCollections.observableArrayList();
        Connection connection = JDBCutil.connectJDBC();
        try {
            String sql = "SELECT * FROM Department WHERE DepID like? ";
            PreparedStatement prepare = connection.prepareStatement(sql);
            prepare.setString(1, "%" + maSearch + "%");
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                Department department = new Department();

                department.setDepID(rs.getString("DepID"));
                department.setDepName(rs.getString("DepName"));
                department.setDepStatus(rs.getString("DepStatus"));

                listData.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;
    }
}
