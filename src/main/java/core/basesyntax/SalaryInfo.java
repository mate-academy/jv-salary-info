package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);
            StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - "
                    + dateTo + System.lineSeparator());

            for (String name : names) {
                int totalSalary = 0;

                for (String entry: data) {
                    String []entryData = entry.split(" ");
                    String entryDateStr = entryData[0];
                    String entryName = entryData[1];
                    int hoursWorked = Integer.parseInt(entryData[2]);
                    int hourlyRate = Integer.parseInt(entryData[3]);
                    Date entryDate = dateFormat.parse(entryDateStr);

                    if (entryName.equals(name) && entryDate.compareTo(fromDate) >= 0
                            && entryDate.compareTo(toDate) <= 0) {
                        totalSalary += hoursWorked * hourlyRate;
                    }
                }
                result.append(name).append(" - ").append(totalSalary)
                        .append(System.lineSeparator());
            }
            return result.toString().trim();
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing dates.", e);
        }
    }
}
