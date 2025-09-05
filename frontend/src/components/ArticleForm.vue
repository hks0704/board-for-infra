<template>
  <form @submit.prevent="createArticle" class="space-y-3 mb-6">
    <input
      v-model="article.title"
      type="text"
      placeholder="제목"
      class="w-full border p-2 rounded"
    />
    <textarea
      v-model="article.content"
      placeholder="내용"
      class="w-full border p-2 rounded"
    ></textarea>
    <button
      @click="insertArticle"
      type="submit"
      class="bg-green-500 text-white px-4 py-2 rounded cursor-pointer"
    >
      등록
    </button>
  </form>
</template>

<script setup>
import { ref } from 'vue'
import { registArticle } from '@/api/article'
import { useRouter } from 'vue-router'

const router = useRouter()

const emit = defineEmits(['added'])

const article = ref({
  title: '',
  content: '',
})

const insertArticle = async () => {
  if (article.value.title.trim().length === 0) {
    alert('제목을 입력해주세요!')
    return
  }

  if (article.value.content.trim().length === 0) {
    alert('내용을 입력해주세요!')
    return
  }

  const id = ref(0)
  registArticle(
    article.value,
    (response) => {
      id.value = response
      emit('added')
    },
    (error) => {
      console.log(error)
    },
  )
}
</script>
