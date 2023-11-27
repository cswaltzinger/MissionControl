
  /* *****************************************
   *
   * Name: Chris Waltzinger
   * Date: 6/16/22
   * Time: 5:52 PM
   *
   * Project: MissionControl * Package: app * Class: ButtonBuilder
   *
   * Description:  this is the ButtonBuilder for app*
   * ****************************************
   */
  package app;

  import javafx.beans.InvalidationListener;
  import javafx.beans.value.ChangeListener;
  import javafx.event.ActionEvent;
  import javafx.event.EventHandler;
  import javafx.scene.Node;
  import javafx.scene.input.MouseEvent;
  import javafx.scene.text.Font;

  public class ItemBuilder {
    private static ItemBuilder itm = new ItemBuilder();
    private Node n;
    private static Font DEFAULTFONT = new Font("Andale Mono" ,20);
    private ItemBuilder(){
      this.n = null;
    }
    private void setNode(Node n){
      this.n = n;
    }
    public static ItemBuilder item(Node n){
      itm.setNode(n);
      itm.setFont(DEFAULTFONT);
      return itm;
    }
    public ItemBuilder onClick(EventHandler<MouseEvent> r){
      this.n.setOnMouseClicked(r);
      return this;
    }
    public ItemBuilder onClick(Runnable r){
      this.n.setOnMouseClicked((j)->{
        r.run();
      });
      return this;
    }
    public ItemBuilder onHover(ChangeListener<Boolean> r){
      this.n.hoverProperty().addListener(r);
      return this;
    }
    public ItemBuilder onHover(Runnable r){
      this.n.hoverProperty().addListener((o,old,nw)->{
        r.run();
      });
      return this;
    }
    public ItemBuilder setFont(Font f){
      itm.setFont(f);
      return itm;
    }
    public Node build(){return this.n;}
  }