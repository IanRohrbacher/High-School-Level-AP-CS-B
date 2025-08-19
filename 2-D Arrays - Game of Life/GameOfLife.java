import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GameOfLife extends JFrame {
	private Cell[][] cellList;
	private final int gridSize = 60;
	private final int buttonRoom = 67;
	private final int screenWidth = 750;
	private final int screenHeight = 750;
	// true = while is running || false = while stops running
	private boolean pausePlayState = false;
	private boolean once = true;
	
	
	public GameOfLife() {
		// creates a space to put two panels next to each other vertically
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10));

		// creates a space to make a grid of buttons
		JPanel gridPanel = new JPanel();
		GridLayout layout = new GridLayout(gridSize, gridSize);
		layout.setHgap(2);
		layout.setVgap(2);
		gridPanel.setLayout(layout);
        // adds the buttons to the space above
		cellList = new Cell[gridSize][gridSize];
        for(int i = 0; i < gridSize; i++) {
        	for(int j = 0; j < gridSize; j++) {
        		cellList[i][j] = new Cell(gridPanel, true);
        	}
        }
        
        // I need this to run parallel so I can click buttons to stop
        Runnable myTask = new Runnable() {
            public void run() {
            	boolean first = true;
            	while(true) {
            		if(pausePlayState) {
            			if(first) {
	            			try {
	        					Thread.sleep(3000);
	        				} catch (InterruptedException e) {}
	            			first = false;
            			}
	            		nextGeneration();
	            		try {
	            			Thread.sleep(180);
	            		} catch (InterruptedException e) {}
            		} else {
            			first = true;
            			try {
        					Thread.sleep(3000);
        				} catch (InterruptedException e) {}
            		}
            	}
            }
        };
        Thread t1 = new Thread(myTask);
        
        
		// creates a space under the grid for buttons
        JPanel buttonPanel = new JPanel();
        //button to move onto one next generation
        JButton generation = new JButton("Next Generation");
        generation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	nextGeneration();
            }
        });
        //button to clear board
        JButton clearBoard = new JButton("Clear");
        clearBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(Cell[] column : cellList) {
        			for(Cell row : column) {
        				row.kill();
        			}
        		}
            }
        });
        //button to fill board
        JButton fillBoard = new JButton("Fill");
        fillBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(Cell[] column : cellList) {
        			for(Cell row : column) {
        				row.revive();
        			}
        		}
            }
        });
        //buttons for examples | 0-59
        int center = 30;
        JPanel examplesPanel = new JPanel(new GridLayout(2,2));
        JButton e1 = new JButton("Examples 1");
        e1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(Cell[] column : cellList) {
        			for(Cell row : column) {
        				row.kill();
        			}
        		}
            	int[] topLeft = new int[]{24, 24};
            	int[][] formation = new int[][] {
            		{0,0,0,0,0,0,0,1,1,0,0,0,0},
            		{0,0,0,0,0,0,0,1,0,1,0,0,0},
            		{0,0,1,0,0,0,0,1,0,1,1,0,0},
            		{0,1,1,0,0,0,0,0,1,0,0,0,0},
            		{1,0,0,1,0,0,0,0,0,0,0,0,0},
            		{1,1,1,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,1,1,1},
            		{0,0,0,0,0,0,0,0,0,1,0,0,1},
            		{0,0,0,0,1,0,0,0,0,0,1,1,0},
            		{0,0,1,1,0,1,0,0,0,0,1,0,0},
            		{0,0,0,1,0,1,0,0,0,0,0,0,0},
            		{0,0,0,0,1,1,0,0,0,0,0,0,0}
            	};
            	
            	for(int i = 0; i < formation.length; i++) {
            		for(int j = 0; j < formation[0].length; j++) {
            			if(formation[i][j] == 1) {
            				cellList[topLeft[0]+i][topLeft[1]+j].revive();
            			}
            		}
            	}
            }
        });
        JButton e2 = new JButton("Examples 2");
        e2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(Cell[] column : cellList) {
        			for(Cell row : column) {
        				row.kill();
        			}
        		}
            	int[] topLeft = new int[]{22, 12};
            	int[][] formation = new int[][] {
            		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            		{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
            		{1,1,0,0,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,1,0,1,1,0,0,1,1,0,0,0,0,0,0,1,0,0,0},
            		{1,1,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0},
            		{0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,1,1,0},
            		{1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
            		{1,1,0,0,0,0,0,0,0,1,0,1,0,0,1,1,0,1,0,0,0,0,1,0,0,1,0,0,0,0,1,1,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0}
            	};
            	
            	for(int i = 0; i < formation.length; i++) {
            		for(int j = 0; j < formation[0].length; j++) {
            			if(formation[i][j] == 1) {
            				cellList[topLeft[0]+i][topLeft[1]+j].revive();
            			}
            		}
            	}
            }
        });
        JButton e3 = new JButton("Examples 3");
        e3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(Cell[] column : cellList) {
        			for(Cell row : column) {
        				row.kill();
        			}
        		}
            	int[] topLeft = new int[]{28, 28};
            	int[][] formation = new int[][] {
            		{1,1,1,0,1},
            		{1,0,0,0,0},
            		{0,0,0,1,1},
            		{0,1,1,0,1},
            		{1,0,1,0,1}
            	};
            	
            	for(int i = 0; i < formation.length; i++) {
            		for(int j = 0; j < formation[0].length; j++) {
            			if(formation[i][j] == 1) {
            				cellList[topLeft[0]+i][topLeft[1]+j].revive();
            			}
            		}
            	}            	
            }
        });
        JButton e4 = new JButton("Examples 4");
        e4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(Cell[] column : cellList) {
        			for(Cell row : column) {
        				row.kill();
        			}
        		}
            	int[] topLeft = new int[]{7, 26};
            	int[][] formation = new int[][] {
            		{0,0,1,1,0,0,0,0,0},
            		{0,0,1,1,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,1,1,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{1,1,0,0,0,1,1,0,0},
            		{0,1,1,1,1,1,0,0,0},
            		{0,1,1,0,1,1,0,0,0},
            		{0,1,1,0,1,1,0,0,0},
            		{0,0,1,1,1,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,1,1,1,0,0},
            		{0,0,0,0,1,1,1,0,0},
            		{0,0,0,1,0,0,0,1,0},
            		{0,0,1,0,0,0,0,0,1},
            		{0,0,0,1,0,0,0,1,0},
            		{0,0,0,0,1,1,1,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,0,0,0,0,0},
            		{0,0,0,0,1,1,0,0,0},
            		{0,0,0,0,1,1,0,0,0},
            		
            	};
            	
            	for(int i = 0; i < formation.length; i++) {
            		for(int j = 0; j < formation[0].length; j++) {
            			if(formation[i][j] == 1) {
            				cellList[topLeft[0]+i][topLeft[1]+j].revive();
            			}
            		}
            	}
            }
        });
        // copy and paste system
        boolean[][] storedGrid = new boolean[gridSize][gridSize];
        JPanel copyPastePanel = new JPanel(new GridLayout(2,1));
        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(int i = 0; i < gridSize; i++) {
                	for(int j = 0; j < gridSize; j++) {
                		if(cellList[i][j].getState()) {
                			storedGrid[i][j] = true;
                		} else {
                			storedGrid[i][j] = false;
                		}
                	}
        		}
            }
        });
        JButton pasteButton = new JButton("Paste");
        pasteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for(int i = 0; i < gridSize; i++) {
                	for(int j = 0; j < gridSize; j++) {
                		if(storedGrid[i][j]) {
                			cellList[i][j].revive();
                		} else {
                			cellList[i][j].kill();
                		}
                	}
        		}
            }
        });
        //button to pause/play a the game
        JButton pausePlay = new JButton("Play");
        pausePlay.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
            	pausePlayState = !pausePlayState;

            	if(pausePlayState) {
            		pausePlay.setText("Pause");
            		for(Cell[] column : cellList) {
            			for(Cell row : column) {
            				row.setEnabled(false);
            			}
            		}
            		if(once) {
            			t1.start();
            			once = false;
            		} else {
            			t1.resume();
            		}
            	} else {
            		pausePlay.setText("Play");
            		for(Cell[] column : cellList) {
            			for(Cell row : column) {
            				row.setEnabled(true);
            			}
            		}
            		t1.suspend();
            		//pausePlay.setEnabled(false);
            		
            	}
            	generation.setEnabled(!pausePlayState);
        		clearBoard.setEnabled(!pausePlayState);
        		fillBoard.setEnabled(!pausePlayState);
        		e1.setEnabled(!pausePlayState);
        		e2.setEnabled(!pausePlayState);
        		e3.setEnabled(!pausePlayState);
        		e4.setEnabled(!pausePlayState);
        		copyButton.setEnabled(!pausePlayState);
        		pasteButton.setEnabled(!pausePlayState);
            }
        });
        
        
        // set window settings
        setResizable(false);
        setSize(screenWidth, screenHeight + buttonRoom);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Conway's Game of Life");
        
        // adds everything together
        examplesPanel.add(e1);
        examplesPanel.add(e2);
        examplesPanel.add(e3);
        examplesPanel.add(e4);
        
        copyPastePanel.add(copyButton);
        copyPastePanel.add(pasteButton);
        
        buttonPanel.add(pausePlay);
        buttonPanel.add(generation);
        buttonPanel.add(clearBoard);
        buttonPanel.add(fillBoard);
        buttonPanel.add(examplesPanel);
        buttonPanel.add(copyPastePanel);
        
        mainPanel.add(gridPanel);
        mainPanel.add(buttonPanel);
        
        add(mainPanel);
	}
	
	// overrides a method to add lines for a grid look
	public void paint(Graphics g) {
        super.paint(g);

        int x1 = 45;
        int y1 = 31;
        int x2 = 45;
        int y2 = 31;
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        for(int i = 0; i < 2; i++) {
        	for(int j = 0; j <= gridSize; j++) {
        		if(i == 0) {
        			g2.draw(new Line2D.Float(
                    		x1,
                    		y1 + (12 * j),
                    		x2 + 660,
                    		y2 + (12 * j)));
        		} else if(i == 1) {
        			g2.draw(new Line2D.Float(
                    		x1 + (11 * j),
                    		y1 + 1,
                    		x2 + (11 * j),
                    		y2 + 720));
        		}
        	}
        }
    }

	public void nextGeneration() {
		// true == alive | false == dead
		boolean[][] newGen = new boolean[gridSize][gridSize];
		for(int i = 0; i < gridSize; i++) {
        	for(int j = 0; j < gridSize; j++) {
        		int num = -1;
        		if(cellList[i][j].getState()) {
        			for(int o = -1; o < 2; o++) {
        				for(int k = -1; k < 2; k++) {
		        			try {
		        				if(cellList[i+o][j+k].getState()) {
			        				num++;
		        				}
		        			} catch(ArrayIndexOutOfBoundsException e) {
		        				//System.out.println("out of bounds");
		        			}
	        			}
        			}
        			if(num == 2 || num == 3) {
        				newGen[i][j] = true;
        			} else {
        				newGen[i][j] = false;
        			}
        		} else {
        			num++;
        			for(int o = -1; o < 2; o++) {
        				for(int k = -1; k < 2; k++) {
		        			try {
		        				if(cellList[i+o][j+k].getState()) {
			        				num++;
		        				}
		        			} catch(ArrayIndexOutOfBoundsException e) {
		        				//System.out.println("out of bounds");
		        			}
	        			}
        			}
        			if(num == 3) {
        				newGen[i][j] = true;
        			} else {
        				newGen[i][j] = false;
        			}
        		}
        	}
        }
		for(int i = 0; i < gridSize; i++) {
        	for(int j = 0; j < gridSize; j++) {
        		if(newGen[i][j]) {
        			cellList[i][j].revive();
        		} else {
        			cellList[i][j].kill();
        		}
        	}
		}
	}
	
	
	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();
        game.setVisible(true);
	}
}