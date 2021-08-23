package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Integer salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate finishDate = LocalDate.parse(dateTo, FORMATTER);
        for (String iterationNames : names) {
            salary = 0;
            for (String record : data) {
                String[] splitData = record.split(" ");
                if (iterationNames.equals(splitData[1])) {
                    LocalDate localDate = LocalDate.parse(splitData[0], FORMATTER);
                    if ((localDate.isAfter(startDate) && localDate.isBefore(finishDate))
                            || localDate.isEqual(startDate) || localDate.isEqual(finishDate)) {
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
