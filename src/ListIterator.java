public class ListIterator<T extends Comparable> {
    private Link current;
    private Link previous;
    private LinkedList ourlist;

    public ListIterator(LinkedList list) {

        ourlist = list;
        reset();

    }

    public Link next() {
        if (current == null) {

            System.err.println("hit the end of list");

            return null;
        }
        Link temp = current;
        previous = temp;
        current = current.getNext();
        return temp;

    }

    public void reset() {
        current = ourlist.getFirst();
        previous = null;

    }

    public void nextLink() {
        previous = current;
        current = current.getNext();
    }

    public boolean atEnd() {
        return current.getNext() == null;
    }

    public boolean isNull() {
        return current == null;
    }

    public Link getCurrent() {

        return current;

    }

    public void insertAfter(T dd) {

        Link newLink = new Link(dd);

        if (ourlist.isEmpty()) {
            ourlist.insertFirst(newLink);
            current = newLink;
        } else {
            newLink.setNext(current.getNext());
            current.setNext(newLink);
            nextLink();

        }
    }

    public void insertbefore(T ad) {

        Link newLink = new Link(ad);
        if (previous == null) {

            newLink.setNext(ourlist.getFirst());
            ourlist.setFirst(newLink);
            reset();

        } else {

            newLink.setNext(previous.getNext());
            previous.setNext(newLink);
            current = newLink;

        }

    }

    public T deleteCurrent() {

        T value = (T) current.getdata();
        if (previous == null) {

            ourlist.setFirst(current.getNext());
            reset();
        } else {
            previous.setNext(current.getNext());
            if (atEnd()) {

                reset();

            } else {
                current = current.getNext();
            }
        }
        return value;
    }
}