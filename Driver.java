//Michael Calle
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.Font;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.Robot;
import java.awt.MouseInfo;
import java.awt.Toolkit;

public class Driver extends JComponent implements KeyListener, MouseListener, MouseMotionListener{
    private int WIDTH, HEIGHT;
    
    //Camera Rotation
    private int yaw = 0;
    private int pitch = 0;
    
    //Compensation
    private double comp = 0;
    
    //User locations
    private Point user = new Point(0,0,10);
    private double userX, userY, userZ;
    
    //Movement
    private int speed = 1;
    private int rateOfTurn = 3;
    
    //Gravity
    private int gravTime = 0;
    private int upTimeSave;
    private int upTime = upTimeSave;
    private double accel = 0.03;
    private boolean isJump = false;
    
    //Lim
    String[] graphics = {"normal", "reduced", "potato", "custom"};
    private int lim = 1;
    
    //Cheats
    private boolean cheats;
    
    //Difficulty
    String[] difficulties = {"easy", "normal", "hard"};
    private int diff;
    
    //Map Counter
    int map;
    
    //Picture Scale
    private double scale;
    
    //Collisions
    private boolean checkF, checkB, checkL, checkR, checkD, SENS_yel_switch, SENS_red_switch, SENS_blue_switch;
    
    //Switches
    private Levers lev = new Levers();
    
    //Keybinds
    private Keys key = new Keys();
    
    //Global and Local
    private ArrayList<Point> global = new ArrayList<>();
    private ArrayList<Point> local = new ArrayList<>();
    private ArrayList<Point> save = new ArrayList<>();
    
    //Images
    private static ImageIcon
            blank,
            cement_01,
            switch_message,
            exit,
            yel_switch_locked,
            yel_switch_opened,
            yel_switch,
            red_switch_locked,
            red_switch_opened,
            red_switch,
            blue_switch_locked,
            blue_switch_opened,
            blue_switch,
            congrats,
            
            fireworks_01,
            fireworks_02,
            fireworks_03,
            fireworks_04,
            fireworks_05,
            fireworks_06
    ;
    
    private static ImageIcon
            skyNorth,
            skySouth
    ;
   
