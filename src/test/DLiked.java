public class Liked {

    private Node head = null;

    private Node tail = null;

    private int len = 0;

    public void add(Node node){
        if (head==null){
            head = node;
            tail = head;
            len++;
        }else {
            tail.setRight(node);
            tail=node;
            len++;
        }
    }

    public void addFirst(Node node){
        if (head==null){
            head = node;
            tail = head;
            len++;
        }else {
            node.setRight(head);
            head=node;
            len++;
        }
    }

    public void addLast(Node node){
        add(node);
    }


    public void bianli(){
        if (head==null){
            System.out.println("链表为空");
        }else{
            System.out.println(head.getName());
            Node node = head;
            while (node.getRight()!=null){
                node=node.getRight();
                System.out.println(node.getName());
            }
        }
    }

    public int getLen(){
        return len;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
