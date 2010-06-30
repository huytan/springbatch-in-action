/**
 * 
 */
package com.manning.sbia.ch08;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TransformingProcessingTest {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("readWriteJob")
	private Job jobWithItemProcessor;
	
	@Autowired
	@Qualifier("readWriteJobPojo")
	private Job jobWithAdapter;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUpt() {
		jdbcTemplate.update("delete from product");
	}

	@Test public void changingState() throws Exception {
		JobExecution exec = jobLauncher.run(jobWithItemProcessor, new JobParametersBuilder()
			.addString("inputFile", "classpath:/partner-products.txt")
			.toJobParameters()
		);
		Assert.assertEquals(ExitStatus.COMPLETED, exec.getExitStatus());
		List<Map<String,Object>> rows = jdbcTemplate.queryForList("select * from product");
		Assert.assertEquals(8,rows.size());
	}
	
	@Test public void changingStateWithAdapter() throws Exception {
		JobExecution exec = jobLauncher.run(jobWithAdapter, new JobParametersBuilder()
			.addString("inputFile", "classpath:/partner-products.txt")
			.toJobParameters()
		);
		Assert.assertEquals(ExitStatus.COMPLETED, exec.getExitStatus());
		List<Map<String,Object>> rows = jdbcTemplate.queryForList("select * from product");
		Assert.assertEquals(8,rows.size());
	}
	
}
