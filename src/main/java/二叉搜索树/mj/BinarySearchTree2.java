package 二叉搜索树.mj;


import 二叉搜索树.mj.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree2<E extends Comparable>  implements BinaryTreeInfo {

    private int size;
    private Node<E> root;



    //添加节点,若节点值已存在  那么直接覆盖
    public void addNode(E element){
        checkElement(element);

        if(root ==null){//添加的节点是根节点
            root = new Node<>(element,null);
            return;
        }

        Node tempNode = root;
        Node parentNode = null ;
        int cmp = 0;
        while (tempNode !=null){
            parentNode  = tempNode;
            //比较结果 如果大于0 那么 element> tempNode.element,== 则二者相等,反之这element< tempNode.element

            cmp = element.compareTo(tempNode.element);

            if(cmp>0){//大于0 那么将数据放到右子树上去
                tempNode = tempNode.right;
            }else if(cmp<0){// 小于0 则放到左子树
                tempNode = tempNode.left;
            }else{// 等于则覆盖
                tempNode.element = element;
                return;
            }
        }

        // 这里tempNode == null
        Node addNode = new Node(element,parentNode);
        if(cmp>0){
            parentNode.right = addNode;
        }else{
            parentNode.left = addNode;
        }
    }


    public boolean contians(E element){
        return getNode(element)!=null;
    }

    public Node getNode(E element){
        if(root==null ){
            return null;
        }

        Node tempNode = root;
        while (tempNode!=null){
            int cmp = element.compareTo(tempNode.element);
            if(cmp ==0) return tempNode;
            else if(cmp>0) tempNode = tempNode.right;
            else tempNode = tempNode.left;
        }
        return null;
    }

    public void preOrder(){
        preOrder(root);
    }
    //前序遍历
    public void preOrder(Node node){
        if(node !=null){
            System.out.print(" "+node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(){
        inOrder(root);
    }
    //中序遍历
    public void inOrder(Node node){
        if(node !=null){
            inOrder(node.left);
            System.out.print(" "+node);
            inOrder(node.right);
        }
    }
    public void postOrder(){
        postOrder(root);
    }
    //中序遍历  中序遍历的结果 要么是升序的要么是降序的
    public void postOrder(Node node){
        if(node !=null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(" "+node);
        }
    }

    public void levelOrder(){
        levelOrder(root);
    }
    //层序遍历，从根节点开始 一层一层的往下遍历,使用队列来做
    public void levelOrder(Node node){
        Queue queue = new LinkedList();
        queue.offer(node);
        while (!queue.isEmpty()){
            Node headNode = (Node) queue.poll();
            System.out.print(" "+headNode);
            if(headNode.left!=null){
                queue.offer(headNode.left);
            }
            if(headNode.right!=null){
                queue.offer(headNode.right);
            }
        }
    }

    //使用层序遍历来计算节点的高度
    public int height2(Node node){
        if(node ==null) return 0;
        int height = 0;
        int levelSize = 1;
        Queue queue  = new LinkedList();
        queue.offer(node);
        while (!queue.isEmpty()){
            Node tempNode = (Node) queue.poll();
            levelSize--;
            if(tempNode.left!=null){
                queue.offer(tempNode.left);
            }
            if(tempNode.right!=null){
                queue.offer(tempNode.right);
            }
            // 其实层序遍历就是一层一层的遍历的，也就是说第N层遍历完之后，队列里就没有了第N层的元素了，有且只有第N+1层的元素，理解这个很重要
            // 那么此时队列的长度就是第N+1层元素的数量
            if(levelSize==0){
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    public int height(Node node){
        if(node == null)return 0;
        return 1+(Math.max(height(node.left),height(node.right)));
    }

    public int height(){
        return height2(root);
    }

    public boolean isCompleteTree(){
        return isCompleteTree(root);
    }

    //判断树是否是完全二叉树
    //完全二叉树的关键是 第一个度不等于2节点之后的所有节点   都必须为叶子节点
    public boolean isCompleteTree(Node node){
        if(node == null )return false;

        Queue queue = new LinkedList();
        queue.offer(node);
        boolean flag = false;
        while (!queue.isEmpty()){
            Node tempNode = (Node) queue.poll();
            if(flag && !tempNode.isLeaf()){//应该为叶子节点  但是却不是叶子节点
                return false;
            }

            Node left = tempNode.left;
            Node right = tempNode.right;
            if(left!=null){
                queue.offer(left);
            }
            if(right!=null){
                queue.offer(right);
            }

            if(left !=null && right!=null){
                continue;
            }else if(left ==null &&right!=null){
                return false;
            }else{ //这是度为1 且左节点不为null，或者度为0的节点
                flag = true;
            }
        }
        return true;
    }



    // 二叉树节点的前驱节点，如下面的二叉树  中序遍历 后的结果是   2 4 5 7 8 9 10
    // 所以7的前驱节点是5，后继节点是8

    //                        7
    //                4               9
    //            2       5       8       10

    //获取二叉树的前驱节点
    private Node<E> predecessor(Node<E> node){
        if(node==null) return null;

        // 如果当前节点的左子树不为空，那么当前节点的前驱节点则必在左子树中，并且前驱节点肯定是左子树中的最大节点
        if(node.left != null){
            Node result = node.left;
            while (result.right!=null){
                result = result.right;
            }
            return result;
        }

        // 如果 左子树为空，那么当前节点的前驱节点可能在其父级几点串中，如果父级节点串中未找到前驱节点 那么该节点吴前驱节点
        // 顺着父节点一直往上找，如果子节点一直是父节点的左节点 ，那么循环终究会结束的，这个这个时候循环退出。此时：node.parent==null  返回node.parent即可
        // 顺着父节点一直往上找，如果突然有个子节点是父节点的右节点，那么说明这个父节点就是要求节点的前驱节点。此时：node.parent.left != node ,就是说node.parent.right == node，返回node.parent即可
        while (node.parent!=null && node.parent.left == node){
            node = node.parent;
        }
        return node.parent;
    }

    public void printPredecessor(E element){
        Node node = getNode(element);
        if(node ==null){
            System.out.println("无前驱节点");
            return;
        }
        System.out.println(predecessor(node));
    }

    //后继节点则与前驱节点想法
    private Node<E> successor(Node<E> node){
        if(node==null) return null;

        if(node.right != null){
            Node result = node.right;
            while (result.left!=null){
                result = result.left;
            }
            return result;
        }

        while (node.parent!=null && node.parent.right == node){
            node = node.parent;
        }
        return node.parent;
    }

    public void printSuccessor(E element){
        Node node = getNode(element);
        if(node ==null){
            System.out.println("无后继节点");
            return;
        }
        System.out.println(successor(node));
    }

    public void remove(E element){
         remove(getNode(element));
    }

    private void remove(Node node){
        if(node ==null){
            return;
        }

        size--;
        //删除的节点度为0
        if(node.isLeaf()){
            Node parent =  node.parent;
            if(parent!=null){
                if(parent.left == node){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }else{// 是叶子节点 但是无父节点  说明该节点是根节点
                root = null;
            }

            return;
        }

        //删除的节点度为2
        if(node.hasTwoChildren()){
            //先找到这个节点的后继节点  这个后继节点肯定在它的右子树种  并且是右子树的最左位置
            Node postNode = successor(node);
            //直接把后继节点的值赋值给删除节点 然后删除后继节点即可
            node.element = postNode.element;

            // 既然是后继节点 那么这个后继节点是肯定没有左节点的 只需要处理右几点即可
            if(postNode.right ==null){
                postNode.parent =null;
            }else{
                postNode.parent.left = postNode.right;
            }
            return;
        }

        //删除的节点度为1

        Node parent = node.parent;
        Node child = node.left != null?node.left:node.right;
        if(parent ==null){
            root = child;
        }else{
            if(parent.left == node){
                parent.left = child;
            }else{
                parent.right = child;
            }
        }
    }



    private void checkElement(E element){
        if(element ==null){
            throw  new IllegalArgumentException("参数错误，element null");
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return  root ==null;
//        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node) node).element;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        public Node(E element,  Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        //是否是叶子节点
        public boolean isLeaf() {
            return left == null && right == null;
        }
        //是否是度为2的节点
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
        @Override
        public String toString() {
            return ""+element;
        }
    }

}
