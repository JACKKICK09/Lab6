package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product implements Comparable<Product>, Serializable {

    private final int id;
    private final String name;
    private final Coordinates coordinates;
    private final LocalDateTime creationDate;
    private final float price;
    private final long manufactureCost;
    private final UnitOfMeasure unitOfMeasure;
    private final Person owner;

    public Product(int id, String name, Coordinates coordinates, LocalDateTime now, float price, long manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.price = price;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    public String getName(){
        return name;
    }

    public int getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
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
    public Product setName(String name) {
        return new Product(this.id, name, this.coordinates, this.creationDate, this.price, this.manufactureCost, this.unitOfMeasure, this.owner);
    }

    // Setter method for coordinates
    public Product setCoordinates(Coordinates coordinates) {
        return new Product(this.id, this.name, coordinates, this.creationDate, this.price, this.manufactureCost, this.unitOfMeasure, this.owner);
    }

    // Setter method for creationDate
    public Product setCreationDate(LocalDateTime creationDate) {
        return new Product(this.id, this.name, this.coordinates, creationDate, this.price, this.manufactureCost, this.unitOfMeasure, this.owner);
    }

    // Setter method for price
    public Product setPrice(float price) {
        return new Product(this.id, this.name, this.coordinates, this.creationDate, price, this.manufactureCost, this.unitOfMeasure, this.owner);
    }

    // Setter method for manufactureCost
    public Product setManufactureCost(long manufactureCost) {
        return new Product(this.id, this.name, this.coordinates, this.creationDate, this.price, manufactureCost, this.unitOfMeasure, this.owner);
    }

    // Setter method for unitOfMeasure
    public Product setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        return new Product(this.id, this.name, this.coordinates, this.creationDate, this.price, this.manufactureCost, unitOfMeasure, this.owner);
    }

    // Setter method for owner
    public Product setOwner(Person owner) {
        return new Product(this.id, this.name, this.coordinates, this.creationDate, this.price, this.manufactureCost, this.unitOfMeasure, owner);
    }

    @Override
    public String toString() {
        return "common.model.Product{id=" + id + ", name='" + name + "', coordinates=" + coordinates +
                ", creationDate=" + creationDate + ", price=" + price +
                ", manufactureCost=" + manufactureCost + ", unitOfMeasure=" + unitOfMeasure +
                ", owner=" + owner + '}';
    }

    @Override
    public int compareTo(Product product) {
        return id - product.getId();
    }

    @Override
    public int hashCode(){
        return id + name.hashCode() + coordinates.hashCode() + (int) price + (int) manufactureCost +
                unitOfMeasure.hashCode() + owner.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Product product) {
            return name.equals(product.getName()) && coordinates.equals(product.getCoordinates()) &&
                    Float.compare(price, product.getPrice()) == 0 &&
                    manufactureCost == product.getManufactureCost() &&
                    unitOfMeasure.equals(product.getUnitOfMeasure()) &&
                    owner.equals(product.getOwner());
        }
        return false;
    }


}