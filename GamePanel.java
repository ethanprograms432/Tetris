import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
/**
* Class that manages the visual displays of the Tetris
* game along with the game loop
*/

public class GamePanel extends JPanel implements Runnable {

   Thread gameThread;
   PieceManager pM = new PieceManager(this);
   KeyManager keyH = new KeyManager();
   public final int width = 500;
   public final int height = 1000;
   public final int tileSize = 50;
   public static int score = 0;
   public static boolean gameOver = false;
   
   /** constructor that sets the panel size, and
   adds all necessary event listeners */
   public GamePanel() {
   
      this.setPreferredSize(new Dimension(500,1000));
      this.setFocusable(true);
      this.addKeyListener(keyH);
   
   }
   
   /** method that initialises the game thread
   * which will start the run() method, and
   commence the game loop */
   
   public void startGameThread() {
   
      gameThread = new Thread(this);
      gameThread.start();
   
   }
   
   /**
   * Overriden method from Runnable interface that
   * handles the game loop by updating game data, and
   * visuals every 5 milliseconds until the Thread
   * is null */
   @Override
   public void run() {
   
      while(gameThread != null && gameOver == false) {
      
         try {
         
            TimeUnit.MILLISECONDS.sleep(5);
            pM.movePiece();
            repaint();
            
            
         } catch(InterruptedException e) {
         
            System.out.println("Time not working");
         
         }
         
      }
      
      repaint();
   
   }
   
   /** Method that draws all necessary components
   * onto the panel by drawing itself, and passing
   * the graphics tool (g) to other methods */
   
   public void paintComponent(Graphics g) {
   
      super.paintComponent(g);
      
      if(gameOver == false) {
      
         g.setColor(Color.black);
         g.fillRect(0,0,width,height);
         pM.drawPieces(g);
         drawScore(g);
         
      } else {
      
         g.setColor(Color.black);
         g.fillRect(0,0,width,height);
         g.setColor(Color.red);
         g.setFont(new Font("TimesRoman",Font.BOLD,25));
         g.drawString("Game Over!",20,450);
         g.drawString("Final Score: " + score,20,475);
         
      }
   
   }
   
   /**
   * Method that draws the score at the top of the screen
   * for the user to see */
   public void drawScore(Graphics g) {
   
      g.setColor(Color.white);
      g.setFont(new Font("TimesRoman",Font.BOLD,50));
      g.drawString(Integer.toString(score),230,50);
   
   }
   

}