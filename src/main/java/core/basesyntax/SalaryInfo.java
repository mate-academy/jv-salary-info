package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalaryInfo {
    private static final DateTimeFormatter
            DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private static final int ZERO_INCOME = 0;
    private static final int EXTRA_WORKING_DAY = 1;
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORK_HOURS = 2;
    private static final int INDEX_OF_INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> mapWithFilteredData =
                getHashMapWithFilteredData(names, data, dateFrom, dateTo);
        StringBuilder stringBuilder = getReportStringBuilder(dateFrom, dateTo);
        return getReportMessage(stringBuilder, mapWithFilteredData, names);
    }

    private StringBuilder getReportStringBuilder(String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        return stringBuilder;
    }

    private String getReportMessage(StringBuilder stringBuilder,
                                    Map<String, Integer> mapWithFilteredData,
                                    String[] names) {
        return stringBuilder
                .append(
                        Arrays.stream(names)
                                .map(name ->
                                        System.lineSeparator()
                                                + name
                                                + " - "
                                                + (mapWithFilteredData.getOrDefault(
                                                        name, ZERO_INCOME))
                                )
                                .collect(Collectors.joining())
                ).toString();
    }

    private Map<String, Integer> getHashMapWithFilteredData(String[] names, String[] data,
                                                            String dateFrom, String dateTo) {
        return Stream.of(data)
                .map(string -> string.split(" "))
                .filter(stringArray ->
                        LocalDate.parse(
                                stringArray[INDEX_OF_DATE],
                                        DATE_TIME_FORMATTER)
                                .isAfter(LocalDate.parse(
                                        dateFrom,
                                        DATE_TIME_FORMATTER))
                                && LocalDate.parse(
                                        stringArray[INDEX_OF_DATE],
                                        DATE_TIME_FORMATTER)
                                .isBefore((LocalDate.parse(
                                        dateTo,
                                        DATE_TIME_FORMATTER)
                                        .plusDays(EXTRA_WORKING_DAY)))
                                && List.of(names)
                                .contains(stringArray[INDEX_OF_NAME]))
                .collect(Collectors.toMap(stringArray -> stringArray[INDEX_OF_NAME],
                        stringArray ->
                                Integer.parseInt(
                                        stringArray[INDEX_OF_WORK_HOURS])
                                        * Integer.parseInt(
                                                stringArray[INDEX_OF_INCOME]),
                        Integer::sum));
    }
}

