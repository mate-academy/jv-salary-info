package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int[] countSalary = new int[names.length];
        LocalDate fromD = LocalDate.parse(dateFrom, format);
        LocalDate toD = LocalDate.parse(dateTo, format);
        String[] dataDetail;
        LocalDate date;

        for (int i = 0; i < data.length; i++) {
            dataDetail = data[i].split(" ");
            date = LocalDate.parse(dataDetail[0], format);
            if (!(date.isBefore(fromD) || date.isAfter(toD))) {
                for (int j = 0; j < names.length; j++) {
                    if (dataDetail[1].equals(names[j])) {
                        countSalary[j] += Integer.parseInt(dataDetail[2])
                                * Integer.parseInt(dataDetail[3]);
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(countSalary[i]);
        }

        return salaryInfo.toString();
    }
}
