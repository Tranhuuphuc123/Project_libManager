package view;

import controller.GenaralLoginController;
import data.ReturnedDocumentService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.BorrowAndReturnBook;

public class ReturnedPage extends BorderPane{
    private VBox vbRight,  renewalVbRight;
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    public ReturnedPage(){
        vbRight = new VBox();
        vbRight.setMargin(vbRight, new Insets(50));
        vbRight.setAlignment(Pos.TOP_CENTER);
        vbRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Label lblTitleRight = new Label("Returned Document");
        lblTitleRight.setStyle(titleFormat);
        lblTitleRight.setPadding(new Insets(10));
        lblTitleRight.setMinHeight(45);
        vbRight.getChildren().add(lblTitleRight);
        lblTitleRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        VBox vbSubRight = new VBox();
        vbSubRight.setAlignment(Pos.CENTER_LEFT); // căn giữa trái
        vbSubRight.setSpacing(10);
        vbSubRight.setPadding(new Insets(20));

        TableView<BorrowAndReturnBook> table = new TableView<>();

        TableColumn<BorrowAndReturnBook, Integer> indexCol = new TableColumn<>("#");
        TableColumn<BorrowAndReturnBook, String> barcodeCol = new TableColumn<>("Barcode");
        TableColumn<BorrowAndReturnBook, String> titleCol = new TableColumn<>("Title");
        TableColumn<BorrowAndReturnBook, String> borrowedCol = new TableColumn<>("Borrowed Date");
        TableColumn<BorrowAndReturnBook, String> returnedCol = new TableColumn<>("Returned Date");
        table.getColumns().addAll(indexCol, barcodeCol, titleCol, borrowedCol, returnedCol);

        String style = "-fx-font-size:18";

        indexCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BorrowAndReturnBook, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<BorrowAndReturnBook, Integer> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1);
            }
        });
        indexCol.setStyle(style);

        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("bookID"));barcodeCol.setStyle(style);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("readerID")); titleCol.setStyle(style);
        borrowedCol.setCellValueFactory(new PropertyValueFactory<>("startDate")); borrowedCol.setStyle(style);
        returnedCol.setCellValueFactory(new PropertyValueFactory<>("retdate")); returnedCol.setStyle(style);


        ReturnedDocumentService returnService = new ReturnedDocumentService();
        ObservableList<BorrowAndReturnBook> arr = returnService.getReturnedDocument(GenaralLoginController.getReaderIdOfLogin());
        table.getItems().addAll(arr);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbSubRight.getChildren().add(table);
        vbRight.getChildren().add(vbSubRight);

        vbRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        lblTitleRight.prefWidthProperty().bind(vbRight.widthProperty());

        this.setCenter(vbRight);
    }
}
