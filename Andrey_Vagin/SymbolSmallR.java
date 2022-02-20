package simulator.Andrey_Vagin;

import simulator.do_not_change.Passive;
import simulator.do_not_change.Position;
import simulator.do_not_change.SmallCase;
import simulator.do_not_change.Symbol;

public class SymbolSmallR extends Symbol
									implements SmallCase, Passive{

	SymbolSmallR(){
		this.sightDistance = 4;
		moved = true;
		breeded = true;
		escaped = true;
	}
	
	boolean escaped;
	
	@Override
	public void escape() {
		escaped = true;
		
		for(int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				if(i != this.getPosition().row || j != this.getPosition().column) {
					
				Position checkPosition = new Position(i, j);
				
				// find save position within sight distance
				
				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance && Simulator.world.get(checkPosition).isEmpty()) {
					this.setPosition(checkPosition);
						i = 11;
						j = 11;
					}else if (this.position.manhattanDistance(checkPosition) <= this.sightDistance && !Simulator.world.get(checkPosition).isEmpty()) {
						if(Simulator.world.get(checkPosition).getFirst() instanceof SymbolSmallR || Simulator.world.get(checkPosition).getFirst() instanceof SymbolCapitalR) {
							
							
							// born new rock if this rock meets another
							
							for(Symbol x: Simulator.world.get(checkPosition)) {
								if(x instanceof SymbolSmallR) {
									this.setPosition(checkPosition);
									Simulator.world.get(this.getPosition()).add(new SymbolSmallR());
									Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
									break;
										}
									}
							
							i = 11;
							j = 11;
						}
					}
				}
			}
		}			
	}


public boolean breeded = false;
	
	@Override
	public void moveBreed() {
		breeded = true;
		
		for(int i = 1; i <= 10; ++i) {  // find symbol of the same class within sight distance
			for (int j = 1; j <= 10; ++j) {
				if(i != this.getPosition().row || j != this.getPosition().column) {
					
				Position checkPosition = new Position(i, j);
				
				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance && !Simulator.world.get(checkPosition).isEmpty()) {
					for(Symbol x: Simulator.world.get(checkPosition)) {
					if(x instanceof SymbolSmallR) {
						this.setPosition(checkPosition);
						i = 11;
						j = 11;
						
						// born new small rock if this rock meets another
						Simulator.world.get(this.getPosition()).add(new SymbolSmallR());
						Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
						((SymbolSmallR) Simulator.world.get(this.getPosition()).getLast()).breeded = true;
						break;
							
						
							}
						}
					}
				}
			}
		}		
	}

	@Override
	public void upgrade() {
		Simulator.world.get(this.getPosition()).add(new SymbolCapitalR());
		Simulator.world.get(this.getPosition()).getLast().setPosition(this.position);
	}

boolean moved = false;
	
	@Override
	public void move() {
		
moved = true;
		
		
		// generate random move in sightDistance
		int bound = (this.sightDistance)*2 + 1;
		int dRow = (int) (Math.random() * bound) - this.sightDistance;
		int dColumn = (int) (Math.random() * (bound - 2*Math.abs(dRow))) - (this.sightDistance - Math.abs(dRow));
		
		
		
		if(dRow + this.getPosition().row > 10 || dRow + this.getPosition().row < 1) { //prevent a case when symbol goes out of the world
			dRow = -dRow;
		}
		
		if(dColumn + this.getPosition().column > 10 || dColumn + this.getPosition().column < 1) {
			dColumn = -dColumn;
		}
		
		
		Position y = new Position(dRow + this.getPosition().row, dColumn + this.getPosition().column);		
		this.setPosition(y);
		
		
		
		// kill Scissors
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalS || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallS) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
		
		if(dRow != 0 || dColumn != 0) {

		// born new Rock
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolSmallR) {
				Simulator.world.get(this.getPosition()).add(new SymbolSmallR());
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
