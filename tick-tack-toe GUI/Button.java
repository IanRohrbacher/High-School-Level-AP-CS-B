import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Button {
	private static boolean turn = false;
	private static int[] stateArray = new int[9];
	private static int spot = 0;
	private int finalSpot;
	private boolean state = false;
	JButton square = new JButton("");
	
	public Button(JPanel panel) {
		finalSpot = spot;
		spot++;
		
		square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(turn) {
            		square.setBackground(Color.red);
            		square.setForeground(Color.red);
            	} else {
            		square.setBackground(Color.blue);
            		square.setForeground(Color.blue);
            	}
            	
            	stateArray[finalSpot] = (turn ? 2 : 1);
            	square.setEnabled(state);
            	state = !state;
            	turn = !turn;
            	//getList();
            }
        });
		square.setBorderPainted(false);
		square.setBackground(Color.white);
		square.setForeground(Color.white);
		
		panel.add(square);
	}
	
	public int gameOver() {
		if(     (stateArray[0] == 1 && stateArray[1] == 1 && stateArray[2] == 1) ||
				(stateArray[3] == 1 && stateArray[4] == 1 && stateArray[5] == 1) ||
				(stateArray[6] == 1 && stateArray[7] == 1 && stateArray[8] == 1) ||
				
				(stateArray[0] == 1 && stateArray[3] == 1 && stateArray[6] == 1) ||
				(stateArray[1] == 1 && stateArray[4] == 1 && stateArray[7] == 1) ||
				(stateArray[2] == 1 && stateArray[5] == 1 && stateArray[8] == 1) ||
				
				(stateArray[0] == 1 && stateArray[4] == 1 && stateArray[8] == 1) ||
				(stateArray[2] == 1 && stateArray[4] == 1 && stateArray[6] == 1)) {
			return 1;
	  } else if((stateArray[0] == 2 && stateArray[1] == 2 && stateArray[2] == 2) ||
				(stateArray[3] == 2 && stateArray[4] == 2 && stateArray[5] == 2) ||
				(stateArray[6] == 2 && stateArray[7] == 2 && stateArray[8] == 2) ||
				
				(stateArray[0] == 2 && stateArray[3] == 2 && stateArray[6] == 2) ||
				(stateArray[1] == 2 && stateArray[4] == 2 && stateArray[7] == 2) ||
				(stateArray[2] == 2 && stateArray[5] == 2 && stateArray[8] == 2) ||
				
				(stateArray[0] == 2 && stateArray[4] == 2 && stateArray[8] == 2) ||
				(stateArray[2] == 2 && stateArray[4] == 2 && stateArray[6] == 2)) {
			return 2;
		} else if (stateArray[0] != 0 && stateArray[1] != 0 && stateArray[2] != 0 &&
				   stateArray[3] != 0 && stateArray[4] != 0 && stateArray[5] != 0 && 
				   stateArray[6] != 0 && stateArray[7] != 0 && stateArray[8] != 0) {
			return 3;
		} else {
			return 0;
		}
	}

	
	public void setStateActive(int num) {
		if(num == 0) {
			this.state = false;
			square.setBackground(Color.white);
			square.setForeground(Color.white);
		} else if(num == 1) {
			this.state = true;
			square.setBackground(Color.blue);
			square.setForeground(Color.blue);
		} else if(num == 2) {
			this.state = true;
			square.setBackground(Color.red);
			square.setForeground(Color.red);
		}
		square.setEnabled(!state);
	}
	public boolean getTurn() {
		return turn;
	}
	public void getList() {
		for(int num : stateArray) {
        	System.out.print(num + " ");
    	}
    	System.out.println("");
	}
	public boolean getState() {
		return state;
	}
	
	public void reset() {
		for(int i = 0; i < 9; i++) {
			stateArray[i] = 0;
    	}
	}
}
