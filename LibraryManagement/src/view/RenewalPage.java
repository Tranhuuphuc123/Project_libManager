package view;

import controller.GenaralLoginController;
import data.RenewDocumentService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.BorrowAndReturnBook;

import java.time.LocalDate;
import java.util.Date;


public class RenewalPage extends BorderPane {
    private VBox vbRight,  renewalVbRight;

    private String titleFormat = "-fx-background-color:#FCEABF ; -fx-text-fill:#8A660D;" +
            "-fx-font-size:18px; -fx-font-weight:700;";
    private String titleTableFormat ="-fx-font-size:18px";
    private  TableView<BorrowAndReturnBook> table;
    private boolean isUpdateSelected  = false;
    protected ObservableList<BorrowAndReturnBook> renewList = null;
    protected RenewDocumentService renewService = new RenewDocumentService();
    protected GenaralLoginController currentReader = new GenaralLoginController();
    protected String readerID = currentReader.getReaderIdOfLogin();

    public RenewalPage(){
        vbRight = new VBox();
        vbRight.setMargin(vbRight, new Insets(50));
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
        TableColumn<BorrowAndReturnBook, Integer> indexCol = new TableColumn<>("#");
        TableColumn<BorrowAndReturnBook, String> barcodeCol = new TableColumn<>("Barcode");
        TableColumn<BorrowAndReturnBook, String> titleCol = new TableColumn<>("Title Book");
        TableColumn<BorrowAndReturnBook, LocalDate> renewalCol = new TableColumn<>("Renewal Date");
        TableColumn<BorrowAndReturnBook, Date> borrowedCol = new TableColumn<>("Borrowed Date");
        TableColumn<BorrowAndReturnBook, Date> returnedCol = new TableColumn<>("Returned Date");
        TableColumn<BorrowAndReturnBook, Node> stateBtnCol = new TableColumn<>("Status");

        // === Add column into table
        table.getColumns().addAll(indexCol,barcodeCol, titleCol, renewalCol,borrowedCol, returnedCol, stateBtnCol);

        //======= titleCol, borrowedCol, returnedCol =========
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("bookID")); barcodeCol.setStyle(titleTableFormat);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("readerID")); titleCol.setStyle(titleTableFormat);
        borrowedCol.setCellValueFactory(new PropertyValueFactory<>("startDate")); borrowedCol.setStyle(titleTableFormat);
        returnedCol.setCellValueFactory(new PropertyValueFactory<>("retdate")); returnedCol.setStyle(titleTableFormat);
        renewalCol.setStyle(titleTableFormat);
        stateBtnCol.setStyle(titleTableFormat);

        //======= indexCol =========
        indexCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BorrowAndReturnBook, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<BorrowAndReturnBook, Integer> param) {
                return new ReadOnlyObjectWrapper<>(table.getItems().indexOf(param.getValue()) + 1);
            }
        });
        indexCol.setStyle(titleTableFormat);

        //======= renewalCol =========
        renewalCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BorrowAndReturnBook, LocalDate>, ObservableValue<LocalDate>>() {
            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<BorrowAndReturnBook, LocalDate> borrowAndReturnBookLocalDateCellDataFeatures) {
                LocalDate nowTime = LocalDate.now();
                return new SimpleObjectProperty<LocalDate>(nowTime);
            }
        });

        //======= stateBtnCol =========
        stateBtnCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BorrowAndReturnBook, Node>, ObservableValue<Node>>() {
            private Button button = null;
            private boolean isButtonDisabled = false;
            @Override
            public ObservableValue<Node> call(TableColumn.CellDataFeatures<BorrowAndReturnBook, Node> dataFeatures) {
                button = new Button("Renew here");
                button.setOnAction(et -> {
                    if(!dataFeatures.getValue().getIsUpdated()){

                        renewService.updateRenewal(readerID,barcodeCol.getCellData(dataFeatures.getValue()));
                        button.setText("Successfully");
                        table.getItems().clear();
                        table.getItems().addAll(renewService.getRenewalDocument(readerID));
                        isButtonDisabled = true;
                        dataFeatures.getValue().setIsUpdated(true);
                    }
                });
                if (isButtonDisabled) {
                    button.setDisable(true);  // nó cho giá tri
                }

                button.setStyle("-fx-font-size:14; -fx-background-color:#02D1FA; -fx-text-fill:#ffffff");

                return new SimpleObjectProperty<Node>(button);
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
