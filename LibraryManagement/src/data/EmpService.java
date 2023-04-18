package data;
// serviec help for EmployeePage Application

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CatalogOfBooks;
import model.Employee;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpService extends  JDBCutil{
     //load comboboxDepID
    public ObservableList<String> loadCombobox_DepID(){
        ObservableList<String>listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select DepID from Department";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                listData.add(rs.getString("DepID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }




    //method load data on tbale_Employee
    public  ObservableList<Employee> getAllEmployee(){
        ObservableList<Employee> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Employee";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setEmpID(rs.getString("EmpID"));
                emp.setEmpName(rs.getString("EmpName"));
                emp.setEmpPhone(rs.getString("EmpPhone"));
                emp.setBirthDay(rs.getDate("DateOfBirth"));
                emp.setEmpGender(rs.getString("EmpGender"));
                emp.setEmpAddress(rs.getString("EmpAddress"));
                emp.setDepID(rs.getString("DepID"));
                emp.setEmpPosition(rs.getString("EmpPosition"));
                emp.setEmpSalary(rs.getString("EmpSalary"));
                emp.setEmpPass(rs.getString("EmpPass"));
                emp.setEmpBarcode(rs.getString("EmpBarCode"));
                emp.setEmpStatus(rs.getString("EmpStatus"));

                listData.add(emp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }


    //method load data ontable has coditional
    public  ObservableList<Employee> getAllEmployee_condition(){
        ObservableList<Employee> listData = FXCollections.observableArrayList();
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "select * from Employee where EmpStatus IN ('Active','On Leave')";
            PreparedStatement prepare = conect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setEmpID(rs.getString("EmpID"));
                emp.setEmpName(rs.getString("EmpName"));
                emp.setEmpPhone(rs.getString("EmpPhone"));
                emp.setBirthDay(rs.getDate("DateOfBirth"));
                emp.setEmpGender(rs.getString("EmpGender"));
                emp.setEmpAddress(rs.getString("EmpAddress"));
                emp.setDepID(rs.getString("DepID"));
                emp.setEmpPosition(rs.getString("EmpPosition"));
                emp.setEmpSalary(rs.getString("EmpSalary"));
                emp.setEmpPass(rs.getString("EmpPass"));
                emp.setEmpBarcode(rs.getString("EmpBarCode"));
                emp.setEmpStatus(rs.getString("EmpStatus"));

                listData.add(emp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }





    //method insert EmployeePage
    public void Insert_Employee(Employee emp){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "insert into Employee(EmpID,EmpName,EmpPhone,DateOfBirth,EmpGender,EmpAddress" +
                    ",DepID,EmpPosition,EmpSalary,EmpPass,EmpBarCode,EmpImg,EmpStatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1,emp.getEmpID());
            prepare.setString(2,emp.getEmpName());
            prepare.setString(3,emp.getEmpPhone());
            prepare.setDate(4, (Date) emp.getBirthDay());
            prepare.setString(5,emp.getEmpGender());
            prepare.setString(6,emp.getEmpAddress());
            prepare.setString(7,emp.getDepID());
            prepare.setString(8,emp.getEmpPosition());
            prepare.setString(9,emp.getEmpSalary());
            prepare.setString(10,emp.getEmpPass());
            prepare.setString(11,emp.getEmpBarcode());
            prepare.setString(12,emp.getEmpImg());
            prepare.setString(13,emp.getEmpStatus());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //method Upodate
    public void Update_Employee(Employee emp){
        Connection conect = JDBCutil.connectJDBC();
        try{
            String sql = "update Employee set EmpName =?, EmpPhone =?, DateOfBirth =?,EmpGender =?, EmpAddress =?" +
                    ", DepID =?, EmpPosition =?,EmpSalary = ?, EmpPass =?, EmpBarCode =?, EmpStatus =? " +
                    "where EmpID =?";
            PreparedStatement prepare = conect.prepareStatement(sql);
            prepare.setString(1,emp.getEmpID());
            prepare.setString(2,emp.getEmpName());
            prepare.setString(3,emp.getEmpPhone());
            prepare.setDate(4, (Date) emp.getBirthDay());
            prepare.setString(5,emp.getEmpGender());
            prepare.setString(6,emp.getEmpAddress());
            prepare.setString(7,emp.getDepID());
            prepare.setString(8,emp.getEmpPosition());
            prepare.setString(9,emp.getEmpSalary());
            prepare.setString(10,emp.getEmpPass());
            prepare.setString(11,emp.getEmpBarcode());
            prepare.setString(12,emp.getEmpImg());
            prepare.setString(13,emp.getEmpStatus());

            int rs = prepare.executeUpdate();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //emthod Search


}
