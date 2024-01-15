package com.my.app;

import com.netflix.conductor.client.worker.Worker;
import io.micrometer.common.util.StringUtils;
import io.orkes.conductor.client.ApiClient;
import io.orkes.conductor.client.OrkesClients;
import io.orkes.conductor.client.TaskClient;
import io.orkes.conductor.client.WorkflowClient;
import io.orkes.conductor.client.automator.TaskRunnerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import java.util.List;

@SpringBootApplication
public class AppApplication {

	private static final String CONDUCTOR_SERVER_URL = "conductor.server.url";
	private static final String CONDUCTOR_CLIENT_KEY_ID = "conductor.security.client.key-id";
	private static final String CONDUCTOR_CLIENT_SECRET = "conductor.security.client.secret";

	private final Environment env;

	public AppApplication(Environment env) {
		this.env = env;
	}


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public OrkesClients orkesClients() {
		String rootUri = env.getProperty(CONDUCTOR_SERVER_URL);
		String key = env.getProperty(CONDUCTOR_CLIENT_KEY_ID);
		String secret = env.getProperty(CONDUCTOR_CLIENT_SECRET);

		if ("_CHANGE_ME_".equals(key) || "_CHANGE_ME_".equals(secret)) {
			System.out.println("Please provide an application key id and secret");
			throw new RuntimeException("No Application Key");
		}

		ApiClient apiClient = null;

		System.out.println("Conductor Server URL: {}"+ rootUri);
		if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(secret)) {
			System.out.println("Using Key and Secret to connect to the server");
			apiClient = new ApiClient(rootUri, key, secret);
		} else {
			System.out.println("setCredentialsIfPresent: Proceeding without client authentication");
			apiClient = new ApiClient(rootUri);
		}
		OrkesClients orkesClients = new OrkesClients(apiClient);
		return orkesClients;
	}

	@Bean
	public TaskClient taskClient(OrkesClients orkesClients) {
		System.out.println(orkesClients);
		TaskClient taskClient = (TaskClient) orkesClients.getTaskClient();
		return taskClient;
	}

	@Bean
	public WorkflowClient workflowClient(OrkesClients orkesClients) {
		WorkflowClient workflowClient = orkesClients.getWorkflowClient();
		return workflowClient;
	}

	@Bean
	public TaskRunnerConfigurer taskRunnerConfigurer(List<Worker> workersList, TaskClient taskClient) {
		System.out.println("Starting workers : {}"+ workersList);
		TaskRunnerConfigurer runnerConfigurer = new TaskRunnerConfigurer
				.Builder(taskClient, workersList)
				.withThreadCount(Math.max(1, workersList.size()))
				.build();
		runnerConfigurer.init();
		return runnerConfigurer;
	}

}
