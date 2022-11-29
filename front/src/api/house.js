import { apiInstance } from "./index.js";
// import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function singleHouseList(params, success, fail) {
  api.get(`/map/oneApt`, { params: params }).then(success).catch(fail);
}

function keywordSearchList(params, success, fail) {
  api.get(`/map/search`, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  houseList,
  singleHouseList,
  dongList,
  keywordSearchList,
};
