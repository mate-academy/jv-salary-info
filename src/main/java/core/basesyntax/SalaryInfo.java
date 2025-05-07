package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_INDEX = 2;
    private static final int SALARY_MULTIPLIER_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            report.append(System.lineSeparator()).append(name).append(" - ");
            int sumSalary = 0;
            for (String dataElement : data) {
                String[] dataToArray = dataElement.split(" ");
                LocalDate parsedDate = LocalDate.parse(
                        dataToArray[DATE_INDEX], dateTimeFormatter);
                if (!parsedDate.isBefore(parsedDateFrom) && !parsedDate.isAfter(parseDateTo)
                        && dataToArray[NAME_INDEX].equals(name)) {
                    sumSalary += Integer.parseInt(dataElement.split(" ")[SALARY_INDEX])
                            * Integer.parseInt(dataElement.split(" ")[SALARY_MULTIPLIER_INDEX]);
                }
            }
            report.append(sumSalary);
        }
        return report.toString();
    }
}
