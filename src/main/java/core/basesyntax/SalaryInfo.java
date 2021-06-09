package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATA = 0;
    private static final int INDEX_SALARY_PERHOUR = 2;
    private static final int INDEX_AMOUNT_HOURS = 3;


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int j = 0; j<names.length; j++) {
            int amountSalary = 0;
            for (int i = 0; i < data.length; i++) {
                if (data[i].contains(names[j])) {
                    String[] array = data[i].split(" ");
                    LocalDate date = LocalDate.parse(array[INDEX_DATA], FORMATTER);
                    if (date.compareTo(from) >= 0 && date.compareTo(to) <= 0) {
                        amountSalary += Integer.valueOf(array[INDEX_SALARY_PERHOUR])
                                * Integer.valueOf(array[INDEX_AMOUNT_HOURS]);
                    }
                }
            }
            result.append("\n").append(names[j]).append(" - ").append(amountSalary);
        }
        return result.toString();
    }
}

