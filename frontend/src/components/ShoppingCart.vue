<template>
  <div class="custom-div">
    <div class="mt-10 mb-10 font-bold" style="font-size: 150%">
      <p>购物车(x个商品)</p>
    </div>
    <div class="overflow-x-auto">
      <table class="table">
        <!-- head -->
        <thead>
          <tr>
            <th>
              <label>
                <input type="checkbox" class="checkbox" />
              </label>
            </th>
            <th>商品名称</th>
            <th>商品数量</th>
            <th>商品单价</th>
            <th>商品总价</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- row 1 -->
          <tr>
            <th>
              <label>
                <input type="checkbox" class="checkbox" />
              </label>
            </th>
            <td>
              <div class="flex items-center gap-3">
                <div class="avatar">
                  <div class="mask mask-squircle w-12 h-12">
                    <img src="" alt="Avatar Tailwind CSS Component" />
                  </div>
                </div>
                <div>
                  <div class="font-bold">苹果15</div>
                  <div class="text-sm opacity-50">United States</div>
                </div>
              </div>
            </td>
            <td>
              <el-input-number v-model="num" :min="1" :max="999" @change="handleChange" />
            </td>
            <td>￥100.00</td>
            <th>
              <p>￥100.00</p>
            </th>
            <th>
              <button class="btn btn-ghost btn-xs">删除</button>
            </th>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="mt-10 font-bold" style="padding-left: 85%; font-size: 120%">
      <p>总计：￥100.00</p>
    </div>
    <div class="mt-10">
      <button class="btn btn-wide btn-ghost" style="margin-left: 41%" @click="$router.push('/goods')">继续浏览商品</button>
      <button class="btn btn-wide btn-outline" style="margin-left:20%">去结账</button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";

const num = ref(1);
const handleChange = (value: number) => {
  console.log(value);
};
</script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}
</style>
