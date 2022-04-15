package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_RATE_INDEX = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        int[] salaries = new int[names.length];
        for (String line : data) {
            String[] splittedLine = line.split(" ");
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(splittedLine[NAME_INDEX])
                        && LocalDate.parse(splittedLine[DATE_INDEX],
                        dateTimeFormatter).isAfter(localDateFrom)
                        && (LocalDate.parse(splittedLine[DATE_INDEX],
                        dateTimeFormatter).isBefore(localDateTo)
                        || (LocalDate.parse(splittedLine[DATE_INDEX],
                        dateTimeFormatter).equals(localDateTo)))) {
                    salaries[j] += Integer.parseInt(splittedLine[HOURS_INDEX])
                            * Integer.parseInt(splittedLine[SALARY_RATE_INDEX]);
                }
            }
        }
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom);
        reportBuilder.append(" - ");
        reportBuilder.append(dateTo);
        reportBuilder.append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i]);
            reportBuilder.append(" - ");
            reportBuilder.append(salaries[i]);
            reportBuilder.append("\n");
        }
        reportBuilder.deleteCharAt(reportBuilder.length() - 1);
        return reportBuilder.toString();
    }
}
