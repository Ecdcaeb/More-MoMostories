package app.Hileb.forgedmomoInstaller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/25 18:21
 **/
public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //setup
        Group root=new Group();

        Scene scene=new Scene(root,480,300);
        scene.setFill(Color.WHITE);

        primaryStage.setTitle("MoreMoMoStories Installer");
        primaryStage.setScene(scene);

        //Image
        Image mainImage=new Image("assets/moremomostories/index.png");
        ImageView imageView=new ImageView(mainImage);
        imageView.setX(300);
        imageView.setY(60);
        imageView.setPreserveRatio(true);
        root.getChildren().add(imageView);
        //Text
        Text mainTextTitle=new Text(20,50,"Installer");
        Text offTextTitle=new Text(100,80,"by Hileb");
        mainTextTitle.setFont(Font.font(60));

        Text copyrightText=new Text(300,200,"MIT Licence@Hileb 2023");
        root.getChildren().add(mainTextTitle);
        root.getChildren().add(offTextTitle);
        root.getChildren().add(copyrightText);
        //Button
        Button forgedMoMoButton=new Button("Install MoreMoMoStories");
        forgedMoMoButton.addEventFilter(MouseEvent.MOUSE_CLICKED, Main::onForgedMoMoButton);
        forgedMoMoButton.setLayoutX(10);
        forgedMoMoButton.setLayoutY(200);

        Button momoButton=new Button("Install MoMoStories");
        momoButton.addEventFilter(MouseEvent.MOUSE_CLICKED, Main::onMomoButton);
        momoButton.setLayoutX(10);
        momoButton.setLayoutY(230);

        Button aboutUsButton=new Button("About us");
        aboutUsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, Main::onAboutUsButton);
        aboutUsButton.setLayoutX(10);
        aboutUsButton.setLayoutY(260);

        root.getChildren().add(forgedMoMoButton);
        root.getChildren().add(momoButton);
        root.getChildren().add(aboutUsButton);

        primaryStage.show();
    }
    public static void onForgedMoMoButton(MouseEvent event){
        Desktop desktop = Desktop.getDesktop();
        try {
            URI url = new URI("https://www.mcmod.cn/download/7481.html");
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(url);
            }
        } catch(IOException | URISyntaxException ignored){

        }
    }
    public static void onMomoButton(MouseEvent event){

    }
    public static void onAboutUsButton(MouseEvent event){

    }

}
