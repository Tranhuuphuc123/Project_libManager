package view;

import controller.GenaralLoginController;
import data.BorrowedDocumentService;
import data.RenewDocumentService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.BorrowAndReturnBook;

import java.time.LocalDate;
import java.util.Date;


public class RenewalPage extends BorderPane {
    private VBox vbRight,  renewalVbRight;
    private  TableView<BorrowAndReturnBook> table;
    private boolean isUpdateSelected  = false;

    protected ObservableList<BorrowAndReturnBook> renewList = null;
    protected RenewDocumentService renewService = new RenewDocumentService();
    protected String readerID = GenaralLoginController.getReaderIdOfLogin();

    private String titleTableFormat ="-fx-font-size:18px";
    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";


    public RenewalPage(){
        vbRight = new VBox();
//        vbRight.setMargin(vbRight, new Insets(50));
        vbRight.setAlignment(Pos.TOP_CENTER);
        vbRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Label lblTitleRight = new Label("Renewal Document");
        lblTitleRight.setStyle(titleFormat);
        lblTitleRight.setPadding(new Insets(10));
        lblTitleRight.setMinHeight(45);
        lblTitleRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vbRight.getChildren().add(lblTitleRight);

        //================SUB RIGHT TABLE ===================
        VBox vbSubRight = new VBox();
        vbSubRight.setAlignment(Pos.CENTER_LEFT); // căn giữa trái
        vbSubRight.setSpacing(10);
        vbSubRight.setPadding(new Insets(20));

        table = new TableView<>();
        // === Create index column
        TableColumn<BorrowAndReturnBook, String> barcodeCol = new TableColumn<>("Barcode");
        TableColumn<BorrowAndReturnBook, String> titleCol = new TableColumn<>("Title Book");
        TableColumn<BorrowAndReturnBook, LocalDate> renewalCol = new TableColumn<>("Renewal Date");
        TableColumn<BorrowAndReturnBook, Date> borrowedCol = new TableColumn<>("Borrowed Date");
        TableColumn<BorrowAndReturnBook, Date> returnedCol = new TableColumn<>("Returned Date");
        TableColumn<BorrowAndReturnBook, Node> stateBtnCol = new TableColumn<>("Status");

        // === Add column into table
        table.getColumns().addAll(barcodeCol, titleCol, renewalCol,borrowedCol, returnedCol, stateBtnCol);

        //======= titleCol, borrowedCol, returnedCol =========
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("bookID")); barcodeCol.setStyle(titleTableFormat);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("readerID")); titleCol.setStyle(titleTableFormat);
        borrowedCol.setCellValueFactory(new PropertyValueFactory<>("startDate")); borrowedCol.setStyle(titleTableFormat);
        returnedCol.setCellValueFactory(new PropertyValueFactory<>("retdate")); returnedCol.setStyle(titleTableFormat);
        renewalCol.setStyle(titleTableFormat);
        stateBtnCol.setStyle(titleTableFormat);

        //======= renewalCol =========
        renewalCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BorrowAndReturnBook, LocalDate>, ObservableValue<LocalDate>>() {
            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<BorrowAndReturnBook, LocalDate> borrowAndReturnBookLocalDateCellDataFeatures) {
                LocalDate nowTime = LocalDate.now();
                return new SimpleObjectProperty<LocalDate>(nowTime);
            }
        });

        //======= stateBtnCol =========
        stateBtnCol.setCellFactory(col -> new TableCell<BorrowAndReturnBook, Node>() {
            private final Button button = new Button("Renewal");
            private boolean isDisabled = false;
            {
                button.setOnAction(event -> {
                    BorrowAndReturnBook rowData = getTableView().getItems().get(getIndex());
                    renewService.updateRenewal(readerID, barcodeCol.getCellData(rowData));
                    isDisabled = true;
                    button.setDisable(true);
                    button.setText("Renewed");
                });
            }

            @Override
            protected void updateItem(Node item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                    button.setStyle("-fx-font-size:14; -fx-background-color:#02D1FA; -fx-text-fill:#ffffff");
                    if (isDisabled) {
                        button.setDisable(true);
                    } else {
                        button.setDisable(false);
                    }
                }
            }
        });

        // === push data into table
        renewList = renewService.getRenewalDocument(readerID);
        table.getItems().addAll(renewList);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbSubRight.getChildren().add(table);
        vbRight.getChildren().add(vbSubRight);
        vbRight.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        lblTitleRight.prefWidthProperty().bind(vbRight.widthProperty());

        this.setCenter(vbRight);
    }

}
