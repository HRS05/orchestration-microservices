package com.my.app.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyFirstWorker implements Worker {
    @Override
    public String getTaskDefName() {
        return "JAVA_TASK_1";
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult t = new TaskResult(task);
        Map<String, Object> outputData = new HashMap<>();
        String s = (String) task.getInputData().get("param1");
        String v = (String) task.getInputData().get("param2");
        String ty = (String) task.getInputData().get("type");
        System.out.println(s);
        System.out.println(v);
        outputData.put("type",ty);
        t.setOutputData(outputData);
        t.setStatus(TaskResult.Status.COMPLETED);
        return t;
    }
}
