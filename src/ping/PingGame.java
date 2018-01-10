package ping;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class PingGame extends BasicGameState {

    private final int ID = 1;

    private Paddle paddle;
    private Ball ball;

    private boolean isPaused = false;
    private boolean active = false;

    private int lives;
    private int score;

    Font javaFont;
    UnicodeFont adlerFont;
    
    Sound ballBounce;
    Sound laugh;

    public PingGame() {
        super();
    }

    public void init(GameContainer gc, StateBasedGame game) {
        paddle = new Paddle(gc);
        ball = new Ball(paddle.getInitX(), paddle.getInitY());
        lives = 3;
        score = 0;
        
        try {
            javaFont = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("res/adler.ttf"));
            javaFont = javaFont.deriveFont(Font.PLAIN, 16f);
            adlerFont = new UnicodeFont(javaFont);
            adlerFont.addAsciiGlyphs();
            org.newdawn.slick.font.effects.ColorEffect ce = new org.newdawn.slick.font.effects.ColorEffect();
            ce.setColor(Color.red);
            adlerFont.getEffects().add(ce);
            adlerFont.loadGlyphs();
            
            ballBounce = new Sound("res/ballbounce.wav");
            laugh = new Sound("res/demonLaugh.wav");
        } catch (SlickException | FontFormatException | IOException ex) {
            System.out.println(ex);
        }
        
        
    }

    public void update(GameContainer gc, StateBasedGame game, int delta) {
        Input input = gc.getInput();
        
        if (input.isKeyDown(Input.KEY_SPACE) && !active && lives != 0) {
            active = true;
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE) && lives != 0) {
            if(!isPaused){
                isPaused = true;
            }else if(isPaused){
                isPaused = false;
            }
        }

        if (active && !isPaused) {
            paddle.movePaddle(gc);
            ball.update(gc, ballBounce);
            if (paddle.ballHitsPaddle(ball.getBallLoc())) {
                ball.bounceOffPaddle(paddle.isLeft(), paddle.isRight(), ballBounce);
                score++;
            }
        }

        if (ball.ballOffScreen(gc, laugh) && active) {
            active = false;
            paddle = new Paddle(gc);
            ball = new Ball(paddle.getInitX(), paddle.getInitY());
            lives--;
        }

        if (lives == 0) {
            active = false;
            isPaused = true;
            if (input.isKeyPressed(Input.KEY_ENTER)) {
                lives = 3;
                score = 0;
                isPaused = false;
                game.enterState(0);
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame game, Graphics g) {
        g.setFont(adlerFont);

        paddle.drawPaddle(g);
        ball.render(g);

        g.drawString("Score: " + score, (gc.getWidth()-(100)), ID);
        
        for (int i = 1; i < lives + 1; i++) {
            g.fillOval(i * 25, 20, 20, 20);
        }

        if (isPaused && lives != 0) {
            g.drawString("Paused", (gc.getWidth() / 2) - 30, 200);
        }

        if (lives == 0) {
            g.drawString("Game Over", (gc.getWidth() / 2) - 45, 200);
            g.drawString("Score: " + score, (gc.getWidth() / 2) - 35, 250);
            g.drawString("Press Enter to Continue", (gc.getWidth() / 2) - 110, 300);
        }
    }

    public int getID() {
        return ID;
    }
}
