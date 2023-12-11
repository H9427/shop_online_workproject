<template>
  <div>
    <Navbar />
    <component :is="currentComponent" />
    <Footer/>
  </div>
</template>

<script>
import HomeView from "./HomeView.vue";

export default {
  components: {
    HomeView,
  },
  data() {
    return {
      currentComponent: "HomeView", // 初始主页面
    };
  },
};
</script>

<style scoped></style>
