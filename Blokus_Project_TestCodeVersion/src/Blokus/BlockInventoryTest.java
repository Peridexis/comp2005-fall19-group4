package Blokus;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.Test;

class BlockInventoryTest {
	private BlockInventory test1;
	private Polyomino poly = Polyomino.F5;
	private Color color;

	@Test
	public void testAvailable() {
		test1 = new BlockInventory(color);
		test1.isAvailable(poly);
		
		assertTrue(test1.getAvailable()[poly.ordinal()]);
	}
	
	@Test
	public void testmakeUnAvailable() {
		test1 = new BlockInventory(color);
		test1.makeUnavailable(poly);
		
		assertFalse(test1.getAvailable()[poly.ordinal()]);
	}
	
	@Test
	public void testmakeAvailable() {
		test1 = new BlockInventory(color);
		test1.makeAvailable(poly);
		
		assertTrue(test1.getAvailable()[poly.ordinal()]);
	}
	//Test3: test if the block inventory will be available for the users.

}
