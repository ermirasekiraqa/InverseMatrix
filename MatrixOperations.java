/** MatrixOperations contains methods for row operations
    and a method for multiplying two matrixes.*/
public class MatrixOperations{

   /** swapRows swaps two rows
     * @param m - the matrix whose rows are being swapped
     * @param r1, r2 - the rows to be swapped.*/
   public void swapRows(Matrix m, int r1, int r2){
   
      for(int i = 0; i < m.getColumn(); i++){
         
         double d = m.getElement(r1, i);
         m.setElement(r1, i, m.getElement(r2, i));
         m.setElement(r2, i, d);
         
      }
         
   }
   
   /** multiply multiplies a row by a constant]
     * @param m - the matrix
     * @param r - the row to be multiplied
     * @param d - the value with which the row is multiplied.*/
   public void multiply(Matrix m, int r, double d){
   
      for(int i = 0; i < m.getColumn(); i++){
      
         m.setElement(r, i, d * m.getElement(r, i));
         if(m.getElement(r, i) == -0.0){
         
            m.setElement(r, i, 0.0);
            
         }
         
      }
      
   }
   
   /** add replaces row r2 by adding a constant times r1 to r2
     * @param m - the matrix
     * @param r1, r2 - the rows of the matrix
     * @param d - the value which multiplies row r1.*/
   public void add(Matrix m, int r1, int r2, double d){
   
      for(int i = 0; i < m.getColumn(); i++){
      
         double value = d * m.getElement(r1, i);
         m.setElement(r2, i, m.getElement(r2, i) + value);
            
      }
      
   }
   
   /** multiplyMatrixes multiplies two matrixes
     * @param a, b - the matrixes to be multiplied
     * returns a Matrix object.*/
   public Matrix multiplyMatrixes(double[][] a, double[][] b){
   
      Matrix m = new Matrix(a.length, b[0].length);
      if(a[0].length == b.length){
      
         for(int i = 0; i < a.length; i++){
         
            for(int j = 0; j < b[0].length; j++){
            
               m.setElement(i, j, 0);
             
               for(int k = 0; k < a.length; k++){
                  
                  m.setElement(i , j, m.getElement(i, j) + a[i][k] * b[k][j]);
               
               }
              
            }
         
         }
      
      }
      
      return m;
   
   }
  
}
