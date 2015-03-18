package q2;

public class Knoop<E> {
	private Knoop<E> leftChild;
	private Knoop<E> rightChild;
	private E userObject;
	
	public Knoop(Knoop<E> leftChild, Knoop<E> rightChild, E userObject) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.userObject = userObject;
	}
	
	public Knoop<E> getLeft(){
		return this.leftChild;
	}
	
	public Knoop<E> getRight(){
		return this.rightChild;
	}
	
	public E get(){
		return this.userObject;
	}
}
