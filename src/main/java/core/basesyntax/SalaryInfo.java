package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final String SAMPLE = "*****";
    private static final int MAX_INDEX = 10;
    private static final int INITIAL_INDEX = 0;

    private final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period ");
        String[] checkedArray = new String[data.length];
        SalaryInfo salaryInfo = new SalaryInfo();
        for (int i = 0; i < data.length; i++) {
            LocalDate dateInfo = LocalDate.parse(data[i].substring(0, MAX_INDEX), formatter);
            if (fromDate.compareTo(dateInfo) <= 0 && toDate.compareTo(dateInfo) >= 0) {
                checkedArray[i] = data[i].substring(MAX_INDEX + 1);
            } else {
                checkedArray[i] = SAMPLE;
            }
        }
        result.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator())
                .append(salaryInfo.toCalculateSalary(names, checkedArray));
        return result.toString();
    }

    private String toCalculateSalary(String[] names, String[] infoData) {
        StringBuilder result = new StringBuilder();
        for (String name : names) {
            int salarySum = INITIAL_INDEX;
            for (int i = 0; i < infoData.length; i++) {
                if (infoData[i].contains(name)) {
                    String[] earnedMoney = infoData[i].substring(name.length() + 1).split(" ");
                    salarySum += Integer.parseInt(earnedMoney[0])
                            * Integer.parseInt(earnedMoney[1]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salarySum);
        }
        return result.toString().trim();
    }
}
