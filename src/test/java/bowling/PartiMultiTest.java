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
}
