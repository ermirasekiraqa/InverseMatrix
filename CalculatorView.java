import java.io.*;
import java.awt.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

/** CalculatorView creates the panel and adds the components.*/
public class CalculatorView extends JPanel{

   //the size of panel
   private int width;
   private int height;
   private int matrix_size; //the size of the matrix to be inverted
   //the size of JScrollPanes
   private int pane_width = 70; 
   private int pane_height = 30;
   private double[][] A; //the matrix to be inverted
   private String[][] output; //Contains the output converted from double to String
   //Appear in the panel when the constructor is invoked, provide the input for matrix A
   private JTextArea text1;
   private JScrollPane text1_pane;
   private JTextArea[][] input_textarea;
   private JScrollPane[][] input_scrollpane;
   //Appear in the panel when Calculate button is pressed, show the inverse matrix of A
   private JTextArea text2;
   private JScrollPane text2_pane;
   private JTextArea[][] output_textarea;
   private JScrollPane[][] output_scrollpane;
   //Appear in the panel when Multiply button is pressed, show the multiplying of A and its inverse
   private JTextArea text3;
   private JScrollPane text3_pane;
   private JTextArea[][] multiply_textarea;
   private JScrollPane[][] multiply_scrollpane;
   private Matrix inverseA;
   
   //The buttons
   JButton Back1_button = new JButton("Back");
   JButton Back2_button = new JButton("Back");
   JButton Multiply_button = new JButton("Multiply");
   JButton Calculate_button = new JButton("Calculate");
   
   Font font1 = new Font("Arial", Font.BOLD, 13); 
   Font font2 = new Font("Arial", Font.BOLD, 16);
   Border border = new LineBorder(Color.black, 1, true); //The border line used to border JScrollPanes
  
   MatrixOperations o = new MatrixOperations();
   
   /** Constructor CalculatorView sets the size of the panel.*/
   public CalculatorView(int w, int h,int n){
   
      width = w;
      height = h;
      matrix_size = n;
      this.setPreferredSize(new Dimension(width, height));
      text1 = new JTextArea();
      text1.setFont(font1);
      text1.append("Type the matrix.");
      text1.setEditable(false); //Cannot be edited
      text1_pane = new JScrollPane(text1); //Appears at the beginning of the panel
      text1_pane.setPreferredSize(new Dimension(width-30, 30)); //The size of the text1_pane
            
      text1_pane.setBorder(border); //Borders text1_pane with a thin black line
      this.add(text1_pane);
 
      //The JScrollPanes which provide the input for A
      input_textarea = new JTextArea[matrix_size][matrix_size];
      input_scrollpane = new JScrollPane[matrix_size][matrix_size];
      for(int i = 0; i < matrix_size; i++){
      
         for(int j = 0; j < matrix_size; j++){
         
            input_textarea[i][j] = new JTextArea();
            input_textarea[i][j].setFont(font2);
            input_scrollpane[i][j] = new JScrollPane(input_textarea[i][j]);
            input_scrollpane[i][j].setPreferredSize(new Dimension(pane_width,pane_height));
            input_scrollpane[i][j].setBorder(border);
            this.add(input_scrollpane[i][j]);
         
         }
      
      }
      
      //Adds the Calculate button
      Calculate_button.setPreferredSize(new Dimension(pane_width + 30, pane_height));
      this.add(Calculate_button);
      
   }

   /** paintComponent fills the panel with items to be displayed
     * @param g - the 'graphics pen'. */
   public void paintComponent(Graphics g){
      
      //sets the background gray
      g.setColor(Color.lightGray);
      g.fillRect(0,0,width,height);
      

      loadContent(this);

   }
   
