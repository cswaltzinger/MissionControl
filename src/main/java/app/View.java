
  /* *****************************************
   * CSCI205 - Software Engineering and Design
   * Spring 2021
   * Instructor: Prof. Chris Dancy
   *
   * Name: Chris Waltzinger
   * Section: Section 2
   * Date: 5/21/22
   * Time: 11:49 PM
   *
   * Project: MissionControl * Package: app * Class: View
   *
   * Description:  this is the View for app*
   * ****************************************
   */
  package app;

  import javafx.beans.value.ChangeListener;
  import javafx.beans.value.ObservableValue;
  import javafx.collections.ObservableList;
  import javafx.geometry.Insets;
  import javafx.geometry.Pos;
  import javafx.scene.Node;
  import javafx.scene.Scene;
  import javafx.scene.control.*;
  import javafx.scene.layout.*;
  import javafx.scene.paint.Color;
  import javafx.scene.text.Font;
  import javafx.scene.text.Text;
  //import javafx.scene.web.WebView;

  import java.io.File;
  import java.time.LocalDate;
  import java.util.Scanner;

  import static app.ItemBuilder.item;

  public class View {
    Container the;
    Scene scene;
    Pane mainPane;
    HBox school = new HBox() ;
    VBox work = new VBox(),notes = new VBox();
    Scene[] scenes = new Scene[]{
            new Scene(school),//0
            new Scene(work),//1
            new Scene(notes)//2
    };
    Pane displayPane = new Pane();
    ObservableList<Node> adder;


    public View(Container c){
      this.displayPane.setPadding(new Insets(1));

      this.the = c;
      this.scene = new Scene(this.mainPane = new VBox(),300,500);
      this.mainPane.setPadding(new Insets(5));
      this.mainPane.hoverProperty().addListener((obs,n,o)->{
        the.controller.resize();
      });


      makeInitialScene();


      makeSchool();
      //makeWork();
      makeNotes();





      //the.controller.schoolButton();

    }
    public Scene getScene(){return this.scene;}
    protected void add(Node... elements){
      this.adder.addAll(elements);

    }
    public static Button buildClickButton(String s, Runnable r){
      Button b = new Button(s);
      b.setFont(new Font("Andale Mono" ,20));

      b.setPadding(new Insets(10));

      b.setOnAction(e->r.run());

      return b;
    }

    public  Node buildHoverButton(String s, Runnable r){

      Text b = new Text(s);
      b.setFont(new Font("Andale Mono" ,20));
      b.hoverProperty().addListener((obs,isOff,isOn)->{
        if (isOn){
          r.run();

        }
      });
      return b;

    }

    public static void pl(Object ...o){
      for (Object i:o) {
        System.out.println(i);
      }
    }
    public static void p(Object ...o){
      for (Object i:o) {

        System.out.print(i);
      }
      System.out.print("\n");
    }

    public void makeInitialScene(){
      adder = mainPane.getChildren();
      mainPane.setStyle("-fx-background-color: #808080;");

      ButtonBar bb = new ButtonBar();

      bb.setStyle("-fx-background-color: white;");
      CheckBox b2  = new CheckBox();
      b2.maxHeight(1.0);
      b2.maxWidth(1.0);
      b2.setFont(new Font("Andale Mono" ,10));
      b2.selectedProperty().addListener((ov,old,nw)->{
        if (nw){
          the.controller.canResize = false;
        }else {
          the.controller.canResize = true;
        }
      });
     bb.getButtons().addAll(
             buildHoverButton("school",()->{the.controller.schoolButton();}),
             buildHoverButton("work",()->{the.controller.workButton(); }),
             buildHoverButton("notes",()->{the.controller.notes(); }),
             
             b2
     );




     TextField ta = new TextField();
     //TextArea ta = new TextArea();
     ta.setStyle("-fx-background-color: blue;");
     //ta.setPrefRowCount(1);
     ta.setPrefColumnCount(50);
     ta.setOnKeyPressed((e)->{
       the.controller.commandScript(e);
     });
     ta.setBackground(new Background(new BackgroundFill(Color.rgb(200,0,0),null,null)));
     ta.setStyle("-fx-background-color: black;-fx-text-fill: lightgreen");
     add(

             bb,
             ta,
             displayPane
     );
    }


    private void makeSchool(){
      //this.school.setBackground(new Background( new BackgroundFill(Color.rgb(255,0,0),null,null)));

      this.adder = this.school.getChildren();
      VBox vb = new VBox();
      this.adder = vb.getChildren();
      this.school.setSpacing(3);
      vb.setSpacing(5);
      vb.setAlignment(Pos.CENTER);
      add(
              buildClickButton("Home",()->{the.controller.workButtons(0);}),
              buildClickButton("Moodle",()->the.controller.workButtons(1)),
              buildClickButton("Drive",()->the.controller.workButtons(2)),
              buildClickButton("Desmos",()->the.controller.workButtons(3)),
              buildClickButton("Bucknell",()->the.controller.workButtons(4))
      );
      this.adder = this.school.getChildren();

      Text time = new Text();
      Text desc = new Text();
      String s = "-fx-font-size: 25;";
      time.setStyle(s+"-fx-text-fill: blue;");
      desc.setStyle(s+"-fx-text-fill: green;");

      switch (LocalDate.now().getDayOfWeek()){
        case MONDAY:
          the.model.fillSchedule(time,desc,0);
          break;
        case TUESDAY:

          the.model.fillSchedule(time,desc,1);
          break;
        case WEDNESDAY:

          the.model.fillSchedule(time,desc,2);
          break;
        case THURSDAY:

          the.model.fillSchedule(time,desc,3);
          break;
        case FRIDAY:

          the.model.fillSchedule(time,desc,4);
          break;
        default:
          desc.setText("NO CLASSES");
          break;
      }
      HBox hb = new HBox();
      hb.getChildren().addAll(time,desc);
      hb.setSpacing(20);


      add(vb,hb);

    }
    private void makeWork(){
      this.work.setBackground(new Background( new BackgroundFill(Color.rgb(0,255,0),null,null)));
      this.adder = this.work.getChildren();
      add(
              buildClickButton("Classroom",()->Controller.doScript("open https://classroom.google.com/u/1/c/NDU3MTU2MjYwNzQ2"))
      );


    }


    private void makeNotes() {

      this.notes.setBackground(new Background( new BackgroundFill(Color.rgb(0,0,255),null,null)));
      this.adder = this.notes.getChildren();
      TextArea tf = new TextArea();
      tf.setPrefSize(the.model.widths[1]-10,the.model.heights[1]-100);
      tf.setWrapText(true);
      String str = "";
      try{
        str = Controller.gr("notes.txt");
        File f = new File(str);

        Scanner fl = new Scanner(f);
        str = "";
        while (fl.hasNextLine()){
          str+= fl.nextLine()+"\n";
        }
        //System.out.println("worked");
        tf.setText(str);
        fl.close();
      }catch (Exception e){
        e.printStackTrace();
        //System.out.println("failed");
      }
      add(tf);
    }

  }
