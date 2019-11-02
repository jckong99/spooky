package Engine;

import java.text.Collator;
import java.util.ArrayList;

public class Collider extends Component {


	public static ArrayList<Collider> collidables = new ArrayList<Collider>();
	public static ArrayList<Collision> collisions = new ArrayList<Collision>();
	
	public class Collision{
		Collider col1, col2;
		
		public Collision(Collider c1, Collider c2) {col1 = c1; col2=c2;}
		
		@Override
		public boolean equals(Object o) {
			if(o.getClass() != Collision.class)
				return false;
			Collision co = (Collision) o;
			
			return col1.equals(co.col1) && col2.equals(co.col2 ) || col1.equals(co.col2) && col2.equals(co.col1 );
				
		}
	}
	
	
	public Collider(GameObject object) {
		super(object);
		collidables.add(this);
		this.Priority = -1;
	}
	
	@Override
	public void logic() {
		Collision temp;
		
		for(Collider c : collidables) {
			if(Collides(c)) {
				temp = new Collision(this, c);
				if(!collisions.contains(temp))
					collisions.add(temp);
			}
			
		}
		
		
		
	}

	boolean Collides(Collider c) {
		return true;
		
	}
	
	
}
