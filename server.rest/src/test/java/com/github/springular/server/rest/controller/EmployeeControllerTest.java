package com.github.springular.server.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springular.server.common.SpringularIntegerationRestControllerTest;
import com.github.springular.server.component.employee.EmployeeDO;
import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.Nationality;
import com.github.springular.server.component.employee.entity.EmployeeBE;
import com.github.springular.server.component.employee.entity.EmployeeConverters;
import com.github.springular.server.component.employee.repository.EmployeeRepository;
import com.github.springular.server.configuration.BackendConfiguration;
import com.github.springular.server.configuration.DataSourceConfiguration;
import com.github.springular.server.configuration.JsonEndpointConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JsonEndpointConfiguration.class, BackendConfiguration.class, DataSourceConfiguration.class})
@WebAppConfiguration
public class EmployeeControllerTest extends SpringularIntegerationRestControllerTest {

    @Autowired
    IEmployeeBCI employeeBCI;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void shouldGetEmployees() throws Exception {
        employeeBCI.createOrUpdateEmployee(employee("login001", "Barrack", "Obama"));
        employeeBCI.createOrUpdateEmployee(employee("login002", "Bill", "Clinton"));

        mvc().perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Barrack")))
                .andExpect(jsonPath("$[1].firstName", is("Bill")));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        employeeBCI.createOrUpdateEmployee(employee("login003", "Abraham", "Lincoln"));

        Integer firstId = employeeBCI.getEmployees().get(0).getId();

        mvc().perform(delete("/employee/" + firstId))
                .andExpect(status().isOk());

        assertThat(employeeRepository.findByLogin("login003")).isNull();
    }

    @Test
    public void shouldCreateEmployee() throws Exception {
        mvc().perform(post("/employee/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(employee("login004", "John", "Kennedy")))
        )
                .andExpect(status().isOk());

        EmployeeBE employeeBE = employeeRepository.findByLogin("login004");

        assertThat(employeeBE).isNotNull();
        assertThat(employeeBE.getFirstName()).isEqualTo("John");
        assertThat(employeeBE.getLastName()).isEqualTo("Kennedy");
    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
        employeeBCI.createOrUpdateEmployee(employee("login005", "Harrison", "Ford"));
        EmployeeBE employee = employeeRepository.findByLogin("login005");

        employee.setFirstName("Gerald");

        mvc().perform(post("/employee/" + employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(employee))
        )
                .andExpect(status().isOk());

        EmployeeBE employeeBE = employeeRepository.findOne(employee.getId());

        assertThat(employeeBE).isNotNull();
        assertThat(employeeBE.getFirstName()).isEqualTo("Gerald");
        assertThat(employeeBE.getLastName()).isEqualTo("Ford");
    }

    private byte[] asJson(EmployeeDO employee) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(employee);
    }

    private byte[] asJson(EmployeeBE employee) throws JsonProcessingException {
        return asJson(new EmployeeConverters.BEToDOConverter().convert(employee));
    }

    private EmployeeDO employee(String login, String firstName, String lastName) {
        EmployeeDO employeeDO = new EmployeeDO();
        employeeDO.setFirstName(firstName);
        employeeDO.setLastName(lastName);
        employeeDO.setLogin(login);
        employeeDO.setNationality(Nationality.POLAND.code());
        return employeeDO;
    }

}
