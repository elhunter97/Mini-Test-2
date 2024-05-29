import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Meat extends Material implements Discount {
    private double weight;

    public Meat(double weight) {
        this.weight = weight;
    }

    public Meat(String id, String name, LocalDate manufactureDate, int cost, double weight) {
        super(id, name, manufactureDate, cost);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return 1.0*weight*getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getExpiryDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        LocalDate today = LocalDate.now();
        if (today.isAfter(getExpiryDate().minusDays(5)) || today.isEqual(getExpiryDate().minusDays(5))){
            return getAmount()*0.7; // 30% discount
        }else{
            return getAmount()*0.9; //10% discount
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Meat{" +
                "weight=" + weight +
                '}';
    }
}
