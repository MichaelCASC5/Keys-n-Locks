//Michael Calle
import java.util.ArrayList;

public class Draw{
    private ArrayList<Point> global = new ArrayList<>();
    
    public void point(int x, int y, int z, String str, int w, int h, boolean b){
        global.add(new Point(x,y,z,str,w,h,b));
    }
    public void lineOld(int x1, int y1, int x2, int y2){
        x1*=2;
        y1*=2;
        x2*=2;
        y2*=2;
        
        y1+=(40*2);
        y2+=(40*2);
        
        for(double i=0;i<=1;i+=0.17){//0.02
            global.add(new Point((int)(x1 + (((double)i)*(x2-x1))),(int)(y1 + (((double)i)*(y2-y1))),(int)(0 + (((double)i)*(0-0))),"white",8,8,true));
            y1+=8;
            y2+=8;
            global.add(new Point((int)(x1 + (((double)i)*(x2-x1))),(int)(y1 + (((double)i)*(y2-y1))),(int)(0 + (((double)i)*(0-0))),"white",8,8,true));
            y1-=8;
            y2-=8;
        }
    }
    public void line(int x1, int y1, int z1, int x2, int y2, int z2, String str, int w, int h, double res, boolean b){
        for(double i=0;i<=1;i+=res){//0.02
            global.add(new Point((int)(x1 + (((double)i)*(x2-x1))),(int)(y1 + (((double)i)*(y2-y1))),(int)(z1 + (((double)i)*(z2-z1))),str,w,h,b));
        }
    }
    public void plane(int x1, int y1, int z1, int x2, int y2, int z2, String str, int w, int h, double res, boolean b, int n, int s){
        line(x1,y1,z1,x2,y2,z2,str,w,h,res,b);
        for(double i=0;i<n;i++){//0.02
            y1+=s;
            y2+=s;
            line(x1,y1,z1,x2,y2,z2,str,w,h,res,b);
        }
    }
    public void planeDiagL(int x1, int y1, int z1, int x2, int y2, int z2, String str, int w, int h, double res, boolean b, int n, int s){
        line(x1,y1,z1,x2,y2,z2,str,w,h,res,b);
        for(double i=0;i<n;i++){//0.02
            y1+=s;
            line(x1,y1,z1,x2,y2,z2,str,w,h,res,b);
        }
    }
    public void planeDiagR(int x1, int y1, int z1, int x2, int y2, int z2, String str, int w, int h, double res, boolean b, int n, int s){
        line(x1,y1,z1,x2,y2,z2,str,w,h,res,b);
        for(double i=0;i<n;i++){//0.02
            y2+=s;
            line(x1,y1,z1,x2,y2,z2,str,w,h,res,b);
        }
    }
    public void removeDoubles(){
        for(int i=0;i<global.size();i++){
            for(int j=i+1;j<global.size();j++){
                if(global.get(i).getX() == global.get(j).getX()
                        && global.get(i).getY() == global.get(j).getY()
                        && global.get(i).getZ() == global.get(j).getZ()){
                    global.remove(i);
                    j--;
                }
            }
        }
    }
    public int size(){
        return global.size();
    }
    public Point get(int i){
        return global.get(i);
    }
    public void printMap(){
        for(int i=0;i<global.size();i++){
            System.out.println(global.get(i).toString());
        }
        System.out.println("number of points: " + global.size());
    }
    public void printTotal(){
        System.out.println("number of points: " + global.size());
    }
}