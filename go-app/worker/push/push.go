package push

import (
	"fmt"
	"time"

	"github.com/conductor-sdk/conductor-go/sdk/model"
)

type TaskOutput struct {
	Message string
}

type PushWorker struct {
	TaskName        string
	ExecuteFunction model.ExecuteTaskFunction
	BatchSize       int
	PollInterval    time.Duration
}

func pushWorkerExecutor(t *model.Task) (interface{}, error) {
	fmt.Println("Push send successfully")
	taskResult := &TaskOutput{
		Message: "Push send successfully",
	}
	return taskResult, nil
}

func GetPushWorker() *PushWorker {
	return &PushWorker{
		TaskName:        "GO_TASK_5",
		ExecuteFunction: pushWorkerExecutor,
		BatchSize:       1,
		PollInterval:    time.Second * 1,
	}
}
