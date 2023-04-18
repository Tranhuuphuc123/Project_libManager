package view;

import data.FeedBackService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class FeedbackPage extends BorderPane {
    private TextField commentField;
    private TextArea commentArea;
    private Button contactBtn;
    private Label statusLbl = new Label();
    private CheckBox complaintCheckBox, problemCheckBox, suggestionCheckBox, praiseCheckBox;
    private String format = "-fx-font-size: 16px; -fx-font-weight: 500;";
    private String formatBtn = "-fx-text-fill:#FFFFFF; -fx-background-color:#B77906;-fx-font-size:16px;";
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
                                    "-fx-font-size:18px; -fx-font-weight:700;";

    private String getTitleCheckBox;
    public FeedbackPage(){
        Label typeOfLabel = new Label("What kind of comment would you like to send?");

        complaintCheckBox = new CheckBox("Complaint");
        problemCheckBox = new CheckBox("Problem");
        suggestionCheckBox = new CheckBox("Suggestion");
        praiseCheckBox = new CheckBox("Praise");

        typeOfLabel.setStyle(format);
        complaintCheckBox.setStyle(format);
        problemCheckBox.setStyle(format);
        suggestionCheckBox.setStyle(format);
        praiseCheckBox.setStyle(format);

        CheckBox[] checkBoxes = {complaintCheckBox,problemCheckBox,suggestionCheckBox,praiseCheckBox};
        CheckBox checkBox;
        for(int i=0; i<checkBoxes.length; i++){
            final int index = i;
            checkBox = checkBoxes[i];
            checkBox.setOnAction(event2->{
                for(int j=0; j<checkBoxes.length; j++){
                    if(j != index)
                        checkBoxes[j].setSelected(false);
                    else getTitleCheckBox = checkBoxes[j].getText();
                }
            });
        }

        VBox checkVbox = new VBox();
        checkVbox.setPadding(new Insets(20));
        checkVbox.getChildren().addAll(typeOfLabel, complaintCheckBox, problemCheckBox, suggestionCheckBox,praiseCheckBox);

        Label questionLabel = new Label("What about the library do you want to comment on?*");
        commentField = new TextField();
        commentField.setMinHeight(35);

        Label areaLabel = new Label("Enter your comments in the space provided below:");
        commentArea = new TextArea();
        commentArea.setPrefRowCount(5);
        commentArea.setPrefColumnCount(5);

        questionLabel.setStyle(format);
        areaLabel.setStyle(format);

        VBox commentVbox = new VBox();
        commentVbox.getChildren().addAll(questionLabel, commentField, areaLabel,commentArea);
        commentVbox.setPadding(new Insets(20));


        contactBtn = new Button("Contact Us");
        contactBtn.setStyle(formatBtn);
        contactBtn.setOnAction(event1 -> contactBtnCheck());
        VBox btnVbox = new VBox();
        btnVbox.getChildren().add(contactBtn);
        btnVbox.setAlignment(Pos.CENTER_RIGHT);
        btnVbox.setPadding(new Insets(15,15,0,0));


        Label feedBackLabel = new Label("Library Feedback Form");
        feedBackLabel.setPadding(new Insets(10));
        feedBackLabel.setStyle(titleFormat);
        feedBackLabel.setPrefHeight(45);
        feedBackLabel.setAlignment(Pos.CENTER_LEFT);
        VBox feedBackLabelVb = new VBox();
        feedBackLabelVb.getChildren().add(feedBackLabel);
        feedBackLabelVb.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        Label noteLabel = new Label("Note: All your feedback will be sent anonymously!");
        noteLabel.setPadding(new Insets(10));
        noteLabel.setStyle("-fx-text-fill:#5B8E9C; -fx-font-size:14px");
        noteLabel.setPrefHeight(40);
        noteLabel.setAlignment(Pos.CENTER_LEFT);

        VBox feedBackVbox = new VBox();
        feedBackLabel.prefWidthProperty().bind(feedBackVbox.widthProperty());
        feedBackVbox.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        feedBackVbox.getChildren().addAll(feedBackLabelVb,checkVbox,commentVbox, statusLbl, btnVbox, noteLabel);

        this.setCenter(feedBackVbox);
    }

    protected void contactBtnCheck(){
        statusLbl.setPadding(new Insets(10,0,0,10));
        if(complaintCheckBox.isSelected() || problemCheckBox.isSelected() || suggestionCheckBox.isSelected() ||
                praiseCheckBox.isSelected() == true){
            String comment = commentField.getText();
            String area = commentArea.getText();
            if(comment.isEmpty() || area.isEmpty()){
                statusLbl.setStyle(format + "-fx-text-fill: red");
                statusLbl.setText("Please enter all fields.");
            }else{
                FeedBackService feedService = new FeedBackService();
                int resultFeed;
                resultFeed = feedService.addFeedBack(getTitleCheckBox, commentField.getText(), commentArea.getText());
                if(resultFeed > 0 ){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("Successfully");
                    alert.setContentText("Your feedback has been sent successfully.");
                    alert.showAndWait();
                    statusLbl.setStyle(format + "-fx-text-fill:green");
                    statusLbl.setText("Your feedback has successfully!");
                    commentField.clear();
                    commentArea.clear();
                }

            }
        }else{
            statusLbl.setStyle(format+ "-fx-text-fill:red");
            statusLbl.setText("Please enter all fields.");
        }
    }
}
