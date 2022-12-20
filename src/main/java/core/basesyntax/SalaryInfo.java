
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DataTimeException, IllegalArgumentException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            String name = "";
            int resultSum = 0;
            for (int j = 0; j < data.length; j++) {
                String[] split = data[j].split(" ");
                try {
                    LocalDate fromThisDate = LocalDate.parse(dateFrom, dtf);
                    LocalDate toThisDate = LocalDate.parse(dateTo, dtf);
                    LocalDate date = LocalDate.parse(split[0], dtf);
                    if (date.isEqual(fromThisDate) || date.isEqual(toThisDate)
                            || date.isAfter(fromThisDate) && date.isBefore(toThisDate)) {
                        if (names[i].equals(split[1])) {
                            int parseInt = Integer.parseInt(split[2]);
                            int parseInt2 = Integer.parseInt(split[3]);
                            int result = parseInt * parseInt2;
                            resultSum += result;
                            if (!name.equals(names[i])) {
                                name += names[i];
                                builder.append(System.lineSeparator())
                                        .append(names[i])
                                        .append(" - ");
                            }
                        }
                    } else {
                        if (!name.equals(names[i])) {
                            name += names[i];
                            builder.append(System.lineSeparator())
                                    .append(names[i])
                                    .append(" - ");
                        }
                    }
                } catch (DateTimeParseException e) {
                    throw new DateTimeParseException("Can not parse string " + e);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Symbol not a number " + e);
                }
            }
            builder.append(resultSum);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}


