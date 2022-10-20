package form;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/*
 * This window handles clicks, mouse events, timers and other window configuration
 */
public class MainWindow extends JFrame implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private GameManager game;
	private boolean decorate = true;
	
	/* To learn about delta time
	    - https://dev.to/dsaghliani/understanding-delta-time-in-games-3olf
	    - https://gafferongames.com/post/fix_your_timestep/
	    - https://stackoverflow.com/questions/26838286/delta-time-getting-60-updates-a-second-in-java
	 */
	
	//private final double UPS_FPS = 60D;
	private final double UPS_FPS = 5D;
	
	private final double timeFpsUps = 1000000000 / UPS_FPS; // nanoseconds
	
	private double deltaFpsUps = 0;
	private long initialTime = System.nanoTime();

    public MainWindow() {
    	// #1: create instance of the game
    	this.game = new GameManager();

    	// #2: Define window
        this.setUndecorated(decorate);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.pack();
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.getContentPane().add(game);

        // #3: Add listeners
        this.addKeyListener(this);
        
        new Thread(this).start();
    }

	// Delta time
	@Override
	public void run() {
		while(true) {

			long currentTime = System.nanoTime();
	        this.deltaFpsUps += (currentTime - this.initialTime) / this.timeFpsUps;

	        this.initialTime = currentTime;

	        // UPS & FPS
		    if(this.deltaFpsUps >= 1){
		    	this.game.tick();
		    	
                this.deltaFpsUps = 0;
		    }
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.updateDirection(e);
	}
}
