package leetcode;

//141 判断链表是否有环 https://leetcode-cn.com/problems/linked-list-cycle/
public class Solution2 {
    //解决思路：快慢指针
    public static boolean hasCycle(ListNode head) {
        //单节点情况
        if(head ==null || (head !=null && head.next==null)){
            return false;
        }
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode.next!=null && fastNode.next.next!=null){
            //提交leetcode的时候，这句输出不要放进去，会影响效率
            System.out.println(fastNode.val+"-"+slowNode.val);
            //为什么慢指针走一步，快指针走两步呢？ 快指针走三步行不行？ 四步呢？
            //因为如果无环，那么快慢指针会越来越远，这种情况下，快指针走三步，四步的效率是高一点的，因为快指针更快到达链表终点嘛
            //但是如果有环，那么快指针走三步(四步)的话，是可能会跳过快慢指针第一次相遇的，那么只能等待第二次(第三次)相遇了，这样的话效率会变低
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if(fastNode ==slowNode){//这里不用使用equals方法来判断，因为节点是同一个节点
                return true;
            }
        }
        return false;
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

        head5.next = head2;
//        list(head1);
        System.out.println("是否有环："+hasCycle(head1));
    }

}
