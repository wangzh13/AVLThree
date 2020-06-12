

import java.util.Comparator;

public class AVLTree<E> {

    public Node root;
    public Integer size = 0;
    public Comparator comparator;

    public AVLTree(){

    }
    public AVLTree(Comparator comparator){
        this.comparator=comparator;
    }

    // 表示节点
    static class Node<E>{
         Node parent;//父节点
         Node leftChild;//左节点
         Node rightChild;//右节点
         int height = 1;//高度，咱们这里默认给1
         E value;//节点的值

        public Node() {
        }

        public Node(Node parent, Node leftChild, Node rightChild, E value) {
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.value = value;
        }
        /**
         * 判断节点的度数
         *
         */
        public boolean isLeaf() {
            //判断当前节点是否是叶子节点（其实就是度的计算）
            return leftChild == null && rightChild == null;
        }

        public boolean hasTwoChildren() {
            //判断当前节点的度数（其实就是查看左右子树是否都存在）
            return leftChild != null && rightChild != null;
        }

        public boolean isLeftChild() {
            //判断当前节点是否是父节点的左节点（如果本节点有父节点的话）
            return parent != null && this == parent.leftChild;
        }

        public boolean isRightChild() {
            //判断当前节点是否是父节点的右节点（如果本节点有父节点的话）
            return parent != null && this == parent.rightChild;
        }

        public void updateHeight(){ //更新节点高度，节点高度等于左右子树中最高树的高度，再加上根节点高度（1）
            int leftHeight = leftChild == null ? 0: leftChild.height;
            int rightHeight = rightChild == null ? 0: rightChild.height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        public int balanceFactor(){//计算平衡因子(平衡因子其实就是左子树的高度减去右子树的绝对值)
            int leftHeight = leftChild == null ? 0: leftChild.height;
            int rightHeight = rightChild == null ? 0: rightChild.height;
            return  Math.abs(leftHeight-rightHeight);
        }

        private Node tallerChild(){
            //返回当下节点两个子树当中最高的树
            //如果左右两个子树，高度一致，就按照当下是父节点的左子树还是右子树，进行返回（如果是根节点直接返回右子树）
            int leftHeight = leftChild == null ? 0: leftChild.height;
            int rightHeight = rightChild == null ? 0: rightChild.height;
            if (leftHeight > rightHeight) return leftChild;
            if (leftHeight < rightHeight) return rightChild;
            return isLeftChild() ? leftChild : rightChild;//如果左右两个子树，高度一致，就按照当下是父节点的左子树还是右子树，进行返回（如果是根节点直接返回右子树）
        }
    }

    public boolean add(E value){
        //这步没什么好说的，就是当树为空（根节点都是空，怎么可能有其余结构），将节点当做根节点
        if (root == null){
            Node<E> node = new Node(null,null,null,value);
            root = node;
            size++;
            balanceTree(node);
            return true;
        }
        Node node = root;
        Node parentNode = new Node();
        int result = 0;
        while(node!=null){
            //首先确定插入节点的位置（确定他是哪个节点下的子节点）
            //从根节点开始比较，依次跟节点比较，直到树的末端（没有后续节点）
            result = compare((E) node.value,value);
            parentNode = node;
            if (result>0){//说明新增值小于比较的节点值，接着与左子树进行比较
                node = node.leftChild;
            }else if (result<0){
                node = node.rightChild;
            }else { //相同就不进行操作了，说明树当中有了
                node.value = value;//如果相同就进行覆盖处理
            }
        }
        //确定位置之后，知道是哪个节点下的子节点，再根据result的大小确定是该节点的左子树还是右子树
        if (result>0){
            Node<E> nodeAdd = new Node(parentNode,null,null,value);
            parentNode.leftChild = nodeAdd;
            balanceTree(nodeAdd);
        }else{
            Node<E> nodeAdd = new Node(parentNode,null,null,value);
            parentNode.rightChild = nodeAdd;
            balanceTree(nodeAdd);
        }
        size++;

        return true;
    }

    private void balanceTree(Node node){
        while ((node = node.parent)!=null){
            if (node.balanceFactor()<=1){
                node.updateHeight();
            }else {
                toBalance(node);
                break;
            }
        }
    }

    private void toBalance(Node grandNode){
        Node<E> parent = (grandNode).tallerChild();//找到左右子树当中最高的树
        Node<E> node = (parent).tallerChild();

        if (parent.isRightChild()){
            if (node.isRightChild()){//RR
                leftRotate(grandNode);
            }else {//RL
                rightRotate(parent);
                leftRotate(grandNode);
            }
        }else {
            if (node.isLeftChild()){//LL
                rightRotate(grandNode);
            }else {//LR
                leftRotate(parent);
                rightRotate(grandNode);
            }
        }
    }

