package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitRowOfData = new String[4];
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo
                + System.lineSeparator());
        String dataName;
        String dataDate;
        int dataHours;
        int dataIncomePerHour;
        int salaryForPeriod = 0;

        //looping through names
        for (String name : names) {
            stringBuilder.append(name);
            stringBuilder.append(" - ");
            //looping through data for every name
            for (String dataRow : data) {
                splitRowOfData = dataRow.split(" ");
                dataDate = splitRowOfData[0];
                dataName = splitRowOfData[1];
                dataHours = Integer.parseInt(splitRowOfData[2]);
                dataIncomePerHour = Integer.parseInt(splitRowOfData[3]);
                if (dateFrom != null && dateTo != null && dataDate != null) {
                    try {
                        //check for meeting names
                        if (name.equals(dataName)
                                //Date from data row is after dateFrom?
                                && ((LocalDate.parse(dataDate, FORMATTER)
                                .isAfter(LocalDate.parse(dateFrom, FORMATTER))
                                //Date from data row is before dateTo?
                                && LocalDate.parse(dataDate, FORMATTER)
                                .isBefore(LocalDate.parse(dateTo, FORMATTER)))
                                //Checking inclusive case for dateFrom
                                || (LocalDate.parse(dataDate, FORMATTER)
                                .isEqual(LocalDate.parse(dateFrom, FORMATTER))
                                //Checking inclusive case for dateTo
                                || LocalDate.parse(dataDate, FORMATTER)
                                .isEqual(LocalDate.parse(dateTo, FORMATTER))))
                        ) {
                            salaryForPeriod += dataHours * dataIncomePerHour;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println(
                                "Parse exception thrown while getting LocalDate from date String"
                        );
                    }
                }
            }
            stringBuilder.append(salaryForPeriod);
            stringBuilder.append(System.lineSeparator());
            salaryForPeriod = 0;
        }
        return stringBuilder.toString().trim();
    }
}
