package Game;

import java.awt.Graphics2D;
import java.awt.Image;

import Engine.*;

public class PlayerController extends Component {
    
    public PlayerController(GameObject gameObject, String name) {
        super(gameObject);
        parent.position.SetPosition(100, 100);
        //parent.position.Scale(2, 2);
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
        parent.position.Translate(.01, 0);
    }
    
}
