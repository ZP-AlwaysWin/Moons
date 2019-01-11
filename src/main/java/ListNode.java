import java.util.HashSet;

class Node{
    int data;
    Node next = null;
    Node(int data){
        this.data=data;
    }
}
public class ListNode {
    public static void main(String[] args) {

        int[] arr = new int[] {5,4,4,3,3,2,1};

        //定义一个for循环,每次在链表头部插入元素,从而创建一个链表
        Node head1 = null;
        for(int i =0;i<arr.length;i++){
            Node node = new Node(arr[i]);
            node.next=head1;
            head1=node;
        }

        System.out.println("原来的链表： ");
        Node temp1=head1;
        while(temp1!=null){
            System.out.print(temp1.data+" ");
            temp1=temp1.next;
        }
        System.out.println();

        //Node head2 = deleteDuplication(head1);
        Node head2 = deleteDuplicatesAll(head1);
        System.out.println("去重后的链表： ");
        Node temp2=head2;
        while(temp2!=null){
            System.out.print(temp2.data+" ");
            temp2=temp2.next;
        }
    }

    public static Node deleteDuplication(Node phead) {
        HashSet<Integer> set = new HashSet<Integer>();
        Node tempNode = phead;
        while (tempNode != null) {
            set.add(tempNode.data);
            tempNode = tempNode.next;
        }
        //for循环,每次在链表的尾部插入元素,从而创建一个链表
        Node head=null;
        Node temp = head;
        for (Integer num : set) {
            Node node = new Node(num);
            if(head==null){
                head=node;
            }else{
                temp.next=node;
            }
            temp=node;
        }
        return head;
    }


    public static Node deleteDuplicatesAll(Node head)
    {
        if (head == null) {
            return head;
        }

        Node dummy = new Node(Integer.MAX_VALUE); // 头结点
        dummy.next = head;
        Node prev, cur;
        prev = dummy;
        cur = head;
        while (cur != null) {
            boolean duplicated = false;
            while (cur.next != null && cur.data == cur.next.data) {
                duplicated = true;
                cur = cur.next;
            }
            if (duplicated) { // 删除重复的最后一个元素
                cur = cur.next;
                continue;
            }
            prev.next = cur;
            prev = prev.next;
            cur = cur.next;
        }
        prev.next = cur;
        return dummy.next;
    }

}
