package q3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestKnoop {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		/**
		 * Boom
		 * 
		 *              1
		 *             / \
		 *            /   \
		 *           /     \
		 *          2       3
		 *         / \
		 *        /   \
		 *       /     \
		 *      4      5
		 *     / \    / \
		 *    /   \  /   \
		 *    6   7  8   9
		 *   /
		 *  /
		 * 10
		 * 
		 * 
		 * Diepte: 5
		 */
		
		Knoop k1 = new Knoop();
		Knoop k2 = new Knoop();
		Knoop k3 = new Knoop();
		Knoop k4 = new Knoop();
		Knoop k5 = new Knoop();
		Knoop k6 = new Knoop();
		Knoop k7 = new Knoop();
		Knoop k8 = new Knoop();
		Knoop k9 = new Knoop();
		Knoop k10 = new Knoop();
		
		k5.voegtoe(k8);
		k5.voegtoe(k9);
		
		k6.voegtoe(k10);
		
		k4.voegtoe(k6);
		k4.voegtoe(k7);
		
		k2.voegtoe(k4);
		k2.voegtoe(k5);
		
		k1.voegtoe(k2);
		k1.voegtoe(k3);
		
		assertEquals(5, k1.diepte());
	}
}
