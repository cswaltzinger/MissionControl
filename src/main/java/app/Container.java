
  /* *****************************************
   * CSCI205 - Software Engineering and Design
   * Spring 2021
   * Instructor: Prof. Chris Dancy
   *
   * Name: Chris Waltzinger
   * Section: Section 2
   * Date: 5/22/22
   * Time: 7:29 AM
   *
   * Project: MissionControl * Package: app * Class: Container
   *
   * Description:  this is the Container for app*
   * ****************************************
   */
  package app;


  import javafx.stage.Stage;

  public class Container{
    public Controller controller;
    public Model model;
    public View view;
    public Stage stage;
    public Container(Stage s){
      this.stage = s;
      this.model = new Model(this);
      this.view = new View(this);
      this.controller = new Controller(this);
    }
  }