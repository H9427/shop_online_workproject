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
      path: "/wClothes",
      name: "wClothes",
      component: () => import("@/views/clothes/wClothesView.vue"),
    },
    {
      path: "/shoes",
      name: "shoes",
      component: () => import("@/views/shoes/ShoesView.vue"),
    },
  ],
});

export default router;
