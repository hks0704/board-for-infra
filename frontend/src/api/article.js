import { localAxios } from '@/util/http-commons'

const local = localAxios()

function readAlltArticle(success, fail) {
  local.get(`/article`).then(success).catch(fail)
}

// TODO:
// function listArticle(param, success, fail) {
//   local.get(`/article`, { params: param }).then(success).catch(fail)
// }

function detailArticle(articleNo, success, fail) {
  local.get(`/article/detail/${articleNo}`).then(success).catch(fail)
}

function registArticle(article, success, fail) {
  local.post(`/article`, article).then(success).catch(fail)
}

function modifyArticle(article, success, fail) {
  local.put(`/article/update`, article).then(success).catch(fail)
}

function deleteArticle(articleNo, success, fail) {
  local.delete(`/article/${articleNo}`).then(success).catch(fail)
}

export { readAlltArticle, detailArticle, registArticle, modifyArticle, deleteArticle }
