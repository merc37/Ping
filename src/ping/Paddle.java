package ping;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Paddle {

    private float initX;
    private float initY;
    private float xChange;

    private final int WIDTH = 100;
    private final int HEIGHT = 50;

    private boolean right;
    private boolean left;

    private Rectangle paddle;

    public Paddle(GameContainer gc) {
        initX = (gc.getWidth() / 2) - (WIDTH / 2);
        initY = gc.getHeight() - 10f;

        paddle = new Rectangle(getInitX(), getInitY(), WIDTH, HEIGHT);
    }

    public void movePaddle(GameContainer gc) {
        Input input = gc.getInput();

        right = false;
        left = false;

        if (input.isKeyDown(Input.KEY_RIGHT)) {
            xChange = 15f;
            right = true;
        }

        if (input.isKeyDown(Input.KEY_LEFT)) {
            xChange = -15f;
            left = true;
        }

        if ((isLeft() || isRight()) && (paddle.getX() + xChange > 0f) && (paddle.getMaxX() + xChange < gc.getWidth())) {
            paddle.setX(paddle.getX() + xChange);

        }
    }

    public void drawPaddle(Graphics g) {
        g.setColor(Color.white);
        g.fill(paddle);
    }
    
    public boolean ballHitsPaddle(Circle ball){
        if((paddle.contains(ball.getX(), ball.getMaxY()) || (paddle.contains(ball.getX(), ball.getY()))) ){
            return true;
        }
        return false;
    }

    public float getInitX() {
        return initX;
    }

    public float getInitY() {
        return initY;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }
}
