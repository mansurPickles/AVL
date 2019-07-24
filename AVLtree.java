public class AVLtree {

    Node root;

    AVLtree(){
        root = null;
    }

    int height (Node n){
        if (n == null){
            return 0;
        }
        else {
            return n.height;
        }
    }

    int max (int a, int b){
        if (a >= b){
            return a;
        }
        else {
            return b;
        }
    }

    Node rotateRight(Node n){
        Node left = n.left;
        Node child = left.right;

        //rotate
        left.right = n;
        n.left = child;

        //update heights
        n.height = max(height(n.left), height(n.right)) + 1;
        left.height = max(height(left.left), height(left.right)) + 1;

        return left;
    }

    Node rotateLeft(Node n){
        Node right = n.right;
        Node child = right.left;

        //rotate
        right.left = n;
        n.right = child;

        //update heights
        n.height = max(height(n.left), height(n.right)) + 1;
        right.height = max(height(right.left), height(right.right)) + 1;
        return right;

    }

    int getBalance(Node n){
        if (n==null){
            return 0;
        }

        else {
            return(height(n.left) - height(n.right));
        }
    }

    Node insert(Node n, int val){
        if (n==null){
            return new Node(val);
        }

        else if(val < n.val){
            n.left = insert(n.left, val);
        }

         else if(val > n.val){
             n.right = insert(n.right, val);
        }

         else if (val == n.val){
             return n;
        }

         n.height = max (height(n.left), height(n.right)) + 1;

         int balance = getBalance(n);

         //left left rotation
        if(balance >1 && val < n.left.val){
            return rotateRight(n);
        }

        //right right rotation
        else if (balance < -1 && val > n.right.val){
            return rotateLeft(n);
        }

        //left right rotation
        else if (balance > 1 && val > n.left.val){
            n.left = rotateLeft(n.left);
            return rotateRight(n);
        }

        //right left rotation
        else if (balance < -1 && val < n.right.val){
            n.right = rotateRight(n.right);
            return rotateLeft(n);
        }

        return n;
    }

    void preOrder(Node n){
        if (n!=null){
            System.out.print(n.val + " ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    void printTree(Node n, int level){

        if (n!=null){
            if (n.right != null){
                printTree(n.right, level +1);
            }
            System.out.println(spacer(level) + "[" + n.val + "]");

            if (n.left != null){
                printTree(n.left, level +1);

            }
        }


    }

    void printTree(){
        printTree(root,0);
    }

    String spacer (int level){
        String str = "";
        for (int i=0; i< level; i++){
            str += "    ";
        }
        return str;
    }

}
