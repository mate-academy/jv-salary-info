package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String NEW_LINE = System.lineSeparator();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder builder =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name: names) {
            int salarySum = 0;
            for (String datum : data) {
                String[] separateData = datum.split(" ");

                LocalDate inputDate = LocalDate.parse(separateData[DATE_INDEX], FORMATTER);
                String inputName = separateData[NAME_INDEX];
                int hoursPerDay = Integer.parseInt(separateData[HOUR_INDEX]);
                int salaryByHour = Integer.parseInt(separateData[SALARY_INDEX]);

                if (name.equals(inputName)) {
                    if (inputDate.isAfter(fromDate) && inputDate.isBefore(toDate)
                            || (inputDate.isEqual(fromDate) || inputDate.isEqual(toDate))) {
                        salarySum += salaryByHour * hoursPerDay;
                    }
                }
            }
            builder.append(NEW_LINE).append(name).append(" - ").append(salarySum);
        }
        return builder.toString();
    }
}
