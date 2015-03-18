package q2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ExpressieKnoopTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBoom1() {
		
		/**
		 * Boom met uitkomst 114
		 * 
		 *              +
		 *             / \
		 *            /   \
		 *           /     \
		 *          +    100
		 *         / \
		 *        /   \
		 *       /     \
		 *      +      +
		 *     / \    / \
		 *    /   \  /   \
		 *    4   3  4   3
		 */
		ExpressieKnoop links1 = new ExpressieKnoop(null,null,new GetalExpressie(4.0));
		ExpressieKnoop rechts1 = new ExpressieKnoop(null,null,new GetalExpressie(3.0));
		
		ExpressieKnoop links2 = new ExpressieKnoop(null,null,new GetalExpressie(4.0));
		ExpressieKnoop rechts2 = new ExpressieKnoop(null,null,new GetalExpressie(3.0));
		
		ExpressieKnoop parentLinks = new ExpressieKnoop(links1, rechts1, new PlusExpressie());
		ExpressieKnoop parentRechts = new ExpressieKnoop(links2, rechts2, new PlusExpressie());
		
		ExpressieKnoop grandParent = new ExpressieKnoop(parentLinks, parentRechts, new PlusExpressie());
		
		ExpressieKnoop grandParentGetal = new ExpressieKnoop(null,null,new GetalExpressie(100.0));
		
		ExpressieKnoop grandGrandParent = new ExpressieKnoop(grandParent, grandParentGetal, new PlusExpressie());
		
		assertEquals(114.0,grandGrandParent.bereken(),0.001);
	}

}
