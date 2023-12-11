<template>
  <div class="navbar bg-base-200">
    <div class="navbar-start">
      <a class="btn btn-ghost text-xl" @click="$router.push('/')">daisyShopping</a>
    </div>

    <div class="navbar-center hidden lg:flex">
      <ul class="menu menu-horizontal px-1">
        <li><a @click="$router.push('/wClothes')">Clothes</a></li>
        <li><a>Shoes</a></li>
        <li><a>Perfume</a></li>
        <li><a>HandBag</a></li>
      </ul>
    </div>
    <div class="navbar-end">
      <a class="btn" @click="$router.push('/login')">登录/注册</a>
    </div>
  </div>
</template>

<script setup></script>

<style scoped></style>
