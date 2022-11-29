<template>
  <!-- <div class="wrapper" v-if="userInfo">
        <b-form-group>
            <b-form-textarea id="textarea-auto-height" rows="3" max-rows="8" v-model="comment.content">
            </b-form-textarea>
        </b-form-group>
        
        <b-form-group class="wrapper_btn" style="text-align: right;">
            <b-button class="form_btn" @click="checkValue">댓글 작성</b-button>
        </b-form-group>
    </div> -->
  <div class="wrapper" v-if="userInfo">
    <div class="card-header bg-light">
      <i class="fa fa-comment fa"></i> {{ userInfo.username }}
    </div>
    <div class="card-body">
      <b-form-group>
        <b-form-textarea id="textarea-auto-height" rows="3" max-rows="8" v-model="comment.content">
        </b-form-textarea>
      </b-form-group>
      <b-form-group class="wrapper_btn" style="text-align: right">
        <b-button class="form_btn" @click="checkValue">댓글 작성</b-button>
      </b-form-group>
    </div>
  </div>
</template>

<script>
import { writeComment } from "@/api/comment";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "CommentWrite",
  data() {
    return {
      comment: {
        articleno: 0,
        content: "",
        writer: "",
      },
    };
  },
  created() {
    this.comment.writer = this.userInfo.username;
    this.comment.articleno = this.$route.params.articleno;
  },
  methods: {
    checkValue() {
      if (this.comment.content == null || this.comment.content == "") return;

      this.registComment();
    },
    registComment() {
      let param = {
        writer: this.comment.writer,
        content: this.comment.content,
        articleno: this.comment.articleno,
      };
      console.log(param);
      writeComment(
        param,
        ({ data }) => {
          console.log(data);
          location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style>
.wrapper {
  width: 100%;
}

.wrapper .form_btn {
  height: 30px;
  border-radius: 5px;
  border: 0;
  background: linear-gradient(to left, #38d85d, #b6db6b);
  color: #fff;
  font-weight: bold;
  font-size: 12px;
  cursor: pointer;
}
</style>
