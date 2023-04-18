package view;

import controller.GenaralLoginController;
import data.BorrowedDocumentService;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.BorrowAndReturnBook;

import java.io.InputStream;
import java.util.Date;

public class ReaderUI extends Application {
    private InputStream input;
    private Image img;
    private ImageView imgView;
    private Stage window;
    private Scene scene;
    private BorderPane root;
    private Label lblTitleRight;
    private VBox vbLeft, vbRight;
    private TableView<BorrowAndReturnBook> table;
    private VBox  profileVbRight;
    private Button borrowedBtn, renewalBtn, returnedBtn, searchBtn, registerDiscussionRoomBtn,
            feedBackBtn, profileBtn, changePasswordBtn;

    //======== some format string ===============
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    private String formatBtn = "-fx-text-fill:#FFFFFF; -fx-background-color:#B77906;-fx-font-size:16px;";
    private String format = "-fx-font-size: 16px; -fx-font-weight: 500;";

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

        borrowedBtn = new Button("Borrowed Documents");
        renewalBtn = new Button("Renew Documents");
        returnedBtn = new Button("Returned Documents");
        searchBtn = new Button("Search Document");
        registerDiscussionRoomBtn = new Button("Discussion Room");
        profileBtn = new Button("My Profile");
        changePasswordBtn = new Button("Change Password");
        feedBackBtn = new Button("Feedback");

        borrowedBtn.setGraphic(borrowIcon()); borrowedBtn.setAlignment(Pos.CENTER_LEFT);
        renewalBtn.setGraphic(reIcon()); renewalBtn.setAlignment(Pos.CENTER_LEFT);
        returnedBtn.setGraphic(returnIcon()); returnedBtn.setAlignment(Pos.CENTER_LEFT);
        searchBtn.setGraphic(searchIcon()); searchBtn.setAlignment(Pos.CENTER_LEFT);
        registerDiscussionRoomBtn.setGraphic(registerIcon()); registerDiscussionRoomBtn.setAlignment(Pos.CENTER_LEFT);
        profileBtn.setGraphic(proIcon()); profileBtn.setAlignment(Pos.CENTER_LEFT);
        changePasswordBtn.setGraphic(passIcon()); changePasswordBtn.setAlignment(Pos.CENTER_LEFT);
        feedBackBtn.setGraphic(feedBackIcon()); feedBackBtn.setAlignment(Pos.CENTER_LEFT);

        borrowedBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        renewalBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        returnedBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        searchBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        registerDiscussionRoomBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        profileBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        changePasswordBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        feedBackBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");

        //Create a VBox contains HBox and padding
        vbLeft = new VBox(10);
        vbLeft.setPrefWidth(280);
        vbLeft.setPadding(new Insets(10));
        vbLeft.setAlignment(Pos.TOP_LEFT);
        vbLeft.setStyle("-fx-background-color: transparent; -fx-background-color:#B4CAF3 ; -fx-text-fill:#FFFFFF ; " +
                "-fx-font-size:18px; -fx-font-weight:500;");

        borrowedBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        renewalBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        returnedBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        searchBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        registerDiscussionRoomBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        profileBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        changePasswordBtn.prefWidthProperty().bind(vbLeft.widthProperty());
        feedBackBtn.prefWidthProperty().bind(vbLeft.widthProperty());

        vbLeft.getChildren().addAll(hbLogoAndTitle, borrowedBtn, returnedBtn, renewalBtn, searchBtn, registerDiscussionRoomBtn,
                profileBtn, changePasswordBtn, feedBackBtn);

        // ================== RIGHT ===============================================
        // Create center content area
        lblTitleRight = new Label("Borrowed Document");
        lblTitleRight.setPadding(new Insets(10));
        lblTitleRight.setMinHeight(45);
        lblTitleRight.setStyle(titleFormat);
        lblTitleRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        vbRight = new VBox();
        vbRight.setAlignment(Pos.TOP_CENTER);
        vbRight.getChildren().add(lblTitleRight);
        vbRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //================ TABLE ===================
        VBox vbSubRight = new VBox();
        vbSubRight.setAlignment(Pos.CENTER_LEFT);
        vbSubRight.setSpacing(10);
        vbSubRight.setPadding(new Insets(20));


        TableView<BorrowAndReturnBook> tableView = new TableView<BorrowAndReturnBook>();

