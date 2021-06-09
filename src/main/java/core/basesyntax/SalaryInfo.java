package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATA = 0;
    private static final int INDEX_FROM_DATA = 1;
    private static final int INDEX_SALARY_PER_HOUR = 2;
    private static final int INDEX_AMOUNT_HOURS = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        LocalDate datefrom = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate dateto = LocalDate.parse(dateTo,FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String datas : data) {
                String [] massive = datas.split(" ");
                LocalDate now = LocalDate.parse(massive[INDEX_DATA],FORMATTER);
                if (name.equals(massive[INDEX_FROM_DATA]) && now.compareTo(datefrom) >= 0
                        && now.compareTo(dateto) <= 0) {
                    salary += Integer.parseInt(massive[INDEX_SALARY_PER_HOUR])
                            * Integer.parseInt(massive[INDEX_AMOUNT_HOURS]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}

