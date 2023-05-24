package view;

import com.jfoenix.controls.JFXButton;
import data.EmpService;
import data.AdminService;
import javafx.application.Application;
import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.LightBase;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Employee;
import model.Reader;
import model.getData;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static controller.AdminController.isValidAddress;
import static controller.AdminController.isValidPassword;

/*page Employee in Pand Adminpane*/
public class EmployeePage extends Application {
    private Image img;
    private ImageView imgView,imgView_Insert, imgView_Update,imgView_Clean,imgView_Refesh,img_ViewSearch,img_ViewShowAll;
    private InputStream input;
    private TableView<Employee> table_EMployee;
    private TableColumn<Employee,String>column_EmpID, column_EmpName, column_EmpPhone,column_Adrress,column_depID,column_EmpPos,column_EmpSalary,
            column_EmpPass, colummn_EmpBarCode, column_EmpStatus,column_Gender;
    private TableColumn<Employee, Date>column_Birth;
    private TextField field_EmpID, field_EmpName, field_EmpPhone, field_EmpGender, field_EmpAdrress, field_EmpPos, field_EmpSal,field_EmpBarCode, field_Search;
    private Label label_EmpID, label_EmpName,label_EmpPhone,label_Gender, label_Address,label_EmpPass,label_EmpSal, label_EmCode, label_DepID,label_Pos,label_Status,label_Birth;
    private Label title;
    private PasswordField pass_EmpPass;
    private DatePicker datePicker_Birth;
    private ComboBox combobox_Status,combobox_Gender,combobox_DepID;

    private JFXButton btn_Inssert, btn_Update, btn_Clean, btn_Refresh, btn_ShowAll, btn_Search;
    private VBox vboxLeft, vboxRight;
    private GridPane gridPane_left;
    private BorderPane root;

    /*=============================================================================================*/
    /*main view */
    public static void main(String[] args) {
        launch(args);
    }

    //khai báo các class
    EmpService empService = new EmpService();
    Employee   employee = new Employee();
    AdminService adminService = new AdminService();

    @Override
    public void start(Stage primaryStage) {
        Employee_View();
        setcombobox_DepID();
        setCombobox_Gender();
        setCombobox_Status();
        setdate_Picker();

        //show data on tbale_EMployee
        showEmployee();
        //event mouseclicked on tbale_Employee
        selectiTem_Emp();

    }
    /*end main view*/

    /*===============================================================================================*/
    public void Employee_View(){
        //Create a logo
        Stage stage = new Stage();
        input = getClass().getResourceAsStream("/icon/ReaderUI/logo5.png");
        Image icon = new Image(input);
        stage.getIcons().add(icon);

//-----------------------------------------------------------------------------------
        /* set vboxLeft*/
        vboxLeft = new VBox();
        vboxLeft.setPrefWidth(290);
        vboxLeft.setPadding(new Insets(5));
        vboxLeft.setAlignment(Pos.TOP_LEFT);
        //dan css vao vboxLeft
        vboxLeft.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
        vboxLeft.getStyleClass().add("thietlap_vboxLeft");


        label_EmpID = new Label("EmpID"); label_EmpID.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_EmpID.getStyleClass().add("fill_Label");
        label_EmpName = new Label("EmpName");label_EmpName.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_EmpName.getStyleClass().add("fill_Label");
        label_EmpPhone=new Label("EmpPhone");label_EmpPhone.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_EmpPhone.getStyleClass().add("fill_Label");
        label_Birth = new Label("Birth");label_Birth.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_Birth.getStyleClass().add("fill_Label");
        label_Gender = new Label("Gender");label_Gender.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_Gender.getStyleClass().add("fill_Label");
        label_Address= new Label("Address");label_Address.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_Address.getStyleClass().add("fill_Label");
        label_DepID = new Label("DepID");label_DepID.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_DepID.getStyleClass().add("fill_Label");
        label_Pos= new Label("Possition");label_Pos.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_Pos.getStyleClass().add("fill_Label");
        label_EmpSal = new Label("Salary");label_EmpSal.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_EmpSal.getStyleClass().add("fill_Label");
        label_EmpPass = new Label("PassWord");label_EmpPass.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_EmpPass.getStyleClass().add("fill_Label");
        label_EmCode = new Label("BarCode");label_EmCode.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_EmCode.getStyleClass().add("fill_Label");
        label_Status = new Label("Status");label_Status.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());label_Status.getStyleClass().add("fill_Label");

