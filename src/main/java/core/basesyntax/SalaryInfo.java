package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder dataResult = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        if (data.length > 0) {
            String reportForPeriod = "Report for period" + " " + dateFrom + " - " + dateTo;
            dataResult.insert(0, reportForPeriod);
            for (String name : names) {
                StringBuilder nameResult = new StringBuilder();
                int totalSalary = 0;
                for (String dates : data) {
                    String[] parts = dates.split(" ");
                    LocalDate currentDate = LocalDate.parse(parts[0], DATE_FORMAT);
                    String employeeName = parts[1];
                    int userHour = Integer.parseInt(parts[2]);
                    int userIncome = Integer.parseInt(parts[3]);
                    int salary = userHour * userIncome;
                    if ((currentDate.isAfter(fromDate) || currentDate.equals(fromDate))
                            && (currentDate.isBefore(toDate) || currentDate.equals(toDate))
                            && employeeName.equals(name)) {
                        totalSalary += salary;
                    }
                }
                nameResult.append(name).append(" - ").append(totalSalary);
                dataResult.append(System.lineSeparator()).append(nameResult);
            }
        }
        return dataResult.toString();
    }
}

