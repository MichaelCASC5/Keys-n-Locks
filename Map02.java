//Michael Calle
import java.util.ArrayList;

public class Map02{
    private ArrayList<Point> global = new ArrayList<>();
    private Draw d = new Draw();
    
    public Map02(ArrayList<Point>g,int lim){
        d.point(0, 0, 0, "user", 1, 1, false);
        
        //Starting walkway
        d.plane(-20,-20,0,20,-20,0,"white",12,8,0.2,true,22,7);
        
        //Left Fork
        d.plane(-120, 100, 0, -20, 60, 0, "white", 12, 8, 0.08, true, 4, 7);
        
        //Right Fork
        d.plane(120, 100, 0, 20, 60, 0, "white", 12, 8, 0.08, true, 4, 7);
        
        d.line(-140, 100, 0, -200, 60, 0, "orange", 8, 8, 0.1, true);
        
        //Yellow Path
        d.line(-200, 60, 0, -200, 120, -30, "yel_path", 8, 8, 0.1, false);
        
        //Orange house
        d.plane(-220, 120, -30, -180, 120, -30, "orange", 8, 8, 0.1, true, 6, 7);//floor
        d.plane(-181, 120, -30, -200, 120, 30, "black", 2, 8, 0.1, true, 6, 7);
        d.plane(-220, 120, -30, -200, 120, 30, "orange", 8, 8, 0.1, true, 6, 7);
        
        //Trek to yellow key
        d.plane(-220, 180, -25, -200, 180, -25, "pink", 8, 8, 0.1, true, 4, 7);
        d.plane(-200, 240, -25, -180, 240, -15, "yellow", 8, 8, 0.1, true, 4, 7);
        d.line(-180,300,-15,-180,360,-15,"yellow", 8, 8, 0.08, true);
        
        //Yellow Key Platform
        d.plane(-160, 330, -10, -140, 330, -10, "yellow", 8, 8, 0.1, true, 4, 7);
        
        //Yellow Switch
        d.point(-150, 350, 0, "yel_switch", 15, 15, false);
        
        //Yellow Lock
        d.point(40, 80, 15, "yel_switch_locked", 15, 15, true);
        
        //Red Lock
        d.point(0, 110, 15, "red_switch_locked", 15, 15, true);
        
        //Red Lobby
        d.plane(120, 60, 0, 200, 60, 0, "blue", 12, 8, 0.1, true, 13, 7);
        
        //Trek to red key
        d.line(130, 150, 5, 190, 150, 30, "red", 3, 3, 0.1, true);
        d.line(190, 130, 35, 190, 40, 50, "red", 3, 3, 0.08, true);
        d.line(190, 10, 50, 80, 10, 50, "red", 3, 3, 0.15, true);
        
        d.plane(40, -10, 20, 80, -10, 20, "red", 8, 8, 0.1, true, 4, 7);
        
        //Red key
        d.point(50, 0, 30, "red_switch", 15, 15, false);
        
        //Exit
        d.point(0, 140, 30, "exit", 20, 15, true);
        
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