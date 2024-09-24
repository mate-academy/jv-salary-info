package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int PAY_PER_HOUR_INDEX = 3;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formatDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate formatDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        salaryInfo(names, data, formatDateFrom, formatDateTo, stringBuilder);
        return stringBuilder.toString();
    }

    private static void salaryInfo(String[] names, String[] data, LocalDate formatDateFrom,
                                   LocalDate formatDateTo, StringBuilder stringBuilder) {
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            String employeeName = names[i];
            for (String employeesData : data) {
                String[] employeeData = employeesData.split(DELIMITER);
                String name = employeeData[NAME_INDEX];
                LocalDate date = LocalDate.parse(employeeData[DATE_INDEX], formatter);

                if (employeeName.equals(name)) {
                    if ((date.isAfter(formatDateFrom) || date.isEqual(formatDateFrom))
                            && (date.isBefore(formatDateTo) || date.isEqual(formatDateTo))) {
                        Integer workHours = Integer.parseInt(employeeData[WORK_HOURS_INDEX]);
                        Integer payPerHour = Integer.parseInt(employeeData[PAY_PER_HOUR_INDEX]);
                        int salaryPerDay = workHours * payPerHour;

                        salary += salaryPerDay;
                    }
                }
            }
            stringBuilder
                    .append(names[i])
                    .append(" - ")
                    .append(salary);

            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }
    }
}
