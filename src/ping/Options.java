package ping;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;

public class Options {
    
    MenuBtn back;
    MenuBtn musicBtn;
    MenuBtn chngSong;
    
    boolean highlighted;
    
    String onOff = "On";
    
    public Options(){
        back = new MenuBtn("Back");
        musicBtn = new MenuBtn("Music\n" + onOff);
        chngSong = new MenuBtn("Change\n" + "Song");
    }
    
    public void update(GameContainer gc, int menuState, ArrayList<Music> music, Music currMusic){
        highlighted = false;
        
        if(back.isClicked(gc, highlighted)){
            menuState = 0;
        }
        
        if(musicBtn.isClicked(gc, highlighted)){
            if(currMusic.playing()){
                currMusic.pause();
                onOff = "Off";
            }
            if(!currMusic.playing()){
                currMusic.resume();
                onOff = "On";
            }
        }
        
        if(chngSong.isClicked(gc, highlighted)){
            currMusic.stop();
            currMusic = music.get((int)(Math.random())*music.size());
            currMusic.play();;
        }
    }
    
    public void render(GameContainer gc, Graphics g){
        back.drawImgBtn(g, 10, 10, 175, 75);
        musicBtn.drawImgBtn(g, 100, 100, 175, 75);
        chngSong.drawImgBtn(g, 100, 200, 175, 75);
    }
}
