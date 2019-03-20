package model;

public class Tile {

    private Integer value;
    private boolean canCombine;

    public Tile(){

        this.value = 0;
        this.canCombine = true;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean sameValue(Tile tile){

        return tile.getValue().equals(this.value);
    }

    public void merge(Tile tile){

        this.setValue(this.value + tile.getValue());
    }

    public void clearTile(){

        this.setValue(0);
    }

    public boolean getCanCombine() {
        return canCombine;
    }

    public void setCanCombine(boolean canCombine) {
        this.canCombine = canCombine;
    }
}
