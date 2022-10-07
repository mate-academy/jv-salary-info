package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String salaryInfo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (names[i].equals(getName(data[j]))
                        && !getDate(dateFrom).isEqual(getDate(dateTo))
                        && (getDate(data[j]).isAfter(getDate(dateFrom))
                        || getDate(data[j]).isEqual(getDate(dateFrom)))
                        && (getDate(data[j]).isBefore(getDate(dateTo))
                        || getDate(data[j]).isEqual(getDate(dateTo)))) {
                    salary += getIndex(data[j]) * getSum(data[j]);
                }
            }
            if (i == 2) {
                builder.append(names[i] + " - " + salary);
            } else {
                builder.append(names[i] + " - " + salary + System.lineSeparator());
                salary = 0;
            }
        }
        salaryInfo = builder.toString();
        return salaryInfo;
    }

    public LocalDate getDate(String dataString) {
        String[] array = dataString.split(" ");
        return LocalDate.parse(array[0], FORMATTER);
    }

    public String getName(String dataString) {
        String[] array = dataString.split(" ");
        return array[1];
    }

    public int getIndex(String dataString) {
        String[] array = dataString.split(" ");
        return Integer.parseInt(array[2]);
    }

    public int getSum(String dataString) {
        String[] array = dataString.split(" ");
        return Integer.parseInt(array[3]);
    }
}
