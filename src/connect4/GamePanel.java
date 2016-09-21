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

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GamePanel extends JPanel
{
    private static final Dimension panelSize = new Dimension(500,500);

    private static int discSize;
    private static int axisY;
    private final Color brown = new Color(110, 55, 15);
    
    private final Connect4Board board;
    
    private final Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
     
    public GamePanel(Connect4Board board)
    {   
        setBackground(brown);
        
        this.board = board;
        
        discSize = panelSize.width/board.getColumns();
        axisY = panelSize.height - discSize;
        
    }
    
    //Paint/Create the corresponding board panel
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBorder(border);
        //g.drawImage(background, 0,0, getWidth(), getHeight(), null);
        
        int bRows = board.getRows();
        int bColumns = board.getColumns();
        
        //Copy board
        String boardGame[][] = board.getBoard();
        
        for(int c = 0; c < bColumns; c++)
        {
            for(int r = 0; r < bRows; r++)
            {
                if(boardGame[c][r] == null)
                {
                    g.setColor(Color.gray);
                    g.fillOval(discSize * c,axisY - (discSize * r), discSize, discSize);
                }
                else
                {
                    if(boardGame[c][r].equals("Red"))
                    {
                        g.setColor(Color.RED);
                    }
                    else
                    {
                        g.setColor(Color.BLACK);
                    }
                    
                    g.fillOval(discSize * c, axisY - (discSize * r), discSize, discSize);
                }
            }
        }
        
    }
    
    //Change background to green
    public void changeToGreen()
    {
        setBackground(Color.GREEN);
    }
    
    //Change background to blue
    public void changeToBlue()
    {
        setBackground(Color.BLUE);
    }
    
    //Change background to magenta
    public void changeToMagenta()
    {
        setBackground(Color.MAGENTA);
    }
    
    //Change background to orange
    public void changeToOrange()
    {
        setBackground(Color.ORANGE);
    }
    
    public void changeToDefault()
    {
        setBackground(brown);
    }
   

    @Override
    public Dimension getPreferredSize() 
    {
        return panelSize;
    }
    
    @Override
    public void setPreferredSize(Dimension d) {}
    
    @Override
    public Dimension getMinimumSize() 
    {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() 
    {
        return getPreferredSize();
    }
}

    /*
    public void createBackground()
    {
        try{
            Image temp = ImageIO.read(getClass().getResource("wdf.jpg"));
            background = temp.getScaledInstance(temp.getWidth(null) * 2, temp.getHeight(null) * 2, Image.SCALE_SMOOTH);
            
            drawImage(temp, 0, 0, getWidth(), getHeight(), null);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    */
    