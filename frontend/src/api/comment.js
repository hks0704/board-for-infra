import { localAxios } from '@/util/http-commons'

const local = localAxios()

function registComment(comment, success, fail) {
  local.post(`/comment`, comment).then(success).catch(fail)
}

function readAllComment(articleNo, success, fail) {
  local.get(`/comment/${articleNo}`).then(success).catch(fail)
}

function modifyComment(comment, success, fail) {
  local.put(`/comment/update`, comment).then(success).catch(fail)
}

function deleteComment(commentNo, success, fail) {
  local.delete(`/comment/${commentNo}`).then(success).catch(fail)
}

export { registComment, readAllComment, modifyComment, deleteComment }
