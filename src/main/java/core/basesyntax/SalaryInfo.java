package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DEFAULT_PATTERN =
            "(\\d{2}\\.\\d{2}\\.\\d{4})\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)";
    private static final Pattern PATTERN = Pattern.compile(DEFAULT_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int totalForName = 0;
            for (String data1 : data) {
                Matcher matcher = PATTERN.matcher(data1);
                if (matcher.find()) {
                    LocalDate localDate = LocalDate.parse(matcher.group(1), FORMATTER);
                    String nameFromArr = matcher.group(2);
                    if (isNameEquals(nameFromArr, name) && isDateEqualsOrInMiddleOfSpecified(
                            localDateFrom, localDateTo, localDate)) {
                        int hours = Integer.parseInt(matcher.group(3));
                        int payment = Integer.parseInt(matcher.group(4));
                        totalForName += hours * payment;
                    }
                }
            }
            stringBuilder.append("\n").append(name).append(" - ")
                    .append(totalForName);
        }
        return stringBuilder.toString();
    }

    private boolean isNameEquals(String nameFromArr, String name) {
        return nameFromArr.equals(name);
    }

    private boolean isDateEqualsOrInMiddleOfSpecified(
            LocalDate localDateFrom, LocalDate localDateTo, LocalDate localDateFromArr) {
        return localDateFromArr.isAfter(localDateFrom)
                && localDateFromArr.isBefore(localDateTo)
                || localDateFromArr.isEqual(localDateFrom)
                || localDateFromArr.isEqual(localDateTo);
    }
}
