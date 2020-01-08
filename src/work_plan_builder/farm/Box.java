package work_plan_builder.farm;

import com.github.miachm.sods.Color;

import work_plan_builder.plan_parts.Turn;

public class Box {
	private boolean is_empty = true;
	Turn turn;
	public Color c;
	public Box(Turn turn) {
		this.turn = turn;
		turn.set_box(this);
		c = turn.c;
		is_empty = false;
	}
	public Box() {
		
	}
	
	public boolean is_empty() {
		return is_empty;
	}
	
	public Turn get_turn() {
		return turn;
	}
}
