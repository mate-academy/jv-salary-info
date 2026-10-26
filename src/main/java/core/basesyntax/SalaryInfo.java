package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String NEWLINE = System.lineSeparator();

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {

        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);

        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(NEWLINE);

        for (int employeeIndex = 0; employeeIndex < names.length; employeeIndex++) {
            int salary = 0;

            for (int recordIndex = 0; recordIndex < data.length; recordIndex++) {
                String[] splitData = data[recordIndex].split(" ");
                LocalDate actualDate = LocalDate.parse(splitData[DATE_INDEX], FORMATTER);
                if (names[employeeIndex].equals(splitData[NAME_INDEX])) {
                    if ((actualDate.isAfter(startDate)
                            || actualDate.isEqual(startDate))
                            && (actualDate.isBefore(endDate)
                            || actualDate.isEqual(endDate))) {

                        int hourName = Integer.valueOf(splitData[HOURS_INDEX]);
                        int salaryName = Integer.valueOf(splitData[SALARY_PER_HOUR_INDEX]);
                        salary += hourName * salaryName;
                    }
                }
            }
            stringBuilder.append(names[employeeIndex])
                    .append(" - ")
                    .append(salary);

            if (employeeIndex < names.length - 1) {
                stringBuilder.append(NEWLINE);
            }
        }

        return stringBuilder.toString();
    }
}
