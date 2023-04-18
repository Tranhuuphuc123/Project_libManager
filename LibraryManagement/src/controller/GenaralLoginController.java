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
                if(combo_UserType.getValue() == "Admin"){
                    loginService = new LoginService();
                    employee = loginService.EmployeeLogin(txtUser.getText(), txtPass.getText());
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
                        alert.setContentText("Login is Fail!");
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
                        alert.setContentText("Login is fails!");
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
