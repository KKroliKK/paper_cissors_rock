package simulator.Andrey_Vagin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import simulator.do_not_change.Aggressive;
import simulator.do_not_change.CapitalCase;
import simulator.do_not_change.Passive;
import simulator.do_not_change.SmallCase;
import simulator.do_not_change.Symbol;
import simulator.do_not_change.WorldController;
import simulator.do_not_change.Position;


public class Simulator extends WorldController{
	
	int whenSmallUpgrade = 2;
	int whenCapitalDie = 6;
	
	
	public static void main(String[] args) {
		
		Simulator launch = new Simulator();
		
		
		// I commented classes SymbolCapitalS and SymbolSmallR
		// all other classes have the same functions
				
		
		demoOfSymbolsMove();
		launch.plotWorld();
		launch.symbolsMove(null);
		launch.symbolsMove(null);
		launch.plotWorld();
		
		
		
		demoOfSymbolsUpgrade();
		for (int i = 1; i <= 9; ++i) {
		launch.symbolsDie(null);
		launch.smallCaseUpgrade(null);
			if(i % 2 == 1) {
				launch.plotWorld();
			}
		}
		
		
		
		demoCapitalJump();
		launch.plotWorld();
		launch.capitalCaseJump(null);
		launch.capitalCaseJump(null);
		launch.plotWorld();
		
		
		
		demoOfPassiveEscape();
		launch.plotWorld();
		launch.passiveEscape(null);
		launch.passiveEscape(null);
		launch.plotWorld();
		
		
		
		demoOfPassiveBreed();
		launch.plotWorld();
		launch.passiveBreed(null);
		launch.passiveBreed(null);
		launch.plotWorld();
		
		
		
		demoOfAttackSmart();
		launch.plotWorld();
		launch.aggressiveAttackSmart(null);
		launch.aggressiveAttackSmart(null);
		launch.plotWorld();
		
		
		
		startSimulation();
		for (int i = 1; i <= 20; ++i) {
			launch.symbolsMove(null);
			launch.symbolsDie(null);
			launch.smallCaseUpgrade(null);
			launch.capitalCaseJump(null);
			launch.passiveEscape(null);
			if(i % 10 == 0) {            // passive breed creates to many new symbols
			launch.passiveBreed(null); 
			}
			launch.aggressiveAttackSmart(null);
			launch.plotWorld();
		}
		

		
		
	}

	
	// All the methods work in the same way: 
	// Method goes through the hashmap and finds symbol that didn't perform it's function,
	// then method lunches this function for Symbol, marks this Symbol as performed the action,
	// and then method starts going through the hash map from the very beginning again, until it finds
	// another symbol that did't perform the action. Method do so until all the symbols perform their method
	// It is needed because I store symbols only in hash map, and when I change the position of Symbol, configuartion of the world changes
	
	
	@Override
	public void symbolsMove(List<Symbol> symbols) {  
		
		Boolean action = true;
		
		while (action) {     // work untill all the symbols jump
			
			action = false;
		
			for (int i = 1; i <= 10; ++i) {
				for (int j = 1; j <= 10; ++j) { // go through hashmap
					
					Position thisPosition = new Position(i, j);
					Position whereToMove = new Position(0, 0);
					int t = 0;  // store the position of current symbol in linked list of sell (i, j)
					boolean toAdd = true;
					for (Symbol x: world.get(thisPosition)) {  // launch move method for all symbols in position (i, j)
												
						char switcher = whatIsIt(x);  // find out what is a symbol
						
						switch (switcher) {
						
						case 'P':  // process move function for Capital P symbol
							if( !((SymbolCapitalP) x).moved) { // launch function if this Symbol didn't moved in this iteration
								//toBreak = true;
								action = true;
								((SymbolCapitalP) x).move();  // launch move method for the symbol
							
								if(!world.get(x.getPosition()).isEmpty())  { // kill the paper simbol if it turned to position with scissors
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalS || world.get(x.getPosition()).getFirst() instanceof SymbolSmallS) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
							
						case 'R':
							if( !((SymbolCapitalR) x).moved) {
								//toBreak = true;
								action = true;
								((SymbolCapitalR) x).move();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalP || world.get(x.getPosition()).getFirst() instanceof SymbolSmallP) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
						
						case 'S':
							if( !((SymbolCapitalS) x).moved) {
								//toBreak = true;
								action = true;
								((SymbolCapitalS) x).move();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalR || world.get(x.getPosition()).getFirst() instanceof SymbolSmallR) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
							
						case 'p':
							if( !((SymbolSmallP) x).moved) {
								//toBreak = true;
								action = true;
								((SymbolSmallP) x).move();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalS || world.get(x.getPosition()).getFirst() instanceof SymbolSmallS) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
							
						case 'r':
							if( !((SymbolSmallR) x).moved) {
								//toBreak = true;
								action = true;
								((SymbolSmallR) x).move();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalP || world.get(x.getPosition()).getFirst() instanceof SymbolSmallP) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
							
						case 's':
							if( !((SymbolSmallS) x).moved) {
								//toBreak = true;
								action = true;
								((SymbolSmallS) x).move();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalR || world.get(x.getPosition()).getFirst() instanceof SymbolSmallR) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
						
						default:
							break;
						}
						
						if(action) {
							break;  // if current symbol was moved go to find another symbol that hasn't moved 
						}
						
						t++;  // store the position of current symbol in linked list of sell (i, j)
					}
					
					if(action) {
						if(toAdd) {
							world.get(whereToMove).add(world.get(thisPosition).get(t));  // save new position of the symbol
						}						
						world.get(thisPosition).remove(t);  // remove the symbol from the previous position
						i = 11;
						j = 11;  // go out of the for loop
					}
					
				}
			}
		}
		
		for (int i = 1; i <= 10; ++i) {                              // mark all the Symbols as not moved before next iteration of the simulation
			for (int j = 1; j <= 10; ++j) {
				Position thisPosition = new Position(i, j);
				for(Symbol x : world.get(thisPosition)) {
					
					char switcher = whatIsIt(x);
					
					switch (switcher) {
					
					case 'P': ((SymbolCapitalP) x).moved = false; break;
						
					case 'R': ((SymbolCapitalR) x).moved = false; break;
					
					case 'S': ((SymbolCapitalS) x).moved = false; break;
						
					case 'p': ((SymbolSmallP) x).moved = false; break;
						
					case 'r': ((SymbolSmallR) x).moved = false; break;
						
					case 's': ((SymbolSmallS) x).moved = false; break;
					
					default: break;
					}
					
				}
			}
		}
		
	}

	
	
	@Override
	public void symbolsDie(List<Symbol> symbols) {
		for (int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				
				Position thisPosition = new Position(i, j);
				int t = 0; // store the position of current symbol in linked list of sell (i, j)
				
				LinkedList<Integer> toDie = new LinkedList<Integer>();  // store numbers of positions of symbols whom has to die in position (i, j) during this iteration 
				
				for (Symbol x: world.get(thisPosition)) {
				
					char switcher = whatIsIt(x);
					
					if (switcher == 'P' || switcher == 'S' || switcher == 'R') {
						x.becomeOlder();
						
						if(x.getNumberIterationsAlive() > whenCapitalDie) {
							toDie.add(t); // mark symbols that have to die in this iteration
						}
						
					}
					t++;
				}
				
				for(int q = 0; q < toDie.size(); ++q) {  // remove all the died symbols from the hashmap
					Simulator.world.get(thisPosition).remove(toDie.get(q) - q);  
				}	
			}
		}	
	}

	
	
	
	
