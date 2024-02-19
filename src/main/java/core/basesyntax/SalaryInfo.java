package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate secondDate = LocalDate.parse(dateTo, FORMATTER);

        int[] incomeInfo = calculateEmployeeIncome(names, data, firstDate, secondDate);

        return generateReport(dateFrom, dateTo, names, incomeInfo);
    }

    private int[] calculateEmployeeIncome(String[] names, String[] data,
                                          LocalDate firstDate, LocalDate secondDate) {
        int currentEmployeeIncome;
        String[] dataInfo;
        LocalDate dataDate;
        int[] incomeInfo = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            currentEmployeeIncome = 0;
            for (String datum : data) {
                dataInfo = datum.split(" ");
                dataDate = LocalDate.parse(dataInfo[DATE_INDEX], FORMATTER);

                if (dataInfo[NAME_INDEX].equals(names[i]) && isDateInRange(dataDate, firstDate,
                        secondDate)) {
                    currentEmployeeIncome += Integer.parseInt(dataInfo[HOURS_INDEX])
                            * Integer.parseInt(dataInfo[SALARY_PER_HOUR]);
                    incomeInfo[i] = currentEmployeeIncome;
                }
            }
        }
        return incomeInfo;
    }

    private boolean isDateInRange(LocalDate dataDate, LocalDate firstDate, LocalDate secondDate) {
        return ((dataDate.isEqual(firstDate) || dataDate.isEqual(secondDate))
                || (dataDate.isAfter(firstDate) && dataDate.isBefore(secondDate)));
    }

    private String generateReport(String dateFrom, String dateTo, String[] names,
                                  int[] incomeInfo) {
        StringBuilder formattedInfo = new StringBuilder();
        formattedInfo.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            formattedInfo.append(names[i]).append(" - ")
                    .append(incomeInfo[i]).append(System.lineSeparator());
        }

        return formattedInfo.toString().trim();
    }
}
