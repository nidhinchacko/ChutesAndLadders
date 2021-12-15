package com.priceline;

import com.priceline.chutes.entity.ChuteAndLadderPlayer;
import com.priceline.chutes.service.impl.ChuteAndLadderGame;
import com.priceline.console.service.ReadConsole;
import com.priceline.console.service.impl.ChuteAndLadderReadConsoleImpl;
import com.priceline.utils.GameUtilities;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainApplication {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    try {
      ReadConsole read = new ChuteAndLadderReadConsoleImpl();
      List<ChuteAndLadderPlayer> playersList = read.playerDetails(in);

      GameUtilities.progressBar();

      List<ChuteAndLadderPlayer> players = new ChuteAndLadderGame().playGame(playersList);

      //Get the winner
      Optional<ChuteAndLadderPlayer> winner = players.stream()
          .filter(player -> player.getTrail().contains(100))
          .findAny();

      if (winner.isPresent()) {

        System.out.println("\n\nThe winner is: " + winner.get().getName());

        System.out.print("\nDo you want to analyse the moves of each player (y/n) ? ");
        if (in.next().equalsIgnoreCase("y")) {
          System.out.println("\n");
          players.stream().forEach(
              player -> System.out.println(
                  "[" + player.getName() + "] [ Spins : " + player.getSpins() + "]\t\t\t\t"
                      + player.getTrail()));
        }
      } else {
        System.out.println("Unfortunately .... no winner at this time");
      }
    } catch (Exception ex) {
      System.out.println("Ooops!! ...... it broke :(");
    } finally {
      in.close();
    }
  }
}
