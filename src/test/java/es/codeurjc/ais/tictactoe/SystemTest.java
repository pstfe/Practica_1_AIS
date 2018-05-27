package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SystemTest {
	WebDriver driver1, driver2;
	
	@BeforeClass
	public static void setupClass() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Paula\\Documents\\GitHub\\Practica1_AIS\\src\\test\\java\\es\\codeurjc\\ais\\tictactoe\\chromedriver.exe");
		WebApp.start();
	} 
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	
	@Before
	public void setup() {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		
		driver1.findElement(By.id("nickname")).sendKeys("Paula");
		driver1.findElement(By.id("startBtn")).click();
		
		driver2.findElement(By.id("nickname")).sendKeys("Pablo");
		driver2.findElement(By.id("startBtn")).click();
		//se inicializa para todos los tests el jugador, se presiona el boton start
		//tambien se crean los drivers que son las dos sesiones para poder jugar
		
	}
	
	@After
	public void teardown() {
		if (driver1 != null) {
			driver1.quit();
		}
		if (driver2 != null) {
			driver2.quit();
		}
	}
	
	@Test
	public void player1WinTest() throws InterruptedException {
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-4")).click();
		driver2.findElement(By.id("cell-2")).click();
		driver1.findElement(By.id("cell-8")).click();
		//cada jugador va marcando las casillas
		
		Thread.sleep(3000); // se introduce para que haya tiempo suficiente para captar el mensaje
		String m1= driver1.switchTo().alert().getText();
		Thread.sleep(3000);
		String m2 = driver2.switchTo().alert().getText();
		//se captan los mensajes que se reproducen en ambas sesiones
		
		String expect = "Paula wins! Pablo looses.";
		assertEquals (expect, m1);
		assertEquals (expect, m2);
		//se comparan con el mensaje esperado
		
	}
	
	@Test
	public void player2WinTest() throws InterruptedException{
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-2")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver1.findElement(By.id("cell-3")).click();
		driver2.findElement(By.id("cell-7")).click();
		//cada jugador va marcando las casillas
		
		Thread.sleep(3500);// se introduce para que haya tiempo suficiente para captar el mensaje
		String m1= driver1.switchTo().alert().getText();
		Thread.sleep(3500);
		String m2 = driver2.switchTo().alert().getText();
		//se captan los mensajes que se reproducen en ambas sesiones
		
		String result = "Pablo wins! Paula looses.";
		assertEquals (result, m1);
		assertEquals (result, m2);
		//se comparan con el mensaje esperado
		
	}
	
	@Test
	public void drawTest() throws InterruptedException{
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-3")).click();
		driver2.findElement(By.id("cell-6")).click();
		driver1.findElement(By.id("cell-4")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-2")).click();
		driver2.findElement(By.id("cell-8")).click();
		driver1.findElement(By.id("cell-7")).click();
		//cada jugador va marcando las casillas
		
		Thread.sleep(3000);// se introduce para que haya tiempo suficiente para captar el mensaje
		String m1= driver1.switchTo().alert().getText();
		Thread.sleep(3000);
		String m2 = driver2.switchTo().alert().getText();
		//se captan los mensajes que se reproducen en ambas sesiones
		
		String result = "Draw!";
		assertEquals (result, m1);
		assertEquals (result, m2);
		//se comparan con el mensaje esperado
	}

}