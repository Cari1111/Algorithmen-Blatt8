public class ExtraList {
    ListElement head;
    ListElement tail;

    private class ListElement{
        public double element;
        public ListElement next;

        ListElement(double element, ListElement next){
            this.element = element;
            this.next = next;
        }
    }
    ExtraList(){
        head = null;
        tail = null;
    }

    ExtraList(double element){
        head = new ListElement(element, null);
        tail = head;
    }
    ExtraList(double[] array){
        if (array == null) return;
        head = new ListElement(array[0], null);
        tail = head;
        if (array.length == 1) return;
        for(int i=1; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    public void add(double element){
        if (head == null) {
            head = new ListElement(element, null);
            tail = head;
        } else {
            tail.next = new ListElement(element, null);
            tail = tail.next;
        }
    }

    public void append(ExtraList list){
        if (list.head == null) return;
        if (this.head == null) {
            this.head = list.head;
            this.tail = list.tail;
        }
        this.tail.next = list.head;
        this.tail = list.tail;
    }

    public boolean oneOrLessElements(){
        if (this.isEmpty()) return true;
        return this.head.equals(tail);
    }

    public boolean isEmpty(){
        return (head == null);
    }

    public double getFirst(){
        double first = head.element;
        head = head.next;
        return first;
    }

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


}
