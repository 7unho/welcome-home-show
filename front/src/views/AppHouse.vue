<template>
  <div class="map">
    <house-list />
    <house-detail />
  </div>
</template>
<script>
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

export default {
  name: "AppHouse",
  components: {
    HouseList,
    HouseDetail,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}

.map {
  width: 100vw;
  height: 100vh;
}
</style>
