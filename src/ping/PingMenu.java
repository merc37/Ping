package ping;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class PingMenu extends BasicGameState {

    private final int ID = 0;

    MenuBtn play;
    MenuBtn optionsBtn;

    Image pingKing;
    Image btnSkull;

    Font javaFont;

    UnicodeFont adlerFontMenu;
    UnicodeFont adlerFontTitle;

    boolean highlighted;

    int menuState;
    
    ArrayList<Music> music = new ArrayList(7);
    
    Music currMusic;
    
    Options options;

    public PingMenu() {
        super();
    }

    public void init(GameContainer gc, StateBasedGame game) {

        try {
            javaFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("res/adler.ttf"));
            javaFont = javaFont.deriveFont(Font.PLAIN, 16f);
            adlerFontMenu = new UnicodeFont(javaFont);
            adlerFontMenu.addAsciiGlyphs();
            org.newdawn.slick.font.effects.GradientEffect ge1 = new org.newdawn.slick.font.effects.GradientEffect(Color.BLACK, Color.RED, 1f);
            adlerFontMenu.getEffects().add(ge1);
            adlerFontMenu.loadGlyphs();

            javaFont = javaFont.deriveFont(Font.BOLD, 50f);
            adlerFontTitle = new UnicodeFont(javaFont);
            adlerFontTitle.addAsciiGlyphs();
            org.newdawn.slick.font.effects.GradientEffect ge2 = new org.newdawn.slick.font.effects.GradientEffect(Color.BLACK, Color.RED, 1f);
            adlerFontTitle.getEffects().add(ge2);
            adlerFontTitle.loadGlyphs();

            pingKing = new Image("res/pingking.jpg");
            btnSkull = new Image("res/btnSkull.png");
        } catch (SlickException | FontFormatException | IOException ex) {
            System.out.println(ex);
        }
        
        try{
            music.add(new Music("res/htk.wav"));
            music.add(new Music("res/aftlif.wav"));
            music.add(new Music("res/shepfire.wav"));
            music.add(new Music("res/unhcon.wav"));
            music.add(new Music("res/remen.wav"));
            music.add(new Music("res/ngtmre.wav"));
            music.add(new Music("res/almeas.wav"));
        } catch(SlickException ex){System.out.println(ex);}
        currMusic = music.get((int)((Math.random())*music.size()));
        //currMusic.play();

        menuState = 0;

        options = new Options();
        
        play = new MenuBtn("Play");
        optionsBtn = new MenuBtn("Options");
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) {
        if (menuState == 0) {
            highlighted = false;
            if (play.isClicked(gc, highlighted)) {
                game.enterState(1);
            }
            if(optionsBtn.isClicked(gc, highlighted)){
                menuState = 1;
            }
        }
        
        if(menuState == 1){
            options.update(gc, menuState, music, currMusic);
        }
    }

    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        pingKing.draw(0, 0, gc.getWidth(), gc.getHeight());
        if (menuState == 0) {
            g.setFont(adlerFontMenu);
            play.drawImgBtn(g, gc.getWidth() - 175, 175, 175, 75);
            optionsBtn.drawImgBtn(g, gc.getWidth() - 175, 275, 175, 75);

            g.setFont(adlerFontTitle);
            g.drawString("Hail To The Ping", (gc.getWidth() / 2) - 275, 50);
        }
        
        if(menuState == 1){
            options.render(gc, g);
        }
    }

    public int getID() {
        return ID;
    }
}
