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

    public static Cell[] currentPlayerCells;
    public static Cell[] winningCellsP1 = new Cell[9];
    public static Cell[] winningCellsP2 = new Cell[9];

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

            boolean cellMatch = false;
            for (Cell cell : cells) {
                if (cell.cellCoordinates.equals(playerChoice)) {
                    cellMatch = true;
                    try {
                        if (!cell.checked) {

//                            if (winningCellsP1 != null) {
//                                for (Cell wcell : winningCellsP1) {
//
//                                    if (wcell != null) {
//                                        if (wcell.cellCoordinates.equals(playerChoice) && currentPlayer == 1) {
//                                            try {
//                                                throw new Exception("P1 WINS");
//                                            } catch (Exception e) {
//                                                System.err.println(e.getMessage());
//                                            }
//                                        }
//                                    }
//                                }
//                            }

                            cell.checkCell(currentPlayer);
//                            adjacentCells(cell);
//                            System.out.println("p1 winning cells: " + Arrays.toString(Arrays.stream(winningCellsP1).filter(Objects::nonNull).map(cell1 -> cell1.cellCoordinates).toArray()));
//                            System.out.println("p2 winning cells: " + Arrays.toString(Arrays.stream(winningCellsP2).filter(Objects::nonNull).map(cell1 -> cell1.cellCoordinates).toArray()));
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

            checkWinner();

            turn++;
            System.out.println("\nTurns played " + turn + "\n");

        }
    }

    private static void checkWinner() {

        currentPlayerCells = Arrays.stream(cells).filter(cell -> cell.checkedBy == currentPlayer).toArray(Cell[]::new);
        System.out.println("p" + currentPlayer + " cells(" + currentPlayerCells.length + "): ");

        int hCells = 0;
        int vCells = 0;
        int lDiagCells = 0;
        int rDiagCells = 0;

        for (Cell cell : currentPlayerCells) {

            hCells = checkHorizontalCells(cell);
            vCells = checkVerticalCells(cell);
            lDiagCells = checkLeftDiagonalCells(cell);
            rDiagCells = checkRightDiagonalCells(cell);
            System.out.print(cell.cellCoordinates + " ");

        }

        System.out.println("\n");
        System.out.println("cells in row: " + hCells);
        System.out.println("cells in col: " + vCells);
        System.out.println("cells in left diagonal: " + lDiagCells);
        System.out.println("cells in right diagonal: " + rDiagCells);
    }

    private static int checkHorizontalCells(Cell currentCell) {

        int colLeft = currentCell.cellCol;
        int colRight = currentCell.cellCol;
        int rowUp = currentCell.cellRow;
        int rowDown = currentCell.cellRow;
        int count = 1;

        for (Cell cell1 : currentPlayerCells) {

            for (int j = 0; j < 2; j++) {

                if (cell1.cellCoordinates.equals((currentCell.cellRow) + "-" + (--colLeft))) {
                    count++;
                }
                if (cell1.cellCoordinates.equals((currentCell.cellRow) + "-" + (++colRight))) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int checkVerticalCells(Cell currentCell) {
        int colLeft = currentCell.cellCol;
        int colRight = currentCell.cellCol;
        int rowUp = currentCell.cellRow;
        int rowDown = currentCell.cellRow;
        int count = 1;

        for (Cell cell1 : currentPlayerCells) {

            for (int j = 0; j < 2; j++) {

                if (cell1.cellCoordinates.equals((--rowUp) + "-" + (currentCell.cellCol))) {
                    count++;
                }
                if (cell1.cellCoordinates.equals((++rowDown) + "-" + (currentCell.cellCol))) {
                    count++;

                }
            }
        }
        return count;
    }

    private static int checkLeftDiagonalCells(Cell currentCell) {
        int colLeft = currentCell.cellCol;
        int colRight = currentCell.cellCol;
        int rowUp = currentCell.cellRow;
        int rowDown = currentCell.cellRow;
        int count = 1;

        for (Cell cell1 : currentPlayerCells) {

            for (int j = 0; j < 2; j++) {

                if (cell1.cellCoordinates.equals((--rowUp) + "-" + (--colLeft))) {
                    count++;
                }
                if (cell1.cellCoordinates.equals((++rowDown) + "-" + (++colRight))) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int checkRightDiagonalCells(Cell currentCell) {
        int colLeft = currentCell.cellCol;
        int colRight = currentCell.cellCol;
        int rowUp = currentCell.cellRow;
        int rowDown = currentCell.cellRow;
        int count = 1;

        for (Cell cell1 : currentPlayerCells) {

            for (int j = 0; j < 2; j++) {

                if (cell1.cellCoordinates.equals((--rowUp) + "-" + (++colRight))) {
                    count++;
                }
                if (cell1.cellCoordinates.equals((++rowDown) + "-" + (--colLeft))) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void adjacentCells(Cell cell) {

        Cell[] playerCells = Arrays.stream(cells).filter(value -> value.checkedBy == currentPlayer).toArray(Cell[]::new);

        for (Cell value : playerCells) {
            if (value.checked) {

                if (value.cellCoordinates.equals((cell.cellRow - 1) + "-" + (cell.cellCol - 1))) {
                    findWinningCells("top-left", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow - 1) + "-" + (cell.cellCol))) {
                    findWinningCells("top", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow - 1) + "-" + (cell.cellCol + 1))) {
                    findWinningCells("top-right", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow) + "-" + (cell.cellCol - 1))) {
                    findWinningCells("left", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow) + "-" + (cell.cellCol + 1))) {
                    findWinningCells("right", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow + 1) + "-" + (cell.cellCol - 1))) {
                    findWinningCells("bot-left", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow + 1) + "-" + (cell.cellCol))) {
                    findWinningCells("bot", value);
                }

                if (value.cellCoordinates.equals((cell.cellRow + 1) + "-" + (cell.cellCol + 1))) {
                    findWinningCells("bot-right", value);
                }
            }
        }
    }

    private static void findWinningCells(String field, Cell value) {

        switch (field) {

            case "top-left":
            case "bot-right":
                String topLeft = (value.cellRow - 1) + "-" + (value.cellCol - 1);
                String botRight = (value.cellRow + 1) + "-" + (value.cellCol + 1);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(topLeft) || cells[i].cellCoordinates.equals(botRight))) {
                        if (!cells[i].checked || cells[i].checkedBy == currentPlayer) {
                            if (currentPlayer == 1) {
                                winningCellsP1[i] = cells[i];
                            } else {
                                winningCellsP2[i] = cells[i];
                            }
                        }
                    }
                }
                break;

            case "top":
            case "bot":
                String top = (value.cellRow - 1) + "-" + (value.cellCol);
                String bottom = (value.cellRow + 1) + "-" + (value.cellCol);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(top) || cells[i].cellCoordinates.equals(bottom))) {
                        if (!cells[i].checked || cells[i].checkedBy == currentPlayer) {
                            if (currentPlayer == 1) {
                                winningCellsP1[i] = cells[i];
                            } else {
                                winningCellsP2[i] = cells[i];
                            }
                        }
                    }
                }
                break;

            case "top-right":
            case "bot-left":
                String topRight = (value.cellRow - 1) + "-" + (value.cellCol + 1);
                String botLeft = (value.cellRow + 1) + "-" + (value.cellCol - 1);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(topRight) || cells[i].cellCoordinates.equals(botLeft))) {
                        if (!cells[i].checked || cells[i].checkedBy == currentPlayer) {
                            if (currentPlayer == 1) {
                                winningCellsP1[i] = cells[i];
                            } else {
                                winningCellsP2[i] = cells[i];
                            }
                        }
                    }
                }
                break;

            case "mid-left":
            case "mid-right":
                String left = (value.cellRow) + "-" + (value.cellCol - 1);
                String right = (value.cellRow) + "-" + (value.cellCol + 1);
                for (int i = 0; i < cells.length; i++) {
                    if ((cells[i].cellCoordinates.equals(left) || cells[i].cellCoordinates.equals(right))) {
                        if (!cells[i].checked || cells[i].checkedBy == currentPlayer) {
                            if (currentPlayer == 1) {
                                winningCellsP1[i] = cells[i];
                            } else {
                                winningCellsP2[i] = cells[i];
                            }
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
