package com.priceline.chutes.entity;

public class BoardSquare {
  private boolean isChute;
  private boolean isLadder;
  private int numberSquaresToSkip;

  BoardSquare() {
    this(false, false, 0);
  }

  BoardSquare(boolean isChute, boolean isLadder, int numberSquaresToSkip) {
    this.isChute = isChute;
    this.isLadder = isLadder;
    this.numberSquaresToSkip = numberSquaresToSkip;
  }

  public int getNumberSquaresToSkip() {
    if (isLadder) {
      return numberSquaresToSkip;
    } else if (isChute) {
      return numberSquaresToSkip * -1;
    } else {
      return 0;
    }
  }
}
