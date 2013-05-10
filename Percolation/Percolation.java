public class Percolation
{
    private int N; // N * N grid
    private int nSquared;
    private boolean[] percolationSystem;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUFOnlyTop;
    
    public Percolation(int n)
    {
        int index;
        
        if (n <= 0)
            throw new IndexOutOfBoundsException();
        
        N = n;
        nSquared = N*N;
        
        percolationSystem = new boolean[nSquared];
        
        for (int i = 0; i < nSquared; i++)
        {
            percolationSystem[i] = false; // All sites are closed initially
        }
        
        // WeightedQuickUnionUF[nSquared]   = Root of top virtual set
        // WeightedQuickUnionUF[nSquared+1] = Root of bottom virtual set
        weightedQuickUnionUF = new WeightedQuickUnionUF(nSquared+2);
        weightedQuickUnionUFOnlyTop = new WeightedQuickUnionUF(nSquared+1);
    }
    
    public void open(int i, int j) 
    {
        if (i < 1 || j < 1 || i > N || j > N)
            throw new IndexOutOfBoundsException();
        
        int index = getIndex(i, j);
        
        if (!percolationSystem[index])
        { 
            percolationSystem[index] = true;
            
            // can connect to [ {i-1, j}, {i+1, j}, {i, j-1}, {i, j+1} ]
            connect(i, j);
        }
    }
    
    public boolean isOpen(int i, int j)
    {
        if (i < 1 || j < 1 || i > N || j > N)
            throw new IndexOutOfBoundsException();
        
        return (percolationSystem[getIndex(i, j)]);
            
    }
    
    public boolean isFull(int i, int j)
    {
        if (i < 1 || j < 1 || i > N || j > N)
            throw new IndexOutOfBoundsException();
        
        if (weightedQuickUnionUFOnlyTop.connected(getIndex(i, j), nSquared))
            return true;
        
        return false;
    }
    
    public boolean percolates()
    {        
        if (weightedQuickUnionUF.connected(nSquared, nSquared+1))
            return true;
                
        return false;
    }
    
    private int getIndex(int i, int j)
    {
        return ((N * i + j) - N - 1);
    }
    
    private void connect(int i, int j)
    {
        int index = getIndex(i, j);
        
        int tempIndex = getLeft(i, j);
        if (tempIndex != -1)
        {
            weightedQuickUnionUF.union(index, tempIndex);
            weightedQuickUnionUFOnlyTop.union(index, tempIndex);
        }
        
        tempIndex = getRight(i, j);
        if (tempIndex != -1)
        {
            weightedQuickUnionUF.union(index, tempIndex);
            weightedQuickUnionUFOnlyTop.union(index, tempIndex);
        }
        
        tempIndex = getTop(i, j);
        if (tempIndex != -1)
        {
            weightedQuickUnionUF.union(index, tempIndex);
            weightedQuickUnionUFOnlyTop.union(index, tempIndex);
        }
        
        tempIndex = getBottom(i, j);
        if (tempIndex != -1)
        {
            weightedQuickUnionUF.union(index, tempIndex);
            if (i != N)
                weightedQuickUnionUFOnlyTop.union(index, tempIndex);
        }
            
    }
    
    private int getLeft(int i, int j)
    {
        if (j > 1)
            if (isOpen(i, j-1))
                return getIndex(i, j-1);
        return -1;
    }
    
    private int getRight(int i, int j)
    {
        if (j+1 < N+1)
            if (isOpen(i, j+1))
                return getIndex(i, j+1);
        return -1;
    }
    
    private int getTop(int i, int j)
    {
        if (i == 1)
            return nSquared;
        if (i > 1)
            if (isOpen(i-1, j))
                return getIndex(i-1, j);
        return -1;
    }
    
    private int getBottom(int i, int j)
    {
        if (i == N)
            return (nSquared+1);
        if (i+1 < N+1)
            if (isOpen(i+1, j))
                return getIndex(i+1, j);
        return -1;
    }
    
}