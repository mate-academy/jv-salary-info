package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final String GIVEN_DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(GIVEN_DATE_FORMAT, Locale.ENGLISH);
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_OF_WORK_POSITION = 2;
    private static final int INCOME_PER_HOUR_POSITION = 3;

    public static String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                       String dateTo) {
        LocalDate localDateFrom = parseDateFromStrings(dateFrom);
        LocalDate localDateTo = parseDateFromStrings(dateTo);
        int[] incomes = new int[names.length];
        for (String dataElement : data) {
            String[] temporarilyDividedData = dataElement.split(" ");
            LocalDate dateFromData = parseDateFromStrings(temporarilyDividedData[DATE_POSITION]);
            String nameFromDataPosition = temporarilyDividedData[NAME_POSITION];
            int hoursOfWork = Integer.parseInt(temporarilyDividedData[HOURS_OF_WORK_POSITION]);
            int incomePerHour = Integer.parseInt(temporarilyDividedData[INCOME_PER_HOUR_POSITION]);
            int incomeFromData = hoursOfWork * incomePerHour;
            if (isDateSufficient(dateFromData, localDateFrom, localDateTo)) {
                 incomes[indexOfValue(names, nameFromDataPosition)] += incomeFromData;
            }
        }
        return generateReport(names, incomes, dateFrom, dateTo);
    }

    private static LocalDate parseDateFromStrings(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private static int indexOfValue(String[] names, String targetName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(targetName)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isDateSufficient(LocalDate dateFromData,
                                            LocalDate localDateFrom, LocalDate localDateTo) {
        return (dateFromData.isAfter(localDateFrom)
                || dateFromData.isEqual(localDateFrom))
                && (dateFromData.isBefore(localDateTo)
                || dateFromData.isEqual(localDateTo));
    }

    private static String generateReport(String[] names, int[] incomes,
                                         String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        int loopCounter = 0;
        for (String name : names) {
            builder.append(name)
                    .append(" - ")
                    .append(incomes[loopCounter++])
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(getSalaryInfo(new String[]{"John", "Andrew", "Kate", "Rita"}, new String[] {
                "25.04.2019 John 60 50",
                "25.04.2019 Andrew 3 200",
                "25.04.2019 Kate 10 100",

                "26-04.2019 Andrew 3 200",
                "26.04.2019 Kate 9 100",

                "27.04.2019 John 7 100",
                "27.04.2019 Kate 3 80",
                "27.04.2019 Andrew 8 100"
        }, "24.04.2019", "27.04.2019"));
    }
}
