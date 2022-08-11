package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        int yearFrom = Integer.parseInt(dateFrom.split("\\.")[2]);
        int monthFrom = Integer.parseInt(dateFrom.split("\\.")[1]);
        int dayFrom = Integer.parseInt(dateFrom.split("\\.")[0]);

        int yearTo = Integer.parseInt(dateTo.split("\\.")[2]);
        int monthTo = Integer.parseInt(dateTo.split("\\.")[1]);
        int dayTo = Integer.parseInt(dateTo.split("\\.")[0]);

        for (int i = 0; i < names.length; i++) {
            int counter = 0;
            for (String datum : data) {
                String[] parts = datum.split(" ");
                if (!parts[1].equals(names[i])) {
                    continue;
                }
                int year = Integer.parseInt(parts[0].split("\\.")[2]);
                int month = Integer.parseInt(parts[0].split("\\.")[1]);
                int day = Integer.parseInt(parts[0].split("\\.")[0]);
                if (year == yearFrom && year == yearTo) {
                    if (month == monthFrom && month == monthTo) {
                        if (day < dayFrom || day > dayTo) {
                            continue;
                        } else {
                            if (parts[1].equals(names[i])) {
                                counter += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                            }
                        }
                    } else {
                        if (month < monthFrom || month > monthTo) {
                            continue;
                        } else {
                            if (month == monthFrom) {
                                if (day < dayFrom) {
                                    continue;
                                } else {
                                    if (parts[1].equals(names[i])) {
                                        counter += Integer.parseInt(parts[2])
                                                * Integer.parseInt(parts[3]);
                                    }
                                }
                            } else if (month == monthTo) {
                                if (day > dayTo) {
                                    continue;
                                } else {
                                    if (parts[1].equals(names[i])) {
                                        counter += Integer.parseInt(parts[2])
                                                * Integer.parseInt(parts[3]);
                                    }
                                }
                            } else {
                                if (parts[1].equals(names[i])) {
                                    counter += Integer.parseInt(parts[2])
                                            * Integer.parseInt(parts[3]);
                                }
                            }
                        }
                    }
                } else {
                    if (year < yearFrom || year > yearTo) {
                        continue;
                    } else {
                        if (parts[1].equals(names[i])) {
                            counter += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                        }
                    }
                }
            }
            builder.append(names[i])
                    .append(" - ")
                    .append(counter);
            if (i != names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
