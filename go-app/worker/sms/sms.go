package sms

import (
	"fmt"
	"time"

	"github.com/conductor-sdk/conductor-go/sdk/model"
)

type TaskOutput struct {
	Message string
}

type SmsWorker struct {
	TaskName        string
	ExecuteFunction model.ExecuteTaskFunction
	BatchSize       int
	PollInterval    time.Duration
}

func smsWorkerExecutor(t *model.Task) (interface{}, error) {
	fmt.Println("Sms send successfully")
	taskResult := &TaskOutput{
		Message: "Sms send successfully",
	}
	return taskResult, nil
}

func GetSmsWorker() *SmsWorker {
	return &SmsWorker{
		TaskName:        "GO_TASK_7",
		ExecuteFunction: smsWorkerExecutor,
		BatchSize:       1,
		PollInterval:    time.Second * 1,
	}
}
