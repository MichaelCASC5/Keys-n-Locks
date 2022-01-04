//Michael Calle
import java.util.ArrayList;

public class Map04{
    private ArrayList<Point> global = new ArrayList<>();
    private Draw d = new Draw();
    
    public Map04(ArrayList<Point>g,int lim){
        d.point(0, 0, 0, "user", 1, 1, false);
        
        //Starting walkway
//        d.plane(-20,-20,0,20,-20,0,"white",12,8,0.2,true,22,7);
        d.lineOld(-40,40,40,40);
        d.lineOld(-40,40,-40,-40);
        d.lineOld(-40,-40,40,-40);
        d.lineOld(40,-40,40,20);
        
        d.lineOld(40,-20,80,-20);
        d.lineOld(80,-20,80,-40);
        d.lineOld(80,-40,100,-40);
        d.lineOld(100,-40,100,-60);
        
        d.lineOld(100,-60,200,-60);
        d.lineOld(200,-60,200,-40);
        d.lineOld(200,-40,160,-40);
        d.lineOld(160,-40,160,0);
        
        d.lineOld(160,0,200,0);
        d.lineOld(200,0,200,60);
        d.lineOld(200,60,160,60);
        d.lineOld(160,60,160,120);
        
        d.lineOld(160,120,80,120);
        d.lineOld(80,120,80,180);
        d.lineOld(80,180,-40,180);
        d.lineOld(-40,180,-40,200);
        
        d.lineOld(-40,200,20,200);
        d.lineOld(20,200,20,240);
        d.lineOld(20,240,40,240);
        d.lineOld(40,240,40,260);
        
        d.lineOld(40,260,20,260);
        d.lineOld(20,260,20,300);
        d.lineOld(20,300,0,300);
        d.lineOld(0,300,0,280);
        
        d.lineOld(0,280,-60,280);
        d.lineOld(-60,280,-60,260);
        d.lineOld(-60,260,-100,260);
        d.lineOld(-100,260,-100,220);
        
        d.lineOld(-100,220,-160,220);
        d.lineOld(-160,220,-160,260);
        
        //After Exit
        d.lineOld(-180,260,-180,180);
        d.lineOld(-180,180,-160,180);
        d.lineOld(-160,180,-160,160);
        d.lineOld(-160,160,-240,160);
        
        d.lineOld(-240,160,-240,100);
        d.lineOld(-240,100,-220,100);
        d.lineOld(-220,100,-220,40);
        d.lineOld(-220,40,-140,40);
        
        d.lineOld(-140,40,-140,80);
        d.lineOld(-140,80,-120,80);
        d.lineOld(-120,80,-120,40);
        d.lineOld(-120,40,-100,40);
        
        d.lineOld(-100,40,-100,20);
        d.lineOld(-100,20,-40,20);
        
        //Middle
        d.lineOld(-20,40,-20,80);
        d.lineOld(-20,80,0,80);
        d.lineOld(0,80,0,40);
        
        d.lineOld(40,60,80,60);
        d.lineOld(80,60,80,80);
        d.lineOld(80,80,120,80);
        d.lineOld(120,80,120,100);
        
        d.lineOld(120,100,100,100);
        d.lineOld(100,100,100,80);
        d.lineOld(60,60,60,0);
        d.lineOld(60,0,80,0);
        
        d.lineOld(80,0,80,40);
        d.lineOld(80,40,100,40);
        d.lineOld(120,0,120,60);
        d.lineOld(120,60,140,60);
        
        d.lineOld(140,60,140,0);
        d.lineOld(140,0,120,0);
        
        //Half
        d.lineOld(-40,60,-80,60);
        d.lineOld(-80,60,-80,80);
        d.lineOld(-80,80,-40,80);
        d.lineOld(-40,60,-40,160);
        
        d.lineOld(-40,100,-60,100);
        d.lineOld(-60,100,-60,120);
        d.lineOld(-60,120,-40,120);
        
        d.lineOld(-40,160,40,160);
        d.lineOld(40,160,40,140);
        d.lineOld(40,140,20,140);
        d.lineOld(20,140,20,120);
        
        d.lineOld(20,120,-20,120);
        d.lineOld(-20,120,-20,140);
        d.lineOld(-20,140,-40,140);
        
        d.lineOld(-80,240,0,240);
        d.lineOld(0,240,0,220);
        d.lineOld(0,220,-20,220);
        d.lineOld(-20,220,-20,240);
        
        d.lineOld(-220,160,-220,120);
        d.lineOld(-220,120,-160,120);
        
        d.lineOld(-120,140,-140,140);
        d.lineOld(-140,140,-140,200);
        d.lineOld(-120,200,-120,160);
        d.lineOld(-120,180,-100,180);
        
        d.lineOld(-100,180,-100,100);
        d.lineOld(-100,200,-80,200);
        d.lineOld(-80,200,-80,180);
        
        d.lineOld(40, 40, 40, 60);
        
        d.point(240, 288, 10, "red_switch", 15, 15, false);
        d.point(-360, 590, 15, "red_switch_locked", 15, 15, true);
        
        //Exit
        d.point(-360, 608, 30, "exit", 20, 15, true);
        
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