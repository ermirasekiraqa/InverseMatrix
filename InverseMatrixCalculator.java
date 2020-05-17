import javax.swing.*;
import java.awt.*;
/** InverseMatrixCalculator calculates the inverse of a matrix.
    If the matrix can be inverted it returns the inverse, otherwise
    it returns the zero matrix. */
public class InverseMatrixCalculator{

  public InverseMatrixCalculator(){
   
      try{ 
            
            String s = JOptionPane.showInputDialog("Type the size of the matrix").trim();
            int n = Integer.parseInt(s); 
            int width = n*78+5;
            int height = n*35+75;
            if(n == 0){
            
               System.out.println("Index 0 out of bounds.");
               System.exit(1);
               
            }
            if(n <= 2){
            
               width = 222;
               height = 150;
         
            }
            
            JFrame frame = new JFrame("Calculator"); //the frame
            CalculatorView v = new CalculatorView(width, height,n);
            
            frame.setSize(width+16, height+39);
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When closed, operation completes
            frame.setLocationRelativeTo(null); //Opens the window in the center of the screen 
            frame.setResizable(false); //makes the window non-resizable
            if(n < 18){
            
               frame.getContentPane().add(v); //adds the panel to the frame
               
            
            }
            else{
               
               //Adds JscrollPane because after n=18 JFrame doesn't show all the components
               Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
               frame.setSize(screenSize.width, screenSize.height);
               JScrollPane pane = new JScrollPane(v);
               pane.setPreferredSize(new Dimension(width,height));
               frame.getContentPane().add(pane);
               
            }
            
            frame.setVisible(true);
         
      }
      
      catch(Exception e){ System.out.println("Invalid input."); }
  
   }
   
   public static void main(String[] args){
   
      new InverseMatrixCalculator();
   
   }

}