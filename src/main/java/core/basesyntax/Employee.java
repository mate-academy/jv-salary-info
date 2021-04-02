package core.basesyntax;

public class Employee {
  private String name;
  private int salaryFromTo;

  public Employee(String name, int totalSalaryFromTo) {
    this.name = name;
    this.salaryFromTo = totalSalaryFromTo;
  }

  public String getName() {
    return name;
  }

  public int getSalaryFromTo() {
    return salaryFromTo;
  }

  public void setSalaryFromTo(int salaryFromTo) {
    this.salaryFromTo = salaryFromTo;
  }

  @Override
  public String toString() {
    return name + " - " + salaryFromTo;
  }
}
