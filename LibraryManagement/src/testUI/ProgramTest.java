package testUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProgramTest extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/Log_in.fxml"));
            // chạy kích thước hiển th giao diện
            Scene scene = new Scene(root);

            // dẫn link file css or có thể dẫn trưc tiếp css cho các thẻ của giaodien fxml
           // scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

            // hiển thị giao diện lên cửa sổ chính, này tự this.setVissible(true) bên swing
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

