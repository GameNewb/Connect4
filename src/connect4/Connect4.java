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

public class Connect4 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        createConnectNGame(args);
    }
    
    public static void createConnectNGame(String[] args)
    {
        if(args.length == 0) //No user input in command line
        {
            Connect4Board board = new Connect4Board();
        }
        else //With user input in command line
        {
            int boardRow = Integer.parseInt(args[0]);
            int boardColumn = Integer.parseInt(args[0]);

            int conLength = Integer.parseInt(args[1]);

            if(boardRow > 20 || boardColumn > 20 || boardRow < 4 || boardColumn < 4)
            {
                System.out.println( "Invalid rows or column. It must be greater than 4 and less than 20.");
            }
            else if(conLength > boardRow || conLength > boardColumn || conLength < 3)
            {
                System.out.println("Invalid connection length. It must not be greater than the columns or rows, and it must be greater than 3.");
            }
            else 
            {
                System.out.println("Creating board of size " + args[0] + "X" + args[0] + " and a connection of " + args[1]);
                Connect4Board board = new Connect4Board(boardRow, boardColumn, conLength);
            }
        
        }
    }
    
    
} //End Connect4 Class


