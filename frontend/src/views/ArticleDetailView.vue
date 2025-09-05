<template>
  <div>
    <div class="flex justify-between mb-4">
      <button class="bg-blue-500 text-white px-4 py-2 rounded cursor-pointer" @click="toggleForm">
        수정
      </button>
      <button class="bg-red-500 text-white px-4 py-2 rounded cursor-pointer" @click="removeArticle">
        삭제
      </button>
    </div>
    <h2 class="text-2xl font-bold mb-2">{{ article.title }}</h2>
    <p class="mb-4">{{ article.content }}</p>
    <p class="text-sm text-gray-500">작성일: {{ article.regDate }}</p>

    <hr class="my-4" />

    <h3 class="text-xl font-semibold mb-2">댓글</h3>
    <CommentForm :articleNo="article.articleNo" @added="fetchComments" />
    <CommentList :commentList="commentList" />
  </div>
</template>

<script setup>
import CommentList from '../components/CommentList.vue'
import CommentForm from '../components/CommentForm.vue'

import { detailArticle, modifyArticle, deleteArticle } from '@/api/article'
import { readAllComment } from '@/api/comment'
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const { VITE_VUE_API_URL } = import.meta.env

const route = useRoute()
const router = useRouter()
const commentList = ref([])
const article = ref({})
const articleNo = route.params.id

const getArticle = async () => {
  detailArticle(
    articleNo,
    (response) => {
      article.value = response.data.data
      console.log(article.value)
    },
    (error) => {
      console.log(error)
    },
    getAllCommentList(),
  )
}

const fetchComments = () => {
  getAllCommentList()
}

const getAllCommentList = async () => {
  readAllComment(articleNo, (response) => {
    commentList.value = response.data.data
    console.log(commentList.value)
  })
}

const updateArticle = () => {}

const removeArticle = () => {
  let deleteCheck = confirm('정말 삭제하시겠습니다?')
  if (deleteCheck) {
    deleteArticle(
      articleNo,
      (response) => {
        console.log(response)
        router.push({ path: '/' })
      },
      (error) => {
        console.log(error)
      },
    )
  }
}

onMounted(() => {
  getArticle()
})
</script>
