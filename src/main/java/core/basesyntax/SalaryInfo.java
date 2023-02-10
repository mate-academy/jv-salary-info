package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class SalaryInfo {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private int getDays(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("End date should be grater or equals to start date");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int numOfDays = 0;
        while (!calendar.getTime().after(endDate)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if ((dayOfWeek > 1) && (dayOfWeek < 7)) {
                numOfDays++;
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return numOfDays;
    }

    private boolean getCompare(String dateFrom, String dateTo, String forCompare) {
        LocalDate datefrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateto = LocalDate.parse(dateTo, formatter);
        LocalDate compare = LocalDate.parse(forCompare, formatter);
        return compare.compareTo(datefrom) >= 0 && compare.compareTo(dateto) <= 0;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        Date start1 = null;
        try {
            start1 = new SimpleDateFormat(DATE_FORMAT).parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date end2 = null;
        try {
            end2 = new SimpleDateFormat(DATE_FORMAT).parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (String name : names) {
            int salary = 0;
            for (String date : data) {
                String[] dateSplit = date.split(" ");
                if (name.equals(dateSplit[1]) && getCompare(dateFrom, dateTo, dateSplit[0])) {
                    salary += parseInt(dateSplit[3]);
                }
            }
            builder.append(name).append(" - ").append(salary).append("\n");
        }
        return builder.toString();
    }
}
