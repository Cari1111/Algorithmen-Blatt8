package CustomSort;
public class ExtraList {
    ListElement head; // first element of the list
    ListElement tail; // last element of the list

    // List element, that can store an element and the next list element
    private class ListElement{
        public double element;
        public ListElement next;

        ListElement(double element, ListElement next){
            this.element = element;
            this.next = next;
        }
    }
    
    // Constructors for ExtraList
    ExtraList(){ // create empty list
        head = null;
        tail = null;
    }

    ExtraList(double element){ // create list with one element
        head = new ListElement(element, null);
        tail = head;
    }
    ExtraList(double[] array){ // create list with an array
        if (array == null) return;
        head = new ListElement(array[0], null);
        tail = head;
        if (array.length == 1) return;
        for(int i=1; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    // Add elements at the end of the list and update the tail
    public void add(double element){
        if (head == null) {
            head = new ListElement(element, null);
            tail = head;
        } else {
            tail.next = new ListElement(element, null);
            tail = tail.next;
        }
    }

    // Append an other list at the end of this list and update tail
    public void append(ExtraList list){
        if (list.head == null) return;
        if (this.head == null) {
            this.head = list.head;
            this.tail = list.tail;
        }
        this.tail.next = list.head;
        this.tail = list.tail;
    }

    // Check if there are one or less elements in the list
    public boolean oneOrLessElements(){
        if (this.isEmpty()) return true;
        return this.head.equals(tail);
    }

    // Check if the list is empty
    public boolean isEmpty(){
        return (head == null);
    }

    // Removes the first element of the list and returns it
    public double getFirst(){
        double first = head.element;
        head = head.next;
        return first;
    }

    // converts the list to a string
    public String toString(){
        String result = "[";
        ListElement currentElement = head;
        while (!currentElement.equals(tail)){
            result = result + currentElement.element + ", ";
            currentElement = currentElement.next;
        }
        result = result + tail.element + "]";
        return result;
        
    }

    // converts the list to an array
    public double[] toArray(){
        int size = 0;
        ListElement currentElement = head;
        while (!(currentElement == null)){
            size += 1;
            currentElement = currentElement.next;
        }
        currentElement = head;
        double[] result = new double[size];
        for (int index = 0; index < result.length; index++) {
            result[index] = currentElement.element;
            currentElement = currentElement.next;
        }
        return result;
        
    }


}
