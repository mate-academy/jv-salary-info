package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            final Date dateFromFormatted = dateFormatter.parse(dateFrom);
            final Date dateToFormatted = dateFormatter.parse(dateTo);
            StringBuilder salaryInfo = new StringBuilder("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo);
            for (String name : names) {
                int salarySum = 0;
                for (String emplData : data) {
                    String[] emplDataSplitted = emplData.split(" ");
                    Date dataSalaryFormatted = dateFormatter.parse(emplDataSplitted[0]);
                    if (name.equals(emplDataSplitted[1])
                            && (dateFromFormatted.compareTo(dataSalaryFormatted) < 1
                            && dateToFormatted.compareTo(dataSalaryFormatted) > -1)
                    ) {
                        salarySum += Integer.parseInt(emplDataSplitted[2])
                                * Integer.parseInt(emplDataSplitted[3]);
                    }
                }
                salaryInfo.append(System.lineSeparator())
                        .append(name)
                        .append(" - ")
                        .append(salarySum);
            }
            return salaryInfo.toString();
        } catch (ParseException e) {
            return "Wrongs data";
        }
    }
}
