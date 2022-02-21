package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate before = parseString(dateFrom);
        LocalDate after = parseString(dateTo);
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            sb.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(streamCalculator(data, after, before, name));
        }
        return sb.toString();
    }

    private Integer streamCalculator(String[] data, LocalDate after,
                                     LocalDate before, String name) {
        int salary = 0;
        return Arrays.stream(data)
                .map(dates -> dates.split(" "))
                .filter(thisDate ->
                        checkData(parseString(thisDate[0]), after, before)
                                && thisDate[1].equals(name))
                .map(hour -> (Integer.parseInt(hour[2]) * Integer.parseInt(hour[3])))
                .reduce(Integer::sum).orElse(salary);
    }

    private boolean checkData(LocalDate infoEmployerDate, LocalDate after, LocalDate before) {
        return (after.isAfter(infoEmployerDate) || after.isEqual(infoEmployerDate))
                && (before.isBefore(infoEmployerDate) || before.isEqual(infoEmployerDate));
    }

    private LocalDate parseString(String dateTo) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(" "
                    + dateTo + "is not parsable! Please use \"dd.MM.yyyy\" format.", e);
        }
    }
}
