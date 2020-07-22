package leetcode;


// 删除排序链表中的重复元素 题目描述有问题，应该说移除相邻并且相同的元素，哎
public class Solution4 {
//    public static ListNode deleteDuplicates(ListNode head) {
//        Map<Integer,Integer> map = new HashMap<>();
//        ListNode temp = head;
//        map.put(head.val,0);
//        while (temp.next!=null){
//            if(map.containsKey(temp.next.val)){
//                temp.next = temp.next.next;
//            }else{
//                map.put(temp.val,0);
//                temp = temp.next;
//            }
//        }
//        return head;
//    }

    public static ListNode deleteDuplicates(ListNode head) {
        //创建遍历指针current，初始化指向head
        ListNode current = head;
        //current.next == null表示遍历的指针到达链表尾部
        while(current != null && current.next != null){
            //如果下一个元素的值和当前元素的值相等，就删除后后面的元素
            //特殊尾部节点分析：(最后一次循环)
            //相等的情况  【1 】（current）---> 【1】（current.next）  这个时候 current.next.next ==== null
            if (current.next.val == current.val){
                // current.next = null (直接删除的尾部的元素)
                current.next = current.next.next;
            } else {
                //不相等的情况  【1 】（current）---> 【2】（current.next）  这个时候 current 就移动到了尾部元素
                //循环结束之后 current.next 为null 即为最后一次循环
                current = current.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(1);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(1);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        list(head1);
        list(deleteDuplicates(head1));

    }

    private static void list(ListNode node){
        while (node!=null){
            System.out.print(node.val +"-");
            node = node.next;
        }
        System.out.println();
    }
}
