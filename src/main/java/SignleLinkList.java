//实现一个单链表
class Node01 {
    protected Node01 next; //指针域
    public  int data;//数据域

    public Node01( int data) {
        this. data = data;
    }

    //显示此节点
    public void display() {
        System. out.print( data + " ");
    }
}

public class SignleLinkList {
    public Node01 first; // 定义一个头结点
    private int pos = 0;// 节点的位置

    public SignleLinkList() {
        this.first = null;
    }

    // 插入一个头节点
    public void addFirstNode(int data) {
        Node01 node = new Node01(data);
        node.next = first;
        first = node;
    }

    // 删除一个头结点,并返回头结点
    public Node01 deleteFirstNode() {
        Node01 tempNode = first;
        first = tempNode.next;
        return tempNode;
    }

    // 在任意位置插入节点 在index的后面插入
    public void add(int index, int data) {
        Node01 node = new Node01(data);
        Node01 current = first;
        Node01 previous = first;
        while (pos != index) {
            previous = current;
            current = current.next;
            pos++;
        }
        node.next = current;
        previous.next = node;
        pos = 0;
    }

    // 删除任意位置的节点
    public Node01 deleteByPos(int index) {
        Node01 current = first;
        Node01 previous = first;
        while (pos != index) {
            pos++;
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            pos = 0;
            previous.next = current.next;
        }
        return current;
    }

    // 根据节点的data删除节点(仅仅删除第一个)
    public Node01 deleteByData(int data) {
        Node01 current = first;
        Node01 previous = first; // 记住上一个节点
        while (current.data != data) {
            if (current.next == null) {
                return null;
            }
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }

    // 显示出所有的节点信息
    public void displayAllNodes() {
        Node01 current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    // 根据位置查找节点信息
    public Node01 findByPos(int index) {
        Node01 current = first;
        if (pos != index) {
            current = current.next;
            pos++;
        }
        return current;
    }

    // 根据数据查找节点信息
    public Node01 findByData(int data) {
        Node01 current = first;
        while (current.data != data) {
            if (current.next == null)
                return null;
            current = current.next;
        }
        return current;
    }
}
