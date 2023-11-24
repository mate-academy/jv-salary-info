package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final String space = " ";
    private final String dash = " - ";
    private final int entryDateIndex = 0;
    private final int entryNameIndex = 1;
    private final int hoursWorkedIndex = 2;
    private final int hourlyRateIndex = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);
            StringBuilder result = new StringBuilder("Report for period " + dateFrom + dash
                    + dateTo + System.lineSeparator());

            for (String name : names) {
                int totalSalary = 0;

                for (String entry : data) {
                    String[] entryData = entry.split(space);
                    String entryDateStr = entryData[entryDateIndex];
                    String entryName = entryData[entryNameIndex];
                    int hoursWorked = Integer.parseInt(entryData[hoursWorkedIndex]);
                    int hourlyRate = Integer.parseInt(entryData[hourlyRateIndex]);
                    Date entryDate = dateFormat.parse(entryDateStr);

                    if (entryName.equals(name) && entryDate.compareTo(fromDate) >= 0
                            && entryDate.compareTo(toDate) <= 0) {
                        totalSalary += hoursWorked * hourlyRate;
                    }
                }
                result.append(name).append(dash).append(totalSalary)
                        .append(System.lineSeparator());
            }
            return result.toString().trim();
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing dates.", e);
        }
    }
}
