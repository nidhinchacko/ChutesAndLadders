package com.priceline.console.service.impl;

import com.priceline.chutes.entity.ChuteAndLadderPlayer;
import com.priceline.console.service.ReadConsole;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChuteAndLadderReadConsoleImpl implements ReadConsole<ChuteAndLadderPlayer> {

  @Override
  public List<ChuteAndLadderPlayer> playerDetails(Scanner in) {

    List<ChuteAndLadderPlayer> playersList = new ArrayList<>();
    System.out.print("\n\nDo you want to modify the existing players  (y/n) ? ");

    if (in.next().equalsIgnoreCase("y")) {
      System.out.print("How many are playing (2-4) ? : ");
      int count = 0;
      while (in.hasNext()) {
        if (in.hasNextInt()) {
          count = Integer.parseInt(in.next());
          if (count < 2 || count > 4) {
            System.out.print("Please enter an valid integer between 2 and 4 : "); // Re-prompt
          } else {
            break;
          }
        } else {
          in.next(); // Read and discard offending non-int input
          System.out.print("Please enter an integer between 2 and 4 : "); // Re-prompt
        }
      }

      for (int i = 0; i < count; i++) {
        System.out.print("Player-" + (i + 1) + " name : ");
        String name = in.next();
        playersList.add(new ChuteAndLadderPlayer(name));
      }
    }
    return playersList;
  }
}
