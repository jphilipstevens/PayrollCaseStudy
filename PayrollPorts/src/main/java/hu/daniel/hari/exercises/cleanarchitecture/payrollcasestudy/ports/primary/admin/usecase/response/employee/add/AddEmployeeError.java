package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.multiple.Error;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.multiple.Visitable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.multiple.Visitor;

public interface AddEmployeeError extends Error, 
	Visitable<AddEmployeeError.AddEmployeeErrorVisitor, AddEmployeeError> 
{
	
	public interface AddEmployeeErrorVisitor extends Visitor<AddEmployeeErrorVisitor, AddEmployeeError> {
		<R> R visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
		<R> R visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
	}

}