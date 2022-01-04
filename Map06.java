//Michael Calle
import java.util.ArrayList;

public class Map06{
    private ArrayList<Point> global = new ArrayList<>();
    private Draw d = new Draw();
    
    public Map06(ArrayList<Point>g,int lim){
        d.point(0, 0, 0, "user", 1, 1, false);
        
        //Starting walkway
        d.plane(-40,-40,0,40,-40,0,"white",12,8,0.1,true,12,7);
        d.plane(-20,40,0,20,40,0,"white",12,8,0.1,true,12,7);
        
        d.plane(-80, -10, 0, -60, -10, 0, "yellow", 8, 8, 0.1, true, 3, 7);
        d.plane(80, -10, 0, 60, -10, 0, "red", 8, 8, 0.1, true, 3, 7);
        d.plane(-10, -80, 0, 10, -80, 0, "blue", 8, 8, 0.1, true, 3, 7);
        
        d.point(-70, 0, 10, "yel_switch", 15, 15, false);
        d.point(70, 0, 10, "red_switch", 15, 15, false);
        d.point(0, -70, 10, "blue_switch", 15, 15, false);
        
        d.point(0, 70, 15, "yel_switch_locked", 15, 15, false);
        d.point(-10, 80, 15, "red_switch_locked", 15, 15, false);
        d.point(10, 80, 15, "blue_switch_locked", 15, 15, false);
        
        d.point(0, 150, 50, "congrats_invs", 130, 30, false);
        d.point(40, 400, 200, "fireworks01_invs", 400, 400, false);
        d.point(40, -400, 500, "fireworks01_invs", 400, 400, false);
        d.point(-300, 250, 600, "fireworks03_invs", 400, 400, false);
        d.point(0, -400, 300, "fireworks03_invs", 400, 400, false);
        d.point(500, 0, 120, "fireworks01_invs", 400, 400, false);
        d.point(-900, 0, 400, "fireworks05_invs", 400, 400, false);
        
        //Pegs
        d.line(-40, 40, 0, -40, 40, 40, "white", 8, 8, 0.1, true);
        d.line(40, 40, 0, 40, 40, 40, "white", 8, 8, 0.1, true);
        d.line(-40, -40, 0, -40, -40, 40, "white", 8, 8, 0.1, true);
        d.line(40, -40, 0, 40, -40, 40, "white", 8, 8, 0.1, true);
        
        //Roof Pegs
        d.line(-40, 40, 40, 40, 40, 40, "white", 8, 8, 0.1, true);
        d.line(40, 40, 40, 40, -40, 40, "white", 8, 8, 0.1, true);
        d.line(-40, -40, 40, 40, -40, 40, "white", 8, 8, 0.1, true);
        d.line(-40, -40, 40, -40, 40, 40, "white", 8, 8, 0.1, true);
        
        //Center
        d.line(-20, 20, 60, 20, 20, 60, "white", 8, 8, 0.2, true);
        d.line(-20, -20, 60, -20, 20, 60, "white", 8, 8, 0.2, true);
        d.line(-20, -20, 60, 20, -20, 60, "white", 8, 8, 0.2, true);
        d.line(20, -20, 60, 20, 20, 60, "white", 8, 8, 0.2, true);
        
        //Pegs to Center
        d.line(-40, 40, 40, -20, 20, 60, "white", 8, 8, 0.1, true);
        d.line(40, 40, 40, 20, 20, 60, "white", 8, 8, 0.1, true);
        d.line(-40, -40, 40, -20, -20, 60, "white", 8, 8, 0.1, true);
        d.line(40, -40, 40, 20, -20, 60, "white", 8, 8, 0.1, true);
        
        int x;
        int y;
        int z;
        for(int i=0;i<50;i++){
            x = (int)(Math.random()*1000)-500;
            y = (int)(Math.random()*1000)-500;
            z = (int)(Math.random()*1000)-500;
            d.point(x,y,z,"yel_switch_locked", 15, 15, false);
            
            x = (int)(Math.random()*1000)-500;
            y = (int)(Math.random()*1000)-500;
            z = (int)(Math.random()*1000)-500;
            d.point(x,y,z,"red_switch_locked", 15, 15, false);
            
            x = (int)(Math.random()*1000)-500;
            y = (int)(Math.random()*1000)-500;
            z = (int)(Math.random()*1000)-500;
            d.point(x,y,z,"blue_switch_locked", 15, 15, false);
        }
        
        d.removeDoubles();
        
        for(int i=0;i<d.size();i++){
            if(i%lim==0
                    || d.get(i).getID().equals("yel_switch")
                    || d.get(i).getID().equals("yel_switch_locked")
                    || d.get(i).getID().equals("red_switch")
                    || d.get(i).getID().equals("red_switch_locked")
                    || d.get(i).getID().equals("blue_switch")
                    || d.get(i).getID().equals("blue_switch_locked")
                    || d.get(i).getID().equals("congrats_invs")
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