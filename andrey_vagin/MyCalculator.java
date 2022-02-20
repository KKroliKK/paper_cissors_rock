package numbers.andrey_vagin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import numbers.do_not_change.Calculator;

public class MyCalculator extends Calculator{

	
	/**
     * This is the constructor of a MyCalculator
     *
     * @param numbers the list of numbers
     */
	public MyCalculator(List<Number> numbers) {
		super(numbers); // use a constructor of Calculator
	}

	
	/**
     * This method allows summarizing all elements of
     * the input list of numbers
     *
     * @return the double sum of all elements
     */
	@Override
	public double summarize() {
		
		Double result = 0.0;  //variable for result calculating
		
		for (Number x: this.getNumbers()) { // go through the list and summarize it's values 
			result += x.doubleValue();
		}
		
		return result;
	}

	
	
	
	/**
     * This method allows multiplying all elements of
     * the input list of numbers
     *
     * @return the double result of all element
     *         multiplication
     */
	@Override
	public double multiply() {
		
		Double result = 1.0;  //variable for result calculating
		
		for (Number x: this.getNumbers()) {  // go through the list and multiply it's values
			result *= x.doubleValue();
		}
		
		return result;
	}

	
	/**
     * This method allows deleting all negative numbers
     * from the list
     */
	@Override
	public void deleteNegativeNumbers() {
		
		for (int i = 0; i < this.getNumbers().size(); ++i) {
			
			if(this.getNumbers().get(i).doubleValue() < 0) { // go through the list and remove negative values
				this.getNumbers().remove(i);
				--i;
			}
			
		}
	
		if (this.getNumbers().size() < 5) {  // throw exception if there is less then 5 numbers i the list after processing
			try {
				throw new Exception("Now list is less than 5 numbers");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	 /**
     * This method allows dividing each number of the
     * list by the divisor and rewrite the list values
     *
     * @param divisor the divisor
     */
	@Override
	public void divideBy(double divisor) 	{
				
		try {
			Double div = divisor;
			int checking = 1 / div.intValue(); // throw exception if divisor is zero
			
			for (int i = 0; i < this.getNumbers().size(); ++i) { // go through the list
				
				Number temp = this.getNumbers().get(i); // variable for result calculating
								
				temp = temp.doubleValue() / div.doubleValue(); // division
				
				if (temp.doubleValue() < -100) { // throw exception if the result became to small
					try {
						throw new Exception("The number is too small");
					} catch (Exception a) {
						a.printStackTrace();
					}
				}
				
				
				if (temp.doubleValue() > 100) { // throw exception if the result became to big
					try {
						throw new Exception("The number is too big");
					} catch (Exception a) {
						a.printStackTrace();
					}
				}

				switch(getType(temp)) {  // get type of current number and cast the result
				case BYTE:
					temp = temp.byteValue();
					break;
					
				case DOUBLE:
					temp = temp.doubleValue();
					break;
					
				case FLOAT:
					temp = temp.floatValue();
					break;
					
				case INTEGER:
					temp = temp.intValue();
					break;
					
				case LONG:
					temp = temp.longValue();
					break;
					
				case SHORT:
					temp = temp.shortValue();
					break;
					
				case BIG_DECIMAL:
					temp = temp.doubleValue();
					break;
					
				case BIG_INTEGER:
					temp = temp.longValue();
					break;
					
				default:
					try { // throw exception if the type of current number is not a child of a Number
						throw new Exception("Is not a child of Number");
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
				
				this.getNumbers().remove(i); // remove previous value of processing number 
				this.getNumbers().add(i, temp); // add new value of processing number
				
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	/**
     * This method allows calculating square root
     * for each element of the array and updating the
     * list values
     */
	@Override
	public void getSquareRoot() {
			
			for (int i = 0; i < this.getNumbers().size(); ++i) {
				
				
				Number temp = this.getNumbers().get(i);  // variable for result calculating
								
					try {
						if(temp.doubleValue() < 0) { // throw exception if the current number is negative
							throw new Exception("Exception because of negative number");
						}
						
						temp = Math.sqrt(temp.doubleValue());  // calculate a new value of current number

						switch(getType(temp)) { // get type of current number and cast the result 
						case BYTE:
							temp = temp.byteValue();
							break;
							
						case DOUBLE:
							temp = temp.doubleValue();
							break;
							
						case FLOAT:
							temp = temp.floatValue();
							break;
							
						case INTEGER:
							temp = temp.intValue();
							break;
							
						case LONG:
							temp = temp.longValue();
							break;
							
						case SHORT:
							temp = temp.shortValue();
							break;
							
						case BIG_DECIMAL:
							temp = temp.doubleValue();
							break;
							
						case BIG_INTEGER:
							
							temp = temp.longValue();
							break;
							
						default:
							try { // throw exception if the type of current number is not a child of a Number
								throw new Exception("Is not a child of Number");
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
						}
						
						this.getNumbers().remove(i);  // remove previous value of processing number
						this.getNumbers().add(i, temp); // add new value of processing number
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			
			}
		
		
	}
	
	
	enum Type{ // enumerator for children of Number
		BYTE, DOUBLE, FLOAT, INTEGER, LONG, SHORT, BIG_DECIMAL, BIG_INTEGER
	};
	
	
	
	/**
     * This method allows to get corresponding  enumerator  
     * constant
     * 
     * @param Number number
     * @return corresponding  enumerator constant
     */
	private Type getType(Number number) {
		
		if(number instanceof Byte) {
			return Type.BYTE;
		}else if (number instanceof Double) {
			return Type.DOUBLE;
		}else if(number instanceof Float) {
			return Type.FLOAT;
		}else if(number instanceof Integer) {
			return Type.INTEGER;
		}else if(number instanceof Long) {
			return Type.LONG;
		}else if(number instanceof Short) {
			return Type.SHORT;
		}else if(number instanceof BigDecimal) {
			return Type.BIG_DECIMAL;
		}else if(number instanceof BigInteger) {
			return Type.BIG_INTEGER;
		}else
		
		return null;
	}
	
	

}


