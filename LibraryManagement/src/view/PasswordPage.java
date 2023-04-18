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

public class PasswordPage extends BorderPane {
    private PasswordField oldPasswordField, newPasswordField, confirmPasswordField;
    Button saveBtn;
    private Label statusLbl = new Label();
    private String format = "-fx-font-size: 16px; -fx-font-weight: 500;";
    private String formatBtn = "-fx-text-fill:#FFFFFF; -fx-background-color:#B77906;-fx-font-size:16px;";
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
                                                     "-fx-font-size:18px; -fx-font-weight:700;";
    public PasswordPage(){
      //  String imgUrl = new String("https://lh3.googleusercontent.com/pIdTUVg2V34SQvJmpXj_wVuiDFRUAkOMf5bPU-OYX-dTT0M4vPz--pb-6lkawhFuf_s4FK34mdgQfRTZCJW5gLrfdk9nv1n7GM2dRBpAPhiovXHnq47BFNl8ScNe0hiHZ3f7oHSi1Z3vNSxa75cg3aawc4bzndto9Mo5D6pcQon4ENsAbCPmfswGm3SxzmuYxAsfRNL-CKGF1TBvLL-JMOxeZeW_wlYfBoJ0-F183SwUdju8GgI7jxk3iLNXFc4GwFGy_59nYIW9wjyJNGR-2tE0Nfl_FygR3PEFDPt_tbR086l5ogHMjCgV9Vh1absflP7j9iKuuP01PtdKzqUJkgGpaMNrvt2hqlF_qqfiYwsYMhXXb2A8GN6FqGkZ8VEPIOeNvSjh5yte5kTFbD1bg5BBv8Mqz0SzI4fNY0RhNzzoLZ2qtMOec49YG4hGwgmJhMPRk2AwE9q3DjB4iwIwrYbINSfANqECK6xKCdtfxB6RBFjv45n0axGvc1Ylt_KfN0BaZuyPGcaMbpayP_WSsgI-GkXnDm1ruMFua4tkvAcDHqJIZXDOgaBtrM2jMUp-wRpi_0WMtM6zGsDDH8_nNT1BFWNzJviT0XFGRsyqFrdgr85PWFwqot4iP1OdqSpWniGJnzgEeoEaFH1lxGBsbIOAEU5PhqSiW83LwNzazzOesZN2KzJnszeOPrLPk4VTtLUkrUWcSkuM4lp_2vDYLXMWfQgTSVaze6Wme2giKsi6eCycTWXFAm4m5KtSs-NLSA_bzTZAcMzFHrUwDFA_RklGewLbD4j59mcOKhabbyc25w2ckNacxLFXj46EUj2Y9anlvFe9rRi9Ac5ptq_5v2hu0WFUsk1yvVtNP5DnoTU7sS5swpn2P-0xKi59nx9p8LPHlwnZAJiMY3h9np1Z64DbJoa1WPocZczrntyqN0AcPc2qat4wuLHjsPqqAGkjzZCASLpMlqKYSZnTVodJq5s-YNS6RdXhqjta_IYtIiKX1xpS1VqYzwKqhUmmPzkVlPpPja46pM8fW9mZhNiJv9o4FjHwyk5wQGo8iy26CaMD5DQf2niqQ-UiHImpaA=w348-h280-s-no?authuser=1");
      //  Image readerImg = new Image(imgUrl);
      //  ImageView readerImgView = new ImageView(readerImg);
      //  readerImgView.setFitHeight(200);
      //  readerImgView.setFitWidth(250);

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
        statusLbl.setStyle(format+"-fx-text-fill:red");
        if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){
            statusLbl.setText("Please enter all fields");
        }else if(!newPassword.equals(confirmPassword)){
            statusLbl.setText("New passwords do not match.");
        }else {
            PasswordService passwordService = new PasswordService();
            int changePass;
            changePass = passwordService.changePassword(oldPasswordField.getText() ,
                                        newPasswordField.getText(), GenaralLoginController.getReaderIdOfLogin());
            if(changePass > 0){
                statusLbl.setText("Your feedback has successfully!");
                statusLbl.setStyle(format+"-fx-text-fill:green; -fx-font-weight:600");
                statusLbl.setText("Password updated successfully.");
                oldPasswordField.clear();
                newPasswordField.clear();
                confirmPasswordField.clear();
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