    public Driver(){
        userX = user.getX();
        userY = user.getY();
        userZ = user.getZ();
        
        cheats = Manager.cheats();
        
        //Download Readme
        String[] readme_opts = {
            "Download!",
            "No, thanks"
        };
        int readme_opt = JOptionPane.showOptionDialog(null,
            "Press \"download\" to download "
                    + "\na README.txt file. This file will"
                    + "\nsave in the same location"
                    + "\nas this game.",
            "Input",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            readme_opts,
            readme_opts[1]
        );
        if(readme_opt == 0){
            Manager.create();
        }

        //Seting resolution choices
        String[] options = {
            "2736, 1824",
            "1920, 1080",
            "1280, 600",
            "1152, 864",
            "1024, 768",
            "800, 600",
            "640, 480",
        };
        
        //Choosing Resolution
        String setStr = (String)JOptionPane.showInputDialog(
            null,
            "Choose Resolution:\n",
            "Launcher",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[1]
        );
        
        int set = 0;
        for(int i=0;i<options.length;i++){
            if(setStr.equals(options[i])){
                set = i;
            }
        }
        
        //Rate of Turn
        String s = (String)JOptionPane.showInputDialog(
            null,
            "Enter turn speed:\nYou can press [M] in-game\nto toggle mouse-look",
            "Input",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            "3"
        );
        key.setM(true);
        rateOfTurn = Integer.parseInt(s);
        
        //Difficulty Settings
        diff = JOptionPane.showOptionDialog(
            null,
            "Choose Difficulty:",
            "Input",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            difficulties,
            difficulties[1]
        );
        
        if(diff == 0){
            accel = 0.015;
            upTimeSave = 60;
        }else if(diff == 1){
            accel = 0.03;
            upTimeSave = 30;
        }else if(diff == 2){
            accel = 0.06;
            upTimeSave = 20;
        }
        
        //Optimization Settings
        int OptiAns;
        OptiAns = JOptionPane.showOptionDialog(
            null,
            "Graphics Settings:",
            "EXPERIMENTAL",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            graphics,
            graphics[0]
        );
        
        if(OptiAns == 0){
            lim = 1;
        }else if(OptiAns == 1){
            lim = 2;
        }else if(OptiAns == 2){
            lim = 3;
        }else if(OptiAns == 3){
            String limAns = JOptionPane.showInputDialog("Enter custom reduction: [over 3]");
            lim = Integer.parseInt(limAns);
        }
        
        //Actual resolution values
        int[] res = {
            2736, 1824,
            1920, 1080,
            1280, 600,
            1152, 864,
            1024, 768,
            800, 600,
            640, 480,
        };
       
        //Setting resolution
        WIDTH = (res[(set*2)]);
        HEIGHT = res[(set*2)+1];
        
        //Setting scale
        scale = (double)(res[(set*2)]) / 3000.0;
        
        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Keys 'n Locks");
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));
        gui.setResizable(false);
        gui.getContentPane().add(this);
       
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        gui.addKeyListener(this);
        gui.addMouseListener(this);
        gui.addMouseMotionListener(this);
        
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        ImageIcon mouse = new ImageIcon(Driver.class.getResource("mouse.png"));
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(mouse.getImage(), new java.awt.Point(0, 0), "blank cursor");
        gui.getContentPane().setCursor(blankCursor);
        
        //Map
        new Map01(global,lim);
        for(int i=0;i<global.size();i++){
            local.add(new Point(0,0,0));
        }
        map = 1;
    }
    public double dist(Point p){
        double output;
        output = Math.sqrt((Math.pow(p.getY()-user.getY(),2))
                + Math.pow(p.getX()-user.getX(),2)
                + Math.pow(p.getZ()-user.getZ(),2));
        return output;
    }
    public boolean sensF(String str){
        boolean output = false;
        for(int i=0;i<local.size();i++){
            if(local.get(i).getID().equals(str)){
                for(int a=0;a<20;a++){
                    for(int b=0;b<10;b++){
                        for(int c=0;c<10;c++){
                            if(local.get(i).getY() == user.getY() + a
                                    && local.get(i).getX() == user.getX() - 5 + b
                                    && local.get(i).getZ() == user.getZ() - 5 + c){
                                output = true;
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
    public boolean collF(){
        boolean output = true;
        for(int i=0;i<local.size();i++){
            if(local.get(i).isActor()){
                for(int a=0;a<10;a++){
                    for(int b=0;b<10;b++){
                        for(int c=0;c<10;c++){
                            if(local.get(i).getY() == user.getY() + a
                                    && local.get(i).getX() == user.getX() - 5 + b
                                    && local.get(i).getZ() == user.getZ() - 5 + c){
                                output = false;
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
    public boolean collB(){
        boolean output = true;
        for(int i=0;i<local.size();i++){
            if(local.get(i).isActor()){
                for(int a=0;a<10;a++){
                    for(int b=0;b<10;b++){
                        for(int c=0;c<10;c++){
                            if(local.get(i).getY() == user.getY() - a
                                    && local.get(i).getX() == user.getX() - 5 + b
                                    && local.get(i).getZ() == user.getZ() - 5 + c){
                                output = false;
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
    public boolean collL(){
        boolean output = true;
        for(int i=0;i<local.size();i++){
            if(local.get(i).isActor()){
                for(int a=0;a<10;a++){
                    for(int b=0;b<10;b++){
                        for(int c=0;c<10;c++){
                            if(local.get(i).getY() == user.getY() - 5 + a
                                    && local.get(i).getX() == user.getX() - b
                                    && local.get(i).getZ() == user.getZ() - 5 + c){
                                output = false;
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
    public boolean collR(){
        boolean output = true;
        for(int i=0;i<local.size();i++){
            if(local.get(i).isActor()){
                for(int a=0;a<10;a++){
                    for(int b=0;b<10;b++){
                        for(int c=0;c<10;c++){
                            if(local.get(i).getY() == user.getY() - 5 + a
                                    && local.get(i).getX() == user.getX() + b
                                    && local.get(i).getZ() == user.getZ() - 5 + c){
                                output = false;
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
    public boolean collD(){
        boolean output = true;
        for(int i=0;i<local.size();i++){
            if(local.get(i).isActor()){
                for(int a=0;a<10;a++){
                    for(int b=0;b<10;b++){
                        for(int c=0;c<10;c++){
                            if(local.get(i).getY() == user.getY() - 5 + a
                                    && local.get(i).getX() == user.getX() - 5 + b
                                    && local.get(i).getZ() == user.getZ() - c){
                                output = false;
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
    public void paintComponent(Graphics g){
        //Dying
        if(userZ < -500){
            global.clear();
            local.clear();
            if(map == 1){
                new Map01(global,lim);
            }else if(map == 2){
                new Map02(global,lim);
            }else if(map == 3){
                new Map03(global,lim);
            }else if(map == 4){
                new Map04(global,lim);
            }else if(map == 5){
                new Map05(global,lim);
            }else if(map == 6){
                new Map06(global,lim);
            }
            
            for(int i=0;i<global.size();i++){
                local.add(new Point());
            }
            
            userX = 0;
            userY = 0;
            userZ = 10;
            
            pitch = 0;
            yaw = 0;
            
            lev.setYel(false);
            lev.setRed(false);
            lev.setBlue(false);
            
            gravTime = 0;
        }
        
        //Switching between maps
        if(map == 1){//to map 2
            if(dist(new Point(0,640,95)) < 20){
                global.clear();
                local.clear();
                new Map02(global,lim);
                for(int i=0;i<global.size();i++){
                    local.add(new Point());
                }
                
                userX = 0;
                userY = 0;
                userZ = 10;
                
                pitch = 0;
                yaw = 0;
                
                map = 2;
                
                lev.setYel(false);
                lev.setRed(false);
                lev.setBlue(false);
            }
        }else if(map == 2){//to map 3
            if(dist(new Point(0,140,15)) < 20){
                global.clear();
                local.clear();
                new Map03(global,lim);
                for(int i=0;i<global.size();i++){
                    local.add(new Point());
                }
                
                userX = 0;
                userY = 0;
                userZ = 10;
                
                pitch = 0;
                yaw = 0;
                
                map = 3;
                
                lev.setYel(false);
                lev.setRed(false);
                lev.setBlue(false);
            }
        }else if(map == 3){//to map 4
            if(dist(new Point(80,20,15)) < 20){
                global.clear();
                local.clear();
                new Map04(global,lim);
                for(int i=0;i<global.size();i++){
                    local.add(new Point());
                }
                
                userX = 0;
                userY = 0;
                userZ = 10;
                
                pitch = 0;
                yaw = 0;
                
                map = 4;
                
                lev.setYel(false);
                lev.setRed(false);
                lev.setBlue(false);
            }
        }else if(map == 4){//to map 5
            if(dist(new Point(-360,608,15)) < 20){
                global.clear();
                local.clear();
                new Map05(global,lim);
                for(int i=0;i<global.size();i++){
                    local.add(new Point());
                }
                
                userX = 0;
                userY = 0;
                userZ = 10;
                
                pitch = 0;
                yaw = 0;
                
                map = 5;
                
                lev.setYel(false);
                lev.setRed(false);
                lev.setBlue(false);
            }
        }else if(map == 5){//to map 6
            if(dist(new Point(-300, 355, 120)) < 20){
                global.clear();
                local.clear();
                new Map06(global,lim);
                for(int i=0;i<global.size();i++){
                    local.add(new Point());
                }
                
                userX = 0;
                userY = 0;
                userZ = 10;
                
                pitch = 0;
                yaw = 0;
                
                map = 6;
                
                lev.setYel(false);
                lev.setRed(false);
                lev.setBlue(false);
            }
        }else if(map == 6){
            if(dist(new Point(0, 90, 15)) < 20){
                for(int i=0;i<global.size();i++){
                    if(global.get(i).getID().equals("congrats_invs")){
                        global.get(i).setID("congrats");
                    }else if(global.get(i).getID().equals("fireworks01_invs")){
                        global.get(i).setID("fireworks_01");
                    }else if(global.get(i).getID().equals("fireworks02_invs")){
                        global.get(i).setID("fireworks_02");
                    }else if(global.get(i).getID().equals("fireworks03_invs")){
                        global.get(i).setID("fireworks_03");
                    }else if(global.get(i).getID().equals("fireworks04_invs")){
                        global.get(i).setID("fireworks_04");
                    }else if(global.get(i).getID().equals("fireworks05_invs")){
                        global.get(i).setID("fireworks_05");
                    }else if(global.get(i).getID().equals("fireworks06_invs")){
                        global.get(i).setID("fireworks_06");
                    }
                }
            }
        }
        
        //Flipping Switches
        SENS_yel_switch = sensF("yel_switch");
        SENS_red_switch = sensF("red_switch");
        SENS_blue_switch = sensF("blue_switch");
        
        if(SENS_yel_switch && key.getE() && !lev.getYel()){
            lev.setYel(true);
            
            //Opening locks
            for(int i=0;i<global.size();i++){
                if(global.get(i).getID().equals("yel_switch_locked")){
                    global.get(i).setID("yel_switch_opened");
                    global.get(i).setActor(false);
                }else if(global.get(i).getID().equals("yel_switch")){
                    global.get(i).setID("invs");
                    global.get(i).setActor(false);
                }else if((global.get(i).getID().equals("yel_path"))){
                    global.get(i).setID("yellow");
                    global.get(i).setActor(true);
                }
            }
        }
        
        if(SENS_red_switch && key.getE() && !lev.getRed()){
            lev.setRed(true);
            
            //Opening locks
            for(int i=0;i<global.size();i++){
                if(global.get(i).getID().equals("red_switch_locked")){
                    global.get(i).setID("red_switch_opened");
                    global.get(i).setActor(false);
                }else if(global.get(i).getID().equals("red_switch")){
                    global.get(i).setID("invs");
                    global.get(i).setActor(false);
                }else if((global.get(i).getID().equals("red_path"))){
                    global.get(i).setID("red");
                    global.get(i).setActor(true);
                }
            }
        }
        
        if(SENS_blue_switch && key.getE() && !lev.getBlue()){
            lev.setBlue(true);
            
            //Opening locks
            for(int i=0;i<global.size();i++){
                if(global.get(i).getID().equals("blue_switch_locked")){
                    global.get(i).setID("blue_switch_opened");
                    global.get(i).setActor(false);
                }else if(global.get(i).getID().equals("blue_switch")){
                    global.get(i).setID("invs");
                    global.get(i).setActor(false);
                }else if((global.get(i).getID().equals("blue_path"))){
                    global.get(i).setID("blue");
                    global.get(i).setActor(true);
                }
            }
        }
        
        //Moving stuff
        for(int i=0;i<global.size();i++){
            if(global.get(i).getID().equals("moving_platform_orangeX")){
                if(Math.random() < 0.5)
                    global.get(i).setX(global.get(i).getX() + 1);
                local.get(i).setAll(global.get(i));
            }else if(global.get(i).getID().equals("moving_platform_orangeY")
                    && global.get(i).getY() < 300){
                if(global.get(i).getY() == 299){
                    global.get(i).setActor(false);
                    global.get(i).setID("invs");
                }
                if(Math.random() < 0.5)
                    global.get(i).setY(global.get(i).getY() + 1);
                local.get(i).setAll(global.get(i));
            }
        }
        
        //Adjust Scaling
        if(key.getU()){
            scale-=0.05;
        }else if(key.getI()){
            scale+=0.05;
        }
        if(scale < 0){
            scale = 0;
        }
        
        //Calculates Z Rotation
        double x1, y1, z1;
        double a1,b1;
        
        Point savePoint = new Point(0,0,0);
        for(int i=0;i<global.size();i++){
            if(cheats){
                if(global.get(i).getID().equals("user")){
                    global.get(i).setX((int)userX);
                    global.get(i).setY((int)userY+20);
                    global.get(i).setZ((int)userZ);
                }
            }
            
            x1 = global.get(i).getX() - userX;
            y1 = global.get(i).getY() - userY;
            z1 = global.get(i).getZ() - userZ;
            
            a1 = x1*Math.cos(yaw/(180.0/Math.PI));
            b1 = y1*Math.sin(yaw/(180.0/Math.PI));
            
            local.get(i).setX((int)(a1 - b1 + userX));
            
            a1 = y1*Math.cos(yaw/(180.0/Math.PI));
            b1 = x1*Math.sin(yaw/(180.0/Math.PI));
            
            local.get(i).setY((int)(a1 + b1 + userY));
            
            //Resets
            savePoint.setAtributes(global.get(i));
            local.get(i).setAtributes(savePoint);
        }
        
        //Calculates Collision
        checkF = collF();
        checkB = collB();
        checkL = collL();
        checkR = collR();
        
        //Invisible Walls
        if(map == 1){
            if(!lev.getYel() && userX < -50){
                userX = -50;
            }
        }else if(map == 2){
            if(!lev.getYel() && userX > 25){
                userX = 25;
            }
            if(!lev.getRed() && (userY > 100 && userX > -20 && userX < 20)){
                userY = 100;
            }
        }
        else if(map == 3){
            if(!lev.getYel() && (userX > 20 && userX < 30 && userY > 0 && userY < 140 && userZ > 0)){
                userX = 20;
            }
            if(!lev.getBlue() && (userY < 60 && userX > 40 && userX < 100)){
                userY = 60;
            }
        }else if(map == 4){
            if(!lev.getRed() && (userY > 560 && userX < -300)){
                userY = 560;
            }
        }else if(map == 5){
            if(!lev.getYel() && (userY > 320 && userX < -150 && userZ > -20)){
                userY = 320;
            }
            if(!lev.getRed() && (userY > 320 && userX < -230 && userZ > 40)){
                userY = 320;
            }
            if(!lev.getBlue() && (userY > 320 && userX < -280 && userZ > 80)){
                userY = 320;
            }
        }else if(map == 6){
            if((!lev.getYel() || !lev.getRed() || !lev.getBlue()) && (userY > 50)){
                userY = 50;
            }
        }
        
        //Jetpack
        if(key.getSpace() && cheats){
            userZ+=2;
            gravTime = 0;
        }
        
        checkD = collD();
        
        //Calculates X Rotation
        for(int i=0;i<local.size();i++){
            x1 = local.get(i).getX() - userX;
            y1 = local.get(i).getY() - userY;
            z1 = global.get(i).getZ() - userZ;
           
            a1 = y1*Math.cos(pitch/(180/Math.PI));
            b1 = z1*Math.sin(pitch/(180/Math.PI));
           
            local.get(i).setY((int)(a1 - b1 + userY));
           
            a1 = z1*Math.cos(pitch/(180/Math.PI));
            b1 = y1*Math.sin(pitch/(180/Math.PI));
           
            local.get(i).setZ((int)(a1 + b1 + userZ));
        }
        
        //Reset Yaw and Pitch
        if(yaw >= 360){
            yaw = yaw - 360;
        }else if(yaw < 0){
            yaw = yaw + 360;
        }
        if(pitch >= 360){
            pitch = pitch - 360;
        }else if(pitch < 0){
            pitch = pitch + 360;
        }
        if(pitch > 90 && pitch < 180){
            pitch = 90;
        }else if(pitch < 270 && pitch > 180){
            pitch = 270;
        }
       
        //Comp comp          (Computing compensation)
        comp = (yaw%90)/90.0;
       
        //Draws Background
        Color south = new Color(198, 250, 254);//old gray 90, 90, 90
        Color north = new Color(100, 100, 100);//old gray
        Color ground = new Color(33,56,47);
        Color skyBlue = new Color(121,160,209);//137,209,235
        Color nightSky = new Color(10,17,18);
        
        int horizon = 0;
        if(pitch > 0 && pitch < 100){
            horizon = pitch;
        }else if(pitch < 360 && pitch >= 100){
            horizon = (360 - pitch)*-1;
        }
        horizon*=20*scale;
        
        //North
        Image skies;
        g.setColor(skyBlue);
        skies = skyNorth.getImage();
        g.fillRect(0, 0, WIDTH, (HEIGHT/2)-horizon);
        g.drawImage(skies,0,-400-horizon,WIDTH,(int)(HEIGHT/2)+400,null);
        
        //South
        g.setColor(south);
        skies = skySouth.getImage();
        g.fillRect(0, HEIGHT/2-horizon, WIDTH, HEIGHT/2+horizon);
        g.drawImage(skies,0,(HEIGHT/2)-horizon,WIDTH,(HEIGHT/2*2),null);
        
        //Rearranges local
        Point temp = new Point(0,0,0);
        for(int i=0;i<local.size();i++){
            for(int j=i+1;j<local.size();j++){
                if(dist(local.get(j)) > dist(local.get(i))){
                    temp.setAll(local.get(i));
                    local.get(i).setAll(local.get(j));
                    local.get(j).setAll(temp);
                }
            }
        }
        
        //g2d
        Graphics2D g2d = (Graphics2D)g;
        
        //Drawing Tiles
        double hypo;
        int hypoI;
       
        int xDist, yDist, zDist;
        int tWidth, tHeight;
        double parr;
        
        Image x;
        for(int i=0;i<local.size();i++){
            //Finds distance on the x and y axis
            xDist = local.get(i).getX() - user.getX();
            yDist = local.get(i).getY() - user.getY();
            zDist = local.get(i).getZ() - user.getZ();
           
            //Distance between local
            hypo = dist(local.get(i));
            hypoI = ((int)(hypo*1.2));
           
            //Calculates player-oriented lighting
            int col = (int)dist(local.get(i));
            
            if(col > 255){
                col = 255;
            }else if(col < 0){
                col = 0;
            }
            
            Color shader;//130  137,209,235
            
            //Calculates parralax
            parr = (1000.0/((double)yDist));
            
            //Building Tiles
//            tHeight = (int)(((-1*(1.0/6.0)*((double)(pitch)))+15));
            tWidth = local.get(i).getWidth();
            tHeight = local.get(i).getHeight();
            
            int a,b,c,d;
            a = (int)((WIDTH/2)+(parr*(xDist))-((parr*tWidth)/2));
            b = (int)(((HEIGHT/2)-(parr*zDist))-((parr*tHeight)/2));
            c = (int)((parr*tWidth));
            d = (int)(parr*tHeight);
            
            //Scaling based on settings
            a = (int)(WIDTH/2 + (scale * (a - WIDTH/2)));
            b = (int)(HEIGHT/2 + (scale * (b - HEIGHT/2)));
            c*=scale;
            d*=scale;
            
            //At least a pixel wide
            if(c < 1){
                c = 1;
            }
            if(d < 1){
                d = 1;
            }
            
            //Drawing Textures
            String setTex = local.get(i).getID();
            
            if(local.get(i).getY() > user.getY()){
                if(setTex.equals("white")){
                    shader = new Color(255-col,255-col,255-col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("black")){
                    g.setColor(Color.black);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("red")){
                    shader = new Color(255-col,0,0);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("green") || setTex.equals("user")){
                    shader = new Color(0,255-col,0);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("blue")){
                    shader = new Color(0,0,255-col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("pink")){
                    int col2 = 0;
                    int shade = 100;
                    if(col > 255-shade){
                        col2 = col - (255-shade);
                    }
                    shader = new Color(255-col,shade-col2,shade-col2);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("yellow")){
                    shader = new Color(255-col,255-col,0);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("yellow_to_blue")){
                    shader = new Color(255-col,255-col,col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("orange")){
                    shader = new Color(255-col,(255/2)-(col/2),0);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("red_to_green")){
                    shader = new Color(255-col,col,0);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("red_to_blue")){
                    shader = new Color(255-col,0,col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("blue_to_red")){
                    shader = new Color(col,0,255-col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("blue_to_green")){
                    shader = new Color(0,col,255-col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("blue_to_pink")){
                    shader = new Color(255-col,col,255-col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("black_to_white")){
                    if(col > 100){
                        col = 100;
                    }
                    shader = new Color(col,col,col);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("moving_platform_orangeX") || setTex.equals("moving_platform_orangeY")){
                    shader = new Color(255-col,(255/2)-(col/2),0);
                    g.setColor(shader);
                    g.fillRect(a,b,c,d);
                }else if(setTex.equals("invs")
                        || setTex.equals("yel_path")
                        || setTex.equals("red_path")
                        || setTex.equals("blue_path")
                        || setTex.equals("congrats_invs")
                        || setTex.equals("fireworks01_invs")
                        || setTex.equals("fireworks02_invs")
                        || setTex.equals("fireworks03_invs")
                        || setTex.equals("fireworks04_invs")
                        || setTex.equals("fireworks05_invs")
                        || setTex.equals("fireworks06_invs")){
                    //Ommitted
                }else if(setTex.equals("cement_01")){
                    x = cement_01.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("congrats")){
                    x = congrats.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("fireworks_01")){
                    x = fireworks_01.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("fireworks_02")){
                    x = fireworks_02.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("fireworks_03")){
                    x = fireworks_03.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("fireworks_04")){
                    x = fireworks_04.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("fireworks_05")){
                    x = fireworks_05.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("fireworks_06")){
                    x = fireworks_06.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("yel_switch_locked")){
                    x = yel_switch_locked.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("yel_switch_opened")){
                    x = yel_switch_opened.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("yel_switch")){
                    x = yel_switch.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("red_switch_locked")){
                    x = red_switch_locked.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("red_switch_opened")){
                    x = red_switch_opened.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("red_switch")){
                    x = red_switch.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("blue_switch_locked")){
                    x = blue_switch_locked.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("blue_switch_opened")){
                    x = blue_switch_opened.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("blue_switch")){
                    x = blue_switch.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else if(setTex.equals("exit")){
                    x = exit.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }else{
                    x = blank.getImage();
                    g2d.drawImage(x,a,b,c,d,null);
                }
                if(key.getO()){
                    String posStr = "" + local.get(i).printGlobal();
                    g.setColor(Color.black);
                    g.fillRect(a-5,b-15,6*posStr.length(),20);
                    g.setColor(Color.white);
                    g.drawString(posStr,a,b);
                }
            }
        }
        
        //OverLay
        if(SENS_yel_switch || SENS_red_switch || SENS_blue_switch){
            x = switch_message.getImage();
            g2d.drawImage(x,0,0,WIDTH,HEIGHT,null);
        }
        
        g.setColor(Color.green);
        g.drawString("Press ESC to escape",0+10,0+20);
        g.drawString("MAP " + map,0+10,0+40);
        g.drawString("Find the exit!",0+10,0+60);
        g.drawString(difficulties[diff],0+10,0+100);
        if(cheats){
            g.drawString("jetpack",0+10,0+120);
        }
    }
    public void loop(){
        //Key Movements
        if(key.getW() && checkF){//FORWARD
            if(yaw >= 0 && yaw < 90){//Top right
                userX = (userX+0)+comp;
                userY = (userY+1)-comp;
            }else if(yaw >= 180 && yaw < 270){//Bottom left
                userX = (userX-0)-comp;
                userY = (userY-1)+comp;
            }else if(yaw >= 270 && yaw < 360){//Top left
                userX = (userX+0)-(1-comp);
                userY = (userY+1)-(1-comp);
            }else{//Bottom right
                userX = (userX-0)+(1-comp);
                userY = (userY-1)+(1-comp);
            }
        }
        if(key.getS() && checkB){//BACKWARD
            if(yaw >= 0 && yaw < 90){//Top right
                userX = (userX-0)-comp;
                userY = (userY-1)+comp;
            }else if(yaw >= 180 && yaw < 270){//Bottom left
                userX = (userX+0)+comp;
                userY = (userY+1)-comp;
            }else if(yaw >= 270 && yaw < 360){//Top left
                userX = (userX-0)+(1-comp);
                userY = (userY-1)+(1-comp);
            }else{//Bottom right
                userX = (userX+0)-(1-comp);
                userY = (userY+1)-(1-comp);
            }
        }
        if(key.getA() && checkL){//LEFT
            if(yaw >= 0 && yaw < 90){//Top right
                userX = (userX-1)+comp;
                userY = (userY-0)+comp;
            }else if(yaw >= 180 && yaw < 270){//Bottom left
                userX = (userX+1)-comp;
                userY = (userY-0)-comp;
            }else if(yaw >= 270 && yaw < 360){//Top left
                userX = (userX-0)-comp;
                userY = (userY-1)+comp;
            }else{//Bottom right
                userX = (userX+0)+comp;
                userY = (userY+1)-comp;
            }
        }
        if(key.getD() && checkR){//RIGHT
            if(yaw >= 0 && yaw < 90){//Top right
                userX = (userX+1)-comp;
                userY = (userY-0)-comp;
            }else if(yaw >= 180 && yaw < 270){//Bottom left
                userX = (userX-1)+comp;
                userY = (userY-0)+comp;
            }else if(yaw >= 270 && yaw < 360){//Top left
                userX = (userX-0)+comp;
                userY = (userY+1)-comp;
            }else{//Bottom right
                userX = (userX+0)-comp;
                userY = (userY-1)+comp;
            }
        }
        
        //Everything gravity
        if(collD()){
            userZ = userZ - (accel * gravTime);
            gravTime++;
        }else{
            gravTime = 0;
        }
        
        if(key.getSpace() && !collD()){
            isJump = true;
        }
        if(isJump){
            userZ = userZ + (accel * upTime);
            upTime--;
            if(upTime < 1){
                isJump = false;
                upTime = upTimeSave;
            }
        }
       
        user.setX((int)(userX)*speed);
        user.setY((int)(userY)*speed);
        user.setZ((int)(userZ));
        
        if(key.getArL()){//ARROW LEFT
            yaw-=rateOfTurn;
        }
        if(key.getArR()){//ARROW RIGHT
            yaw+=rateOfTurn;
        }
       
        if(key.getArU()){//ARROW UP
            pitch-=rateOfTurn;
        }
        if(key.getArD()){//ARROW DOWN
            pitch+=rateOfTurn;
        }
        
        if(key.getCtrl() && cheats){
            if(Math.random() < 0.5){
                global.add(new Point((int)userX,(int)(userY+20),(int)userZ,"blue_to_pink",7,7,true));
                local.add(new Point((int)userX,(int)(userY+20),(int)userZ,"blue_to_pink",7,7,true));
            }
        }
        if(key.getShiftL() && cheats){
            for(int i=0;i<global.size();i++){
                if(global.get(i).getGlobalX() == (int)userX){
                    global.remove(i);
                    local.remove(i);
                }
            }
        }
        repaint();
    }
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        int k = e.getKeyCode();
        
        if(k == 17){//Ctrl and shift
            key.setCtrl(true);
        }else if(k == 16){
            key.setShiftL(true);
        }
        
        if(k == 87){//FORWARD
            key.setW(true);
        }else if(k == 83){//BACKWARD
            key.setS(true);
        }else if(k == 65){//LEFT
            key.setA(true);
        }else if(k == 68){//RIGHT
            key.setD(true);
        }else if(k == 69){//USE
            key.setE(true);
        }
        
        if(k == 32){//UP
            key.setSpace(true);
        }else if(k == 79){//DOWN
            if(key.getO()){
                key.setO(false);
            }else{
                key.setO(true);
            }
        }
        
        if(k == 77){//M
            if(key.getM()){
                key.setM(false);
            }else{
                key.setM(true);
            }
        }
        
        if(k == 37){//ARROW LEFT
            key.setArL(true);
        }else if(k == 39){//ARROW RIGHT
            key.setArR(true);
        }else if(k == 38){//ARROW UP
            key.setArU(true);
        }else if(k == 40){//ARROW DOWN
            key.setArD(true);
        }
        
        if(k == 85){
            key.setU(true);
        }else if(k == 73){
            key.setI(true);
        }
       
        if(k == 27){//ESC
            System.exit(0);
        }
    }
    public void keyReleased(KeyEvent e){
        int k = e.getKeyCode();
       
        if(k == 17){//Ctrl and shift
            key.setCtrl(false);
        }else if(k == 16){
            key.setShiftL(false);
        }
        
        if(k == 87){//FORWARD
            key.setW(false);
        }else if(k == 83){//BACKWARD
            key.setS(false);
        }else if(k == 65){//LEFT
            key.setA(false);
        }else if(k == 68){//RIGHT
            key.setD(false);
        }else if(k == 69){//USE
            key.setE(false);
        }
       
        if(k == 37){//ARROW LEFT
            key.setArL(false);
        }else if(k == 39){//ARROW RIGHT
            key.setArR(false);
        }
        if(k == 38){//ARROW UP
            key.setArU(false);
        }else if(k == 40){//ARROW DOWN
            key.setArD(false);
        }
       
        if(k == 32){//UP
            key.setSpace(false);
        }else if(k == 17){//DOWN
//            ctrl = false;
        }
        
        if(k == 85){
            key.setU(false);
        }else if(k == 73){
            key.setI(false);
        }
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseMoved(MouseEvent e){
        if(key.getM()){
            java.awt.Point locOnScreen = e.getLocationOnScreen();
            double xOnScreen = locOnScreen.getX();
            double yOnScreen = locOnScreen.getY();
            
            int xOnGUI = e.getX();
            int yOnGUI = e.getY();
            
            int displacementX = (int)xOnScreen - xOnGUI;
            
            int displacementY = (int)yOnScreen - yOnGUI;
            
            try{
                if(Math.random() < 1){
                    double posX = MouseInfo.getPointerInfo().getLocation().x/1;
                    double posY = MouseInfo.getPointerInfo().getLocation().y/1;
                    
                    double mouseX = this.getWidth()/2 + displacementX;
                    double mouseY = this.getHeight()/2 + displacementY;

                    int threshold = 10;
                    int mouseSens = rateOfTurn * 3;
                    
                    
                    yaw+= (posX - mouseX)/mouseSens;
                    pitch+= (posY - mouseY)/mouseSens;
                    
                    Robot robot = new Robot();
                    robot.mouseMove((int)mouseX,(int)mouseY);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public void mouseDragged(MouseEvent e){}
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }
    public static void main(String[] args){
        blank = new ImageIcon(Driver.class.getResource("blank.png"));
        cement_01 = new ImageIcon(Driver.class.getResource("cement_01.png"));
        
        yel_switch_locked  = new ImageIcon(Driver.class.getResource("yel_switch_locked.png"));
        yel_switch_opened  = new ImageIcon(Driver.class.getResource("yel_switch_opened.png"));
        yel_switch = new ImageIcon(Driver.class.getResource("yel_switch.png"));
        
        red_switch_locked  = new ImageIcon(Driver.class.getResource("red_switch_locked.png"));
        red_switch_opened  = new ImageIcon(Driver.class.getResource("red_switch_opened.png"));
        red_switch = new ImageIcon(Driver.class.getResource("red_switch.png"));
        
        blue_switch_locked  = new ImageIcon(Driver.class.getResource("blue_switch_locked.png"));
        blue_switch_opened  = new ImageIcon(Driver.class.getResource("blue_switch_opened.png"));
        blue_switch = new ImageIcon(Driver.class.getResource("blue_switch.png"));
        
        skyNorth  = new ImageIcon(Driver.class.getResource("skyNorth.png"));
        skySouth = new ImageIcon(Driver.class.getResource("skySouth.png"));
        
        switch_message = new ImageIcon(Driver.class.getResource("switch_message.png"));
        exit = new ImageIcon(Driver.class.getResource("exit.png"));
        
        congrats = new ImageIcon(Driver.class.getResource("congrats.png"));
        
        fireworks_01 = new ImageIcon(Driver.class.getResource("fireworks_01.gif"));
        fireworks_02  = new ImageIcon(Driver.class.getResource("fireworks_02.gif"));
        fireworks_03  = new ImageIcon(Driver.class.getResource("fireworks_03.gif"));
        fireworks_04  = new ImageIcon(Driver.class.getResource("fireworks_04.gif"));
        fireworks_05 = new ImageIcon(Driver.class.getResource("fireworks_05.gif"));
        fireworks_06 = new ImageIcon(Driver.class.getResource("fireworks_06.gif"));
        
        //Driver
        DisplayMode dm = new DisplayMode(800,600,16,DisplayMode.REFRESH_RATE_UNKNOWN);
        
        Driver g = new Driver();
        g.start(60);
    }
}