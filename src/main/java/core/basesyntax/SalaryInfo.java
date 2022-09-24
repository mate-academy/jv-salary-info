package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter).plusDays(1);
        LocalDate[] dates = new LocalDate[data.length];
        String[] namesOfData = new String[data.length];
        int[] hours = new int[data.length];
        int[] rate = new int[data.length];
        int[] salary = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] datas = data[i].split(" ");
            dates[i] = LocalDate.parse(datas[0], formatter);
            namesOfData[i] = datas[1];
            hours[i] = Integer.parseInt(datas[2]);
            rate[i] = Integer.parseInt(datas[3]);
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(namesOfData[i])
                        && (dates[i].isAfter(startDate) && dates[i].isBefore(endDate))) {
                    salary[j] = (hours[i] * rate[i]) + salary[j];
                }
            }
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator()
                + names[0] + " - " + salary[0]
                + System.lineSeparator()
                + names[1] + " - " + salary[1]
                + System.lineSeparator()
                + names[2] + " - " + salary[2];
    }
}
