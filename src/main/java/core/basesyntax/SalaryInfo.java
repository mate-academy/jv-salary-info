package core.basesyntax;

public class SalaryInfo {
    private ValidDate validDate = new ValidDate();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int j = 0; j < names.length; j++) {
            result.append("\r\n").append(names[j]);
            int allSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] arrayWithDate = data[i].split(" ");
                if (arrayWithDate[1].equals(names[j])) {
                    if (validDate.checkValidDate(arrayWithDate[0], dateFrom, dateTo)) {
                        allSalary += Integer.parseInt(arrayWithDate[2])
                                * Integer.parseInt(arrayWithDate[3]);
                    }
                }
            }
            result.append(" - ").append(allSalary);
        }
        return result.toString();
    }
}
