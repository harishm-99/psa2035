package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    //create add employee method for Employee entity
    public EmployeeDto addEmployee(EmployeeDto dto){

        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
//        employeeDto.setDate(new Date());
        return employeeDto;
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

//    public void updateEmployee(Long id, EmployeeDto dto) {
      public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
//        Optional<Employee> opEmp = employeeRepository.findById(id);

//        Employee employee = opEmp.get();

//        employee.setName(dto.getName());
//        employee.setEmailId(dto.getEmailId());
//        employee.setMobile(dto.getMobile());

        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updatedEmployee);

        return employeeDto;
    }
 
    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

//        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);

        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto = employees.stream()
                .map(emp->mapToDto(emp))
                .collect(Collectors.toList());

        return dto;
    }

    //converts Entity Object to Dto Object
    EmployeeDto mapToDto(Employee employee){

        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);

//        EmployeeDto dto = new EmployeeDto();

//        dto.setId(employee.getId());
//        dto.setEmailId(employee.getEmailId());
//        dto.setName(employee.getName());
//        dto.setMobile(employee.getMobile());
//
        return dto;
    }

    //converts Dto Object to Entity Object
    Employee mapToEntity(EmployeeDto dto){

        Employee emp = modelMapper.map(dto, Employee.class);
//        Employee emp = new Employee();
//
//        emp.setId(dto.getId());
//        emp.setEmailId(dto.getEmailId());
//        emp.setName(dto.getName());
//        emp.setMobile(dto.getMobile());

        return emp;
    }

    public EmployeeDto getEmployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                ()->new ResourceNotFound("Record not found with id : "+empId)
        );
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }
}