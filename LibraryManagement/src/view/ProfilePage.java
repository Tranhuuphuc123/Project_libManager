package view;

import controller.GenaralLoginController;
import data.ProfileService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Reader;

import java.io.InputStream;
import java.text.SimpleDateFormat;

public class ProfilePage extends BorderPane {
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    private String lblFormat = "-fx-font-size:18px; -fx-font-weight:500;";
    public ProfilePage(){
        // =========== TOP PROFILE ==============
        VBox topProfile = new VBox();
        Label lblTitleRight = new Label("General Profile");
        lblTitleRight.setStyle(titleFormat);
        lblTitleRight.setMinHeight(45);
        lblTitleRight.setAlignment(Pos.CENTER_LEFT);
        lblTitleRight.setPadding(new Insets(10));
        lblTitleRight.prefWidthProperty().bind(topProfile.widthProperty());
        topProfile.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        topProfile.getChildren().add(lblTitleRight);

        // =========== LEFT PROFILE =========
        VBox leftProfile = new VBox(20);
        InputStream input = getClass().getResourceAsStream("/icon/ReaderUI/b1.jpg");
        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(180);
        imgView.setFitWidth(180);
        leftProfile.getChildren().add(imgView);

        // ========== RIGHT PROFILE ================
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        ProfileService profileService = new ProfileService();
        Reader infoReader = new Reader();
        infoReader = profileService.inforReader(GenaralLoginController.getReaderIdOfLogin());

        Label usernameTitle = new Label("Username ");
        Label usernameLbl = new Label();
        usernameLbl.setText(infoReader.getReaderName()); usernameLbl.setStyle(lblFormat + "-fx-font-weight:700");

        Label idTitle = new Label("Reader ID ");
        Label idLbl = new Label();
        idLbl.setText(infoReader.getReaderID()); idLbl.setStyle(lblFormat);

        Label birthTitle = new Label("Date Of Birth ");
        birthTitle.setMinWidth(125);
        Label birthLbl = new Label();
        birthLbl.setText(sdf.format(infoReader.getBirth())); birthLbl.setStyle(lblFormat);

        Label dateCreatedTitle = new Label("Date Created ");
        Label dateCreatedLbl = new Label();
        dateCreatedLbl.setText(sdf.format(infoReader.getDateCreated())); dateCreatedLbl.setStyle(lblFormat);

        Label outDateTitle = new Label("Out Of Title ");
        Label outDateLbl = new Label();
        outDateLbl.setText(sdf.format(infoReader.getOutOfDate())); outDateLbl.setStyle(lblFormat);

        Label addressTitle = new Label("Address ");
        Label addressLbl = new Label();
        addressLbl.setText(infoReader.getReaderAddress()); addressLbl.setStyle(lblFormat);

        Label phoneTitle = new Label("Phone ");
        Label phoneLbl = new Label();
        phoneLbl.setText(infoReader.getReaderPhone()); phoneLbl.setStyle(lblFormat);

        usernameTitle.prefWidthProperty().bind(birthTitle.widthProperty());
        idTitle.prefWidthProperty().bind(birthTitle.widthProperty());
        dateCreatedTitle.prefWidthProperty().bind(birthTitle.widthProperty());
        outDateTitle.prefWidthProperty().bind(birthTitle.widthProperty());
        addressTitle.prefWidthProperty().bind(birthTitle.widthProperty());
        phoneTitle.prefWidthProperty().bind(birthTitle.widthProperty());

        // ===== format for right profile
        usernameTitle.setStyle(lblFormat + "-fx-font-weight:700");
        idTitle.setStyle(lblFormat);
        birthTitle.setStyle(lblFormat);
        dateCreatedTitle.setStyle(lblFormat);
        outDateTitle.setStyle(lblFormat);
        addressTitle.setStyle(lblFormat); addressTitle.setWrapText(true);
        phoneTitle.setStyle(lblFormat);

        GridPane centerProfile = new GridPane();
        centerProfile.setVgap(10);
        centerProfile.setHgap(20);
        centerProfile.setPadding(new Insets(25,45,25,10));
        centerProfile.addRow(0,usernameTitle, usernameLbl);
        centerProfile.addRow(1,idTitle, idLbl);
        centerProfile.addRow(2,birthTitle, birthLbl);
        centerProfile.addRow(3,dateCreatedTitle, dateCreatedLbl);
        centerProfile.addRow(4,outDateTitle, outDateLbl);
        centerProfile.addRow(5,addressTitle, addressLbl);
        centerProfile.addRow(6,phoneTitle, phoneLbl);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topProfile);
        borderPane.setLeft(leftProfile);
        borderPane.setRight(centerProfile);
        borderPane.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setCenter(borderPane);
    }
}
