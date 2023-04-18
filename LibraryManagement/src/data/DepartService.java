package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Department;
import model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// method actiont event of departmentpage
public class DepartService {
    //method laod data on table_Depart
    //method load data on table_Depart
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




  //end
}
