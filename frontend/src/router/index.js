import ArticleDetailView from '@/views/ArticleDetailView.vue'
import ArticleListView from '@/views/ArticleListView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: ArticleListView,
    },
    {
      path: '/articles/:id',
      name: 'articleDetail',
      component: ArticleDetailView,
    },
  ],
})

export default router
