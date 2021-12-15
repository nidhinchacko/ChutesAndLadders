package com.priceline.utils;

import java.util.Random;

public class GameUtilities {
  private GameUtilities() {
  }

  private static Random random = new Random();
  public  static  int spin() {
    return random.nextInt(6) + 1;
  }

  public static void progressBar(){
    char[] progressChars = new char[] {'|', '/', '-', '\\'};

    System.out.println("\n");
    for (int i = 0; i <= 100; i++) {
      System.out.print("Progressing: " + i + "% " + progressChars[i % 4] + "\r");

      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
    System.out.println("Progressing: Done!");
  }
}
