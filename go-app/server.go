package main

import (
	"github.com/gin-gonic/gin"
	"github.com/hrs/workflows/worker"
)

func main() {
	server := gin.Default()
	server.GET("/test", func(ctx *gin.Context) {
		ctx.JSON(200, gin.H{
			"message": "OK!!",
		})
	})
	worker.RunWorker()
	server.Run(":8081")
}
