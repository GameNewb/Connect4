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

public class Controller 
{
    private final Connect4Board board;
    
    private final String player1Color;
    private final String player2Color;
    
    private String currentPlayer;
    private String previousPlayer;
       
    private int redScore = 0;
    private int blackScore = 0;
    
    
    public Controller(Connect4Board board) 
    {
        this.board = board;
        
        player1Color = "Black";
        
        if(player1Color.equals("Black"))
        {
            player2Color = "Red";
        }
        else
        {
            player2Color = "Black";
        }
       
        currentPlayer = "Red";//needs to be random
        previousPlayer = currentPlayer;
        
    }
    
    //Get Red Score
    public int getRedScore() 
    {
        return redScore;
    }
    
    //Sets score for Red
    public void setRedScore(int redScore) 
    {
        this.redScore = redScore;
    }
    
    //Get Black Score
    public int getBlackScore() 
    {
        return blackScore;
    }
    
    //Sets score for Black
    public void setBlackScore(int blackScore) 
    {
        this.blackScore = blackScore;
    }
    
    //Get Columns
    public int getColumns() 
    {
        return board.getColumns();
    }
    
    //Get Rows
    public int getRows() 
    {
        return board.getRows();
    }
    
    //Get player 1's color
    public String getPlayer1Color() 
    {
        return player1Color;
    }
    
    //Get player 2's color
    public String getPlayer2Color() 
    {
        return player2Color;
    }
    
    //Get winner
    public String getWinner() 
    {
        return board.getWinner();
    }
    
    //Get board
    public Connect4Board getBoard() 
    {
        return board;
    }
    
    //Show current player
    public String getCurrentPlayer() 
    {
        return currentPlayer;
    }
      
    //Check if it's possible to insert
    public boolean canInsert(int column) 
    {
        return board.canInsert(column);
    }
    
    //Check if board is full
    public boolean isFull() 
    {
        return board.isFull();
    }
    
    //Create new game with same board size
    public void newGame() 
    {
        if(previousPlayer.equals("Black"))
        {
            currentPlayer = "Red";
        }
        else
        {
            currentPlayer = "Black";
        }
        
        previousPlayer=currentPlayer;
        board.reset();
    }
    
    //End current game
    public void endGame() 
    {
        currentPlayer = null;
        board.clearBoard();
    }
    
    //Reinitialize board size and length
    public void reinitialize()
    {
        board.reinitializeBoard();
    }
    
    //Turn base
    public void move(int column, String color) 
    {
        if(color == null)
        {
            return;
        }
        
        if (canInsert(column) && color.equals(currentPlayer) && getWinner() == null) 
        {
            if(currentPlayer.equals("Black"))
            {
                currentPlayer = "Red";
            }
            else
            {
                currentPlayer = "Black";
            }
            
            board.putDisk(column,color);
        }
    } //End move
    
    //Initialize new connection length
    public void newLength()
    {
        board.newLength();
    }
}

