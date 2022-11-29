<template>
  <b-jumbotron class="usermypagebookmark">
    <h1>Bookmark List</h1>
    <hr class="my-1" />
    <div id="bookmarks">
      <b-input-group
        v-for="(apt, index) in userInfo.aptBookmark"
        :key="index"
        class="mt-3"
      >
        <b-form-input v-model="apt.aptName" readonly></b-form-input>

        <router-link :to="{ name: 'house', query: { aptCode: apt.aptCode } }">
          <b-button class="mx-1" variant="outline-success">지도</b-button>
        </router-link>
        <b-button
          class="mx-1"
          variant="outline-danger"
          @click="removeBookmark(apt.aptCode)"
          >삭제</b-button
        >
      </b-input-group>
    </div>
  </b-jumbotron>
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
export default {
  name: "UserMyPageBookMark",
  data() {
    return {
      aptInfo: null,
    };
  },
  created() {
    this.aptInfo = this.apt;
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["deleteBookmark"]),
    removeBookmark(aptCode) {
      var really = confirm("삭제하시겠습니까?");
      if (!really) return;
      var param = {
        aptCode: aptCode,
        userId: this.userInfo.userid,
      };
      this.deleteBookmark(param);
    },
  },
  watch: {
    userInfo: function (val) {
      this.userInfo = val;
    },
  },
};
</script>

<style scoped>
.usermypagebookmark {
  height: 700px;
  box-shadow: rgba(0, 0, 0, 0.4) 0px 1px 3px, rgba(0, 0, 0, 0.8) 0px 1px 2px;
}

.bookmark {
  display: contents;
  margin: 10px;
}

#bookmarks {
  max-height: 500px;
  overflow-y: scroll;
}
/* 
.bookmark>button {
    width: 100px;
    margin-left: 15px;
    font-size: 0.5rem;
} */
</style>
