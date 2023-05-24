package view;
        import data.EmpService;
        import data.ManagerBooks_service;
        import java.time.format.DateTimeFormatter;
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
        import model.Employee;
        import model.ManagesBook;

        import java.io.InputStream;
        import java.time.LocalDate;
        import java.util.Date;

public class ManageBooks extends Application {
    private Image img;
    private ImageView imgView,imgView_Insert, imgView_Update,imgView_Clean,imgView_Refesh,img_ViewSearch,img_ViewShowAll;

    private BorderPane root;
    private GridPane gridpane_Left;
    private InputStream input;
    private VBox vboxLeft, vboxRight,vsubbox_tabledep;

    private DatePicker datePicker_DateAeded;

    private ComboBox comboBox_EmpID,comboBox_BookID,comboBox_Activity;

    private TableView<ManagesBook> table_Managerbook;
    private TableColumn<ManagesBook, String> column_EmpID, column_BookID, colum_Activity;
    private TableColumn<ManagesBook, Date>column_DateAedded;

    private JFXButton btn_Insert, btn_Update, btn_Clean, btn_Refesh, btn_Search,btn_ShowAll;
    private Label label_EmpID, label_BookID,label_Activity,label_DateAeded;
    private TextField field_Search;

    /*==================call class, controller, serrvice===============*/
    DepartService departService = new DepartService();
    ManagerBooks_service manaService = new ManagerBooks_service();
    ManagesBook managerBooks = new ManagesBook();
    public static void main(String[] args) {
        launch(args);
    }
    //
    Department department = new Department();
    ManagerBooks_service managerBooksService = new ManagerBooks_service();

