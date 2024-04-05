package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final int dateIndex = 0;
    private static final int nameIndex = 1;
    private static final int hoursWorkedIndex = 2;
    private static final int hourlyRateIndex = 3;
    private static final int expectedParts = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append(reportGenerator(dateFrom, dateTo));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);
            for (String name : names) {
                result.append("\n");
                int totalIncome = calculateTotalIncome(data, name, fromDate, toDate, dateFormat);
                result.append(salaryLineGenerator(name, totalIncome));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private int calculateTotalIncome(String[] data, String name, Date fromDate, Date toDate,
                                     SimpleDateFormat dateFormat) throws ParseException {
        int totalIncome = 0;
        for (String entry : data) {
            String[] parts = entry.split(" ");
            if (isValidEntry(parts, name, fromDate, toDate, dateFormat)) {
                int hoursWorked = Integer.parseInt(parts[hoursWorkedIndex]);
                int hourlyRate = Integer.parseInt(parts[hourlyRateIndex]);
                totalIncome += hoursWorked * hourlyRate;
            }
        }
        return totalIncome;
    }

    private boolean isValidEntry(String[] parts, String name, Date fromDate,
                                 Date toDate, SimpleDateFormat dateFormat) throws ParseException {
        if (parts.length == expectedParts) {
            Date entryDate = dateFormat.parse(parts[dateIndex]);
            return entryDate.compareTo(fromDate) >= 0 && entryDate.compareTo(toDate)
                    <= 0 && parts[nameIndex].equals(name);
        }
        return false;
    }

    private String reportGenerator(String dateFrom, String dateTo) {
        return "Report for period " + dateFrom + " - " + dateTo;
    }

    private String salaryLineGenerator(String name, int totalIncome) {
        return name + " - " + totalIncome;
    }
}
