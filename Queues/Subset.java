public class Subset
{
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        int num = Integer.parseInt(args[0]);
        
        String s = null;
        
        while (!StdIn.isEmpty())
        {
            s = StdIn.readString();
            rq.enqueue(s);
        }
        
        while (num > 0)
        {
            StdOut.println(rq.dequeue());
            num--;
        }
    }
}