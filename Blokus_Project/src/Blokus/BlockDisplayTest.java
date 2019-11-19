package Blokus;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.Test;

class BlockDisplayTest {
	
	private BlockDisplay test1;
	private Polyomino poly = Polyomino.F5;
	private static Color color;
	

	@Test
	public void testSetPolyomino() {
		test1 = new BlockDisplay(1);
		test1.setPolyomino(poly);
		
		assertEquals(poly,test1.getSelected());
	}
	@Test
	public void testSetColor() {
		test1 = new BlockDisplay(1);
		test1.setColor(color);
		
		assertEquals(color,test1.getColor());
	}

	@Test
	public void testSetRotations() {
		test1 = new BlockDisplay(1);
		test1.setRotations(1);
		
		assertEquals(1,test1.getCwRotations());
	}
	@Test
	public void testRotate() {
		test1 = new BlockDisplay(1);
		test1.rotate(1);
		
		assertEquals(1,test1.getCwRotations());
	}
	//Test1: test the function of "Rotate".
	@Test
	public void testflip() {
		test1 = new BlockDisplay(1);
		test1.flip();
		
		assertTrue(test1.isFlipped());
	}
	//Test2: test the function of "Flip".
}
