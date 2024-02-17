package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate secondDate = LocalDate.parse(dateTo, FORMATTER);

        int[] incomeInfo = calculateIncomeEmployee(names, data, firstDate, secondDate);

        return formattingResult(dateFrom, dateTo, names, incomeInfo);
    }

    private boolean isDateInRange (LocalDate dataDate, LocalDate firstDate, LocalDate secondDate) {
        return  ((dataDate.isEqual(firstDate) || dataDate.isEqual(secondDate))
                || (dataDate.isAfter(firstDate) && dataDate.isBefore(secondDate)));
    }

    private int[] calculateIncomeEmployee (String[] names, String[] data, LocalDate firstDate, LocalDate secondDate) {
        final int DATE = 0;
        final int NAME = 1;
        final int HOURS = 2;
        final int SALARY_PER_HOUR = 3;
        int firstIncome;
        String[] dataInfo;
        LocalDate dataDate;
        int[] incomeInfo = new int[names.length];

        for (int c = 0; c < names.length; c++) {
            firstIncome = 0;
            for (String datum : data) {
                dataInfo = datum.split(" ");
                dataDate = LocalDate.parse(dataInfo[DATE], FORMATTER);

                if (dataInfo[NAME].equals(names[c]) && isDateInRange(dataDate, firstDate, secondDate)) {
                    firstIncome += Integer.parseInt(dataInfo[HOURS]) * Integer.parseInt(dataInfo[SALARY_PER_HOUR]);
                    incomeInfo[c] = firstIncome;
                } else {
                    incomeInfo[c] += 0;
                }
            }
        }
        return incomeInfo;
    }

    private String formattingResult (String dateFrom, String dateTo, String[] names, int[] incomeInfo) {
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
