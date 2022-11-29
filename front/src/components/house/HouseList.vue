<template>
  <div>
    <div id="map"></div>
    <DIV></DIV>
    <HouseSearchBar></HouseSearchBar>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseSearchBar,
  },
  data() {
    return {
      filter: {},
      map: null,
      latitude: 37.501273,
      longitude: 127.039675,
      infos: [],
      markers: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e8f8d8cb6582946aeb204beacc6ae59e&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(houseStore, ["detailHouseByNumber", "getHouseList"]),
    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.latitude, this.longitude), // 지도의 중심좌표
          level: 4, // 지도의 확대 레벨
        };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
    },
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    moveMapToMarker() {
      // 마커들의 중심값을 구한 후 그곳으로 지도의 중심점 변경
      if (this.infos.length != 0) {
        let lat = 0;
        let lng = 0;
        for (let i = 0; i < this.infos.length; i++) {
          lat += Number(this.infos[i].lat);
          lng += Number(this.infos[i].lng);
        }
        lat /= this.infos.length;
        lng /= this.infos.length;
        this.map.setCenter(new kakao.maps.LatLng(lat, lng));
      }
    },

    displayMarker() {
      console.log(this.infos + "제발..");
      this.removeMarker();
      for (let i = 0; i < this.infos.length; i++) {
        if (this.infos[i].filteredDeals.length == 0) continue;
        let marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(this.infos[i].lat, this.infos[i].lng),
          clickable: true,
          title: i + "",
        });
        marker.setMap(this.map);

        // let infowindow = new kakao.maps.InfoWindow({
        //   content: this.infos[i].aptName,
        //   //removable: true,
        // });

        kakao.maps.event.addListener(marker, "click", () => {
          var no = Number(marker.getTitle());
          this.map.panTo(
            new kakao.maps.LatLng(this.infos[no].lat, this.infos[no].lng)
          );
          this.detailHouseByNumber(no);
        });

        // kakao.maps.event.addListener(
        //   marker,
        //   "mouseover",
        //   this.makeOverListener(this.map, marker, infowindow)
        // );
        // kakao.maps.event.addListener(
        //   marker,
        //   "mouseout",
        //   this.makeOutListener(infowindow)
        // );
        this.markers.push(marker);
      }
      this.moveMapToMarker();
    },

    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },

    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
  },
  computed: {
    ...mapState(houseStore, ["houses", "dong"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  watch: {
    houses: function (val) {
      this.infos = val;
      this.displayMarker();
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 53rem;
}
</style>
