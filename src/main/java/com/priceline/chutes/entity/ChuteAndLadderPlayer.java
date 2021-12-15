package com.priceline.chutes.entity;

import java.util.ArrayList;
import java.util.List;

public class ChuteAndLadderPlayer extends Player {
  private int position = 0;
  private int spins = 0;
  private List<Integer> trail = new ArrayList<>();

  public ChuteAndLadderPlayer(String name) {
    super(name);
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getSpins() {
    return spins;
  }

  public void setSpins(int spins) {
    this.spins = spins;
  }

  public List<Integer> getTrail() {
    return trail;
  }

}
