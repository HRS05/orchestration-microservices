package com.my.app.api;

import com.my.app.exception.MyException;
import com.my.app.service.MyExecutor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class Api {

    public final MyExecutor myExecutor;
    @GetMapping()
    public ResponseEntity<String> status() throws MyException {
        return ResponseEntity.ok("Working");
    }

    @PostMapping("/exec-task")
    public ResponseEntity<Map<String, Object>> execTask(@RequestBody Map<String,Object> data) throws MyException {
        Map<String, Object> res = myExecutor.workFlowExecutorService(data);
        return ResponseEntity.ok(res);
    }
}
