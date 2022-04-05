package core.basesyntax;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SalaryInfo {
    private static final Calendar dateFromGet = new GregorianCalendar();
    private static final Calendar dateToGet = new GregorianCalendar();
    private static final Calendar date = new GregorianCalendar();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salary = new StringBuilder();
        dateFromGet.set(Integer.parseInt(dateFrom.split("\\.")[2]),
                Integer.parseInt(dateFrom.split("\\.")[1]) - 1,
                Integer.parseInt(dateFrom.split("\\.")[0]));
        dateToGet.set(Integer.parseInt(dateTo.split("\\.")[2]),
                Integer.parseInt(dateTo.split("\\.")[1]) - 1,
                Integer.parseInt(dateTo.split("\\.")[0]) + 1);
        salary.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salaryCounter = 0;
            salary.append(System.lineSeparator())
                    .append(name);
            for (String datum : data) {
                date.set(Integer.parseInt(datum.split(" ")[0].split("\\.")[2]),
                        Integer.parseInt(datum.split(" ")[0].split("\\.")[1]) - 1,
                        Integer.parseInt(datum.split(" ")[0].split("\\.")[0]));
                if (datum.split(" ")[1].equals(name)
                        && date.after(dateFromGet)
                        && date.before(dateToGet)) {
                    salaryCounter = Integer.parseInt(datum.split(" ")[2])
                            * Integer.parseInt(datum.split(" ")[3])
                            + salaryCounter;
                }
            }
            salary.append(" - ")
                    .append(salaryCounter);

        }

        return salary.toString();
    }
}
