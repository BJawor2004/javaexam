package pl.umcs.oop.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDuel {
    @Test
    public void testDuelCreate()
    {
        Player p1 = new Player();
        Player p2 = new Player();
        Duel d = new Duel(p1,p2);

        assertTrue(p1.isDuel());
        assertTrue(p2.isDuel());
    }

    @Test
    public void testDuelWin()
    {
        Player p1 = new Player();
        Player p2 = new Player();
        Duel d = new Duel(p1,p2);

        p1.makeGesture(Gesture.ROCK);
        p2.makeGesture(Gesture.SCISSORS);

        Duel.Result result = d.evaluate();
        assertEquals(p1, result.winner());
        assertEquals(p2, result.loser());
    }
    @Test
    public void testDuelLos()
    {
        Player p1 = new Player();
        Player p2 = new Player();
        Duel d = new Duel(p1,p2);

        p1.makeGesture(Gesture.PAPER);
        p2.makeGesture(Gesture.SCISSORS);

        Duel.Result result = d.evaluate();
        assertEquals(p1, result.loser());
        assertEquals(p2, result.winner());

    }
    @Test
    public void testDuelDraw()
    {
        Player p1 = new Player();
        Player p2 = new Player();
        Duel d = new Duel(p1,p2);

        p1.makeGesture(Gesture.SCISSORS);
        p2.makeGesture(Gesture.SCISSORS);

        Duel.Result result = d.evaluate();
        assertNull(result);
    }
}
