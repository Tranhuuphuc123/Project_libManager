package view;

import com.jfoenix.controls.JFXButton;
import data.DepartService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Department;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public class DepartmentPage extends Application {
    private BorderPane root;
    private GridPane gridpane_Left;
    private InputStream input;
    private VBox vboxLeft, vboxRight,vsubbox_tabledep;
    private ComboBox combobox_status;
    private TableView<Department>table_Derpart;
    private TableColumn<Department, String>column_DepID, column_DepName, column_DepStatus;
    private JFXButton btn_Insert, btn_Update, btn_Clean, btn_Refesh, btn_Search,btn_ShowAll;
    private Label label_DepID, label_DepName, label_DepStatus;
    private TextField field_DepID, field_DepName, field_Search;
    private ImageView add_ImgView, imgView_Insert,imgView_Update,imgView_Clean,imgView_ShowAll,imgView_Refesh,imgView_Search;
    private Image img;



    public static void main(String[] args) {
        launch(args);
    }
    /*==================call class, controller, serrvice===============*/
    DepartService departService = new DepartService();
    Department depart = new Department();


    @Override
    public void start(Stage primaryStage) {
            Depart_View();
            setCombobox_Status();
            selectItem_Depart();
            showDepartment();
            showAllDepart();

    }

    public void Depart_View(){
      //set icon DerpartPage
        Stage stage = new Stage();
        input = getClass().getResourceAsStream("/icon/ReaderUI/logo5.png");
        Image icon = new Image(input);
        stage.getIcons().add(icon);

    /*------------------set vboxLeft-----------------------*/
        vboxLeft = new VBox();
        vboxLeft.setPrefWidth(290);
        vboxLeft.setPadding(new Insets(5));
        vboxLeft.setAlignment(Pos.TOP_LEFT);
        //add css on vboxLeft
        vboxLeft.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        vboxLeft.getStyleClass().add("thietlap_vboxLeft");

        label_DepID = new Label("DepID");label_DepID.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());label_DepID.getStyleClass().add("fill_Label");
        label_DepName= new Label("DepName");label_DepName.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());label_DepName.getStyleClass().add("fill_Label");
        label_DepStatus = new Label("Status");label_DepStatus.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());label_DepStatus.getStyleClass().add("fill_Label");

        field_DepID = new TextField();
        field_DepName = new TextField();
        combobox_status = new ComboBox();


        //add component for vboxLeft
        HBox box_img = new HBox(4);
        box_img.getChildren().add(setImgView());

        gridpane_Left = new GridPane();
        gridpane_Left.setHgap(4);//set k/c |.| column
        gridpane_Left.setVgap(4);//set k/c doc giua cac cot

        gridpane_Left.add(label_DepID,0,0);
        gridpane_Left.add(field_DepID,1,0);
        field_DepID.setPrefWidth(170);
        //set scale chieu doc
        field_DepID.setScaleY(0.70);

        gridpane_Left.add(label_DepName,0,1);
        gridpane_Left.add(field_DepName,1,1);
        field_DepName.setPrefWidth(170);
        field_DepName.setScaleY(0.70);

        gridpane_Left.add(label_DepStatus,0,2);
        gridpane_Left.add(combobox_status,1,2);
        combobox_status.setPrefWidth(170);
        combobox_status.setScaleY(0.70);

        //add gridpane and Hbox_img on vboxLeft
        vboxLeft.getChildren().addAll(box_img,gridpane_Left);



     /*---------------------vboxRight---------------*/
        vboxRight = new VBox();
        vboxRight.setPadding(new Insets(5));
        vboxRight.setAlignment(Pos.TOP_CENTER);
        //add css for vboxRight
        vboxRight.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        vboxRight.getStyleClass().add("thietlap_vboxRight");

        //Button_bả há textfield, button btn_Search,btn_refesh,btn_ShowAll
        ButtonBar btnBar = new ButtonBar();
        vboxRight.setMargin(btnBar, new Insets(5,5,10,0));
        field_Search = new TextField();field_Search.setPrefWidth(150);

        btn_Search = new JFXButton("Search");
        btn_Search.setGraphic(img_btnSearch());
        btn_Search.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());btn_Search.getStyleClass().add("title_Button");
        btn_Search.setTranslateX(-10);

        btn_ShowAll = new JFXButton("ShowAll");
        btn_ShowAll.setGraphic(img_btnShowAll());
        btn_ShowAll.setTranslateX(-50);
        btn_ShowAll.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());btn_ShowAll.getStyleClass().add("title_Button");

        btn_Refesh = new JFXButton("Refesh");
        btn_Refesh.setGraphic(img_btnRefesh());
        btn_Refesh.setTranslateX(-50);
        btn_Refesh.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());btn_Refesh.getStyleClass().add("title_Button");

        //..add component on btnBar(Buttonbar)
        btnBar.getButtons().addAll(field_Search,btn_Search,btn_ShowAll,btn_Refesh);

        //table_Emp
        table_Derpart = new TableView<Department>();
        column_DepID = new TableColumn<>("DepID");
        column_DepName = new TableColumn<>("DepName");
        column_DepStatus = new TableColumn<>("Status");

        //set size column
        table_Derpart.setPrefSize(1050,350);
        column_DepID.setPrefWidth(120);
        column_DepName.setPrefWidth(150);
        column_DepStatus.setPrefWidth(140);
        //add column on table_Depart
        table_Derpart.getColumns().addAll(column_DepID,column_DepName,column_DepStatus);
        //add table_Depart on scrollpane
        ScrollPane scrollPane_tableDepart = new ScrollPane(table_Derpart);

        vsubbox_tabledep = new VBox();
        vsubbox_tabledep.setAlignment(Pos.CENTER_LEFT);
        vsubbox_tabledep.setSpacing(0);
        vsubbox_tabledep.setPadding(new Insets(0));
        //add scrollpane on vsubbox_tabeldep
        vsubbox_tabledep.getChildren().add(scrollPane_tableDepart);

        //buutonbar btnbar01
        ButtonBar btnBar01 = new ButtonBar();
        vboxRight.setMargin(btnBar01,new Insets(30,0,0,0));
        btn_Insert = new JFXButton("Insert");
        btn_Update = new JFXButton("Updates");
        btn_Clean = new JFXButton("Clean");
        //set img, icon for button
        btn_Insert.setGraphic(img_btnInsert());
        //add css
        btn_Insert.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_Insert.getStyleClass().add("title_Button");

        btn_Update.setGraphic(img_btnUpdate());
        //add css
        btn_Update.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_Update.getStyleClass().add("title_Button");

        btn_Clean.setGraphic(img_btnClean());
        //add css
        btn_Clean.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_Clean.getStyleClass().add("title_Button");

        //them cac button vao trong ButtonBar co ten la btnBar01
        btnBar01.getButtons().addAll(btn_Insert,btn_Update,btn_Clean);

    /*---add all component on vboxRight---*/
        vboxRight.getChildren().addAll(btnBar,vsubbox_tabledep,btnBar01);

        //-----------------------------------------Runtime program------------------------------------------------
        /*runtime for javaFX Application*/
        root = new BorderPane();
        //thiết lập khoảng cách margin cho vboxRight cách vboxLeft là 10px bền lề trái tính qua;
        root.setMargin(vboxRight, new Insets(0,0,0,10));
        root.setPadding(new Insets(10));
        root.setLeft(vboxLeft);
        root.setCenter(vboxRight);

        //create scence and show stage
        Scene scene = new Scene(root,850,500);
        stage.setScene(scene);
        stage.setTitle("DepartmentPage");
        stage.show();

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
       /*==============action event method=======================*/

        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>Action event for Employee_View*<<<<<<<<<<<<<<<<<<*/
        //===================================================================

        //Actiont event btn_ShowAll
        btn_ShowAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleShowAll();
            }
        });

        //action event btn_Clean
        btn_Clean.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Clean_Depart();
            }
        });

        //Action event btn_Refesh
        btn_Refesh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Refesh_Depart();
            }
        });


        //set actiont event btn_Insert
        btn_Insert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleInsert();
            }
        });


        // set Update
        btn_Update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleUpdate();
            }
        });



        // set search
        btn_Search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleSearch();
            }
        });
     //end method DepartView
    }


 /*-----------------method set Imgview, image, icon--------------------*/
 public Node setImgView(){
     input = getClass().getResourceAsStream("/icon/Depart.png");
     img = new Image(input);
     add_ImgView = new ImageView(img);
     //set size
     add_ImgView.setFitHeight(127);
     add_ImgView.setFitWidth(60);
     //>> dich sang phai 60px
     add_ImgView.setTranslateX(80);
     return add_ImgView;
 }

    //set Image for button
    public ImageView img_btnInsert(){
        input = getClass().getResourceAsStream("/icon/Insert.png");
        img = new Image(input);
        imgView_Insert = new ImageView(img);
        //set size, pos
        imgView_Insert.setFitHeight(26);
        imgView_Insert.setFitWidth(26);
        return imgView_Insert;
    }
    //set load ImageView for btn_Update
    public ImageView img_btnUpdate(){
        input = getClass().getResourceAsStream("/icon/update.png");
        img = new Image(input);
        imgView_Update = new ImageView(img);
        //set Size
        imgView_Update.setFitWidth(26);
        imgView_Update.setFitHeight(26);
        return imgView_Update;
    }
    //set load ImgeView for btn_Clean
    public ImageView img_btnClean(){
        input = getClass().getResourceAsStream("/icon/clean.png");
        img = new Image(input);
        imgView_Clean = new ImageView(img);
        //set size
        imgView_Clean.setFitHeight(26);
        imgView_Clean.setFitWidth(26);
        return imgView_Clean;
    }

    //set load ImgView for btn_Search
    public ImageView img_btnSearch(){
        input = getClass().getResourceAsStream("/icon/search2.png");
        img = new Image(input);
        imgView_Search = new ImageView(img);
        //set size
        imgView_Search.setFitWidth(26);
        imgView_Search.setFitHeight(26);
        return imgView_Search;
    }
    public ImageView img_btnShowAll(){
        input = getClass().getResourceAsStream("/icon/Showall.png");
        img= new Image(input);
        imgView_ShowAll = new ImageView(img);
        //setsize
        imgView_ShowAll.setFitHeight(26);
        imgView_ShowAll.setFitWidth(26);
        return imgView_ShowAll;
    }

    public ImageView img_btnRefesh(){
        input = getClass().getResourceAsStream("/icon/refesh.png");
        img = new Image(input);
        imgView_Refesh = new ImageView(img);
        //set size
        imgView_Refesh.setFitWidth(26);
        imgView_Refesh.setFitHeight(26);
        return imgView_Refesh;
    }

    /*====================================method loadcombobox===========================*/
    public void setCombobox_Status(){
        ObservableList<String> loadCombobox_Status = FXCollections.observableArrayList("Active","Inactive",
                "Merged","Deleted","Pending");
        combobox_status.setPromptText("choose Status");
        combobox_status.setItems(loadCombobox_Status);
    }


    //method load data on table_Depart
    ObservableList<Department> listdepart;
    public void showAllDepart(){
        listdepart = departService.getAllDepart();
        column_DepID.setCellValueFactory(new PropertyValueFactory<>("depID"));
        column_DepName.setCellValueFactory(new PropertyValueFactory<>("depName"));
        column_DepStatus.setCellValueFactory(new PropertyValueFactory<>("depStatus"));

        table_Derpart.setItems(listdepart);
    }


    //methoc selcetItem_Depart
    public void selectItem_Depart(){
        table_Derpart.setOnMouseClicked(event->{
            //texxt user has choose row on table or no?
            if(event.getClickCount()==1){
                Department depart = table_Derpart.getSelectionModel().getSelectedItem();
                field_DepID.setText(depart.getDepID());
                field_DepName.setText(depart.getDepName());
                //select combobox
                String slect = String.valueOf(depart.getDepStatus());
                if(slect.equals("Active")){
                    combobox_status.setValue("Active");
                }else if(slect.equals("Inactive")){
                    combobox_status.setValue("Inactive");
                }else if(slect.equals("Merged")){
                    combobox_status.setValue("Merged");
                }else if(slect.equals("Deleted")){
                    combobox_status.setValue("Deleted");
                }else if(slect.equals("Pending")){
                    combobox_status.setValue("Pending");
                }
            }
        });
    }
    //---------------------------method load data ontbale------------------------------

    public void showDepartment(){
        listdepart = departService.getAllDepartment_condition();
        column_DepID.setCellValueFactory(new PropertyValueFactory<>("depID"));
        column_DepName.setCellValueFactory(new PropertyValueFactory<>("depName"));
        column_DepStatus.setCellValueFactory(new PropertyValueFactory<>("depStatus"));
    }

    //---------------method handleShowAll for btn_showAll
    public void handleShowAll(){
        listdepart = departService.getAllDepart();
        column_DepID.setCellValueFactory(new PropertyValueFactory<>("depID"));
        column_DepName.setCellValueFactory(new PropertyValueFactory<>("depName"));
        column_DepStatus.setCellValueFactory(new PropertyValueFactory<>("depStatus"));
       table_Derpart.setItems(listdepart);
    }
    //------------method Refesh and clean------------

    // method insert
    public void handleInsert(){
        try {
            if (
                    field_DepID.getText().isEmpty()
                            || field_DepName.getText().isEmpty()
                            || combobox_status.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                // pt headerText (null) loại bỏ đi tiêu đề mặc định của hộp thoại
                alert.setHeaderText(null);
                alert.setContentText("Please don't leave it blank!");
                // showAndwait dùng để hiển thị v chờ người dùng phàn hồi lỗi
                alert.showAndWait();
            } else{

                depart.setDepID(field_DepID.getText());
                depart.setDepName(field_DepName.getText());
                String select1 = (String) combobox_status.getValue();
                depart.setDepStatus(select1);

                departService.Insert_Department(depart);
                // tạo thông báo là đã insert thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("You have successfully added!");
                alert.showAndWait();

                // khi inssert xong thì tiến hành clear thông tin vừa nhập và show lên tablevie từ hai method đã viết trc đó
                Clean_Depart();
                showDepartment();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//end
    }
    //method update
    public void handleUpdate(){
        try{
            if (table_Derpart.getSelectionModel().getSelectedCells()==null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please select the line to update");
                alert.showAndWait();
            }else {
                depart.setDepID(field_DepID.getText());
                depart.setDepName(field_DepName.getText());
                String select1 = (String) combobox_status.getValue();
                depart.setDepStatus(select1);
                departService.Update_Department(depart);
                // tạo thông báo là đã insert thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("You have successfully added!");
                alert.showAndWait();

                // khi inssert xong thì tiến hành clear thông tin vừa nhập và show lên tablevie từ hai method đã viết trc đó
                Clean_Depart();
                showDepartment();
            }
        }catch (Exception e){
            e.printStackTrace();
        }//end
    }





    // method handleSearch
    public void handleSearch(){
        try{
            String maSearch = field_Search.getText();
            listdepart = departService.Search_Department(maSearch);
            if (listdepart != null && !listdepart.isEmpty()) {
                ObservableList<Department> data = FXCollections.observableArrayList(listdepart);
                table_Derpart.setItems(data);
            } else if(listdepart.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your search text!");
                alert.showAndWait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //method Clean
    public void Clean_Depart(){
        field_DepID.setText("");
        field_DepName.setText("");
        combobox_status.setValue(null);
    }

    //method refesh
    public void Refesh_Depart(){
        Clean_Depart();
        showAllDepart();
    }


//end
}
