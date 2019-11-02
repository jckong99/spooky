package Game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import Engine.*;

public class PlayerController extends Component {
    
	float catSpeed = 10;
	
    public PlayerController(GameObject gameObject, String name) {
        super(gameObject);
        parent.position.Scale(8, 8);
        parent.position.Translate(900, 900);
    }

    @Override
    public void graphics(Graphics2D g) {
    	Image sprite = null;
    	
		try {
			sprite = FlagMain.assets.getImage("idle_1.png", 0);
		}
		catch (ResourceNotFound e) {
			e.printStackTrace();
		}
    	
        g.drawImage(sprite, parent.position.GetAffine(), null);
    }

    @Override
    public void logic() {
        
    	if(FlagMain.inputs.pending.contains(KeyEvent.VK_D))
    		parent.position.Translate(catSpeed, 0);
    	if(FlagMain.inputs.pending.contains(KeyEvent.VK_A))
    		parent.position.Translate(-catSpeed , 0);
        //System.out.println(parent.position.getX() + "   " + parent.position.getY());
        
        
    }
    
}
