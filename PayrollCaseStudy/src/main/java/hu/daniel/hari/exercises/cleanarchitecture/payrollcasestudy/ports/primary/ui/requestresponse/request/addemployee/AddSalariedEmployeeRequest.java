package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee;

public class AddSalariedEmployeeRequest extends AddEmployeeRequest {
	public int monthlySalary;

	public AddSalariedEmployeeRequest(int employeeId, String name, String address, int monthlySalary) {
		super(employeeId, name, address);
		this.monthlySalary = monthlySalary;
	}
}