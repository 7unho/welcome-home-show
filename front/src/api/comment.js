import { apiInstance } from "./index.js";

const api = apiInstance();

function writeComment(comment, success, fail) {
  api.post(`/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function listComment(articleno, success, fail) {
  api.get(`/comment/${articleno}`).then(success).catch(fail);
}

function updateComment(params, success, fail) {
  api
    .put(`/comment`, JSON.stringify(params), {
      headers: { "Content-Type": `application/json` },
    })
    .then(success)
    .catch(fail);
}

function deleteComment(articleno, success, fail) {
  api.delete(`/comment/${articleno}`).then(success).catch(fail);
}

export { writeComment, listComment, updateComment, deleteComment };
