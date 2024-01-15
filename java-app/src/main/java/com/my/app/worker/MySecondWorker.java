package com.my.app.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MySecondWorker implements Worker {

    @Override
    public String getTaskDefName() {
        return "JAVA_TASK_2";
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult t = new TaskResult(task);
        Map<String, Object> outputData = new HashMap<>();
        String s = (String) task.getInputData().get("type");
        outputData.put("case", s);
        t.setOutputData(outputData);
        t.setStatus(TaskResult.Status.COMPLETED);
        return t;
    }
}
