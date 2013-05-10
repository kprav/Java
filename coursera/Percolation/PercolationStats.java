public class PercolationStats
{
    private Percolation percolation;
    
    private int N; // N*N grid
    private int T; // No. of times to run computation
    
    private double[] fractionOpenSites;
    
    public PercolationStats(int n, int t)
    {
        int i, j, numOpenSites, counter;
        
        N = n;
        T = t;
        
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        
        counter = 0;
        
        fractionOpenSites = new double[T];
        
        for (int iter = 0; iter < T; iter++)
        {
            
            percolation = new Percolation(N);
            
            numOpenSites = 0;
            
            while (!percolation.percolates())
            {
                i = StdRandom.uniform(1, N+1);
                j = StdRandom.uniform(1, N+1);
                
                if (!percolation.isOpen(i, j))
                {
                    percolation.open(i, j);
                    numOpenSites++;
                }
            }
            
            fractionOpenSites[counter] = (double) numOpenSites/(N*N);
            
            counter += 1; 
            
        }
    }
    
    public double mean()
    {
        return (StdStats.mean(fractionOpenSites));
    }
    
    public double stddev()
    {
        if (T == 1)
            return Double.NaN;
        return (StdStats.stddev(fractionOpenSites));
    }
    
    public double confidenceLo()
    {
        if (T > 1)
            return (StdStats.mean(fractionOpenSites) 
                        - 
                    1.96 * StdStats.stddev(fractionOpenSites) 
                        / 
                    Math.sqrt(T));
        else
            throw new IllegalArgumentException();
    }
    
    public double confidenceHi() 
    {
        if (T > 1)
            return (StdStats.mean(fractionOpenSites) 
                        + 
                    1.96 * StdStats.stddev(fractionOpenSites) 
                        / 
                    Math.sqrt(T));
        else
            throw new IllegalArgumentException();
    }
     
    public static void main(String[] args)
    {
        int n, t;
        
        if (args.length < 2)
        {
            StdOut.println("Insufficient number of arguments");
            return;
        }
        
        n = Integer.parseInt(args[0]);
        t = Integer.parseInt(args[1]);
        
        if (n <= 0 || t <= 0)
            throw new IllegalArgumentException();
        
        Stopwatch stopWatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println("mean \t\t\t\t = " + percolationStats.mean());
        StdOut.println("stddev \t\t\t\t = " + percolationStats.stddev());
        StdOut.println("95% confidence interval \t = " 
                           + 
                       percolationStats.confidenceLo() 
                           + 
                       ", " + percolationStats.confidenceHi());
        StdOut.println("Elapsed Time = " + stopWatch.elapsedTime()); 
        
    }
}