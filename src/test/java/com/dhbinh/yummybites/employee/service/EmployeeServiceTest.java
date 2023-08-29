package com.dhbinh.yummybites.employee.service;

import com.dhbinh.yummybites.base.exception.ResourceNotFoundException;
import com.dhbinh.yummybites.employee.entity.Employee;
import com.dhbinh.yummybites.employee.entity.StatusEnum;
import com.dhbinh.yummybites.employee.repository.EmployeeRepository;
import com.dhbinh.yummybites.employee.service.dto.EmployeeDTO;
import com.dhbinh.yummybites.employee.service.mapper.EmployeeMapper;
import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import com.dhbinh.yummybites.restaurant.service.RestaurantService;
import com.dhbinh.yummybites.utils.Utils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.jpa.domain.Specification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dhbinh.yummybites.employee.entity.StatusEnum.STATUS_ACTIVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private Utils utils;

    private Restaurant validRestaurant() {
        return Restaurant.builder()
                .id(1L)
                .name("YummyBites")
                .address("4089 Charing Cross Drive")
                .phone("341-724-5075")
                .openHour(LocalTime.of(11, 0, 0))
                .closingHour(LocalTime.of(22, 0, 0))
                .build();
    }

    private Employee validEmployee() {
        return Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .phone("341-724-5075")
                .email("johndoe@example.com")
                .address("4089 Charing Cross Drive")
                .dob(LocalDate.of(1984, 12, 31))
                .status(STATUS_ACTIVE)
                .restaurant(validRestaurant())
                .build();
    }

    private EmployeeDTO validEmployeeDTO() {
        return EmployeeDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .phone("341-724-5075")
                .email("johndoe@example.com")
                .address("4089 Charing Cross Drive")
                .dob(LocalDate.of(1990, 12, 31))
                .restaurantName("YummyBites")
                .build();
    }

    @Test
    void createEmployee_WithMandatoryFields_ReturnDTO() {

    }

    @Test
    void findEmployee_ExistedId_ReturnDTO() {
        Employee employee = validEmployee();

        EmployeeDTO dto = validEmployeeDTO();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDTO(employee)).thenReturn(dto);

        EmployeeDTO result = employeeService.findById(1L);

        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getPhone(), result.getPhone());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getAddress(), result.getAddress());
        assertEquals(dto.getDob(), result.getDob());
        assertEquals(dto.getRestaurantName(), result.getRestaurantName());
    }

    @Test
    void findEmployee_NonExistedId_ThrowException() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employeeService.findById(999L));
    }

    @Test
    void findEmployee_WithExistedEmail_ReturnDTO() {
        Employee employee = validEmployee();

        EmployeeDTO dto = validEmployeeDTO();

        when(employeeRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(employee));
        when(employeeMapper.toDTO(employee)).thenReturn(dto);

        EmployeeDTO result = employeeService.findByEmail("johndoe@example.com");

        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getPhone(), result.getPhone());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getAddress(), result.getAddress());
        assertEquals(dto.getDob(), result.getDob());
        assertEquals(dto.getRestaurantName(), result.getRestaurantName());
    }


    @Test
    void findEmployee_WithNonExistedEmail_ThrowException() {
        when(employeeRepository.findByEmail("non-existed-email@gmail.com")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employeeService.findByEmail("non-existed-email@gmail.com"));
    }

    @Test
    public void testFindAll_PositiveCase() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeDTOs.add(new EmployeeDTO());
        employeeDTOs.add(new EmployeeDTO());
        when(employeeMapper.toDTOList(employees)).thenReturn(employeeDTOs);

        List<EmployeeDTO> result = employeeService.findAll();
        assertEquals(employeeDTOs, result);
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testExportEmployeeList() throws IOException {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe", LocalDate.of(1990, 12, 30), "Address 1", "123456789", "john.doe@example.com", STATUS_ACTIVE, validRestaurant()));
        employees.add(new Employee(2L, "Jane", "Smith", LocalDate.of(1990, 11, 30), "Address 2", "987654321", "jane.smith@example.com", StatusEnum.STATUS_ACTIVE, validRestaurant()));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeDTOs.add(new EmployeeDTO(1L, "John", "Doe", LocalDate.of(1990, 12, 30), "Address 1", "123456789", "john.doe@example.com", StatusEnum.STATUS_ACTIVE, "YummyBites"));
        employeeDTOs.add(new EmployeeDTO(2L, "Jane", "Smith", LocalDate.of(1990, 11, 30), "Address 2", "987654321", "jane.smith@example.com", StatusEnum.STATUS_ACTIVE, "YummyBites"));

        when(employeeMapper.toDTOList(employees)).thenReturn(employeeDTOs);

        employeeService.exportEmployeeList();

        File file = new File("employee_data.xlsx");

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet("Employee Data");

            Row titleRow = sheet.getRow(0);
            assertEquals("First Name", titleRow.getCell(0).getStringCellValue());
            assertEquals("Last Name", titleRow.getCell(1).getStringCellValue());
            assertEquals("Address", titleRow.getCell(2).getStringCellValue());
            assertEquals("Phone", titleRow.getCell(3).getStringCellValue());
            assertEquals("Email", titleRow.getCell(4).getStringCellValue());
            assertEquals("Restaurant", titleRow.getCell(5).getStringCellValue());

            for (int i = 0; i < employeeDTOs.size(); i++) {
                Row row = sheet.getRow(i + 1);
                EmployeeDTO employeeDTO = employeeDTOs.get(i);

                assertEquals(employeeDTO.getFirstName(), row.getCell(0).getStringCellValue());
                assertEquals(employeeDTO.getLastName(), row.getCell(1).getStringCellValue());
                assertEquals(employeeDTO.getAddress(), row.getCell(2).getStringCellValue());
                assertEquals(employeeDTO.getPhone(), row.getCell(3).getStringCellValue());
                assertEquals(employeeDTO.getEmail(), row.getCell(4).getStringCellValue());
                assertEquals(employeeDTO.getRestaurantName(), row.getCell(5).getStringCellValue());
            }
        }
    }

    @Test
    public void testFindWithSpecifications_Positive() {
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        int day = 1;
        int month = 1;
        int year = 1990;
        int ageLessThan = 30;
        int ageGreaterThan = 20;

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());

        when(employeeRepository.findAll(any(Specification.class))).thenReturn(employees);
        when(employeeMapper.toDTOList(employees)).thenReturn(new ArrayList<>());

        List<EmployeeDTO> result = employeeService.findWithSpecifications(firstName, lastName, address, day, month, year, ageLessThan, ageGreaterThan);

        assertEquals(0, result.size());
        verify(employeeRepository, times(1)).findAll(any(Specification.class));
        verify(employeeMapper, times(1)).toDTOList(employees);
    }

    @Test
    public void deleteEmployee_NonExistedId_ThrowException() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(999L));
    }
}