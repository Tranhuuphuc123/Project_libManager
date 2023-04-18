package view;

import data.SearchDocumentService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Books;

public class SearchDocumentPage extends BorderPane {
    private TextField barTxt;
    private Button searchBtn;
    private TableView<Books> table;
    private Label message = new Label();
    private Label messageObj = new Label();
    ObservableList<Books> bookList;
    private SearchDocumentService searchService;

    private String titleTableFormat ="-fx-font-size:18px";
    private String format = "-fx-font-size: 16px; -fx-font-weight: 500;";
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    public SearchDocumentPage(){
        VBox searchVb = new VBox();

        Label titleLbl = new Label("Search Document");
        titleLbl.setStyle(titleFormat);
        titleLbl.setMinHeight(45);
        titleLbl.setAlignment(Pos.CENTER_LEFT);
        titleLbl.setPadding(new Insets(10));
        titleLbl.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // ===== Search bar
        barTxt  = new TextField();
        barTxt.setStyle(format);
        barTxt.setMinWidth(400);
        barTxt.setMinHeight(42);
        barTxt.setPromptText("Enter your search ");
        barTxt.setBorder(new Border(new BorderStroke(Color.rgb(179, 212, 195 ), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        barTxt.setOnAction(event -> handleSearch());

        searchBtn = new Button("Search");
        searchBtn.setMinWidth(50);
        searchBtn.setMinHeight(42);
        searchBtn.setBorder(new Border(new BorderStroke(Color.rgb(179, 212, 195 ), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        searchBtn.setStyle(format+ "-fx-font-weight:700; -fx-text-fill:#FFFFFF; -fx-background-color:#049A63");
        searchBtn.setOnMouseClicked(et->handleSearch());

        HBox barHb = new HBox();
        barHb.getChildren().addAll(barTxt, searchBtn);
        barHb.setAlignment(Pos.CENTER);
        barHb.setPadding(new Insets(10));

        HBox messageHb= new HBox();
        messageHb.getChildren().addAll(messageObj,message);

        // ===== Table
        table = new TableView<>();
        // === Create index column
        TableColumn<Books, Integer> indexCol = new TableColumn<>("#");
        TableColumn<Books, String> barcodeCol = new TableColumn<>("Barcode");
        TableColumn<Books, String> titleCol = new TableColumn<>("Title Book");
        TableColumn<Books, String> amountCol = new TableColumn<>("Amount");

        // === Add column into table
        table.getColumns().addAll(indexCol,barcodeCol, titleCol, amountCol);
        table.setPadding(new Insets(0,25,25,25));
        //======= titleCol, borrowedCol, returnedCol =========
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("bookBarCode"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("bookAmount"));

        barcodeCol.setStyle(titleTableFormat);
        titleCol.setStyle(titleTableFormat);
        amountCol.setStyle("-fx-alignment: center;" + titleTableFormat );
        indexCol.setResizable(false); indexCol.setMaxWidth(35);
        barcodeCol.setResizable(false); barcodeCol.setMaxWidth(135);
        titleCol.setMinWidth(120);
        amountCol.setMinWidth(90);
        //======= indexCol =========

        indexCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Books, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Books, Integer> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue())+1);
            }
        });

        indexCol.setStyle(titleTableFormat);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        searchVb.setSpacing(10);
        searchVb.getChildren().addAll(titleLbl, barHb, messageHb, table);
        titleLbl.prefWidthProperty().bind(searchVb.widthProperty());
        searchVb.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setCenter(searchVb);
    }


    public void handleSearch(){
        if(barTxt.getText() != null){
            searchService = new SearchDocumentService();
            bookList = searchService.searchBook(barTxt.getText());
            if(! bookList.isEmpty()){
                messageObj.setText(" ");
                message.setText(" ");
                table.getItems().clear();
                table.getItems().addAll(bookList);
            }else{
                messageObj.setPadding(new Insets(0,0,10,110));
                message.setPadding(new Insets(0,0,10,0));
                messageObj.setStyle(format + "-fx-text-fill:green; -fx-font-weight:700");
                message.setStyle(format + "-fx-text-fill:green");
                messageObj.setText(barTxt.getText());
                message.setText(" not found");
            }

        }
    }



}
