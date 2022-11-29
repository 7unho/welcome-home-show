import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
// module import
import boardStore from "@/store/modules/boardStore";
import houseStore from "@/store/modules/houseStore";
import memberStore from "@/store/modules/memberStore";
import todoStore from "@/store/modules/todoStore";
import noticeStore from "@/store/modules/noticeStore";
import newsStore from "@/store/modules/newsStore";
import commentStore from "@/store/modules/commentStore";
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    boardStore,
    houseStore,
    memberStore,
    todoStore,
    noticeStore,
    newsStore,
    commentStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
