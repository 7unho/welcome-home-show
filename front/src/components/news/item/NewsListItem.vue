<template>
  <b-card
    :header-html="news.title"
    header-text-variant="white"
    header-tag="header"
    header-bg-variant="dark"
    src="news.link"
    footer-border-variant="dark"
    style="max-width: 20rem;"
  >
    <b-card-text v-html="news.description"></b-card-text>
    <b-button @click="detailNews">보러 가기</b-button>
  </b-card>
</template>

<script>
export default {
    name: "NewsListItem",
    props: {
        news: Object,
        // title, pubDate, link, description
    },
    methods: {
        detailNews() {
            window.open(this.news.link);
        }
    }
}
</script>

<style>

</style>