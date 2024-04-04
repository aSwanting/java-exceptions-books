package org.learning.bonus;

import java.util.Scanner;

public class Main {
    public static Cell[] cells = new Cell[9];
    public static Cell[] cellsP1 = new Cell[9];
    public static Cell[] cellsP2 = new Cell[9];
    public static int rows = 3;
    public static int cols = 3;
    public static Scanner scan = new Scanner(System.in);
    public static int currentPlayer;

    public static void startGame() {
        boolean gameOver = false;
        int turn = 0;
        int cellCount = 9;

        for (int i = 0; i < cellCount; i++) {
            cells[i] = new Cell();
        }

        System.out.println("\nStarting game...\n");
        System.out.println(drawGrid());

        while (!gameOver || turn < 9) {
            currentPlayer = turn % 2 == 0 ? 1 : 2;
            System.out.print("Player " + currentPlayer + " enter cell coordinates (row-col): ");
            String playerChoice = scan.nextLine();

            for (Cell cell : cells) {
                if (cell.cellCoordinates.equals(playerChoice)) {
                    try {
                        if (!cell.checked) {
                            cell.checkCell(currentPlayer);
                            adjacentCells(cell);
                        } else {
                            throw new RuntimeException("ERROR: Cell is already checked, choose a different cell.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        turn--;
                    }
                }
            }

            System.out.println("\nRedrawing...\n");
            System.out.println(drawGrid());
            storePlayedCells();
            turn++;
            System.out.println("\nTurns played " + turn + "\n");

        }
    }

    public static void storePlayedCells() {
        for (int i = 0; i < 9; i++) {
            if (cells[i].checked) {
                if (cells[i].checkedBy == 1) {
                    cellsP1[i] = cells[i];
                } else {
                    cellsP2[i] = cells[i];
                }
            }
        }
    }

    public static void adjacentCells(Cell cell) {
        System.out.println("\nCell " + cell.cellCoordinates + " has the following adjacent cells:");
        System.out.println("(Negative values should be ignored, work in progress...)\n");
        System.out.println("top-left: " + ((cell.cellRow - 1) + "-" + (cell.cellCol - 1)));
        System.out.println("top: " + ((cell.cellRow - 1) + "-" + (cell.cellCol)));
        System.out.println("top-right: " + ((cell.cellRow - 1) + "-" + (cell.cellCol + 1)));

        System.out.println("left: " + ((cell.cellRow) + "-" + (cell.cellCol - 1)));
        System.out.println("right: " + ((cell.cellRow) + "-" + (cell.cellCol + 1)));

        System.out.println("bot-left: " + ((cell.cellRow + 1) + "-" + (cell.cellCol - 1)));
        System.out.println("bot: " + ((cell.cellRow + 1) + "-" + (cell.cellCol)));
        System.out.println("bot-right: " + ((cell.cellRow + 1) + "-" + (cell.cellCol + 1)));
    }

    public static StringBuilder drawGrid() {

        int cellCount = 0;

        StringBuilder grid = new StringBuilder();

        for (int i = 0; i < rows; i++) {

            if (i != 1) grid.append("|-----|-----|-----|" + "\n");

            for (int j = 0; j < cols; j++) {

                cells[cellCount].setCellCoordinates(i, j);
                cells[cellCount].generateCellContents();
                String c = cells[cellCount].cellContents;
                StringBuilder cell = new StringBuilder("| " + c + " ");
                if (j == cols - 1) cell.append("|\n");
                grid.append(cell);
                cellCount++;

            }

            if (i != 1) grid.append("|-----|-----|-----|" + "\n");

        }

        return grid;
    }

    public static void main(String[] args) {

        startGame();
        scan.close();

    }
}
