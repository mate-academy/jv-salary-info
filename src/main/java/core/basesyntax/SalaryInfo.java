package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateFromFormat = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToFormat = LocalDate.parse(dateTo, DATE_FORMAT);
        String userInfo = "";
        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name: names) {
            int totalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] arrDataSplit = data[j].split(" ");
                final LocalDate INPUT_DATE = LocalDate.parse(arrDataSplit[0], DATE_FORMAT);
                final String INPUT_NAME = arrDataSplit[1];
                final int INPUT_DAYS = Integer.parseInt(arrDataSplit[2]);
                final int SALARY_RATE = Integer.parseInt(arrDataSplit[3]);
                if (name.equals(INPUT_NAME) && (
                        INPUT_DATE.isAfter(dateFromFormat)
                                && INPUT_DATE.isBefore(dateToFormat)
                                || INPUT_DATE.equals(dateFromFormat)
                                || INPUT_DATE.equals(dateToFormat))) {
                    totalSalary += INPUT_DAYS * SALARY_RATE;
                }
                userInfo = (name + " - " + totalSalary);
            }
            builder.append(System.lineSeparator()).append(userInfo);
        }
        return builder.toString();
    }
}
