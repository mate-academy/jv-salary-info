package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int POSITION_OF_DATE = 0;
    private static final int POSITION_OF_NAME = 1;
    private static final int POSITION_OF_WORKED_HOURS = 2;
    private static final int POSITION_OF_SALARY_PER_HOURS = 3;
    private static final String HYPHEN_WITH_SPACES = " - ";
    private static final String SEPARATOR = System.lineSeparator();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dataSplit;
        String[] dataOutput = new String[names.length];
        int j = 0;
        for (String name : names) {
            int sum = 0;
            for (String oneData : data) {
                dataSplit = oneData.split(" ");
                String dataDate = dataSplit[POSITION_OF_DATE];
                if (dataSplit[POSITION_OF_NAME].equals(name)) {
                    if (getDate(dataDate).isAfter(getDate(dateFrom).minusDays(1))
                            && getDate(dataDate).isBefore(getDate(dateTo).plusDays(1))) {
                        sum += Integer.parseInt(dataSplit[POSITION_OF_WORKED_HOURS])
                                * Integer.parseInt(dataSplit[POSITION_OF_SALARY_PER_HOURS]);
                    }
                }
            }
            dataOutput[j] = names[j] + HYPHEN_WITH_SPACES + sum;
            j++;
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + SEPARATOR + getStringFromNewLine(dataOutput);
    }

    private LocalDate getDate(String date) {
        LocalDate parsedDate;
        return LocalDate.parse(date, FORMATTER);
    }

    private String getStringFromNewLine(String[] strings) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            builder.append(strings[i]).append(SEPARATOR);
        }
        return builder.append(strings[strings.length - 1]).toString();
    }
}
