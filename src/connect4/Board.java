/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

/**
 *
 * @author Kiyeon
 * Kyle Del Castillo
 * 009445384
 * CS 151 - Object Oriented Design
 * 
 */

public interface Board 
{
    String[][] getBoard();
    
    int getColumns();
    
    int getRows();
    
    boolean canInsert(int column);
    
    void putDisk(int column, String color);
    
    String getWinner();
    
    boolean checkColumns();
    
    void reset();
    
    void reinitializeBoard();
    
    boolean isFull();
}
