package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PartiMultiTest {

	private PartieMulti partie;
	private String[] nomsJoueurs;

	@BeforeEach
	public void setUp() {
		nomsJoueurs = new String[]{"Pierre", "Paul"};
		partie = new PartieMulti();
	}

	@Test
	public void testDemarrerUnePartie() {
		assertEquals(partie.demarreNouvellePartie(nomsJoueurs), "Prochain tir : joueur Pierre, tour n° 1, boule n° 1", "la partie est mal initialisé");
	}

	@Test
	public void testPasDeJoueurs() {

		assertThrows(IllegalArgumentException.class, () -> {
			// On doit avoir une exception
			partie.demarreNouvellePartie(new String[]{});
		}, "Il n'y a pas de joueurs, la partie ne peut pas démarrer");

		assertThrows(IllegalArgumentException.class, () -> {
			// On doit avoir une exception
			partie.demarreNouvellePartie(null);
		}, "Il n'y a pas de joueurs, la partie ne peut pas démarrer");
		
	}
	
	@Test
	public void testPartieNonDemarree(){
		assertThrows(IllegalStateException.class, () -> {
			// On doit avoir une exception
			partie.enregistreLancer(1);
		}, "La partie n'a pas démarrer");
	}
	
	@Test
	public void  testLanceEnregistre(){
		partie.demarreNouvellePartie(nomsJoueurs);
		assertEquals(partie.enregistreLancer(10),"Prochain tir : joueur Paul, tour n° 1, boule n° 1","pas le bon prochain tir pour le strike");
		assertEquals(partie.enregistreLancer(4),"Prochain tir : joueur Paul, tour n° 1, boule n° 2","pas le bon prochain tir");
		assertEquals(partie.enregistreLancer(0),"Prochain tir : joueur Pierre, tour n° 2, boule n° 1","pas le bon prochain tir");
	}
	
	@Test
	public void testPartieTerminee(){
		partie.demarreNouvellePartie(nomsJoueurs);
		for(int i=0;i<41;i++){
			partie.enregistreLancer(5);
		}
		assertEquals(partie.enregistreLancer(4),"Partie terminée","la partie ne se termine pas");
	}
	@Test
	public void testScoreStrikeSpare(){
		partie.demarreNouvellePartie(nomsJoueurs);
		partie.enregistreLancer(10);
		partie.enregistreLancer(5);
		partie.enregistreLancer(5);
		partie.enregistreLancer(2);
		partie.enregistreLancer(3);
		partie.enregistreLancer(1);
		partie.enregistreLancer(0);
		assertEquals(partie.scorePour("Pierre"),20,"pas le bon score lors d'un strike");
		assertEquals(partie.scorePour("Paul"),12,"pas le bon score lors d'un spare");
	}
	
}
