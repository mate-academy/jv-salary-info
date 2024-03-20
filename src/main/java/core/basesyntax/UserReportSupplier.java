package core.basesyntax;

public class UserReportSupplier {
    private final UserWithSalary user;

    public UserReportSupplier(UserWithSalary user) {
        this.user = user;
    }

    public String createUserReportTextLine() {
        return System.lineSeparator() + user.getUserName() + " - " + user.getSalaryAmount();
    }
}

