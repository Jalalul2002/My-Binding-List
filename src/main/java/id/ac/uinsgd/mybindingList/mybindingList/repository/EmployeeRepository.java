package id.ac.uinsgd.mybindingList.mybindingList.repository;

import id.ac.uinsgd.mybindingList.mybindingList.employee.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {
    void saveEmployee(Employee employee);
}
