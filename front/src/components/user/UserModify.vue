<template>
  <b-modal id="modify-form" ref="modify-form" hide-footer>
    <template #modal-title> 회원정보수정 </template>
    <div class="d-block text-center">
      <b-form id="join_form">
        <b-form-group
          label-cols="4"
          label-cols-lg="2"
          label-size="sm"
          label="아이디"
          label-for="userid"
        >
          <b-form-input
            type="text"
            id="userid"
            v-model="changeUser.userid"
            required
            readonly
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label-cols="4"
          label-cols-lg="2"
          label-size="sm"
          label="이름"
          label-for="username"
        >
          <b-form-input
            type="text"
            id="username"
            v-model="changeUser.username"
            required
            placeholder="이름 입력"
            ref="username"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label-cols="4"
          label-cols-lg="2"
          label-size="sm"
          label="이메일"
          label-for="email"
        >
          <b-form-input
            type="email"
            id="email"
            v-model="changeUser.email"
            required
            placeholder="example@ssafy.com"
            ref="email"
          ></b-form-input>
        </b-form-group>
      </b-form>
    </div>

    <b-row>
      <b-col>
        <b-button class="mx-2" variant="outline-success" @click="updateCheck()"
          >수정</b-button
        ><b-button
          class="mx-2"
          variant="outline-danger"
          @click="updateFormClose"
          >취소</b-button
        ></b-col
      >
    </b-row>
  </b-modal>
</template>

<script>
import { updateUser } from "@/api/member";
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
  name: "UserModify",
  data() {
    return {
      changeUser: {
        userid: "",
        username: "",
        email: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(memberStore, {
      user: "checkUserInfo",
    }),
  },
  created() {
    this.changeUser.userid = this.userInfo.userid;
    this.changeUser.username = this.userInfo.username;
    this.changeUser.email = this.userInfo.email;
  },
  methods: {
    updateCheck() {
      let check = confirm("정말 수정하시겠습니까?");
      if (!check) return;

      this.modifyUser();
    },
    modifyUser() {
      console.log(this.changeUser);
      updateUser(
        this.changeUser,
        ({ data }) => {
          console.log(data);
          let msg = "회원 수정에 실패하였습니다!";
          if (data === "success") {
            msg = "회원 수정이 완료되었습니다.";
          }
          alert(msg);
          location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateFormClose() {
      this.changeUser.userid = this.userInfo.userid;
      this.changeUser.username = this.userInfo.username;
      this.changeUser.email = this.userInfo.email;
      this.$refs["modify-form"].hide();
    },
  },
};
</script>

<style></style>
