import java.util.Stack;
import java.util.Scanner;

public class SuperMarket {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		// thought this would be fun
		System.out.print("How Many Loops To Run: ");
		int loopLength = input.nextInt();
		System.out.print("How Many Minutes For Each Loop: ");
		int runningMinutes = input.nextInt();
		System.out.print("Random Number Length Until Next Customer: ");
		int newCustomerChange = input.nextInt();
		
		double AverageLonestLine = 0.0;
		double AverageLongestWait = 0.0;
		double AverageLineSize = 0;
		double AverageLeftOverLineSize = 0;

		loop: for (int k = 0; k < loopLength; k++) {
			System.out.println("\nLoop: " + k + "  -------------------------------------------------------------------------------------------------\n");

			Stack<Customer> line = new Stack<Customer>();
			int lonestLine = 0;
			int longestWait = 0;
			double lineSize = 0;

			// starting with 0 because as 1 minute passes actions would take place
			int minutes = 0;
			
			int newCustomerTime = 1;
			int newCustomer = (int) ((Math.random() * newCustomerChange) + 1);
			// one loop simulates one minute each
			simulation: while (minutes < runningMinutes) { // 720
				System.out.println("New Customer Percent : " + newCustomerTime + "/" + newCustomer);

				// everyone's total timer goes up
				for (int i = 0; i < line.size(); i++) {
					line.get(i).addTotalWaitedTime();
					// System.out.println("Added Time for Index : " + i + " With Wait Times of : " +
					// line.get(i).getWaitedTime() + "/" + line.get(i).getServiceTime());
				}
				// System.out.println("Every Customer Total Time Increases");

				// there needs to be customers to remove or add time
				if (line.size() > 0) {

					// time moves and if conditions are meet then things happen
					line.firstElement().waited();
					System.out.println("Customer in the Front Checking out Percent : "
							+ line.firstElement().getWaitedTime() + "/" + line.firstElement().getServiceTime());

					// one minute happens so they can leave, move along in line or keep checking out
					if (line.firstElement().isLeaving()) {
						// if the person is leaving they get removed and longest wait is recorded
						longestWait = (line.firstElement().getTotalWaitedTime() > longestWait)
								? line.firstElement().getTotalWaitedTime()
								: longestWait;
						System.out.println("Longest Waited Time: " + longestWait);
						System.out.println("Customer in the Front is Leaving and Waited : "
								+ line.firstElement().getTotalWaitedTime());
						line.remove(0);
					}
				}

				// customers time increase shouldn't happen if they just arrived so new arriving
				// customers comes last
				if (newCustomerTime == newCustomer) {
					Customer person = new Customer((int) ((Math.random() * 4) + 1));
					line.push(person);
					// resets the time for a new customer
					newCustomerTime = 0;
					newCustomer = (int) ((Math.random() * 4) + 1);
					System.out.println("A New Customer Arrives at the End of the Line");
					System.out.println("Customer Waits : " + line.peek().getServiceTime());
				}
				
				// marks the line size to average later
				lineSize += line.size();
				// marks the lines longest length
				lonestLine = ((line.size()) > lonestLine) ? (line.size()) : lonestLine;
				System.out.println("The Lonest Line is : " + lonestLine);
				// moves the chance that a new customer is added
				newCustomerTime = (newCustomerTime == 4) ? 1 : newCustomerTime + 1;
				// keeps time flowing
				minutes++;
				System.out.println(minutes + " Minutes have passed\n\n");
			}
			System.out.println(minutes + " Minutes have Passed");
			System.out.println("The Average Line Size Was " + lineSize/runningMinutes);
			System.out.println("The Line got to a Length of " + lonestLine);
			System.out.println("The Longest Time One Customer Waited was " + longestWait + " Minutes");
			System.out.println("There was " + line.size() + " Customers Left Over by the End");
		AverageLonestLine += lonestLine;
		AverageLineSize += (lineSize/runningMinutes);
		AverageLongestWait += longestWait;
		AverageLeftOverLineSize += line.size();
		}
		System.out.println("\n-------------------------------------------------------------------------------------------------------\n");
		System.out.println(loopLength + " Loops have Passed With " + runningMinutes + " Minutes");
		System.out.println("Line Size Was on Average " + AverageLineSize/loopLength);
		System.out.println("The Line got to a Length on Average of " + AverageLonestLine/loopLength);
		System.out.println("The Longest Time One Customer Waited on Average was " + AverageLongestWait/loopLength + " Minutes");
		System.out.println("There was " + AverageLeftOverLineSize/loopLength + " Customers Left Over on Average by the End");
	}
}

/*
1. What is the maximum number of customers in the queue at any time?
There was a average of 6 people in the line and 13 people max on average

2. What is the longest wait any one customer experiences?
around 33 minutes

3. What happens if the arrival interval is changed from 1 to 4 minutes to 1 to 3 minutes?
there is a small increase to each number when you change the time from 4 to 3 (look below)

Four People Until Next Customer
1000 Loops have Passed With 720 Minutes
Line Size Was on Average 6.219218055555558
The Line got to a Length on Average of 14.009
The Longest Time One Customer Waited on Average was 33.819 Minutes
There was 9.373 Customers Left Over on Average by the End


Three People Until Next Customer
1000 Loops have Passed With 720 Minutes
Line Size Was on Average 6.30036388888889
The Line got to a Length on Average of 14.106
The Longest Time One Customer Waited on Average was 33.891 Minutes
There was 9.59 Customers Left Over on Average by the End
*/
