package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ARRAY_INDEX_ZERO = 0;
    private static final int ARRAY_INDEX_ONE = 1;
    private static final int ARRAY_INDEX_TWO = 2;
    private static final int ARRAY_INDEX_THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int salary;
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        String[] dataDetail;
        LocalDate date;
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (int j = 0; j < data.length; j++) {
                dataDetail = data[j].split(" ");
                date = LocalDate.parse(dataDetail[ARRAY_INDEX_ZERO], FORMATTER);
                if (dataDetail[ARRAY_INDEX_ONE].equals(names[i]) && !(date.isBefore(fromDate)
                        || date.isAfter(toDate))) {
                    salary += Integer.parseInt(dataDetail[ARRAY_INDEX_TWO])
                                * Integer.parseInt(dataDetail[ARRAY_INDEX_THREE]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salary);
        }
        return salaryInfo.toString();
    }
}
