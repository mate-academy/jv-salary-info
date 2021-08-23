package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder answer = new StringBuilder();

        answer.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String oneLine : data) {
                String[] dataArray = oneLine.split(" ");
                LocalDate date = LocalDate.parse(dataArray[DATE], DATE_FORMAT);
                if (!date.isBefore(LocalDate.parse(dateFrom, DATE_FORMAT))
                        && !date.isAfter(LocalDate.parse(dateTo, DATE_FORMAT))
                        && dataArray[NAME].equals(name)) {
                    salary += Integer.parseInt(dataArray[HOURS])
                            * Integer.parseInt(dataArray[SALARY]);
                }
            }
            answer.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());

        }

        return answer.toString().trim();
    }
}
