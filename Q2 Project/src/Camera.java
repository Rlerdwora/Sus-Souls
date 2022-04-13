
public class Camera {
	
	private Character character;
	private static int x, y;
	
	public Camera(Character focus) {
		character = focus;
	}
	
	public void focus() {
		while(character.x() != 395) {
			if(character.x() < 395) {
				x ++;
				character.setX(character.x() + 1);
			}else if(character.x() > 395){
				x --;
				character.setX(character.x() - 1);
			}
		}
		
		while(character.y() != 270) {
			if(character.y() < 270) {
				y ++;
				character.setY(character.y() + 1);
			}else if(character.y() > 270){
				y --;
				character.setY(character.y() - 1);
			}
		}
	}
	
	public static int x() {return x;}

	public static int y() {return y;}
}
