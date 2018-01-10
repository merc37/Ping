package ping;

import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MenuBtn {
    private Image image;
    
    private boolean highlighted;
    
    private final String text;
    
    private float x, y, width, height;
    
    public MenuBtn(String text){
        this.text = text;
        try{
        image = new Image("res/btnSkull.png");
        } catch(SlickException ex){}
    }
    
    public void drawImgBtn(Graphics g, float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        image.draw(x, y, width, height, Color.gray);
        
        g.drawString(text, (x+(width/2))-(text.length()*5), y+6);
        
        if(highlighted){
            image.draw(x, y, width, height, Color.red);
            g.drawString(text, (x+(width/2))-(text.length()*5), y+6);
        }
    }
    
    public boolean isClicked(GameContainer gc, boolean highlighted){
        Input input = gc.getInput();
        
        this.highlighted = highlighted;
        
        if((input.getMouseX() > x && input.getMouseX() < x+width) && (input.getMouseY() > y && input.getMouseY() < y+height)){
            this.highlighted = true;
            if(input.isMouseButtonDown(0)){
                return true;
            }
        }
        return false;
    }
}
