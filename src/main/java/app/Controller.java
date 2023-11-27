
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
   * Project: MissionControl * Package: app * Class: Controller
   *
   * Description:  this is the Controller for app*
   * ****************************************
   */
  package app;

  import javafx.beans.property.SimpleIntegerProperty;
  import javafx.scene.control.TextArea;
  import javafx.scene.control.TextField;
  import javafx.scene.input.KeyCode;
  import javafx.scene.input.KeyEvent;

  import java.io.BufferedReader;
  import java.io.File;
  import java.io.FileWriter;
  import java.io.InputStreamReader;

  import static app.View.p;

  public class Controller {
    Container the;
    public static String gr(String s) throws  Exception{
      if (s.contains("notes")){
        return "./" + s;
      }

      String r = Controller.class.getClassLoader().getResource(s).toURI().toURL().toString();
      if (r.contains("file:")){
        r = r.substring(5);
        return r;
      }
      return r;
    }
    private static final String PATH = "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin";
    public static void doScript(String cm){
      try {
        if (cm.equals("")){
          return ;
        }
        //need to export path to use basic functions
        Process p = Runtime.getRuntime().exec(cm);
        /*
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

        //System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
          System.out.println(s);
        }
        String line ="";
        s= "";
        while ((s = stdError.readLine()) != null) {
          line +=s +"\n";
        }
        if (!line.equals("")){
          System.out.println("ERROR:\n");
          System.out.println(line);
        }*/
      }catch (Exception e){
        e.printStackTrace();
      }
    }

    public Controller(Container c){

      this.the = c;

    }

    public void schoolButton(){
      if (!the.view.displayPane.getChildren().isEmpty()){
        the.view.displayPane.getChildren().remove(0);
      }
      the.view.displayPane.getChildren().add(the.view.school);
    }

    public void workButton(){
      if (!the.view.displayPane.getChildren().isEmpty()){
        the.view.displayPane.getChildren().remove(0);
      }
      the.view.displayPane.getChildren().add(the.view.work);

    }



    public void notes(){
      if (!the.view.displayPane.getChildren().isEmpty()){
      the.view.displayPane.getChildren().remove(0);
    }
      the.view.displayPane.getChildren().add(the.view.notes);

    }

    boolean canResize = true;
    public void resize(){
      if (this.canResize){
        resizeHelpper();
      }
    }
    boolean b = false;
    public void resizeHelpper(){
      the.stage.setResizable(true);
      if (b){
        setSmall();
      }else{
        setLarge();
      }
      the.stage.setResizable(false);
      b = !b;

    }
    public void setSmall(){
      the.stage.setHeight(the.model.heights[0]);
      the.stage.setWidth(the.model.widths[0]);
      the.stage.setTitle("");


    }
    public void setLarge(){
      the.stage.setHeight(the.model.heights[1]);
      the.stage.setWidth(the.model.widths[1]);
      the.stage.setTitle(the.model.getTitle());
    }

    public void saveNotes(){
      try{
        TextArea ta = (TextArea) the.view.notes.getChildren().get(0);

        String u = false ? "./notes.txt":gr("notes.txt");
        File fold=  new File(u);
        FileWriter fw = new FileWriter(fold,false);
        String toWrite = ta.getText();
        fw.write(toWrite);
        fw.close();
        //System.out.println("wrote:\n"+toWrite+"\nto: "+u);


      }catch (Exception e){
       e.printStackTrace();
      }

    }

    protected void workButtons(int i){
      String[] commands = new String[]{
              "open -a /Applications/Safari.app",
              "open https://moodle.bucknell.edu/my/index.php",
              "open https://drive.google.com",
              "open https://www.desmos.com/calculator",
              "open https://my.bucknell.edu/apps/m/dashboard/"
      };
      if (i < commands.length){
        doScript(commands[i]);
      }
    }
    private String command ="";
    protected void commandScript(KeyEvent c){
      if (c.getCode() == KeyCode.ENTER){
        try{
          TextField t = ((TextField) c.getSource());
          doScript(t.getText());
          t.setText("");
          command = "";
        }catch (Exception e){
          e.printStackTrace();
        }
      }else{
       command += c.getText();

      }



    }
  }