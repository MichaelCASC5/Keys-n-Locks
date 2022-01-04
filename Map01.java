//Michael Calle
import java.util.ArrayList;

public class Map01{
    private ArrayList<Point> global = new ArrayList<>();
    private Draw d = new Draw();
    
    public Map01(ArrayList<Point>g,int lim){
        d.point(0, 0, 0, "user", 1, 1, false);
        
        //Starting Octogon and walkway
        d.plane(-10,-20,0,10,-20,0,"red",10,8,0.3,true,22,7);
        d.line(-20,-10,0,-20,10,0,"red",8,8,0.3,true);
        d.line(20,-10,0,20,10,0,"red",8,8,0.3,true);
        
        d.line(-10,20,0,-20,10,0,"red",8,8,0.3,true);
        d.line(10,20,0,20,10,0,"red",8,8,0.3,true);
        d.line(-20,-10,0,-10,-20,0,"red",8,8,0.3,true);
        d.line(20,-10,0,10,-20,0,"red",8,8,0.3,true);
        
        d.line(-15,-10,0,-15,10,0,"red",8,8,0.3,true);
        d.line(15,-10,0,15,10,0,"red",8,8,0.3,true);
        
        //Triangles
        d.line(-30,40,-5,30,40,-5,"red",8,5,0.08,true);
        d.line(-30,40,-5,0,40,30,"red",5,5,0.08,true);
        d.line(30,40,-5,0,40,30,"red",5,5,0.08,true);
        
        d.line(-30,80,-5,30,80,-5,"red",8,5,0.08,true);
        d.line(-30,80,-5,0,80,30,"red",5,5,0.08,true);
        d.line(30,80,-5,0,80,30,"red",5,5,0.08,true);
        
        d.line(-30,120,-5,30,120,-5,"red",8,5,0.08,true);
        d.line(-30,120,-5,0,120,30,"red",5,5,0.08,true);
        d.line(30,120,-5,0,120,30,"red",5,5,0.08,true);
        
        //Lobby
        d.plane(-60,140,0,40,140,0,"red",10,8,0.08,true,6,7);
        d.plane(40,120,0,90,120,0,"red",11,8,0.14,true,12,7);
        
        //Giant stairs
        d.planeDiagL(80,140,7,140,140,7,"blue",8,8,0.1,true,8,8);
        d.planeDiagR(80,204,14,140,204,14,"yellow",8,8,0.1,true,8,-8);
        d.plane(80,205,20,140,205,20,"orange",8,8,0.1,true,8,8);
        
        //Parkour squares
        d.plane(100,300,20,120,300,20,"orange",5,5,0.1,true,5,4);
        d.plane(100,350,20,120,350,20,"orange",5,5,0.1,true,5,4);
        d.plane(100,350,20,120,350,20,"orange",5,5,0.1,true,5,4);
        
        //Ramp
        d.plane(100,400,20,160,400,60,"pink",5,5,0.08,true,5,4);
        
        //Yellow platform
        d.line(140,450,60,160,450,60,"yellow",10,5,0.3,true);
        d.line(140,455,60,160,455,60,"yellow",10,5,0.3,true);
        d.plane(100,460,60,160,460,60,"yellow",10,5,0.08,true,5,5);
        
        //Yellow Switch
        d.point(120,480,70,"yel_switch",15,15,false);
        
        //Yellow Lock
        d.point(-60,160,20,"yel_switch_locked",15,15,true);
        
        //Enterance to Maze
        d.plane(-140,140,0,-60,140,0,"black_to_white",11,8,0.1,true,20,7);
//        d.plane(-180,140,0,-140,140,0,"black_to_white",11,8,0.2,true,3,7);
//        d.plane(-180,160,0,-140,160,30,"black_to_white",11,8,0.2,true,5,7);//stairs
//        d.plane(-140,160,30,-100,160,30,"black_to_white",11,8,0.2,true,5,7);
        
        //Maze
        d.line(-140, 200, 12, -100, 200, 12, "black_to_white", 8, 17, 0.1, true);
        d.line(-110, 240, 12, -60, 240, 12, "black_to_white", 8, 17, 0.1, true);
        
        //The trek to the exit
        d.line(-120, 300, 0, -120, 360, 0, "black_to_white", 8, 8, 0.1, true);
        d.line(-120, 380, 5, -100, 460, 5, "black_to_white", 8, 8, 0.1, true);
        
        //Crazy spiral
        d.line(-100, 480, 10, -42, 498, 40, "black_to_white", 8, 8, 0.05, true);
        d.line(-50, 500, 40, -200, 510, 85, "black_to_white", 8, 8, 0.03, true);
        d.line(-200, 518, 85, -40, 500, 130, "black_to_white", 8, 8, 0.03, true);
        
        //Platform
        d.plane(-10, 500, 80, 10, 500, 80, "white", 8, 8, 0.3, true, 2, 7);
        
        //Stepping stones to exit
        d.line(0, 540, 80, 0, 570, 80, "white", 2, 2, 0.2, true);
        
        //Exit Platform
        d.plane(-10, 600, 80, 10, 600, 80, "white", 8, 8, 0.3, true, 4, 7);
        
        //Exit Sign
        d.point(0, 640, 105, "exit", 20, 15, true);
        
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