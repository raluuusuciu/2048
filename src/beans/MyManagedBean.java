package beans;

import controller.GridController;
import extras.Constants;
import model.Grid;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean //no eager => bean created only when requested
@SessionScoped //bean lives as long as the HTTP session lives.
public class MyManagedBean {

    private Grid grid = new Grid();
    private GridController gc = new GridController();
    private Integer gameState = null;

    public Integer getValueOfTile(Integer row, Integer column) {

        Integer tileValue = grid.getTiles()[row][column].getValue();

        if (tileValue != 0)
            return tileValue;

        return null;
    }

    public void startGame() {

        int count = 0;

        for (int i = 0; i < grid.getTiles()[0].length; i++) {

            for (int j = 0; j < grid.getTiles().length; j++) {

                if (grid.getTiles()[i][j].getValue() != 0)

                    count++;
            }
        }

        if (count >= 2)
            return;

        generateValuesUpdateScore();
    }

    private void generateValuesUpdateScore() {

        gc.generateRandomValue(grid);
        gc.generateRandomValue(grid);

        for (int i = 0; i < grid.getTiles()[0].length; i++) {

            for (int j = 0; j < grid.getTiles().length; j++) {

                if (grid.getTiles()[i][j].getValue() != 0)

                    grid.setScore(grid.getScore() + grid.getTiles()[i][j].getValue());
            }
        }
    }

    public void restartGame() {

        for (int i = 0; i < grid.getTiles()[0].length; i++) {

            for (int j = 0; j < grid.getTiles().length; j++) {

                grid.getTiles()[i][j].clearTile();
            }
        }

        grid.setScore(0);

        generateValuesUpdateScore();
    }

    private void youWonYouLost() {


        int oneIsZero = 0;

        for (int i = 0; i < Constants.GRID_SIZE; i++) {

            for (int j = 0; j < Constants.GRID_SIZE; j++) {

                if (grid.getTiles()[i][j].getValue() == 2048) {

                    gameState =  1;
                }

                if (grid.getTiles()[i][j].getValue() == null) {

                    oneIsZero ++;
                }
            }
        }
        if(oneIsZero == 0)
            gameState = 0;

        gameState = null;
    }

    public void up() {

        List<Integer> startList = gc.currentGrid(grid);
        List<Integer> beforeMovingList = gc.currentGrid(grid);
        while (true) {

            for (int row = 1; row < Constants.GRID_SIZE; row++) {

                for (int column = 0; column < Constants.GRID_SIZE; column++) {

                    if (grid.getTiles()[row - 1][column].getValue() == 0) {

                        grid.getTiles()[row - 1][column].setValue(grid.getTiles()[row][column].getValue());
                        grid.getTiles()[row][column].clearTile();
                    }

                    if (grid.getTiles()[row][column].sameValue(grid.getTiles()[row - 1][column]) && grid.getTiles()[row][column].getValue() != 0) {

                        if (grid.getTiles()[row][column].getCanCombine() && grid.getTiles()[row - 1][column].getCanCombine()) {

                            grid.getTiles()[row - 1][column].merge(grid.getTiles()[row][column]);
                            grid.getTiles()[row][column].clearTile();
                            grid.getTiles()[row - 1][column].setCanCombine(false);
                            grid.setScore(grid.getScore() + grid.getTiles()[row - 1][column].getValue());
                        }
                    }
                }
            }
            List<Integer> afterMovingList = gc.currentGrid(grid);
            if (beforeMovingList.equals(afterMovingList))
                break;
            else {

                beforeMovingList = afterMovingList;
            }
        }

        List<Integer> endList = gc.currentGrid(grid);

        gc.updateCanCombine(grid);

        if (!startList.equals(endList))
            gc.generateRandomValue(grid);


        youWonYouLost();
    }

