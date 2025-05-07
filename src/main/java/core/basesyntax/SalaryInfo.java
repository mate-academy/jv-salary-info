package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final String SPLITTER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder tempSB = new StringBuilder();
        LocalDate currentDate;
        String[] userData;
        int income = 0;
        tempSB.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            for (String info : data) {
                userData = info.split(SPLITTER);
                currentDate = LocalDate.parse(userData[DAY_INDEX], FORMATTER);
                if (userData[NAME_INDEX].equals(name) && dateFrom != null && dateTo != null
                        && (!currentDate.isBefore(LocalDate.parse(dateFrom, FORMATTER))
                        && !currentDate.isAfter(LocalDate.parse(dateTo, FORMATTER)))) {
                    income += Integer.parseInt(userData[WORK_HOURS_INDEX])
                            * Integer.parseInt(userData[SALARY_INDEX]);
                }
            }
            tempSB.append(System.lineSeparator()).append(name).append(" - ").append(income);
            income = 0;
        }
        return tempSB.toString();
    }
}
