package Blokus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlokusGUITest {
	private BlokusGUI blokusGUI1;

	@SuppressWarnings("static-access")
	@Test
	public void testBlindColor() {
		blokusGUI1 = new BlokusGUI();
		
		assertFalse(blokusGUI1.colorBlind);
	}
	//Test4: test if the colorBlind of the game will be closed at the beginning of the game.

}
