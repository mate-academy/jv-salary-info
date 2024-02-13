package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = getDate(dateFrom);
        LocalDate localDateTo = getDate(dateTo);

        LocalDate [] consistencyLocalData = new LocalDate[data.length];
        String [] consistencyEmployee = new String[data.length];
        int [] consistencyWorkingHourDuringTheParticularDay = new int [data.length];
        int [] consistencyIncomePerHour = new int[data.length];

        int [] sum = new int[names.length];

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < data.length; i++) {
            String [] dataSplit = data[i].split(" ");

            consistencyLocalData[i] = getDate(dataSplit[0]);
            consistencyEmployee[i] = dataSplit[1];
            consistencyWorkingHourDuringTheParticularDay[i] = Integer.parseInt(dataSplit[2]);
            consistencyIncomePerHour[i] = Integer.parseInt(dataSplit[3]);
        }

        for (int i = 0; i < names.length; i++) { //проходим по іменах

            for (int j = 0; j < consistencyEmployee.length; j++) {
                if (consistencyEmployee[j].equals(names[i])) {

                    if (consistencyLocalData[j].isEqual(localDateFrom)
                            || consistencyLocalData[j].isEqual(localDateTo)
                            || consistencyLocalData[j].isAfter(localDateFrom)
                            && consistencyLocalData[j].isBefore(localDateTo)) {

                        sum[i] += (consistencyWorkingHourDuringTheParticularDay[j]
                                * consistencyIncomePerHour[j]);
                    }
                }
            }

            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(sum[i]);
        }

        return stringBuilder.toString();

    }

    public static LocalDate getDate(String input) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(input, formatter);
    }

}
