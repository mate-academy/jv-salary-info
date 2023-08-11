package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder info = new StringBuilder("Report for period ");
        info.append(dateFrom).append(" - ").append(dateTo);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date dateFromDate = dateFormat.parse(dateFrom);
            Date dateToDate = dateFormat.parse(dateTo);

            for (int i = 0; i < names.length; i++) {
                int tempSalaryInMonth = 0;
                for (int j = 0; j < data.length; j++) {
                    String[] tempSpaceSeparatorData = data[j].split(" ");

                    Date temporaryDate = dateFormat.parse(tempSpaceSeparatorData[0]);

                    if ((temporaryDate.after(dateFromDate) || temporaryDate.equals(dateFromDate))
                            && (temporaryDate.before(dateToDate) || temporaryDate.equals(dateToDate))) {
                        if (data[j].contains(names[i])) {
                            int tempHourInThatDay = Integer.parseInt(tempSpaceSeparatorData[2]);
                            int tempSalaryPerHourInThatDay = Integer
                                    .parseInt(tempSpaceSeparatorData[3]);

                            int tempSalarySumInThatDay = tempHourInThatDay * tempSalaryPerHourInThatDay;
                            tempSalaryInMonth += tempSalarySumInThatDay;
                        }
                    }
                    if (j == data.length - 1) {
                        info.append(System.lineSeparator()).append(names[i])
                                .append(" - ").append(tempSalaryInMonth);
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("date in not valid");
        }
        return info.toString();
    }
}
