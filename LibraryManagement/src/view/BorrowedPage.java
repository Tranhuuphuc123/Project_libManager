package view;

import controller.GenaralLoginController;
import data.BorrowedDocumentService;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.BorrowAndReturnBook;

import java.util.Date;

public class BorrowedPage extends BorderPane {
    private Label lblTitleRight;
    private VBox vbLeft, vbRight;
    protected TableView<BorrowAndReturnBook> tableView = new TableView<>();
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";

    public BorrowedPage(){
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

        BorrowedDocumentService brr = new BorrowedDocumentService();
        ObservableList<BorrowAndReturnBook> arr = brr.getBorrowedDocument(GenaralLoginController.getReaderIdOfLogin());
        tableView.getItems().addAll(arr);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbSubRight.getChildren().add(tableView);
        vbRight.getChildren().add(vbSubRight);

        //===============================================================

        vbRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        lblTitleRight.prefWidthProperty().bind(vbRight.widthProperty());

        this.setCenter(vbRight);
    }
}
