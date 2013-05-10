public class Board
{
    private final int[][] board;
    private final int dimension;
    private int blockXIndex;
    private int blockYIndex; 
    
    public Board(int[][] blocks)
    {
        dimension = blocks.length;
        
        board = new int[dimension][dimension];
        
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                board[i][j] = blocks[i][j];
            }
        }
    }
    
    public int dimension()
    {
        return dimension;
    }
    
    public int hamming()
    {
        int numBlocksOutOfPlace = 0;
        int correctValue = 1;
        
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if (board[i][j] == 0)
                {
                    correctValue++;
                    continue;
                }
                if (board[i][j] != correctValue)
                {
                    numBlocksOutOfPlace++;
                }
                correctValue++;
            }
        }
        return numBlocksOutOfPlace;
    }
    
    public int manhattan()
    {
        int manhattan = 0;
        
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if (board[i][j] == 0)
                {
                    continue;
                }
                
                findCorrectBlock(board[i][j]);
                
                if (i != blockXIndex || j != blockYIndex)
                {
                    manhattan += (Math.abs(blockXIndex - i) 
                                      + 
                                  Math.abs(blockYIndex - j));
                }
            }
        }
        return manhattan;
    }
    
    public boolean isGoal()
    {
        int rightValue = 1;
        
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if (board[i][j] != rightValue)
                {
                    return false;
                }
                
                if (i == dimension - 1 && j == dimension - 2)
                {
                    break;
                }
                
                rightValue++;
            }
        }
        return true;
    }
    
    public Board twin()
    {
        int[][] twinBoard = new int[dimension][dimension];
        
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                twinBoard[i][j] = board[i][j];
            }
        }
        
        if (dimension == 1)
        {
            return new Board(twinBoard);
        }
        else
        {
            findBlank();
            if (blockXIndex == dimension - 1)
            {
                int swap = twinBoard[dimension - 2][0];
                twinBoard[dimension - 2][0] = twinBoard[dimension - 2][1];
                twinBoard[dimension - 2][1] = swap;
            }
            else
            {
                int swap = twinBoard[blockXIndex + 1][0];
                twinBoard[blockXIndex + 1][0] = twinBoard[blockXIndex + 1][1];
                twinBoard[blockXIndex + 1][1] = swap;
            }
        }
        
        return new Board(twinBoard);
    }
    
    public boolean equals(Object y)
    {
        if (y == this)
        {
            return true;
        }
        
        if (y == null)
        {
            return false;
        }
        
        if (y.getClass() != this.getClass())
        {
            return false;
        }
        
        Board that = (Board) y;
        
        if (this.dimension != that.dimension)
        {
            return false;
        }
        
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if (this.board[i][j] != that.board[i][j])
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Iterable<Board> neighbors()
    {
         Queue<Board> neighbors = new Queue<Board>();
         
         findBlank();
         
         if (blockXIndex - 1 != -1)
         {
             Board tempBoard = cloneBoard(this);
             tempBoard.board[blockXIndex][blockYIndex] 
                 = tempBoard.board[blockXIndex - 1][blockYIndex];
             tempBoard.board[blockXIndex - 1][blockYIndex] = 0;
             neighbors.enqueue(tempBoard);
         }
         
         if (blockXIndex + 1 != dimension)
         {
             Board tempBoard = cloneBoard(this);
             tempBoard.board[blockXIndex][blockYIndex] 
                 = tempBoard.board[blockXIndex + 1][blockYIndex];
             tempBoard.board[blockXIndex + 1][blockYIndex] = 0;
             neighbors.enqueue(tempBoard);
         }
         
         if (blockYIndex - 1 != -1)
         {
             Board tempBoard = cloneBoard(this);
             tempBoard.board[blockXIndex][blockYIndex] 
                 = tempBoard.board[blockXIndex][blockYIndex - 1];
             tempBoard.board[blockXIndex][blockYIndex - 1] = 0;
             neighbors.enqueue(tempBoard);
         }
         
         if (blockYIndex + 1 != dimension)
         {
             Board tempBoard = cloneBoard(this);
             tempBoard.board[blockXIndex][blockYIndex] 
                 = tempBoard.board[blockXIndex][blockYIndex + 1];
             tempBoard.board[blockXIndex][blockYIndex + 1] = 0;
             neighbors.enqueue(tempBoard);
         }
             
         return neighbors;
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++) 
            {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    private int correctBlockValue(int i, int j)
    {
        return ((i * dimension) + j + 1);
    }
    
    private void findCorrectBlock(int val)
    {
        blockXIndex = (int) (Math.ceil(val/(double) dimension) - 1);
        if (blockXIndex == 0)
            blockYIndex = val - 1;
        else
            blockYIndex = val - (dimension * blockXIndex) - 1;
    }
    
    private void findBlank()
    {
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if (board[i][j] == 0)
                {
                    blockXIndex = i;
                    blockYIndex = j;
                }
            }
        }
    }
    
    private Board cloneBoard(Board board)
    {
        int[][] cloneBoard = new int[dimension][dimension];
        Board clone = new Board(cloneBoard);
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                clone.board[i][j] = board.board[i][j];
            }
        }
        return clone;
    }
}
