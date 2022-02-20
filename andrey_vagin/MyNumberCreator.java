package numbers.andrey_vagin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import numbers.do_not_change.NumberCreator;

public class MyNumberCreator extends NumberCreator{

	
	/**
     * This method allows to choose the size of list
     * with numbers by scanning it
     *
     * @return the quantity of numbers in the list
     */
	@Override
	public int validateAndSetNumberQuantity() {
		
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Insert the number quanity from 5 to 20"); // throw exception if the quantity is too small
			int amount = input.nextInt();
			
			if (amount < MIN_NUMBER_QUANTITY) {
				try {
					throw new Exception("The number is too small"); // throw exception if the quantity is too small
				} catch (Exception a) {
					a.printStackTrace();
					return 0;
				}
			}
			
			
			if (amount > MAX_NUMBER_QUANTITY) {
				try {
					throw new Exception("The number is too big");  // throw exception if the quantity is too big
				} catch (Exception a) {
					a.printStackTrace();
					return 0;
				}
			}
			
			return amount;
			
		}catch(Exception e) { // throw exception if input is invalid
			try {
				throw new Exception("Invalid input");
			} catch (Exception a) {
				a.printStackTrace();
				return 0;
			}
		}
		
		
	}
	
	
	
	
	/**
     * This method allows generating the list of numbers
     * of different types
     *
     * @param numberQuantity the quantity of numbers in the list
     * @return the list of generated numbers
     */
	@Override
	public List<Number> generateNumbers(int numberQuantity) {
		 
		List<Number> theList = new ArrayList<Number>(); // temporary list 
		
		for (int i = 1; i <= numberQuantity; ++i) {
			
			int switcher = (int) (Math.random() * 8 + 1); // random selection of generating type
			
			Number newNumber = Math.random()*202 - 101; // generate random number  
			
			switch (switcher) { // add generated number
			case 1:
				theList.add(newNumber.byteValue());
				break;
				
			case 2:
				theList.add(newNumber.doubleValue());
				break;
				
			case 3:
				theList.add(newNumber.floatValue());
				break;
			
			case 4:
				theList.add(newNumber.intValue());
				break;
				
			case 5:
				theList.add(newNumber.longValue());
				break;
				
			case 6:
				theList.add(newNumber.shortValue());
				break;
				
			case 7:
				theList.add(BigDecimal.valueOf(newNumber.doubleValue()));
				break;
				
			case 8:
				theList.add(BigInteger.valueOf(newNumber.longValue()));
				break;	
			}
			
		}
		
		return theList;
	}
	
	
	
	/**
     * This method allows to insert numbers into the list
     * of randomly chosen types. The user has to print the
     * values via console. The incorrectly printed data has
     * to be handled
     *
     * @return the list of numbers inserted by user
     */
	@Override
	public List<Number> insertNumbers(int numberQuantity) {
		
		System.out.println("Enter the numbers from -100 to 100");
		
		List<Number> theList = new ArrayList<Number>(); // temporary list
		
		Scanner input = new Scanner(System.in);
		
		for (int i = 1; i <= numberQuantity; ++i) {
		
			int switcher = (int) (Math.random() * 8 + 1); // random type selection for inserting number
			
			switch (switcher) {  // get number from user
			
			case 1:
				System.out.println("\n\nInsert your Byte number");
				theList.add(checkBounders(input.nextByte())); // checkBoundares throws an exception if input number is out boundaries
				break;
			
			case 2:
				System.out.println("\n\nInsert your Double number");
				theList.add(checkBounders(input.nextDouble()));
				break;	
				
			case 3:
				System.out.println("\n\nInsert your Float number");
				theList.add(checkBounders(input.nextFloat()));
				break;	
			
			case 4:
				System.out.println("\n\nInsert your Integer number");
				theList.add(checkBounders(input.nextInt()));
				break;
				
			case 5:
				System.out.println("\n\nInsert your Long number");
				theList.add(checkBounders(input.nextLong()));
				break;
				
			case 6:
				System.out.println("\n\nInsert your Short number");
				theList.add(checkBounders(input.nextShort()));
				break;
				
			case 7:
				System.out.println("\n\nInsert your BigDecimal number");
				theList.add(checkBounders(input.nextBigDecimal()));
				break;
				
			case 8:
				System.out.println("\n\nInsert your BigInteger number");
				theList.add(checkBounders(input.nextBigInteger()));
				break;
				
			default:
				
				break;
			}
		
		}
		
		return theList;
	}

	
	
	/**
     * This method checks if the value is within the boundaries
     * If it is not, the method throws exception
     * @param Number number
     * @return Number number
     */
	Number checkBounders(Number number) {
		if (number.doubleValue() < NUMBER_LOWER_BOUNDARY ) { // throw exception if the number is too small
			try {
				throw new Exception("The number is too small");
			} catch(Exception e) {
				e.printStackTrace();
				throw new Error();
			}
		}else if (number.doubleValue() > NUMBER_UPPER_BOUNDARY ) { // throw exception if the number is too big
			try {
				throw new Exception("The number is too big");
			} catch(Exception e) {
				e.printStackTrace();
				throw new Error();
			}
		}
		return number;
	}
	
}
