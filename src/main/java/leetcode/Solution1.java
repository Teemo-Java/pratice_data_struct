package leetcode;


//206-单链表反转 https://leetcode-cn.com/problems/reverse-linked-list/

//遍历法
public  class Solution1 {
    private static ListNode copyNode(ListNode node){
        return new ListNode(node.val);
    }
    //遍历法思路：从头遍历老链表 根据老元素复制出新元素，然后每复制出一个元素都插入新链表的head与第一个元素之间
    public static ListNode reverseList(ListNode head) {
        ListNode newHead;
        if(head == null || head.next ==null){
            return head;
        }else{
            ListNode temp =head;
            newHead = copyNode(head)  ;//临时头节点
            ListNode addNode  ;
            while (temp!=null){
                addNode =copyNode(temp);
                addNode.next =newHead.next;
                newHead.next = addNode;
                temp = temp.next;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        ListNode head6 = new ListNode(6);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        list(head1);
        ListNode newHead =   reverseList(head1);
        list(newHead);
    }

    private static void list(ListNode node){
        while (node!=null){
            System.out.print(node.val +"-");
            node = node.next;
        }
        System.out.println();
    }
}

