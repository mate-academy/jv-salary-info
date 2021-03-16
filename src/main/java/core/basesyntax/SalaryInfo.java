package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final String formatter = "dd.MM.yyyy";

    protected String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            return "Incorrect input data";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");
        LocalDate localDateFrom = parseStringToLocalDate(dateFrom);
        LocalDate localDateTo = parseStringToLocalDate(dateTo);
        int salarySummary;
        for (int i = 0; i < names.length; i++) {
            salarySummary = 0;
            for (int j = 0; j < data.length; j++) {
                if (checkName(names[i], data[j])) {
                    if (checkDate(localDateFrom, localDateTo.plusDays(1), data[j])) {
                        salarySummary += parseAndCalcSalary(data[j]);
                    }
                }
            }
            sb.append(names[i]).append(" - ").append(salarySummary).append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private LocalDate parseStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(formatter));
    }

    private boolean checkName(String name, String data) {
        if (name != null && data != null) {
            data = data.replaceAll("[^A-Za-z]+", "");
            if (data.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDate(LocalDate dateFrom, LocalDate dateTo, String data) {
        if (data != null) {
            data = data.replaceAll("([\\d\\.]+)[\\w\\d\\s]+", "$1");
            LocalDate dateFromData = parseStringToLocalDate(data);
            if (dateFromData.isAfter(dateFrom) && dateFromData.isBefore(dateTo)) {
                return true;
            }
        }
        return false;
    }

    private int parseAndCalcSalary(String data) {
        int hours = Integer.parseInt(data.replaceAll("[\\d\\.]+[\\D]+ ([\\d]+)[\\s\\d]+$", "$1"));
        int rate = Integer.parseInt(data.replaceAll("[\\d\\.]+[\\D]+ [\\d]+\\s([\\d]+)$", "$1"));
        return hours * rate;
    }

}
