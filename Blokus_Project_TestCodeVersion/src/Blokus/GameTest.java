package Blokus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {
	private Game game1;
	private GameMenu Gm1;
	private Polyomino poly = Polyomino.O0;


	@Test
	void testIsSelected() {
		game1 = new Game(Gm1);

		assertFalse(game1.isSelected());
	}
	
	@Test
	void testSelectedPoly() {
		game1 = new Game(Gm1);
		game1.isSelected(poly);

		assertTrue(poly == game1.selected);
	}

	@Test
	void testDeselected() {
		game1 = new Game(Gm1);
		game1.deselect();

		assertEquals(poly, game1.selected);
	}
	
	@Test
	void testNextTurn1() {
		game1 = new Game(Gm1);
		game1.nextTurn();
		
		assertTrue(game1.active == 2);
	}
	
	@Test
	void testNextTurn2() {
		game1 = new Game(Gm1);
		game1.nextTurn();
		
		assertFalse(game1.active == 1);
	}
	//Test5: test if the "Next Turn" works.
	

}
