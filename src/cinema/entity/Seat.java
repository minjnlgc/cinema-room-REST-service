package cinema.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private int row;
    private int column;
    private int price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean availability;

    public Seat() {}

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.availability = true;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}