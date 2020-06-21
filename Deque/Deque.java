import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private Item[] a;
    private int n;


    // construct an empty deque
    public Deque() {
        this.a = (Item[]) new Object[1];
        this.n = 0;
    }

    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        while (true) {
            StdOut.print();
            StdOut.println("[1] Add an Item to the beginning of the list");
            StdOut.println("[2] Add an Item to the end of the list");
            StdOut.println("[3] Print the current Items on the list");
            StdOut.println("[4] Exit");
            StdOut.println();
            int answer = StdIn.readInt();
            StdOut.println();

            if (answer == 4) break;

            if (answer == 1) {
                StdOut.println("Add Items to the Beginning of the list (0 -> quit)");
                while (true) {
                    String readVal = StdIn.readString();
                    if (readVal.equals("0"))
                        break;
                    dq.addFirst(readVal);
                }
            }
            else if (answer == 2) {
                StdOut.println("Add Items to the End of the list (0 -> quit)");
                while (true) {
                    String readVal = StdIn.readString();
                    if (readVal.equals("0"))
                        break;
                    dq.addLast(readVal);
                }

            }
            else if (answer == 3) {

                for (String i : dq) {
                    StdOut.println(i);
                }
                StdOut.println("(" + dq.size() + " items)");
            }

        }
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Oops! Item cannot be null.");
        if (isEmpty()) {
            if (n == a.length) a = Arrays.copyOf(a, 2 * a.length);
            a[0] = item;
            n++;
        }
        else {
            if (n == a.length) a = Arrays.copyOf(a, 2 * a.length);
            Item[] copy = (Item[]) new Object[a.length];
            copy[0] = item;
            System.arraycopy(a, 0, copy, 1, copy.length - 1);
            a = copy;
            n++;
        }
    }
    
    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Oops! Item cannot be null.");
        if (n == a.length) a = Arrays.copyOf(a, 2 * a.length);
        a[n++] = item;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Oops! Deque is empty!");
        Item item = a[0];
        a[0] = null;
        n--;
        if (n > 0 && n == a.length / 4) a = Arrays.copyOf(a, a.length / 2);
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Oops! Deque is empty!");
        Item item = a[n - 1];
        a[n - 1] = null;
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int i;

        public ListIterator() {
            i = 0;
        }
        public boolean hasNext() {
            return i < n && a[i] != null;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i++];
        }
        public void remove() {
        }
    }
}
