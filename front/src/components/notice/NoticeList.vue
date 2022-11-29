<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show variant="">
          <h3>공지사항</h3>
        </b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <p v-if="userInfo">
        <b-button variant="outline-primary" v-if="userInfo.category == '1'" @click="moveWrite()">글쓰기</b-button>
        </p>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
          :current-page="currentPage"
          :per-page="perPage"
        >
          <template #cell(subject)="data">
            <router-link
              :to="{
                name: 'noticeview',
                params: { noticeno: data.item.noticeno },
              }"
            >
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
    ></b-pagination>
  </b-container>
</template>

<script>
import { listArticle } from "@/api/notice";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "noticeno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
      perPage: 10,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    rows() {
      return this.articles.length;
    },
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      ({ data }) => {
        this.articles = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticewrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "noticeview",
        params: { noticeno: article.noticeno },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}

.tdSubject {
  width: 300px;
  text-align: left;
}

.pagination {
  display: inline-flex;
}
</style>
