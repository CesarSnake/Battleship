package model;

import model.ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public final char HIDE_SYMBOL = '·';
    public final char WATER_SYMBOL = '~';
    public final char HIT_SYMBOL = '/';
    public final char DESTROYED_SYMBOL = 'X';
    public final int BOARD_SIZE = 10;

    private final ArrayList<Coordinate> coordinatesUsedList;
    private final ArrayList<Ship> shipsList;
    private final ArrayList<Cell> cellsList;

    public List<Coordinate> CoordinatesUsed() {
        return coordinatesUsedList;
    }
    public List<Ship> Ships() {
        return shipsList;
    }

    public Board() {
        coordinatesUsedList = new ArrayList<>();
        shipsList = new ArrayList<>();
        cellsList = new ArrayList<>();

        // add cells on the board
        for (char i = 'A'; i <= 'J'; i++) {
            for (int j = 1; j <= 10; j++) {
                Coordinate coordinate = new Coordinate(i,j);
                Cell cell = new Cell(coordinate);

                cellsList.add(cell);
            }
        }
    }

    public boolean AddShip(Ship ship) {
        /* Add a ship to the board, it must be only one ship per shipType, they cannot be duplicated,
         * the ships added must be without hits */
        if (ship == null) {
            throw new NullPointerException("Cannot add null ship");
        }

        // Check if the ship was already placed in the board
        if (shipsList.stream().anyMatch(shipInList -> shipInList.getClass() == ship.getClass())) {
            throw new UnsupportedOperationException(
                String.join(" ", "The ship", ship.getClass().getSimpleName(),"was already placed"));
        }

        // Check if the ship has any hit
        if (ship.Hits().size() > 0) {
            throw new UnsupportedOperationException("A ship hit or destroyed cannot be placed");
        }

        // Check if it is any ship in the coordinates that the new one is going to be placed
        if (shipsList.stream().anyMatch(shipInList ->
                shipInList.Coordinates().stream().anyMatch(shipInListCoordinate ->
                        ship.Coordinates().contains(shipInListCoordinate)))) {
            return false;
        }

        shipsList.add(ship);

        // update cells with the ship
        for (Cell cell: cellsList) {
            if (ship.Coordinates().contains(cell.Coordinate())) {
                cell.SetShip(ship);
            }
        }

        return true;
    }

    public Cell GetCell(Coordinate coordinate) {
        Cell cell = null;

        if (coordinate == null) {
            throw new NullPointerException("Cannot get a cell with a null coordinate");
        }

        for (Cell cellInList: cellsList) {
            if (cell != null ||
                !cellInList.Coordinate().equals(coordinate)) {
                continue;
            }

            cell = cellInList;
        }

        return cell;
    }

    public CellStatus HitCell(Coordinate coordinate) {
        CellStatus cellStatus = null;

        if (coordinate == null) {
            throw new NullPointerException("Cannot hit a cell with a null coordinate");
        }

        // Check if the cell was already attacked
        if (CoordinatesUsed().contains(coordinate)) {
            throw new UnsupportedOperationException(
                String.join(" ", "Coordinate:", coordinate.toString(), "already hit"));
        }

        for (Cell cell: cellsList) {
            if (!cell.Coordinate().equals(coordinate)) {
                continue;
            }

            coordinatesUsedList.add(coordinate);
            cellStatus = cell.Hit();

            /* each cell changes his status, but if the ship is destroyed,
             * the cells with hits are not updated, we must change them to destroyed */
            if (cellStatus == CellStatus.Destroyed) {
                UpdateCells(cell.GetShip());
            }
        }

        return cellStatus;
    }

    @Override
    public String toString() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add(String.join(" ", List.of("#","1","2","3","4","5","6","7","8","9","10")));

        for (char i = 'A'; i <= 'J'; i++) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(i));

            for (int j = 1; j <= 10; j++) {
                Cell cell = GetCell(new Coordinate(i,j));
                line.add(GetCellStatusSymbol(cell));
            }

            columns.add(String.join(" ", line));
        }

        return String.join("\n", columns);
    }

    private void UpdateCells(Ship ship) {
        for (Coordinate shipCoordinate: ship.Coordinates()) {
            for (Cell cellToUpdate: cellsList) {
                if (!cellToUpdate.Coordinate().equals(shipCoordinate)) {
                    continue;
                }
                cellToUpdate.SetStatus(CellStatus.Destroyed);
            }
        }
    }

    private String GetCellStatusSymbol(Cell cell) {
        char statusSymbol;

        switch (cell.Status()) {
            case Water:
                statusSymbol = WATER_SYMBOL;
                break;

            case Hit:
                statusSymbol = HIT_SYMBOL;
                break;

            case Destroyed:
                statusSymbol = DESTROYED_SYMBOL;
                break;

            case Hide:
            default:
                statusSymbol = HIDE_SYMBOL;
                break;
        }

        return String.valueOf(statusSymbol);
    }
}
