package 二叉搜索树.mj;


import 二叉搜索树.mj.printer.BinaryTreeInfo;

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
        while (tempNode !=null){
            parentNode  = tempNode;
            //比较结果 如果大于0 那么 element> tempNode.element,== 则二者相等,反之这element< tempNode.element

            int cmp = element.compareTo(tempNode.element);

            if(cmp>0){//大于0 那么将数据放到右子树上去
                tempNode = tempNode.right;
            }else if(cmp<0){// 小于0 则放到左子树
                tempNode = tempNode.left;
            }else{// 等于则覆盖
                tempNode.element = element;
                return;
            }
        }

        Node addNode = new Node(element,parentNode);
        if(parentNode.right == tempNode){
            parentNode.right = tempNode;
        }else{
            parentNode.left = tempNode;
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
    }




}
