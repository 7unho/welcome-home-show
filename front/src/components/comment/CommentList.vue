<template>
  <div class="wrapper">
    <b-container class="bv-example-row mt-3">
      <comment-write></comment-write>
      <div>
        <hr />
        <b-card bg-variant="light">
          <b-row>
            <b-col cols="3">작성자</b-col>
            <b-col cols="3"> 내용 </b-col>
            <b-col cols="3"> 작성시간 </b-col></b-row
          ></b-card
        >
      </div>
      <div v-for="(comment, index) in comments" :key="index">
        <div>
          <b-card class="my-1">
            <b-row>
              <b-col cols="3">{{ comment.writer }}</b-col>
              <b-col cols="3">
                {{ comment.content }}
              </b-col>
              <b-col cols="3">
                {{ comment.regtime }}
              </b-col>
              <b-col cols="3">
                <b-button
                  v-if="userInfo.username == comment.writer"
                  class="form_btn mr-1"
                  @click="selectComment(comment.no)"
                  >댓글 수정</b-button
                >
                <b-button
                  v-if="userInfo.username == comment.writer"
                  class="form_btn mr-2"
                  @click="delComment(comment.no)"
                  >댓글 삭제</b-button
                >
              </b-col>
            </b-row>
          </b-card>
        </div>
        <!-- 댓글수정시작 -->
        <div v-show="selectedComment == comment.no">
          <div class="card-body">
            <b-form-group>
              <b-form-textarea
                id="textarea-auto-height"
                rows="3"
                max-rows="8"
                v-model="newContent"
              >
              </b-form-textarea>
            </b-form-group>
            <b-form-group class="wrapper_btn" style="text-align: right">
              <b-button class="form_btn" @click="editComment(comment.no)"
                >댓글 수정</b-button
              >
            </b-form-group>
          </div>
        </div>
        <!-- 댓글수정끝 -->
      </div>
    </b-container>
  </div>
</template>

<script>
import { updateComment, deleteComment } from "@/api/comment";
import CommentWrite from "@/components/comment/CommentWrite";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "CommentList",
  data() {
    return {
      selectedComment: null,
      newContent: "",
    };
  },
  components: {
    CommentWrite,
  },
  props: {
    comments: Object,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    selectComment(index) {
      this.selectedComment = index;
      this.newContent = "";
    },

    editComment(commentNo) {
      var really = confirm("댓글을 수정하시겠습니까?");
      if (!really) return;

      var params = {
        no: commentNo,
        content: this.newContent,
      };
      updateComment(params);
      this.newContent = "";
      alert("댓글을 수정하였습니다.");
      this.$router.go();
    },

    delComment(commentNo) {
      var really = confirm("댓글을 삭제하시겠습니까?");
      if (!really) return;

      deleteComment(commentNo);
      alert("댓글을 삭제하였습니다.");
      this.$router.go();
    },
  },
};
</script>

<style>
.wrapper {
  width: 100%;
  text-align: left;
}

.wrapper hr {
  width: 100%;
}

.wrapper .delete-btn {
  text-align: right;
}
</style>
