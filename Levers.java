//Michael Calle
public class Levers{
    private boolean yel_switch = false;
    private boolean red_switch = false;
    private boolean blue_switch = false;
    
    public void setYel(boolean b){
        yel_switch = b;
    }
    public boolean getYel(){
        return yel_switch;
    }
    public void setRed(boolean b){
        red_switch = b;
    }
    public boolean getRed(){
        return red_switch;
    }
    public void setBlue(boolean b){
        blue_switch = b;
    }
    public boolean getBlue(){
        return blue_switch;
    }
}