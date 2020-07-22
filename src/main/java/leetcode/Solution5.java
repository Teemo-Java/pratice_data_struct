package leetcode;
//876. 链表的中间结点
//https://leetcode-cn.com/problems/middle-of-the-linked-list/solution/
public class Solution5 {
    //最普通的做法 先找总长度  然后找中间长度
    public static ListNode middleNode(ListNode head) {
        int size = getSize(head);
        return getByIndex((size/2),head);
    }

    //快慢指针做法
    //效率上来说，没有什么区别，因为都是遍历两次，第一次遍历全部，第二次遍历一半。但是确实是不同的思路
    public static ListNode middleNode1(ListNode head) {
        ListNode fastNode = head;
        ListNode slowNode = head;
        while (fastNode.next !=null && fastNode.next.next!=null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        if(fastNode.next == null){
            return slowNode;
        }else{
            return slowNode.next;
        }
    }

    public static int getSize(ListNode head){
        Integer size =0;
        while (head!=null){
            size++;
            head = head.next;
        }
        return size;
    }
    public static ListNode getByIndex(int index,ListNode head){
        ListNode node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        list(head1);
        System.out.println(middleNode1(head1).val);
    }

    private static void list(ListNode node){
        while (node!=null){
            System.out.print(node.val +"-");
            node = node.next;
        }
        System.out.println();
    }
}