        field_EmpID = new TextField();
        field_EmpName = new TextField();
        field_EmpPhone = new TextField();
        datePicker_Birth = new DatePicker();
        combobox_Gender = new ComboBox();
        field_EmpAdrress = new TextField();
        combobox_DepID = new ComboBox();
        field_EmpPos = new TextField();
        field_EmpSal = new TextField();
        pass_EmpPass = new PasswordField();
        field_EmpBarCode = new TextField();
        imgView = new ImageView();
        combobox_Status = new ComboBox();

        /*add all control at row and clolumn*/
        //image set
        HBox box_Img = new HBox(4);
        box_Img.getChildren().add(EmpImage_View());

        //set layout Gritgrand(same gridLayout)
        gridPane_left = new GridPane();
        gridPane_left.setHgap(4); // set khoảng cách ngang giữa các cột
        gridPane_left.setVgap(0); // set khoảng cách dọc giữa các hàng

        gridPane_left.add(label_EmpID,0,0);
        gridPane_left.add(field_EmpID,1,0);
        field_EmpID.setPrefWidth(170);
        field_EmpID.setScaleY(0.70);

        gridPane_left.add(label_EmpName,0,1);
        gridPane_left.add(field_EmpName,1,1);
        field_EmpName.setPrefWidth(170);
        field_EmpName.setScaleY(0.70);

        gridPane_left.add(label_EmpPhone,0,2);
        gridPane_left.add(field_EmpPhone,1,2);
        field_EmpPhone.setPrefWidth(170);
        field_EmpPhone.setScaleY(0.70);

        gridPane_left.add(label_Birth,0,3);
        gridPane_left.add(datePicker_Birth,1,3);
        datePicker_Birth.setPrefWidth(170);
        datePicker_Birth.setScaleY(0.70);

        gridPane_left.add(label_Gender,0,4);
        gridPane_left.add(combobox_Gender,1,4);
        combobox_Gender.setPrefWidth(170);
        combobox_Gender.setScaleY(0.70);

        gridPane_left.add(label_Address,0,5);
        gridPane_left.add(field_EmpAdrress,1,5);
        field_EmpAdrress.setPrefWidth(170);
        field_EmpAdrress.setScaleY(0.70);

        gridPane_left.add(label_DepID,0,6);
        gridPane_left.add(combobox_DepID,1,6);
        combobox_DepID.setPrefWidth(170);
        combobox_DepID.setScaleY(0.70);

        gridPane_left.add(label_Pos,0,7);
        gridPane_left.add(field_EmpPos,1,7);
        field_EmpPos.setPrefWidth(170);
        field_EmpPos.setScaleY(0.70);

        gridPane_left.add(label_EmpSal,0,8);
        gridPane_left.add(field_EmpSal,1,8);
        field_EmpSal.setPrefWidth(170);
        field_EmpSal.setScaleY(0.70);

        gridPane_left.add(label_EmpPass,0,9);
        gridPane_left.add(pass_EmpPass,1,9);
        pass_EmpPass.setPrefWidth(170);
        pass_EmpPass.setScaleY(0.70);

        gridPane_left.add(label_EmCode,0,10);
        gridPane_left.add(field_EmpBarCode,1,10);
        field_EmpBarCode.setPrefWidth(170);
        field_EmpBarCode.setScaleY(0.65);

        gridPane_left.add(label_Status,0,11);
        gridPane_left.add(combobox_Status,1,11);
        combobox_Status.setPrefWidth(170);
        combobox_Status.setScaleY(0.65);

        //add gridpane_left and Hbox on vboxLeft
        vboxLeft.getChildren().addAll(box_Img, gridPane_left);


