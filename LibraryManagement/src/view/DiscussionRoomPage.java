package view;

import controller.GenaralLoginController;
import data.DiscussionRoomService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.DiscussionRoom;
import model.RetristersBorrowed;

import java.time.LocalDate;

public class DiscussionRoomPage extends BorderPane {
    ComboBox<DiscussionRoom> roomCbb = null;
    ComboBox<String> borrowedByShift = null;
    DatePicker nowDate;
    TextField totalRoomTxt;
    private  DiscussionRoom disName = null;
    private String shift = null;
    private LocalDate disDate;
    private Label statusLbl = new Label();
    private String format = "-fx-text-fill:#8A660D; -fx-font-size:18px; -fx-font-weight:500;";
    private String btnFormat = "-fx-text-fill:#FFFFFF; -fx-font-size:18px; -fx-font-weight:500; ";
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    public DiscussionRoomPage(){
        // ====== TOP TITLE =====
        Label discussionTitle = new Label("Borrow a Discussion Room");
        discussionTitle.setStyle(format + "-fx-background-color:#FCEABF ;-fx-font-weight:800;");
        discussionTitle.setMinHeight(45);
        discussionTitle.setAlignment(Pos.TOP_LEFT);
        discussionTitle.setPadding(new Insets(10));
        VBox discussionTitleVb = new VBox();
        discussionTitleVb.getChildren().add(discussionTitle);
        discussionTitleVb.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        // ======
        Label nowDateTitle = new Label("Borrowed Date: ");
        nowDate = new DatePicker();
        nowDate.setPromptText("dd/MM/YYYY");
        nowDate.valueProperty().addListener((observable, oldValue, newValue)-> {
            LocalDate localDate = LocalDate.now();
            if(newValue != null && newValue.isBefore(localDate)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("Registration date expired. Please enter a valid registration date.");
                alert.showAndWait();
                nowDate.setValue(null);
            }else disDate = newValue;
        });

        Label roomCbbTitle = new Label("Discussion Room: ");
        DiscussionRoomService disService = new DiscussionRoomService();
        roomCbb = new ComboBox<>(disService.showListOfDiscussionRoom());
        roomCbb.setPromptText("Discussion Room");
        roomCbb.setOnAction((et)->{
            disName = roomCbb.getValue();
        });


        Label borrowedByShiftTitle = new Label("Shift: ");
        ObservableList<String> shiftList = FXCollections.observableArrayList();
        shiftList.add("Shift 1: 7h30 - 9h30");
        shiftList.add("Shift 2: 9h40 - 11h40");
        shiftList.add("Shift 3: 11h50 - 13h50");
        shiftList.add("Shift 4: 14h00 - 16h00");
        shiftList.add("Shift 5: 16h10 - 18h10");
        shiftList.add("Shift 6: 18h20 - 20h20");
        borrowedByShift = new ComboBox<>(shiftList);
        borrowedByShift.setPromptText("Shift 1 - 6");
        borrowedByShift.setOnAction(event -> {
            shift = borrowedByShift.getValue();
        });

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Label totalRoomTitle = new Label("Total number of members");
        totalRoomTxt = new TextField();
        totalRoomTxt.setPromptText("From 5 people or more");

        // === format text & size ===
        nowDateTitle.setStyle(format);
        nowDate.setStyle(format);

        roomCbbTitle.setStyle(format);
        roomCbb.setStyle(format);

        borrowedByShiftTitle.setStyle(format);
        borrowedByShift.setStyle(format);

        totalRoomTitle.setStyle(format);
        totalRoomTxt.setStyle(format);

        nowDateTitle.prefWidthProperty().bind(totalRoomTitle.widthProperty());
        roomCbbTitle.prefWidthProperty().bind(totalRoomTitle.widthProperty());
        borrowedByShiftTitle.prefWidthProperty().bind(totalRoomTitle.widthProperty());

        // ===== checkbox in HBox =====
        CheckBox borrowedTV = new CheckBox();
        borrowedTV.setText("Borrow TV");  borrowedTV.setStyle(format);
        HBox borrowedTVHb = new HBox(20);
        borrowedTVHb.getChildren().add(borrowedTV);

        // ===== btn option pushed into HBox =====
        Button verifyBtn = new Button("Verify"); verifyBtn.setOnAction(event -> handleVerify());
        Button cancelBtn = new Button("Cancel");


        verifyBtn.setStyle(format + "-fx-text-fill:#FFFFFF; -fx-background-color: #08C664; ");
        cancelBtn.setStyle(format + "-fx-text-fill:#FFFFFF; -fx-background-color: #E20825; ");

        HBox btnHb = new HBox(35);
        btnHb.setAlignment(Pos.CENTER_RIGHT);
        btnHb.setPadding(new Insets(30, 250, 200,50));
        btnHb.getChildren().addAll(verifyBtn, cancelBtn);

        HBox.setHgrow(verifyBtn, Priority.ALWAYS);
        HBox.setHgrow(cancelBtn, Priority.ALWAYS);
        verifyBtn.setMaxWidth(Double.MAX_VALUE);
        cancelBtn.setMaxWidth(Double.MAX_VALUE);

        // ===== push into gridPane =====
        statusLbl.setMaxWidth(Double.MAX_VALUE);
        statusLbl.setWrapText(true);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0,nowDateTitle, nowDate);
        gridPane.addRow(1,roomCbbTitle, roomCbb);
        gridPane.addRow(2,borrowedByShiftTitle, borrowedByShift);
        gridPane.addRow(3,totalRoomTitle ,totalRoomTxt);
        gridPane.addRow(4, borrowedTVHb);
        gridPane.addRow(5, statusLbl);
        gridPane.setPadding(new Insets(35));

