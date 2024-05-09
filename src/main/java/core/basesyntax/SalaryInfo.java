package core.basesyntax;

public class SalaryInfo {
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

    private boolean workingUpToDate(int[] dateEmploy, int[] dateSalaryFrom, int[] dateSalaryTo) {
        if (dateEmploy[2] >= dateSalaryFrom[2] && dateEmploy[2] <= dateSalaryTo[2]) {
            if (dateEmploy[1] >= dateSalaryFrom[1] && dateEmploy[1] <= dateSalaryTo[1]) {
                return !(dateEmploy[1] == dateSalaryFrom[1]
                        && dateEmploy[0] < dateSalaryFrom[0])
                        && !(dateEmploy[1] == dateSalaryTo[1]
                        && dateEmploy[0] > dateSalaryTo[0]);
            }
        }

        return false;
    }
}
