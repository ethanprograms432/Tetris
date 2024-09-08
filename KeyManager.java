import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/** Class that listens for all key
* user inputs required to alter the
* visual display of the Tetris game */

public class KeyManager implements KeyListener {

   /** method that controls what is done upon various key presses */
   @Override
   public void keyPressed(KeyEvent e) {
   
      int code = e.getKeyCode();
      
      // left arrow key
      if(code == 37) {
      
         PieceManager.movePieceLeft();
      
      }
      
      // right arrow key
      if(code == 39) {
      
         PieceManager.movePieceRight();
      
      }
      
      
      // down arrow key
      if(code == 40) {
      
         PieceManager.movePieceDown();
      
      }
      
      // space key
      if(code == 32) {
      
         PieceManager.spaceFeature();
         
      }
      
   }
   
   /** method that controls what to do upon key release */
   @Override
   public void keyReleased(KeyEvent e) {
   
   }
   
   /** method that controls what to do upon key press, and release */
   @Override
   public void keyTyped(KeyEvent e) {
   
   }

}