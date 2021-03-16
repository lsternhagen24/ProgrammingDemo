import java.util.*;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class Checkout {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //all valid tools mapped to their data
        Map<String, Tool> toolCodesToData = Tool.dctToolCodeToData();

        //read input until checkout ends
        while (true){
            System.out.print("Please Enter ToolCode or 'END' if finished:   ");
            String toolCode = sc.nextLine();
            if(toolCode.equals("END"))break;
            //validate toolcode.
            if(!toolCodesToData.containsKey(toolCode)) throw new Exception("ToolCode: " + toolCode + " doesn't exist.");
            Tool curTool = toolCodesToData.get(toolCode);

            System.out.print("Enter Rental Day count:     ");
            int rentalCount = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Check Out Date: yyyy-mm-dd    ");
            LocalDate checkOutDate = LocalDate.parse(sc.nextLine());

            System.out.print("Enter Discount Percentage:     ");
            int discount = Integer.parseInt(sc.nextLine());
            System.out.println("\n\n\n");

            RentalAgreement rentalAgreement = CheckoutItem(curTool, rentalCount, checkOutDate, discount);
            System.out.println(rentalAgreement.toString());
        }
        sc.close();

    }
    public static RentalAgreement CheckoutItem(Tool tool, int rentalCount, LocalDate checkOutdate, int discount) throws Exception{

        //validate input
        if(rentalCount <= 0) throw new Exception("Invalid Rental Day Count " + rentalCount + ". Must be greater than or equal to 1.");
        if(discount < 0 || discount > 100)throw new Exception("Invalid Discount Percentage " + discount + "%. Must be between 0% and 100%");

        int chargeDays = 0;
        //Start the loop on the start date + 1
        LocalDate date = checkOutdate.plusDays(1);
        LocalDate endDate = checkOutdate.plusDays(rentalCount);        
        int curYear = date.getYear();
        HashSet<LocalDate> holidays = RentChargeProperties.Holidays(curYear);

        //loop through all days for rental and calculate how many are charge days.
        while(date.isBefore(endDate) || date.equals(endDate)){
            //If we are on a new year then update the holidays to be for the new year.
            if(date.getYear() != curYear){
                curYear = date.getYear();
                holidays = RentChargeProperties.Holidays(curYear);
            }
            //check if current day is on the weekend
            boolean blnWeekend = (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
            //if the day should be charged increment the charge count
            if(tool.rentChrgProp.shouldBeCharged(!blnWeekend, blnWeekend, holidays.contains(date))){
                chargeDays += 1;
            }
            date = date.plusDays(1);   
        }
        //generate the rental aggreement
        return new RentalAgreement(tool, checkOutdate, rentalCount, discount, endDate, chargeDays);
    }
}

