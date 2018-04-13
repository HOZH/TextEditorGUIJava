public class Link<T extends Comparable> {
    private T data;
    private Link next, previous;
    private LinkedList babies;

    public Link(T Data) {
        this.data = Data;
        this.next = null;
        this.previous = null;
        this.babies = new LinkedList();
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public void displayLink() {
        System.out.println(data + "\t\t");
    }

    public T getdata() {
        return data;
    }

    public void setdata(T data) {
        this.data = data;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    public Link getPrevious() {
        return previous;
    }

    public void setPrevious(Link previous) {
        this.previous = previous;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedList getBabies() {
        return babies;
    }

    public void setBabies(LinkedList babies) {
        this.babies = babies;
    }
}