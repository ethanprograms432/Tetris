import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyManager implements KeyListener {

   @Override
   public void keyPressed(KeyEvent e) {
   
      int code = e.getKeyCode();
      
      if(code == 37) {
      
         PieceManager.movePieceLeft();
      
      }
      
      
      if(code == 39) {
      
         PieceManager.movePieceRight();
      
      }
      
      if(code == 40) {
      
         PieceManager.movePieceDown();
      
      }
      
      if(code == 32) {
      
         PieceManager.spaceFeature();
         
      }
      
   }
   
   @Override
   public void keyReleased(KeyEvent e) {
   
   }
   
   @Override
   public void keyTyped(KeyEvent e) {
   
   }

}