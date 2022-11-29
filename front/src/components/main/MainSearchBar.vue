<template>
    <b-row class="mb-4 text-center" style="width: 60%;
      margin: auto;">
      <b-col class="sm-3" style="margin-top: 340px;">
        <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
      </b-col>
      <b-col class="sm-3" style="margin-top: 340px;">
        <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
      </b-col>
      <b-col class="sm-3" style="margin-top: 340px;">
        <b-form-select v-model="dongCode" :options="dongs" @change="searchApt"></b-form-select>
      </b-col>
      <b-col class="sm-3" style="margin-top: 340px;">
        <b-button>test</b-button>
      </b-col>
    </b-row>
  </template>
  
  <script>
  import { mapState, mapActions, mapMutations } from "vuex";
  
  /*
    namespaced: true를 사용했기 때문에 선언해줍니다.
    index.js 에서 modules 객체의 '키' 이름입니다.
  
    modules: {
      키: 값
      memberStore: memberStore,
      houseStore: houseStore
    }  
  */
  const houseStore = "houseStore";
  
  export default {
    name: "MainSearchBar",
    data() {
      return {
        sidoCode: null,
        gugunCode: null,
        dongCode: null,
      };
    },
    computed: {
      ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
      // sidos() {
      //   return this.$store.state.sidos;
      // },
    },
    created() {
      // this.$store.dispatch("getSido");
      // this.sidoList();
      this.CLEAR_SIDO_LIST();
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_DONG_LIST();
      this.CLEAR_APT_LIST();
      this.getSido();
    },
    methods: {
      ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
      ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "CLEAR_APT_LIST"]),
      // sidoList() {
      //   this.getSido();
      // },
      gugunList() {
        this.CLEAR_GUGUN_LIST();
        this.gugunCode = null;
        if (this.sidoCode) this.getGugun(this.sidoCode);
      },
      dongList() {
        this.CLEAR_DONG_LIST();
        this.dongCode = null;
        if (this.gugunCode) this.getDong(this.gugunCode);
      },
      searchApt() {
        if (this.dongCode) this.getHouseList(this.dongCode);
      },
    },
  };
  </script>
  
  <style>
  </style>
  