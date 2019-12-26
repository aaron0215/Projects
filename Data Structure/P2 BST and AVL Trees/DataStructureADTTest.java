import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: add imports as needed for your JUnit tests

/**
 * ABSTRACT super-class with DataStructureADT JUnit tests.
 * 
 * This class contains methods for testing the basic functionality of a DataStructureADT
 * implementation.   Such a d.s. type was designed and tested in Program 1.
 * 
 * This class will now be the super-class of SearchTreeADTTest.  This means that 
 * SearchTreeADTTest inherit all of tests (public and protect methods) from 
 * DataStructureADTTest.  
 * 
 * For Program 2, almost all tests from your p1 DataStructureADTTest class 
 * can be copied and run here without changes.  There are some required changes.
 * 
 * TODO: 1. Copy your DataStructureADTTest methods to this class 
 *       2. Edit your tests to handle the changed names, types, and exception handling requirements
 *          for insert, remove, and get methods.
 *          
 * See @DataStructureADT for more details 
 *          
 * NOTE: this class has changed the visibility of dataStructureInstance
 * and added dataStructureInstance2, and dataStructureInstance3.
 * 
 * dataStructureInstance is still a DataStructure<String,String>.
 * dataStructureInstance2 is a DataStructure<Integer,String>.
 * dataStructureInstance3 is a DataStructure<Integer,Integer>.
 * DO NOT CHANGE THE TYPES, NAMES, OR VISIBLITY OF THOSE FIELDS
 * 
 * @author Debra Deppeler (deppeler@cs.wisc.edu)
 */
abstract class DataStructureADTTest {
	
	// CHANGED FROM P1: fields are protected (so they may be accessed from sub-classes)
	protected DataStructureADT<String,String> dataStructureInstance;
	
	// ADDED FROM P1: added another dataStructureInstance type <Integer,String>
	protected DataStructureADT<Integer,String> dataStructureInstance2;

	
	// CHANGED FROM P1: methods are protected (so they may be accessed from sub-classes)
	protected abstract DataStructureADT<String,String> createInstance();

	// ADDED FROM P1: added method to create another dataStructureInstance type <Integer,String> 
	protected abstract DataStructureADT<Integer,String> createInstance2();

	@BeforeAll
	static void setUpBeforeClass() {
		// UNUSED - may be removed if not using
	}

	@AfterAll
	static void tearDownAfterClass() {
		// UNUSED - may be removed if not using
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
		dataStructureInstance2 = createInstance2();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
		dataStructureInstance2 = null;
	}

	/**
	 * Provided Utility Method for comparing List<K> with other List<K>
	 * 
	 * Helper assert method for comparing lists of various element types.
	 * List must have the same number of elements, 
	 * be of the same type, and have elements that are the same
	 * in the same order.
	 * 
	 * @param list1<?> 
	 * @param list2<?>
	 */
	public void assertEquals(List<?> list1, List<?> list2) {
		assertTrue(list1!=null);
		assertTrue(list2!=null);
		assertTrue(list1.size()==list2.size());
		for (int i=0; i < list1.size(); i++ ) {
			assertTrue(list1.get(i).equals(list2.get(i)));			
		}
	}

	@Test
	void testDS00_empty_ds_size() {
		// It it works for one test, should work for all
		assertTrue(dataStructureInstance.numKeys()==0);
		assertTrue(dataStructureInstance2.numKeys()==0);
	}

	@Test
	void testDS01_insert_one_ds_size() throws IllegalNullKeyException {
		try {
			// It it works for one test, should work for all
			dataStructureInstance.insert("mykey1", "myvalue1");
			dataStructureInstance2.insert(2, "myvalue2");
			System.out.println(dataStructureInstance.numKeys());

			assertTrue(dataStructureInstance.numKeys()==1);
			assertTrue(dataStructureInstance2.numKeys()==1);

		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			fail("Unexpected exception DS01: "+e.getMessage());
		}
	}

	// [OPTIONAL]: you can add basic data structure tests here or test all in BSTTest
	// TODO: 1) copy your tests from this class in P1 here

	// TODO: 2) Edit your tests for exception handling of insert, remove, get as there were changes

	// TODO: 3) add at least one new test of the general data structure operations (methods)
	
}
