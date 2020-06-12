
/**
 * @Description  ：测试结果
 * @author       : 王作虎
 */
public class Main {

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
//        avlTree.add(4);  RL 情景
//        avlTree.add(5);
//        avlTree.add(7);
//        avlTree.add(8);
//        avlTree.add(9);
//        System.out.println("我是根节点"+avlTree.root.value);
//        System.out.println("我是根节点右子树"+avlTree.root.rightChild.value);
//        System.out.println("我是根节点左子树"+avlTree.root.leftChild.value );
//        System.out.println("我是根节点右子树的左子树"+avlTree.root.rightChild.leftChild.value );
//        System.out.println("我是根节点右子树的右子树"+avlTree.root.rightChild.rightChild.value );
//
//        System.out.println("----------------华丽的分割线------------------");
//
//        avlTree.add(6);
//        System.out.println("我是根节点"+avlTree.root.value);
//        System.out.println("我是根节点右子树"+avlTree.root.rightChild.value);
//        System.out.println("我是根节点左子树"+avlTree.root.leftChild.value );
//        System.out.println("我是根节点左子树的左子树"+avlTree.root.leftChild.leftChild.value );
//        System.out.println("我是根节点右子树的右子树"+avlTree.root.rightChild.rightChild.value );
//        System.out.println("我是根节点左子树的右子树"+avlTree.root.leftChild.rightChild.value );

            avlTree.add(8);
            avlTree.add(5);
            avlTree.add(9);
            avlTree.add(2);
            avlTree.add(7);
            System.out.println("我是根节点"+avlTree.root.value);
            System.out.println("我是根节点右子树"+avlTree.root.rightChild.value);
            System.out.println("我是根节点左子树"+avlTree.root.leftChild.value );
            System.out.println("我是根节点左子树的左子树"+avlTree.root.leftChild.leftChild.value );
            System.out.println("我是根节点左子树的右子树"+avlTree.root.leftChild.rightChild.value );

            System.out.println("----------------华丽的分割线------------------");

            avlTree.add(6);
            System.out.println("我是根节点"+avlTree.root.value);
            System.out.println("我是根节点右子树"+avlTree.root.rightChild.value);
            System.out.println("我是根节点左子树"+avlTree.root.leftChild.value );
            System.out.println("我是根节点左子树的左子树"+avlTree.root.leftChild.leftChild.value );
            System.out.println("我是根节点右子树的右子树"+avlTree.root.rightChild.rightChild.value );
            System.out.println("我是根节点左子树的右子树"+avlTree.root.leftChild.rightChild.value );

//        System.out.println("------------超级华丽的分割线------------");
//
//
//        avlTree.remove(8);
//
//        System.out.println("我是根节点"+avlTree.root.value);
//        System.out.println("我是根节点右子树"+avlTree.root.rightChild.value);
//        System.out.println("我是根节点左子树"+avlTree.root.leftChild.value );
//        System.out.println("我是根节点左子树的左子树"+avlTree.root.leftChild.leftChild.value );
//        System.out.println("我是根节点左子树的右子树"+avlTree.root.leftChild.rightChild.value );
//        System.out.println("我是根节点右子树的右子树"+avlTree.root.rightChild.rightChild.value );

        System.out.println("------------超级华丽的分割线------------");


        avlTree.remove(5);

        System.out.println("我是根节点"+avlTree.root.value);
        System.out.println("我是根节点右子树"+avlTree.root.rightChild.value);
        System.out.println("我是根节点左子树"+avlTree.root.leftChild.value );

        System.out.println("我是根节点左子树的右子树"+avlTree.root.leftChild.rightChild.value );
        System.out.println("我是根节点右子树的右子树"+avlTree.root.rightChild.rightChild.value );

        System.out.println("我是根节点左子树的左子树"+avlTree.root.leftChild.leftChild.value );




    }
}
