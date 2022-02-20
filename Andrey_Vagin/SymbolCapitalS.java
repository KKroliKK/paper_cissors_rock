package simulator.Andrey_Vagin;

import java.util.LinkedList;

import simulator.do_not_change.Aggressive;
import simulator.do_not_change.CapitalCase;
import simulator.do_not_change.Position;
import simulator.do_not_change.Symbol;

public class SymbolCapitalS extends Symbol
									implements Aggressive, CapitalCase{
	
	SymbolCapitalS(){
		this.sightDistance = 3;
		this.numberIterationsAlive = 0;
		moved = true;
		attacked = true;
		jumped = true;
	}
		
	boolean attacked = false;
	boolean jumped = false;
	
	@Override
	public void jump() {
		
		jumped = true;
		
		// generate random jump
		int dRow = (int) (Math.random() * 10) + 1;
		int dColumn = (int) (Math.random() * 10) + 1;
		Position lastPosition = this.getPosition();
		Position y = new Position(dRow, dColumn);				
		this.setPosition(y);
				
				
			// kill Paper
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalP || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallP) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
				
		if(lastPosition.column != 0 || lastPosition.row != 0) {
		
		// born new Scissors
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolCapitalS) {
				Simulator.world.get(this.getPosition()).add(new SymbolSmallS());
				Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
				break;
			}
		}
		}
	}

	
	
	@Override
	public void attackSmart() {
		
		attacked = true;
		
		for(int i = 1; i <= 10; ++i) {  // find a symbol to kill wihin sight distance
			for (int j = 1; j <= 10; ++j) {
				
				Position checkPosition = new Position(i, j);
				
				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance && !Simulator.world.get(checkPosition).isEmpty()) {
					if(Simulator.world.get(checkPosition).getFirst() instanceof SymbolCapitalP || Simulator.world.get(checkPosition).getFirst() instanceof SymbolSmallP) {
						Simulator.world.get(checkPosition).clear();
						this.setPosition(checkPosition);
						i = 11;
						j = 11;
					}	
				}
			}
		}
	}

	
	
	
	boolean moved = false;
	
	@Override
	public void move() {
		
		moved = true;
		
		
		// generate random move in sightDistance
		int bound = (this.sightDistance)*2 + 1;
		int dRow = (int) (Math.random() * bound) - this.sightDistance;
		int dColumn = (int) (Math.random() * (bound - 2*Math.abs(dRow))) - (this.sightDistance - Math.abs(dRow));
		
		
		
		if(dRow + this.getPosition().row > 10 || dRow + this.getPosition().row < 1) {  // prevent case of going out of the world
			dRow = -dRow;
		}
		
		if(dColumn + this.getPosition().column > 10 || dColumn + this.getPosition().column < 1) {
			dColumn = -dColumn;
		}
		
		
		Position y = new Position(dRow + this.getPosition().row, dColumn + this.getPosition().column);		
		this.setPosition(y);
		
		
		
		// kill Paper
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalP || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallP) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
		
		if(dRow != 0 || dColumn != 0) {
		// born new Scissor
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolCapitalS) {
				Simulator.world.get(this.getPosition()).add(new SymbolSmallS());
				Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
				break;
			}
		}
		}
	}

	
	
	@Override
	public void die() {
	}

}
