package tfc.utils;

/**
 * This is actually surprisingly useful at times
 * @param <V> The class of the first object
 * @param <K> The class of the second object
 */
public class BiObject<V,K> {
	private V object1;
	private K object2;
	private final boolean isFinal;
	
	public BiObject(V object1, K object2, boolean isFinal) {
		this.object1 = object1;
		this.object2 = object2;
		this.isFinal = isFinal;
	}
	
	public BiObject(V object1, K object2) {
		this.object1 = object1;
		this.object2 = object2;
		isFinal = true;
	}
	
	public V getObject1() {
		return object1;
	}
	
	public void setObject1(V object1) {
		if (isFinal) throw new RuntimeException(new IllegalAccessException("Cannot modify a final value."));
		this.object1 = object1;
	}
	
	public K getObject2() {
		return object2;
	}
	
	public void setObject2(K object2) {
		if (isFinal) throw new RuntimeException(new IllegalAccessException("Cannot modify a final value."));
		this.object2 = object2;
	}
	
	public boolean isFinal() {
		return isFinal;
	}
}
