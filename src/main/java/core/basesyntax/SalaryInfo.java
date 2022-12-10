package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final static int INDEX_ZERO = 0;
    private final static int INDEX_ONE = 1;
    private final static int INDEX_TWO = 2;
    private final static int INDEX_THREE = 3;

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
                LocalDate userDate = LocalDate.parse(arrDataSplit[INDEX_ZERO], DATE_FORMAT);
                if (name.equals(arrDataSplit[INDEX_ONE]) && (
                        userDate.isAfter(dateFromFormat)
                                && userDate.isBefore(dateToFormat)
                                || userDate.equals(dateFromFormat)
                                || userDate.equals(dateToFormat))) {
                    totalSalary += (Integer.parseInt(arrDataSplit[INDEX_TWO]))
                            * (Integer.parseInt(arrDataSplit[INDEX_THREE]));
                }
                userInfo = (name + " - " + totalSalary);
            }
            builder.append(System.lineSeparator()).append(userInfo);
        }
        return builder.toString();
    }
}
