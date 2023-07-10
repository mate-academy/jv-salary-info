package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private static final int DATE_IND = 0;
    private static final int NAME_IND = 1;
    private static final int SALARY_IND = 2;
    private static final int HOURS_IND = 3;

    private String dateFrom;
    private String dateTo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;

        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salarySum = 0;
            for (String line : data) {
                String[] dataLine = line.split(SEPARATOR);
                if (name.equals(dataLine[NAME_IND]) && isNeededDate(dataLine[DATE_IND])) {
                    salarySum += Integer.parseInt(dataLine[SALARY_IND])
                            * Integer.parseInt(dataLine[HOURS_IND]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salarySum);
        }
        return stringBuilder.toString();
    }

    private boolean isNeededDate(String currentDate) {
        return compareDates(currentDate, dateFrom) >= 0 && compareDates(currentDate, dateTo) <= 0;
    }

    private int compareDates(String dateString1, String dateString2) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date1 = format.parse(dateString1);
            Date date2 = format.parse(dateString2);
            return date1.compareTo(date2);
        } catch (ParseException e) {
            throw new RuntimeException("Cant parse date", e);
        }
    }
}
