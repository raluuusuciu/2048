package controller;

import extras.Constants;
import model.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridController {


    private Integer generateTileValue(){

        Random random = new Random();

        int rand = random.nextInt(2) + 1;

        return rand * 2;
    }

    public void generateRandomValue(Grid grid) {

        Random random = new Random();
        boolean wasNotOccupied = true;

        while (wasNotOccupied) {

            int row = random.nextInt(Constants.GRID_SIZE);
            int column = random.nextInt(Constants.GRID_SIZE);

            if (grid.getTiles()[row][column].getValue() == 0) {

                grid.getTiles()[row][column].setValue(generateTileValue());
                wasNotOccupied = false;
            }
        }
    }

    public void updateCanCombine(Grid grid) {

        for (int i = 0; i < Constants.GRID_SIZE; i++) {
            for (int j = 0; j < Constants.GRID_SIZE; j++) {

                grid.getTiles()[i][j].setCanCombine(true);
            }
        }
    }

    public List<Integer> currentGrid(Grid grid){

        List<Integer> tilesList = new ArrayList<>();

        for (int i = 0; i < Constants.GRID_SIZE; i++) {
            for (int j = 0; j < Constants.GRID_SIZE; j++) {

                tilesList.add(grid.getTiles()[i][j].getValue());
            }
        }

        return tilesList;
    }
}
