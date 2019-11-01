package Game;

import Engine.Component;
import Engine.GameObject;

public class PlanetLogic extends Component{

	public PlanetLogic(GameObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void logic() {
		super.parent.position.rotate(.01);
		//super.parent.position.Translate(1, -2);
		//super.parent.position.Scale(1.001, 1.001);
		
	}
	
}
