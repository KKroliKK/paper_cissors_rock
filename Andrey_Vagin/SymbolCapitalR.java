package simulator.Andrey_Vagin;

import java.util.LinkedList;

import simulator.do_not_change.Aggressive;
import simulator.do_not_change.CapitalCase;
import simulator.do_not_change.Position;
import simulator.do_not_change.Symbol;

public class SymbolCapitalR extends Symbol
									implements Aggressive, CapitalCase{

	SymbolCapitalR(){
		this.sightDistance = 3;
		jumped = true;
		attacked = true;
		moved = true;
	}
	
	boolean attacked = false;
	
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
		
		
		
		// kill Scissors
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalS || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallS) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
		
		
		if(dRow != 0 || dColumn != 0) {

		// born new Rock
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolCapitalR) {
				Simulator.world.get(this.getPosition()).add(new SymbolSmallR());
				Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
				break;
			}
		}
		
		}
		
	}


	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
//	public void attackSmart() {
//		attacked = true;
//		
//		for(int i = 1; i <= 10; ++i) {
//			for (int j = 1; j <= 10; ++j) {
//				
//				Position checkPosition = new Position(i, j);
//				
//				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance) {
//					
//					int t = 0;
//					LinkedList<Integer> killed = new LinkedList<Integer>();
//					for(Symbol x: Simulator.world.get(checkPosition)) {
//						if(x instanceof SymbolCapitalS || x instanceof SymbolSmallS) {
//							killed.add(t);
//						}
//						t++;
//					}
//					
//						for(int q = 0; q < killed.size(); ++q) {
//							Simulator.world.get(checkPosition).remove(killed.get(q) - q);
//						}
//
//						this.setPosition(checkPosition);
//
//						
//						i = 11;
//						j = 11;
//					
//					
//				}
//				
//			}
//		}
//		
//				
//	}
	
	public void attackSmart() {
		attacked = true;
		
		for(int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				
				Position checkPosition = new Position(i, j);
				
				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance) {
					
					if(!Simulator.world.get(checkPosition).isEmpty()) {
						if(Simulator.world.get(checkPosition).getFirst() instanceof SymbolCapitalS || Simulator.world.get(checkPosition).getFirst() instanceof SymbolSmallS) {
							
							Simulator.world.get(checkPosition).clear();
							
							this.setPosition(checkPosition);
							i = 11;
							j = 11;
						}
					}

						
					
					
				}
				
			}
		}
		
				
	}

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
				
				
			// kill Scissors
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalS || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallS) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
				
		if(lastPosition.column != 0 || lastPosition.row != 0) {
		
		// born new Rock
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolCapitalS) {
				Simulator.world.get(this.getPosition()).add(new SymbolSmallR());
				Simulator.world.get(this.getPosition()).getLast().setPosition(this.getPosition());
				break;
			}
		}
		}
	}

}
