import { newsInstance } from "./index.js";

const news = newsInstance();

function newsList(params, success, fail) {
  news.get('', { params: params }).then(success).catch(fail);
}

export { newsList };
  