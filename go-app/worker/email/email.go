package email

import (
	"fmt"
	"time"

	"github.com/conductor-sdk/conductor-go/sdk/model"
)

type TaskOutput struct {
	Message string
}

type emailWorker struct {
	TaskName        string
	ExecuteFunction model.ExecuteTaskFunction
	BatchSize       int
	PollInterval    time.Duration
}

func emailWorkerExecutor(t *model.Task) (interface{}, error) {
	fmt.Println("Email send successfully")
	taskResult := &TaskOutput{
		Message: "Email send successfully",
	}
	return taskResult, nil
}

func GetEmailWorker() emailWorker {
	return emailWorker{
		TaskName:        "GO_TASK_6",
		ExecuteFunction: emailWorkerExecutor,
		BatchSize:       1,
		PollInterval:    time.Second * 1,
	}
}
