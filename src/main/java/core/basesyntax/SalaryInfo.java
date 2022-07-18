package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PATTERN_OF_DATE = "dd.MM.yyyy";
    private static final String LINE_SEPARATOR = " ";
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN_OF_DATE);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] dataOfEmployee = line.split(LINE_SEPARATOR);
                LocalDate dateOfWork = LocalDate.parse(dataOfEmployee[INDEX_OF_DATE], FORMATTER);
                String nameEmployee = dataOfEmployee[INDEX_OF_NAME];
                int amountHoursWork = Integer.parseInt(dataOfEmployee[INDEX_OF_HOURS]);
                int salaryPerHour = Integer.parseInt(dataOfEmployee[INDEX_OF_SALARY]);

                if (nameEmployee.equals(name) && (dateOfWork.isEqual(startDate)
                        || dateOfWork.isAfter(startDate)) && (dateOfWork.isBefore(endDate)
                        || dateOfWork.isEqual(endDate))) {
                    salary += amountHoursWork * salaryPerHour;
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
