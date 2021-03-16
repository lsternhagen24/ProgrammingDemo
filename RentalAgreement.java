import java.time.LocalDate;
import java.math.*;


public class RentalAgreement {
    public Tool tool;
    public LocalDate checkOutDate;
    public int rentalDays;
    public int discount;
    public LocalDate dueDate;
    public int chargeDays;
    public double preDiscountCharge;
    public double discountAmount;
    public double finalCharge;

    public RentalAgreement(Tool tool, LocalDate checkOutDate, int rentalDays, int discount, LocalDate dueDate, int chargeDays){
        this.tool = tool;
        this.checkOutDate = checkOutDate;
        this.rentalDays = rentalDays;
        this.discount = discount;
        this.dueDate =dueDate;
        this.chargeDays = chargeDays;
        //preDiscountCharge = chargedays * dailychargerate 
        this.preDiscountCharge = new BigDecimal(((double)chargeDays) * tool.rentChrgProp.dblDailyCharge).setScale(2, RoundingMode.HALF_UP).doubleValue();
        //discountAmount = preDiscountCharge * discount percentage
        this.discountAmount = new BigDecimal(preDiscountCharge * (((double) discount) * .01)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        //finalCharge = preDiscountCharge - discountAmount
        this.finalCharge = new BigDecimal(preDiscountCharge - discountAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------Rental Agreement----------\n");
        sb.append("Tool code: " + tool.strToolCode + "\n");
        sb.append("Tool type: " + tool.strType + "\n");
        sb.append("Tool brand: " + tool.strBrand + "\n");
        sb.append("Rental days: " + rentalDays + "\n");
        sb.append("Check out date: " + checkOutDate + "\n");
        sb.append("Due date: " + dueDate + "\n");
        sb.append("Daily Rental Charge: " + tool.rentChrgProp.dblDailyCharge + "\n");
        sb.append("Charge Days: " + chargeDays + "\n");
        sb.append("Pre-Discount Charge: " + preDiscountCharge + "\n");
        sb.append("Discount Percent: " + discount + "%\n");
        sb.append("Discount Amount: " + discountAmount + "\n");
        sb.append("Final Charge Amount: " + finalCharge + "\n");
        sb.append("----------End Rental Agreement----------\n");

        return sb.toString();
    }
}
