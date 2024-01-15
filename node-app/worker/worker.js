const {
  orkesConductorClient,
  TaskManager,
} = require("@io-orkes/conductor-javascript");

const config = {
  keyId: "__KEY__",
  keySecret: "__KEY_SECRET__",
  serverUrl: `https://play.orkes.io/api`,
};

(async () => {
  console.log("cunductor got setup -- initiated --");
  const clientPromise = orkesConductorClient(config);
  const client = await clientPromise;
  console.log(client);
  console.log("cunductor got setup -- done --");

  const NODE_WROKER_1 = {
    taskDefName: "NODE_TASK_3",
    execute: async (task) => {
      console.log("NODE_TASK_3 got called");
      return {
        outputData: {
          hello: "From your worker",
        },
        status: "COMPLETED",
      };
    },
  };

  const NODE_WROKER_2 = {
    taskDefName: "NODE_TASK_4",
    execute: async (task) => {
      console.log("NODE_TASK_4 got called");
      setTimeout(()=>{
        console.log("hey");
      }, 5000);
      return {
        outputData: {
          hello: "From your worker 5",
        },
        status: "COMPLETED",
      };
    },
  };

  const manager = new TaskManager(client, [NODE_WROKER_1, NODE_WROKER_2]);

  manager.startPolling();
})();
