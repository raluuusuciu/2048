package model;

import extras.Constants;

public class Grid {

    private Tile[][] tiles = new Tile[Constants.GRID_SIZE][Constants.GRID_SIZE];
    private Integer score;

    public Grid(){

        for(int i = 0; i < tiles[0].length; i++){

            for(int j = 0; j < tiles.length; j ++){

                tiles[i][j] = new Tile();
            }
        }

        this.score = 0;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
