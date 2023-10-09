<template>
  <div>
    Point X: <input type="text" v-model="point.x" /> <br /><br />
    Point Y:<input type="text" v-model="point.y" /> <br /><br />
    <button v-on:click="add">Add</button>
    <button v-on:click="generate">Generate</button>
    <button v-on:click="clear">Clear</button> <br /><br />
    slope: <input type="text" :value="line.slope" /> yintercept:
    <input type="text" :value="line.yintercept" />
  </div>
</template>

<script>
export default {
  name: "LinearRegression",
  data() {
    return {
      point: {
        x: 0,
        y: 0
      },
      line: {
        slope: 0,
        yintercept: 0
      }
    };
  },
  methods: {
    add() {
      this.$axios
        .post("/model/linearModel/add", {
          x: this.point.x,
          y: this.point.y
        })
        .then(successresponse => {})
        .catch(failResponse => {
          alert("error!");
        });
    },
    generate() {
      this.$axios
        .post("/model/linearModel/generate", {})
        .then(successresponse => {
          this.line.slope = successresponse.data.slope;
          this.line.yintercept = successresponse.data.yintercept;
        })
        .catch(failResponse => {
          console.log(failResponse);
          alert("error!");
        });
    },
    clear() {
      this.$axios
        .post("/model/linearModel/clear", {})
        .then(successresponse => {
          this.point.x = 0;
          this.point.y = 0;
          this.line.slope = 0;
          this.line.yintercept = 0;
        })
        .catch(failResponse => {
          alert("error!");
        });
    }
  }
};
</script>
