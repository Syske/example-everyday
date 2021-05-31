package io.github.syske.example20210527;

import java.nio.BufferUnderflowException;

/**
 * 树
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-27 21:51
 */
class TreeNode {
    Object element;
    TreeNode firstChild;
    TreeNode nextSibling;
}


public class BinarySearchTree<T extends Comparable<? super T>> {
    /**
     * 二叉树
     */
    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode() {
        }

        public BinaryNode(T element) {
            this.element = element;
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "BinaryNode{" +
                    "element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(T element) {
        root = new BinaryNode<>(element);
    }

    public BinarySearchTree(T element, T left, T right) {
        root = new BinaryNode<T>(element, new BinaryNode<>(left), new BinaryNode<>(right));
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                '}';
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return findMin(root).element;
    }

    public T findMax() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return findMax(root).element;
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return finMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.left;
            }
        }
        return t;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = insert(x, root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }

    }

    private BinaryNode<T> finMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return finMin(t.left);
    }

    private BinaryNode<T> finMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<T>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return t;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else  {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode<T> t) {
       if (t != null) {
           printTree(t.left);
           System.out.println(t.element);
           printTree(t.right);
       }
    }

    private int height(BinaryNode<T> t) {
        if (t == null) {
            return -1;
        } else {
            return 1 + Math.max(height(t.left), height(t.right));
        }
    }
}

class UnderflowException extends Exception {
    public UnderflowException() {
    }
}

