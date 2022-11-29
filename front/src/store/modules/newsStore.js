import { newsList } from "@/api/news.js";
const newsStore = {
    namespaced: true,
    state: {
        newsList: [],
    },
    getters: {},
    mutations: {
        SET_NEWS_LIST(state, titles) {
            titles.items.forEach((title) => {
                state.newsList.push(title);
            });
        },
        CLEAR_NEWS_LIST(state) {
            state.newsList = [];
          },
    },
    actions: {
        getNews: ({ commit }, query) =>
         {
            // ?display=10&start=1&sort=sim&
            const params = { 
                "query": query, 
                "display": "100",
                "sort": "date",
            };
            newsList(
                params,
                ({ data }) => {
                    commit("CLEAR_NEWS_LIST");
                    commit("SET_NEWS_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        }
    },
};

export default newsStore;
