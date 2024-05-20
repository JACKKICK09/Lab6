package Objects;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    private Double x;
    private Long y;

    public Coordinates(Double x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }
    public Double getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public int hashCode() {
        return y.hashCode() + x.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }


}
