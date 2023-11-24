package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final String SPACE = " ";
    private final String DASH = " - ";
    private final int ENTRY_DATE_INDEX = 0;
    private final int ENTRY_NAME_INDEX = 1;
    private final int HOURS_WORKED_INDEX = 2;
    private final int HOURLY_RATE_INDEX = 3;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);
            StringBuilder result = new StringBuilder("Report for period " + dateFrom + DASH
                    + dateTo + System.lineSeparator());

            for (String name : names) {
                int totalSalary = 0;

                for (String entry : data) {
                    String[] entryData = entry.split(SPACE);
                    String entryDateStr = entryData[ENTRY_DATE_INDEX];
                    String entryName = entryData[ENTRY_NAME_INDEX];
                    int hoursWorked = Integer.parseInt(entryData[HOURS_WORKED_INDEX]);
                    int hourlyRate = Integer.parseInt(entryData[HOURLY_RATE_INDEX]);
                    Date entryDate = dateFormat.parse(entryDateStr);

                    if (entryName.equals(name) && entryDate.compareTo(fromDate) >= 0
                            && entryDate.compareTo(toDate) <= 0) {
                        totalSalary += hoursWorked * hourlyRate;
                    }
                }
                result.append(name).append(DASH).append(totalSalary)
                        .append(System.lineSeparator());
            }
            return result.toString().trim();
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing dates.", e);
        }
    }
}
