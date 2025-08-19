import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {
	static Button[] squareList = new Button[9];
	
	public Gui() {
		JPanel mainPanel = new JPanel();
		GridLayout layout = new GridLayout(3, 3);
		layout.setHgap(4);
		layout.setVgap(4);
		mainPanel.setLayout(layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		setResizable(false);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tick-Tack-Toe");
		
        
		for(int i = 0; i < 9; i++) {
			Button square = new Button(mainPanel);
			squareList[i] = square;
		}
		
		add(mainPanel);
	}
	
	public static void main(String[] args) {
		Gui game = new Gui();
        game.setVisible(true);
        while(true) {
	        if(squareList[0].gameOver() == 1) {
	        	try {
	        		setActive(1);
					Thread.sleep(2000);
//					System.out.println("Ran");
					squareList[0].reset();
					setActive(0);
					squareList[0].getList();
//					for(Button button : squareList) {
//			        	System.out.print(button.getState() + " ");
//			    	}
//			    	System.out.println("");
				} catch (InterruptedException e1) {}
	    	} else if(squareList[0].gameOver() == 2) {
	    		try {
	    			setActive(2);
	    			Thread.sleep(2000);
	    			squareList[0].reset();
	    			setActive(0);
	    			squareList[0].getList();
//	    			for(Button button : squareList) {
//	    	        	System.out.print(button.getState() + " ");
//	    	    	}
//	    	    	System.out.println("");
				} catch (InterruptedException e1) {}
	    	} else if(squareList[0].gameOver() == 3) {
	    		try {
	    			Thread.sleep(2000);
	    			squareList[0].reset();
	    			setActive(0);
	    			squareList[0].getList();
//	    			for(Button button : squareList) {
//	    	        	System.out.print(button.getState() + " ");
//	    	    	}
//	    	    	System.out.println("");
				} catch (InterruptedException e1) {}
	    	}
        }
	}
	
	public static void setActive(int num) {
		for(int i = 0; i < 9; i++) {
			squareList[i].setStateActive(num);
		}
	}
}