	@Override
	public void smallCaseUpgrade(List<SmallCase> symbols) {
		for (int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				
				Position thisPosition = new Position(i, j);
				int t = 0;  // store the position of current symbol in linked list of sell (i, j)
				
				LinkedList<Integer> toUpgrade = new LinkedList<Integer>();
				
				for (Symbol x: world.get(thisPosition)) {
				
					char switcher = whatIsIt(x);
					
					if (switcher == 'p' || switcher == 's' || switcher == 'r') {
						x.becomeOlder();
						
						if(x.getNumberIterationsAlive() > whenSmallUpgrade) {
							toUpgrade.add(t);  // add symbol that has to be upgraded to the list
						}
						
					}
					t++;
				}
				
				for(int q = 0; q < toUpgrade.size(); ++q) { // upgrade the symbols
					
					if(Simulator.world.get(thisPosition).get(toUpgrade.get(q) - q)	 instanceof SymbolSmallP) {
						((SymbolSmallP) Simulator.world.get(thisPosition).get(toUpgrade.get(q) - q)	).upgrade();
						
					}else if(Simulator.world.get(thisPosition).get(toUpgrade.get(q) - q)	 instanceof SymbolSmallR) {
						((SymbolSmallR) Simulator.world.get(thisPosition).get(toUpgrade.get(q) - q)	).upgrade();
						
					}else if(Simulator.world.get(thisPosition).get(toUpgrade.get(q) - q)	 instanceof SymbolSmallS) {
						((SymbolSmallS) Simulator.world.get(thisPosition).get(toUpgrade.get(q) - q)	).upgrade();
					}
											
					Simulator.world.get(thisPosition).remove(toUpgrade.get(q) - q); // remove small-case instance of a current upgraded symbol
				}	
			}
		}	
	}

	
	// all the same as for move method, but I call .jump() method for symbols
	
	@Override
	public void capitalCaseJump(List<CapitalCase> symbols) {
Boolean action = true;
		
		while (action) {
			
			action = false;
		
			for (int i = 1; i <= 10; ++i) {
				for (int j = 1; j <= 10; ++j) {
					
					Position thisPosition = new Position(i, j);
					Position whereToMove = new Position(0, 0);
					int t = 0;
					boolean toAdd = true;
					for (Symbol x: world.get(thisPosition)) {
												
						char switcher = whatIsIt(x);
						
						switch (switcher) {
						
						case 'P':
							if( !((SymbolCapitalP) x).jumped) {
								//toBreak = true;
								action = true;
								((SymbolCapitalP) x).jump();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalS || world.get(x.getPosition()).getFirst() instanceof SymbolSmallS) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
							
						case 'R':
							if( !((SymbolCapitalR) x).jumped) {
								//toBreak = true;
								action = true;
								((SymbolCapitalR) x).jump();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalP || world.get(x.getPosition()).getFirst() instanceof SymbolSmallP) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
						
						case 'S':
							if( !((SymbolCapitalS) x).jumped) {
								action = true;
								((SymbolCapitalS) x).jump();
							
								if(!world.get(x.getPosition()).isEmpty())  {
									if(world.get(x.getPosition()).getFirst() instanceof SymbolCapitalR || world.get(x.getPosition()).getFirst() instanceof SymbolSmallR) {
										toAdd = false;
									}
								}								
							}
							whereToMove = x.getPosition();
							break;
							
						
						default:
							break;
						}
						
						if(action) {
							break;
						}
						
						t++;
					}
					
					if(action) {
						if(toAdd) {
							world.get(whereToMove).add(world.get(thisPosition).get(t));
						}						
						world.get(thisPosition).remove(t);
						i = 11;
						j = 11;
					}
				}
			}
		}
		
		for (int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				Position thisPosition = new Position(i, j);
				for(Symbol x : world.get(thisPosition)) {
					
					char switcher = whatIsIt(x);
					
					switch (switcher) {
					
					case 'P': ((SymbolCapitalP) x).jumped = false; break;
						
					case 'R': ((SymbolCapitalR) x).jumped = false; break;
					
					case 'S': ((SymbolCapitalS) x).jumped = false; break;
						
					default: break;
					}
					
				}
			}
		}
		
	}

	
	
	// all the same as for move method, but with some corrections for .escape() method
	
	@Override
	public void passiveEscape(List<Passive> symbols) {
		Boolean action = true;
		
		while (action) {
			
			action = false;
		
			for (int i = 1; i <= 10; ++i) {
				for (int j = 1; j <= 10; ++j) {
					
					Position thisPosition = new Position(i, j);
					int t = 0;
					for (Symbol x: world.get(thisPosition)) {
					
						if(x instanceof SymbolCapitalP) {
							if(!((SymbolCapitalP)x).escaped) {
								
								((SymbolCapitalP) x).escape();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									i = 11;
									j = 11;
								}
							}
							break;
						}else if (x instanceof SymbolSmallR) {
							if(!((SymbolSmallR)x).escaped) {
								
								((SymbolSmallR) x).escape();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									i = 11;
									j = 11;
								}
							}
							break;
						}else if (x instanceof SymbolSmallS) {
							if(!((SymbolSmallS)x).escaped) {
								
								((SymbolSmallS) x).escape();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									i = 11;
									j = 11;
								}
							}
							break;
						}
						
						t++;
					}
				}
			}
		}
		
		for (int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				Position thisPosition = new Position(i, j);
				for(Symbol x : world.get(thisPosition)) {
					
					if(x instanceof SymbolCapitalP) {
						((SymbolCapitalP) x).escaped = false;
					}else if (x instanceof SymbolSmallR) {
						((SymbolSmallR) x).escaped = false;
					}else if (x instanceof SymbolSmallS) {
						((SymbolSmallS) x).escaped = false;
					}
					
				}
			}
		}
				
	}

	
	// all the same as for move method, but with some corrections for .breed() method
	
	@Override
	public void passiveBreed(List<Passive> symbols) {
		Boolean action = true;
		
		while (action) {
			
			
			action = false;
		
			for (int i = 1; i <= 10; ++i) {
				for (int j = 1; j <= 10; ++j) {
					
					Position thisPosition = new Position(i, j);
					int t = 0;
					if(!world.get(thisPosition).isEmpty()) {
					for (Symbol x: world.get(thisPosition)) {
					
						if(x instanceof SymbolCapitalP) {
							if(!((SymbolCapitalP)x).breeded) {
								
								((SymbolCapitalP) x).moveBreed();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									
								}
								i = 11;
								j = 11;
							}
							break;
						}else if(x instanceof SymbolSmallR) {
							if(!((SymbolSmallR)x).breeded) {
								
								((SymbolSmallR) x).moveBreed();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									
								}
								i = 11;
								j = 11;
							}
							break;
						} else if(x instanceof SymbolSmallS) {
							if(!((SymbolSmallS)x).breeded) {
								
								((SymbolSmallS) x).moveBreed();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									
								}
								i = 11;
								j = 11;
							}
							break;
						}

						
						t++;
					
					}
				}
			}
		}
		
		
		}
		for (int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				Position thisPosition = new Position(i, j);
				for(Symbol x : world.get(thisPosition)) {
					
					if(x instanceof SymbolCapitalP) {
						((SymbolCapitalP) x).breeded = false;
					}
					else if (x instanceof SymbolSmallR) {
						((SymbolSmallR) x).breeded = false;
					}else if (x instanceof SymbolSmallS) {
						((SymbolSmallS) x).breeded = false;
					}
					
				}
			}
		}
		
	}

	
	// all the same as for move method, but with some corrections for .attack() method

	
	@Override
	public void aggressiveAttackSmart(List<Aggressive> symbols) {
		
		Boolean action = true;
		
		while (action) {
			
			action = false;
		
			for (int i = 1; i <= 10; ++i) {
				for (int j = 1; j <= 10; ++j) {
					
					Position thisPosition = new Position(i, j);
					int t = 0;
					for (Symbol x: world.get(thisPosition)) {
					
						if(x instanceof SymbolCapitalS) {
							if(!((SymbolCapitalS)x).attacked) {
								
								((SymbolCapitalS) x).attackSmart();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									i = 11;
									j = 11;
								}
							}
							break;
						}else if (x instanceof SymbolCapitalR) {
							if(!((SymbolCapitalR)x).attacked) {
								
								((SymbolCapitalR) x).attackSmart();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									i = 11;
									j = 11;
								}
							}
							break;
						}else if (x instanceof SymbolSmallP) {
							if(!((SymbolSmallP)x).attacked) {
								
								((SymbolSmallP) x).attackSmart();
								action = true;

								if (x.getPosition().column != thisPosition.column || x.getPosition().row != thisPosition.row ) {
								
									world.get(x.getPosition()).add(world.get(thisPosition).get(t));
									world.get(thisPosition).remove(t);
									i = 11;
									j = 11;
								}
							}
							break;
						}
						
						t++;
					}
				}
			}
		}
		
		for (int i = 1; i <= 10; ++i) {
			for (int j = 1; j <= 10; ++j) {
				Position thisPosition = new Position(i, j);
				for(Symbol x : world.get(thisPosition)) {
					
					if(x instanceof SymbolCapitalS) {
						((SymbolCapitalS) x).attacked = false;
					}else if (x instanceof SymbolCapitalR) {
						((SymbolCapitalR) x).attacked = false;
					}else if (x instanceof SymbolSmallP) {
						((SymbolSmallP) x).attacked = false;
					}
					
				}
			}
		}
		
	}
	
	
	
	
	
	public char whatIsIt(Symbol symbol) {             // returns a corresponding char for a given Symbol
		if (symbol instanceof SymbolCapitalS) {
			return CAPITAL_S;
		}else if (symbol instanceof SymbolCapitalP) {
			return CAPITAL_P;
		}else if (symbol instanceof SymbolCapitalR) {
			return CAPITAL_R;
		}else if (symbol instanceof SymbolSmallS) {
			return SMALL_S;
		}else if (symbol instanceof SymbolSmallP) {
			return SMALL_P;
		}else if (symbol instanceof SymbolSmallR) {
			return SMALL_R;
		}
		
		return ' ';
	}

	
	
	@Override
	public String plotWorld() {
		
		String[] theWorld = new String[25];
		String rowOfDividers = "+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+";
		
		System.out.println(rowOfDividers);
		
		for(int i = 0; i < 25; ++i) {
			theWorld[i] = "|";
		}
		
		for (int i = 1; i <= 10; ++i) {
			for(int j = 1; j <= 10; ++j) {
				
				Position checkPosition = new Position(i, j);
					
					int amountOfSymbolsInCurrentPosition = world.get(checkPosition).size();
					
					switch (amountOfSymbolsInCurrentPosition) {
					
					case 0:
						theWorld[2*i - 1] += "     |";
						theWorld[2*i] += "     |";
						break;
						
					case 1: 
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).getFirst());
						theWorld[2*i - 1] += "    |";
						theWorld[2*i] += "     |";
						break;
						
					case 2:
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).getFirst());
						theWorld[2*i - 1] += "    |";
						theWorld[2*i] += "    ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(1));
						theWorld[2*i] += "|";
						break;
						
					case 3:
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).getFirst());
						theWorld[2*i - 1] += "   ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(1));
						theWorld[2*i - 1] += "|";
						theWorld[2*i] += "  ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(2));
						theWorld[2*i] += "  ";
						theWorld[2*i] += "|";
						break;
						
					case 4:
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).getFirst());
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(1));
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(2));
						theWorld[2*i - 1] += "|";
						theWorld[2*i] += "  ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(3));
						theWorld[2*i] += "  ";
						theWorld[2*i] += "|";
						break;
						
					case 5:
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).getFirst());
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(1));
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(2));
						theWorld[2*i - 1] += "|";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(3));
						theWorld[2*i] += "   ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(4));
						theWorld[2*i] += "|";
						break;
						
					case 6:
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).getFirst());
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(1));
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(2));
						theWorld[2*i - 1] += "|";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(3));
						theWorld[2*i] += " ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(4));
						theWorld[2*i] += " ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(5));
						theWorld[2*i] += "|";
						break;
						
					default:
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).getFirst());
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(1));
						theWorld[2*i - 1] += " ";
						theWorld[2*i - 1] += whatIsIt(world.get(checkPosition).get(2));
						theWorld[2*i - 1] += "|";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(3));
						theWorld[2*i] += " ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(4));
						theWorld[2*i] += " ";
						theWorld[2*i] += whatIsIt(world.get(checkPosition).get(5));
						theWorld[2*i] += "|";
						break;
					}
					
				
			}
		}
		
		for (int i = 1; i <= 10; i++) {
			System.out.println(theWorld[i*2 - 1]);
			System.out.println(theWorld[i*2]);
			System.out.println(rowOfDividers);
		}
		
		
		System.out.println("\n\n\n");
		
		return null;
	}

	
	
	
