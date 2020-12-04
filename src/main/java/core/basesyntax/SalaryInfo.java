package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATA_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATA_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATA_FORMATTER);

        StringBuilder reportBuilder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            int employeesMoney = 0;
            for (int j = 0; j < data.length; j++) {
                String[] strings = data[j].split(" ");
                LocalDate day = LocalDate.parse(strings[0], DATA_FORMATTER);
                String localName = strings[1];
                int localWorkHours = Integer.parseInt(strings[2]);
                int localWorkSalary = Integer.parseInt(strings[3]);
                if (localName.equals(names[i])
                        && (day.isAfter(localDateFrom)
                        || day.isEqual(localDateFrom))
                        && (day.isBefore(localDateTo)
                        || day.isEqual(localDateTo))) {
                    employeesMoney = employeesMoney + localWorkHours * localWorkSalary;
                }
            }
            reportBuilder.append("\n").append(names[i]).append(" - ").append(employeesMoney);
        }
        return reportBuilder.toString();
    }
}
