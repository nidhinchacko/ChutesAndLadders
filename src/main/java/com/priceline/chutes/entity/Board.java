package com.priceline.chutes.entity;

import static java.util.Map.entry;

import java.util.Map;
import java.util.Optional;

public class Board {

  private BoardSquare[] squares; // created array for fast retrieval / Access modifier

  public Board() { // TODO Access modifier
    squares = java.util.stream.IntStream.rangeClosed(1, 100)
        .mapToObj(i -> Optional
            .ofNullable(specialSquares.get(i))
            .orElseGet(BoardSquare::new))
        .toArray(BoardSquare[]::new);
    //.collect(Collectors.toList());
  }

  public BoardSquare getSquareAtPosition(int i) { //TODO Access modifier
    //return squares.get(i-1); //
    return squares[i - 1]; //  array for retrieval
  }


  private Map<Integer, BoardSquare> specialSquares = Map.ofEntries(  //TODO Access modifier
      entry(1, new BoardSquare(false, true, 37)),
      entry(4, new BoardSquare(false, true, 10)),
      entry(9, new BoardSquare(false, true, 22)),
      entry(16, new BoardSquare(true, false, 10)),
      entry(21, new BoardSquare(false, true, 21)),
      entry(28, new BoardSquare(false, true, 56)),
      entry(36, new BoardSquare(false, true, 8)),
      entry(47, new BoardSquare(true, false, 21)),
      entry(49, new BoardSquare(true, false, 38)),
      entry(51, new BoardSquare(false, true, 16)),
      entry(56, new BoardSquare(true, false, 3)),
      entry(62, new BoardSquare(true, false, 43)),
      entry(64, new BoardSquare(true, false, 4)),
      entry(71, new BoardSquare(false, true, 20)),
      entry(80, new BoardSquare(false, true, 20)),
      entry(87, new BoardSquare(true, false, 63)),
      entry(93, new BoardSquare(true, false, 20)),
      entry(95, new BoardSquare(true, false, 20)),
      entry(98, new BoardSquare(true, false, 20))
  );
}
