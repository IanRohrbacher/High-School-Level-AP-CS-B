import java.util.Stack;
import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Stack<Double> stack = new Stack<Double>();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Booting Stacked Based Calculator...");
		System.out.println("Stacked Based Calculator Running");
		System.out.println("'esc' to quit | 'c' to clear | suports: '+' '-' '*' '/'");
		viewStack(stack);
		runningLoop: while(true) {
			String typed = input.nextLine();
			
			if(stack.size() >= 2) {
				double first = stack.pop();
				double second = stack.pop();
				
				switch(typed) {
					case "+":
						stack.push(second + first);
						break;
						
					case "-":
						stack.push(second - first);
						break;
						
					case "*":
						stack.push(second * first);
						break;
						
					case "/":
						stack.push(second / first);
						break;
						
					case "c":
						stack.removeAll(stack);
						System.out.println("clearing...");
						break;
						
					case "esc":
						break runningLoop;
						
					default:
						stack.push(second);
						stack.push(first);
						break;
				}
			}
			if(isNumber(typed))
				stack.push(Double.parseDouble(typed));

			viewStack(stack);
		}
	}
	
	// shows what's stored in the calculator
	public static void viewStack(Stack<Double> stack) {
		System.out.println("\nIn Queue: " + stack.size());
		for (int i = 0; i < stack.size(); i++)
			System.out.println(stack.get(i));
		System.out.print("\nCurent Number: ");
	}
	
	// not my solution but is necessary
	public static boolean isNumber(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}
