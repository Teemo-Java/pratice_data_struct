package leetcode;

import java.util.Stack;

public class Solution6 {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode virtualNode = new ListNode(0);
        virtualNode.next = head;
        ListNode tailNode = getStartIndex(virtualNode,k);
        int  i = 0;
        ListNode  result  = head;
        while (tailNode !=null){
            // 做部分反转操作
            i++;
            ListNode partHeaadNode = reversePart(virtualNode,k);
//            if(i !=1){
//                virtualNode.next = partHeaadNode;
//            }
            if(i==1){
                result = tailNode;
            }
            virtualNode = virtualNode.next;
//            virtualNode.next = partHeaadNode;
            list(tailNode);
            tailNode =  getStartIndex(virtualNode,k);

            int a =1;
        }
        return result;
    }

    /**
     * 返回可用的尾部节点，返回null表示无可用尾节点  剩下的部分不用反转
     * @param preNode
     * @param k
     * @return
     */
    public static ListNode  getStartIndex(ListNode preNode,int k){
        int i = 0;
        while (preNode.next!=null && i < k){
            preNode = preNode.next;
            i++;
        }
        return i== k?preNode:null;
    }

    /**
     * 使用栈反转部分链表
     * @param startNode 传入的头结点
     * @param k         反转的长度
     * @return          返回反转后的头节点
     */
    public  static ListNode reversePart(ListNode startNode,int k){
        Stack<ListNode> stack  = new Stack<>();
        for (int i = 0; i <k ; i++) {
            stack.push(startNode.next);
            startNode =startNode.next;
        }
        ListNode tailNode = startNode.next;
        ListNode firstNode = stack.pop();
        ListNode tempNode = firstNode;
        while (!stack.isEmpty()){
            ListNode newNode = stack.pop();
            tempNode.next = newNode;
            tempNode = newNode;
        }
        tempNode.next = tailNode;
        return firstNode;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        ListNode head6 = new ListNode(6);
        ListNode head7 = new ListNode(7);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        head6.next = head7;

        ListNode node =  reverseKGroup(head1,2);


        list(node);
    }


    private static void list(ListNode node){
        while (node!=null){
            System.out.print(node.val +"-");
            node = node.next;
        }
        System.out.println();
    }
}
