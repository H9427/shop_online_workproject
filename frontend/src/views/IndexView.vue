<template>
  <div>
    <Navbar @show-clothes="ClothesView"/>
    <component :is="currentComponent" />
  </div>
</template>

<script>
import HomeView from "./HomeView.vue";
import ClothesView from "./ClothesView.vue";
import ShoesView from "./ShoesView.vue"

export default {
  components: {
    HomeView,
    ClothesView,
    ShoesView
  },
  data() {
    return {
      currentComponent: "HomeView", // 初始显示衣服页面
    };
  },
  methods: {
    showClothes() {
      this.currentComponent = "ClothesView";
    },
  },
};
</script>

<style scoped></style>
