package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Engine.Component;
import Engine.GameObject;
import Engine.ResourceNotFound;

public class Planet extends Component {

	BufferedImage PI;
	
	public Planet(GameObject object, String Name) {
		super(object);
		try
        { 
			PI = (BufferedImage) (Main.grid.assets.getImage(Name, 0));
			parent.position.setSize(PI.getWidth(), PI.getHeight());
			 
        } catch (ResourceNotFound e) {
			// TODO Auto-generated catch block
			System.out.println(e);
        }
	}
	
	@Override
	public void graphics(Graphics2D G) {
		G.drawImage(PI, super.parent.position.GetAffine(), null);
	}
	

}
