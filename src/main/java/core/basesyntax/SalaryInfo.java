package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] elementsOfData;
        int[] employeeSalary = new int[names.length];
        int indexEmployee = 0;
        try {
            Date parsedDateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date parsedDateTo = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            for (String s : data) {
                elementsOfData = s.split(" ");
                // we keep here all the parts from our Data array[i]:
                // element[0] - date,
                // element[1] - name,
                // element[2] - working hours,
                // element[3] - rate per hour
                Date dateFromDataArray = new SimpleDateFormat("dd.MM.yyyy")
                        .parse(elementsOfData[0]);
                if ((!parsedDateFrom.after(dateFromDataArray))
                        && (!parsedDateTo.before(dateFromDataArray))) {
                    // searching array of names for the one to increase its salary
                    for (int j = 0; j < names.length; j++) {
                        if (names[j].equals(elementsOfData[1])) {
                            indexEmployee = j;
                        }
                    }
                    employeeSalary[indexEmployee] = employeeSalary[indexEmployee]
                            + Integer.parseInt(elementsOfData[2])
                            * Integer.parseInt(elementsOfData[3]);
                }
            }
            StringBuilder report = new StringBuilder();
            report.append("Report for period ").append(dateFrom)
                    .append(" - ").append(dateTo);
            for (int i = 0; i < names.length; i++) {
                report.append(System.lineSeparator()).append(names[i]).append(" - ")
                        .append(employeeSalary[i]);
            }
            return report.toString();
        } catch (ParseException e) {
            return "Data parse exception";
        }
    }
}
