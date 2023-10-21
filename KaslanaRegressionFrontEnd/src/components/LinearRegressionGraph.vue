<template>
  <div>
    <canvas
      ref="canvas"
      :width="canvasWidth"
      :height="canvasHeight"
      @click="handleCanvasClick"
    ></canvas>

    <button v-on:click="generate">Generate</button>
    <button v-on:click="clear">Clear</button> <br /><br />
    slope: <input type="text" :value="line.slope" /> yintercept:
    <input type="text" :value="line.yintercept" /> <br /><br />
    x: <input type="text" :value="point.x" /> y:
    <input type="text" :value="point.y" /><br /><br />
    mse: <input type="text" :value="line.mse" />
  </div>
</template>

<script>
export default {
  name: "LinearRegression",
  data() {
    return {
      canvasWidth: 800, // 设置Canvas宽度
      canvasHeight: 400, // 设置Canvas高度
      canvas: null,
      ctx: null,
      points: [],
      isDrawing: false,
      startCoords: null,
      point: {
        x: 0,
        y: 0
      },
      line: {
        slope: 0,
        yintercept: 0,
        mse: 0
      }
    };
  },
  mounted() {
    this.canvas = this.$refs.canvas;
    this.ctx = this.canvas.getContext("2d");
    this.drawCoordinateSystem();
  },
  methods: {
    drawCoordinateSystem() {
      // 绘制坐标系
      const { canvasWidth, canvasHeight } = this;
      this.ctx.clearRect(0, 0, canvasWidth, canvasHeight);
      this.ctx.beginPath();
      this.ctx.moveTo(canvasWidth / 2, 0);
      this.ctx.lineTo(canvasWidth / 2, canvasHeight);
      this.ctx.moveTo(0, canvasHeight / 2);
      this.ctx.lineTo(canvasWidth, canvasHeight / 2);
      this.ctx.stroke();
    },
    handleCanvasClick(event) {
      // 处理Canvas点击事件
      const { offsetX, offsetY } = event;
      const x = offsetX - this.canvasWidth / 2;
      const y = this.canvasHeight / 2 - offsetY;

      // 保存点击的点的坐标
      this.points.push({ x, y });

      //发送addAPI
      this.point.x = x;
      this.point.y = y;
      this.add();

      // 绘制点击的点
      this.ctx.beginPath();
      this.ctx.arc(offsetX, offsetY, 3, 0, Math.PI * 2);
      this.ctx.fill();

      this.startCoords = { x, y };
    },
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
          this.line.mse = successresponse.data.mse;

          // 绘制线
          // 斜率和截距
          const slope = this.line.slope; // 例子中的斜率
          const intercept = this.line.yintercept; // 例子中的截距

          // 计算两个点的坐标
          const x1 = this.canvas.width * -0.5;
          const y1 = slope * x1 + intercept;
          const x2 = this.canvas.width * 0.5;
          const y2 = slope * x2 + intercept;

          // 绘制直线
          this.ctx.beginPath();
          this.ctx.moveTo(0, -y1 + 200);
          this.ctx.lineTo(800, -y2 + 200);
          this.ctx.stroke();
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
          this.line.mse = 0;
          this.points = [];
          this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
          this.canvas = this.$refs.canvas;
          this.ctx = this.canvas.getContext("2d");
          this.drawCoordinateSystem();
        })
        .catch(failResponse => {
          alert("error!");
        });
    }
  }
};
</script>
