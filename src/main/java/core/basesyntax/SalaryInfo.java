package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        dateFrom = dateFrom.trim();
        dateTo = dateTo.trim();

        StringBuilder report = new StringBuilder();
        appendReportHeader(report, dateFrom, dateTo);

        try {
            LocalDate fromDate = parseDate(dateFrom);
            LocalDate toDate = parseDate(dateTo);

            for (String name : names) {
                int earnedMoney = calculateEarnedMoneyForName(name, data, fromDate, toDate);
                appendReportEntry(report, name, earnedMoney);
            }
        } catch (DateTimeParseException exception) {
            throw new RuntimeException("Can't parse this date", exception);
        }

        return report.toString();
    }

    private void appendReportHeader(StringBuilder report, String dateFrom, String dateTo) {
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(LINE_SEPARATOR);
    }

    private LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    private int calculateEarnedMoneyForName(
            String name, String[] data, LocalDate fromDate, LocalDate toDate) {
        int earnedMoney = 0;
        for (String entry : data) {
            String[] entryData = entry.split(" ");
            LocalDate entryDate = parseDate(entryData[0]);
            if (isWithinDateRange(entryDate, fromDate, toDate)
                    && isMatchingName(name, entryData[1])) {
                int hours = Integer.parseInt(entryData[2]);
                int hourlyRate = Integer.parseInt(entryData[3]);
                earnedMoney += hours * hourlyRate;
            }
        }
        return earnedMoney;
    }

    private boolean isWithinDateRange(LocalDate entryDate, LocalDate fromDate, LocalDate toDate) {
        return !entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate);
    }

    private boolean isMatchingName(String name, String workerName) {
        return workerName.equals(name);
    }

    private void appendReportEntry(StringBuilder report, String name, int earnedMoney) {
        report.append(name).append(" - ").append(earnedMoney).append(LINE_SEPARATOR);
    }
}



//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class SalaryInfo {
//    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
//
//        StringBuilder report = new StringBuilder();
//        report.append("Report for period ").append(dateFrom).append(" - ")
//                .append(dateTo).append("\n");
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        try {
//            Date fromDate = dateFormat.parse(dateFrom);
//            Date toDate = dateFormat.parse(dateTo);
//
//            for (String name : names) {
//                int earnedMoney = 0;
//                for (String workerInfo : data) {
//                    String[] workersList = workerInfo.split(" ");
//                    Date entryDate = dateFormat.parse(workersList[0]);
//
//                    if (workersList[1].equals(name) && entryDate.compareTo(fromDate) >= 0
//                            && entryDate.compareTo(toDate) <= 0) {
//                        int hours = Integer.parseInt(workersList[2]);
//                        int hourlyRate = Integer.parseInt(workersList[3]);
//                        earnedMoney += hours * hourlyRate;
//                    }
//                }
//                report.append(name).append(" - ").append(earnedMoney).append("\n");
//            }
//        } catch (ParseException exception) {
//            throw new RuntimeException("Can't parse this date" ,exception);
//        }
//
//        return report.toString();
//    }
//}