        TableColumn<BorrowAndReturnBook, Integer> indexCol = new TableColumn<>("#");
        TableColumn<BorrowAndReturnBook, String> barcodeCol = new TableColumn<>("Barcode");
        TableColumn<BorrowAndReturnBook, String> titleCol = new TableColumn<>("Title");
        TableColumn<BorrowAndReturnBook, Date> borrowedCol = new TableColumn<>("Borrowed Date");
        TableColumn<BorrowAndReturnBook,Date> returnedCol = new TableColumn<>("Returned Date");
        TableColumn<BorrowAndReturnBook, Integer> stateCol = new TableColumn<>("State");

        tableView.getColumns().addAll(indexCol,barcodeCol,titleCol,borrowedCol,returnedCol,stateCol);

        indexCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BorrowAndReturnBook, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<BorrowAndReturnBook, Integer> param) {
                return new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(param.getValue()) + 1);
            }
        });
        String syle = "-fx-font-size:18px";
        indexCol.setStyle(syle);
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("readerID")); barcodeCol.setStyle(syle); //barcode
        titleCol.setCellValueFactory(new PropertyValueFactory<>("bookID")); titleCol.setStyle(syle);// title
        borrowedCol.setCellValueFactory(new PropertyValueFactory<>("startDate")); borrowedCol.setStyle(syle);
        returnedCol.setCellValueFactory(new PropertyValueFactory<>("retdate")); returnedCol.setStyle(syle);
        stateCol.setCellValueFactory(new PropertyValueFactory<>("borrStatus")); stateCol.setStyle(syle);

        GenaralLoginController currentReader = new GenaralLoginController();
        String readerID = currentReader.getReaderIdOfLogin();

        BorrowedDocumentService brr = new BorrowedDocumentService();
        ObservableList<BorrowAndReturnBook> arr = brr.getBorrowedDocument(readerID);
        tableView.getItems().addAll(arr);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbSubRight.getChildren().add(tableView);
        vbRight.getChildren().add(vbSubRight);

        //===============================================================

        vbRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        lblTitleRight.prefWidthProperty().bind(vbRight.widthProperty());

        root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setLeft(vbLeft);
        root.setCenter(vbRight);

        // Create scene and show stage
        scene = new Scene(root, 995, 620);
        window.setScene(scene);
        window.setTitle("Library");
        window.show();
    }
    public void addChangeColorBtn(){
        // Change color buttons
        borrowedBtn.setOnMouseEntered(et->{
            borrowedBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        borrowedBtn.setOnMouseExited(et->{
            borrowedBtn.setStyle("-fx-background-color:#BB6E04;-fx-text-fill:#FFFFFF");
        });

        renewalBtn.setOnMouseEntered(et->{
            renewalBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        renewalBtn.setOnMouseExited(et->{
            renewalBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        });

        returnedBtn.setOnMouseEntered(et->{
            returnedBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        returnedBtn.setOnMouseExited(et->{
            returnedBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        });

        searchBtn.setOnMouseEntered(et->{
            searchBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        searchBtn.setOnMouseExited(et->{
            searchBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        });

        registerDiscussionRoomBtn.setOnMouseEntered(et->{
            registerDiscussionRoomBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        registerDiscussionRoomBtn.setOnMouseExited(et->{
            registerDiscussionRoomBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        });

        profileBtn.setOnMouseEntered(et->{
            profileBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        profileBtn.setOnMouseExited(et->{
            profileBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        });

        changePasswordBtn.setOnMouseEntered(et->{
            changePasswordBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        changePasswordBtn.setOnMouseExited(et->{
            changePasswordBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        });

        feedBackBtn.setOnMouseEntered(et->{
            feedBackBtn.setStyle("-fx-background-color:#F5E62D;-fx-text-fill:#000000");
        });
        feedBackBtn.setOnMouseExited(et->{
            feedBackBtn.setStyle("-fx-background-color:#496292;-fx-text-fill:#FFFFFF");
        });
    }
    public void moveGoBorrowedPage(){
        borrowedBtn.setOnAction(event -> {
            root.setCenter(vbRight);
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
            Label lbl = new Label("THIS FUNCTION IS IN DEVELOPMENT STAGE");
            lbl.setStyle(format + "-fx-font-size:20px");
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



    //=====================     EVENT =======================================================


    //===================== ICON FUNCTION ====================================================
    public ImageView loginIcon(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/logo4.png");
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
}
