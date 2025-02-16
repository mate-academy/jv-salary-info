package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = dateFormat.parse(dateFrom.trim());
        Date endDate = dateFormat.parse(dateTo.trim());

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            Date workDate = dateFormat.parse(parts[0]);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);

            if (workDate.compareTo(startDate) >= 0 && workDate.compareTo(endDate) <= 0) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += hoursWorked * incomePerHour;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom.trim()).append(" - ").append(dateTo.trim()).append("\n");
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]).append("\n");
        }

        return report.toString();
    }
}
