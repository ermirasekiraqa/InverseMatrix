public class Matrix{

   private int row;
   private int column;
   private double[][] matrix;
   
   /** Constructor Matrix initializes the two-dimensional array matrix
     * @param r - the number of rows
     * @param c - the number of columns.*/
   public Matrix(int r, int c){
   
      row = r;
      column = c;
      matrix = new double[row][column];
      
   }
   
   /** getRow returns the number of rows of the matrix.*/
   public int getRow(){
   
      return row;
      
   }
   
   /** getColumn returns the number of columns of the matrix.*/
   public int getColumn(){
   
      return column;
      
   }
   
   /** getElement returns the element in the position[r][c]
     * @param r - the row
     * @param c - the column.*/
   public double getElement(int r, int c){
   
      return matrix[r][c];
      
   }
   
   /** setElement sets a value in  the position [r][c]
     * @param r - the row
     * @param c - the column
     * @param d - the value to be assigned.*/
   public void setElement(int r, int c, double d){
   
      matrix[r][c] = d;
      
   }
   
   /** identityMatrix creates a square matrix that has 1's along the 
       main diagonal and 0's for all other entries
     * @param n - the size of the matrix (n x n)
     * returns the matrix.*/
   public Matrix identityMatrix(int n){
   
      Matrix m = new Matrix(n, n);
      for(int i = 0; i < n; i++){
         
            m.setElement(i, i, 1.0);
         
      }

      return m;
      
   } 
   
   /** zeroMatrix creates a square matrix where all elements are 0
     * @param n - the size of the matrix (n x n)
     * returns the matrix.*/
   public Matrix zeroMatrix(int n){
   
      Matrix m = new Matrix(n, n);
      for(int i = 0; i < n; i++){
         
         for(int j = 0; j < n; j++){
         
            m.setElement(i, j, 0);
         
         }
         
      }
      
      return m;
   
   }
   
   /** isZero checks if the element in position [r][c] is equal to 0
     * returns true if the element is equal to 0, false otherwise.*/
   public boolean isZero(int r, int c){
   
      return matrix[r][c] == 0;
   
   }
   
   /** toDouble returns the two-dimensional array matrix.*/
   public double[][] toDouble(){
      
      return matrix;
   
   }
   
}