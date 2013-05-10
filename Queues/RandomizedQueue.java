import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int size;
    
    public RandomizedQueue()
    {
        first = null;
        last = null;
        size = 0;
    }
    
    public boolean isEmpty()
    {
        return first == null;
    }
    
    public int size()
    {
        return size;
    }
        
    public void enqueue(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }
    
    public Item dequeue()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        int pos = StdRandom.uniform(1, size+1);
        
        Node dq = first;
        
        while (pos > 1)
        {
            dq = dq.next;
            pos--;
        }
        
        Item item = dq.item;
        
        if (first == last)
        {
            first = null;
        }
        else if (dq == first)
        {
            first = first.next;
            first.previous.next = null;
            first.previous = null;            
        }
        else if (dq == last)
        {
            last = last.previous;
            last.next.previous = null;
            last.next = null;
        }
        else
        {
            dq.previous.next = dq.next;
            dq.next.previous = dq.previous;
            dq.previous = null;
            dq.next = null;    
        }
        
        dq = null;
        
        if (isEmpty())
            last = null;
        
        size--;
        return item;
    }
    
    public Item sample()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        int pos = StdRandom.uniform(1, size+1);
        
        Node dq = first;
        
        while (pos > 1)
        {
            dq = dq.next;
            pos--;
        }
        
        return dq.item;
    }
    
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }
    
    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }
    
    private class ListIterator implements Iterator<Item>
    {
        private Node current;
        private int track;
        private int pos;
        private int recordPos;
        private boolean[] visited;        
        
        ListIterator()
        {
            current = first;
            track = 0;
            visited = new boolean[size];            
            pos = StdRandom.uniform(1, size+1);
            
            visited[pos - 1] = true;
            
            while (pos > 1)
            {
                current = current.next;
                pos--;
            }
        }
        
        public boolean hasNext()
        {
            return track != size;
        }
        
        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            
            Item item = current.item;
            
            track++;
            
            if (track == size)
                return item;
            
            current = first;
            
            pos = StdRandom.uniform(1, size+1);
            
            recordPos = pos;
            
            while (pos > 1)
            {
                current = current.next;
                pos--;
            }
            
            if (current != null)
            {
                if (visited[recordPos - 1])
                {
                    track--;
                    next();
                }
                else
                {
                    visited[recordPos - 1] = true;
                }
            }
            
            return item;
        }
        
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}
