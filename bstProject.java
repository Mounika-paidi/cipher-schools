import java.util.ArrayList;
import java.util.Scanner;
class BSTNode {
    int key;
    BSTNode left, right;

    public BSTNode(int item) {
        key = item;
        left = right = null;
        //This class represents the Nodes of Binary Search tree
    }
}
class BinarySearchTree {
    BSTNode root;
    BinarySearchTree() {
        root = null;
        // Constructor
    }

    
    void insert(int key) {
        root = recursiveNodeOfBST(root, key);
        // Insert method
    }

    
    BSTNode recursiveNodeOfBST(BSTNode root, int key) {
        if (root == null) {
            root = new BSTNode(key);
            return root;
        }

        if (key < root.key)
            root.left = recursiveNodeOfBST(root.left, key);
        else if (key > root.key)
            root.right = recursiveNodeOfBST(root.right, key);

        return root;
        // Recursive insert function
    }

    
    boolean search(int key) {
        return recursiveSearch(root, key);
     //Serch method
    }

    
    boolean recursiveSearch(BSTNode root, int key) {
        if (root == null) {
            return false;
        }
        if (root.key == key) {
            return true;
        }
        if (root.key > key) {
            return recursiveSearch(root.left, key);
        }
        return recursiveSearch(root.right, key);
    }

   
    void deleteKey(int key) {
        root = recursiveDelete(root, key);
         // Delete method
    }

   
    BSTNode recursiveDelete(BSTNode root, int key) {
        if (root == null) return root;

        if (key < root.key)
            root.left = recursiveDelete(root.left, key);
        else if (key > root.key)
            root.right = recursiveDelete(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = recursiveDelete(root.right, root.key);
        }

        return root;
    }

    int minValue(BSTNode root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

   
    void inOrderTraversal(BSTNode root, ArrayList<Integer> result) {
        if (root != null) {
            inOrderTraversal(root.left, result);
            result.add(root.key);
            inOrderTraversal(root.right, result);
             //This is used to collect elements in sorted order(inorder)
        }
    }

    // pre-order
    void preOrderTraversal(BSTNode root, ArrayList<Integer> result) {
        if (root != null) {
            result.add(root.key);
            preOrderTraversal(root.left, result);
            preOrderTraversal(root.right, result);
        }
    }

    //post-order
    void postOrderTraversal(BSTNode root, ArrayList<Integer> result) {
        if (root != null) {
            postOrderTraversal(root.left, result);
            postOrderTraversal(root.right, result);
            result.add(root.key);
        }
    }

   
    ArrayList<Integer> getSortedArray() {
        ArrayList<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    
    ArrayList<Integer> getPreOrderArray() {
        ArrayList<Integer> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    
    ArrayList<Integer> getPostOrderArray() {
        ArrayList<Integer> result = new ArrayList<>();
        postOrderTraversal(root, result);
        return result;
    }
}

public class bstProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            bst.insert(value);
        }

        boolean sloving = true; // Initialize the loop control variable to true

        while (sloving) { // Loop is running as long as sloving is true
            System.out.println("\nCurrent BST (In-Order): " + bst.getSortedArray());
            System.out.println("Current BST (Pre-Order): " + bst.getPreOrderArray());
            System.out.println("Current BST (Post-Order): " + bst.getPostOrderArray());

            System.out.println("\nMenu:");
            System.out.println("1. Insert an element");
            System.out.println("2. Search an element");
            System.out.println("3. Delete an element");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int insertValue = scanner.nextInt();
                    bst.insert(insertValue);
                    System.out.println("Inserted " + insertValue);
                    break;

                case 2:
                    System.out.print("Enter value to search: ");
                    int searchValue = scanner.nextInt();
                    ArrayList<Integer> sortedArray = bst.getSortedArray();
                    int position = sortedArray.indexOf(searchValue);
                    boolean found = bst.search(searchValue);
                    if (found) {
                        System.out.println("Search " + searchValue + ": Found at position " + position);
                    } else {
                        System.out.println("Search " + searchValue + ": Not Found");
                    }
                    break;

                case 3:
                    System.out.print("Enter value to delete: ");
                    int deleteValue = scanner.nextInt();
                    bst.deleteKey(deleteValue);
                    System.out.println("Deleted " + deleteValue);
                    break;

                case 4:
                    sloving = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
