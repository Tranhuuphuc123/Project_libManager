package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Desktop;

public class ReaderUI extends Application {
    private InputStream input ;
    private Image img;
    private ImageView imgView;
    private Stage window;
    private Scene scene;
    private BorderPane root;
    private VBox vbLeft;
    private Button borrowedBtn, renewalBtn, returnedBtn, searchBtn, registerDiscussionRoomBtn,
            feedBackBtn, profileBtn, changePasswordBtn, instructionBtn;

    //======== some format string ===============
    //  private String menuFormat = "-fx-background-color:#496292;-fx-text-fill:#FFFFFF";
    private String menuFormat = "-fx-background-color:#005864 ;-fx-text-fill:#FFFFFF";

    private String menuEffect = "-fx-background-color:#93F3ED;-fx-text-fill:#000000";
    // ==========================================

    // Information Reader Login
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        showWindow();
        addChangeColorBtn();
        moveGoBorrowedPage();
        moveGoReturnedPage();
        moveGoRenewalPage();
        moveGoSearchPage();
        moveGoRegisterDisRoomPage();
        moveGoProfilePage();
        moveGoPasswordPage();
        moveGoFeedbackPage();
        moveGoInstructionPage();
    }
    public void showWindow(){

        // =========== LEFT =================
        //Create a logo and name library
        window = new Stage();
        Text titleLogo = new Text("Library");
        titleLogo.setFont(Font.font("SansSerif", FontWeight.BOLD,40));
        HBox hbLogoAndTitle = new HBox(10);
        hbLogoAndTitle.getChildren().addAll(loginIcon(),titleLogo);

        input = getClass().getResourceAsStream("/icon/ReaderUI/logo5.png");
        Image icon = new Image(input);
        window.getIcons().add(icon);

        //========== Navigation ===============

        borrowedBtn = new Button("Borrowed Document");
        renewalBtn = new Button("Renew Document");
        returnedBtn = new Button("Returned Document");
        searchBtn = new Button("Search Document");
        registerDiscussionRoomBtn = new Button("Discussion Room");
        profileBtn = new Button("My Profile");
        changePasswordBtn = new Button("Change Password");
        feedBackBtn = new Button("Feedback");
        instructionBtn = new Button("Instructions For Use");

        borrowedBtn.setGraphic(borrowIcon()); borrowedBtn.setAlignment(Pos.CENTER_LEFT);
        renewalBtn.setGraphic(reIcon()); renewalBtn.setAlignment(Pos.CENTER_LEFT);
        returnedBtn.setGraphic(returnIcon()); returnedBtn.setAlignment(Pos.CENTER_LEFT);
        searchBtn.setGraphic(searchIcon()); searchBtn.setAlignment(Pos.CENTER_LEFT);
        registerDiscussionRoomBtn.setGraphic(registerIcon()); registerDiscussionRoomBtn.setAlignment(Pos.CENTER_LEFT);
        profileBtn.setGraphic(proIcon()); profileBtn.setAlignment(Pos.CENTER_LEFT);
        changePasswordBtn.setGraphic(passIcon()); changePasswordBtn.setAlignment(Pos.CENTER_LEFT);
        feedBackBtn.setGraphic(feedBackIcon()); feedBackBtn.setAlignment(Pos.CENTER_LEFT);
        instructionBtn.setGraphic(instructionIcon()); instructionBtn.setAlignment(Pos.CENTER_LEFT);

        borrowedBtn.setStyle(menuFormat);
        renewalBtn.setStyle(menuFormat);
        returnedBtn.setStyle(menuFormat);
        searchBtn.setStyle(menuFormat);
        registerDiscussionRoomBtn.setStyle(menuFormat);
        profileBtn.setStyle(menuFormat);
        changePasswordBtn.setStyle(menuFormat);
        feedBackBtn.setStyle(menuFormat);
        instructionBtn.setStyle(menuFormat);

        // Create a VBox contains HBox and padding
        vbLeft = new VBox(10);
        vbLeft.setPrefWidth(280);
        vbLeft.setPadding(new Insets(10));
        vbLeft.setAlignment(Pos.TOP_LEFT);
        vbLeft.setStyle("-fx-background-color: transparent; -fx-background-color:#426EB4 ; -fx-text-fill:#FFFFFF ; " +
                "-fx-font-size:18px; -fx-font-weight:500;");

        borrowedBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        renewalBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        returnedBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        searchBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        registerDiscussionRoomBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        profileBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        changePasswordBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        feedBackBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        instructionBtn.prefWidthProperty().bind(vbLeft.widthProperty());

        vbLeft.getChildren().addAll(hbLogoAndTitle, borrowedBtn, returnedBtn, renewalBtn, searchBtn, registerDiscussionRoomBtn,
                profileBtn, changePasswordBtn, feedBackBtn, instructionBtn);

        //================ MAIN LAYOUT =============================================
        BorrowedPage borrowedPage = new BorrowedPage();
        root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setLeft(vbLeft);
        root.setCenter(borrowedPage);

        // Create scene and show stage
        scene = new Scene(root, 1024, 650);
        window.setScene(scene);
        window.setTitle("Library");
        window.show();
    }
    public void addChangeColorBtn(){
        // Change color buttons
        borrowedBtn.setOnMouseEntered(et->{
            borrowedBtn.setStyle(menuEffect);
        });
        borrowedBtn.setOnMouseExited(et->{
            borrowedBtn.setStyle("-fx-background-color:#04837A;-fx-text-fill:#FFFFFF");
        });

        renewalBtn.setOnMouseEntered(et->{
            renewalBtn.setStyle(menuEffect);
        });
        renewalBtn.setOnMouseExited(et->{
            renewalBtn.setStyle(menuFormat);
        });

        returnedBtn.setOnMouseEntered(et->{
            returnedBtn.setStyle(menuEffect);
        });
        returnedBtn.setOnMouseExited(et->{
            returnedBtn.setStyle(menuFormat);
        });

        searchBtn.setOnMouseEntered(et->{
            searchBtn.setStyle(menuEffect);
        });
        searchBtn.setOnMouseExited(et->{
            searchBtn.setStyle(menuFormat);
        });

        registerDiscussionRoomBtn.setOnMouseEntered(et->{
            registerDiscussionRoomBtn.setStyle(menuEffect);
        });
        registerDiscussionRoomBtn.setOnMouseExited(et->{
            registerDiscussionRoomBtn.setStyle(menuFormat);
        });

        profileBtn.setOnMouseEntered(et->{
            profileBtn.setStyle(menuEffect);
        });
        profileBtn.setOnMouseExited(et->{
            profileBtn.setStyle(menuFormat);
        });

        changePasswordBtn.setOnMouseEntered(et->{
            changePasswordBtn.setStyle(menuEffect);
        });
        changePasswordBtn.setOnMouseExited(et->{
            changePasswordBtn.setStyle(menuFormat);
        });

        feedBackBtn.setOnMouseEntered(et->{
            feedBackBtn.setStyle(menuEffect);
        });
        feedBackBtn.setOnMouseExited(et->{
            feedBackBtn.setStyle(menuFormat);
        });

        instructionBtn.setOnMouseEntered(et->{
            instructionBtn.setStyle(menuEffect);
        });
        instructionBtn.setOnMouseExited(et->{
            instructionBtn.setStyle(menuFormat);
        });
    }
    public void moveGoBorrowedPage(){
        borrowedBtn.setOnAction(event -> {
            BorrowedPage borrowedPage = new BorrowedPage();
            root.setCenter(borrowedPage);
        });
    }
    public void moveGoRenewalPage(){
        renewalBtn.setOnAction(et-> {
            RenewalPage renewal = new RenewalPage();
            root.setCenter(renewal);
        });
    }
    public void moveGoReturnedPage(){
        // Create center content area
        returnedBtn.setOnAction(event -> {
            ReturnedPage returnedPage = new ReturnedPage();
            root.setCenter(returnedPage);
        });
    }
    public void moveGoSearchPage(){
        searchBtn.setOnAction(event -> {
            SearchDocumentPage seachPage = new SearchDocumentPage();
            root.setCenter(seachPage);
        });
    }
    public void moveGoRegisterDisRoomPage(){
        registerDiscussionRoomBtn.setOnAction(event -> {
            DiscussionRoomPage discussionRoom = new DiscussionRoomPage();
            root.setCenter(discussionRoom);
        });

    }
    public void moveGoProfilePage(){
        profileBtn.setOnAction(et->{
            ProfilePage profilePage = new ProfilePage();
            root.setCenter(profilePage);
        });
    }
    public void moveGoPasswordPage(){
        changePasswordBtn.setOnAction(event -> {
            PasswordPage passwordPage = new PasswordPage();
            root.setCenter(passwordPage);
        });
    }

    public void moveGoFeedbackPage(){
        feedBackBtn.setOnAction(event -> {
            FeedbackPage feedbackPage = new FeedbackPage();
            root.setCenter(feedbackPage);
        });
    }

    public void moveGoInstructionPage(){
        instructionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File htmlFile = new File("src/Guidline/reader.html");
                    Desktop.getDesktop().browse(htmlFile.toURI());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    //===================== ICON FUNCTION ====================================================
    public ImageView loginIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/logo7.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(52);
        imgView.setFitWidth(52);
        return imgView;
    }

    public ImageView borrowIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/doc4.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(28);
        imgView.setFitWidth(28);
        return imgView;
    }

    public ImageView returnIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/doc5.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(28);
        imgView.setFitWidth(28);
        return imgView;
    }

    public ImageView reIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/doc6.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(28);
        imgView.setFitWidth(28);
        return imgView;
    }

    public ImageView passIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/doc9.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(30);
        imgView.setFitWidth(30);
        return imgView;
    }

    public ImageView proIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/doc8.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(28);
        imgView.setFitWidth(28);
        return imgView;
    }
    public ImageView searchIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/search1.png");
        img = new Image(input);
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(28);
        imageView.setFitWidth(28);
        return imageView;
    }
    public ImageView registerIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/room1.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(28);
        imgView.setFitWidth(28);
        return imgView;
    }
    public ImageView feedBackIcon() {
        input = getClass().getResourceAsStream("/icon/ReaderUI/feed2.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(30);
        imgView.setFitWidth(30);
        return imgView;
    }

    public ImageView instructionIcon() {
        input = getClass().getResourceAsStream("/icon/ReaderUI/instruc.png");
        img = new Image(input);
        imgView = new ImageView(img);
        imgView.setFitHeight(30);
        imgView.setFitWidth(30);
        return imgView;
    }
}
