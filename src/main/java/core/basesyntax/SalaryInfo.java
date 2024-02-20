package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final DateTimeFormatter
            DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DELIMITER_SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = countSalary(names, data, dateFrom, dateTo);
        StringBuilder salaryInfo = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(LINE_SEPARATOR)
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return salaryInfo.toString();
    }

    private int[] countSalary(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate dateBeginning = convertStringToLocalDate(dateFrom);
        LocalDate dateEnding = convertStringToLocalDate(dateTo);
        for (int i = 0; i < data.length; i++) {
            LocalDate currentDate = convertStringToLocalDate(data[i].substring(0, 10));
            if (checkData(currentDate, dateBeginning, dateEnding)) {
                setMoney(data, i, salary, names);
            }
        }
        return salary;
    }

    private LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMAT);
    }

    private boolean checkData(LocalDate date, LocalDate dateBeginning, LocalDate dateEnding) {
        return !date.isBefore(dateBeginning) && !date.isAfter(dateEnding);
    }

    private void setMoney(String[] data, int index, int[] salary, String[] names) {
        String name = getName(data, index);
        for (int i = 0; i < names.length; ++i) {
            if (names[i].equals(name)) {
                salary[i] += getMoney(data, index);
                return;
            }
        }
    }

    private int getMoney(String[] data, int index) {
        String[] splitData = data[index].split(DELIMITER_SPACE);
        return Integer.parseInt(splitData[INDEX_OF_WORKING_HOURS])
                * Integer.parseInt(splitData[INDEX_OF_INCOME_PER_HOUR]);
    }

    private String getName(String[] data, int index) {
        String[] splitData = data[index].split(DELIMITER_SPACE);
        return splitData[INDEX_OF_NAME];
    }
}
