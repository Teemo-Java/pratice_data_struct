package 二叉搜索树.mj;

import org.yaml.snakeyaml.nodes.Node;
import 二叉搜索树.mj.printer.BinaryTrees;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        test2();
    }

    public static void test2(){

        BinarySearchTree2 bst = new BinarySearchTree2();

        Random random = new Random(1);
        Integer size = 500;
        Integer max = 2000;
        Set<Integer> set = new HashSet<>(size);
        for (int i = 0; i <2000000 ; i++) {
            int ran1 = random.nextInt(max);
            if(set.size()!=size){
                set.add(ran1);
            }else{
                break;
            }
        }


        List<Integer> list = new ArrayList<>(set);

        if(list.size()==size){
            for (int i = 0; i < list.size(); i++) {
                bst.addNode(list.get(i));
            }
            System.out.println("------------------删除前的树结构--------------------");
//            BinaryTrees.println(bst);
            for (int i = 0; i < list.size(); i++) {
//                System.out.println("-----------------删除节点"+list.get(i)+"后的树结构---------------------");
                bst.remove(list.get(i));
//                BinaryTrees.println(bst);
            }
            System.out.println("------删除后的结果------");
            BinaryTrees.println(bst);
        }else{
            System.out.println("测试失败");
        }
    }

    // 测试前驱与后继节点
    public static void  test1(){

        //http://www.520it.com/binarytrees/  这个网站可以很好的看效果
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
