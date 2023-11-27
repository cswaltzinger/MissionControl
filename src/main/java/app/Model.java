
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
   * Project: MissionControl * Package: app * Class: Model
   *
   * Description:  this is the Model for app*
   * ****************************************
   */
  package app;

  import javafx.scene.text.Text;

  public class Model {
    Container the;
    protected String title = "MissionControl";
    protected int[] widths = new int[]{100,350};
    protected int[] heights = new int[]{40,500};


    public Model(Container c){
      the = c;
    }
    public String getTitle(){
      return this.title;
    }

    protected String[][][] schedule= new String[][][]{
            //monday
            {
                    {"11 - 11:50","3 - 4:20"},
                    {"CSCI 341","HIST 282"}
            },
            //tuesday
            {
                    {"1 - 2:20","3 - 4:20"},
                    {"CBST 204","ENCW 204"}
            },
            //weds
            {
                    {"11 - 11:50","3 - 4:20"},
                    {"CSCI 341","HIST 282"}
            },
            //thurs
            {
                    {"10 - 10:50","1 - 2:20","3 - 4:20"},
                    {"CSCI 341R","CBST 204","ENCW 204"}
            },
            //friday
            {
                    {"11 - 11:50"},
                    {"CSCI 341"}
            }
    };
    public void fillSchedule(Text time,Text desc,int dow){
        String[][] day = this.schedule[dow];
        String timeText = "", descText ="";
        for (int i =0 ; i < day[0].length;i++){
            timeText+= day[0][i]+"\n\n";
            descText+= day[1][i]+"\n\n";
        }
        time.setText(timeText);
        desc.setText(descText);

    }



  }