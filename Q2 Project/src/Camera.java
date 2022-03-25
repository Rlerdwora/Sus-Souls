
public class Camera {
	
	private Amogus amogus;
	private static int x, y;
	
	public Camera(Amogus focus) {
		this.amogus = focus;
	}
	
	public void focus() {
		while(amogus.x() != 395) {
			if(amogus.x() < 395) {
				x ++;
				amogus.setX(amogus.x() + 1);
			}else if(amogus.x() > 395){
				x --;
				amogus.setX(amogus.x() - 1);
			}
		}
		
		while(amogus.y() != 270) {
			if(amogus.y() < 270) {
				y ++;
				amogus.setY(amogus.y() + 1);
			}else if(amogus.y() > 270){
				y --;
				amogus.setY(amogus.y() - 1);
			}
		}
	}
	
	public static int x() {return x;}

	public static int y() {return y;}
}
