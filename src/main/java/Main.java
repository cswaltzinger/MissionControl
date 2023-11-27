
  /* *****************************************
   * CSCI205 - Software Engineering and Design
   * Spring 2021
   * Instructor: Prof. Chris Dancy
   *
   * Name: Chris Waltzinger
   * Section: Section 2
   * Date: 5/21/22
   * Time: 11:48 PM
   *
   * Project: MissionControl * Package: PACKAGE_NAME * Class: Main
   *
   * Description:  this is the Main for PACKAGE_NAME*
   * ****************************************
   */
import app.*;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;
import java.util.Collection;

public class Main extends Application {
    Container the ;

    @Override
    public void start(Stage primaryStage) throws Exception {
        the = new Container(primaryStage);
        //Image im = new Image(getClass().getClassLoader().getResourceAsStream("icon.png"));
        //primaryStage.getIcons().add(im);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle(the.model.getTitle());

      primaryStage.setScene(the.view.getScene());
      primaryStage.setResizable(false);
      the.controller.setSmall();
      //the.controller.doScript("source ./functions.sh");
      primaryStage.setOnCloseRequest(e->{
          //the.controller.saveNotes();
      });
      //primaryStage.initOwner(new Popup());
      //System.out.println(primaryStage.getOwner());
      primaryStage.show();
        //primaryStage.getOwner().setX(0);
        //primaryStage.getOwner().setY(0);

      the.controller.schoolButton();
      
    }

    public static void main(String[] args) {
        launch(args);
    }


  }