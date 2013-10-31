package pacman.entries.ghosts;

public class Coords {
	public int x;
	public int y;
	
	public Coords(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	public Coords(int z) {
		x = z;
		y = z;
	}
	
	public Coords(Coords _source) {
		x = _source.x;
		y = _source.y;
	}
}
