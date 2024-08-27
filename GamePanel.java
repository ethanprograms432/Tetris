import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements Runnable {

   Thread gameThread;
   PieceManager pM = new PieceManager(this);
   KeyManager keyH = new KeyManager();
   public final int width = 500;
   public final int height = 1000;
   public final int tileSize = 50;
   public static int score = 0;
   public static boolean gameOver = false;
   
   public GamePanel() {
   
      this.setPreferredSize(new Dimension(500,1000));
      this.setFocusable(true);
      this.addKeyListener(keyH);
   
   }
   
   public void startGameThread() {
   
      gameThread = new Thread(this);
      gameThread.start();
   
   }
   
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
   
   public void drawScore(Graphics g) {
   
      g.setColor(Color.white);
      g.setFont(new Font("TimesRoman",Font.BOLD,50));
      g.drawString(Integer.toString(score),230,50);
   
   }
   

}