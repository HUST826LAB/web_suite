public class Test {

    public static void main(String[] args) {
        Node a = new Node("A",null,null);
        Node b = new Node("B",null,null);
        Node c = new Node("C",null,null);
        Node d = new Node("D",null,null);
        Node e = new Node("E",null,null);
        Node f = new Node("F",null,null);
        Node g = new Node("G",null,null);
        Node h = new Node("H",null,null);
        Node i = new Node("I",null,null);
        Node j = new Node("J",null,null);

        DLiked liked = new DLiked();
        liked.addLast(a);
        liked.addLast(b);
        liked.addLast(c);
        liked.addLast(d);
        liked.addLast(e);
        liked.addLast(f);
        liked.addLast(g);
        liked.addLast(h);
        liked.addFirst(i);
        liked.addFirst(j);
        liked.bianLiHeadToTail();
        System.out.println();
        liked.bianLiTailToHead();
//        a.setLeft(b);
//        b.setRight(c);
//        c.setLeft(d);
//        c.setRight(e);
//        a.setRight(f);
//        f.setLeft(g);
//        g.setLeft(i);
//        g.setRight(j);
//        f.setRight(h);

//        houxubianli(a);
    }

    public static void qianxubianli(Node current){
        System.out.println(current.getName());
        if(current.getLeft()!=null)
            qianxubianli(current.getLeft());
        if (current.getRight()!=null)
            qianxubianli(current.getRight());
    }

    public static void zhongxubianli(Node current){
        if(current.getLeft()!=null)
            zhongxubianli(current.getLeft());
        System.out.println(current.getName());
        if (current.getRight()!=null)
            zhongxubianli(current.getRight());
    }

    public static void houxubianli(Node current){
        if(current.getLeft()!=null)
            houxubianli(current.getLeft());
        if (current.getRight()!=null)
            houxubianli(current.getRight());
        System.out.println(current.getName());
    }
}