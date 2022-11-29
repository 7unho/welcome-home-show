<template>
    <div>
        <b-row>
            <b-col>
                <b-alert show variant="">
                    <h3>뉴스</h3>
                </b-alert>
            </b-col>
        </b-row>
        <div class="wrapper">
            <div class="item-container" style="display:grid">
                <news-list-item v-for="(news, index) in newsList" :key="index" :news="news"></news-list-item>
            </div>
        </div>
    </div>
</template>
<script>
import { mapState, mapActions, mapMutations } from "vuex";
import NewsListItem from "@/components/news/item/NewsListItem";

const newsStore = "newsStore";

export default {
    name: "NewsList",
    components: {
        NewsListItem,
    },
    data() {
        return {
        }
    },
    computed: {
        ...mapState(newsStore, ["newsList"]),
    },
    created() {
        // methods/getNewsList()
        this.getNewsList();
    },
    methods: {
        ...mapActions(newsStore, ["getNews"]),
        ...mapMutations(newsStore, ["SET_NEWS_LIST"]),
        getNewsList() {
            if (this.newsList) this.getNews("부동산"); // store/getNewsList
        },
    }
};
</script>
<style lang="scss" scoped>
.wrapper {
    padding-bottom: 10vh;
}

.item-container {
    max-width: 90rem;
    width: 90vw;
    margin: 0 auto;
    padding: 2rem;
    background-color: #fff;
    display: grid;
    grid-template-columns: repeat(auto-fill, 20rem);
    justify-content: center;
    gap: 2rem;
    font-family: 'maple';
    box-shadow: rgba(0, 0, 0, 0.4) 0px 1px 3px, rgba(0, 0, 0, 0.8) 0px 1px 2px;
    border-radius: 1rem;
}

.underline-hotpink {
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
}
</style>
    