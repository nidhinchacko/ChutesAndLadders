package com.priceline.chutes.service.impl;

import com.priceline.chutes.entity.Board;
import com.priceline.chutes.entity.BoardSquare;
import com.priceline.chutes.service.Game;
import com.priceline.chutes.entity.ChuteAndLadderPlayer;
import com.priceline.chutes.entity.Player;
import com.priceline.utils.GameUtilities;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ChuteAndLadderGame implements Game<ChuteAndLadderPlayer> {

  private Board board = new Board();

  @Override
  public List<ChuteAndLadderPlayer> playGame(List<ChuteAndLadderPlayer> players) { // input players

    List<Integer> trails;

    if (players.isEmpty()) {
      ChuteAndLadderPlayer dijkstra = new ChuteAndLadderPlayer("dijkstra");
      ChuteAndLadderPlayer turing = new ChuteAndLadderPlayer("turing");
      ChuteAndLadderPlayer hopper = new ChuteAndLadderPlayer("hopper");
      ChuteAndLadderPlayer torvalds = new ChuteAndLadderPlayer("torvalds");

      players = List.of(dijkstra, turing, hopper, torvalds);
    }

    //Spin to find the highest
    firstRoundSpin(players);

    while (true) {

      for (ChuteAndLadderPlayer currentPlayer : players) { // Can create a method to reduce complexity

        int spinResult = GameUtilities.spin();
        trails = currentPlayer.getTrail();
        currentPlayer.setSpins(currentPlayer.getSpins() + 1);

        int nextPosition = currentPlayer.getPosition() + spinResult;

        if (nextPosition <= 100) {
          trails.add(nextPosition);
        }
        else if (nextPosition > 100) {
          break;
        }
        BoardSquare nextSquare = board.getSquareAtPosition(nextPosition);

        nextPosition += nextSquare.getNumberSquaresToSkip();
        if (nextSquare.getNumberSquaresToSkip() != 0) {
          trails.add(nextPosition);
        }
        if (nextPosition < 100) {

          currentPlayer.setPosition(nextPosition);
        } else if (nextPosition == 100) {

          return players; // Return all players data
        }
      }
    }
  }

  /**
   * Find the highest scored player in the first round.
   * If there are more than one player scored highest, repeat.
   *
   * @param players List<Player> inputs
   */
  private void firstRoundSpin(List<ChuteAndLadderPlayer> players) {

    Map<String, Integer> spinScores = new TreeMap<>();

    while (true) {

      //Everyone Spins
      for (Player currentPlayer : players) {
        int spinResult = GameUtilities.spin();
        spinScores.put(currentPlayer.getName(), spinResult);
      }

      // Sort the scores (Value) in reverse order to find the highest score(s)
      LinkedHashMap<String, Integer> sortedSpinScores = spinScores
          .entrySet()
          .stream()
          .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
              (e1, e2) -> e1, LinkedHashMap::new));

      System.out.println("First round scores: " + sortedSpinScores);

      //Get the highest scored player and the score.
      String highestScoredPlayer = sortedSpinScores.keySet().iterator().next();
      Integer highestScore = sortedSpinScores.get(highestScoredPlayer);

      //remove the top one to get the second-highest score, to check if there are
      //more than one player scored the highest
      sortedSpinScores.remove(highestScoredPlayer);

      //Get the second-highest score to compare
      Integer secondHighestScore =
          sortedSpinScores.get(sortedSpinScores.keySet().iterator().next());

      System.out.println(
          "Highest two scores - " + highestScoredPlayer + ", " + highestScore + ", " +
              secondHighestScore);

      //If only one player scored the highest / else repeat the spin
      if (!highestScore.equals(secondHighestScore)) {

        Optional<ChuteAndLadderPlayer> firstPlayer = players.stream()
            .filter(player -> highestScoredPlayer.equals(player.getName()))
            .findAny();

        int nextPosition = highestScore;

        if (firstPlayer.isPresent()) {

          firstPlayer.get().setPosition(highestScore);
          firstPlayer.get().setSpins(1);
          firstPlayer.get().getTrail().add(highestScore);

          BoardSquare nextSquare = board.getSquareAtPosition(firstPlayer.get().getPosition());

          nextPosition += nextSquare.getNumberSquaresToSkip();
          if (nextSquare.getNumberSquaresToSkip() != 0) {
            firstPlayer.get().getTrail().add(nextPosition);
          }
        }
        break;
      }
    }
  }
}
