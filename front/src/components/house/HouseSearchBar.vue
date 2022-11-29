<template>
  <div>
    <div id="filterBox">
      <b-card bg-variant="light " border-variant="primary">
        <!-- 맨 위 동/아파트 검색 조건 -->

        <b-row class="mb-2 text-center">
          <b-col class="sm-3">
            <b-form-radio size="sm" v-model="queryType" value="주소분류"
              >주소분류로 검색</b-form-radio
            >
          </b-col>
          <b-col class="sm-3">
            <b-form-radio size="sm" v-model="queryType" value="이름"
              >이름으로 검색</b-form-radio
            >
          </b-col>
        </b-row>
        <!-- 주소분류로 검색 -->
        <div v-if="queryType == '주소분류'">
          <b-row class="mb-4 text-center">
            <b-col class="sm-3">
              <b-form-select
                v-model="sidoCode"
                size="sm"
                :options="sidos"
                @change="gugunList"
              ></b-form-select>
            </b-col>
            <b-col class="sm-3">
              <b-form-select
                v-model="gugunCode"
                size="sm"
                :options="guguns"
                @change="dongList"
              ></b-form-select>
            </b-col>
            <b-col class="sm-3">
              <b-form-select
                v-model="dongCode"
                size="sm"
                :options="dongs"
                @change="searchAptByDongCode"
              ></b-form-select>
            </b-col>
          </b-row>
        </div>
        <!-- 이름으로 검색 -->
        <div v-else>
          <b-form-input
            class="my-2"
            size="sm"
            v-model="searchKeyword"
            @keyup="executeQuery"
            placeholder="두 글자 이상 검색어를 입력하세요..."
          ></b-form-input>
          <!-- 검색 결과 -->
          <b-container id="search_result" class="p-1">
            <b-list-group>
              <div v-if="searchList.length == 0">
                <b-list-group-item>
                  <b-row class="px-2" style="font-size: 12px" align-h="center">
                    검색 결과가 없습니다 :(
                  </b-row>
                </b-list-group-item>
              </div>
              <div v-else>
                <!-- 동 검색 결과 -->
                <b-list-group-item
                  v-for="(dongitem, dongindex) in searchList.dongResult"
                  :key="dongindex"
                  button
                  @click="searchResultClick_dong(dongitem.dongCode)"
                >
                  <b-row class="px-1 dongResult"> {{ dongitem.address }}</b-row>
                </b-list-group-item>

                <!-- 아파트 검색 결과 -->
                <b-list-group-item
                  class="aptResult"
                  v-for="(aptitem, aptindex) in searchList.aptResult"
                  :key="`o-${aptindex}`"
                  button
                  @click="searchResultClick_apt(aptitem)"
                >
                  <b-row class="px-1 aptResult"> {{ aptitem.aptName }}</b-row>
                  <b-row class="px-1 aptResultAddr">
                    {{ aptitem.address }}</b-row
                  >
                </b-list-group-item>
              </div>
            </b-list-group>
          </b-container>
        </div>
        <hr />
        <div>
          <b-tabs id="filter" pills size="sm">
            <hr />

            <b-tab title="가격" active>
              <!-- https://nightcatsama.github.io/vue-slider-component/#/  슬라이더 관련 -->
              <vue-slider
                v-model="filter.priceRange"
                :min="0"
                :max="70"
                @change="applyFilter"
                :lazy="true"
              >
              </vue-slider>

              {{ filter.priceRange[0] }} 억 ~ {{ filter.priceRange[1] }} 억
            </b-tab>
            <b-tab title="날짜"
              ><label for="minDate">시작 날짜</label>
              <b-form-datepicker
                id="minDate"
                size="sm"
                v-model="filter.minDate"
                class="mb-2"
                @input="applyFilter"
              >
              </b-form-datepicker>

              <label for="maxDate">끝 날짜</label>
              <b-form-datepicker
                id="maxDate"
                size="sm"
                v-model="filter.maxDate"
                class="mb-2"
                @input="applyFilter"
              >
              </b-form-datepicker>
            </b-tab>
          </b-tabs>
          <hr />
        </div>
        <div>
          <b-button
            size="sm"
            class="mx-2"
            variant="secondary light"
            @click="resetAndFilter"
          >
            필터 초기화</b-button
          >
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      filter: {
        priceRange: [],
        minDate: "",
        maxDate: "",
      },
      queryType: "주소분류",
      searchKeyword: "",
      searchByWhat: "",

      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      aptCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "searchList",
    ]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.resetFilter();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_APT_LIST();
    this.CLEAR_DONG();
    this.getSido();
    this.CLEAR_KEYWORD_SEARCH_LIST();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "keywordSearch",
      "getSingleHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
      "CLEAR_DONG",
      "CLEAR_KEYWORD_SEARCH_LIST",
    ]),
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

    searchAptByDongCode() {
      this.searchByWhat = "dong";
      const param = {
        dong: this.dongCode,
        minPriceRange: Number(this.filter.priceRange[0]) * 10000,
        maxPriceRange: Number(this.filter.priceRange[1]) * 10000,
        minDateRange: this.filter.minDate,
        maxDateRange: this.filter.maxDate,
      };
      if (this.dongCode) this.getHouseList(param);
    },

    searchAptByAptCode() {
      this.searchByWhat = "apt";
      const param = {
        dong: this.dongCode,
        aptCode: this.aptCode,
        minPriceRange: Number(this.filter.priceRange[0]) * 10000,
        maxPriceRange: Number(this.filter.priceRange[1]) * 10000,
        minDateRange: this.filter.minDate,
        maxDateRange: this.filter.maxDate,
      };
      if (this.dongCode) this.getSingleHouseList(param);
    },

    applyFilter() {
      if (!this.dongCode && !this.aptCode) {
        alert("동이나 아파트를 먼저 선택해주세요.");
        this.resetFilter();
        return;
      }

      if (this.searchByWhat == "apt") {
        this.searchAptByAptCode();
      } else {
        this.searchAptByDongCode();
      }
    },

    resetFilter() {
      this.filter.priceRange = [0, 70];
      this.filter.minDate = "2015-01-01";
      this.filter.maxDate = "2022-12-31";
    },

    resetAndFilter() {
      this.resetFilter();
      this.applyFilter();
    },

    executeQuery() {
      console.log(this.searchKeyword);
      if (this.searchKeyword.length >= 2) {
        this.keywordSearch(this.searchKeyword);
        console.log(this.searchContent);
      }
    },

    searchResultClick_dong(item) {
      this.dongCode = item;
      this.searchAptByDongCode();
    },
    searchResultClick_apt(item) {
      this.dongCode = item.dongCode;
      this.aptCode = item.aptCode;
      this.searchAptByAptCode();
    },
  },
};
</script>

<style scoped>
#filterBox {
  position: absolute;
  top: 13%;
  /* bottom : 10px; */
  left: 1%;
  width: 23rem;
  /* height: 400px; */
  padding: 10px;
  z-index: 100;
  opacity: 95%;
  /* overflow-y: auto; */
}

#search_result {
  max-height: 300px;
  overflow: auto;
}

.dongResult {
  font-size: 13px;
  font-weight: bold;
  color: rgb(46, 100, 67);
}

.aptResult {
  font-size: 13px;
  font-weight: bold;
  color: rgb(67, 35, 99);
}

.aptResultAddr {
  font-size: 11px;
  color: gray;
}
</style>
