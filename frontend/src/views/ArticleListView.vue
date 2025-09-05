<template>
  <div>
    <div class="flex justify-between mb-4">
      <h2 class="text-2xl font-semibold">게시글 목록</h2>
      <button class="bg-blue-500 text-white px-4 py-2 rounded cursor-pointer" @click="toggleForm">
        새 글 작성
      </button>
    </div>
    <ArticleForm v-if="showForm" @Added="onArticleAdded" />

    <ul class="space-y-3">
      <li
        v-for="article in articleList"
        :key="article.articleNo"
        class="p-4 border rounded hover:bg-gray-50"
      >
        <router-link
          :to="{
            path: `/articles/${article.articleNo}`,
            params: { articleNo: article.articleNo },
          }"
          class="block"
        >
          <h3 class="font-bold">{{ article.title }}</h3>
          <p class="text-sm text-gray-600">작성일 : {{ article.regDate }}</p>
          <p class="text-sm text-gray-600">수정일 : {{ article.modDate }}</p>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { readAlltArticle } from '@/api/article'
import ArticleForm from '../components/ArticleForm.vue'
import { onMounted, ref } from 'vue'

const articleList = ref([])
const showForm = ref(false)

const toggleForm = () => {
  showForm.value = !showForm.value
}

const param = ref({
  word: '',
})

const getAllArticleList = () => {
  readAlltArticle((response) => {
    articleList.value = response.data.data
  })
}

const onArticleAdded = () => {
  getAllArticleList()
  showForm.value = false
}

onMounted(() => {
  getAllArticleList()
})

// export default {
//   components: { ArticleForm },
//   data() {
//     return {
//       articles: [],
//       showForm: false,
//     }
//   },
//   methods: {
//     toggleForm() {
//       this.showForm = !this.showForm
//     },
//     async fetchArticles() {
//       const res = await fetch('/api/articles') // Spring Boot API 연결
//       this.articles = await res.json()
//     },
//   },
//   mounted() {
//     this.fetchArticles()
//   },
// }
</script>
