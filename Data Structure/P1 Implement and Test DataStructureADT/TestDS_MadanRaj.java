// TO TEST A DATA STRUCTURE CLASS:
//
// for each data structure class file you wish to test:
//     1. create a test class (like this one) 
//     2. edit the actual type being created (line 16)
//     3. run this test class 
//     4. OR, configure Eclipse project to run all tests
//        Eclipse: Run->Run Configurations->"Run All Tests..."

@SuppressWarnings("rawtypes")
public class TestDS_MadanRaj extends DataStructureADTTest {

	// the return type must be the name of the data structure class you are testing
	@Override
	protected DataStructureADT createInstance() {
		return new DS_MadanRaj();
	}

}
