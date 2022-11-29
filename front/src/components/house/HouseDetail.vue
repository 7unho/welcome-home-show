<template>
  <div id="houseDetail">
    <div v-if="house" class="bv-example-row">
      <b-card>
        <b-row>
          <b-col cols="8"
            ><h4>{{ house.aptName }}</h4></b-col
          >
          <b-col>
            <div v-if="this.userInfo">
              <b-button
                v-if="!isBookmarked"
                pill
                variant="info"
                @click="bookmark(house.aptCode)"
              >
                북마크하기</b-button
              >
              <b-button
                v-else
                pill
                variant="warning"
                @click="removeBookmark(house.aptCode)"
              >
                북마크 취소</b-button
              >
            </div></b-col
          >
        </b-row>

        <hr />
        <b-alert show variant="info"
          >건축년도 : {{ house.buildYear }} 년
        </b-alert>
        <b-alert show variant="primary"
          >거래 평수 : {{ minarea | area }} 평 ~ {{ maxarea | area }} 평
        </b-alert>
        <hr />
        <!-- <b-tabs id="houseinfo" pills size="sm">
          <b-tab title="거래정보"> -->
        <div class="py-3">
          총 {{ house.deals.length }} 개의 결과 중 필터링 된
          {{ house.filteredDeals.length }} 개의 결과들입니다.
        </div>
        <b-list-group id="houselist">
          <div v-for="(deal, index) in house.filteredDeals" :key="index">
            <b-list-group-item class="mx-3">
              <h3>
                <b-badge variant="light"
                  >거래가 :
                  {{
                    (parseInt(deal.dealAmount.replace(",", "")) * 10000)
                      | price
                  }}원</b-badge
                >
              </h3>
              <hr />
              <h4>
                <b-badge variant="info" pill size="lg">
                  거래날짜 : {{ deal.dealYear }}년 {{ deal.dealMonth }}월
                  {{ deal.dealDay }}일<br />
                </b-badge>
              </h4>
              <h5>
                <b-badge variant="primary" pill size="lg"
                  >{{ deal.area | area }} 평</b-badge
                >
                &nbsp;
                <b-badge variant="success" pill>{{ deal.floor }} 층</b-badge>
              </h5> </b-list-group-item
            ><br />
          </div>
        </b-list-group>
        <!-- </b-tab> -->
        <!-- <b-tab title="거래정보그래프"> -->
        <!-- <house-detail-chart></house-detail-chart>
          </b-tab>
        </b-tabs> -->
      </b-card>
    </div>
    <div v-else class="bv-example-row mt-3">
      <b-card>
        <b-row>
          <b-col>
            <b-alert show>선택된 주택이 없습니다.</b-alert>
          </b-col>
        </b-row>
      </b-card>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
// import HouseDetailChart from "@/components/house/HouseDetailChart.vue";

const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseDetail",
  data() {
    return {
      isBookmarked: false,
      minarea: 999,
      maxarea: 0,
    };
  },
  // components: { HouseDetailChart },
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["addBookmark", "deleteBookmark"]),
    bookmark(aptCode) {
      for (var i = 0; i < this.userInfo.aptBookmark.length; i++) {
        if (aptCode === this.userInfo.aptBookmark[i].aptCode) {
          alert("이미 북마크한 아파트입니다!");
          return;
        }
      }
      var param = {
        aptCode: aptCode,
        userId: this.userInfo.userid,
      };
      this.isBookmarked = true;
      this.addBookmark(param);
    },

    removeBookmark(aptCode) {
      var really = confirm("북마크를 취소하시겠습니까?");
      if (!really) return;
      var param = {
        aptCode: aptCode,
        userId: this.userInfo.userid,
      };
      this.isBookmarked = false;
      this.deleteBookmark(param);
    },
  },

  watch: {
    house(val) {
      if (!val) return;

      for (var j = 0; j < val.deals.length; j++) {
        this.minarea = Math.min(val.deals[j].area, this.minarea);
        this.maxarea = Math.max(val.deals[j].area, this.maxarea);
      }
      console.log(this.minarea, this.maxarea);

      this.isBookmarked = false;
      if (!this.userInfo) return;
      for (var i = 0; i < this.userInfo.aptBookmark.length; i++) {
        if (this.house.aptCode === this.userInfo.aptBookmark[i].aptCode) {
          console.log("발견");
          this.isBookmarked = true;
          return;
        }
      }
    },
  },

  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    area(value) {
      return (value / 3.3058).toFixed(1);
    },
  },
};
</script>

<style scoped>
#houseDetail {
  position: absolute;
  top: 11%;
  /* bottom : 10px; */
  right: 1%;
  width: 30rem;

  padding: 10px;
  z-index: 100;
  overflow-y: auto;

  /* height: 100vh; */
}

#houselist {
  max-height: 300px;
  overflow-y: scroll;
}
</style>
