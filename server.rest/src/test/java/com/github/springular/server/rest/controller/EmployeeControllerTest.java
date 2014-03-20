package com.github.springular.server.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springular.server.component.employee.EmployeeDO;
import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.Nationality;
import com.github.springular.server.component.employee.entity.EmployeeBE;
import com.github.springular.server.component.employee.impl.EmployeeBCI;
import com.github.springular.server.component.employee.repository.EmployeeRepository;
import com.github.springular.server.configuration.BackendConfiguration;
import com.github.springular.server.configuration.DataSourceConfiguration;
import com.github.springular.server.configuration.JsonEndpointConfig;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JsonEndpointConfig.class, BackendConfiguration.class, DataSourceConfiguration.class})
@WebAppConfiguration
public class EmployeeControllerTest {

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    IEmployeeBCI employeeBCI;

    @Autowired
    EmployeeRepository employeeRepository;

    @Before
    public void configureEmployeeEndpoint() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldGetEmployees() throws Exception {
        employeeBCI.createOrUpdateEmployee(employee("login001", "Barrack", "Obama"));
        employeeBCI.createOrUpdateEmployee(employee("login002", "Bill", "Clinton"));

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Barrack")))
                .andExpect(jsonPath("$[1].firstName", is("Bill")));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        employeeBCI.createOrUpdateEmployee(employee("login003", "Abraham", "Lincoln"));

        Integer firstId = employeeBCI.getEmployees().get(0).getId();

        mockMvc.perform(delete("/employee/" + firstId))
                .andExpect(status().isOk());

        assertThat(employeeRepository.exists(3)).isFalse();
    }

    @Test
    public void shouldCreateEmployee() throws Exception {

        mockMvc.perform(post("/employee/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJson(employee("login004", "John", "Kennedy")))
                )
                .andExpect(status().isOk());

        List<EmployeeBE> employees = employeeRepository.findAll();

        EmployeeBE employeeBE = employeeRepository.findByLogin("login004");
        
        assertThat(employeeBE).isNotNull();
        assertThat(employeeBE.getFirstName()).isEqualTo("John");
        assertThat(employeeBE.getLastName()).isEqualTo("Kennedy");
    }

    private byte[] asJson(EmployeeDO employee) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(employee);
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
