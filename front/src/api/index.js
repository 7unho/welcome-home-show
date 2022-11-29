import axios from "axios";

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: "http://localhost:9999/",
    // baseURL: "http://70.12.50.143:9999/",
    // baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

// house deal API axios instance
function houseInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_HOUSE_DEAL_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

// house deal API axios instance
function newsInstance() {
  const instance = axios.create({
    // baseURL: "https://openapi.naver.com/v1/search/news?",
    baseURL: "/v1/search/news",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "X-Naver-Client-Id": process.env.VUE_APP_NAVER_CLIENT_ID,
      "X-Naver-Client-Secret": process.env.VUE_APP_NAVER_CLIENT_SECRET,
    },
  });
  return instance;
}

export { apiInstance, houseInstance, newsInstance };
