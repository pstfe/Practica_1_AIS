package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;




public class BoardTest {
	Player pl1;
	Player pl2;
	Board tablero;
	
	@Before
	public void setUp(){
		pl1 = new Player (1,"X","Paula");
		pl2 = new Player (2,"O","Pablo");
		tablero = new Board();
	}

	@Test
	public void player1WinsTest() {
		Cell cell0 = tablero.getCell(0);
		cell0.value= pl1.getLabel();
		Cell cell1 = tablero.getCell(1);
		cell1.value = pl1.getLabel();
		Cell cell2 = tablero.getCell(2);
		cell2.value = pl1.getLabel();
		//se marcan las casillas 0 1 2 con la X del pl1
		
		int[] result = {0,1,2};
		int[] result1 = tablero.getCellsIfWinner(pl1.getLabel());
		
		//comprobamos que el array de casillas marcadas sea el mismo
		//que el que te tiene que devolver el programa si detecta un ganador
		assertArrayEquals(result,result1);
		
		
	}
	
	
	@Test
	public void player2WinsTest() {
		Cell cell2 = tablero.getCell(2);
		cell2.value= pl1.getLabel();
		Cell cell3 = tablero.getCell(3);
		cell3.value= pl2.getLabel();
		Cell cell4 = tablero.getCell(4);
		cell4.value = pl2.getLabel();
		Cell cell5 = tablero.getCell(5);
		cell5.value = pl2.getLabel();
		//se marcan las casillas 3 4 5 con la O del pl2
		
		int[] result = {3,4,5};
		int[] result2 = tablero.getCellsIfWinner(pl2.getLabel());
		
		
		//comprobamos que el array de casillas marcadas sea el mismo
		//que el que te tiene que devolver el programa si detecta un ganador
		assertArrayEquals(result,result2);	
		
	}
	
	
	@Test
	public void drawTest() {
		Cell cell0 = tablero.getCell(0);
		cell0.value=pl1.getLabel();
		Cell cell1 = tablero.getCell(1);
		cell1.value=pl2.getLabel();
		Cell cell2 = tablero.getCell(2);
		cell2.value=pl1.getLabel();
		Cell cell3 = tablero.getCell(3);
		cell3.value=pl2.getLabel();
		Cell cell4 = tablero.getCell(4);
		cell4.value=pl1.getLabel();
		Cell cell5 = tablero.getCell(5);
		cell5.value=pl1.getLabel();
		Cell cell6 = tablero.getCell(6);
		cell6.value=pl2.getLabel();
		Cell cell7 = tablero.getCell(7);
		cell7.value=pl1.getLabel();
		Cell cell8 = tablero.getCell(8);
		cell8.value=pl2.getLabel();
		//se consigue que ambos jugadores queden empate
		
		int[] result1 = tablero.getCellsIfWinner(pl1.getLabel());
		int[] result2 = tablero.getCellsIfWinner(pl2.getLabel());
		
		//comprobamos que los arrays sean null puesto que al no haber ningún ganador
		//el programa debe devolver un array a null
		assertNull(result1);
		assertNull(result2);
		
		//en el caso de haber empate este subrograma tiene que devolver true
		assertTrue(tablero.checkDraw());
		
		
		
	}

}