package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = dateFormat.parse(dateFrom);
            endDate = dateFormat.parse(dateTo);
        } catch (Exception e) {
            System.err.println("Error parsing dates: " + e.getMessage());
            return "Invalid date format.";
        }
        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] recordParts = record.split(" ");
                String employeeName = recordParts[1];
                Date workDate = null;
                try {
                    workDate = dateFormat.parse(recordParts[0]);
                } catch (Exception e) {
                    System.err.println("Error parsing work date for "
                            + employeeName + ": " + e.getMessage());
                    continue;
                }
                int workHours = Integer.parseInt(recordParts[2]);
                int incomePerHour = Integer.parseInt(recordParts[3]);
                if (employeeName.equals(name) && !workDate.before(startDate)
                        && !workDate.after(endDate)) {
                    totalSalary += workHours * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return result.toString();

    }
}
