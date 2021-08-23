package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOUR = 2;
    private static final int MONEY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate finishDate = LocalDate.parse(dateTo, FORMATTER);
        Integer salary;
        for (String record : names) {
            salary = 0;
            for (String evaluation : data) {
                String[] splitData = evaluation.split(" ");
                if (record.equals(splitData[NAME])) {
                    LocalDate localDate = LocalDate.parse(splitData[DATE], FORMATTER);
                    if ((localDate.isAfter(startDate) && localDate.isBefore(finishDate))
                            || localDate.isEqual(startDate) || localDate.isEqual(finishDate)) {
                        salary +=
                                Integer.parseInt(splitData[HOUR])
                                        * Integer.parseInt(splitData[MONEY]);
                    }
                }
            }
            salaryInfo.append(record);
            salaryInfo.append(" - ");
            salaryInfo.append(salary);
            salaryInfo.append(System.lineSeparator());

        }
        return salaryInfo.toString().trim();
    }
}
