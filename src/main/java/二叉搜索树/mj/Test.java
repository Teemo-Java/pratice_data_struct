package 二叉搜索树.mj;

import org.yaml.snakeyaml.nodes.Node;
import 二叉搜索树.mj.printer.BinaryTrees;

public class Test {

    public static void main(String[] args) {
        test1();
    }

    // 测试前驱与后继节点
    public static void  test1(){
        Integer[] arr  = { 7, 4, 9 ,2, 5 ,8 ,10};

        BinarySearchTree2 bst = new BinarySearchTree2();
        for (Integer integer : arr) {
            bst.addNode(integer);
        }
        BinaryTrees.println(bst);

//        bst.printPredecessor(10);
        bst.printSuccessor(10);
    }


    public static  void test(){
        //        Integer[] arr  = {5,3,6,9,1,8,7,4};
        Integer[] arr  = {7, 4, 9, 2, 5,1};


        BinarySearchTree2 bst = new BinarySearchTree2();
        for (Integer integer : arr) {
            bst.addNode(integer);
        }

        BinaryTrees.println(bst);

//        System.out.println(bst.contians(8));

//        bst.preOrder();
//        System.out.println();
//        bst.inOrder();
//        System.out.println();
//        bst.postOrder();
//        System.out.println();
//        bst.levelOrder();

//        System.out.println(bst.height());
        System.out.println(bst.isCompleteTree());
    }
}
