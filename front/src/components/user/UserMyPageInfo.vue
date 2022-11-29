<template>
  <b-jumbotron class="usermypageinfo">
    <h1>User Info</h1>
    <hr class="my-1" />
    <br />

    <b-img
      src="https://picsum.photos/150/150/?image=41"
      fluid
      rounded="circle"
    ></b-img>

    <br />

    <b-list-group class="my-4">
      <b-list-group-item button>
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">아이디</b-col>
          <b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
          <b-col cols="2"></b-col> </b-row
      ></b-list-group-item>
      <b-list-group-item button>
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">이름</b-col>
          <b-col cols="4" align-self="start">{{ userInfo.username }}</b-col>
          <b-col cols="2"></b-col> </b-row
      ></b-list-group-item>
      <b-list-group-item button>
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">이메일</b-col>
          <b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
          <b-col cols="2"></b-col> </b-row
      ></b-list-group-item>
      <b-list-group-item button>
        <b-row>
          <b-col cols="2"></b-col>
          <b-col cols="2" align-self="end">가입일</b-col>
          <b-col cols="4" align-self="start">{{ userInfo.joindate }}</b-col>
          <b-col cols="2"></b-col> </b-row
      ></b-list-group-item>
    </b-list-group>

    <div>
      <b-button
        variant="outline-success"
        id="show-btn"
        @click="$bvModal.show('modify-form')"
        class="mr-1"
        >정보수정
      </b-button>
      <user-modify />
      <b-button variant="outline-danger" @click="checkMsg()">회원탈퇴</b-button>
    </div>
  </b-jumbotron>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { removeUser } from "@/api/member.js";
import UserModify from "@/components/user/UserModify";
const memberStore = "memberStore";
export default {
  name: "UserMyPageInfo",
  components: {
    UserModify,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    checkMsg() {
      if (!confirm("정말 탈퇴하시겠습니까?")) return;

      this.deleteUser();
    },
    deleteUser() {
      removeUser(
        this.userInfo.userid,
        ({ data }) => {
          let msg = "회원 탈퇴 중 오류가 발생했습니다 !";
          if (data === "success") {
            msg = "회원 탈퇴가 완료되었습니다.";
          }
          alert(msg);
          this.userLogout(this.userInfo.userid);
          sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
          sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
          if (this.$route.path != "/") this.$router.push({ name: "main" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>
.usermypageinfo {
  background-color: transparent;
  box-shadow: rgba(0, 0, 0, 0.4) 0px 1px 3px, rgba(0, 0, 0, 0.8) 0px 1px 2px;
  height: 700px;
}
</style>
