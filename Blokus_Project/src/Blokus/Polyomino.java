package Blokus;

public enum Polyomino
{
	// Names from http://puzzler.sourceforge.net/docs/polyominoes-intro.html
	// The letter roughly refers to the shape, and the number is how many tiles it uses

	// The one free Monomino
	O1,
	// The one free Domino
	I2,
	// The two free Trominos
	I3, V3,
	// The five free Tertominos
	I4, L4, O4, T4, Z4,
	// The twelve free Pentominoes
	F5, I5, L5, N5, P5, T5, U5, V5, W5, X5, Y5, Z5,

	O0 // Empty tile value
}
