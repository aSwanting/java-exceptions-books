package org.learning.bonus;

public class Cell {
    public String cellContents, cellCoordinates;
    public int cellRow, cellCol, checkedBy;
    public boolean checked = false;

    public void checkCell(int player) {
        checkedBy = player;
        checked = true;
    }

    public void setCellCoordinates(int row, int col) {
        cellRow = row;
        cellCol = col;
        cellCoordinates = cellRow + "-" + cellCol;
    }

    public void generateCellContents() {
        if (!this.checked) {
            cellContents = cellCoordinates;
        } else
            cellContents = checkedBy == 1 ? " o " : " x ";
    }


}