        //-------------------------------------------------------
        /* Create the vboxRight*/
        vboxRight = new VBox(); // Add this line to initialize vboxRight
//      vboxRight.setPrefWidth(500);
        vboxRight.setPadding(new Insets(5));
        vboxRight.setAlignment(Pos.TOP_CENTER);
        //add css for vboxRight
        vboxRight.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
        vboxRight.getStyleClass().add("thietlap_vboxRight");

        //name titlte
//        title = new Label("Employee Page");
//        title.setPadding(new Insets(10));
//        title.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
//        title.getStyleClass().add("title");
//        vboxRight.getChildren().add(title);


        //table
        table_EMployee = new TableView<Employee>();
        column_EmpID = new TableColumn<>("EmpID");
        column_EmpName = new TableColumn<>("EmpName");
        column_EmpPhone = new TableColumn<>("Phone");
        column_Birth = new TableColumn<>("Birth");
        column_Gender = new TableColumn<>("Gender");
        column_Adrress = new TableColumn<>("Address");
        column_depID = new TableColumn<>("DepID");
        column_EmpPos = new TableColumn<>("Possition");
        column_EmpSalary = new TableColumn<>("Salary");
        column_EmpPass = new TableColumn<>("Pass");
        colummn_EmpBarCode = new TableColumn<>("Barcode");
        column_EmpStatus = new TableColumn<>("Status");

        VBox vbox_table = new VBox();
        vbox_table.setAlignment(Pos.CENTER_LEFT);
        vbox_table.setSpacing(0);
        vbox_table.setPadding(new Insets(0));

        table_EMployee.getColumns().addAll(column_EmpID,column_EmpName,column_EmpPhone,column_Birth
                ,column_Gender,column_Adrress,column_depID,column_EmpPos,column_EmpSalary,column_EmpPass
                ,colummn_EmpBarCode,column_EmpStatus);

        //add table vao vbox_Table
        ScrollPane scrollPane = new ScrollPane(table_EMployee);
        //set Size column, Table
        table_EMployee.setPrefSize(1050,500);
        column_EmpID.setPrefWidth(80);
        column_EmpName.setPrefWidth(100);
        column_Adrress.setPrefWidth(140);
        column_EmpPos.setPrefWidth(100);
        //setsize scrollpane
        scrollPane.setPrefSize(600,460);
        //add table at vbox_Table
        vbox_table.getChildren().add(scrollPane);
//----------------------------------------------------btn_Button-------------------------------------------------
        //btn_searh, and btn_Refesh
        ButtonBar box_Refesh_Search = new ButtonBar();
        vboxRight.setMargin(box_Refesh_Search, new Insets(5,5,10,0));
        field_Search = new TextField();
        field_Search.setPrefWidth(150);

        btn_Search = new JFXButton("Search");
        btn_Search.setGraphic(img_btnSearch());
        btn_Search.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
        btn_Search.getStyleClass().add("title_Button");
        //set scale TranslateX
        btn_Search.setTranslateX(-30);

        btn_ShowAll = new JFXButton("Show All");
        btn_ShowAll.setGraphic(img_btnShowAll());
        btn_ShowAll.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
        btn_ShowAll.getStyleClass().add("title_Button");
        btn_ShowAll.setTranslateX(-50);

        btn_Refresh = new JFXButton("Refesh");
        btn_Refresh.setGraphic(img_btnRefesh());
        btn_Refresh.getStyleClass().add(getClass().getResource("/css/Emp.css").toExternalForm());
        btn_Refresh.getStyleClass().add("title_Button");
        btn_Refresh.setTranslateX(-50);

        //add textfield, btn_search, btn_ShowAll, btn_Refesh on ButtonBar("box_Refesh_Search")
        box_Refesh_Search.getButtons().addAll(field_Search,btn_Search,btn_ShowAll,btn_Refresh);
        vboxRight.getChildren().add(box_Refesh_Search);


        //button_bar has 3 button: btn_Update,insert,clean
        ButtonBar box_button = new ButtonBar();
        //set margin for all button
        vboxRight.setMargin(box_button,new Insets(30,10,0,0));
        btn_Inssert = new JFXButton("Insert");
        btn_Update = new JFXButton("Updates");
        btn_Clean = new JFXButton("Clean");

