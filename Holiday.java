import java.time.DayOfWeek;
import java.time.LocalDate;

public class Holiday {

    public LocalDate date;

    public Holiday(LocalDate date){
        this.date = date;
    }

    //creates a holiday based on the month, year, and nth occurance weekday.
    //Will start at the first day of the month and increment every day until the date is found. 
    public Holiday(int year, int month, int nthOccurence, DayOfWeek dayOfWeek){
        int weekDayCount = 0;
        LocalDate tmpDate =  LocalDate.of(year, month, 1);
        //loop and increment tmpDate until we have a matching week and day of week.
        while(true){
            //we have reached the day we are looking for. Increment counter
            if(tmpDate.getDayOfWeek() == dayOfWeek) weekDayCount +=1;

            //if we have found the date then break
            if(weekDayCount == nthOccurence && tmpDate.getDayOfWeek() == dayOfWeek)break;

            //we moved on to a new month. Invalid criteria passed in.
            if(month != tmpDate.getMonth().getValue()){
                System.out.println("Unable to create holiday. Invalid parameters." +
                 "There is no " + dayOfWeek + " day of week on " + nthOccurence + " week " + " on month " + month + " in year " + year);
                 break;
            }
            tmpDate = tmpDate.plusDays(1);
        }
        this.date = tmpDate;
    }

    public Holiday(LocalDate date, Boolean blnClosestNonWeekend){
        //If we are looking for the closest non weekend time then add a day to sunday or subtract a day from saturday.
        if(blnClosestNonWeekend){
            switch(date.getDayOfWeek()){
                case SUNDAY:
                    date = date.plusDays(1);
                    break;
                case SATURDAY:
                    date = date.plusDays(-1);
                    break;
                default:
            }
        }
        this.date = date;
    }

}
