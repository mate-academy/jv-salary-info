package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INPUT_DATE = 0;
    private static final int INPUT_NAME = 1;
    private static final int INPUT_DAYS = 2;
    private static final int INPUT_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormat = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate dateToFormat = LocalDate.parse(dateTo, DATE_FORMAT);

        String userInfo = "";
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] arrDataSplit = data[j].split(" ");
                LocalDate userDate = LocalDate.parse(arrDataSplit[INPUT_DATE], DATE_FORMAT);
                if (name.equals(arrDataSplit[INPUT_NAME]) && (
                        userDate.isAfter(dateFromFormat)
                                && userDate.isBefore(dateToFormat)
                                || userDate.equals(dateFromFormat)
                                || userDate.equals(dateToFormat))) {
                    totalSalary += (Integer.parseInt(arrDataSplit[INPUT_DAYS]))
                            * (Integer.parseInt(arrDataSplit[INPUT_RATE]));
                }
                userInfo = (name + " - " + totalSalary);
            }
            builder.append(System.lineSeparator()).append(userInfo);
        }
        return builder.toString();
    }
}
