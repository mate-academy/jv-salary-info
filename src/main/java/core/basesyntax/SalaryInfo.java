package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date fromDate;
        Date toDate;

        try {
            fromDate = dateFormat.parse(dateFrom);
            toDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            return "Invalid date format!";
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\r\n");

        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] parts = datum.split(" ");
                if (parts[1].equals(name)) {
                    Date recordDate;
                    try {
                        recordDate = dateFormat.parse(parts[0]);
                    } catch (ParseException e) {
                        return "Invalid date format!";
                    }
                    if (recordDate.compareTo(fromDate) >= 0 && recordDate.compareTo(toDate) <= 0) {
                        int hoursWorked = Integer.parseInt(parts[2]);
                        int wages = Integer.parseInt(parts[3]);
                        totalSalary += hoursWorked * wages;
                    }
                }
            }
            if (!(name.equals(names[names.length - 1]))) {
                report.append(name).append(" - ").append(totalSalary).append("\r\n");
            } else {
                report.append(name).append(" - ").append(totalSalary);
            }
        }
        return report.toString();
    }
}