        /*set icon,color... for Alls button*/
        btn_Inssert.setGraphic(img_btnInsert());
        //add css
        btn_Inssert.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
        btn_Inssert.getStyleClass().add("title_Button");

        btn_Update.setGraphic(img_btnUpdate());
        //add css
        btn_Update.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
        btn_Update.getStyleClass().add("title_Button");

        btn_Clean.setGraphic(img_btnClean());
        //add css
        btn_Clean.getStylesheets().add(getClass().getResource("/css/Emp.css").toExternalForm());
        btn_Clean.getStyleClass().add("title_Button");

        //them cac button vao trong ButtonBar co ten la box_button
        box_button.getButtons().addAll(btn_Inssert,btn_Update,btn_Clean);

//------------------------------------------------------------------------------------------------
        //add vboxRight
        vboxRight.getChildren().addAll(vbox_table,box_button);

        //-----------------------------------------------------------------------------------------
        /*runtime for javaFX Application*/
        root = new BorderPane();
        //thiết lập khoảng cách margin cho vboxRight cách vboxLeft là 10px bền lề trái tính qua;
        root.setMargin(vboxRight, new Insets(0,0,0,10));
        root.setPadding(new Insets(10));
        root.setLeft(vboxLeft);
        root.setCenter(vboxRight);

        //create scence and show stage
        Scene scene = new Scene(root,900,640);
        stage.setScene(scene);
        stage.setTitle("Employee Page");
        stage.show();


