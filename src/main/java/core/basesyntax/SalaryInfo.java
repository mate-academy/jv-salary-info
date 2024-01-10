package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom,DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        if (data.length > 0) {
            String reportPeriod = "Report for period " + dateFrom + " - " + dateTo;
            result.insert(0, reportPeriod);
            for (String name : names) {
                StringBuilder nameResult = new StringBuilder();
                int totalSalary = 0;
                for (int i = 0; i < data.length; i++) {
                    String[] date = data[i].split(" ");
                    LocalDate currentDate = LocalDate.parse(date[0], DATE_FORMAT);
                    String employeeName = date[1];
                    int hoursWorked = Integer.parseInt(date[2]);
                    int hourlyRate = Integer.parseInt(date[3]);
                    int salary = hoursWorked * hourlyRate;
                    if ((currentDate.isAfter(fromDate) || currentDate.equals(fromDate))
                            && (currentDate.isBefore(toDate) || currentDate.equals(toDate))
                            && employeeName.equals(name)) {
                        totalSalary += salary;
                    }
                }
                nameResult.append(name).append(" - ").append(totalSalary);
                result.append(System.lineSeparator()).append(nameResult);
            }
        }
        return result.toString();
    }
}
