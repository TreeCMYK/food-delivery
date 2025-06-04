import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/orders',
      component: () => import('../components/ui/OrderGrid.vue'),
    },
    {
      path: '/payments',
      component: () => import('../components/ui/PaymentGrid.vue'),
    },
    {
      path: '/foodCookings',
      component: () => import('../components/ui/FoodCookingGrid.vue'),
    },
    {
      path: '/deliveries',
      component: () => import('../components/ui/DeliveryGrid.vue'),
    },
    {
      path: '/myPages',
      component: () => import('../components/MyPageView.vue'),
    },
  ],
})

export default router;
