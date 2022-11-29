import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "@/api/lib/fontAwesomeIcon.js"; // fontAwesomeIcon.js 불러옴
import "@/api/lib/vueBootstrap.js";

import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";

Vue.config.productionTip = false;
Vue.component("VueSlider", VueSlider); // 슬라이더 추가
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