static void demoOfSymbolsMove() {
		
		world = new HashMap<Position, LinkedList<Symbol>>();
		Position setPos = new Position(0, 0);
		
		System.out.println("\n               Demonstration of symbolsMove\n");
		
		for (int i = 1; i <= 10 ; ++i) {
			for (int j = 1; j <= 10; ++ j) {
				setPos = new Position(i, j);
				LinkedList<Symbol> emptyList = new LinkedList<Symbol>();
				world.put(setPos, emptyList);
			}
		}
				
		setPos = new Position(1,1);
		world.get(setPos).add(new SymbolCapitalP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(3,3);
		world.get(setPos).add(new SymbolCapitalS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(7,7);
		world.get(setPos).add(new SymbolCapitalR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,10);
		world.get(setPos).add(new SymbolSmallP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(1,10);
		world.get(setPos).add(new SymbolSmallR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,1);
		world.get(setPos).add(new SymbolCapitalS());
		world.get(setPos).getLast().setPosition(setPos);
	}
	
	
	static void demoOfSymbolsUpgrade() {
		
		world = new HashMap<Position, LinkedList<Symbol>>();
		Position setPos = new Position(0, 0);
		
		System.out.println("\n            Demonstration of symbolsUpgrade and symbolsDie\n");
		
		for (int i = 1; i <= 10 ; ++i) {
			for (int j = 1; j <= 10; ++ j) {
				setPos = new Position(i, j);
				LinkedList<Symbol> emptyList = new LinkedList<Symbol>();
				world.put(setPos, emptyList);
			}
		}
				
		setPos = new Position(1,1);
		world.get(setPos).add(new SymbolCapitalP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(3,3);
		world.get(setPos).add(new SymbolCapitalS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(7,7);
		world.get(setPos).add(new SymbolCapitalR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,10);
		world.get(setPos).add(new SymbolSmallP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(1,10);
		world.get(setPos).add(new SymbolSmallR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,1);
		world.get(setPos).add(new SymbolCapitalS());
		world.get(setPos).getLast().setPosition(setPos);

	}
	
	
	static void demoCapitalJump() {
		
		world = new HashMap<Position, LinkedList<Symbol>>();
		Position setPos = new Position(0, 0);
		
		System.out.println("\n                Demonstration of capitalJump\n");
		
		for (int i = 1; i <= 10 ; ++i) {
			for (int j = 1; j <= 10; ++ j) {
				setPos = new Position(i, j);
				LinkedList<Symbol> emptyList = new LinkedList<Symbol>();
				world.put(setPos, emptyList);
			}
		}
				
		setPos = new Position(1,1);
		world.get(setPos).add(new SymbolCapitalP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(3,3);
		world.get(setPos).add(new SymbolCapitalS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(7,7);
		world.get(setPos).add(new SymbolCapitalR());
		world.get(setPos).getLast().setPosition(setPos);
		
	}
	
	
	
	static void demoOfPassiveEscape() {
		
		world = new HashMap<Position, LinkedList<Symbol>>();
		Position setPos = new Position(0, 0);
		
		System.out.println("\n                Demonstration of passiveEscape\n");
		
		for (int i = 1; i <= 10 ; ++i) {
			for (int j = 1; j <= 10; ++ j) {
				setPos = new Position(i, j);
				LinkedList<Symbol> emptyList = new LinkedList<Symbol>();
				world.put(setPos, emptyList);
			}
		}
				
		setPos = new Position(4,4);
		world.get(setPos).add(new SymbolCapitalS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(3, 3);
		world.get(setPos).add(new SymbolCapitalP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,1);
		world.get(setPos).add(new SymbolCapitalR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(9,1);
		world.get(setPos).add(new SymbolSmallS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(9,9);
		world.get(setPos).add(new SymbolSmallR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,10);
		world.get(setPos).add(new SymbolSmallP());
		world.get(setPos).getLast().setPosition(setPos);
	}
	
	
	
	static void demoOfPassiveBreed() {
		
		world = new HashMap<Position, LinkedList<Symbol>>();
		Position setPos = new Position(0, 0);
		
System.out.println("\n                Demonstration of passiveBreed\n");
		
		for (int i = 1; i <= 10 ; ++i) {
			for (int j = 1; j <= 10; ++ j) {
				setPos = new Position(i, j);
				LinkedList<Symbol> emptyList = new LinkedList<Symbol>();
				world.put(setPos, emptyList);
			}
		}
				
		setPos = new Position(1,1);
		world.get(setPos).add(new SymbolCapitalP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(1,2);
		world.get(setPos).add(new SymbolCapitalP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,10);
		world.get(setPos).add(new SymbolSmallS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(9,10);
		world.get(setPos).add(new SymbolSmallS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(1,10);
		world.get(setPos).add(new SymbolSmallR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(2,10);
		world.get(setPos).add(new SymbolSmallR());
		world.get(setPos).getLast().setPosition(setPos);
	}
	
	
	
	static void demoOfAttackSmart() {
		
		world = new HashMap<Position, LinkedList<Symbol>>();
		Position setPos = new Position(0, 0);
		
System.out.println("\n                Demonstration of aggressiveAttack\n");
		
		for (int i = 1; i <= 10 ; ++i) {
			for (int j = 1; j <= 10; ++ j) {
				setPos = new Position(i, j);
				LinkedList<Symbol> emptyList = new LinkedList<Symbol>();
				world.put(setPos, emptyList);
			}
		}
				
		setPos = new Position(4,4);
		world.get(setPos).add(new SymbolCapitalS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(3, 3);
		world.get(setPos).add(new SymbolSmallP());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,1);
		world.get(setPos).add(new SymbolCapitalR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(9,1);
		world.get(setPos).add(new SymbolSmallS());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(9,9);
		world.get(setPos).add(new SymbolSmallR());
		world.get(setPos).getLast().setPosition(setPos);
		
		setPos = new Position(10,10);
		world.get(setPos).add(new SymbolSmallP());
		world.get(setPos).getLast().setPosition(setPos);
	}
	
	
	
	static void startSimulation() {
		
		world = new HashMap<Position, LinkedList<Symbol>>();
		Position setPos = new Position(0, 0);
		
		
System.out.println("\n\n\n\n                The whole simulation:\n");

		
		
		for (int i = 1; i <= 10 ; ++i) {   // construction for creating random symbols for created world
			for (int j = 1; j <= 10; ++ j) {
				setPos = new Position(i, j);
				LinkedList<Symbol> emptyList = new LinkedList<Symbol>();
				world.put(setPos, emptyList);
				
				
				if(i % 2 == 1 || j % 2 == 0) {
					
					int switcher = (int) (Math.random()*6) + 1;
					
					switch(switcher) {
					
					case 1:
						world.get(setPos).add(new SymbolCapitalP());
						world.get(setPos).getLast().setPosition(setPos);
					break;
					
					case 2:
						world.get(setPos).add(new SymbolCapitalR());
						world.get(setPos).getLast().setPosition(setPos);
					break;
					
					case 3:
						world.get(setPos).add(new SymbolCapitalS());
						world.get(setPos).getLast().setPosition(setPos);
					break;
					
					case 4:
						world.get(setPos).add(new SymbolSmallP());
						world.get(setPos).getLast().setPosition(setPos);
					break;
					
					case 5:
						world.get(setPos).add(new SymbolSmallR());
						world.get(setPos).getLast().setPosition(setPos);
					break;
					
					case 6:
						world.get(setPos).add(new SymbolSmallS());
						world.get(setPos).getLast().setPosition(setPos);
					break;
					}
					
				}
			}
		}
	}
}
