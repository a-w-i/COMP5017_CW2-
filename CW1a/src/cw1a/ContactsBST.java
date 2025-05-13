package cw1a;// or whatever

/**
 *
 * @author your name here\a\zAZSX|CD
 * template for use of students by D Lightfoot 2025-03-18
 */

public  class ContactsBST implements  IContactDB {

    private class Node {
        Contact data;
        Node left, right;
        private Node(Contact m) { data = m; left = null; right = null; }
    }
    private Node root; // this is the tree
    private int numEntries;
    private int totalVisited;

    public ContactsBST() {
        System.out.println("Binary Search Tree");
        clearDB();
    }

    /**
     * Empties the database.
     * @pre true
     */
    @Override
    public void clearDB() {
        root = null; // garbage collector will tidy up
        numEntries = 0;
        totalVisited = 0;
    }

    /**
     * Determines whether a contact's name exists as a key inside the database
     * @pre name is not null and not empty string or all blanks
     * @param name the contact name (key) to locate
     * @return true iff the name exists as a key in the database
     */
    @Override
    public boolean containsName(String name) {
        resetTotalVisited();
        Contact found = retrieve(root, name);
        System.out.println("[containsName] Contact: " + name);
        System.out.println("[containsName] Nodes visited: " + totalVisited);
        return found != null;
    }

    private Contact retrieve(Node tree, String name) {
        if (tree == null || name == null || name.trim().isEmpty()) {
            return null;
        }

        totalVisited++;
        System.out.println("Visited node: " + tree.data.getName());

        int comparison = name.compareTo(tree.data.getName());
        if (comparison == 0) {
            return tree.data;
        } else if (comparison < 0) {
            return retrieve(tree.left, name);
        } else {
            return retrieve(tree.right, name);
        }
    }

    /**
     * Returns a Contact object mapped to the supplied name.
     * @pre name not null and not empty string or all blanks
     * @param name The Contact name (key) to locate
     * @return the Contact object mapped to the key name if the name
     * exists as key in the database, otherwise null
     */
    @Override
    public Contact get(String name){
        assert name != null && !name.trim().isEmpty() : "Name must not be null or empty";
        resetTotalVisited();
        Contact result = retrieve(root, name);
        System.out.println("[get] Contact: " + name);
        System.out.println("[get] Nodes visited: " + totalVisited);
        return result;
    }

    /**
     * Returns the number of members in the database
     * @pre true
     * @return number of members in the database.
     */
    @Override
    public int size() {return numEntries;}

    /**
     * Determines if the database is empty or not.
     * @pre true
     * @return true iff the database is empty
     */
    @Override
    public boolean isEmpty() { return size() == 0; }

    private Node insert(Node tree, Contact employee) {
        assert employee != null && !employee.getName().trim().isEmpty() : "Contact or name is invalid";

        if (tree == null) {
            numEntries++;
            System.out.println("Inserting: " + employee.getName());
            return new Node(employee);
        }

        int comparison = employee.getName().compareTo(tree.data.getName());

        if (comparison < 0) {
            tree.left = insert(tree.left, employee);
        } else if (comparison > 0) {
            tree.right = insert(tree.right, employee);
        } else {
            System.out.println("Overwriting: " + employee.getName());
            tree.data = employee;
        }

        return tree;
    }

    /**
     * Inserts an Contact object into the database, with the key of the supplied
     * contact's name.
     * Note: If the name already exists as a key, then then the original entry
     * is overwritten.
     * This method must return the previous associated value
     * if one exists, otherwise null
     *
     * @pre contact not null and contact name not empty string or all blanks
     */
    @Override
    public Contact put(Contact contact){
        assert contact != null && !contact.getName().trim().isEmpty() : "Contact must be valid and name non-empty";

        resetTotalVisited();
        Contact returned = get(contact.getName());
        root = insert(root, contact);
        System.out.println("[put] Contact: " + contact.getName());
        System.out.println("[put] Nodes visited: " + totalVisited);
        return returned;
    }

    /**
     * Removes and returns a contact from the database, with the key
     * the supplied name.
     * @param name The name (key) to remove.
     * @pre name not null and name not empty string or all blanks
     * @return the removed contact object mapped to the name, or null if
     * the name does not exist.
     */
    @Override
    public Contact remove(String name){
        assert name != null && !name.trim().isEmpty() : "Name must not be null or empty";

        resetTotalVisited();
        System.out.println("[remove] Contact: " + name);
        Node parent = null, del, p = null, q = null;
        Contact result;
        del = root;
        while (del != null && !del.data.getName().equals(name)) {
            totalVisited++;
            System.out.println("Visited node: " + del.data.getName());
            parent = del;
            if (name.compareTo(del.data.getName()) < 0)
                del = del.left;
            else
                del = del.right;
        }
        if(del != null) {
            totalVisited++;
            System.out.println("Visited node: " + del.data.getName());
            if (del.right == null) p = del.left;
            else if (del.right.left == null) {
                p = del.right; p.left = del.left;
            } else {
                p = del.right;
                while (p.left != null) {
                    totalVisited++;
                    System.out.println("Visited node: " + p.data.getName());
                    q = p; p = p.left;
                }
                q.left = p.right; p.left = del.left; p.right = del.right;
            }
            if(del == root) root = p;
            else if (del.data.getName().compareTo(parent.data.getName()) < 0)
                parent.left = p;
            else parent.right = p;
            numEntries--;
            result = del.data;
        }
        else result = null;

        System.out.println("[remove] Nodes visited: " + totalVisited);
        return result;
    }

    private void traverse(Node tree) {
        // to do
    }

    /**
     * Prints the names and affiliations of all the members in the database in
     * alphabetic order.
     * @pre true
     */
    @Override
    public void displayDB(){
        traverse(root);
    }

    @Override
    public void resetTotalVisited() {
        totalVisited = 0;
    }

    /**
     * @pre true
     * @return number of nodes visited (for monitoring/logging)
     */
    @Override
    public int getTotalVisited() {
        return totalVisited;
    }

    /**
     * @pre true
     * @return number of entries (contacts) in the database
     */
    @Override
    public int getNumEntries() {
        return numEntries;
    }
}

