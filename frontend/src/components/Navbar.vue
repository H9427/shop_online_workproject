<template>
  <div class="navbar bg-base-200">
    <div class="navbar-start">
      <a class="btn btn-ghost text-xl" @click="$router.push('/')">daisyShopping</a>
    </div>
    <div class="navbar-center hidden lg:flex">
      <ul class="menu menu-horizontal px-1">
        
        <li><a @click="showClothes">Clothes</a></li>
        <li><a>Shoes</a></li>
        <li>
          <details>
            <summary>Parent</summary>
            <ul class="p-2">
              <li><a>123</a></li>
              <li><a>456</a></li>
            </ul>
          </details>
        </li>
      </ul>
    </div>
    <div class="navbar-end">
      <a class="btn" @click="$router.push('/login')">登录/注册</a>
    </div>
  </div>
</template>

<script setup>
const setup = (props, { emit }) => {
  const showClothes = () => {
    emit('show-clothes');
  };

  // 返回供模板使用的数据和方法
  return {
    showClothes,
  };
};
</script>

<style scoped></style>
