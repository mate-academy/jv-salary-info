package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                date = LocalDate.parse(dataDetail[0], FORMATTER);
                if (dataDetail[1].equals(names[i]) && !(date.isBefore(fromDate)
                        || date.isAfter(toDate))) {
                    salary += Integer.parseInt(dataDetail[2])
                                * Integer.parseInt(dataDetail[3]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salary);
        }

        return salaryInfo.toString();
    }
}
