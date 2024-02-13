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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = countSalary(names, data, dateFrom, dateTo);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + LINE_SEPARATOR);
        for (int i = 0; i < names.length; i++) {
            if (i < names.length - 1) {
                salaryInfo.append(names[i])
                        .append(" - ")
                        .append(salary[i])
                        .append(LINE_SEPARATOR);
            } else {
                salaryInfo.append(names[i])
                        .append(" - ")
                        .append(salary[i]);
            }
        }
        return salaryInfo.toString();
    }

    private LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMAT);
    }

    private boolean checkData(LocalDate date, LocalDate dateBeginning, LocalDate dateEnding) {
        return (date.isAfter(dateBeginning) || date.equals(dateBeginning))
                && (date.isBefore(dateEnding) || date.equals(dateEnding));
    }

    private String getName(String[] data, int index) {
        String[] splitData = data[index].split(" ");
        return splitData[INDEX_OF_NAME];
    }

    private Boolean checkName(String[] data, int index, String[] names) {
        for (String name : names) {
            if (getName(data, index).equals(name)) {
                return true;
            }
        }
        return false;
    }

    private int getMoney(String[] data, int index) {
        String[] splitData = data[index].split(" ");
        return Integer.parseInt(splitData[INDEX_OF_WORKING_HOURS])
                * Integer.parseInt(splitData[INDEX_OF_INCOME_PER_HOUR]);
    }

    private void setMoney(String[] data, int index, int[] salary, String[] names) {
        for (int i = 0; i < names.length; ++i) {
            if (names[i].equals(getName(data, index))) {
                salary[i] += getMoney(data, index);
            }
        }
    }

    private int[] countSalary(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate dateBeginning = convertStringToLocalDate(dateFrom);
        LocalDate dateEnding = convertStringToLocalDate(dateTo);
        for (int i = 0; i < data.length; i++) {
            LocalDate currentDate = convertStringToLocalDate(data[i].substring(0, 10));
            if (checkData(currentDate, dateBeginning, dateEnding)
                    && checkName(data, i, names)) {
                setMoney(data, i, salary, names);
            }
        }
        return salary;
    }
}