        // === Main contains some layout ===
        BorderPane mainBorderPane = new BorderPane();
        discussionTitle.prefWidthProperty().bind(mainBorderPane.widthProperty());
        mainBorderPane.setTop(discussionTitleVb);
        mainBorderPane.setCenter(gridPane);
        mainBorderPane.setBottom(btnHb);
        mainBorderPane.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.setCenter(mainBorderPane);

    }
    protected void handleVerify(){

        statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
        statusLbl.setPadding(new Insets(15));
        if(nowDate.getValue() == null){
            statusLbl.setText("You have not selected a loan date.");
        }else if(roomCbb.getSelectionModel().isEmpty() || borrowedByShift.getSelectionModel().isEmpty()){
            statusLbl.setText("Room or Shift has not been selected.");
        } else if(totalRoomTxt.getText().matches("\\d+") && Integer.parseInt(totalRoomTxt.getText()) < 5){
            statusLbl.setText("Room can only be borrowed when there are at least 5 members.");
        }else if(totalRoomTxt.getText().matches("\\d+") == false){
            statusLbl.setText("Value is invalid! Please to check it!");
        }else{
            DiscussionRoomService disService = new DiscussionRoomService();
            RetristersBorrowed retristerDisRoom = new RetristersBorrowed();

            retristerDisRoom.setDisID(disName.getDisName());
            retristerDisRoom.setReaderID(GenaralLoginController.getReaderIdOfLogin());
            retristerDisRoom.setBorrowedByShift(shift);
            retristerDisRoom.setDescription(totalRoomTxt.getText());
            System.out.println("Gia tri cua nowDate: "+ retristerDisRoom.getDisDate());
            int result = disService.handleRegisterBorrowedDisRoom(retristerDisRoom, disDate);
            if (result > 0) {
                statusLbl.setText("Register to borrow discussion room successfully. Admin is processing.");
                statusLbl.setStyle("-fx-text-fill:green; -fx-font-size:16px");
            }else{
                statusLbl.setText("Room has been borrowed!");
                statusLbl.setStyle("-fx-text-fill:red; -fx-font-size:16px");
            }

        }
    }
}
