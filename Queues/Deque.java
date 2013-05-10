import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int size;
    
    public Deque()
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
    
    public void addFirst(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (oldFirst != null)
            oldFirst.previous = first;
        else  
            last = first;
        size++;
    }
    
    public void addLast(Item item)
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
    
    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null)
            first.previous = null;
        if (isEmpty())
            last = null;
        size--;
        return item;
    }
    
    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last != null)
            last.next = null;
        else
            first = null;
        size--;
        return item;
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
        private Node current = first;
        
        public boolean hasNext()
        {
            return current != null;
        }
        
        public Item next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove()
        {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}
