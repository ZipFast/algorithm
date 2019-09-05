public class BST {
    private BSTNode root;

    public BST() {
        this.root = null;
    }
    public BSTNode insert(int t) {
        if (this.root == null) {
            this.root = new BSTNode(null, t);
            return this.root;
        }
        return this.root.insert(t);
    }

    public BSTNode find(int t) {
        if (this.root == null) {
             return null;
        }
        return this.root.find(t);
    }

    public int rank(int t) {
        if (this.root == null) {
            return 0;
        }
        return this.root.rank(t);
    }

    public BSTNode delete(int t) {
        BSTNode node = this.find(t);
        BSTNode deleted = node.delete();
        return deleted;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        for (int i = 0; i < 10; i++) {
            bst.insert(i);
        }
        bst.delete(1);
    }
}
