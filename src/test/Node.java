public class Node {

    private String name;

    private Node left;

    private Node right;

    Node(String name,Node left,Node right){
        this.name = name;
        this.left = left;
        this.right = right;
    }

    Node(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString(){
        return name;
    }
}
