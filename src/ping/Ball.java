package ping;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Ball {
    
    private float ballX;
    private float ballY;
    
    private float ballSpd = 7;
    
    private int paddleHits;
    
    private Circle ball;
    
    private Vector2f ballVeloc;
    
    public Ball(float paddleInitX, float paddleInitY){
        ballX = paddleInitX;
        ballY = paddleInitY;
        
        ball = new Circle(ballX+50f, ballY-20f, 10, 10);
        
        paddleHits = 0;
        ballSpd = 7;
        
        ballVeloc = new Vector2f(0, -ballSpd);
        ballVeloc.setTheta(185+(Math.random()*((355-185)+1)));
    }
    
    public void update(GameContainer gc, Sound ballBounce){
        ball.setLocation(ball.getX()+ballVeloc.getX(), ball.getY()+ballVeloc.getY());
        
        if(ball.getX() < 0){
            ballBounce.play(1, 30);
            ballVeloc = new Vector2f(ballSpd, 0);
            if(Math.random()>0.5){
                ballVeloc.setTheta(285+(Math.random()*((345-285)+1)));
            } else if(Math.random()<0.5){
                ballVeloc.setTheta(15+(Math.random()*((75-15)+1)));
            }
        } else if(ball.getMaxX() > gc.getWidth()){
            ballBounce.play(1, 30);
            ballVeloc = new Vector2f(-ballSpd, 0);
            ballVeloc.setTheta(105+(Math.random()*((255-105)+1)));
        } else if(ball.getY() < 0){
            ballBounce.play(1, 30);
            ballVeloc = new Vector2f(0, ballSpd);
            ballVeloc.setTheta(15+(Math.random()*((165-15)+1)));
        }
        
        if(paddleHits == 5){
            paddleHits = 0;
            ballSpd += 2;
        }
    }
    
    public boolean ballOffScreen(GameContainer gc, Sound laugh){
        if(ball.getMaxY() > gc.getHeight()){
            laugh.play(1, 15);
            paddleHits = 0;
            return true;
        }
        return false;
    }
    
    public void bounceOffPaddle(boolean left, boolean right, Sound ballBounce){
        ballBounce.play(1, 30);
        ballVeloc = new Vector2f(0, -ballSpd);
        if(left){
            ballVeloc.setTheta(185+(Math.random()*((255-205)+1)));
        } else if(right){
            ballVeloc.setTheta(275+(Math.random()*((345-285)+1)));
        }
        paddleHits++;
    }
    
    public void render(Graphics g){
        g.setColor(Color.white);
        g.fill(ball);
    }
    
    public Circle getBallLoc() {
        return ball;
    }
}
