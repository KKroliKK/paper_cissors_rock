package simulator.Andrey_Vagin;

import java.util.LinkedList;

import simulator.do_not_change.Aggressive;
import simulator.do_not_change.Position;
import simulator.do_not_change.SmallCase;
import simulator.do_not_change.Symbol;

public class SymbolSmallP extends Symbol
									implements Aggressive, SmallCase{

	SymbolSmallP(){
		this.sightDistance = 3;
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
		
		
		
		// kill Rock
		if(!Simulator.world.get(this.getPosition()).isEmpty()) {
			if(Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolCapitalR || Simulator.world.get(this.getPosition()).getFirst() instanceof SymbolSmallR) {
				Simulator.world.get(this.getPosition()).clear();
			}
		}
		
		if(dRow != 0 || dColumn != 0) {

		// born new Paper
		for (Symbol x: Simulator.world.get(this.getPosition())) {
			if (x instanceof SymbolSmallP) {
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

	@Override
	public void upgrade() {
		Simulator.world.get(this.getPosition()).add(new SymbolCapitalP());
		Simulator.world.get(this.getPosition()).getLast().setPosition(this.position);
	}

	@Override
	public void attackSmart() {
attacked = true;
		
		for(int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				
				Position checkPosition = new Position(i, j);
				
				if (this.position.manhattanDistance(checkPosition) <= this.sightDistance) {
					
					int t = 0;
					LinkedList<Integer> killed = new LinkedList<Integer>();
					for(Symbol x: Simulator.world.get(checkPosition)) {
						if(x instanceof SymbolCapitalR || x instanceof SymbolSmallR) {
							killed.add(t);
						}
						t++;
					}
					
					if(!killed.isEmpty()) {
						for(int q = 0; q < killed.size(); ++q) {
							Simulator.world.get(checkPosition).remove(killed.get(q) - q);
						}

						this.setPosition(checkPosition);

						
						i = 11;
						j = 11;
					}
					
				}
				
			}
		}
		
				
	}

}
