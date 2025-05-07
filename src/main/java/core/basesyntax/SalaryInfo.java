package core.basesyntax;

public class SalaryInfo {
    private static final int YEAR_INDEX = 2;
    private static final int MONTH_INDEX = 1;
    private static final int DAY_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] dateFromArr = getDateInArrayIntegerFormat(dateFrom);
        int[] dateToArr = getDateInArrayIntegerFormat(dateTo);

        StringBuilder outputBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salaryCounter = 0;
            outputBuilder.append("\n");

            for (String info : data) {
                String[] infoArr = info.split(" ");

                if (infoArr[1].equals(name)) {
                    int[] dateEmploy = getDateInArrayIntegerFormat(infoArr[0]);

                    if (workingUpToDate(dateEmploy, dateFromArr, dateToArr)) {
                        salaryCounter += Integer.parseInt(infoArr[2])
                                * Integer.parseInt(infoArr[3]);
                    }
                }
            }

            outputBuilder
                    .append(name)
                    .append(" - ")
                    .append(salaryCounter);
        }

        return outputBuilder.toString();
    }

    private int[] getDateInArrayIntegerFormat(String date) {
        int[] dateInArrayInteger = new int[3];
        String[] dateInArrayString = date.split("\\.");

        for (int i = 0; i < dateInArrayInteger.length; i++) {
            dateInArrayInteger[i] = Integer.parseInt(dateInArrayString[i]);
        }

        return dateInArrayInteger;
    }

    private boolean workingUpToDate(int[] dateOfWork, int[] dateSalaryFrom, int[] dateSalaryTo) {
        if (dateOfWork[YEAR_INDEX] >= dateSalaryFrom[YEAR_INDEX]
                && dateOfWork[YEAR_INDEX] <= dateSalaryTo[YEAR_INDEX]) {
            if (dateOfWork[MONTH_INDEX] >= dateSalaryFrom[MONTH_INDEX]
                    && dateOfWork[MONTH_INDEX] <= dateSalaryTo[MONTH_INDEX]) {
                return !(dateOfWork[MONTH_INDEX] == dateSalaryFrom[MONTH_INDEX]
                        && dateOfWork[DAY_INDEX] < dateSalaryFrom[DAY_INDEX])
                        && !(dateOfWork[MONTH_INDEX] == dateSalaryTo[MONTH_INDEX]
                        && dateOfWork[DAY_INDEX] > dateSalaryTo[DAY_INDEX]);
            }
        }

        return false;
    }
}
