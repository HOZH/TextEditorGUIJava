public class LinkedList<T extends Comparable> {
    private Link first, last;


    public LinkedList() {
        this.first = null;
        this.last = null;
    }


    public boolean isEmpty() {
        return first == null;
    }

    public ListIterator iterator() {
        return new ListIterator(this);
    }


    public void insertFirst(Link newLink) {

        Link link = newLink;
        if (isEmpty()) {
            last = link;
        } else {
            this.first.setPrevious(link);
        }
        link.setNext(first);


        this.first = link;

    }

    public void insetLast(Link newLink) {
        Link link = newLink;
        if (isEmpty()) {
            first = link;
        } else {
            last.setNext(link);
            link.setPrevious(last);
        }
        last = link;
    }


    public Link deleteFirst() {
        Link temp = first;
        if (first.getNext() == null) {
            last = null;
        } else {
            first.getNext().setPrevious(null);
        }

        first = first.getNext();
        return temp;
    }

    public Link deleteLast() {
        Link temp = last;
        if (first.getNext() == null) {
            first = null;
        } else {
            last.getPrevious().setNext(null);
        }
        last = last.getPrevious();
        return temp;
    }


    public Link deleteKeybyValue(T key) {
        Link current = first;
        while (current.getdata() != key) {
            current = current.getNext();
            if (current == null) {
                return null;
            }
        }


        if (current.getdata() == first.getdata()) {
            first = current.getNext();
            first.setPrevious(null);
        } else if (current.getdata() == last.getdata()) {
            last = current.getPrevious();
            last.setNext(null);
        } else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }
        return current;


    }


    public void displayForward() {
        Link current = first;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }

    }

    public void displayBackward() {

        Link current = last;
        while (current != null) {
            System.out.println(current);
            current = current.getPrevious();
        }


    }


    public void insertFirstbyValue(T d) {
        Link link = new Link(d);
        if (isEmpty()) {
            last = link;
        } else {
            this.first.setPrevious(link);
        }
        link.setNext(first);


        this.first = link;

    }


    public boolean insertBeforebyValue(T key, T data) {
        Link newLink = new Link(data);
        Link current = first;
        while (current.getdata() != key) {
            current = current.getNext();
            if (current == null) {
                return false;
            }
        }

        if (current == first) {
            newLink.setNext(first);
            first.setPrevious(newLink);
            first = newLink;
        } else {
            newLink.setNext(current);
            current.getPrevious().setNext(newLink);
            newLink.setPrevious(current.getPrevious());
            current.setPrevious(newLink);


        }
        return true;

    }


    public boolean insertAfterbyValue(T key, T data) {
        Link newLink = new Link(data);
        Link current = first;
        while (current.getdata() != key) {
            current = current.getNext();
            if (current == null) {
                return false;
            }
        }

        if (current == last) {
            last = newLink;


        } else {
            newLink.setNext(current.getNext());
            current.getNext().setPrevious(newLink);
        }

        newLink.setPrevious(current);
        current.setNext(newLink);
        return true;
    }


    public Link getFirst() {
        return first;
    }

    public void setFirst(Link first) {
        this.first = first;
    }

    public Link getLast() {
        return last;
    }

    public void setLast(Link last) {
        this.last = last;
    }

}