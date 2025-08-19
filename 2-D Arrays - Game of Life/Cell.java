import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Cell {
	//true = alive | false = dead
	private boolean state;
	private JPanel panel;
	private JButton button;
	
	public Cell(JPanel panel, boolean state) {
		this.state = state;
		this.panel = panel;
		newButton();
	}
	
	private void newButton() {
		button = new JButton("");
    	button.setBorderPainted(false);
    	button.setBackground(Color.darkGray);
    	button.setForeground(Color.darkGray);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(state) {
            		kill();
            	} else {
            		revive();
            	}
            }
        });
        panel.add(button);
        if(state) {
    		kill();
    	} else {
    		revive();
    	}
	}
	
	public void kill() {
		state = false;
		button.setOpaque(false);
    	button.setContentAreaFilled(false);
	}
	
	public void revive() {
		state = true;
		button.setOpaque(true);
    	button.setContentAreaFilled(true);
	}
	
	public boolean getState() {
		return state;
	}
	
	public void setEnabled(boolean enabled) {
		button.setEnabled(enabled);
	}
}
