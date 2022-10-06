package core.basesyntax;

public class SalaryInfo {
    private String salaryInfo;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (names[i].equals(getName(data[j]))
                        && getMonth(data[j]) == getMonth(dateFrom)
                        && getMonth(data[j]) == getMonth(dateTo)
                        && getDay(data[j]) >= getDay(dateFrom)
                        && getDay(data[j]) <= getDay(dateTo)
                        && !dateFrom.equals(dateTo)) {
                    salary += getIndex(data[j]) * getSum(data[j]);
                } else if (names[i].equals(getName(data[j]))
                        && getMonth(data[j]) == getMonth(dateFrom)
                        && getMonth(data[j]) < getMonth(dateTo)
                        && getDay(data[j]) >= getDay(dateFrom)) {
                    salary += getIndex(data[j]) * getSum(data[j]);
                } else if (names[i].equals(getName(data[j]))
                        && getMonth(data[j]) > getMonth(dateFrom)
                        && getMonth(data[j]) < getMonth(dateTo)) {
                    salary += getIndex(data[j]) * getSum(data[j]);
                } else if (names[i].equals(getName(data[j]))
                        && getMonth(data[j]) == getMonth(dateTo)
                        && getMonth(data[j]) > getMonth(dateFrom)
                        && getDay(data[j]) <= getDay(dateTo)) {
                    salary += getIndex(data[j]) * getSum(data[j]);
                }
            }
            if (i == 2) {
                builder.append(names[i] + " - " + salary);
            } else {
                builder.append(names[i] + " - " + salary + System.lineSeparator());
                salary = 0;
            }
        }
        salaryInfo = builder.toString();
        return salaryInfo;
    }

    public int getDay(String dataString) {
        String[] array = dataString.split(" ");
        String[] day = array[0].split("\\.");
        return Integer.parseInt(day[0]);
    }

    public int getMonth(String dataString) {
        String[] array = dataString.split(" ");
        String[] month = array[0].split("\\.");
        return Integer.parseInt(month[1]);
    }

    public String getName(String dataString) {
        String[] array = dataString.split(" ");
        return array[1];
    }

    public int getIndex(String dataString) {
        String[] array = dataString.split(" ");
        return Integer.parseInt(array[2]);
    }

    public int getSum(String dataString) {
        String[] array = dataString.split(" ");
        return Integer.parseInt(array[3]);
    }
}
