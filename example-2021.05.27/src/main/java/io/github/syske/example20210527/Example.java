package io.github.syske.example20210527;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021-05-27 example
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-27 21:52
 */
public class Example {
    public static void main(String[] args) {
        Integer[] root = {1, null, 2, 3};
        List<BinarySearchTree<Integer>> binarySearchTreeList = new ArrayList<>();
        BinarySearchTree<Integer> binarySearchTree = null;
        for (int i = 0; i < root.length - 2; i++) {
            binarySearchTree = new BinarySearchTree<>(root[i], root[i + 1], root[i + 2]);
            binarySearchTreeList.add(binarySearchTree);
        }
        System.out.println(binarySearchTreeList);
        System.out.println(binarySearchTree);
    }
}
