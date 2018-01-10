package ping;

import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Ping extends StateBasedGame{
    
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
    private static boolean fullscreen = false;
    
    public Ping(){
        super("Ping");
    }

    public void initStatesList(GameContainer gc){
        addState(new PingMenu());
        addState(new PingGame());
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Ping());
            app.setDisplayMode(WIDTH, HEIGHT, fullscreen);
            app.setTargetFrameRate(60);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException ex) {
            System.out.println(ex);
        }
    }
    
}
