package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder report = new StringBuilder();
        String newline = System.lineSeparator();
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            report.append("Report for period ")
                    .append(dateFrom).append(" - ")
                    .append(dateTo)
                    .append(newline);

            for (String name : names) {
                int totalEarnings = 0;
                for (String entry : data) {
                    String[] entryParts = entry.split(" ");
                    String entryDateStr = entryParts[0];
                    String employeeName = entryParts[1];
                    int hoursWorked = Integer.parseInt(entryParts[2]);
                    int hourlyRate = Integer.parseInt(entryParts[3]);

                    Date entryDate = dateFormat.parse(entryDateStr);

                    if (entryDate.after(fromDate) && entryDate.before(toDate)
                            || entryDate.equals(fromDate)
                            || entryDate.equals(toDate)) {
                        if (employeeName.equals(name)) {
                            totalEarnings += hoursWorked * hourlyRate;
                        }
                    }
                }

                report.append(name)
                        .append(" - ")
                        .append(totalEarnings);

                if (!name.equals(names[names.length - 1])) {
                    report.append(newline);
                }
            }
        } catch (ParseException e) {
            System.out.println("Error parsing dates: " + e.getMessage());
        }

        return report.toString();
    }
}
