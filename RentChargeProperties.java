import java.util.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class RentChargeProperties{

    public String strProductType;
    public double dblDailyCharge;
    public boolean blnWeekdayCharge;
    public boolean blnWeekendCharge;
    public boolean blnHolidayCharge;

    //constructor. uses the correct properties for the given product type.
    public RentChargeProperties(String strProductType) throws Exception{
        if(!RentChargeProperties.dctRentChargeProperties().containsKey(strProductType)){
            throw new Exception("Attempting to create invalid product type " + strProductType + " doesn't exist");
        }
        RentChargeProperties tmp = RentChargeProperties.dctRentChargeProperties().get(strProductType);
        this.strProductType = strProductType;
        this.dblDailyCharge = tmp.dblDailyCharge;
        this.blnWeekdayCharge = tmp.blnWeekdayCharge;
        this.blnWeekendCharge = tmp.blnWeekendCharge;
        this.blnHolidayCharge = tmp.blnHolidayCharge;
    }
    //deep copy
    public RentChargeProperties copy()throws Exception{
        return new RentChargeProperties(this.strProductType);
    }
    //default constructor 
    private RentChargeProperties(String strProductType, double dblDailyCharge, boolean blnWeekdayCharge, boolean blnWeekendCharge, boolean blnHolidayCharge){
        this.strProductType = strProductType;
        this.dblDailyCharge = dblDailyCharge;
        this.blnWeekdayCharge = blnWeekdayCharge;
        this.blnWeekendCharge = blnWeekendCharge;
        this.blnHolidayCharge = blnHolidayCharge;
    }

    //returns true if a given day should be charged for
    public boolean shouldBeCharged(boolean blnWeekDay, boolean blnWeekend, boolean blnHoliday){
        //check if we are on a day that we don't charge for.
        if(blnWeekDay && !this.blnWeekdayCharge)return false;
        if(blnWeekend && !this.blnWeekendCharge)return false;
        if(blnHoliday && !this.blnHolidayCharge)return false;
        //criteria is valid. 
        return true;
    }

    //returns a list of all rent charge properties
    public static RentChargeProperties[] AllRentChargeProperties(){
        RentChargeProperties ladder = new RentChargeProperties("Ladder", 1.99, true, true, false);
        RentChargeProperties chainsaw = new RentChargeProperties("Chainsaw", 1.49, true, false, true);
        RentChargeProperties jackhammer = new RentChargeProperties("Jackhammer", 2.99, true, false, false); 
        return new RentChargeProperties[] {ladder, chainsaw, jackhammer};
    }

    //creates a dictionary mapping every product type to the proper rent charge properties
    public static Map<String, RentChargeProperties> dctRentChargeProperties(){
        Map<String, RentChargeProperties> dctRentChargeProperties = new HashMap<String, RentChargeProperties>();
        for(RentChargeProperties i : AllRentChargeProperties()){
            dctRentChargeProperties.put(i.strProductType, i);
        }
        return dctRentChargeProperties;
    }

    //returns an array of all holidays for a given year.
    public static LocalDate[] AllHolidays(int year){
        Holiday IndDay = new Holiday(LocalDate.of(year, 7, 4), true);
        Holiday LabDay = new Holiday(year, 9, 1, DayOfWeek.MONDAY);
        return new LocalDate[] {IndDay.date, LabDay.date};
    }
    //returns a set of all holidays for a given year. Useful for quick lookups if a date is considered a holiday
    public static HashSet<LocalDate> Holidays(int year){
        HashSet<LocalDate> holidays = new HashSet<LocalDate>();
        for(LocalDate i : AllHolidays(year)){
            holidays.add(i);
        }
        return holidays;
    }

    @Override
    public String toString(){
        return "Product Type: " + this.strProductType + "\n"
         + "Daily Charge: " + this.dblDailyCharge + "\n" 
        + "Weekday Charge: " + this.blnWeekdayCharge + "\n"
        + "Weekend Charge: " + this.blnWeekendCharge + "\n"
        + "Holiday Charge: " + this.blnHolidayCharge;
    }

    
    @Override
    public int hashCode() {
        return this.strProductType.hashCode();
    }

    @Override
    public boolean equals(Object otherRentChrPrp){
        //If we are comparing the wrong object type return false
        if(otherRentChrPrp.getClass() != RentChargeProperties.class) return false;
        //Cast and compare product types
        RentChargeProperties other = (RentChargeProperties)otherRentChrPrp;
        return this.strProductType == other.strProductType;
    }

}