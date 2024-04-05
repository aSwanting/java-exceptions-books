package org.learning.bonus;

import java.util.Scanner;

public class Main {
    public static Cell[] cells;
    public static int currentPlayer;
    public static boolean gameOver;

    public static void startGame() {
        
        int turn = 0;
        int cellCount = 9;
        cells = new Cell[cellCount];
        Scanner scan = new Scanner(System.in);
        gameOver = false;

        for (int i = 0; i < cellCount; i++) {
            cells[i] = new Cell();
        }

        System.out.println("\nStarting game...\n");
        System.out.println(drawGrid());

        while (!gameOver && turn < cellCount) {
            currentPlayer = turn % 2 == 0 ? 1 : 2;
            System.out.print("Player " + currentPlayer + " enter cell coordinates (row-col): ");
            String playerChoice = scan.nextLine();

            boolean cellMatch = false;
            for (Cell cell : cells) {
                if (cell.cellCoordinates.equals(playerChoice)) {
                    cellMatch = true;
                    try {
                        if (!cell.checked) {
                            cell.checkCell(currentPlayer);//
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

            gridStats();

            turn++;
            System.out.println("\nTurns played " + turn + "\n");

        }
        scan.close();
    }

    private static void gridStats() {
        checkRows();
        checkCols();
        checkLeftDiagonal();
        checkRightDiagonal();
    }

    private static void checkRows() {
        for (int i = 0; i < 3; i++) {
            int p1Score = 0, p2Score = 0;
            for (int j = 0; j < 3; j++) {
                for (Cell cell : cells) {
                    if (cell.cellCoordinates.equals(i + "-" + j)) {
                        if (cell.checkedBy == 1) p1Score++;
                        if (cell.checkedBy == 2) p2Score++;
                    }
                }
            }
            if (p1Score == 3 || p2Score == 3) {
                gameOver = true;
                System.err.println("Player " + currentPlayer + " WINS!");
            }
        }
    }

    private static void checkCols() {
        for (int i = 0; i < 3; i++) {
            int p1Score = 0, p2Score = 0;
            for (int j = 0; j < 3; j++) {
                for (Cell cell : cells) {
                    if (cell.cellCoordinates.equals(j + "-" + i)) {
                        if (cell.checkedBy == 1) p1Score++;
                        if (cell.checkedBy == 2) p2Score++;
                    }
                }
            }
            if (p1Score == 3 || p2Score == 3) {
                gameOver = true;
                System.err.println("Player " + currentPlayer + " WINS!");
            }
        }
    }

    private static void checkLeftDiagonal() {
        int p1Score = 0, p2Score = 0;
        for (int i = 0; i < 3; i++) {
            for (Cell cell : cells) {
                if (cell.cellCoordinates.equals(i + "-" + i)) {
                    if (cell.checkedBy == 1) p1Score++;
                    if (cell.checkedBy == 2) p2Score++;
                }
            }
        }
        if (p1Score == 3 || p2Score == 3) {
            gameOver = true;
            System.err.println("Player " + currentPlayer + " WINS!");
        }
    }

    private static void checkRightDiagonal() {
        int p1Score = 0, p2Score = 0;
        for (int i = 0; i < 3; i++) {
            for (Cell cell : cells) {
                if (cell.cellCoordinates.equals((2 - i) + "-" + i)) {
                    if (cell.checkedBy == 1) p1Score++;
                    if (cell.checkedBy == 2) p2Score++;
                }
            }
        }
        if (p1Score == 3 || p2Score == 3) {
            gameOver = true;
            System.err.println("Player " + currentPlayer + " WINS!");
        }
    }

    public static StringBuilder drawGrid() {

        int rows = 3;
        int cols = 3;
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

    }
}
