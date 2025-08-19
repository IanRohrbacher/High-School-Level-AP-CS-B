import javax.swing.*;
import java.awt.Dimension;
import java.util.*;

public class GUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame("My First GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		JButton button = new JButton("Press");
		frame.getContentPane().add(button); // Adds Button to content pane of frame
		frame.setVisible(true);
		
		JFrame myFrame = new JFrame("My Own Frame");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size1 = new Dimension(450, 450);
		Dimension size2 = new Dimension(300, 300);
		myFrame.setSize(size1);
		//myFrame.setMaximumSize(size1);
		myFrame.setMinimumSize(size2);
		
		if(button.isSelected()) {
			System.out.println("Quit/Open");
			frame.setVisible(false);
			myFrame.setVisible(true);
		}
		
		

	}

}
