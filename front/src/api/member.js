import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function joinUser(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function removeUser(userid, success, fail) {
  await api.delete(`/user/${userid}`).then(success).catch(fail);
}

async function updateUser(user, success, fail) {
  await api.put(`/user`, user).then(success).catch(fail);
}

async function addNewBookmark(params, success, fail) {
  await api
    .post(`/user/bookmark`, JSON.stringify(params), {
      headers: { "Content-Type": `application/json` }
    })
    .then(success)
    .catch(fail);
}

async function delBookmark(params, success, fail) {
  await api
    .delete(`/user/bookmark`, { headers: { "Content-Type": `application/json` }, data: JSON.stringify(params) })
    .then(success)
    .catch(fail);
}






export { login, findById, tokenRegeneration, logout, joinUser, removeUser, updateUser, addNewBookmark, delBookmark };
