package worker

import (
	"fmt"

	"github.com/conductor-sdk/conductor-go/sdk/client"
	"github.com/conductor-sdk/conductor-go/sdk/settings"
	"github.com/conductor-sdk/conductor-go/sdk/worker"
	"github.com/hrs/workflows/worker/email"
	"github.com/hrs/workflows/worker/push"
	"github.com/hrs/workflows/worker/sms"
)

func RunWorker() {
	fmt.Println("Wroker running start")

	apiClient := client.NewAPIClient(
		settings.NewAuthenticationSettings(
			KEY,
			SECRATE,
		),
		settings.NewHttpSettings(
			"https://play.orkes.io/api",
		))

	fmt.Println(apiClient)

	taskRunner := worker.NewTaskRunnerWithApiClient(apiClient)

	emailWorker := email.GetEmailWorker()
	pushWorker := push.GetPushWorker()
	smsWorker := sms.GetSmsWorker()

	taskRunner.StartWorker(emailWorker.TaskName, emailWorker.ExecuteFunction, emailWorker.BatchSize, emailWorker.PollInterval)
	taskRunner.StartWorker(pushWorker.TaskName, pushWorker.ExecuteFunction, pushWorker.BatchSize, pushWorker.PollInterval)
	taskRunner.StartWorker(smsWorker.TaskName, smsWorker.ExecuteFunction, smsWorker.BatchSize, smsWorker.PollInterval)

	taskRunner.WaitWorkers()
}
