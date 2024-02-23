package com.bdn.multifiles;

import com.bdn.multifiles.job.SimpleFileJobConfig;
import com.bdn.multifiles.reader.MultiFileReaderConfig;
import com.bdn.multifiles.step.MultiStepConfig;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

//MultiFilesApplication.class,
//,SimpleFileJobConfig.class, MultiFileReaderConfig.class, MultiStepConfig.class
@SpringBatchTest
@SpringJUnitConfig(classes = {MultiFilesApplication.class})
@TestPropertySource("classpath:application-test.properties")
class MultiFilesApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	void contextLoads() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

	}
}
