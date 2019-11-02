package Engine;

import java.util.ArrayList;

public class CollisionComponent extends Component {
	
	public static ArrayList<CollisionComponent> obstacles = new ArrayList<CollisionComponent>();
	public ArrayList<CollisionComponent> collisions = new ArrayList<CollisionComponent>();
	
	
	public double Width;
	public double Height;
	
	
	boolean ColTop,
			ColBot,
			ColRight,
			ColLeft;
	
	public CollisionComponent(GameObject go,double width, double height) {
		super(go);
		this.Width = width;
		this.Height = height;
		ColTop = false;
		ColBot = false;
		ColRight = false;
		ColLeft = false;
		obstacles.add(this);
	}
	
	@Override
	public void logic() {
		this.collisions.clear();
		Position myPosition = parent.position;
		
		double myX = myPosition.getX();
		double myY = myPosition.getX();
		
		
		boolean sameH;
		boolean sameW;
		
		CollisionComponent other;
		
		for(int i = 0 ;  i < obstacles.size(); i++) {
			
			other = obstacles.get(i);
			
			if(other.equals(this))
				continue;
			
			Position othePosition = other.parent.position;
			double otherX = othePosition.getX();
			double otherY = othePosition.getY();
			
			sameH = false;
			sameW = false;
			
			if((myX - this.Width/2 +1 > otherX - other.Width/2 && myX - this.Width/2 +1 < otherX + other.Width/2  )||
			   (myX + this.Width/2 +1 > otherX - other.Width/2 && myX + this.Width/2 +1 < otherX + other.Width/2  )||
			   (myX - this.Width/2 +1 < otherX - other.Width/2 && myX + this.Width/2 +1 > otherX - other.Width/2  )){
				sameW = true;
			}
			
			if((myY - this.Height/2 +1 > otherY - other.Height/2 && myY - this.Height/2 +1 < otherY + other.Height/2  )||
			   (myY + this.Height/2 +1 > otherY - other.Height/2 && myY + this.Height/2 +1 < otherY + other.Height/2)||
			   (myY - this.Height/2 +1 < otherY - other.Height/2 && myY + this.Height/2 +1 > otherY - other.Height/2  )){
				sameH = true;
			}
			
			if(sameH && sameW) {
				/*if(((GraphicComponent)this.parent.GetComponent(GraphicComponent.class)).name.equals("Mario.png")) {
					System.out.println(" myX: " + myX + " +- " + Width/2);
					System.out.println(" myY: " + myY + " +- " + Height/2);
					System.out.println(" otherX: " + otherX + " +- " + other.Width/2);
					System.out.println(" otherY: " + otherY + " +- " + other.Height/2);
					System.out.println("----------------------------------------------------------");
				}*/
				
				this.collisions.add(other);
			}
		}
	}
	
	public Position GetPostition() {
		return parent.position;
	}
	
	
}
