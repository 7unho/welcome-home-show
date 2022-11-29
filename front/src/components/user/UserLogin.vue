<template>
  <div id="login_form" class="wrap" style="margin-top: 130px">
    <div>
      <h1>로그인</h1>
      <b-form id="login_form">
        <b-form-group>
          <b-form-input
            id="userid"
            v-model="user.userid"
            required
            placeholder="아이디 입력...."
            @keyup.enter="confirm"
          >
          </b-form-input>
        </b-form-group>
        <b-form-group>
          <b-form-input
            type="password"
            id="userpwd"
            v-model="user.userpwd"
            required
            placeholder="비밀번호 입력...."
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form-group>
        <!-- <p class="forgetpw"><a href="changepw.php">찾자. 비밀번호</a></p> -->
        <p>
          <!-- <input type="submit" value="Login" class="form_btn"> -->
          <b-button class="form_btn" @click="confirm">로그인</b-button>
        </p>
      </b-form>
      <p class="regist_btn">
        회원이 아니신가요? &nbsp;<a href="/user/join">회원가입</a>
      </p>
    </div>
  </div>
  <!-- <b-container class="bv-example-row mt-3">
    
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="confirm">로그인</b-button>
            <b-button type="button" variant="success" class="m-1" @click="movePage">회원가입</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container> -->
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      } else {
        alert("아이디 또는 비밀번호를 확인해주세요 !");
        this.user = {
          userid: "",
          userpwd: "",
        };
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>

<style>
.wrap {
  width: 500px;
  height: 650px;
  margin: 0 auto;
  background-color: #fff;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 2px 2px 2px 2px #999;
}

.wrap > div {
  width: 100%;
  margin: 0 100px;
}

h1 {
  margin-bottom: 20px;
}

.wrap .form input:not(input[type="submit"], input[type="checkbox"]) {
  border: 1px solid #d9d9d9;
  width: 400px;
  height: 50px;
  margin: 5px;
  padding-left: 10px;
  border-radius: 5px;
  box-sizing: border-box;
  font-size: 18px;
}

#login_form .forgetpw {
  text-align: left;
  font-size: 14px;
  margin: 0 0 10px 10px;
  cursor: pointer;
}

.wrap .form_btn {
  width: 400px;
  height: 50px;
  margin: 10px;
  border-radius: 5px;
  border: 0;
  background: linear-gradient(to left, #38d85d, #b6db6b);
  color: #fff;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
}

/* #login_form .forgetpw a,
#login_form .regist_btn a {
    color: red;
}

#login-form {
    position: relative;
    z-index: 2;
}

login-form h1 {
    font-size: 32px;
    color: #fff;
    text-align: center;
} */

.int-area {
  width: 400px;
}

.int-area input {
  width: 100%;
  padding: 20px 10px 10px;
  background-color: transparent;
  border: none;
  border-bottom: 1px solid #999;
  font-size: 18px;
  color: #999;
  outline: none;
}

#login_form .forgetpw a,
#login_wrap .regist_btn a {
  color: red;
}
</style>
