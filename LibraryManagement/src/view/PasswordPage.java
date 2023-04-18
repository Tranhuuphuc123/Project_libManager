package view;

import controller.GenaralLoginController;
import data.PasswordService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.util.regex.Pattern;

public class PasswordPage extends BorderPane {
    private PasswordField oldPasswordField, newPasswordField, confirmPasswordField;
    Button saveBtn;
    private Label statusLbl = new Label();
    private String format = "-fx-font-size: 16px; -fx-font-weight: 500;";
    private String formatBtn = "-fx-text-fill:#FFFFFF; -fx-background-color:#B77906;-fx-font-size:16px;";
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    public PasswordPage(){

        InputStream input = getClass().getResourceAsStream("/icon/ReaderUI/b1.jpg");
        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(150);
        imgView.setFitWidth(150);

        VBox passVbLeft = new VBox(20);
        passVbLeft.setPadding(new Insets(20,0,0,20));
        passVbLeft.setAlignment(Pos.TOP_LEFT);
        passVbLeft.getChildren().add(imgView);

        //==============================================

        Label oldPasswordLbl = new Label("Current Password ");
        oldPasswordField = new PasswordField(); oldPasswordField.setMaxSize(280,28);
        oldPasswordField.setPromptText("Enter current password");

        Label newPasswordPassLbl = new Label("New Password ");
        newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Enter new password");

        Label confirmPasswordLbl = new Label("Confirm Password ");
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Re-enter password");

        oldPasswordLbl.setStyle(format);
        oldPasswordField.setStyle(format);
        newPasswordPassLbl.setStyle(format);
        newPasswordField.setStyle(format);
        confirmPasswordLbl.setStyle(format);
        confirmPasswordField.setStyle(format);

        statusLbl.setStyle(format + "-fx-text-fill: red");

        saveBtn = new Button("Save");
        saveBtn.setPrefSize(70,12);
        saveBtn.setStyle(formatBtn);
        saveBtn.setOnAction(event1 -> savePassword());

        //
        HBox btnHBox = new HBox(10, saveBtn);
        btnHBox.setAlignment(Pos.CENTER_RIGHT);

        //
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.addRow(0,oldPasswordLbl, oldPasswordField);
        formGrid.addRow(1, newPasswordPassLbl, newPasswordField);
        formGrid.addRow(2,confirmPasswordLbl, confirmPasswordField);

        //
        Label passTitle = new Label("Change Your Password");
        passTitle.setStyle(titleFormat);
        passTitle.setMinHeight(45);
        passTitle.setAlignment(Pos.TOP_LEFT);
        passTitle.setPadding(new Insets(10));
        VBox passTitleVb = new VBox();
        passTitleVb.getChildren().add(passTitle);
        passTitle.prefWidthProperty().bind(passTitleVb.widthProperty());
        passTitleVb.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        VBox passVbRight = new VBox(10, formGrid, statusLbl, btnHBox);
        passVbRight.setPadding(new Insets(20));
        passVbRight.setAlignment(Pos.TOP_LEFT);

        BorderPane passwordPane = new BorderPane();
        passwordPane.setTop(passTitleVb);
        passwordPane.setLeft(passVbLeft);
        passwordPane.setRight(passVbRight);
        passwordPane.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        this.setCenter(passwordPane);

    }

    protected void savePassword(){
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        Pattern passwordPattern = Pattern.compile("^(?=.*[!@#$%^&*()_.+=])(?=\\S+$).{6,}$");
        statusLbl.setStyle(format+"-fx-text-fill:red");

        if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){
            statusLbl.setText("Please enter all fields");
        }else if(!newPassword.equals(confirmPassword)){
            statusLbl.setText("New passwords do not match.");
        }else if(!passwordPattern.matcher(newPassword).matches()){
            statusLbl.setText("Password must be at least 6 characters long \nand must contain at least one special character (!@#$%^&*()_.+=)");
        }else {
            PasswordService passwordService = new PasswordService();
            if(passwordService.findPassword(oldPassword) > 0){  //correct your password
                int changePass = passwordService.changePassword(oldPassword ,
                        newPassword, GenaralLoginController.getReaderIdOfLogin());
                if(changePass > 0){
                    statusLbl.setText("Your feedback has successfully!");
                    statusLbl.setStyle(format+"-fx-text-fill:green; -fx-font-weight:600");
                    statusLbl.setText("Password updated successfully.");
                    oldPasswordField.clear();
                    newPasswordField.clear();
                    confirmPasswordField.clear();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Invalid Value");
                alert.setContentText("Your old password is invalid! PLease to check it.");
                alert.showAndWait();
            }


        }
    }

    public ImageView loginIcon(){
        InputStream input = getClass().getResourceAsStream("/icon/ReaderUI/b1.jpg");
        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(260);
        imgView.setFitWidth(260);
        return imgView;
    }


}
