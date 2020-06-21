import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node head;
    private int n;

    public RandomizedQueue() {
        this.head = new Node();
    }

    private class Node {
        Item val;
        Node next;

        public Node(Item val) {
            this.val = val;
        }

        public Node() {
            this.val = (Item) new Object();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<>();
        StdOut.println("***Add Items to the queue*** (0 to Exit to Operations)");
        StdOut.println();
        while (true) {
            String input = StdIn.readString();
            if (input.equals("0")) {
                break;
            }
            q.enqueue(input);
        }

        while (true) {

            StdOut.println("[1] Remove and return a random element?");
            StdOut.println("[2] Return a random element?");
            StdOut.println("[3] Print current elements?");
            StdOut.println("[4] Exit?");

            int input = StdIn.readInt();

            if (input == 4) break;

            if (input == 1) {
                String s = q.dequeue();
                StdOut.println(s);
            }

            if (input == 2) {
                String s = q.sample();
                StdOut.println(s);
            }

            if (input == 3) {
                for (String s : q) {
                    StdOut.println(s);
                }
            }
        }
    }

    // construct an empty randomized queue

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null input is not accepted.");
        }

        Node node = new Node(item);
        n++;
        if (head == null) {
            head = node;
        }
        else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }

        //
        // if (n == a.length) {
        //     a = Arrays.copyOf(a, 2 * a.length);
        // }
        // a[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        Node current = head;
        Item item = (Item) new Object();


        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }

        if (n == 1) {
            item = current.next.val;
            current.next = current.next.next;
            n--;
            return item;
        }

        int rnd = StdRandom.uniform(1, n);
        n--;


        for (int i = 0; i < rnd - 1; i++) {
            current = current.next;
        }

        item = current.next.val;
        current.next = current.next.next;
        return item;
        // int rnd = StdRandom.uniform(n);
        // Item item = a[rnd];
        //
        // a[rnd] = null;
        // n--;
        // if (n > 0 && n == a.length / 4) {
        //     a = Arrays.copyOf(a, a.length / 2);
        // }
        // return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        int rndSample = StdRandom.uniform(n);

        Node current = head;

        for (int i = 0; i <= rndSample; i++) {
            current = current.next;
        }

        Item item = current.val;
        return item;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    private class ListIterator implements Iterator<Item> {
        private Item s;
        private int i;
        private int count;

        public ListIterator() {
            this.s = (Item) new Object();
            this.count = 0;
        }

        public boolean hasNext() {
            return count < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Node current = head;
            this.i = StdRandom.uniform(n);

            for (int j = 0; j < i; j++)
                current = current.next;

            count++;
            s = current.next.val;
            return s;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported.");
        }
    }
}


