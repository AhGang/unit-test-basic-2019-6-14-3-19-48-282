package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ExpenseService.Expense.ExpenseType.*;
import static ExpenseService.Project.ProjectType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(INTERNAL,"ProjectA");
        // when
        ProjectType result = project.getProjectType();
        // then
        Assertions.assertEquals(INTERNAL,result);

    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(EXTERNAL,"Project A");
        ExpenseService expenseService = new ExpenseService();
        // when
        ExpenseType result = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(EXPENSE_TYPE_A,result);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(EXTERNAL,"Project B");
        ExpenseService expenseService = new ExpenseService();
        // when
        ExpenseType result = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(EXPENSE_TYPE_B,result);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(EXTERNAL,"Other Project ");
        ExpenseService expenseService = new ExpenseService();
        // when
        ExpenseType result = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
        Assertions.assertEquals(OTHER_EXPENSE,result);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project project = new Project(UNEXPECTED_PROJECT_TYPE,"ProjectA");
        ExpenseService expenseService = new ExpenseService();
        // when&then
        Assertions.assertThrows(UnexpectedProjectTypeException.class,()->expenseService.getExpenseCodeByProjectTypeAndName(project));



    }
}