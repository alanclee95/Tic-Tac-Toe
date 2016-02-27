import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class TicTacToe
{
   public int player;                                       //current player
   public int turn = 1;                                       //current turn
   public JButton[] buttons = new JButton[10];
   public JPanel squares = new JPanel(new GridLayout(3,3));
   public JPanel controls = new JPanel(new FlowLayout()); 
   public JPanel info = new JPanel(new FlowLayout()); 
   public JFrame frame = new JFrame();
   
   public TicTacToe()
   {
      {
         frame.setLocation(500, 200);   //location on monitor
         frame.setTitle("TicTacToe");             //title of frame
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what happens when the box is closed
         frame.setSize( new Dimension( 400, 300 ) );
         frame.setResizable(true);
         ImageIcon img = new ImageIcon("tic-tac-toe.png");
         frame.setIconImage(img.getImage());
      }      
      //edit panel
      {
         squares.setBackground(new java.awt.Color(28,28,28));
         squares.setPreferredSize(new Dimension(300, 300));
         squares.setMaximumSize(new Dimension(300, 300));
           
         controls.setBackground(new java.awt.Color(122,183,179));
         controls.setMaximumSize(new Dimension(100, 100));
      }
      
      newGame();
      
            
      JPanel mainPanel = new JPanel(new GridLayout());
      {
         mainPanel.setSize( new Dimension( 300, 300 ) );
         mainPanel.setBackground(new java.awt.Color(122,183,179));
      }
      

      mainPanel.add(squares);
      mainPanel.add(controls);
      
      
      frame.add(mainPanel, BorderLayout.CENTER);
      frame.setVisible(true);
   }
   
   public void currentInfo()
   {
      controls.removeAll();
      
      //new game button
      {
         JButton button = new JButton();
         button.addActionListener(
               new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     newGame();
                  }
               }
            );
            
         button.setText("New Game");
         button.setForeground(Color.WHITE);
         button.setBackground(new java.awt.Color(28,28,28));
         button.setFont(new Font("Century Gothic", Font.ITALIC, 15));
         button.setPreferredSize(new Dimension(126, 75));
         
         controls.add(button);
         
      }
      
      JLabel player = new JLabel("Player: " + getPlayer());
      player.setForeground(Color.WHITE);
      player.setFont(new Font("Century Gothic", Font.ITALIC, 25));
      JLabel turnNum = new JLabel("  Turn: " + turn);
      turnNum.setForeground(Color.WHITE);
      turnNum.setFont(new Font("Century Gothic", Font.ITALIC, 25));
                                    
      controls.add(player);
      controls.add(turnNum);
   }
   public void newGame()
   {
      setPlayer(1);
      turn = 1;
      squares.removeAll();
      
      for(int i = 0; i<9; i++)
      {
         buttons[i] = makeButton(i);
         squares.add(buttons[i]);
      }
      
      squares.revalidate();
      
      currentInfo();
   
   }
   
   //win conditions
   public boolean checkWin()
   {
      if(buttons[0].getText().equals(buttons[1].getText()) && buttons[1].getText().equals(buttons[2].getText()) && !buttons[0].getText().equals(""))
      {
         return true;
      }
      else if(!buttons[3].getText().equals("") && buttons[3].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[5].getText()))
      {
         return true;
      }
      else if(!buttons[6].getText().equals("") && buttons[6].getText().equals(buttons[7].getText()) && buttons[7].getText().equals(buttons[8].getText()))
      {
         return true;
      }
      else if(!buttons[0].getText().equals("") && buttons[0].getText().equals(buttons[3].getText()) && buttons[3].getText().equals(buttons[6].getText()))
      {
         return true;
      }
      else if(!buttons[1].getText().equals("") && buttons[1].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[7].getText()))
      {
         return true;
      }
      else if(!buttons[2].getText().equals("") && buttons[2].getText().equals(buttons[5].getText()) && buttons[5].getText().equals(buttons[8].getText()))
      {
         return true;
      }
      else if(!buttons[0].getText().equals("") && buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText()))
      {
         return true;
      }
      else if(!buttons[2].getText().equals("") && buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText()))
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   public void setPlayer(int i)
   {
      player =i;
      currentInfo();
   }
   
   public int getPlayer()
   {
      return player;
   }
   
   public JButton makeButton(int i)
   {
      JButton temp = new JButton();
      temp.setText("");
      temp.setSize( new Dimension( 100, 100 ) );
      temp.setForeground(Color.WHITE);
      temp.setBackground(new java.awt.Color(28,28,28));
      temp.setFont(new Font("Monospaced", Font.BOLD, 25));
         
      temp.addActionListener(
            new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                  currentInfo();
                  if(getPlayer()==1)
                  {
                     temp.setText("X");
                     setPlayer(2);
                     turn++;
                     temp.setBackground(new java.awt.Color(85,255,46));
                     //System.out.println("X");
                  }
                  else
                  {
                     temp.setText("O");
                     setPlayer(1);
                     turn++;
                     temp.setBackground(new java.awt.Color(46,141,255));
                     //System.out.println("O");
                  }
                  temp.setEnabled(false);//disables button after click
                  
                  if(turn>=3)
                  {
                     if(checkWin())
                        gameOver();
                     else if(!checkWin() && turn == 10)
                        gameOver();
                  }
               
                  temp.setVisible(true);
               }
            }
            );
      return temp;
   }
   
   public void gameOver()
   {
      
      if(checkWin() && getPlayer()==1)
      {
         JOptionPane.showMessageDialog(frame,"Player 2 Wins!!!");
         //System.out.println("Player 2 Wins");
      }
      else if(checkWin() && getPlayer()==2)
      {
         JOptionPane.showMessageDialog(frame,"Player 1 Wins!!!");
         //System.out.println("Player 1 Wins");
      }
      else if(!checkWin() && turn == 10)
      {
         JOptionPane.showMessageDialog(frame,"Draw!!!");
         //System.out.println("Draw");
      }
      
      newGame();
   }
   public static void main(String [] args)
   {
      TicTacToe newGame = new TicTacToe();
   }//main end

}//class end
