package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_AMOUNT_OF_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ");
        reportBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                String[] splitteddLine = line.split(" ");
                LocalDate dateOfWork = LocalDate.parse(splitteddLine[INDEX_OF_DATE], FORMATTER);
                if (name.equals(splitteddLine[INDEX_OF_NAME])) {
                    if (!dateOfWork.isBefore(LocalDate.parse(dateFrom, FORMATTER))
                            && !dateOfWork.isAfter(LocalDate.parse(dateTo, FORMATTER))) {
                        salary += Integer.parseInt(splitteddLine[INDEX_OF_AMOUNT_OF_HOURS])
                                * Integer.parseInt(splitteddLine[INDEX_OF_INCOME_PER_HOUR]);
                    }
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