    EmpService empService = new EmpService();
    @Override
    public void start(Stage primaryStage) {
        Depart_View();
        setcombobox_EmpID();
        setDatePicker_DateAeded();
        setComboBox_BookID();
        setComboBox_Activity();
        showAllManager();
        selectItem_ManagerBook();



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

        label_EmpID = new Label("EmpID");
        label_EmpID.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        label_EmpID.getStyleClass().add("fill_Label");

        label_BookID= new Label("BookID");
        label_BookID.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        label_BookID.getStyleClass().add("fill_Label");

        label_DateAeded = new Label("DateAdded");
        label_DateAeded.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        label_DateAeded.getStyleClass().add("fill_Label");

        label_Activity = new Label("Activity");
        label_Activity.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        label_Activity.getStyleClass().add("fill_Label");

        comboBox_EmpID = new ComboBox();
        comboBox_BookID = new ComboBox();
        comboBox_Activity = new ComboBox();
        datePicker_DateAeded = new DatePicker();


        //add component for vboxLeft
        HBox box_img = new HBox(4);
        box_img.getChildren().add(EmpImage_View());

        gridpane_Left = new GridPane();
        gridpane_Left.setHgap(4);//set k/c |.| column
        gridpane_Left.setVgap(4);//set k/c doc giua cac cot

        gridpane_Left.add(label_EmpID,0,0);
        gridpane_Left.add(comboBox_EmpID,1,0);
        comboBox_EmpID.setPrefWidth(170);
        comboBox_EmpID.setScaleY(0.70);

        gridpane_Left.add(label_BookID,0,1);
        gridpane_Left.add(comboBox_BookID,1,1);
        comboBox_BookID.setPrefWidth(170);
        comboBox_BookID.setScaleY(0.70);

        gridpane_Left.add(label_DateAeded,0,2);
        gridpane_Left.add(datePicker_DateAeded,1,2);
        datePicker_DateAeded.setPrefWidth(170);
        datePicker_DateAeded.setScaleY(0.70);

        gridpane_Left.add(label_Activity,0,3);
        gridpane_Left.add(comboBox_Activity,1,3);
        comboBox_Activity.setPrefWidth(170);
        comboBox_Activity.setScaleY(0.70);

        //add gridpane and Hbox_img on vboxLeft
        vboxLeft.getChildren().addAll(box_img,gridpane_Left);



        /*---------------------vboxRight---------------*/
        vboxRight = new VBox();
        vboxRight.setPadding(new Insets(5));
        vboxRight.setAlignment(Pos.TOP_CENTER);
        //add css for vboxRight
        vboxRight.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        vboxRight.getStyleClass().add("thietlap_vboxRight");


        ButtonBar btnBar = new ButtonBar();
        vboxRight.setMargin(btnBar, new Insets(5,5,10,0));
        field_Search = new TextField();field_Search.setPrefWidth(150);

        btn_Search = new JFXButton("Search");
        btn_Search.setGraphic(img_btnSearch());
        btn_Search.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_Search.getStyleClass().add("title_Button");
        btn_Search.setTranslateX(-10);

        btn_ShowAll = new JFXButton("ShowAll"   );
        btn_ShowAll.setGraphic(img_btnShowAll());
        btn_ShowAll.setTranslateX(-20);
        btn_ShowAll.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_ShowAll.getStyleClass().add("title_Button");

        btn_Refesh = new JFXButton("Refesh");
        btn_Refesh.setGraphic(img_btnRefesh());
        btn_Refesh.setTranslateX(-30);
        btn_Refesh.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_Refesh.getStyleClass().add("title_Button");

        //..add component on btnBar(Buttonbar)
        btnBar.getButtons().addAll(field_Search,btn_Search,btn_ShowAll,btn_Refesh);

        //table_managerbook
        table_Managerbook = new TableView<ManagesBook>();
        column_EmpID= new TableColumn<>("EmpID");
        column_BookID = new TableColumn<>("BookID");
        column_DateAedded = new TableColumn<>("DateAedded ");
        colum_Activity = new TableColumn<>("Activity");

        //set size column
        table_Managerbook.setPrefSize(1050,350);
        column_EmpID.setPrefWidth(120);
        column_BookID.setPrefWidth(150);
        column_DateAedded.setPrefWidth(140);
        colum_Activity.setPrefWidth(140);
        //add column on table_Depart
        table_Managerbook.getColumns().addAll(column_EmpID,column_BookID,column_DateAedded,colum_Activity);
        //add table_Depart on scrollpane
        ScrollPane scrollPane_tableDepart = new ScrollPane(table_Managerbook);

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
        //add css
        btn_Insert.setGraphic(img_btnInsert());
        btn_Insert.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_Insert.getStyleClass().add("title_Button");

        //add css
        btn_Update.setGraphic(img_btnUpdate());
        btn_Update.getStylesheets().add(getClass().getResource("/css/Depart.css").toExternalForm());
        btn_Update.getStyleClass().add("title_Button");

        //add css
        btn_Clean.setGraphic(img_btnClean());
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
        stage.setTitle("managerbook");
        stage.show();
//end

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
                Clean_MangerBook();
            }
        });

        //Action event btn_Refesh
        btn_Refesh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Refesh_Emp();
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
    }
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

    // load comnbobox_EmpID
    public void setcombobox_EmpID(){
        comboBox_EmpID.setPromptText("choose EmpID");
        comboBox_EmpID.setItems(manaService.loadCombobox_EmpID());
    }
    public void setComboBox_BookID(){
        comboBox_BookID.setPromptText("choose BookID");
        comboBox_BookID.setItems(manaService.loadCombobox_BookID());
    }


    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //method datpicker
    public void setDatePicker_DateAeded() {
        javafx.util.StringConverter<LocalDate> converter = new javafx.util.StringConverter<LocalDate>() {
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
        datePicker_DateAeded.setConverter(converter);
    }


    //method load combobox
    public void setComboBox_Activity(){
        ObservableList<String> loadCombobox = FXCollections.observableArrayList("Available", "Borrowed", "Overdue", "Lost", "Damaged"," Withdrawn");
        comboBox_Activity.setPromptText("choose Activity");
        comboBox_Activity.setItems(loadCombobox);
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++
    //---------------------------method load data ontbale------------------------------
    ObservableList<ManagesBook> list;
    public void showAllManager(){
        list = manaService.getAllmanagerbook();
        column_EmpID.setCellValueFactory(new PropertyValueFactory<>("EmpID"));
        column_BookID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        column_DateAedded.setCellValueFactory(new PropertyValueFactory<>("DateAdded"));
        colum_Activity.setCellValueFactory(new PropertyValueFactory<>("Activity"));
        table_Managerbook.setItems(list);
    }
    //methoc selcetItem_Depart
    public void selectItem_ManagerBook(){
        table_Managerbook.setOnMouseClicked(event->{
            //texxt user has choose row on table or no?
            if(event.getClickCount()==1){
                ManagesBook managesBook = table_Managerbook.getSelectionModel().getSelectedItem();
                String date = String.valueOf(managesBook.getDateAdd());
                LocalDate local = LocalDate.parse(date);


                datePicker_DateAeded.setValue(local);
                //select combobox
                String slect = String.valueOf(managesBook.getActivity());
                if(slect.equals("Available")){
                    comboBox_Activity.setValue("Available");
                }else if(slect.equals("Borrowed")){
                    comboBox_Activity.setValue("Borrowed");
                }else if(slect.equals("Overdue")){
                    comboBox_Activity.setValue("Overdue");
                }else if(slect.equals("Lost")){
                    comboBox_Activity.setValue("Lost");
                }else if(slect.equals("Damanged")){
                    comboBox_Activity.setValue("Damaged");
                }else if(slect.equals("Withdrawn")){
                    comboBox_Activity.setValue("Withdrawn");
                }
            }
        });
    }

    //---------------method handleShowAll for btn_showAll
    public void handleShowAll() {
        list = manaService.getAllmanagerbook();
        column_EmpID.setCellValueFactory(new PropertyValueFactory<>("EmpID"));
        column_BookID.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        column_DateAedded.setCellValueFactory(new PropertyValueFactory<>("dateAdd"));
        colum_Activity.setCellValueFactory(new PropertyValueFactory<>("Activity"));
    }
    //------------method Refesh and clean------------


    // method insert
    public void handleInsert(){
        try {
            if (
                    comboBox_EmpID.getValue() == null
                            ||comboBox_BookID.getValue() == null
                            ||datePicker_DateAeded.getValue() == null
                            || comboBox_Activity.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                // pt headerText (null) loại bỏ đi tiêu đề mặc định của hộp thoại
                alert.setHeaderText(null);
                alert.setContentText("Please don't leave it blank!");
                // showAndwait dùng để hiển thị v chờ người dùng phàn hồi lỗi
                alert.showAndWait();
            } else {
                String select = (String) comboBox_EmpID.getValue();
                managerBooks.setEmpID(select);

                String select1 = (String) comboBox_BookID.getValue();
                managerBooks.setBookID(select1);

                LocalDate local = datePicker_DateAeded.getValue();
                java.sql.Date activity = java.sql.Date.valueOf(local);
                managerBooks.setDateAdd(activity);

                String select2 = (String) comboBox_Activity.getValue();
                managerBooks.setEmpID(select2);

                ManageBooks.Insert_ManagerBook(managerBooks);

                // tạo thông báo là đã insert thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("You have successfully added!");
                alert.showAndWait();

                // khi inssert xong thì tiến hành clear thông tin vừa nhập và show lên tablevie từ hai method đã viết trc đó
                Clean_MangerBook();
                showAllManager();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//end
    }

    private static void Insert_ManagerBook(ManagesBook managerBooks) {
    }


    //method update
    public void handleUpdate(){
        try{
            if (table_Managerbook.getSelectionModel().getSelectedCells()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                // pt headerText (null) loại bỏ đi tiêu đề mặc định của hộp thoại
                alert.setHeaderText(null);
                alert.setContentText("Please don't leave it blank!");
                // showAndwait dùng để hiển thị v chờ người dùng phàn hồi lỗi
                alert.showAndWait();
            } else {
                String select = (String) comboBox_EmpID.getValue();
                managerBooks.setEmpID(select);

                String select1 = (String) comboBox_BookID.getValue();
                managerBooks.setBookID(select1);

                LocalDate local = datePicker_DateAeded.getValue();
                java.sql.Date activity = java.sql.Date.valueOf(local);
                managerBooks.setDateAdd(activity);

                String select2 = (String) comboBox_Activity.getValue();
                managerBooks.setEmpID(select2);

                managerBooksService.Update(managerBooks);

                // tạo thông báo là đã insert thành công
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("You have successfully added!");
                alert.showAndWait();


                // khi inssert xong thì tiến hành clear thông tin vừa nhập và show lên tablevie từ hai method đã viết trc đó
                Clean_MangerBook();
                showAllManager();
            }
        }catch (Exception e){
            e.printStackTrace();
        }//end
    }





    // method handleSearch
    public void handleSearch(){
        try{
            String maSearch = field_Search.getText();
            list = manaService.Search(maSearch);
            if (list != null && !list.isEmpty()) {
                ObservableList<ManagesBook> data = FXCollections.observableArrayList(list);
                table_Managerbook.setItems(data);
            } else if(list.isEmpty()) {
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

    public void Clean_MangerBook(){
        comboBox_EmpID.setValue(null);
        comboBox_BookID.setValue(null);
        datePicker_DateAeded.getValue();
        comboBox_Activity.setValue(null);
        //imgView.setImage(null);

    }

    public void Refesh_Emp(){
        Clean_MangerBook();
        showAllManager();
    }




    //_____________________________________________________________________________________________

//end
}

