package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_INCOME_INDEX = 3;

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
                    totalSalary = calculateTotalSalary(dates, fromDate, toDate, name, totalSalary);
                }
                nameResult.append(name).append(" - ").append(totalSalary);
                dataResult.append(System.lineSeparator()).append(nameResult);
            }
        }
        return dataResult.toString();
    }

    private static int calculateTotalSalary(String dates, LocalDate fromDate, LocalDate toDate,
                                            String name, int totalSalary) {
        String[] parts = dates.split(" ");
        LocalDate currentDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMAT);
        String employeeName = parts[NAME_INDEX];
        int userHour = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
        int userIncome = Integer.parseInt(parts[HOURLY_INCOME_INDEX]);
        int salary = userHour * userIncome;
        if (isDataInReportPeriod(currentDate, fromDate, toDate) && employeeName.equals(name)) {
            totalSalary += salary;
        }
        return totalSalary;
    }

    private static boolean isDataInReportPeriod(LocalDate currentDate, LocalDate fromDate,
                                                LocalDate toDate) {
        return (currentDate.isAfter(fromDate) || currentDate.equals(fromDate))
                && (currentDate.isBefore(toDate) || currentDate.equals(toDate));
    }
}
