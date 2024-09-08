import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
/** A class that manages all properties
* and methods of piece objects */

public class Piece {

   private int pieceType;
   public int piecesRemaining;
   public int xPos;
   public int yPos;
   public int moveCounter = 0;
   public Color c;
   public boolean collided;
   public ArrayList<String> takenCoordinates = new ArrayList<String>();
   public ArrayList<Integer> deadPieces = new ArrayList<Integer>();
   
   /** constructor for piece object with their piece type (direction faced),
   * upmost y coordinate, and leftmost x coordinate */
   public Piece(int pieceType,int xPos,int yPos) {
   
      this.pieceType = pieceType;
      this.xPos = xPos;
      this.yPos = yPos;
      this.updateCoordinates(this.xPos,this.yPos);
   
   }
   
   /** accessor method for the pieces taken coordinates */
   public ArrayList<String> getCoords() {
   
      return this.takenCoordinates;
   
   }
   
   /** A method that removes all neccessary blocks from
   * pieces upon the clearance of a line
   @param lineRemoved The row that was cleared
   */
   public void clearPiecesFromLine(int lineRemoved) {
   

      for (int i = 0; i < this.takenCoordinates.size(); i++) {
      
         String currPoint = takenCoordinates.get(i);
         int X = Integer.valueOf(currPoint.substring(0,currPoint.indexOf(",")));
         int Y = Integer.valueOf(currPoint.substring(currPoint.indexOf(",") + 1));
            
         if(Y == lineRemoved) {
         
            this.takenCoordinates.remove(currPoint);
            System.out.println(currPoint);
            
            PieceManager.map[Y-1][X-1] = null;
            
         }
      
      }
      
      int belowCounter = 1;
      
      while(belowCounter != 0) {
      
         belowCounter = 0;
         for (int j = 0; j < this.takenCoordinates.size(); j++) {
         
            String currPoint = takenCoordinates.get(j);
            int X = Integer.valueOf(currPoint.substring(0,currPoint.indexOf(",")));
            int Y = Integer.valueOf(currPoint.substring(currPoint.indexOf(",") + 1));
            
            if(Y < lineRemoved) {
            
               if(PieceManager.map[Y][X-1] == null) {
               
                  this.takenCoordinates.set(j,(X + "," + (Y + 1)));
                  PieceManager.map[Y][X-1] = "Piece";
                  PieceManager.map[Y-1][X-1] = null;
                  belowCounter++;
                  
               }
            
            }
         
         }
         
     }
   
   }
   
   /** A method that updates the pieces taken coordinates based
   * on its movement
   * @param xPos The current x position of the piece (the leftmost block of the piece)
   * @param yPos The current y position of the piece (the upmost block of the piece)
   */
   public void updateCoordinates(int xPos,int yPos) {
   
      
      for (int i = 0; i < takenCoordinates.size(); i++) {
      
        String coord = takenCoordinates.get(i);
        int X = Integer.valueOf(coord.substring(0,coord.indexOf(",")));
        int Y = Integer.valueOf(coord.substring(coord.indexOf(",") + 1));
        PieceManager.map[Y-1][X-1] = null;
      
      }
      
      takenCoordinates.clear();
      
      
      switch(pieceType) {
      
         case(1):
         
            this.c = (Color.blue);
            takenCoordinates.add((xPos + 1) + "," + yPos);
            takenCoordinates.add((xPos) + "," + (yPos - 2));
            takenCoordinates.add((xPos + 1) + "," + (yPos - 1));
            takenCoordinates.add((xPos + 1) + "," + (yPos - 2));
            takenCoordinates.add((xPos + 2) + "," + (yPos - 2));
            
            break;
            
         case(2):
         
            this.c = (Color.red);
            takenCoordinates.add(xPos + "," + yPos);
            takenCoordinates.add(xPos + "," + (yPos - 1));
            takenCoordinates.add(xPos + "," + (yPos - 2));
            takenCoordinates.add((xPos + 1) + "," + (yPos - 1));
            takenCoordinates.add((xPos + 2) + "," + (yPos - 1));
            
            break;
            
         case(3):
         
            this.c = (Color.yellow);
            takenCoordinates.add(xPos + "," + yPos);
            takenCoordinates.add((xPos + 1) + "," + yPos);
            takenCoordinates.add((xPos + 2) + "," + yPos);
            takenCoordinates.add((xPos + 1) + "," + (yPos - 1));
            takenCoordinates.add((xPos + 1) + "," + (yPos - 2));

            break;
            
         case(4):
         
            this.c = (Color.green);
            takenCoordinates.add(xPos + "," + (yPos - 1));
            takenCoordinates.add((xPos + 1) + "," + (yPos - 1));
            takenCoordinates.add((xPos + 2) + "," + (yPos - 1));
            takenCoordinates.add((xPos + 2) + "," + (yPos));
            takenCoordinates.add((xPos + 2) + "," + (yPos - 2));
            
            break;
      
      }
      
      for (int i = 0; i < takenCoordinates.size(); i++) {
      
         String coord = takenCoordinates.get(i);
         int X = Integer.valueOf(coord.substring(0,coord.indexOf(",")));
         int Y = Integer.valueOf(coord.substring(coord.indexOf(",") + 1));
         PieceManager.map[Y-1][X-1] = "Piece";
      
      }
   
   }
   
   
   


}