package q2;

import java.util.*;

public class BKnoop<E> {
  private BKnoop<E> parent, leftChild, rightChild;
  private E userObject;
  public static final int LEFT = 0;
  public static final int RIGHT = 1;

  private static StringBuffer buffer;
  private static Queue q;

  // Constructors
  public BKnoop() {
    this( null );
  }

  public BKnoop( E userObject ) {
    parent = null;
    leftChild = null;
    rightChild = null;
    this.userObject = userObject;
  }

  // Methoden

  public String toString() {
    return userObject.toString();
  }


  public E get() {
    return userObject;
  }

  public void add( BKnoop<E> newChild ) {
    if( leftChild == null )
      insert( newChild, LEFT );
    else
      if( rightChild == null )
        insert( newChild, RIGHT );
      else
        throw new IllegalArgumentException(
                             "meer dan 2 kinderen" );
  }

  public void insert( BKnoop<E> newChild, int childIndex ) {
    if( isAncestor( newChild ) )
      throw new IllegalArgumentException(
                            "nieuw kind is voorouder" );
    if( childIndex != LEFT &&
        childIndex != RIGHT )
      throw new IllegalArgumentException(
                            "index ongelijk 0 of 1" );

     if( newChild != null ) {
      BKnoop oldParent = newChild.getParent();
      if( oldParent != null )
        oldParent.remove( newChild );
    }

    newChild.parent = this;
    if( childIndex == LEFT )
      leftChild = newChild;
    else
      rightChild = newChild;
  }

  public BKnoop getParent() {
    return parent;
  }

 public BKnoop getLeftChild() {
    return leftChild;
  }
 public BKnoop getRightChild() {
    return rightChild;
  }

  public void remove( BKnoop<E> aChild ) {
    if( aChild == null )
      throw new IllegalArgumentException(
                           "argument is null" );

    if( !isChild( aChild ) )
      throw new IllegalArgumentException(
                          "argument is geen kind" );

    if( aChild == leftChild ) {
      leftChild.parent = null;
      leftChild = null;
    }
    else {
      rightChild.parent = null;
      rightChild = null;
    }
  }

  public boolean isChild( BKnoop<E> aNode ) {
    return aNode == null?
           false :
           aNode.getParent() == this;
  }

  public boolean isAncestor( BKnoop<E> aNode ) {
    BKnoop ancestor = this;
    while( ancestor != null && ancestor != aNode )
      ancestor = ancestor.getParent();
    return ancestor != null;
  }

  public Iterator iterator() {
    return new BKnoopIterator();
  }

  private class BKnoopIterator implements java.util.Iterator {
    private BKnoop<E> pos;
    private Deque<BKnoop<E>> stack;

    public BKnoopIterator() {
      pos = BKnoop.this;
      stack = new LinkedList();
      stack.push( BKnoop.this );
    }

    public boolean hasNext() {
      return !stack.isEmpty();
    }

    public Object next() {
      BKnoop<E> knoop = stack.pop();
      if( knoop.rightChild != null )
        stack.push( knoop.rightChild );
      if( knoop.leftChild != null )
        stack.push( knoop.leftChild );
      return knoop.userObject;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }  // Einde BKnoopIterator


  // preOrderToString() levert het resultaat van
  // een preorder wandeling af in een string
  public String preOrderToString() {
    buffer = new StringBuffer();
    preOrder();            // roep recursieve methode aan
    return buffer.toString();
  }

  private void preOrder() {
    buffer.append( userObject.toString() );
    if( leftChild != null )
      leftChild.preOrder();
    if( rightChild != null )
      rightChild.preOrder();
  }

  // levelOrderToString() levert het resultaat van
  // een level-order wandeling af in een string
  public String levelOrderToString() {
    buffer = new StringBuffer();
    q = new ArrayDeque< BKnoop<E> >();
    q.add( this );
    levelOrder();
    return buffer.toString();
  }

  private void levelOrder() {
    while( !q.isEmpty() ) {
      BKnoop<E> knoop = (BKnoop<E>) q.remove();
      buffer.append( knoop.userObject.toString() );
      if( knoop.leftChild != null )
        q.add( knoop.leftChild );
      if( knoop.rightChild != null )
        q.add( knoop.rightChild );
    }
  }

  public ArrayList toArrayList() {
    ArrayList array = new ArrayList();
    q = new ArrayDeque();
    q.add( this );
    q.add( new Integer( -1 ) );
    levelOrderToArrayList( array );
    return array;
  }

  private void levelOrderToArrayList( ArrayList array ) {
    int index = -1;
    int kindnr = 0;
    final int TEMP = -1;

    while( !q.isEmpty() ) {
      BKnoop<E> bOuder = (BKnoop<E>)q.remove();
      int parentIndex = ((Integer) q.remove()).intValue();
      AKnoop<E> aOuder = new AKnoop<E>( parentIndex, TEMP, TEMP, bOuder.get() );
      array.add( aOuder );
      index++;
      if( bOuder.leftChild != null ) {
        q.add( bOuder.leftChild );
        q.add( new Integer( index ) );
        kindnr++;
        aOuder.setLeft( kindnr );
      }
      if( bOuder.rightChild != null ) {
        q.add( bOuder.rightChild );
        q.add( new Integer( index ) );
        kindnr++;
        aOuder.setRight( kindnr );
      }
    }
  }


  public static BKnoop toEchteBoom( ArrayList array ) {
    q = new ArrayDeque();
    q.add( array.get( 0 ) );
    q.add( null );
    return levelOrderToEchteBoom( array );
  }

  private static BKnoop levelOrderToEchteBoom( ArrayList array ) {
    BKnoop  wortel = null;
    while( !q.isEmpty() ) {
      AKnoop aknoop = (AKnoop) q.remove();
      BKnoop parent = (BKnoop) q.remove();
      BKnoop bknoop = new BKnoop ( aknoop.get() );
      if( parent != null )
        parent.add( bknoop );
       else
         wortel = bknoop;

      int left = aknoop.getLeft();
      if( left != -1 ) {
        AKnoop alKind = (AKnoop) array.get( left );
        q.add( alKind );
        q.add( bknoop );
      }

      int right = aknoop.getRight();
      if( right != -1 ) {
        AKnoop arKind = (AKnoop) array.get( right );
        q.add( arKind );
        q.add( bknoop );
      }
    }
    return wortel;
  }
}

class AKnoop<E> {
  private int parent, left, right;
  private E userObject;

  public AKnoop( int parent, int left,
                 int right, E userObject ) {
    this.parent = parent;
    this.left = left;
    this.right = right;
    this.userObject = userObject;
  }

  public int getLeft() {
    return left;
  }


  public int getRight() {
    return right;
  }

  public E get() {
    return userObject;
  }

  public void setLeft( int left ) {
    this.left = left;
  }

  public void setRight( int right ) {
    this.right = right;
  }

  public String toString() {
    return "[p" + parent + "," + left +
             "," + right + ", " + userObject.toString()
             + "]";
  }
}