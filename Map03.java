//Michael Calle
import java.util.ArrayList;

public class Map03{
    private ArrayList<Point> global = new ArrayList<>();
    private Draw d = new Draw();
    
    public Map03(ArrayList<Point>g,int lim){
        d.point(0, 0, 0, "user", 1, 1, false);
        
        //Starting walkway
        d.plane(-20,-20,0,20,-20,0,"blue",12,8,0.2,true,22,7);
        d.plane(-20,-20,0,-20,-20,30,"blue",12,8,0.2,true,22,7);
        
        //Slanted walls
        d.plane(20,-20,0,40,-20,30,"blue",12,8,0.2,true,12,7);
        d.plane(20,100,0,40,100,30,"blue",12,8,0.2,true,5,7);
        
        //Buffer walls
        d.line(40, 100, 0, 40, 100, 30, "blue", 8, 8, 0.2, true);
        d.line(34, 100, 0, 34, 100, 15, "blue", 12, 12, 0.2, true);
        d.line(40, 60, 0, 40, 60, 30, "blue", 8, 8, 0.2, true);
        d.line(34, 60, 0, 34, 60, 15, "blue", 12, 12, 0.2, true);
        
        //Yellow Lock
        d.point(40, 80, 15, "yel_switch_locked", 15, 15, false);
        
        //Intersection
        d.line(-40, 170, 0, 40, 170, 0, "white", 8, 8, 0.08, true);
        
        //Trek to Yellow Key
        d.plane(60, 180, 5, 100, 180, 5, "yellow", 8, 8, 0.2, true, 4, 7);
        d.line(100, 220, 10, 0, 220, 40, "yellow", 8, 8, 0.05, true);
        d.line(0, 210, 40, 0, 170, 80, "red_path", 8, 8, 0.2, false);
        
        //Red Lock
        d.point(0, 200, 75, "red_switch_locked", 15, 15, true);
        
        //Yellow Key Platform
        d.plane(-5, 140, 80, 10, 140, 80, "yellow", 8, 8, 0.1, true, 4, 7);
        d.point(0, 140, 90, "yel_switch", 15, 15, false);
        
        //Fork right
        d.plane(20, 70, 0, 130, 60, 0, "blue", 12, 8, 0.1, true, 4, 7);
        d.plane(120, 60, 0, 200, 60, 100, "blue", 12, 8, 0.05, true, 4, 7);
        
        //Trek to Red Key
        d.plane(-100, 120, -25, -80, 120, -25, "red", 8, 8, 0.2, true, 4, 7);
        d.plane(-100, 40, -50, -80, 40, -50, "red", 8, 8, 0.2, true, 4, 7);
        d.plane(-100, -40, -75, -80, -40, -75, "red", 8, 8, 0.2, true, 4, 7);
        
        //Back trek red invs
        d.line(-40, 162, 0, -60, -40, -75, "red_path", 8, 8, 0.05, false);
        
        //Red Key
        d.point(-90, -30, -65, "red_switch", 15, 15, false);
        
        //Blue lock
        d.point(80, 50, 15, "blue_switch_locked", 15, 15, true);
        d.plane(60, 20, 0, 80, 20, 0, "blue", 8, 8, 0.1, true, 6, 7);
        
        //Trek to blue switch
        d.plane(140, 100, 80, 160, 100, 80, "blue", 8, 8, 0.2, true, 2, 7);
        d.plane(100, 100, 95, 120, 100, 85, "blue", 8, 8, 0.2, true, 2, 7);
        d.plane(60, 100, 100, 80, 100, 100, "blue", 8, 8, 0.2, true, 2, 7);
        
        //Blue key
        d.point(70, 100, 110, "blue_switch", 15, 15, false);
        
        //Exit
        d.point(80, 20, 30, "exit", 20, 15, true);
        
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