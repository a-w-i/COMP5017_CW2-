package comp5017;// or whatever

/**
 *
 * @author your name here
 * template for use of students by D Lightfoot 2025-03-18
 */
 
public  class ContactsBST implements  IContactsDB {
    
   private class Node {
      Contact data;
      Node left, right;
	  private Node(Contact m) { data = m; left = null; right = null; } 
   }
   private Node root; // this is the tree
   private int numEntries;
            
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
    }
    
    /**
     * Determines whether a contact's name exists as a key inside the database
     * @pre name is not null and not empty string or all blanks
     * @param name the contact name (key) to locate
     * @return true iff the name exists as a key in the database
     */
    @Override
    public boolean containsName(String name){return get(name) != null;}
        
    private Contact retrieve (Node tree, String name) {
		// to do
		return null; // not really
	}
	/**
     * Returns a Contact object mapped to the supplied name.
     * @pre name not null and not empty string or all blanks
     * @param name The Contact name (key) to locate
     * @return the Contact object mapped to the key name if the name
        exists as key in the database, otherwise null
     */
    @Override
    public Contact get(String name){ 
       // assert statements here
       return retrieve(root, name);
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
	   // to do
       return null; // not really
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
      Contact returned;
      returned = null; // for now
      root = insert(root, contact);
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
    public  Contact remove(String name){        
    /* based on Object-Oriented Programming in Oberon-2
       Hanspeter Mössenböck Springer-Verlag 1993, page 78
       transcribed into Java by David Lightfoot
    */
    Node parent = null, del, p = null, q = null;
    Contact result;
	del = root;
	while (del != null && !del.data.getName().equals(name)) {
        parent = del;
        if (name.compareTo(del.data.getName()) < 0)
            del = del.left;
        else 
	    del = del.right;
	}// del == null || del.data.getName().equals(name))
    if(del != null) { // del.data.getName().equals(name)
        // find the pointer p to the node to replace del 
        if (del.right == null) p = del.left;
        else if (del.right.left == null) {
            p = del.right; p.left = del.left;
        } else {
            p = del.right;
            while (p.left != null) {q = p; p = p.left;}
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
        return result;
    } // delete
    
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
}