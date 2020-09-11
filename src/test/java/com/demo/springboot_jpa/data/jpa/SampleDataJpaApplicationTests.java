package com.demo.springboot_jpa.data.jpa;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration test to run the application.
 * 
 * @author Vijayasai Kesanupalli
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
// Separate profile for web tests to avoid clashing databases
public class SampleDataJpaApplicationTests {}
