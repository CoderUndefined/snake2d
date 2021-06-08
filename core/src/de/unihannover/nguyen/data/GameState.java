package de.unihannover.nguyen.data;

/**
 * Possible things that the map can represent
 * A map tile can either be EMPTY,
 * or is used up by player ONE,
 * or is used up by player TWO,
 * or is a food BEACON that can be eaten by a snake
 */
public enum GameState {
    EMPTY, ONE, TWO, BEACON
}
