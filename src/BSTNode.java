public class BSTNode {
    private int key;
    private BSTNode right;
    private BSTNode left;
    private BSTNode parent;
    private int size;

    public BSTNode(BSTNode parent ,int key) {
        this.key = key;
        this.parent = parent;
        this.left = null;
        this.right = null;
        size = 1;
    }

    public void update_size() {
        this.size = 1 + (this.left == null ? 0 : this.left.size) + (this.right == null ? 0 : this.left.size);
    }
    public BSTNode insert(int x) {
        this.size += 1;
        if (x < this.key) {
            if (this.left == null) {
                this.left = new BSTNode(this, x);
                return this.left;
            } else {
                return this.left.insert(x);
            }
        } else {
            if (this.right == null) {
                this.right = new BSTNode(this, x);
                return this.right;
            } else {
                return this.right.insert(x);
            }
        }
    }

    public BSTNode find(int x) {
        if (this.key == x) {
            return this;
        }

        if (x < this.key) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.find(x);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.find(x);
            }
        }
    }

    // return the number of keys <= t in the subtree rooted at this node
    public int rank(int t) {
        int left_size = (this.left == null ? 0 : this.left.size);
        if (t == this.key) {
            return 1 + left_size;
        }
        if (t < this.key) {
            if (this.left == null) {
                return 0;
            } else {
                return this.left.rank(t);
            }
        } else {
            if (this.right == null) {
                return 1 + left_size;
            } else {
                return 1 + left_size + this.right.rank(t);
            }
        }
    }

    public BSTNode minimum() {
        BSTNode p = this;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public BSTNode successor() {
        if (this.right != null) {
            return this.right.minimum();
        } else {
            if (this.parent == null) {
                return null;
            } else {
                BSTNode p = this;
                while (p.parent != null && p.parent.right == p) {
                    p = p.parent;
                }
                return p.parent;
            }
        }
    }

    public BSTNode delete()  {
        if (this.left == null) {
            BSTNode p = this.right;
            if (this.parent.left == this) {
                this.parent.left = p;
                p.parent = this.parent;
            }
            if (this.parent.right == this) {
                this.parent.right = p;
                p.parent = this.parent;
            }
            return this;
        } else if (this.right == null) {
            BSTNode p = this.left;
            if (this.parent.left == this) {
                this.parent.left = p;
                p.parent = this.parent;
            }
            if (this.parent.right == this) {
                this.parent.right = p;
                p.parent = this.parent;
            }
            return this;
        } else {
            BSTNode p = this.successor();
            int tmp = p.key;
            p.key = this.key;
            this.key = tmp;
            return p.delete();
        }
    }
}

