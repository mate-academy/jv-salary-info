package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOUR_INCOME_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startLocalDate = parseDate(dateFrom);
        LocalDate endLocalDate = parseDate(dateTo);
        int[] totalSalaries = new int[names.length];
        int workingHours;
        int hourSalary;
        for (String datum : data) {
            String[] splittedData = datum.split(" ");
            String date = splittedData[DATE_INDEX];
            String name = splittedData[NAME_INDEX];
            workingHours = Integer.parseInt(splittedData[HOURS_INDEX]);
            hourSalary = Integer.parseInt(splittedData[HOUR_INCOME_INDEX]);
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name) && isValidDate(startLocalDate,
                        endLocalDate, date)) {
                    totalSalaries[i] += workingHours * hourSalary;
                }
            }
        }
        return reportGenerator(names, totalSalaries, dateFrom, dateTo);
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    private boolean isValidDate(LocalDate startDate, LocalDate endDate, String checkedDate) {
        LocalDate localDate = parseDate(checkedDate);
        return localDate.isAfter(startDate)
                && localDate.isBefore(endDate.plusDays(1));
    }

    private String reportGenerator(String[] names, int[] totalSalaries,
                                   String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(names[i]).append(" - ").append(totalSalaries[i]);
        }
        return stringBuilder.toString();
    }
}




//package core.basesyntax;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.HashMap;
//
//public class SalaryInfo {
//    private static final int DATE_INDEX = 0;
//    private static final int NAME_INDEX = 1;
//    private static final int HOURS_INDEX = 2;
//    private static final int HOUR_INCOME_INDEX = 3;
//    private static final String SPLIT_REGEX = " ";
//    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
//    private final static String REPORT_REGEX = " - ";
//
//    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
//        LocalDate parsedDateFrom = parseDate(dateFrom);
//        LocalDate parsedDateTo = parseDate(dateTo);
//        HashMap<String, Integer> salariesMap = createMap(names);
//        String workerName;
//        String stringDate;
//        int hours;
//        int hourSalary;
//        for (String dataUnit : data) {
//            workerName = getNameFromDataUnit(dataUnit);
//            stringDate = getStringDate(dataUnit);
//            hours = getHoursFromDataUnit(dataUnit);
//            hourSalary = getIncomeFromDataUnit(dataUnit);
//            if (salariesMap.containsKey(workerName)
//                    && isValidDate(parseDate(stringDate), parsedDateFrom, parsedDateTo)) {
//                salariesMap.put(workerName, salariesMap.get(workerName) + hours * hourSalary);
//            }
//        }
//        return generateReport(salariesMap, dateFrom, dateTo);
//    }
//
//    private String getStringDate(String dataUnit) {
//        return dataUnit.split(SPLIT_REGEX)[DATE_INDEX];
//    }
//
//    private String getNameFromDataUnit(String dataUnit) {
//        return dataUnit.split(SPLIT_REGEX)[NAME_INDEX];
//    }
//
//    private int getHoursFromDataUnit(String dataUnit) {
//        return Integer.parseInt(dataUnit.split(SPLIT_REGEX)[HOURS_INDEX]);
//    }
//
//    private int getIncomeFromDataUnit(String dataUnit) {
//        return Integer.parseInt(dataUnit.split(SPLIT_REGEX)[HOUR_INCOME_INDEX]);
//    }
//
//    private LinkedHashMap<String, Integer> createMap(String[] names) {
//        LinkedHashMap<String, Integer> newMap = new LinkedHashMap<>();
//        for (String name : names) {
//            newMap.put(name, 0);
//        }
//        return newMap;
//    }
//
//    private boolean isValidDate(LocalDate dataDate, LocalDate dateFrom, LocalDate dateTo) {
//        return dataDate.isAfter(dateFrom) && dataDate.isBefore(dateTo.plusDays(1));
//    }
//
//    private LocalDate parseDate(String stringDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
//        return LocalDate.parse(stringDate, formatter);
//    }
//
//    private String generateReport(HashMap<String, Integer> salaryMap,
//                                  String dateFrom, String dateTo) {
//        StringBuilder reportBuilder = new StringBuilder();
//        reportBuilder.append("Report for period ")
//                .append(dateFrom)
//                .append(REPORT_REGEX)
//                .append(dateTo)
//                .append(System.lineSeparator());
//        for (Map.Entry<String, Integer> entry : salaryMap.entrySet()) {
//            reportBuilder.append(entry.getKey())
//                    .append(REPORT_REGEX)
//                    .append(entry.getValue())
//                    .append(System.lineSeparator());
//        }
//        return reportBuilder.toString().trim();
//    }
//}
