<template>
  <div class="navbar bg-error-content text-neutral-content">
    <div class="flex-1">
      <a class="btn btn-ghost text-xl" @click="$router.push('/')">daisyShopping</a>

      <div class="navbar-center hidden lg:flex" style="margin-left: 33%">
        <ul class="menu menu-horizontal px-1">
          <li v-for="(item, index) in store.TotalGoods" :key="index">
            <a @click="changeCategory(index)">{{ item.categoryName }}</a>
          </li>
        </ul>
      </div>
    </div>

    <div v-if="takeAccessToken() != null">
      <div class="flex-none">
        <div class="dropdown dropdown-end">
          <div tabindex="0" role="button" class="btn btn-ghost btn-circle">
            <div class="indicator">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" /></svg>
              <span class="badge badge-sm indicator-item">{{ Cart.itemsCount }}</span>
            </div>
          </div>
          <div tabindex="0" class="mt-3 z-[1] card card-compact dropdown-content w-52 bg-base-100 shadow">
            <div class="card-body">
              <span class="font-bold text-lg">{{ Cart.itemsCount }} Items</span>
              <span style="color: black">Subtotal: ￥{{ Cart.prices }}</span>
              <div class="card-actions">
                <button class="btn btn-neutral btn-block" @click="router.push('/shoppingCart')">我的购物车</button>
              </div>
            </div>
          </div>
        </div>
        <div class="dropdown dropdown-end">
          <div tabindex="0" role="button" class="btn btn-ghost btn-circle avatar">
            <div class="w-10 rounded-full">
              <img alt="Tailwind CSS Navbar component" :src="information.userImg" />
            </div>
          </div>
          <ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
            <p class="font-bold ml-2 mb-2" style="color: black; font-size: 20px">{{ information.nickName }}</p>
            <li style="color: black">
              <a @click="router.push('/info')"
                ><el-icon><User /></el-icon>个人中心</a
              >
            </li>
            <li style="color: black">
              <a @click="LogOut()"
                ><el-icon><SwitchButton /></el-icon>退出登录</a
              >
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div v-else>
      <div class="btn btn-neutral" @click="router.push('/login')">登录/注册</div>
    </div>
  </div>
</template>

<script setup>
import { User, SwitchButton } from "@element-plus/icons-vue";
import { ref, reactive } from "vue";
import { get, takeAccessToken, logout } from "../net";
import { onMounted } from "vue";
import router from "@/router";
import { useCategoryStore } from "../stores/category";
const store = useCategoryStore();
const information = ref("");
const Cart = ref("");

onMounted(() => {
  //获取分类以及所有商品
  get("/api/category/queryAllCategory", (data) => {
    store.TotalGoods = data;
    console.log(store.TotalGoods)
    store.Category = store.TotalGoods[0].childen;
    console.log(store.Category)
    store.Goods = store.Category[0].goods;
    console.log(store.Goods)
  });

  //获取个人信息
  get("/api/personal/queryInformation", (data) => {
    information.value = data;
  });

  //获取购物车商品个数和总钱数
  get("/api/shopingCart/allItemsAndPrices", (data) => {
    Cart.value = data;
  });
  
});

function LogOut() {
  logout();
  router.push("/");
}

function changeCategory(index) {
  router.push("/goods");
  store.Category = store.TotalGoods[index].childen;
  store.Goods = store.Category[0].goods;
  store.check = 0;
}
</script>

<style scoped></style>