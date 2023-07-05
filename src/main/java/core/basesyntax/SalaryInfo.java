package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder report = new StringBuilder("Report for period ")
                                                                    .append(dateFrom)
                                                                    .append(" - ")
                                                                    .append(dateTo);
        for (String name : names) {
            int salary = 0;

            for (String particularData : data) {
                if (particularData.contains(name)) {
                    String[] infoWorker = particularData.split(" ");
                    LocalDate particularDay = LocalDate.parse(infoWorker[DATE_INDEX],
                                                              dateTimeFormatter);

                    if (isDateInRange(particularDay, localDateFrom, localDateTo)) {
                        salary += calculateSalary(infoWorker);
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }

    private boolean isDateInRange(LocalDate particularDay, LocalDate dateFrom, LocalDate dateTo) {
        return (particularDay.isAfter(dateFrom) || particularDay.isEqual(dateFrom))
                && (particularDay.isBefore(dateTo) || particularDay.isEqual(dateTo));
    }

    private int calculateSalary(String[] data) {
        int workingHours = Integer.parseInt(data[HOUR_INDEX]);
        int income = Integer.parseInt(data[INCOME_INDEX]);
        return workingHours * income;
    }
}
