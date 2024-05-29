import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CrispyFlour extends Material implements Discount {
    private int quantity;

    public CrispyFlour(int quantity) {
        this.quantity = quantity;
    }

    public CrispyFlour(String id, String name, LocalDate manufactureDate, int cost, int quantity) {
        super(id, name, manufactureDate, cost);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getAmount() {
        return 1.0*quantity*getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufactureDate().plusYears(1);
    }


    @Override
    public double getRealMoney() {
        LocalDate today = LocalDate.now();
        if(today.isAfter(getExpiryDate().minusMonths(4)) || today.isEqual(getExpiryDate().minusMonths(4))){
            return getAmount()*0.80; //20% discount
        }else{
            return getAmount()*0.95; //5% discount
        }
    }

    @Override
    public String toString() {
        return super.toString() + " CrispyFlour{" +
                "quantity=" + quantity +
                '}';
    }
}
