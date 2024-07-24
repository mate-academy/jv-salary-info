package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatter = parseDate(dateFrom);
        LocalDate dateToFormFormatter = parseDate(dateTo);
        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;

            for (String datum : data) {
                String[] dataArrSplit = datum.split(" ");
                String dateFromDataArr = dataArrSplit[0];
                String employeeNameFromDataArr = dataArrSplit[1];
                int hoursWorkedFromDataArr = Integer.parseInt(dataArrSplit[2]);
                int incomePerHourFromDataArr = Integer.parseInt(dataArrSplit[3]);
                LocalDate workedDate = parseDate(dateFromDataArr);

                if (!workedDate.isBefore(dateFromFormatter) && !workedDate.isAfter(dateToFormFormatter)
                        && employeeNameFromDataArr.equals(name)) {
                    salary += hoursWorkedFromDataArr * incomePerHourFromDataArr;
                }

            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString().trim();
    }

    private LocalDate parseDate(String data) {
        return LocalDate.parse(data, DATE_TIME_FORMATTER);
    }
}

