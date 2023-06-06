package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        try {
            Date fromDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date toDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            for (String name : names) {
                int totalSalary = 0;
                for (String record : data) {
                    String[] recordData = record.split(" ");
                    Date recordDate = new SimpleDateFormat("dd.MM.yyyy").parse(recordData[0]);
                    if (recordDate.compareTo(fromDate) >= 0 && recordDate.compareTo(toDate) <= 0) {
                        if (recordData[1].equals(name)) {
                            int hoursWorked = Integer.parseInt(recordData[2]);
                            int salaryPerHour = Integer.parseInt(recordData[3]);
                            int salaryPerDay = hoursWorked * salaryPerHour;
                            totalSalary += salaryPerDay;
                        }
                    }
                }
                if (names[2].equals(name)) {
                    result.append(name).append(" - ").append(totalSalary);
                } else {
                    result.append(name).append(" - ").append(totalSalary)
                            .append(System.lineSeparator());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