   /** loadContent adds functionality to buttons
     * @param panel - the JPanel in which the buttons operate.*/
   public void loadContent(JPanel panel){
      
      //JPanel panel = new JPanel();
      //When Calculate_button is clicked, it calculates and shows the inverse matrix of A
      Calculate_button.addActionListener(new ActionListener(){
      
         public void actionPerformed(ActionEvent e){
            
               //Removes every component of the panel
               panel.removeAll();
               panel.revalidate();
               panel.repaint();
               
               //A new JScrollPane is added in the panel
               text2 = new JTextArea();
               text2.setFont(font1);
               text2.append("The inverse matrix.");
               text2.setEditable(false); //Cannot be edited
               text2_pane = new JScrollPane(text2);
               text2_pane.setPreferredSize(new Dimension(width-30, 30));       
               text2_pane.setBorder(border);
               panel.add(text2_pane);
            
               //A gets the elements from input_textarea
               A = new double[matrix_size][matrix_size];
               for(int i = 0; i < matrix_size; i++){
               
                  for(int j = 0; j < matrix_size; j++){
                     //if input is written with '/' instead of '.'
                     try{
                     
                           String s = input_textarea[i][j].getText().trim();
                           if(s.contains("/")){
            
                              String[] r = s.split("/");  
                                           
                              A[i][j] = Double.parseDouble(r[0]) / Double.parseDouble(r[1]);
                              
                           }
                           else{
                           
                              A[i][j] = Double.parseDouble(s);
                              
                           }
                           
                     }
                  
                     catch(Exception ex){ 
                     
                        System.out.println("Cannot convert to double.");
                        System.exit(1);
                        
                     }
                     
                  }
               
               }
               
               GaussElimination IA = new GaussElimination(A);
               inverseA = IA.inverse(); //The inverse of A
               
               //Converts the elements of inverseA to String
               output = new String[matrix_size][matrix_size];
               for(int i = 0; i < matrix_size; i++){
               
                  for(int j = 0; j < matrix_size; j++){
                  
                     output[i][j] = new DecimalFormat("0.000").format(inverseA.getElement(i,j));
                  
                  }
               
               }
               
               //The JScrollPanes which provide the output
               output_textarea = new JTextArea[matrix_size][matrix_size];
               output_scrollpane = new JScrollPane[matrix_size][matrix_size];
               //Adds output[i][j] to output_textarea, the output_textarea to output_scrollpane respectively
               for(int i = 0; i < matrix_size; i++){
               
                  for(int j = 0; j < matrix_size; j++){
                  
                     output_textarea[i][j] = new JTextArea();
                     output_textarea[i][j].setFont(font2);
                     output_textarea[i][j].setEditable(false); //Cannot be edited
                     output_scrollpane[i][j] = new JScrollPane(output_textarea[i][j]);
                     output_scrollpane[i][j].setPreferredSize(new Dimension(pane_width,pane_height));
                     output_scrollpane[i][j].setBorder(border);
                     output_textarea[i][j].append(output[i][j]);
                     panel.add(output_scrollpane[i][j]); //Adds the output_scrollpanes into the panel
                  
                  }
               
               }
               
               //Sets the size of Multiply_button and Back1_button and adds them in the panel 
               Multiply_button.setPreferredSize(new Dimension(pane_width + 30, pane_height));
               Back1_button.setPreferredSize(new Dimension(pane_width + 30, pane_height));
               panel.add(Multiply_button);
               panel.add(Back1_button);
               
            }

      });
      
      //When Multiply_button is clicked, it multiplies A and its inverse and provides the output  
      Multiply_button.addActionListener(new ActionListener(){
      
         public void actionPerformed(ActionEvent e){
            
               //Removes every component of the panel
               panel.removeAll();
               panel.revalidate();
               panel.repaint();
               
               //A new JScrollPane is added in the panel
               text3 = new JTextArea();
               text3.setFont(font1);
               text3.append("The Proof.");
               text3.setEditable(false);
               text3_pane = new JScrollPane(text3);
               text3_pane.setPreferredSize(new Dimension(width-30, 30));      
               text3_pane.setBorder(border);
               panel.add(text3_pane);
               
               //Multiplies A and its inverse, proof_matrix - the result
               Matrix proof_matrix = o.multiplyMatrixes(A, inverseA.toDouble());
               
               //The new JScrollPanes which provide the output
               multiply_textarea = new JTextArea[matrix_size][matrix_size];
               multiply_scrollpane = new JScrollPane[matrix_size][matrix_size];
               //Adds the elements of proof_matrix to multiply_textarea, the multiply_textarea to multiply_scrollpane respectively
               for(int i = 0; i < matrix_size; i++){
            
                  for(int j = 0; j < matrix_size; j++){
                  
                     multiply_textarea[i][j] = new JTextArea();
                     multiply_textarea[i][j].setFont(font2);
                     multiply_textarea[i][j].setEditable(false); //Cannot be edited
                     multiply_textarea[i][j].append(new DecimalFormat("0.00").format(proof_matrix.getElement(i,j)));
                     multiply_scrollpane[i][j] = new JScrollPane(multiply_textarea[i][j]);
                     multiply_scrollpane[i][j].setPreferredSize(new Dimension(pane_width,pane_height));
                     multiply_scrollpane[i][j].setBorder(border);
                     panel.add(multiply_scrollpane[i][j]); //Adds the multiply_scrollpanes into the panel
                  
                  }
            
            }
            
            //Sets the size of Back2_button and adds it in the panel
            Back2_button.setPreferredSize(new Dimension(pane_width + 30, pane_height));
            panel.add(Back2_button);
               
         }
         
      });
      
      //When Back1_button is clicked, it adds back the components of the panel before Calculate_button was clicked
      Back1_button.addActionListener(new ActionListener(){
      
         public void actionPerformed(ActionEvent e){
         
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            panel.add(text1_pane);
            
            for(int i = 0; i < matrix_size; i++){
            
               for(int j = 0; j < matrix_size; j++){
               
                  panel.add(input_scrollpane[i][j]);
               
               }
               
            }
         
            panel.add(Calculate_button);
         }
         
      });
      
      //When Back2_button is clicked, it adds back the components of the panel before Multiply_button was clicked
      Back2_button.addActionListener(new ActionListener(){
      
         public void actionPerformed(ActionEvent e){
         
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            
            panel.add(text2_pane);
            
            for(int i = 0; i < matrix_size; i++){
            
               for(int j = 0; j < matrix_size; j++){
               
                  panel.add(output_scrollpane[i][j]);
               
               }
            
            }
            
            panel.add(Multiply_button);
            panel.add(Back1_button);
         
         }
         
      });
   
   }
   
}