package com.priceline.console.service;

import java.util.List;
import java.util.Scanner;

public interface ReadConsole<T> {
  List<T> playerDetails(Scanner in);
}
