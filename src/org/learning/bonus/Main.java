package org.learning.bonus;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Cell[] cells = new Cell[9];
    public static Cell[] cellsP1 = new Cell[9];
    public static Cell[] cellsP2 = new Cell[9];
    public static int rows = 3;
    public static int cols = 3;
    public static Scanner scan = new Scanner(System.in);
    public static int currentPlayer;
    public static Cell[] winningCellsP1 = new Cell[9];
    public static Cell[] winningCellsP2 = new Cell[9];

//    public static playedCells(Cell cell, int player) {
//        if (player ==1) {
//            cellsP1.
//        }
//    }

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

            String[] coords = new String[cells.length];
            for (int i = 0; i < cells.length; i++) {
                coords[i] = cells[i].cellCoordinates;
            }

            boolean cellMatch = false;
            for (Cell cell : cells) {
                if (cell.cellCoordinates.equals(playerChoice)) {
                    cellMatch = true;
                    try {
                        if (!cell.checked) {
                            cell.checkCell(currentPlayer);
                            adjacentCells(cell, currentPlayer);
                        } else {
                            throw new IllegalArgumentException("Cell is already checked, choose a different cell.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        turn--;
                    }
                }
            }

            try {
                if (!cellMatch)
                    throw new IllegalArgumentException("Cell is out of range, choose a different cell.");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                turn--;
            }
            System.out.println("\nRedrawing...\n");
            System.out.println(drawGrid());
            turn++;
            System.out.println("\nTurns played " + turn + "\n");

        }
    }

    public static void adjacentCells(Cell cell, int player) {
        System.out.println("\nCell " + cell.cellCoordinates + " has the following adjacent cells:");
        System.out.println("(Negative values should be ignored, work in progress...)\n");

        int adjCount = 0;
        Cell topLeft;
        Cell top;
        Cell topRight;
        Cell midLeft;
        Cell midRight;
        Cell botLeft;
        Cell bot;
        Cell botRight;

        Cell[] playerCells = Arrays.stream(cells).filter(value -> value.checkedBy == player).toArray(Cell[]::new);

        for (Cell value : playerCells) {
            if (value.checked) {

                if (value.cellCoordinates.equals((cell.cellRow - 1) + "-" + (cell.cellCol - 1))) {
                    topLeft = value;
                    System.out.println("top-left: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("top-left", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow - 1) + "-" + (cell.cellCol))) {
                    top = value;
                    System.out.println("top: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("top", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow - 1) + "-" + (cell.cellCol + 1))) {
                    topRight = value;
                    System.out.println("top-right: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("top-right", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow) + "-" + (cell.cellCol - 1))) {
                    midLeft = value;
                    System.out.println("left: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("left", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow) + "-" + (cell.cellCol + 1))) {
                    midRight = value;
                    System.out.println("right: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("right", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow + 1) + "-" + (cell.cellCol - 1))) {
                    botLeft = value;
                    System.out.println("bot-left: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("bot-left", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow + 1) + "-" + (cell.cellCol))) {
                    bot = value;
                    System.out.println("bot: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("bot", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow + 1) + "-" + (cell.cellCol + 1))) {
                    botRight = value;
                    System.out.println("bot-right: " + value.cellCoordinates);
                    adjCount++;
                    findWinningCells("bot-right", value);
                }
            }
        }

        System.out.println(adjCount + " adjacent cell(s)");

    }

    private static void findWinningCells(String field, Cell value) {


        switch (field) {

            case "top-left":
            case "bot-right":
                System.out.println("winning tiles top-left or bottom-right");
                String topLeft = (value.cellRow - 1) + "-" + (value.cellCol - 1);
                String botRight = (value.cellRow + 1) + "-" + (value.cellCol + 1);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(topLeft) || cells[i].cellCoordinates.equals(botRight)) && !cells[i].checked) {
                        if (currentPlayer == 1) {
                            winningCellsP1[i] = cells[i];
                        } else {
                            winningCellsP2[i] = cells[i];
                        }
                    }
                }
                break;

            case "top":
            case "bot":
                System.out.println("winning tiles top or bottom");
                String top = (value.cellRow - 1) + "-" + (value.cellCol);
                String bottom = (value.cellRow + 1) + "-" + (value.cellCol);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(top) || cells[i].cellCoordinates.equals(bottom)) && !cells[i].checked) {
                        if (currentPlayer == 1) {
                            winningCellsP1[i] = cells[i];
                        } else {
                            winningCellsP2[i] = cells[i];
                        }
                    }
                }
                break;

            case "top-right":
            case "bot-left":
                System.out.println("winning tiles top-right or bottom-left");
                String topRight = (value.cellRow - 1) + "-" + (value.cellCol + 1);
                String botLeft = (value.cellRow - 1) + "-" + (value.cellCol - 1);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(topRight) || cells[i].cellCoordinates.equals(botLeft)) && !cells[i].checked) {
                        if (currentPlayer == 1) {
                            winningCellsP1[i] = cells[i];
                        } else {
                            winningCellsP2[i] = cells[i];
                        }
                    }
                }
                break;

            case "mid-left":
            case "mid-right":
                System.out.println("winning tiles left or right");
                String left = (value.cellRow) + "-" + (value.cellCol - 1);
                String right = (value.cellRow) + "-" + (value.cellCol + 1);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(left) || cells[i].cellCoordinates.equals(right)) && !cells[i].checked) {
                        if (currentPlayer == 1) {
                            winningCellsP1[i] = cells[i];
                        } else {
                            winningCellsP2[i] = cells[i];
                        }
                    }
                }
                break;

        }
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