    private void leftRotate(Node grandNode){
        Node parentNode = grandNode.rightChild; //右旋转，所以出问题的是grandNode的右子树，我们将它命名为parentNode
        grandNode.rightChild = parentNode.leftChild; // parentNode的左子树，给予grandNode的右子树
        parentNode.leftChild = grandNode; // parentNode的左子树就变为grantNode

        parentNode.parent = grandNode.parent; //parentNode的父节点变为，grantNode的父节点
        if (grandNode.isLeftChild()){ // 判读grandNode原本是父节点的左子树还是右子树，相应进行改变
            grandNode.parent.leftChild = parentNode;
        }else if (grandNode.isRightChild()){
            grandNode.parent.rightChild = parentNode;
        } else {
            root = parentNode; //当grandNode既不是父节点的左子树，也不是父节点的右子树，那么相当于grandNode是根节点，相应进行改变
        }
        grandNode.parent = parentNode; //grantNode的父节点变为parentNode
        if (grandNode.rightChild != null ){
            grandNode.rightChild.parent = grandNode;//parentNode原本的左子树（现在grandNode的右子树）的父节点变为grandNode
        }

        parentNode.updateHeight();
        grandNode.updateHeight();
    }

    private void rightRotate(Node grandNode){
        Node parentNode = grandNode.leftChild; //右旋转，所以出问题的是grandNode的左子树，我们将它命名为parentNode
        grandNode.leftChild = parentNode.rightChild; // parentNode的右子树，给予grandNode的左子树
        parentNode.rightChild = grandNode; // parentNode的右子树就变为grantNode

        parentNode.parent = grandNode.parent; //parentNode的父节点变为，grantNode的父节点

        if (grandNode.isLeftChild()){ // 判读grandNode原本是父节点的左子树还是右子树，相应进行改变
            grandNode.parent.leftChild = parentNode;
        }else if (grandNode.isRightChild()){
            grandNode.parent.rightChild = parentNode;
        } else {
            root = parentNode; //当grandNode既不是父节点的左子树，也不是父节点的右子树，那么相当于grandNode是根节点，相应进行改变
        }
        grandNode.parent = parentNode; //grantNode的父节点变为parentNode
        if (grandNode.rightChild != null ){
            grandNode.rightChild.parent = grandNode;//parentNode原本的左子树（现在grandNode的右子树）的父节点变为grandNode
        }

        parentNode.updateHeight();
        grandNode.updateHeight();
    }

    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    public int compare(E e1,E e2){
       if (comparator!=null){
           //如果通过构造函数传入比较器将优先使用比较器
           return  comparator.compare(e1,e2);
       }
       return ((Comparable)e1).compareTo(e2);
    }

    public void remove(E element){
       Node node = queryNode(element);
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<E> s = predecessor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.value = s.value;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<E> replacement = node.leftChild != null ? node.leftChild : node.rightChild;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.leftChild) {
                node.parent.leftChild = replacement;
            } else { // node == node.parent.right
                node.parent.rightChild = replacement;
            }

            // 删除节点之后的处理
            balanceTree(node);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;

            // 删除节点之后的处理
            //afterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.leftChild) {
                node.parent.leftChild = null;
            } else { // node == node.parent.right
                node.parent.rightChild = null;
            }

            // 删除节点之后的处理
            balanceTree(node);
        }
    }

    public Node queryNode (E element){

        //从根节点往下进行寻找比较
        //值相同返回，值大则往右子树寻找
        //值小则往左子树寻找
        Node<E> node = root;
        while (node!=null){
            int result = compare(node.value,element);
            if (result>0){
                node = node.leftChild;
            }else if (result<0){
                node = node.rightChild;
            }else {
                return node;
            }
        }
        return null;
    }

    private Node predecessor(Node node){
        if (node == null){
            return null;
        }

        Node nodeLeft = node.leftChild;
        if (nodeLeft != null){
            while (nodeLeft.rightChild!=null){
                nodeLeft = nodeLeft.rightChild;
            }
            return nodeLeft;
        }

        return null;
    }

    private Node successor(Node node){
        if (node == null){
            return null;
        }

        Node noderight = node.rightChild;
        if (noderight != null){
            while (noderight.leftChild!=null){
                noderight = noderight.leftChild;
            }
            return noderight;
        }

        return null;
    }



}
