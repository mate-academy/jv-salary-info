package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Integer salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ");
        salaryInfo.append(dateFrom);
        salaryInfo.append(" - ");
        salaryInfo.append(dateTo);
        salaryInfo.append(System.lineSeparator());
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String iterationNames : names) {
            salary = 0;
            for (String iterationDate : data) {
                String[] splitData = iterationDate.split(" ");
                if (iterationNames.equals(splitData[1])) {
                    LocalDate localDate = LocalDate.parse(splitData[0], FORMATTER);
                    if ((localDate.isAfter(localDateFrom) && localDate.isBefore(localDateTo))
                            || localDate.isEqual(localDateFrom) || localDate.isEqual(localDateTo)) {
                        this.salary +=
                                Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                    }
                }
            }
            salaryInfo.append(iterationNames);
            salaryInfo.append(" - ");
            salaryInfo.append(this.salary);
            salaryInfo.append(System.lineSeparator());

        }
        return salaryInfo.toString().trim();
    }
}
