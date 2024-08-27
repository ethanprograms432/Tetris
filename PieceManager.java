import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

public class PieceManager {

   public static ArrayList<Piece> pieces = new ArrayList<Piece>();
   public static String[][] map = new String[20][10];
   static GamePanel gp;
   public static Random rand = new Random();
   
   public PieceManager(GamePanel gp) {
   
      this.gp = gp;
      generatePiece();
   
   }
   
   public static void generatePiece() {
   
      int type = rand.nextInt(1,5);
      int X = rand.nextInt(3,7);
      pieces.add(new Piece(type,X,3));
   
   }
   
   public static void checkIfLineCleared() {
   
      for (int i = 0; i < map.length; i++) {
      
         int fillCounter = 0;
         
         for (int j = 0; j < map[i].length; j++) {
         
            if(map[i][j] != null) {
            
               fillCounter++;
               
            }
         
         }
         
         if(fillCounter == 10) {
         
            gp.score += 300;
            
            for (Piece p: pieces) {
            
               p.clearPiecesFromLine(i + 1);
            
            }
         
         }
      
      }
   
   }
   
   
   
   public static void movePiece() {
   
      try {
      
         TimeUnit.MILLISECONDS.sleep(750);
         Piece p = pieces.get(pieces.size() - 1);
         
         boolean c = checkIfCollided(p,"down");
         
         if(c == false) {
         
            p.yPos = (p.yPos + 1);
            p.updateCoordinates(p.xPos,p.yPos);
            p.moveCounter += 1;
            
         } else {
         
            if(p.moveCounter != 0) {
            
               checkIfLineCleared();
               generatePiece();
               
            } else {
            
               gp.gameOver = true;
               
            }
            
         }
         gp.score += 10;
      
      } catch(InterruptedException e) {
      
         e.printStackTrace();
      }
   
   }
   
   public static void movePieceLeft() {
   
      Piece p = pieces.get(pieces.size() - 1);
      
      
      if(p.xPos != 1) {

         boolean c = checkIfCollided(p,"left");
         System.out.println(c);
         
         if(c == false) {
            
            p.xPos -= 1;
            p.updateCoordinates(p.xPos,p.yPos);
            
         }
         
      }
   
   }
   
   public static void movePieceRight() {
   
      Piece p = pieces.get(pieces.size() - 1);
      
   
      if(p.xPos != 8) {
      
         boolean c = checkIfCollided(p,"right");
         System.out.println(c);
         if(c == false) {
         
            p.xPos += 1;
            p.updateCoordinates(p.xPos,p.yPos);
            
         }
         
      }
   
   }
   
   public static void movePieceDown() {
   
      Piece p = pieces.get(pieces.size() - 1);
      
      boolean c = checkIfCollided(p,"down");
         
      if(c == false) {
         
         p.yPos = (p.yPos + 1);
         p.updateCoordinates(p.xPos,p.yPos);
         p.moveCounter += 1;
            
      }
      gp.score += 20;
   
   }
   
   public static void spaceFeature() {
   
      Piece p = pieces.get(pieces.size() - 1);
      
      boolean c = false;
      
      while(c == false) {
      
         c = checkIfCollided(p,"down");
         
         if(c == false) {
               
            p.yPos = (p.yPos + 1);
            p.updateCoordinates(p.xPos,p.yPos);
            p.moveCounter += 1;
                  
         }
      
      }
      gp.score += 40;
   
   }
   
   public static boolean checkIfCollided(Piece p,String command) {
   
      ArrayList<String> coords = p.getCoords();
      boolean collided = false;
      
      for (int i = 0; i < coords.size(); i++) {
      
         String coord = coords.get(i);
         int X = Integer.valueOf(coord.substring(0,coord.indexOf(",")));
         int Y = Integer.valueOf(coord.substring(coord.indexOf(",") + 1));
         int yCheck = Y + 1;
         int leftX = X - 1;
         int rightX = X + 1;
       
         if(command.equals("down")) {
         
            if(yCheck > 20) {
            
               collided = true;
            
            } else if(map[yCheck-1][X-1] != null) {
            
               boolean ownSpot = checkIfOwnSpot(X,yCheck,coords);
               
               if(ownSpot == false) {
               
                  collided = true;
                  
               }
               
            
            }
         
         } else if(command.equals("left")) {
         
            if(map[Y-1][leftX - 1] != null) {
            
               boolean ownSpot = checkIfOwnSpot(leftX,Y,coords);
               
               if(ownSpot == false) {
               
                  collided = true;
               
               }
            
            }
         
         } else if(command.equals("right")) {
         
            if(map[Y-1][rightX - 1] != null) {
            
               boolean ownSpot = checkIfOwnSpot(rightX,Y,coords);
               
               if(ownSpot == false) {
               
                  collided = true;
               
               }
            
            }
         
         } 
      
      }
      return collided;
   
   }
   
   public static boolean checkIfOwnSpot(int X,int Y,ArrayList<String> coords) {
   
      boolean ownSpot = false;
      
      for (int i = 0; i < coords.size(); i++) {
      
         String coord = coords.get(i);
         int x = Integer.valueOf(coord.substring(0,coord.indexOf(",")));
         int y = Integer.valueOf(coord.substring(coord.indexOf(",") + 1));
         
         if((X == x) && (Y == y)) {
         
            ownSpot = true;
            
         } 
      
      }
      return ownSpot;
   
   }
   
   public void drawPieces(Graphics g) {
   
      for (int i = 0; i < pieces.size(); i++) {
      
         Piece p = pieces.get(i);
         ArrayList<String> coords = p.getCoords();
         
         for (int k = 0; k < coords.size(); k++) {
         
            String coord = coords.get(k);
            int X = Integer.valueOf(coord.substring(0,coord.indexOf(",")));
            int Y = Integer.valueOf(coord.substring(coord.indexOf(",") + 1));
            
            
            int startX = ((X-1)*gp.tileSize);
            int startY = ((Y-1)*gp.tileSize);
            g.setColor(p.c);
            g.fillRect(startX,startY,gp.tileSize,gp.tileSize);
         
         }
      
      }
   
   }

}