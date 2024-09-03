package com.techlab.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.techlab.entity.Employee;

@Configuration
public class EmployeeBatchConfig2 {

    @Bean
    public JdbcCursorItemReader<Employee> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Employee>()
            .dataSource(dataSource)
            .name("employeeReader")
            .sql("SELECT employee_id, name, salary FROM employees")
            .rowMapper(new BeanPropertyRowMapper<>(Employee.class))
            .build();
    }

    @Bean
    public EmployeeProcessor employeeProcessor() {
        return new EmployeeProcessor();
    }

    @Bean
    public FlatFileItemWriter<Employee> writer() {
        return new FlatFileItemWriterBuilder<Employee>()
            .name("employeeCsvWriter")
            .resource(new FileSystemResource("employees.csv"))
            .delimited()
            .delimiter(",")
            .names("employeeId", "name", "salary")
            .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Step step1(JobRepository jobRepository, 
                      DataSourceTransactionManager transactionManager,
                      JdbcCursorItemReader<Employee> reader, 
                      EmployeeProcessor processor, 
                      FlatFileItemWriter<Employee> writer) {
        return new StepBuilder("exportCsvStep", jobRepository)
            .<Employee, Employee>chunk(2, transactionManager)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }

    @Bean
    public Job exportUserJob(JobRepository jobRepository, 
                             Step step1, 
                             JobCompletionNotificationListener listener) {
        return new JobBuilder("exportUserJob", jobRepository)
            .listener(listener)
            .start(step1)
            .build();
    }
}
