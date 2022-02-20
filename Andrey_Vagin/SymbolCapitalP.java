package simulator.Andrey_Vagin;

import simulator.do_not_change.CapitalCase;
import simulator.do_not_change.Passive;
import simulator.do_not_change.Position;
import simulator.do_not_change.Symbol;

public class SymbolCapitalP extends Symbol 
										implements CapitalCase, Passive{

	SymbolCapitalP(){
		this.idSymbol = COUNT_SYMBOLS;
		COUNT_SYMBOLS++;
		this.sightDistance = 3;
		breeded = true;
		jumped = true;
		moved = true;
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
				
				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance && Simulator.world.get(checkPosition).isEmpty()) {
					this.setPosition(checkPosition);
						i = 11;
						j = 11;
					}else if (this.position.manhattanDistance(checkPosition) <= this.sightDistance && !Simulator.world.get(checkPosition).isEmpty()) {
						if(Simulator.world.get(checkPosition).getFirst() instanceof SymbolCapitalP || Simulator.world.get(checkPosition).getFirst() instanceof SymbolSmallP) {
							
							for(Symbol x: Simulator.world.get(checkPosition)) {
								if(x instanceof SymbolCapitalP) {
									this.setPosition(checkPosition);
									Simulator.world.get(this.getPosition()).add(new SymbolSmallP());
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

	boolean breeded = false;
	
	@Override
	public void moveBreed() {
		breeded = true;
		
		for(int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				if(i != this.getPosition().row || j != this.getPosition().column) {
					
				Position checkPosition = new Position(i, j);
				
				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance && !Simulator.world.get(checkPosition).isEmpty()) {
					for(Symbol x: Simulator.world.get(checkPosition)) {
					if(x instanceof SymbolCapitalP) {
						this.setPosition(checkPosition);
						i = 11;
						j = 11;
						Simulator.world.get(this.getPosition()).add(new SymbolSmallP());
						Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
						break;
							
						
							}
						}
					}
				}
			}
		}		
	}

	
	
boolean jumped;
	
	@Override
	public void jump() {
		
		jumped = true;
		
		// generate random jump
		int dRow = (int) (Math.random() * 10) + 1;
		int dColumn = (int) (Math.random() * 10) + 1;
		Position lastPosition = this.getPosition();
		Position y = new Position(dRow, dColumn);				
		this.setPosition(y);
				
				
			// kill Rock
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalR || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallR) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
				
		if(lastPosition.column != 0 || lastPosition.row != 0) {
		
		// born new Paper
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolCapitalS) {
				Simulator.world.get(this.getPosition()).add(new SymbolSmallP());
				Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
				break;
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
		
		
		
		if(dRow + this.getPosition().row > 10 || dRow + this.getPosition().row < 1) {
			dRow = -dRow;
		}
		
		if(dColumn + this.getPosition().column > 10 || dColumn + this.getPosition().column < 1) {
			dColumn = -dColumn;
		}
		
		
		Position y = new Position(dRow + this.getPosition().row, dColumn + this.getPosition().column);		
		this.setPosition(y);
		
		
		
		// kill Rock
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalR || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallR) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
		
		
		if(dRow != 0 || dColumn != 0) {
		// born new Paper
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolCapitalP) {
				Simulator.world.get(this.getPosition()).add(new SymbolSmallP());
				Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
				break;
			}
		}
		}
		
		
	}

	@Override
	public void die() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAA");
		
	}

}
