package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter OUR_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_AMOUNT_OF_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String workInfo: data) {
                String[] dateOfWork = workInfo.split(" ");
                if (name.equals(dateOfWork[INDEX_OF_NAME])) {
                    if (!LocalDate.parse(dateOfWork[INDEX_OF_DATE], OUR_FORMAT)
                            .isBefore(LocalDate.parse(dateFrom, OUR_FORMAT))
                            && !LocalDate.parse(dateOfWork[INDEX_OF_DATE], OUR_FORMAT)
                            .isAfter(LocalDate.parse(dateTo, OUR_FORMAT))) {
                        salary += Integer.parseInt(dateOfWork[INDEX_OF_AMOUNT_OF_HOURS])
                                * Integer.parseInt(dateOfWork[INDEX_OF_INCOME_PER_HOUR]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
