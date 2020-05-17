/** GaussElimination calculates the inverse of a matrix based on the Gauss Elimination.
    If the matrix cannot be inverted, it returns the zero matrix.*/
public class GaussElimination{

   private int row;
   private int column;
   private boolean state;
   private Matrix m;
   private MatrixOperations o = new MatrixOperations();
   
   /** Constructor GaussElimination initializes the matrix m with 
       A.length rows and 2*A[0].length columns if A is square. 
       The first A.length columns of the matrix m are assigned with elements 
       from A, whereas the remaining part is assigned with an identity matrix.*/
   public GaussElimination(double[][] A){
   
      
      row = A.length;
      column = A[0].length;
      
      m = new Matrix(row, 2 * column);
      for(int i = 0; i < row; i++){
      
         for(int j = 0; j < column; j++){
         
            m.setElement(i, j, A[i][j]);
                                               //_                                                           _
         }                                    //|     m00       m01    ...   m0(column-1)    | 1   0  ...  0  |
                                              //|     m10       m11    ...   m1(column-1)    | 0   1  ...  0  |
      }                                       //|     ...       ...    ...       ...         |... ... ... ... |
                                              //|_ m(row-1)0 m(row-1)1 ... m(row-1)(column-1)| 0   0  ...  1 _|
      for(int i = 0; i < row; i++){          
      
         m.setElement(i, column + i, 1);
      
      }
         
   }
      

        
   /** ifZero checks if the element in position (c,c) is zero, if so swap rows until the element is not zero
     * @param c - the element to be checked.*/
   private void ifZero(int c){
   
      int i;
      boolean processing = m.isZero(c, c);
      while(processing){
         for(i = c+1; i < m.getRow(); i++){
         
            o.swapRows(m, c, i);
            if (!m.isZero(c, c)){
            
               i = m.getRow();
               processing = false;
         
            }
         }
            
         if(i == m.getRow() && m.isZero(c, c)){
         
            processing = false;
            state = true;
      
         }
      
      }
   
   }
   
   /** diagonalize manipulates with rows of the matrix m so that 
       all the elements outside the diagonal become zero.*/
   private void diagonalize(){
   
      for(int i = 0; i < m.getRow(); i++){                         
      
         ifZero(i);                                                  
         for(int j = 0; j < m.getRow(); j++){ 
                             
            if (i != j && m.getElement(i, i) != 0){                    //_                                                _
                                                                      //| m00  0  ...        0            | *   *  ...  *  |
               double d = -m.getElement(j, i) / m.getElement(i, i);   //|  0  m11 ...        0            | *   *  ...  *  |
               o.add(m, i, j, d);                                     //| ... ... ...       ...           |... ... ... ... |
                                                                      //|_ 0   0  ... m(row-1)(column-1)  | *   *  ...  * _|
            }
                  
         }
      
      }
   
   }
   
   /** divideByDiagonal turns the elements of the diagonal into 1's.*/
   private void divideByDiagonal(){
   
      for(int i = 0; i < m.getRow(); i++){                            //_                                _
                                                                     //|  1  0  ...   0  | *   *  ...  *  |
         if(m.getElement(i, i) != 0){                                //|  0  1 ...    0  | *   *  ...  *  |
                                                                     //| ... ... ... ... |... ... ... ... |
            o.multiply(m, i, 1 / m.getElement(i, i));                //|_ 0   0  ...  1  | *   *  ...  * _|  
                  
         }                                                   
      }                                                              
         
   }
   
   /** inverse calculates and returns the inverse of the matrix m using the upper methods.*/
   public Matrix inverse(){

      diagonalize();
      divideByDiagonal();

      //the inverse matrix
      Matrix inverse_m = new Matrix(m.getRow(), m.getColumn()); 
      for(int i = 0; i < inverse_m .getRow(); i++){
         
         for(int j = 0; j <inverse_m .getRow(); j++){
         
            inverse_m .setElement(i, j, m.getElement(i, j + inverse_m .getRow()));
            
         }
         
      }
      
      if(!state){
      
         return inverse_m ;
         
      }
      else{
      
         return inverse_m.zeroMatrix(inverse_m.getRow());
      
      }

   }
   
}