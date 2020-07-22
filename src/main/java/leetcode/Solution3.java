package leetcode;

//203 移除链表中的元素(可删除重复元素) https://leetcode-cn.com/problems/remove-linked-list-elements/
public class Solution3 {
    // 哎，这么简单都没做出来
    public static ListNode removeElements(ListNode head, int val) {
        ListNode virtualHead  = new ListNode(0);//虚拟头节点
        virtualHead.next = head;
        ListNode cur = virtualHead;
        while(cur.next != null){
            if(cur.next.val == val ){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return virtualHead.next;
    }


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(1);
//        ListNode head3 = new ListNode(1);
//        ListNode head4 = new ListNode(4);
//        ListNode head5 = new ListNode(5);
        head1.next = head2;
//        head2.next = head3;
//        head3.next = head4;
//        head4.next = head5;
        list(head1);
        list(removeElements(head1,1));

    }

    private static void list(ListNode node){
        while (node!=null){
            System.out.print(node.val +"-");
            node = node.next;
        }
        System.out.println();
    }
}
