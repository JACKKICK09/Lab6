package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProductModel implements Serializable {

    private final String name;
    private final Coordinates coordinates;
    private final float price;
    private final long manufactureCost;
    private final UnitOfMeasure unitOfMeasure;
    private final Person owner;

    public ProductModel(String name, Coordinates coordinates, LocalDateTime now, float price, long manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    public String getName(){
        return name;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public float getPrice() {
        return price;
    }

    public long getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "common.model.ProductModel{name='" + name + "', coordinates=" + coordinates +
                ", price=" + price +
                ", manufactureCost=" + manufactureCost + ", unitOfMeasure=" + unitOfMeasure +
                ", owner=" + owner + '}';
    }


}