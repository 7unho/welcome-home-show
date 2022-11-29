<template>
  <b-container>
    <b-row>
      <b-col>
        <b-carousel id="carousel-1" :interval="4000" indicators background="#ababab" img-width="1024" img-height="480"
          style="text-shadow: 1px 1px 2px #333">
          <!-- Text slides with image -->
          <b-carousel-slide caption="안녕하세요!" text="매물 조회 사이트 어서홈쇼입니다."
            img-src="https://images.pexels.com/photos/681331/pexels-photo-681331.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1">
          </b-carousel-slide>

          <!-- Slides with custom text -->
          <b-carousel-slide caption="안녕하세요!" text="매물 조회 사이트 어서홈쇼입니다."
            img-src="https://images.pexels.com/photos/439227/pexels-photo-439227.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1">
          </b-carousel-slide>

          <!-- Slides with image only -->
          <b-carousel-slide caption="안녕하세요!" text="매물 조회 사이트 어서홈쇼입니다."
            img-src="https://images.pexels.com/photos/9111879/pexels-photo-9111879.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1">
          </b-carousel-slide>
        </b-carousel>
      </b-col>
    </b-row>
    <b-row class="py-1">
      <b-col class="py-1">
        <router-link :to="{ name: 'notice' }">
          <b-card :img-src="require('../assets/main_note.jpg')" img-height="180" img-alt="Image" img-top tag="article"
            class="mb-2 cards">
            <p>공지사항</p>
          </b-card>
        </router-link>
      </b-col>

      <b-col class="py-1">
        <router-link :to="{ name: 'board' }">
          <b-card
            img-src="https://assets-global.website-files.com/61766c42e8e50c99a04fbd4b/62bf38493c10d5bd2a6f5bec_shutterstock_1151217977.jpg"
            img-alt="Image" img-height="180" img-top tag="article" class="mb-2 cards">
            <p>자유게시판</p>
          </b-card>
        </router-link>
      </b-col>
      <b-col class="py-1">
        <router-link :to="{ name: 'news' }">
          <b-card
            img-src="https://images.pexels.com/photos/518543/pexels-photo-518543.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            img-alt="Image" img-height="180" img-top tag="article" class="mb-2 cards">
            <p>최신 부동산 뉴스</p>
          </b-card>
        </router-link>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "AppMain",
  components: {},
  data() {
    return {
      articles: [],
      fields: [
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
      ],
      perPage: 10,
      currentPage: 1,
    };
  },
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg,
      rgba(255, 255, 255, 0) 70%,
      rgba(72, 190, 233, 0.3) 30%);
}

#container {
  width: 100vw;
  height: 40vh;
  background-color: gray;
}

a {
  color: #28a745 !important;
  text-decoration: none !important;
}
</style>
