<template>
  <div class="container">
    <h1>Request For Workload (RFW)</h1>
    <form @submit.prevent="processForm">

      <label>Serialization Type</label>
      <select v-model="serializationType" required>
        <option>JSON</option>
        <option>Proto</option>
      </select>

      <label>RFW ID</label>
      <input type="text" v-model="rfwid" required/>

      <label>Benchmark Type</label>
      <select v-model="benchmarkType" required>
        <option>DVD-testing</option>
        <option>DVD-training</option>
        <option>NDBench-testing</option>
        <option>NDBench-training</option>
      </select>

      <label>Workload Metric</label>
      <select v-model="workloadMetric" required>
          <option>CPU</option>
          <option>NetworkIn</option>
          <option>NetworkOut</option>
          <option>Memory</option>
      </select>

      <label>Batch Unit</label> <!-- how many rows you want per batch -->
      <input type="number" min="1" v-model="batchUnit" required/>

      <label>Batch ID</label> <!-- start at row:  batch id * batch unit-->
      <input type="number" min="0" v-model="batchId" required/>

      <label>Batch Size</label> <!-- how many batches you want -->
      <input type="number" min="1" v-model="batchSize" required/>

      <input type="submit" value="Submit"/>
    </form>
    
    <div v-if="deserializedDataProto">
      <h3>Proto</h3>
      <p>
        RFW ID: {{deserializedDataProto.rfwid}} <br>
        Last Batch ID: {{deserializedDataProto.lastID}} <br>
      </p>
      <div :key="index" v-for="(batch, index) in deserializedDataProto.batchLists">
        <div style="margin-top: 1rem">
          <strong>Batch: {{deserializedDataProto.lastID - deserializedDataProto.batchLists.length + 1 + index}}</strong>
        </div>
        <hr>
        <div :key="index" v-for="(metric, index) in batch.array[0]">
          {{metric[0]}}: {{metric[1]}}
        </div>
      </div>
      <br>
    </div>

    <div v-if="deserializedDataJson">
      <h3>Json</h3>
      <p>
        RFW ID: {{deserializedDataJson.rfwid}} <br>
        Last Batch ID: {{deserializedDataJson.lastBatchId}} <br>
      </p>
      <div :key="index" v-for="(batch, index) in deserializedDataJson.samplesRequested">
        <div style="margin-top: 1rem">
          <strong>Batch: {{deserializedDataJson.lastBatchId - deserializedDataJson.samplesRequested.length + index}}</strong>
        </div>
        <hr>
        <div :key="index" v-for="(metric, index) in batch">
          {{metric.chosenMetric}}
        </div>
      </div>
      <br>
      
    </div>
    <div v-if="deserializedDataJson || deserializedDataProto">
      <button class="button" @click="cleardeserializedData">Clear</button>
    </div>
  </div>
</template>

<script>
const Schema = require("../response_pb");
import Axios from 'axios'
let output = new Schema.Batches();
export default {
  name: 'RFWForm',
  props: {
    
  },
  data()  {
    return {
      serializationType: "JSON",
      rfwid: 123456789,
      benchmarkType: "DVD-testing",
      workloadMetric: "CPU",
      batchUnit: 7,
      batchId: 1,
      batchSize: 2,

      deserializedDataProto: null,
      deserializedDataJson: null

    }
  },
  methods: {
    async processForm() {
      const url = `http://localhost:8082/api/${this.serializationType.toLowerCase()}/rfw/${this.rfwid}/benchmark/${this.benchmarkType}/metric/${this.workloadMetric}/batch/${this.batchUnit}/${this.batchId}/${this.batchSize}`;
      
      console.log("Sending Request For Workload...");
      console.log(url);

      if (this.serializationType.toLowerCase() === "json") {
        await fetch(url, {
          method: 'GET',
          headers: {
              'Content-Type': 'application/json',
          }})
          .then(response => response.json())
          .then(data => {

              this.deserializedDataJson = data;
              console.log('deserializedDataJson', this.deserializedDataJson);
              console.log('deserializedDataJson stringified', JSON.stringify(this.deserializedDataJson, null, 2));
              
          })
          .catch((error) => {
              console.error('Error:', error);
          });
      }
      else if (this.serializationType.toLowerCase() === "proto") {
        Axios.get(url, {
          responseType: "arraybuffer",  
        //, {
          //method: 'GET'
          headers: {
              'Content-Type': 'application/x-protobuf'}
              } 
          )
          .then(response => {
            let arrayData = new Uint8Array(response.data)
            console.log('u8intArray data:', arrayData)
            output = Schema.Batches.deserializeBinary(arrayData); //new Uint8Array(response))
              console.log('response', output.getRfwid());
              console.log('response', output.getLastid());
              console.log('response', output.getBatchesList());  
              
              //const batchList = output.getBatchesList(); //array of our metrics

              this.deserializedDataProto = {
                rfwid: output.getRfwid(), 
                lastID: output.getLastid(), 
                batchLists: output.getBatchesList()
              }

              console.log('Deserialization of json data...');
              //deserialization of data received from the backend
              
          })
          .catch((error) => {
              console.error('Error:', error);
          });
      }
    },
    cleardeserializedData() {
      this.deserializedDataProto = null;
      this.deserializedDataJson = null;
    }
  }
}
</script>

<style scoped>
  .container {
    border: 5px;
    padding: 10px;
  }

  input, select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }

  input[type=submit]:hover, button {
    background-color: #969696;
    cursor: pointer;
  }
</style>
