/**
 * Filename:   Package.java
 * Project:    p4
 * Authors:    Debra Deppeler
 * 
 * Class representation of the package object found in a json file.
 * 
 * A package is a package name and an array of the names of other packages
 * that this package depends upon.
 * 
 */
public class Package {
	private String name;	
	private String[] dependencies;
	
	public Package() {
		
	}
	
	public Package(String name, String[] dependencies) {
		this.name = name;
		this.dependencies = dependencies;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String[] getDependencies() {
		return this.dependencies;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDependencies(String[] dependencies) {
		this.dependencies = dependencies;
	}
}
