import java.util.Comparator;

public class Solver
{
    private final Board initialBoard;
    private final Comparator<SearchNode> PRIORITY_ORDER;
    private SearchNode deletedFromPriorityQueue;
    private SearchNode deletedFromTwinPriorityQueue;
    private MinPQ<SearchNode> priorityQueue;
    private MinPQ<SearchNode> twinPriorityQueue;
    private boolean solved;
        
    public Solver(Board initial)
    {
        initialBoard = initial;
        PRIORITY_ORDER = new PriorityOrder();
        priorityQueue = new MinPQ<SearchNode>(PRIORITY_ORDER);
        twinPriorityQueue = new MinPQ<SearchNode>(PRIORITY_ORDER);
        deletedFromPriorityQueue = new SearchNode();
        deletedFromTwinPriorityQueue = new SearchNode();
        solved = false;
    }
    
    public boolean isSolvable()
    {
        SearchNode initialSearchNode = new SearchNode();
        initialSearchNode.board = initialBoard;
        initialSearchNode.manhattan = initialBoard.manhattan();
        initialSearchNode.moves = 0;
        initialSearchNode.priority = 
            getPriority(initialSearchNode.manhattan, initialSearchNode.moves);
        initialSearchNode.previousSearchNode = null;
        priorityQueue.insert(initialSearchNode); 
        
        SearchNode twinSearchNode = new SearchNode();
        twinSearchNode.board = initialSearchNode.board.twin();
        twinSearchNode.manhattan = twinSearchNode.board.manhattan();
        twinSearchNode.moves = 0;
        twinSearchNode.priority = 
            getPriority(twinSearchNode.manhattan, twinSearchNode.moves);
        twinSearchNode.previousSearchNode = null;
        twinPriorityQueue.insert(twinSearchNode);
        
        deletedFromPriorityQueue = advancePQ(priorityQueue);
        deletedFromTwinPriorityQueue = advancePQ(twinPriorityQueue);
        
        while (!deletedFromPriorityQueue.board.isGoal()
                   && 
               !deletedFromTwinPriorityQueue.board.isGoal())
        {
            deletedFromPriorityQueue = advancePQ(priorityQueue);
            deletedFromTwinPriorityQueue = advancePQ(twinPriorityQueue);
        }
        
        if (deletedFromPriorityQueue.board.isGoal())
        {
            solved = true;
        }
        else if (deletedFromTwinPriorityQueue.board.isGoal())
        {
            solved = false;
        }
        
        return solved;
    }
    
    public int moves()
    {
        isSolvable();
        
        if (!solved)
        {
            return -1;
        }
        else
        {
            return deletedFromPriorityQueue.moves;
        }
    }
    
    public Iterable<Board> solution()
    {
        isSolvable();
        
        if (!solved)
            return null;
        
        Stack<Board> trace = new Stack<Board>();
        
        SearchNode goalNode = deletedFromPriorityQueue;
        
        trace.push(goalNode.board);
        
        while (goalNode.previousSearchNode != null)
        {
            trace.push(goalNode.previousSearchNode.board);
            goalNode = goalNode.previousSearchNode;
        }
                  
        return trace;
    }
        
    private SearchNode advancePQ(MinPQ<SearchNode> priorityQueue)
    {
        SearchNode deletedFromPQ = priorityQueue.delMin();
        
        Queue<Board> deletedFromPQNeighbors = 
            (Queue<Board>) deletedFromPQ.board.neighbors();
        
        for (Board board : deletedFromPQNeighbors)
        {
            if (deletedFromPQ.previousSearchNode != null)
            {
                if (board.equals(deletedFromPQ.previousSearchNode.board))
                {
                    continue;
                }
            }
            
            SearchNode neighbhorSearchNode = new SearchNode();
            neighbhorSearchNode.board = board;
            neighbhorSearchNode.manhattan = board.manhattan();
            neighbhorSearchNode.moves = deletedFromPQ.moves + 1;
            neighbhorSearchNode.priority = 
                getPriority(neighbhorSearchNode.manhattan, 
                            neighbhorSearchNode.moves);
            neighbhorSearchNode.previousSearchNode = deletedFromPQ;
            
            priorityQueue.insert(neighbhorSearchNode);
        }
        
        return deletedFromPQ;
    }
    
    private int getPriority(int manhattan, int moves)
    {
        return (manhattan + moves);
    }
    
    private class SearchNode
    {
        Board board;
        int manhattan;
        int moves;
        int priority;
        SearchNode previousSearchNode;
    }
    
    private class PriorityOrder implements Comparator<SearchNode>
    {
        public int compare(SearchNode sn1, SearchNode sn2)
        {
            if (sn1.priority < sn2.priority)
                return -1;
            else if (sn1.priority > sn2.priority)
                return 1;
            else
                return 0;
        }
    }
    
    public static void main(String[] args)
    {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                blocks[i][j] = in.readInt();
            }
        }
        
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
        {
            StdOut.println("No solution possible");
        }
        else 
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
            
            for (Board board : solver.solution())
            {
                StdOut.println(board);
            }
        }
    }
}