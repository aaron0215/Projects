// DO NOT EDIT THIS INTERFACE IN ANY WAY -- DO NOT SUBMIT
/**
 * Defines the additional operations required of student's BST class.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public interface BSTADT<K extends Comparable<K>,V> extends SearchTreeADT<K, V> {
	
	/**
	 * Returns the key that is in the root node of this BST.
	 * If root is null, returns null.
	 * @return key found at root node, or null
	 */
	K getKeyAtRoot() ;
	
	/**
	 * Tries to find a node with a key that matches the specified key.
	 * If a matching node is found, it returns the returns the key that is in the left child.
	 * If the left child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the left child of the found key
	 * 
	 * @throws IllegalNullKeyException if key argument is null
	 * @throws KeyNotFoundException if key is not found in this BST
	 */
	K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException;
	
	/**
	 * Tries to find a node with a key that matches the specified key.
	 * If a matching node is found, it returns the returns the key that is in the right child.
	 * If the right child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the right child of the found key
	 * 
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException if key is not found in this BST
	 */
	K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException;
	

	/**
	 * Returns the height of this BST.
	 * H is defined as the number of levels in the tree.
	 * 
	 * If root is null, return 0
	 * If root is a leaf, return 1
	 * Else return 1 + max( height(root.left), height(root.right) )
	 * 
	 * Examples:
	 * A BST with no keys, has a height of zero (0).
	 * A BST with one key, has a height of one (1).
	 * A BST with two keys, has a height of two (2).
	 * A BST with three keys, can be balanced with a height of two(2)
	 *                        or it may be linear with a height of three (3)
	 * ... and so on for tree with other heights
	 * 
	 * @return the number of levels that contain keys in this BINARY SEARCH TREE
	 */
	int getHeight();
	
}
