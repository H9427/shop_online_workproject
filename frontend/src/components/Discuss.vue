<template>
  <div class="custom-div">
    <div class="mt-10 rounded-md border-2 border-black">
      <div class="flex items-center gap-3 mt-2 ml-2 mb-2" style="justify-content: center">
        <div class="avatar">
          <div class="mask mask-squircle w-12 h-12">
            <img src="" alt="Avatar Tailwind CSS Component" />
          </div>
        </div>
        <div>
          <div class="font-bold">商品名称</div>
          <div class="text-sm opacity-50 mt-1">商品描述</div>
        </div>
        <div></div>
      </div>

      <div class="mt-2 ml-2 mb-2">
        添加图片或视频：
        <input type="file" class="file-input file-input-bordered file-input-md w-full max-w-xs" />
      </div>
      <div>
        <textarea placeholder="展开说说对商品的想法吧..." class="textarea textarea-bordered textarea-lg ml-3 mr-3" style="width: 98%"></textarea>
      </div>
      <div class="mt-2 ml-2">匿名：<input type="checkbox" checked="checked" class="checkbox" /></div>
      <div class="flex mr-4 mb-3" style="justify-content: right">
        <button class="btn btn-outline btn-warning">
          <el-icon><Position /></el-icon>发布
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { Position } from "@element-plus/icons-vue";
</script>

<style scoped>
.custom-div {
  width: 70%;
  margin: 0 auto; /* 居中 */
}
</style>
