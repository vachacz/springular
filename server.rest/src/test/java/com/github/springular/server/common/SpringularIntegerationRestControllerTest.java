package com.github.springular.server.common;

import com.github.springular.server.component.employee.IEmployeeBCI;
import com.github.springular.server.component.employee.repository.EmployeeRepository;
import com.github.springular.server.configuration.BackendConfiguration;
import com.github.springular.server.configuration.DataSourceConfiguration;
import com.github.springular.server.configuration.JsonEndpointConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JsonEndpointConfiguration.class, BackendConfiguration.class, DataSourceConfiguration.class})
@WebAppConfiguration
public class SpringularIntegerationRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void configureEmployeeEndpoint() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected MockMvc mvc() {
        return mockMvc;
    }
}
