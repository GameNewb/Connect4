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

import java.util.Observable;
import javax.swing.JOptionPane;

public class Connect4Board extends Observable implements Board
{
    private String gameBoard[][];
    private String winner;
    private static int boardRows;
    private static int boardColumns;
    private static int connectionLength;
   
    public Connect4Board()
    {
        //Let Connect4Board class handle creation of board
        getUserInput();
        if(boardRows > 20 || boardColumns > 20 || boardRows < 4 || boardColumns < 4)
        {
            JOptionPane.showMessageDialog(null, "Invalid rows or column. It must be greater than 4 and less than 20.");
        }
        else if(connectionLength > boardRows || connectionLength > boardColumns || connectionLength < 3)
        {
            JOptionPane.showMessageDialog(null, "Invalid connection length. It must not be greater than the columns or rows, and it must be greater than 3.");
        }
        else //Invalid input leads to no creation of board
        {   
            initializeBoard();
            Controller controller = new Controller(this);
            GUI gui = new GUI(controller);
        }
        
    }
    
    public Connect4Board(int row, int column, int length)
    {
        boardRows = row;
        boardColumns = column;
        connectionLength = length;
        
        if(boardRows > 20 || boardColumns > 20 || boardRows < 4 || boardColumns < 4)
        {
            JOptionPane.showMessageDialog(null, "Invalid rows or column. It must be greater than 4 and less than 20.");
        }
        else if(connectionLength > boardRows || connectionLength > boardColumns || connectionLength < 3)
        {
            JOptionPane.showMessageDialog(null, "Invalid connection length. It must not be greater than the columns or rows, and it must be greater than 3.");
        }
        else //Invalid input leads to no creation of board
        {   
            initializeBoard();
            Controller controller = new Controller(this);
            GUI gui = new GUI(controller);
        }
        
    }
    
    //Obtain user input
    public void getUserInput()
    {
        String height = JOptionPane.showInputDialog("Enter the board rows: ");
        String width = JOptionPane.showInputDialog("Enter the board columns: ");
        String length = JOptionPane.showInputDialog("Please enter the connection length: ");
        
        boardRows = Integer.parseInt(height); 
        boardColumns = Integer.parseInt(width);
        connectionLength = Integer.parseInt(length);
    }
    
    //Initialize board
    public final void initializeBoard()
    {
        winner = null;
        gameBoard = new String[boardColumns][boardRows];
        
        //Generates an empty board based on user input
        for(int i = 0; i < boardColumns; i++)
        {
            for(int j = 0; j < boardRows; j++)
            {
                gameBoard[i][j] = null;
            }
        }
        
        setChanged();
        notifyObservers();
    }
    
    @Override
    public final void reinitializeBoard()
    {
        getUserInput();
        if(boardRows > 20 || boardColumns > 20 || boardRows < 4 || boardColumns < 4)
        {
            JOptionPane.showMessageDialog(null, "Invalid rows or column. It must be greater than 4 and less than 20.");
        }
        else if(connectionLength > boardRows || connectionLength > boardColumns || connectionLength < 3)
        {
            JOptionPane.showMessageDialog(null, "Invalid connection length. It must not be greater than the columns or rows, and it must be greater than 3.");
        }
        else //Invalid input leads to no creation of board
        {   
            initializeBoard();
        }
    }
    
    //Returns the rows of the board
    @Override
    public int getRows()
    {
        return boardRows;
    }
    
    //Returns the columns of the board
    @Override
    public int getColumns()
    {
        return boardColumns;
    }
    
    //Returns the board
    @Override
    public String[][] getBoard()
    {
        return gameBoard;
    }
    
    //Returns the winner of the game
    @Override
    public String getWinner()
    {
        return winner;
    }
    
    //Check to see if there's still space to insert disk
    @Override
    public boolean canInsert(int column)
    {
        if(gameBoard[column][boardRows - 1] == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Insert disk in proper column and row
    @Override
    public void putDisk(int column, String color)
    {
        //Base case, if there is a winner, just return
        if(winner != null)
        {
            return;
        }
        
        //While there's an empty slot
        int row = 0;
        while(gameBoard[column][row] != null)
        {
            row++;
        }
        
        //Put disk to corresponding column/row
        gameBoard[column][row] = color;
        
        if(isWinner(color, column, row))
        {
            winner = color;
        }
        else
        {
            winner = null;
        }
        
        //Let the observer know
        setChanged();
        notifyObservers();
        
    }
    
    @Override
    public boolean isFull()
    {
        for(int i = 0; i < boardColumns; i++)
        {
            if(canInsert(i))
            {
                return false;
            }
        }
        return true;
    }
    
    //Checks to see if there's N disks in every direction
    public boolean checkNLength(String color, int column, int row, int x, int y)
    {
        int xRows = row;
        int yColumns = column;
        
        int sum = 0;
        
        //While there's a row/column, until it reaches max rows/column, and as long as the colors are the same
        while(xRows >= 0 //While there's a row
                && xRows < boardRows 
                && yColumns >= 0 
                && yColumns < boardColumns 
                && gameBoard[yColumns][xRows] != null 
                && gameBoard[yColumns][xRows].equals(color))
        {
            xRows += x; //Add to xRows
            yColumns += y; //Add to yColumns
            
            sum++; //Add to total
        }
        
        xRows = row - x;
        yColumns = column - y;
        
        while(xRows >= 0 //While there's a row
                && xRows < boardRows 
                && yColumns >= 0 
                && yColumns < boardColumns 
                && gameBoard[yColumns][xRows] != null 
                && gameBoard[yColumns][xRows].equals(color))
        {
            xRows -= x; //Subtract to xRows
            yColumns -= y; //Subtract to yColumns
            
            sum++; //Add to total
        }
        
        if(sum >= connectionLength)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    //Call checkNLength 4 times for each possible win
    public boolean isWinner(String color, int column, int row)
    {
        return( checkNLength(color, column, row, 0, 1) ||
                checkNLength(color, column, row, 1, 0) ||
                checkNLength(color, column, row, 1, 1) ||
                checkNLength(color, column, row, -1, 1));
    }
    
    //Checks to see if the columns are full
    @Override
    public boolean checkColumns()
    {
        for(int i = 0; i < boardColumns; i++)
        {
            //If the space is full, return false
            if(canInsert(i))
            {
                return false;
            }
        }
        
        return true;
    } //End checkColumns
   
    //Reset the board
    @Override
    public void reset()
    {
        initializeBoard();
    }
    
    //Clears the board
    public void clearBoard()
    {
        for(int i = 0; i < boardColumns; i++)
        {
            for(int j = 0; j < boardRows; j++)
            {
                gameBoard[i][j] = null;
            }
        }
        
        winner = null;
        setChanged();
        notifyObservers();
    }
    
    // Changes length needed to win
    public void newLength()
    {
        String length = JOptionPane.showInputDialog("Please enter the connection length: ");
        
        //Used to retain previous connection length if the new one isn't valid
        int previousLength = connectionLength; 
        
        connectionLength = Integer.parseInt(length);
        
        if(connectionLength > boardRows || connectionLength > boardColumns || connectionLength < 3)
        {
            JOptionPane.showMessageDialog(null, "Invalid connection length. It must not be greater than the columns or rows, and it must be greater than 3.");
            connectionLength = previousLength;
            JOptionPane.showMessageDialog(null, "Connection length is still " + connectionLength);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Connection length has been set to " + connectionLength);
        }
        
    }
    
} //End Connect4Board