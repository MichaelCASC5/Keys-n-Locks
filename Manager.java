//Michael Calle
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Manager{
    public static void create(){
        File myObj = new File("file.txt");
        if(true){
            try{
                FileWriter f = new FileWriter("README.txt");
                f.write("/*\n" +
"	* Michael Calle\n" +
"	* Forest Hills High School\n" +
"	* AP Computer Science\n" +
"	* JUNE 2020\n" +
"*/\n" +
"\n" +
"Keys 'n Locks - A simple puzzle-parkour game made to example 3D within the Java\n" +
"2D Graphics class.\n" +
"\n" +
"*** HOW TO PLAY ***\n" +
"	\n" +
"	Progress through each level to navigate towards the exit. As you go, \n" +
"	you'll need to find the keys to the various locks that obstruct your path.\n" +
"	\n" +
"	Use the WASD keys to move. Use either the arrow keys or the mouse to look\n" +
"	around. Press SPACE to jump into and through the air.\n" +
"	\n" +
"*** INFORMATION ***\n" +
"	\n" +
"	It took more time to build the 3D engine and backbone of this game, than it \n" +
"	did to build the game itself. Therefore, I apologize for the crude and largely\n" +
"	unfinished state that it is in.\n" +
"	\n" +
"	Within the game world, the user can press keyboard [M] to toggle mouselook.\n" +
"	However, this can cause problems on computers with high DPI screens, or\n" +
"	strange system scaling. As a failsafe, the user can always press ESC to leave.\n" +
"	\n" +
"	The user can also press keyboard [O] to display the coordinates of each tile\n" +
"	in the world.\n" +
"	\n" +
"	When entering turn speed, a greater value will increase arrow key speed,\n" +
"	but will lower mouse speed.\n" +
"	\n" +
"	The user can use keyboard [U] and [I] to adjust FOV as desired.\n" +
"	\n" +
"*** CHEATS AND GLITCHES ***\n" +
"	\n" +
"	Moving in a diagonal line, such as combining the [W] and [A] keys, will\n" +
"	increase your speed. In hard mode, this is required for virtually all jumps.\n" +
"	\n" +
"	Constantly rotating and moving into a wall, will result in the character\n" +
"	scaling the wall. This can be used to climb walls, although there aren't\n" +
"	that many in the game.\n" +
"	\n" +
"	Creating a file called \"jetpack.lol\" in the same location that the game\n" +
"	is being run will enable the jetpack.\n" +
"	\n" +
"	There are messy hit boxes in this version, so looking around may ever so\n" +
"	slightly change your position. Looking down may also result in clipping\n" +
"	into the floor.");
                f.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static boolean cheats(){
        File obj = new File("jetpack.lol");
        if(obj.exists()){
            try{
                FileWriter f = new FileWriter("jetpack.lol");
                f.write("You cheater...");
                f.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return true;
        }else{
            return false;
        }
    }
}