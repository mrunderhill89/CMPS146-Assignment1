package pacman.entries.ghosts;

import pacman.game.Constants.GHOST;


public class PatrolRegion {
	public int region;
	public Coords[] points;
	public GHOST[] assignment;
	
	public PatrolRegion(int _region, Coords[] _points) {
		
		region = _region;
		points = new Coords[_points.length];
		for (int i = 0; i < _points.length; i++) {
			points[i] = new Coords(_points[i]);
		}
	}
	
	public PatrolRegion(PatrolRegion _region) {
		region = _region.region;
		points = new Coords[_region.points.length];
		assignment = new GHOST[points.length];
		
		for (int i = 0; i < points.length; i++) {
			if (_region.points != null)
				points[i] = new Coords(_region.points[i]);
			if (_region.assignment != null)
				assignment[i] = _region.assignment[i];
		}
	}
	
	public void assign(GHOST ghost, int point) {
		if (assignment != null && point > 0 && point < assignment.length) {
			assignment[point] = ghost;
		}
		else if (points != null && point > 0 && point < points.length){
			assignment = new GHOST[points.length];
			assignment[point] = ghost;
		}
	}
	
	public void assign(GHOST ghost) {
		if (assignment != null) {
			for (int i = 0; i < assignment.length; i++) {
				if ( assignment[i] == null) {
					assignment[i] = ghost;
					break;
				}
			}
		}
		else if (points != null){
			assignment = new GHOST[points.length];
			assignment[0] = ghost;
		}
	}
	
	public void clearAssignments() {
		assignment = new GHOST[points.length];
	}
	
	public Coords getPatrolPoint(GHOST ghost) {
		for (int i = 0; i < assignment.length; i++) {
			if (assignment[i] == ghost)
				return points[i];
		}
		
		return null;
	}
}
