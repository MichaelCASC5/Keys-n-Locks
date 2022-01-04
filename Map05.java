//Michael Calle
import java.util.ArrayList;

public class Map05{
    private ArrayList<Point> global = new ArrayList<>();
    private Draw d = new Draw();
    
    public Map05(ArrayList<Point>g,int lim){
        d.point(0, 0, 0, "user", 1, 1, false);
        
        //Starting walkway
        d.plane(-20,-20,0,20,-20,0,"red",12,8,0.2,true,10,7);
        
        //Moving platforms
        d.plane(-80, 80, 0, -60, 80, 0, "moving_platform_orangeX", 8, 8, 0.1, true, 4, 7);
        d.plane(120, -80, -10, 140, -80, -10, "moving_platform_orangeY", 8, 8, 0.1, true, 4, 7);
        
        //Other end
        d.plane(140, 300, -20, 60, 200, 0, "red", 8, 8, 0.08, true,5,20);
        
        d.line(60, 200, 0, -100, 200, 0, "blue", 8, 8, 0.1, true);
        
        d.line(-300, 200, 200, -100, 200, 0, "blue", 8, 8, 0.02, true);
        
        d.plane(-300, 200, 100, -200, 200, 0, "green", 8, 8, 0.5, true, 20, 7);
        d.line(-300, 200, 100, -200, 200, 0, "blue", 8, 8, 0.08, true);
        
        //Locks
        d.point(-200, 340, 15, "yel_switch_locked", 15, 15, true);
        d.point(-250, 340, 70, "red_switch_locked", 15, 15, true);
        d.point(-300, 340, 120, "blue_switch_locked", 15, 15, true);
        
        //Trek to yellow key
        d.plane(140, 420, -20, 160, 420, -20, "pink", 8, 8, 0.1, true, 4, 7);
        d.plane(140, 480, -20, 160, 480, -20, "pink", 8, 8, 0.1, true, 4, 7);
        d.plane(140, 540, -20, 160, 540, -20, "pink", 8, 8, 0.1, true, 4, 7);
        
        d.line(150, 570, -20, 150, 420, 50, "yellow", 5, 5, 0.1, true);
        d.plane(140, 400, 50, 160, 400, 50, "yellow", 8, 8, 0.1, true, 4, 7);
        
        d.point(150, 410, 60, "yel_switch", 15, 15, false);
        
        //Trek to red key
        d.line(-200, 340, 0, -200, 400, 0, "green", 8, 8, 0.2, true);
        d.line(-100, 400, 0, -200, 400, 0, "green", 8, 8, 0.2, true);
        d.plane(-80, 390, 0, -60, 390, 0, "red", 8, 8, 0.1, true, 4, 7);
        d.plane(-80, 440, 5, -60, 440, 5, "red", 8, 8, 0.1, true, 4, 7);
        d.plane(-80, 490, 10, -60, 490, 10, "red", 8, 8, 0.1, true, 4, 7);
        d.point(-70, 500, 20, "red_switch", 15, 15, false);
        
        //Trek to blue key
        d.line(-250, 340, 50, -250, 500, 50, "green", 8, 8, 0.1, true);
        d.plane(-260, 515, 50, -240, 515, 50, "blue", 8, 8, 0.1, true, 4, 7);
        d.point(-250, 525, 60, "blue_switch", 15, 15, false);
        
        //Exit
        d.point(-300, 355, 130, "exit", 20, 15, true);
        
        d.removeDoubles();
        
        for(int i=0;i<d.size();i++){
            if(i%lim==0
                    || d.get(i).getID().equals("yel_switch")
                    || d.get(i).getID().equals("yel_switch_locked")
                    || d.get(i).getID().equals("red_switch")
                    || d.get(i).getID().equals("red_switch_locked")
                    || d.get(i).getID().equals("blue_switch")
                    || d.get(i).getID().equals("blue_switch_locked")
                    || d.get(i).getID().equals("exit")){
                g.add(d.get(i));
            }
        }
    }
    public void printMap(){
        d.printMap();
    }
    public void printTotal(){
        d.printTotal();
    }
}