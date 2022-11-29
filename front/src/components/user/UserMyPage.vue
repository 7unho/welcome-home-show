<template>
  <div id="container">
    <b-row>
      <b-col class="mypagecontainer" cols="6">
        <user-my-page-info></user-my-page-info>
      </b-col>
      <b-col class="mypagecontainer" cols="6">
        <user-my-page-book-mark />
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
import UserMyPageInfo from "@/components/user/UserMyPageInfo";
import UserMyPageBookMark from "@/components/user/UserMyPageBookmark";

export default {
  name: "UserMyPage",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  components: {
    UserMyPageInfo,
    UserMyPageBookMark,
  },
};
</script>

<style scoped>
.mypagecontainer {
  padding: 1px;
}
</style>
