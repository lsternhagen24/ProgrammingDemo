import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import org.junit.Test;


public class Tests {
    //rounding error. Needed for double comparisons
    public static final double ROUNDING_ERROR = .001;

    //run all tests
    public static void main(String[] args) throws Exception {

        Tests t = new Tests();
        t.Test1();
        t.Test2();
        t.Test3();
        t.Test4();
        t.Test5();
        t.Test6();

    }
    
    @Test
    public void Test1()throws Exception{
        Exception e = assertThrows(Exception.class, () -> {
            Checkout.CheckoutItem(new Tool("JAKR"), 5, LocalDate.parse("2015-09-03"), 101);
        });
        assertTrue(e.getMessage().contains("Invalid Discount Percentage"));
         e = assertThrows(Exception.class, () -> {
            Checkout.CheckoutItem(new Tool("JAKR"), 5, LocalDate.parse("2015-09-03"), -1);
        });
        assertTrue(e.getMessage().contains("Invalid Discount Percentage"));    
    }
    @Test
    public void Test2()throws Exception{
        RentalAgreement r = Checkout.CheckoutItem(new Tool("LADW"), 3, LocalDate.parse("2020-07-02"), 10);
        assertEquals(r.rentalDays, 3);
        assertEquals(r.tool.strBrand, "Werner");
        assertEquals(r.tool.strToolCode, "LADW");
        assertEquals(r.tool.strType, "Ladder");
        assertEquals(r.checkOutDate,  LocalDate.parse("2020-07-02"));
        assertEquals(r.dueDate, LocalDate.parse("2020-07-05"));
        assertEquals(r.chargeDays, 2);
        assertEquals(r.tool.rentChrgProp.dblDailyCharge, 1.99, ROUNDING_ERROR);
        assertEquals(r.discount, 10);   
        assertEquals(r.preDiscountCharge, 3.98, ROUNDING_ERROR);
        assertEquals(r.discountAmount, 0.4, ROUNDING_ERROR);
        assertEquals(r.finalCharge, 3.58, ROUNDING_ERROR);
    }

    @Test
    public void Test3()throws Exception{
        RentalAgreement r = Checkout.CheckoutItem(new Tool("CHNS"), 5, LocalDate.parse("2015-07-02"), 25);
        assertEquals(r.rentalDays, 5);
        assertEquals(r.tool.strBrand, "Stihl");
        assertEquals(r.tool.strToolCode, "CHNS");
        assertEquals(r.tool.strType, "Chainsaw");
        assertEquals(r.checkOutDate,  LocalDate.parse("2015-07-02"));
        assertEquals(r.dueDate, LocalDate.parse("2015-07-07"));
        assertEquals(r.chargeDays, 3);
        assertEquals(r.tool.rentChrgProp.dblDailyCharge, 1.49, ROUNDING_ERROR);
        assertEquals(r.discount, 25);   
        assertEquals(r.preDiscountCharge, 4.47, ROUNDING_ERROR);
        assertEquals(r.discountAmount, 1.12, ROUNDING_ERROR);
        assertEquals(r.finalCharge, 3.35, ROUNDING_ERROR);
    }
    public void Test4()throws Exception{
        RentalAgreement r = Checkout.CheckoutItem(new Tool("JAKD"), 6, LocalDate.parse("2015-09-03"), 0);
        assertEquals(r.rentalDays, 6);
        assertEquals(r.tool.strBrand, "DeWalt");
        assertEquals(r.tool.strToolCode, "JAKD");
        assertEquals(r.tool.strType, "Jackhammer");
        assertEquals(r.checkOutDate,  LocalDate.parse("2015-09-03"));
        assertEquals(r.dueDate, LocalDate.parse("2015-09-09"));
        assertEquals(r.chargeDays, 3);
        assertEquals(r.tool.rentChrgProp.dblDailyCharge, 2.99, ROUNDING_ERROR);
        assertEquals(r.discount, 0);   
        assertEquals(r.preDiscountCharge, 8.97, ROUNDING_ERROR);
        assertEquals(r.discountAmount, 0, ROUNDING_ERROR);
        assertEquals(r.finalCharge, 8.97, ROUNDING_ERROR);
    }
    public void Test5()throws Exception{
        RentalAgreement r = Checkout.CheckoutItem(new Tool("JAKR"), 9, LocalDate.parse("2015-07-02"), 0);
        assertEquals(r.rentalDays, 9);
        assertEquals(r.tool.strBrand, "Ridgid");
        assertEquals(r.tool.strToolCode, "JAKR");
        assertEquals(r.tool.strType, "Jackhammer");
        assertEquals(r.checkOutDate,  LocalDate.parse("2015-07-02"));
        assertEquals(r.dueDate, LocalDate.parse("2015-07-11"));
        assertEquals(r.chargeDays, 5);
        assertEquals(r.tool.rentChrgProp.dblDailyCharge, 2.99, ROUNDING_ERROR);
        assertEquals(r.discount, 0);   
        assertEquals(r.preDiscountCharge, 14.95, ROUNDING_ERROR);
        assertEquals(r.discountAmount, 0, ROUNDING_ERROR);
        assertEquals(r.finalCharge, 14.95, ROUNDING_ERROR);
    }
    public void Test6()throws Exception{
        RentalAgreement r = Checkout.CheckoutItem(new Tool("JAKR"), 4, LocalDate.parse("2020-07-02"), 50);
        assertEquals(r.rentalDays, 4);
        assertEquals(r.tool.strBrand, "Ridgid");
        assertEquals(r.tool.strToolCode, "JAKR");
        assertEquals(r.tool.strType, "Jackhammer");
        assertEquals(r.checkOutDate,  LocalDate.parse("2020-07-02"));
        assertEquals(r.dueDate, LocalDate.parse("2020-07-06"));
        assertEquals(r.chargeDays, 1);
        assertEquals(r.tool.rentChrgProp.dblDailyCharge, 2.99, ROUNDING_ERROR);
        assertEquals(r.discount, 50);   
        assertEquals(r.preDiscountCharge, 2.99, ROUNDING_ERROR);
        assertEquals(r.discountAmount, 1.5, ROUNDING_ERROR);
        assertEquals(r.finalCharge, 1.49, ROUNDING_ERROR);
    }
}
