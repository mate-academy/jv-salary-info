package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            for (String name : names) {
                result.append("\n");
                int totalIncome = 0;
                for (String entry : data) {
                    String[] parts = entry.split(" ");
                    if (parts.length == 4) {
                        Date entryDate = dateFormat.parse(parts[0]);
                        if (entryDate.compareTo(fromDate) >= 0 && entryDate.compareTo(toDate)
                                <= 0 && parts[1].equals(name)) {
                            int hoursWorked = Integer.parseInt(parts[2]);
                            int hourlyRate = Integer.parseInt(parts[3]);
                            totalIncome += hoursWorked * hourlyRate;
                        }
                    }
                }
                result.append(name).append(" - ").append(totalIncome);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}

