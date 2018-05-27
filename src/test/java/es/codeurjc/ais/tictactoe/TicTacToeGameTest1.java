package es.codeurjc.ais.tictactoe;

import static org.mockito.hamcrest.MockitoHamcrest.argThat;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

public class TicTacToeGameTest1 {
	TicTacToeGame juego;
	Player pl1, pl2;
	Connection c1, c2;
	
	@Before
	public void setUp() {
		juego = new TicTacToeGame();
		c1 = mock (Connection.class);
		c2 = mock (Connection.class);
		juego.addConnection(c1);
		juego.addConnection(c2);
		pl1= new Player (1,"X","Paula");
		pl2= new Player (2, "O", "Pablo");
		

		juego.addPlayer(pl1);
		reset(c1);
		reset(c2);
		juego.addPlayer(pl2);
	}
	
	@Test
	public void player1WinsTest(){
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq (pl1));
		juego.mark(2);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(6);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(1);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(7);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(0);
		
		//cada jugador va marcando una casilla en su turno
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		verify(c2).sendEvent(eq(EventType.GAME_OVER), eq(argument.getValue()));
		
		//Comprobamos que sea el jugador1 el que se guarda en el WinnerValue
		assertThat(argument.getValue().player, is(pl1));
		//Comprobamos que no sea el jugador2 el que se guarda en el WinnerValue
		assertThat(argument.getValue().player, is(not(pl2)));
		
	}
	
	@Test
	public void player2WinsTest(){
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq (pl1));
		juego.mark(2);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(3);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(8);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(5);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(7);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(4);
		//cada jugador va marcando una casilla en su turno
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		verify(c2).sendEvent(eq(EventType.GAME_OVER), eq(argument.getValue()));
		
		//Comprobamos que sea el jugador2 el que se guarda en el WinnerValue
		assertThat(argument.getValue().player, is(pl2));
		//Comprobamos que no sea el jugador1 el que se guarda en el WinnerValue
		assertThat(argument.getValue().player, is(not(pl1)));
		
	}
	
	@Test
	public void drawTest(){
		verify(c1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		verify(c2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(pl1,pl2)));
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq (pl1));
		juego.mark(0);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(1);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(2);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(3);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(4);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(8);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(5);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl2));
		reset(c1);
		reset(c2);
		juego.mark(6);
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(pl1));
		reset(c1);
		reset(c2);
		juego.mark(7);
		
		//cada jugador va marcando una casilla en su turno
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), eq(argument.getValue()));
		
		//si se guarda null en argument entonces hay empate ya que no hay ninun ganador
		assertNull(argument.getValue());
		
	}
}
