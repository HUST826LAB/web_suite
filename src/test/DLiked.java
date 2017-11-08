@SuppressWarnings("Duplicates")
public class DLiked {

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
            node.setLeft(tail);
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
            head.setLeft(node);
            head=node;
            len++;
        }
    }

    public void addLast(Node node){
        add(node);
    }


    public void bianLiHeadToTail(){
        if (head != null) {
            System.out.print(head.getName()+",");
            Node node = head;
            while (node.getRight()!=null){
                node=node.getRight();
                System.out.print(node.getName()+",");
            }
        } else {
            System.out.println("链表为空");
        }
    }

    public void bianLiTailToHead(){
        if (tail != null) {
            System.out.print(tail.getName()+",");
            Node node = tail;
            while (node.getLeft()!=null){
                node=node.getLeft();
                System.out.print(node.getName()+",");
            }
        } else {
            System.out.println("链表为空");
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
