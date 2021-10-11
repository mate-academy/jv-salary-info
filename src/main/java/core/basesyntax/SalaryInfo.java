package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Pattern patternData = Pattern.compile("^(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])"
                + "\\.((?:19|20)\\d{2}) ([a-z|A-Z])* ([1-9]|[0-9][1-9]|[1-9][0-9]) "
                + "(?:[1-9][0-9][0-9]?|1000)$");
        for (String s: data) {
            Matcher matcher = patternData.matcher(s);
            boolean found = matcher.matches();
            if (! found) {
                throw new RuntimeException("Wrong format of input data");
            }
        }
        String[] elementsOfData;
        int[] employeeSalary = new int[names.length];
        int indexEmployee = 0;
        try {
            Date dataFrom = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date dataTo = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            Date dataInData;
            Pattern patternTrimming = Pattern.compile(" ");

            for (String s : data) {
                elementsOfData = patternTrimming.split(s);
                // we keep here all the parts from our Data array[i]:
                // element[0] - date,
                // element[1] - name,
                // element[2] - working hours,
                // element[3] - rate per hour
                dataInData = new SimpleDateFormat("dd.MM.yyyy").parse(elementsOfData[0]);
                if ((!dataFrom.after(dataInData)) && (!dataTo.before(dataInData))) {
                    // checking who's gonna increase its salary
                    for (int j = 0; j < names.length; j++) {
                        if (names[j].equals(elementsOfData[1])) {
                            indexEmployee = j;
                        }
                    }
                    employeeSalary[indexEmployee] = employeeSalary[indexEmployee]
                            + (stringToInt(elementsOfData[2]) * stringToInt(elementsOfData[3]));
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Report for period ").append(dateFrom)
                    .append(" - ").append(dateTo);
            for (int i = 0; i < names.length; i++) {
                stringBuilder.append("\r\n").append(names[i]).append(" - ")
                        .append(employeeSalary[i]);
            }
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        } catch (ParseException e) {
            return "Data parse exception";
        }
    }

    public int stringToInt(String inputInt) {
        int i = 0;
        int intNumber = 0;
        if (inputInt.charAt(0) == '-') {
            throw new RuntimeException("Negative input data!");
        }
        while (i < inputInt.length()) {
            intNumber = intNumber * 10;
            intNumber = intNumber + inputInt.charAt(i++) - '0';
        }
        return intNumber;
    }
}
