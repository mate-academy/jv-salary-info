package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORK_DATE_INDEX = 0;
    private static final int NAME_OF_WORKER_INDEX = 1;
    private static final int HOURS_PER_DAY_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate workDate = LocalDate.parse(splittedLine[WORK_DATE_INDEX], FORMATTER);
                String nameOfWorker = splittedLine[NAME_OF_WORKER_INDEX];
                int hoursPerDay = Integer.parseInt(splittedLine[HOURS_PER_DAY_INDEX]);
                int salaryPerHour = Integer.parseInt(splittedLine[SALARY_PER_HOUR_INDEX]);
                boolean isValidDate = workDate.isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        && workDate.isBefore(LocalDate.parse(dateTo, FORMATTER))
                        || workDate.isEqual(LocalDate.parse(dateFrom, FORMATTER))
                        || workDate.isEqual(LocalDate.parse(dateTo, FORMATTER));
                if (name.equals(nameOfWorker) && isValidDate) {
                    salary += hoursPerDay * salaryPerHour;
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
