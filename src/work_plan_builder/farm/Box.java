package work_plan_builder.farm;


import java.awt.Color;

import work_plan_builder.plan_parts.Turn;

public class Box {
	private boolean is_empty = true;
	Turn turn;
	public Color c;

	public Box(Turn turn) {
		this.turn = turn;
		turn.set_box(this);
		is_empty = false;
		this.c = turn.c;
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
