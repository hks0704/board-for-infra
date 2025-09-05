<template>
  <ul class="space-y-2">
    <li v-for="c in commentList" :key="c.commentNo" class="p-2 border rounded">
      <p>{{ c.content }}</p>
      <p class="text-sm text-gray-500">작성일: {{ c.regDate }}</p>
      <p class="text-sm text-gray-500">수정일: {{ c.modDate }}</p>
      <div class="flex justify-between mb-4">
        <button class="bg-blue-500 text-white px-3 py-1 rounded cursor-pointer" @click="toggleForm">
          수정
        </button>
        <button
          class="bg-red-500 text-white px-3 py-1 rounded cursor-pointer"
          @click="removeArticle"
        >
          삭제
        </button>
      </div>
    </li>
  </ul>
</template>

<script setup>
const { commentList } = defineProps({ commentList: [] })
</script>
