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
import java.awt.event.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import javax.swing.border.Border;

public class GUI extends JFrame implements Observer, MouseListener, ActionListener
{
    private static final Dimension panelSize = new Dimension(500,500);
    private static int discSize;
    
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu file = new JMenu("File");
    private final JMenu bg = new JMenu("Background");
    private final JMenu options = new JMenu("Options");
    
    private final JMenuItem newGame = new JMenuItem("New Game");
    private final JMenuItem endGame = new JMenuItem("End Game");
    private final JMenuItem exitGame = new JMenuItem("Exit Game");
    
    private final JMenuItem bgGreen = new JMenuItem("Green Background");
    private final JMenuItem bgBlue = new JMenuItem("Blue Background");
    private final JMenuItem bgMagenta = new JMenuItem("Magenta Background");
    private final JMenuItem bgOrange = new JMenuItem("Orange Background");
    private final JMenuItem bgDefault = new JMenuItem("Default Background");
    
    private final JMenuItem newBoard = new JMenuItem("Reinitialize Board Size");
    private final JMenuItem newConLength = new JMenuItem("New Connection Length");
    private final JMenuItem resetScores = new JMenuItem("Reset Scores");
    
    private final JTextField alertField = new JTextField(30);
    private JTextField redScoreField;
    private JTextField blackScoreField;
    
    private JLabel redScoreLabel;
    private JLabel blackScoreLabel;
    
    private JFrame gameFrame;
    
    private GamePanel gamePanel;
    private JPanel menuPanel;
    
    private final Border alertBorder = BorderFactory.createBevelBorder(0, Color.BLACK, Color.gray);
    private final Border scoreLabelBorder = BorderFactory.createBevelBorder(0, Color.BLACK, Color.gray);
    //private final Border scoreTextBorder = BorderFactory.createDashedBorder(Color.BLACK, 4, 5);
    
    private final Color brown = new Color(110, 55, 15);
    
    private final Controller controller;
    
    public GUI(Controller controller)
    {
        //Set disc size dependent on the number of columns
        discSize = panelSize.width/controller.getColumns();
        this.controller = controller;
        
        controller.getBoard().addObserver(GUI.this);
        createControlFrame();
        update(controller.getBoard(), null);
    }
    
