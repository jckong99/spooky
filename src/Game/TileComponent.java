package Game;

import java.awt.Graphics2D;
import java.awt.Image;

import Engine.*;

public class TileComponent extends Component {

	public Image testImage;
	
	public TileComponent(GameObject object, float x, float y) {
		super(object);
		
		 parent.position.Scale(2, 2);
	     parent.position.Translate(200, 900);
	     parent.position.Translate(x, y);
		
		
		
		
		try {
			testImage = FlagMain.assets.getImage("centertile.png", 0);
		} catch (ResourceNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//parent.position.Translate(800, 800);
		
		
	}
	
	@Override
	public void graphics(Graphics2D g) {
		g.drawImage(testImage, parent.position.GetAffine(), null);
	}
	

}
