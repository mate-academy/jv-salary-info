package core.basesyntax;

import static java.lang.Integer.valueOf;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        // defence against empty input
        if (names == null
                || data == null
                || dateFrom == null
                || dateTo == null) {
            throw new RuntimeException("Empty data");
        }

        //array with int = 0 for each name, which will be used to sum up salaries
        int [] results = new int[names.length];

        // both dates converted into numbers yyyymmdd
        String[] dateFromSplitted = dateFrom.split("\\.");
        int dateFromConvertedIntoNumber = valueOf(dateFromSplitted[2]) * 10000
                + valueOf(dateFromSplitted[1]) * 100
                + valueOf(dateFromSplitted[0]);

        String [] dateToSplitted = dateTo.split("\\.");
        int dateToConvertedIntoNumber = valueOf(dateToSplitted[2]) * 10000
                + valueOf(dateToSplitted[1]) * 100
                + valueOf(dateToSplitted[0]);

        // StringBuilder collects data from a loop and returns the result as a String
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        //1. outer loop is taking name by name
        //2. inner loop divides data rows into arrays with separated
        //   [0] data [1] name [2] working hours [3] income per hour
        //3. inner loop compares names in a given row
        //   if conditions are met, increases the pay value on the j-position
        //4. outer loop is combining searched information
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                String[] dataSplitted = data[i].split(" ");
                String[] dateSplitted = dataSplitted[0].split("\\.");
                int dateConvertedIntoNumber = valueOf(dateSplitted[2]) * 10000
                        + valueOf(dateSplitted[1]) * 100
                        + valueOf(dateSplitted[0]);

                if (dateConvertedIntoNumber >= dateFromConvertedIntoNumber
                        && dateConvertedIntoNumber <= dateToConvertedIntoNumber
                        && dataSplitted[1].equals(names[j])) {
                    results[j] = results[j] + valueOf(dataSplitted[2]) * valueOf(dataSplitted[3]);
                }
            }
            builder.append(names[j])
                    .append(" - ")
                    .append(results[j]);
            if (j != results.length - 1) {
                builder.append(System.lineSeparator());
            }
        }

        String raport = builder.toString();
        return raport;
    }
}