    public void down() {
        List<Integer> startList = gc.currentGrid(grid);
        List<Integer> beforeMovingList = gc.currentGrid(grid);

        while (true) {

            for (int row = 2; row >= 0; row--) {

                for (int column = 0; column < Constants.GRID_SIZE; column++) {

                    if (grid.getTiles()[row + 1][column].getValue() == 0) {

                        grid.getTiles()[row + 1][column].setValue(grid.getTiles()[row][column].getValue());
                        grid.getTiles()[row][column].clearTile();
                    }

                    if (grid.getTiles()[row][column].sameValue(grid.getTiles()[row + 1][column]) && grid.getTiles()[row][column].getValue() != 0) {

                        if (grid.getTiles()[row][column].getCanCombine() && grid.getTiles()[row + 1][column].getCanCombine()) {

                            grid.getTiles()[row + 1][column].merge(grid.getTiles()[row][column]);
                            grid.getTiles()[row][column].clearTile();
                            grid.getTiles()[row + 1][column].setCanCombine(false);
                            grid.setScore(grid.getScore() + grid.getTiles()[row + 1][column].getValue());
                        }
                    }
                }
            }
            List<Integer> afterMovingList = gc.currentGrid(grid);
            if (beforeMovingList.equals(afterMovingList))
                break;
            else {

                beforeMovingList = afterMovingList;
            }
        }

        List<Integer> endList = gc.currentGrid(grid);

        gc.updateCanCombine(grid);

        if (!startList.equals(endList))
            gc.generateRandomValue(grid);

        youWonYouLost();
    }

    public void left() {

        List<Integer> startList = gc.currentGrid(grid);
        List<Integer> beforeMovingList = gc.currentGrid(grid);

        while (true) {

            for (int column = 1; column < Constants.GRID_SIZE; column++) {

                for (int row = 0; row < Constants.GRID_SIZE; row++) {

                    if (grid.getTiles()[row][column - 1].getValue() == 0) {

                        grid.getTiles()[row][column - 1].setValue(grid.getTiles()[row][column].getValue());
                        grid.getTiles()[row][column].clearTile();
                    }

                    if (grid.getTiles()[row][column].sameValue(grid.getTiles()[row][column - 1]) && grid.getTiles()[row][column].getValue() != 0) {

                        if (grid.getTiles()[row][column].getCanCombine() && grid.getTiles()[row][column - 1].getCanCombine()) {

                            grid.getTiles()[row][column - 1].merge(grid.getTiles()[row][column]);
                            grid.getTiles()[row][column].clearTile();
                            grid.getTiles()[row][column - 1].setCanCombine(false);
                            grid.setScore(grid.getScore() + grid.getTiles()[row][column - 1].getValue());
                        }
                    }
                }
            }
            List<Integer> afterMovingList = gc.currentGrid(grid);
            if (beforeMovingList.equals(afterMovingList))
                break;
            else {

                beforeMovingList = afterMovingList;
            }
        }

        List<Integer> endList = gc.currentGrid(grid);

        gc.updateCanCombine(grid);

        if (!startList.equals(endList))
            gc.generateRandomValue(grid);

        youWonYouLost();
    }

    public void right() {
        List<Integer> startList = gc.currentGrid(grid);
        List<Integer> beforeMovingList = gc.currentGrid(grid);

        while (true) {

            for (int column = 2; column >= 0; column--) {

                for (int row = 0; row < Constants.GRID_SIZE; row++) {

                    if (grid.getTiles()[row][column + 1].getValue() == 0) {

                        grid.getTiles()[row][column + 1].setValue(grid.getTiles()[row][column].getValue());
                        grid.getTiles()[row][column].clearTile();
                    }

                    if (grid.getTiles()[row][column].sameValue(grid.getTiles()[row][column + 1]) && grid.getTiles()[row][column].getValue() != 0) {

                        if (grid.getTiles()[row][column].getCanCombine() && grid.getTiles()[row][column + 1].getCanCombine()) {

                            grid.getTiles()[row][column + 1].merge(grid.getTiles()[row][column]);
                            grid.getTiles()[row][column].clearTile();
                            grid.getTiles()[row][column + 1].setCanCombine(false);
                            grid.setScore(grid.getScore() + grid.getTiles()[row][column + 1].getValue());
                        }
                    }
                }
            }
            List<Integer> afterMovingList = gc.currentGrid(grid);
            if (beforeMovingList.equals(afterMovingList))
                break;
            else {

                beforeMovingList = afterMovingList;
            }
        }

        List<Integer> endList = gc.currentGrid(grid);

        gc.updateCanCombine(grid);

        if (!startList.equals(endList))
            gc.generateRandomValue(grid);

        youWonYouLost();
    }


    public String getLiveScore() {

        return grid.getScore().toString();
    }

    public Integer getGameState() {

        return gameState;
    }
}