    //Creates the Menu Frame for the board
    private void createControlFrame() 
    {
        gameFrame = new JFrame("Connect Four Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        //Menu Bars
        gameFrame.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(bg);
        menuBar.add(options);
        
        //Menu Items
        newGame.addActionListener(GUI.this);
        endGame.addActionListener(GUI.this);
        exitGame.addActionListener(GUI.this);
        
        //Background Items
        bgGreen.addActionListener(GUI.this);
        bgBlue.addActionListener(GUI.this);
        bgMagenta.addActionListener(GUI.this);
        bgOrange.addActionListener(GUI.this);
        bgDefault.addActionListener(GUI.this);
        
        //Option Items
        newBoard.addActionListener(GUI.this);
        newConLength.addActionListener(GUI.this);
        resetScores.addActionListener(GUI.this);
        
        Container contentPane = gameFrame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); //Top to bottom
        
        //Alert fields
        alertField.setHorizontalAlignment(JTextField.TRAILING);
        alertField.setEditable(false);
        alertField.setHorizontalAlignment(JTextField.CENTER);
        alertField.setBorder(alertBorder);
        alertField.setBackground(brown);
        
        updateAlertField(controller.getCurrentPlayer() + " goes first");
        contentPane.add(alertField); //Add alert field to the content pane
        
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2,2));
        
        //Title labels
        redScoreLabel = new JLabel("Red Score: ");
        redScoreLabel.setBackground(Color.RED);
        redScoreLabel.setBorder(scoreLabelBorder);
        redScoreLabel.setOpaque(true);
        blackScoreLabel = new JLabel("Black Score: ");
        blackScoreLabel.setForeground(Color.WHITE);
        blackScoreLabel.setBackground(Color.BLACK);
        blackScoreLabel.setBorder(scoreLabelBorder);
        blackScoreLabel.setOpaque(true);
        
        //Red Score
        redScoreField = new JTextField(3);
        redScoreField.setHorizontalAlignment(JTextField.CENTER);
        redScoreField.setBackground(Color.RED);
        redScoreField.setBorder(scoreLabelBorder);
        redScoreField.setEditable(false);
        redScoreField.setText(Integer.toString(controller.getRedScore()));
        
        //Black Score
        blackScoreField = new JTextField(3);
        blackScoreField.setHorizontalAlignment(JTextField.CENTER);
        blackScoreField.setForeground(Color.WHITE);
        blackScoreField.setBackground(Color.BLACK);
        blackScoreField.setBorder(scoreLabelBorder);
        blackScoreField.setEditable(false);
        blackScoreField.setText(Integer.toString(controller.getBlackScore()));
        
        //File menu
        file.add(newGame);
        file.add(endGame);
        file.add(exitGame);
        bg.add(bgGreen);
        bg.add(bgBlue);
        bg.add(bgMagenta);
        bg.add(bgOrange);
        bg.add(bgDefault);
        options.add(newBoard);
        options.add(newConLength);
        options.add(resetScores);
        
        //Score Labels
        menuPanel.add(redScoreLabel);
        menuPanel.add(redScoreField);
        menuPanel.add(blackScoreLabel);
        menuPanel.add(blackScoreField);
        
        contentPane.add(menuPanel);
        gamePanel = new GamePanel(controller.getBoard());
        gamePanel.addMouseListener(GUI.this);
        contentPane.add(gamePanel);
       
        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
       
    }
    
    private void updateAlertField(String alert)
    {
        alertField.setText(alert);
    }
    
    private void newGame()
    {
        controller.newGame();
    }
    
    private void endGame()
    {
        controller.endGame();
    }
    
    private void reinitialize()
    {
        controller.reinitialize();
    }
    
    //Resets the game scores
    private void resetScores()
    {
        controller.setRedScore(0);
        controller.setBlackScore(0);
        blackScoreField.setText(Integer.toString(controller.getBlackScore()));
        redScoreField.setText(Integer.toString(controller.getRedScore()));
    }
    
    private void newConnectionLength()
    {
        controller.newLength();
    }
    
    @Override
    public final void update(Observable o, Object arg)
    {
        alertField.setForeground(Color.BLACK);
        if(controller.isFull())
        {
            alertField.setForeground(Color.GREEN);
            updateAlertField("Draw!");
        }
        else if(controller.getWinner() != null) //Found a winner
        {
            updateAlertField(controller.getWinner() + " wins the game!"); //Alert who won
            
            if(controller.getWinner().equals("Red")) //If winner is red
            {
                alertField.setForeground(Color.RED);
                controller.setRedScore(controller.getRedScore()+1); //Add 1 to red score
                redScoreField.setText(Integer.toString(controller.getRedScore())); //Sets the text to proper score
            }
            else //Else if black wins
            {
                alertField.setForeground(Color.BLACK);
                controller.setBlackScore(controller.getBlackScore()+1);
                blackScoreField.setText(Integer.toString(controller.getBlackScore()));
            }
        }
        else if(controller.getCurrentPlayer() == null)
        {
            updateAlertField("Game Ended");
        }
        else
        {
            updateAlertField(controller.getCurrentPlayer()+"'s turn");
        }
        
        gameFrame.repaint();
    }
    
    //Action Performed
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == newGame)
        {
            newGame();
        }
        else if(event.getSource() == endGame)
        {
            endGame();
        }
        else if(event.getSource() == exitGame)
        {
            System.exit(0);
        }
        else if(event.getSource() == bgGreen)
        {
            gamePanel.changeToGreen();
        }
        else if(event.getSource() == bgBlue)
        {
            gamePanel.changeToBlue();
        }
        else if(event.getSource() == bgMagenta)
        {
            gamePanel.changeToMagenta();
        }
        else if(event.getSource() == bgOrange)
        {
            gamePanel.changeToOrange();
        }
        else if(event.getSource() == bgDefault)
        {
            gamePanel.changeToDefault();
        }
        else if(event.getSource() == newBoard)
        {
            reinitialize();
        }   
        else if(event.getSource() == newConLength)
        {
            newConnectionLength();
        }
        else if(event.getSource() == resetScores)
        {
            resetScores();
        }
        
    }
    
    //Mouse Actions
    @Override
    public void mousePressed(MouseEvent e)
    {
        move((int)e.getX()/discSize, controller.getCurrentPlayer());
    }
    
    private void move(int column, String color)
    {
        controller.move(column, color);
    }
    
    @Override public void mouseExited(MouseEvent e){}
       
    @Override public void mouseEntered(MouseEvent e){}
       
    @Override public void mouseReleased(MouseEvent e){}
   
    @Override public void mouseClicked(MouseEvent e){}
    
    
}
