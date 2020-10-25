<template>
  <div class="container">
    <h1>Request For Workload (RFW)</h1>
    <form @submit.prevent="processForm">
      <label>RFW ID</label>
      <input type="text" v-model="rfwid" required/>

      <label>Benchmark Type</label>
      <select v-model="benchmarkType" required>
        <option>DVD</option>
        <option>NDBench</option>
      </select>

      <label>Workload Metric</label>
      <select v-model="workloadMetric" required>
          <option>CPU</option>
          <option>NetworkIn</option>
          <option>NetworkOut</option>
          <option>Memory</option>
      </select>

      <label>Batch Unit</label>
      <input type="number" v-model="batchUnit" required/>

      <label>Batch ID</label>
      <input type="text" v-model="batchId" required/>

      <label>Batch Size</label>
      <input type="number" v-model="batchSize" required/>

      <input type="submit" value="Submit"/>

    </form>
  </div>
</template>

<script>
export default {
  name: 'RFWForm',
  props: {
    
  },
  data()  {
    return {
      rfwid: "",
      benchmarkType: "DVD",
      workloadMetric: "CPU",
      batchUnit: 0,
      batchId: "",
      batchSize: 0

    }
  },
  methods: {
    async processForm() {
      const url = `http://localhost:8082/api/json/rfw/${this.rfwid}/benchmark/${this.benchmarkType}/metric/${this.workloadMetric}/batch/${this.batchUnit}/${this.batchId}/${this.batchSize}`;
      
      console.log("Sending Request For Workload...");
      console.log(url);

      await fetch(url, {
          method: 'GET',
          headers: {
              'Content-Type': 'application/json',
          }
        }
      )
      .then(response => response.json())
      .then(data => {
          console.log('Success:', data);
      })
      .catch((error) => {
          console.error('Error:', error);
      });
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

  input[type=submit]:hover {
    background-color: #969696;
    cursor: pointer;
  }
</style>
