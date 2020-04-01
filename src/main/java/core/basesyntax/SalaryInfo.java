package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {
        LocalDate localFrom = LocalDate.parse(dateFrom, DTF);
        LocalDate localTo = LocalDate.parse(dateTo, DTF);
        if (localTo.isBefore(localFrom)) {
            throw new IllegalDateParametersException();
        } else {
            StringBuilder result = new StringBuilder();
            result.append("Отчёт за период " + dateFrom + " - " + dateTo);
            for (int i = 0; i < names.length; i++) {
                int salary = 0;
                String[] time;
                for (int j = 0; j < data.length; j++) {
                    time = data[j].split(" ");
                    LocalDate localNow = LocalDate.parse(time[0], DTF);
                    if (time[1].equals(names[i])
                            && (localNow.isAfter(localFrom) || localNow.isEqual(localFrom))
                            && (localNow.isBefore(localTo) || localNow.isEqual(localTo))) {
                        salary = salary + Integer.parseInt(time[2]) * Integer.parseInt(time[3]);
                    }

                }
                result.append("\n" + names[i] + " - " + salary);
            }
            return result.toString();
        }

    }
}
