import java.util.List;

// An intermediate type that extends a basic DataStructureADT
// and adds traversal operations as used in search trees.

public interface SearchTreeADT<K extends Comparable<K>,V> extends DataStructureADT<K, V> {

	/**
	 * Returns the keys of the data structure in sorted order.
	 * In the case of binary search trees, the visit order is: L V R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in-order
	 */
	List<K> getInOrderTraversal();
	
	/**
	 * Returns the keys of the data structure in pre-order traversal order.
	 * In the case of binary search trees, the order is: V L R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in pre-order
	 */
	List<K> getPreOrderTraversal();

	/**
	 * Returns the keys of the data structure in post-order traversal order.
	 * In the case of binary search trees, the order is: L R V 
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in post-order
	 */
	List<K> getPostOrderTraversal();

	/**
	 * Returns the keys of the data structure in level-order traversal order.
	 * 
	 * The root is first in the list, then the keys found in the next level down,
	 * and so on. 
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in level-order
	 */
	List<K> getLevelOrderTraversal();

}
