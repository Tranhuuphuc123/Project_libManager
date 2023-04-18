package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// xây dựng chức năng kịch bản xử lý cho giao diện của Reader.fxml
public class ReadeController implements Initializable {

    @FXML
    private AnchorPane Pane_Home;
    @FXML
    private AnchorPane Pane_Borr_Ret;

    @FXML
    private JFXButton btn_Borr_Ret;

    @FXML
    private JFXButton btn_HomePage;

    @FXML
    private JFXButton btn_LogOut;

    @FXML
    private JFXButton btn_Option;

    @FXML
    private AnchorPane menu_Header;

    @FXML
    private AnchorPane subMenu_Option;


    //method xủ lý chuyển sự kiện qua lại các sub pane
    @FXML
    public void handleActionEvent(ActionEvent event) {
        if(event.getSource() == btn_HomePage ){
            Pane_Home.toFront();
        }else if(event.getSource()==btn_Borr_Ret){
            Pane_Borr_Ret.setVisible(true);
            Pane_Borr_Ret.toFront();
        }else if(event.getSource() == btn_Option){
            if(subMenu_Option.visibleProperty().getValue() == true){
                subMenu_Option.setVisible(false);
            }else {
                subMenu_Option.setVisible(true);
            }
        }else if(event.getSource()==btn_LogOut){
            try {
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/View/Log_In.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
