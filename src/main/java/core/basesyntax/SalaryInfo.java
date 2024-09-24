package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final int NAME_INDEX = 1;
    private static final String DATA_DELIMITER = " ";
    private static final String INDEX_NAME_DELIMITER = ":";
    private static final String INDEX_NAME_PAIRS_DELIMITER = "|";
    private static final String REPORT_DELIMITER = " - ";
    private static final String REPORT_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String indexedNameString = buildIndexedNameString(names);
        int[] salaries = new int[names.length];
        LocalDate dateFromParsed = parseStringToLocalDate(dateFrom);
        LocalDate dateToParsed = parseStringToLocalDate(dateTo);
        for (String dataRecord : data) {
            String[] records = dataRecord.split(DATA_DELIMITER);
            if (!indexedNameString.contains(records[NAME_INDEX])) {
                continue;
            }
            LocalDate parsedRecordDate = parseStringToLocalDate(records[DATE_INDEX]);
            if (parsedRecordDate.isBefore(dateFromParsed)) {
                continue;
            }
            if (parsedRecordDate.isAfter(dateToParsed)) {
                continue;
            }
            int salariesIndex = getSalariesIndexByName(indexedNameString, records[NAME_INDEX]);
            try {
                salaries[salariesIndex] +=
                        Integer.parseInt(records[INCOME_INDEX]) * Integer.parseInt(
                                records[HOURS_INDEX]);
            } catch (NumberFormatException e) {
                throw new RuntimeException(
                        "Wrong format of working hours or income per hour in " + "records line.");
            }
        }
        return composeReport(names, dateFrom, dateTo, salaries);
    }

    private static int getSalariesIndexByName(String indexedNameString, String employeeName) {
        String employeeNameFormatted =
                INDEX_NAME_DELIMITER + employeeName + INDEX_NAME_PAIRS_DELIMITER;
        int startIndex = indexedNameString.lastIndexOf(INDEX_NAME_PAIRS_DELIMITER,
                indexedNameString.indexOf(employeeNameFormatted));
        int endIndex = indexedNameString.indexOf(INDEX_NAME_DELIMITER, startIndex);
        String indexSalaries = indexedNameString.substring(startIndex + 1, endIndex);
        try {
            return Integer.parseInt(indexSalaries);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Wrong index format in indexed string of employee names.");
        }
    }

    private static String composeReport(String[] names, String dateFrom, String dateTo,
                                        int[] salaries) {
        StringBuilder reportBuilder = new StringBuilder(REPORT_HEADER).append(dateFrom)
                .append(REPORT_DELIMITER)
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(names[i]).append(REPORT_DELIMITER).append(salaries[i]);
            if (i < names.length - 1) {
                reportBuilder.append(System.lineSeparator());
            }
        }
        return String.valueOf(reportBuilder);
    }

    private static String buildIndexedNameString(String[] names) {
        StringBuilder indexedNameBuilder = new StringBuilder();
        int index = 0;
        for (String name : names) {
            indexedNameBuilder.append(index)
                    .append(INDEX_NAME_DELIMITER)
                    .append(name)
                    .append(INDEX_NAME_PAIRS_DELIMITER);
            index++;
        }
        return String.valueOf(indexedNameBuilder);
    }

    private static LocalDate parseStringToLocalDate(String date) {
        LocalDate dateParsed;
        try {
            dateParsed = LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Wrong date format.");
        }
        return dateParsed;
    }
}
