import {
  sidoList,
  gugunList,
  houseList,
  dongList,
  keywordSearchList,
  singleHouseList,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "동" }],
    dong: null,
    houses: [],
    house: null,
    searchList: [],
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "동" }];
    },
    CLEAR_DONG(state) {
      state.dong = null;
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_KEYWORD_SEARCH_LIST(state) {
      state.searchList = [];
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_DONG(state, dong) {
      state.dong = dong;
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_DETAIL_HOUSE_BYNUMBER(state, num) {
      state.house = state.houses[num];
    },
    SET_KEYWORD_SEARCH_LIST(state, list) {
      state.searchList = list;
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, param) => {
      commit("SET_DONG", param.dong);
      commit("CLEAR_APT_LIST");
      console.log(param.minDateRange, param.maxDateRange);
      const params = {
        dong: param.dong,
        minPriceRange: param.minPriceRange,
        maxPriceRange: param.maxPriceRange,
        minDateRange: param.minDateRange,
        maxDateRange: param.maxDateRange,
      };
      houseList(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getSingleHouseList: ({ commit }, param) => {
      commit("SET_DONG", param.dong);
      commit("CLEAR_APT_LIST");
      const params = {
        aptCode: param.aptCode,
        minPriceRange: param.minPriceRange,
        maxPriceRange: param.maxPriceRange,
        minDateRange: param.minDateRange,
        maxDateRange: param.maxDateRange,
      };
      singleHouseList(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSE_LIST", data);
          // commit("SET_DETAIL_HOUSE", data[0]);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    detailHouse: ({ commit }, house) => {
      commit("SET_DETAIL_HOUSE", house);
    },
    detailHouseByNumber: ({ commit }, num) => {
      commit("SET_DETAIL_HOUSE_BYNUMBER", num);
    },

    keywordSearch: ({ commit }, param) => {
      const params = {
        keyword: param,
      };
      keywordSearchList(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_KEYWORD_SEARCH_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
