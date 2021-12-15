package com.priceline.chutes.service;

import java.util.List;

public interface Game<T> {
  List<T> playGame(List<T> players);
}
