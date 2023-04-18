package controller;

import data.LoginService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Employee;
import model.Reader;
import view.ReaderUI;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class GenaralLoginController implements Initializable{
    @FXML
    private Label label_thongbao;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btn_SignIn;
    @FXML
    private ComboBox<String> combo_UserType;

    private static String readerIdLogin;

    // thiết lập các trường items để sổ ra khi click vào combobox
    ObservableList<String> userTypeList = FXCollections.observableArrayList("Reader","Admin");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo_UserType.setPromptText("Please select an account!");
        combo_UserType.setItems(userTypeList);
        handleLogin();
    }

    Stage stage = new Stage();
    @FXML
    protected void handleLogin(){
        btn_SignIn.setOnAction(et->{
            LoginService loginService = null;
            Employee employee;
            Reader reader = null;

            try {
                if(txtUser.getText().isEmpty() || txtPass.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Problem");
                    alert.setContentText("Username and password cannot be blank!");
                    alert.showAndWait();
                }else if(combo_UserType.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Problem");
                    alert.setContentText("You have not selected the type of account you want to log in to. Please click Option and select it.");
                    alert.showAndWait();
                }
                if(combo_UserType.getValue() == "Admin"){
                    loginService = new LoginService();
                    employee = loginService.EmployeeLogin(txtUser.getText(), txtPass.getText());
                    System.out.println("EMP: " + txtUser + " pass : " + txtPass);
                    System.out.println("Emp : " + employee.getEmpID());
                    if(employee != null) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Successful!");
                        alert.setHeaderText(null);
                        alert.setContentText("Login is successful!");
                        alert.showAndWait();

                        Parent root = FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
                        Scene scene = new Scene(root);

                        // đóng giao diện login
                        Stage loginStage = (Stage) btn_SignIn.getScene().getWindow();
                        loginStage.close();

                        stage.setScene(scene);
                        stage.show();

                    }else{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Login is fails!");
                        alert.setHeaderText(null);
                        alert.setContentText("Your Username or Password is incorrect.\nPlease check again.");
                        alert.showAndWait();
                    }


                }else if(combo_UserType.getValue() == "Reader"){
                    loginService = new LoginService();
                    reader = loginService.ReaderLogin(txtUser.getText(), txtPass.getText());
                    if(reader != null) {
                        readerIdLogin = txtUser.getText();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Successful");
                        alert.setHeaderText(null);
                        alert.setContentText("Login is successful!");
                        alert.showAndWait();

                        ReaderUI UI = new ReaderUI();
                        UI.start(stage);
                        // đóng giao diện login
                        Stage loginStage = (Stage) btn_SignIn.getScene().getWindow();
                        loginStage.close();

                    }else{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Fail Login");
                        alert.setHeaderText(null);
                        alert.setContentText("Your Username or Password is incorrect.\nPlease check again.");
                        alert.showAndWait();
                    }
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
            }
        });
    }

    public static String getReaderIdOfLogin(){
        return readerIdLogin;
    }
}
