package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        String lineSeparator = System.lineSeparator();

        result.append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(lineSeparator);

        int[] salaries = this.countSalaries(names, data, dateFrom, dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
            if (i != names.length - 1) {
                result.append(lineSeparator);
            }
        }

        return result.toString();
    }

    private int[] convertDateToIntArray(String date) {
        String[] dateArray = date.split("\\.");
        int[] result = new int[dateArray.length];
        for (int i = 0; i < dateArray.length; i++) {
            result[i] = Integer.parseInt(dateArray[i]);
        }
        return result;
    }

    private boolean isDateValid(String dateActual, String dateFrom, String dateTo) {

        int[] dateActualArray = this.convertDateToIntArray(dateActual);
        int[] dateFromArray = this.convertDateToIntArray(dateFrom);
        int[] dateToArray = this.convertDateToIntArray(dateTo);

        boolean result = false;

        if (dateActualArray[2] >= dateFromArray[2]) {
            if (dateActualArray[2] > dateFromArray[2]) {
                result = true;
            } else if (dateActualArray[1] > dateFromArray[1]) {
                result = true;
            } else if (dateActualArray[1] == dateFromArray[1]) {
                result = dateActualArray[0] >= dateFromArray[0];
            }
        }

        if (result) {
            if (dateActualArray[2] <= dateToArray[2]) {
                if (dateActualArray[2] < dateToArray[2]) {
                    result = true;
                } else if (dateActualArray[1] < dateToArray[1]) {
                    result = true;
                } else if (dateActualArray[1] == dateToArray[1]) {
                    result = dateActualArray[0] <= dateToArray[0];
                } else {
                    result = false;
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    private int[] countSalaries(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] result = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (final String datum : data) {
                String[] dataSplit = datum.split(" ");
                if (names[i].equals(dataSplit[1])) {
                    if (
                            this.isDateValid(
                                    dataSplit[0],
                                    dateFrom,
                                    dateTo
                            )
                    ) {
                        result[i] += Integer.parseInt(dataSplit[3])
                                * Integer.parseInt(dataSplit[2]);
                    }
                }
            }
        }
        return result;
    }
}
