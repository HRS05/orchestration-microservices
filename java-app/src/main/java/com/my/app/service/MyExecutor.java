package com.my.app.service;

import com.my.app.exception.MyException;
import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import io.orkes.conductor.client.WorkflowClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@AllArgsConstructor
@Service
public class MyExecutor {

    private final WorkflowClient workflowClient;

    public Map<String, Object> workFlowExecutorService(Map<String, Object> mp) throws MyException {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("my_first_workflow_hrs");
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("param1", mp.get("param1"));
        inputData.put("param2", mp.get("param2"));
        workflowRequest.setInput(inputData);
        String workflowId = workflowClient.startWorkflow(workflowRequest);
        System.out.println("Workflow id: {"+workflowId+"}");
        return Map.of("workflowId", workflowId);
    }
}
