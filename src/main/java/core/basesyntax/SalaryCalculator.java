package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryCalculator {
    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {
        StringBuilder result = new StringBuilder();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            result.append("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo)
                    .append(System.lineSeparator());

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                int totalSalary = 0;

                for (String entry : data) {
                    String[] entryParts = entry.split(" ");
                    String entryDateStr = entryParts[0];
                    String entryName = entryParts[1];
                    int hours = Integer.parseInt(entryParts[2]);
                    int hourlyRate = Integer.parseInt(entryParts[3]);
                    Date entryDate = dateFormat.parse(entryDateStr);

                    if (entryName.equals(name) && entryDate.compareTo(fromDate) >= 0
                            && entryDate.compareTo(toDate) <= 0) {
                        totalSalary += hours * hourlyRate;
                    }
                }

                result.append(name)
                        .append(" - ")
                        .append(totalSalary)
                        .append(System.lineSeparator());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while calculating salaries");
        }

        return result.toString();
    }
}

