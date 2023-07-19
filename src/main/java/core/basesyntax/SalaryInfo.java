package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DASH = " - ";
    private static final int DATE_EMPLOYEE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int MONEY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        StringBuilder infoAboutEmployer = new StringBuilder();
        infoAboutEmployer.append("Report for period ").append(dateFrom).append(DASH).append(dateTo);

        for (String name : names) {
            int salary = 0;

            for (String employeeData : data) {
                String[] info = employeeData.split(" ");
                String date = info[DATE_EMPLOYEE_INDEX];
                String employeeName = info[EMPLOYEE_NAME_INDEX];
                int hours = Integer.parseInt(info[HOURS_INDEX]);
                int moneyPerHour = Integer.parseInt(info[MONEY_PER_HOUR_INDEX]);

                LocalDate employeeDate = LocalDate.parse(date, formatter);

                if (!employeeDate.isBefore(fromDate) && !employeeDate.isAfter(toDate)) {
                    if (employeeName.equals(name)) {
                        salary += hours * moneyPerHour;
                    }
                }
            }

            infoAboutEmployer.append(System.lineSeparator()).append(name)
                    .append(DASH).append(salary);
        }

        return infoAboutEmployer.toString();
    }
}
