public class Tile {
    private int number;
    private int jumpIndex;
    private String name;
    private String colour = Pallette.ANSI_RESET;
    private boolean scoreTile = false;

    public Tile(int numberSet, int jumpIndexSet, boolean scoreSet) {
        this.number = numberSet;
        this.jumpIndex = jumpIndexSet;
        this.scoreTile = scoreSet;

        if (jumpIndexSet < -1 &&!(numberSet == 15 || numberSet == 26 || numberSet == 57 ||
                numberSet == 69 || numberSet == 77 || numberSet == 90)) {
            throw new IllegalArgumentException("Jump index of tile cannot be below -1!");
        }

        updateName();
    }

    public Tile(int numberSet, int jumpIndexSet, boolean scoreSet, String nameSet) {
        this.number = numberSet;
        this.jumpIndex = jumpIndexSet;
        this.name = nameSet;
        this.scoreTile = scoreSet;

        if (jumpIndexSet < -1) {
            throw new IllegalArgumentException("Jump index of tile cannot be below -1!");
        }
    }

    private void updateName() {
        if (number == 15 || number == 26 || number == 57 || number == 69 || number == 77 || number == 90) {
            name = TileNames.stunMine;
        } else if (jumpIndex == -1) {
            name = TileNames.tile;
        } else if (jumpIndex > number) {
            name = TileNames.ladder;
        } else if (jumpIndex < number) {
            name = TileNames.snake;
        }

        updateColour();
    }

    private void updateColour() {
        switch (name) {
            case TileNames.ladder:
                colour = Pallette.ANSI_GREEN;
                break;
            case TileNames.snake:
                colour = Pallette.ANSI_RED;
                break;
            case TileNames.stunMine:
                colour = Pallette.ANSI_BLUE;
                break;
            default:
                break;
        }
    }

    // --------- setters

    public void setName(String newName) {
        name = newName;
    }

    public void setNumber(int newNumber) {
        number = newNumber;
        updateName();
    }

    public void setJumpIndex(int newJumpIndex) {
        jumpIndex = newJumpIndex;
        updateName();
    }

    public void setColour(String newColour) {
        colour = newColour;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getJumpIndex() {
        return jumpIndex;
    }

    public String getColour() {
        return colour;
    }

    public void setScoreTile(boolean n) {
        this.scoreTile = n;

        if (!n) {
            setColour(Pallette.ANSI_RESET);
        } else {
            setColour(Pallette.ANSI_YELLOW);
        }
    }

    public boolean isScoreTile() {
        return scoreTile;
    }
}