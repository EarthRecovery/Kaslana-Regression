import Vue from "vue";
import Router from "vue-router";

import LinearRegression from "@/components/LinearRegression.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/LinearRegression",
      name: "LinearRegression",
      component: LinearRegression
    }
  ]
});
