<template>
  <form @submit.prevent="writeComment" class="space-y-2 mb-4">
    <!-- v-model="content" -->
    <textarea
      v-model="comment.content"
      placeholder="댓글을 입력하세요"
      class="w-full border p-2 rounded"
    ></textarea>
    <button type="submit" class="bg-blue-500 text-white px-3 py-1 rounded cursor-pointer">
      댓글 등록
    </button>
  </form>
</template>

<script setup>
import { ref, watch } from 'vue'
import { registComment } from '@/api/comment'
import { useRouter } from 'vue-router'

const props = defineProps({ articleNo: Number })
const router = useRouter()
const emit = defineEmits(['added'])

const comment = ref({
  content: '',
  articleNo: 0,
})

watch(
  () => props.articleNo,
  (newVal) => {
    comment.value.articleNo = newVal
  },
  { immediate: true },
)

const writeComment = () => {
  console.log(comment.value)

  registComment(
    comment.value,
    (success) => {
      console.log(success)
      emit('added')
      router.push({ path: `/articles/${comment.value.articleNo}` })
      comment.value.content = ''
    },
    (error) => {
      console.log(error)
    },
  )
}

// export default {
//   props: ['articleNo'],
//   data() {
//     return { content: '' }
//   },
//   methods: {
//     async createComment() {
//       await fetch(`/api/articles/${this.articleNo}/comments`, {
//         method: 'POST',
//         headers: { 'Content-Type': 'application/json' },
//         body: JSON.stringify({ content: this.content }),
//       })
//       this.content = ''
//       this.$emit('added')
//     },
//   },
// }
</script>
