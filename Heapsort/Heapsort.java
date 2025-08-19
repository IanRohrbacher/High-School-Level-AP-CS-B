
public class Heapsort {

	public static void main(String[] args) {
		//double[] list = new double[] {Double.MAX_VALUE, 7, 10, 3, 2, 1, 4, 5, 6, 8, 9 };
		//double[] list = new double[] {-1.0, 26.38, 33.56, 82.33, 5.1, 42.15, 73.37, 40.35, 63.31, 89.38, 72.11};
		double[] list = randomArray(10, 0.0, 100.0);
		
		System.out.println("Unsorted:\n" + arrayToString(list) + "\n");
		sort(list);
		System.out.println("Sorted:\n" + arrayToString(list) + "\n");
		
	}

	public static void sort(double[] a) {
		int n = a.length;
		for (int i = n / 2; i >= 1; i--) {
			reheapDown(a, i, n);
		}

		//System.out.println("arrayToString(a));
		//System.out.println("----------------------------");

		while (n > 1) {
			
			// swap a[1] with a[n-1]:
			double temp = a[1];
			a[1] = a[n - 1];
			a[n - 1] = temp;

			n--;
			reheapDown(a, 1, n);
		}
		
		// unless you can find a better way to fix the last swap this is the best solution i got
		if(a[1] > a[2]) {
			double temp = a[1];
			a[1] = a[2];
			a[2] = temp;
		}
	}

	// parent = n/2
	// left child = n*2
	// right child = n*2+1
	/* not ganna lie, this code below sucks and is the reason i could't understand
	private static void reheapDown(double[] a, int i, int n) {
		try {
			double temp = a[i];
			double leftChild = a[i * 2];
			if ((i == n / 2) && (n % 2 == 1)) {
				if (leftChild > temp) {
					a[i] = leftChild;
					a[i * 2] = temp;
					reheapDown(a, i * 2, n);
				}
			} else {
				double rightChild = a[(i * 2) + 1];

				if (leftChild > rightChild && leftChild > temp) {
					a[i] = leftChild;
					a[i * 2] = temp;
					reheapDown(a, i * 2, n);
				} else if (leftChild < rightChild && rightChild > temp) {
					a[i] = rightChild;
					a[(i * 2) + 1] = temp;
					reheapDown(a, i * 2 + 1, n);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}

	}
	*/
	
	private static void reheapDown(double[] a, int i, int n) {
		if (i <= n) {
			double rightChild = Double.MIN_VALUE;
			double leftChild = Double.MIN_VALUE;
			try { rightChild = a[(i * 2) + 1]; } catch (ArrayIndexOutOfBoundsException e) {}
			try { leftChild = a[i * 2]; } catch (ArrayIndexOutOfBoundsException e) {}
			double root = a[i];

			if ((leftChild > rightChild) && (leftChild > root) && (i*2 < n)) {
				// should never run if right dosn't exist
				// swap leftChild with root
				// call reheapDown for the Child just swapped
				a[i] = leftChild;
				a[i * 2] = root;
				reheapDown(a, i * 2, n);
			} else if ((rightChild > leftChild) && (rightChild > root) && (i*2+1 < n)) {
				// should never run if right dosn't exist
				// swap rightChild with root
				// call reheapDown for the Child just swapped
				a[i] = rightChild;
				a[(i * 2) + 1] = root;
				reheapDown(a, (i * 2) + 1, n);
			}// else {
				// DO NOT call reheapDown for the ANY Child
				//return;
			//}
		}
		//System.out.println(arrayToString(a) + "\n\n");
		return;
	}

	public static String arrayToString(double[] array) {
		String text = "|";

		for (int i = 0; i < array.length; i++) {
			text += array[i] + "|";			
		}

		return text;
	}
	
	public static double[] randomArray(int length, double min, double max) {
		double[] a = new double[length+1];
		a[0] = min - 1;
		
		for(int i = 1; i < length+1; i++) {
			a[i] = ((int)((((Math.random() * max) + min)*100)+0.5))/100.0;
		}
		
		return a;
	}
}
