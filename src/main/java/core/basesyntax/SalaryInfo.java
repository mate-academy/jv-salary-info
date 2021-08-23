package core.basesyntax;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];

        for (String oneLine : data) {
            String[] dataArray = oneLine.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (!LocalDate.parse(dataArray[DATE_INDEX], DATE_FORMAT).isBefore(LocalDate.parse(dateFrom, DATE_FORMAT))
                        && !LocalDate.parse(dataArray[DATE_INDEX], DATE_FORMAT).isAfter(LocalDate.parse(dateTo, DATE_FORMAT))
                        && dataArray[NAME_INDEX].equals(names[i])) {
                    salary[i] += Integer.parseInt(dataArray[HOURS_INDEX]) * Integer.parseInt(dataArray[SALARY_INDEX]);
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        answer.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            answer.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return answer.toString();
    }
}