        //===================================================================
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
                Clean_Emp();
            }
        });

        //Action event btn_Refesh
        btn_Refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Refesh_Emp();
            }
        });


        //set actiont event btn_Insert
        btn_Inssert.setOnAction(new EventHandler<ActionEvent>() {
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
        //actiont Mouseclicke fot table_Employee



        //end method Employee_View
    }



    /*=======================================================method event Icon, Image=============================================*/
    /*setup ImageView*/
    public ImageView EmpImage_View(){
        input = getClass().getResourceAsStream("/icon/ReaderUI/logo4.png");
        img = new Image(input);
        imgView = new ImageView(img);
        // >> dich phai 60pixel;
        imgView.setTranslateX(80);
        imgView.setFitHeight(127);
        imgView.setFitWidth(60);
        return imgView;
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
        img_ViewSearch = new ImageView(img);
        //set size
        img_ViewSearch.setFitWidth(26);
        img_ViewSearch.setFitHeight(26);
        return img_ViewSearch;
    }
    public ImageView img_btnShowAll(){
        input = getClass().getResourceAsStream("/icon/Showall.png");
        img= new Image(input);
        img_ViewShowAll = new ImageView(img);
        //setsize
        img_ViewShowAll.setFitHeight(26);
        img_ViewShowAll.setFitWidth(26);
        return  img_ViewShowAll;
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


    /*========================================================setCombobox=================================================================*/

    //method laodcombobox
    public void setcombobox_DepID(){
        combobox_DepID.setPromptText("choose role");
        combobox_DepID.setItems(empService.loadCombobox_DepID());
    }

    //method laod combobox_Gender
    public void setCombobox_Gender(){
        combobox_Gender.setPromptText("choose Gender");
        ObservableList<String> loadcombobox = FXCollections.observableArrayList("Male", "Female");
        combobox_Gender.setItems(loadcombobox);
    }


    //method load combobox_Status
    public void setCombobox_Status(){
        ObservableList<String> loadCombobox = FXCollections.observableArrayList("Active",
                "On Leave","Resigned","Terminated","Retired","Suspended", "Trainning");
        combobox_Status.setPromptText("choose Status");
        combobox_Status.setItems(loadCombobox);
    }


    //------------------------------------method datepicker------------------------------------------------------
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //method datpicker
    public void setdate_Picker(){
        javafx.util.StringConverter<LocalDate> converter = new  javafx.util.StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        //add datepicker
        datePicker_Birth.setConverter(converter);


    }

    //---------------------------method load data ontbale------------------------------
    ObservableList<Employee> listEmployee;
    public void showEmployee(){
        listEmployee = empService.getAllEmployee_condition();
        column_EmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        column_EmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        column_EmpPhone.setCellValueFactory(new PropertyValueFactory<>("empPhone"));
        column_Birth.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        column_Gender.setCellValueFactory(new PropertyValueFactory<>("empGender"));
        column_Adrress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        column_depID.setCellValueFactory(new PropertyValueFactory<>("depID"));
        column_EmpPos.setCellValueFactory(new PropertyValueFactory<>("empPosition"));
        column_EmpSalary.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
        column_EmpPass.setCellValueFactory(new PropertyValueFactory<>("empPass"));
        colummn_EmpBarCode.setCellValueFactory(new PropertyValueFactory<>("empBarcode"));
        column_EmpStatus.setCellValueFactory(new PropertyValueFactory<>("empStatus"));

        table_EMployee.setItems(listEmployee);
    }


    //---------------method handleShowAll for btn_showAll
    public void handleShowAll(){
        listEmployee = empService.getAllEmployee();

        column_EmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        column_EmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        column_EmpPhone.setCellValueFactory(new PropertyValueFactory<>("empPhone"));
        column_Birth.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        column_Gender.setCellValueFactory(new PropertyValueFactory<>("empGender"));
        column_Adrress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        column_depID.setCellValueFactory(new PropertyValueFactory<>("depID"));
        column_EmpPos.setCellValueFactory(new PropertyValueFactory<>("empPosition"));
        column_EmpSalary.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
        column_EmpPass.setCellValueFactory(new PropertyValueFactory<>("empPass"));
        colummn_EmpBarCode.setCellValueFactory(new PropertyValueFactory<>("empBarcode"));
        column_EmpStatus.setCellValueFactory(new PropertyValueFactory<>("empStatus"));

        table_EMployee.setItems(listEmployee);
    }


    //------------method Refesh and clean------------
    public void Clean_Emp(){
        field_EmpID.setText("");
        field_EmpName.setText("");
        field_EmpPhone.setText("");
        datePicker_Birth.setValue(null);
        combobox_Gender.setValue(null);
        field_EmpAdrress.setText("");
        combobox_DepID.setValue(null);
        field_EmpPos.setText("");
        field_EmpSal.setText("");
        field_EmpPos.setText("");
        field_EmpBarCode.setText("");
        imgView.setImage(null);
        combobox_Status.setValue(null);
        pass_EmpPass.setText("");
    }

    public void Refesh_Emp(){
        Clean_Emp();
        showEmployee();
    }

    //-------------method selectItem---------------
    public void selectiTem_Emp(){
        table_EMployee.setOnMouseClicked(event ->{
            //texxt xem usser has choose row on table
            if(event.getClickCount()==1){
                // lấy dòng đc chọn từ bảng
                Employee employee = table_EMployee.getSelectionModel().getSelectedItem();

                field_EmpID.setText(employee.getEmpID());
                field_EmpName.setText(employee.getEmpName());
                field_EmpPhone.setText(employee.getEmpPhone());
                //set datepicker
                String dateBirth = String.valueOf(employee.getBirthDay());
                LocalDate local = LocalDate.parse(dateBirth);
                datePicker_Birth.setValue(local);

                //set combobox_Gender
                String gender = String.valueOf(employee.getEmpGender());
                if(gender.equals("Male")){
                    combobox_Gender.setValue("Male");
                } else if (gender.equals("Female")) {
                    combobox_Gender.setValue("Female");
                }

                field_EmpAdrress.setText(employee.getEmpAddress());
                field_EmpPos.setText(employee.getEmpPosition());
                field_EmpSal.setText(employee.getEmpSalary());
                pass_EmpPass.setText(employee.getEmpPass());
                field_EmpBarCode.setText(employee.getEmpBarcode());

                //combobox_DepID
                String depID = String.valueOf(employee.getDepID());
                for(Object itemDepID: combobox_DepID.getItems()){
                    if(itemDepID.equals(depID)){
                        combobox_DepID.setValue(itemDepID);
                        break;
                    }
                }
                //select imgView
                String uri  = "file: " + employee.getEmpImg();
                Image img = new Image(uri,117,138,false,true);
                imgView.setImage(img);
                System.out.println(uri);

                //combobox_Status
                String status = String.valueOf(employee.getEmpStatus());
                if(status.equals("Active")){
                    combobox_Status.setValue("Active");
                }else if(status.equals("On Leave")){
                    combobox_Status.setValue("On Leave");
                }else if(status.equals("Resigned")){
                    combobox_Status.setValue("Resigned");
                }else if(status.equals("Terminated")){
                    combobox_Status.setValue("Terminated");
                }else if(status.equals("Retired")){
                    combobox_Status.setValue("Retired");
                }else if(status.equals("Suspended")){
                    combobox_Status.setValue("Suspended");
                }else if(status.equals("Trainning")){
                    combobox_Status.setValue("Trainning");
                }
            }
        } );
    }



    // method insert
    public void handleInsert(){
        try {
            if (
                    field_EmpID.getText().isEmpty()
                            || field_EmpName.getText().isEmpty()
                            || field_EmpPhone.getText().isEmpty()
                            || datePicker_Birth.getValue() == null
                            || combobox_Gender.getValue() == null
                            || field_EmpAdrress.getText().isEmpty()
                            || combobox_DepID.getValue() == null
                            || field_EmpPos.getText().isEmpty()
                            || field_EmpSal.getText().isEmpty()
                            || pass_EmpPass.getText().isEmpty()
                            || field_EmpBarCode.getText().isEmpty()
                            || combobox_Status.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                // pt headerText (null) loại bỏ đi tiêu đề mặc định của hộp thoại
                alert.setHeaderText(null);
                alert.setContentText("Please don't leave it blank!");
                // showAndwait dùng để hiển thị v chờ người dùng phàn hồi lỗi
                alert.showAndWait();
            } else {
                employee.setEmpID(field_EmpID.getText());
                employee.setEmpName(field_EmpName.getText());
                //phone
                String phonePattern = "\\d{10}"; // chuỗi số điện thoại phải có đúng 10 chữ số
                String phone = field_EmpPhone.getText();
                if (!phone.matches(phonePattern)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid phone number. Please enter all 10 numbers!");
                    alert.showAndWait();
                    return;
                } else {
                    employee.setEmpPhone(field_EmpPhone.getText());
                }

                //date picker birthdate
                LocalDate local = datePicker_Birth.getValue();
                java.sql.Date dateBirth = java.sql.Date.valueOf(local);
                employee.setBirthDay(dateBirth);

                //combobox Gender
                String select = (String) combobox_Gender.getValue();
                employee.setEmpGender(select);


                //address
                if (!isValidAddress(field_EmpAdrress.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("wrong address, need to enter enough: street name, ward/commune, district/district, province/city!");
                    alert.showAndWait();
                    return;
                } else {
                    employee.setEmpAddress(field_EmpAdrress.getText());
                }

                //combobox depId
                String select2 = (String) combobox_DepID.getValue();
                employee.setDepID(select2);

                employee.setEmpPosition(field_EmpPos.getText());
                employee.setEmpSalary(field_EmpSal.getText());

                //pass
                if (!isValidPassword(pass_EmpPass.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Password must be at least 6-10 characters, have lowercase letters, and at least 1 special character");
                    alert.showAndWait();
                    return;
                } else {
                    employee.setEmpPass(pass_EmpPass.getText());
                }

                employee.setEmpBarcode(field_EmpBarCode.getText());

                //sưt image
                String uri = getData.path;
                employee.setEmpImg(uri);

                //combobox Status
                String select1 = (String) combobox_Status.getValue();
                employee.setEmpStatus(select1);

                empService.Insert_Employee(employee);

                // tạo thông báo là đã insert thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("You have successfully added!");
                alert.showAndWait();

                // khi inssert xong thì tiến hành clear thông tin vừa nhập và show lên tablevie từ hai method đã viết trc đó
                Clean_Emp();
                showEmployee();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//end
    }




    //method update
    public void handleUpdate(){
        try{
            if (table_EMployee.getSelectionModel().getSelectedCells()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Please select the line to update");
                alert.showAndWait();
            }else {
                employee.setEmpID(field_EmpID.getText());
                employee.setEmpName(field_EmpName.getText());

                //phone
                String phonePattern = "\\d{10}"; // chuỗi số điện thoại phải có đúng 10 chữ số
                String phone = field_EmpPhone.getText();
                if (!phone.matches(phonePattern)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid phone number. Please enter all 10 numbers!");
                    alert.showAndWait();
                    return;
                } else {
                    employee.setEmpPhone(field_EmpPhone.getText());
                }

                //date picker birthdate
                LocalDate local = datePicker_Birth.getValue();
                java.sql.Date dateBirth = java.sql.Date.valueOf(local);
                employee.setBirthDay(dateBirth);

                //combobox Gender
                String select = (String) combobox_Gender.getValue();
                employee.setEmpGender(select);


                //address
                if (!isValidAddress(field_EmpAdrress.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("wrong address, need to enter enough: street name, ward/commune, district/district, province/city!");
                    alert.showAndWait();
                    return;
                } else {
                    employee.setEmpAddress(field_EmpAdrress.getText());
                }

                //combobox depId
                String select2 = (String) combobox_DepID.getValue();
                employee.setDepID(select2);

                employee.setEmpPosition(field_EmpPos.getText());
                employee.setEmpSalary(field_EmpSal.getText());

                //pass
                if (!isValidPassword(pass_EmpPass.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Password must be at least 6-10 characters, have lowercase letters, and at least 1 special character");
                    alert.showAndWait();
                    return;
                } else {
                    employee.setEmpPass(pass_EmpPass.getText());
                }

                employee.setEmpBarcode(field_EmpBarCode.getText());

                //sưt image
               /* String uri = getData.path;
                employee.setEmpImg(uri);
*/
                //combobox Status
                String select1 = (String) combobox_Status.getValue();
                employee.setEmpStatus(select1);

                empService.Update_Employee(employee);

                // tạo thông báo là đã insert thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("You have successfully added!");
                alert.showAndWait();

                // khi inssert xong thì tiến hành clear thông tin vừa nhập và show lên tablevie từ hai method đã viết trc đó
                Clean_Emp();
                showEmployee();
            }
        }catch (Exception e){
            e.printStackTrace();
        }//end
    }





    // method handleSearch
    public void handleSearch(){
        try{
            String maSearch = field_Search.getText();
            listEmployee = empService.Search_Employee(maSearch);
            if (listEmployee != null && !listEmployee.isEmpty()) {
                ObservableList<Employee> data = FXCollections.observableArrayList(listEmployee);
                table_EMployee.setItems(data);
            } else if(listEmployee.isEmpty()) {
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






    //method alert: wrong Address
    public static boolean isValidAddress(String address) {
        String[] addressParts = address.split(",");
        if (addressParts.length <4) {
            return false;
        }
        String street = addressParts[0].trim();
        String ward = addressParts[1].trim();
        String district = addressParts[2].trim();
        String city = addressParts[3].trim();
        if (street.isEmpty() || ward.isEmpty() || district.isEmpty() ||  city.isEmpty()) {
            return false;
        }
        return true;
    }
    // kiểm tra field pass có đủ ký tự in thường, in hoa và ký tự đặt biệt (@,#) hay không
    public static boolean isValidPassword(String password) {
        // kiểm tra độ dài của password có từ 8 đến 20 ký tự
        if (password.length() < 6 || password.length() > 10) {
            return false;
        }
        // kiểm tra password có chứa ký tự in thường
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        // kiểm tra password có chứa ký tự in hoa
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // kiểm tra password có chứa ký tự đặt biệt (@,#)
        if (!password.matches(".*[@#].*")) {
            return false;
        }
        return true;
    }

    /*end program*/
}
