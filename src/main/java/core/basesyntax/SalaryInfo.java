package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String regex = "\\s+";
        int indexDate = 0;
        int indexName = 1;
        int indexWorkingHour = 2;
        int indexSalary = 3;

        LocalDate salaryDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate salaryDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sumSalary = 0;
            for (String dataEntry : data) {
                String[] parsDatum = dataEntry.split(regex);
                LocalDate currentDate = LocalDate.parse(parsDatum[indexDate], dateTimeFormatter);
                if (!currentDate.isBefore(salaryDateFrom) && !currentDate.isAfter(salaryDateTo)
                        && parsDatum[indexName].equals(name)) {
                    sumSalary += Integer.parseInt(parsDatum[indexWorkingHour])
                            * Integer.parseInt(parsDatum[indexSalary]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(sumSalary);
        }
        return stringBuilder.toString();
    }
}
