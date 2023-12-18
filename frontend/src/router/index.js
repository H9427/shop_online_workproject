import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "index",
      component: () => import("@/views/IndexView.vue"),
    },
    {
      path: "/",
      name: "welcome",
      component: () => import("@/views/WelcomeView.vue"),
      children: [
        {
          path: "/login",
          name: "welcome-login",
          component: () => import("@/views/welcome/LoginPage.vue"),
        },
        {
          path: "/register",
          name: "welcome-register",
          component: () => import("@/views/welcome/RegisterPage.vue"),
        },
        {
          path: "/forget",
          name: "welcome-forget",
          component: () => import("@/views/welcome/ForgetPage.vue"),
        },
      ],
    },
    {
      path: "/goodsDetail",
      name: "goodsDetail",
      component: () => import("@/views/GoodsDetailView.vue"),
    },
    {
      path: "/goods",
      name: "goods",
      component: () => import("@/views/GoodsView.vue"),
    },
    {
      path: "/shoppingCart",
      name: "shoppingCart",
      component: () => import("@/views/ShoppingCartView.vue"),
    },
    {
      path: "/info",
      name: "info",
      component: () => import("@/views/InfoView.vue"),
    },
    {
      path: "/order",
      name: "order",
      component: () => import("@/views/OrderView.vue"),
    },    {
      path: "/discuss",
      name: "discuss",
      component: () => import("@/views/DiscussView.vue"),
    },
  ],
});

export default router;
