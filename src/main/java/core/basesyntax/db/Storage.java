package core.basesyntax.db;

import core.basesyntax.model.EmployeeWorkDayInfo;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static List<EmployeeWorkDayInfo> employeeWorkDayInfo = new ArrayList<>();

    public static List<EmployeeWorkDayInfo> getEmployeeWorkDayInfo() {
        return employeeWorkDayInfo;
    }
}
