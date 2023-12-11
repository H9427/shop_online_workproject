<template>
  <goodsNav />
  <div class="custom-div">
    <div class="mt-5">
      <button class="btn btn-sm" @click="drawer = true" style="margin-left: 78%">
        筛选条件 <el-icon><ArrowDown /></el-icon>
      </button>
      <!-- sm -->
      <span style="font-size: small" class="ml-2 mr-2">排序</span>
      <select class="select select-bordered select-sm w-30 max-w-xs">
        <option selected>综合</option>
        <option>销量</option>
        <option>价格由低到高</option>
        <option>价格由高到低</option>
      </select>
    </div>
    <div role="tablist" class="tabs tabs-bordered">
      <el-drawer v-model="drawer" :direction="direction">
        <template #header>
          <div>
            <p class="mb-5">自定义价格</p>
            <input v-model="minPrice" type="text" placeholder="自定义最低价" class="input input-bordered w-full max-w-xs" style="width: 200px" />
            <el-icon class="ml-2 mr-2"><Minus /></el-icon>
            <input v-model="maxPrice" type="text" placeholder="自定义最高价" class="input input-bordered w-full max-w-xs" style="width: 200px" />
            <div class="mt-5">
              <button class="btn btn-sm mr-4" @click="setPriceRange(0, 100)">0-100</button>
              <button class="btn btn-sm mr-4" @click="setPriceRange(100, 200)">100-200</button>
              <button class="btn btn-sm mr-4" @click="setPriceRange(200, 500)">200-500</button>
              <button class="btn btn-sm mr-4" @click="setPriceRange(500, 1000)">500-1000</button>
              <button class="btn btn-sm mr-4" @click="setPriceRange(1000, 1500)">1000-1500</button>
              <button class="btn btn-sm mr-4" @click="setPriceRange(1500, Infinity)">1500+</button>
            </div>

            <div class="mt-8" style="margin-left: 90%">
              <button class="btn" @click="resetPrice">重置</button>
              <button class="btn btn-neutral ml-5" @click="confirmClick">确定</button>
            </div>
          </div>
        </template>
      </el-drawer>

      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="外套" checked />
      <div role="tabpanel" class="tab-content p-10 pl-15 pr-15">
        <el-row>
          <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
            <router-link :to="{ name: 'goodsDetail', params: { id: o } }" class="card w-96 bg-base-100 shadow-xl" style="width: 300px">
              <img src="https://ufm-pic.ur.com.cn/product/UML130023/image/UML130023K0A_1.jpg?x-image-process=image/quality,Q_90" />
              <h2 class="card-title ml-3 mt-1">GoodsName!</h2>
              <p class="ml-3 mb-1">￥1000.00</p>
            </router-link>
          </el-col>
        </el-row>
      </div>

      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="羽绒" />
      <div role="tabpanel" class="tab-content p-10">
        <el-row>
          <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
            <div class="card w-96 bg-base-100 shadow-xl" style="width: 300px">
              <img src="https://ufm-pic.ur.com.cn/product/MF48S1KN2002/image/MF48S1KN2002B6_1.jpg?x-image-process=image/quality,Q_90" />
              <h2 class="card-title ml-3 mt-1">GoodsName!</h2>
              <p class="ml-3 mb-1">￥1000.00</p>
            </div>
          </el-col>
        </el-row>
      </div>

      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="针织" />
      <div role="tabpanel" class="tab-content p-10">
        <el-row>
          <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
            <div class="card w-96 bg-base-100 shadow-xl" style="width: 300px">
              <img src="https://ufm-pic.ur.com.cn/product/UMV930020/image/UMV930020B6A_1.jpg?x-image-process=image/quality,Q_90" />
              <h2 class="card-title ml-3 mt-1">GoodsName!</h2>
              <p class="ml-3 mb-1">￥1000.00</p>
            </div>
          </el-col>
        </el-row>
      </div>

      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="裤子" />
      <div role="tabpanel" class="tab-content p-10">
        <el-row>
          <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
            <div class="card w-96 bg-base-100 shadow-xl" style="width: 300px">
              <img src="https://ufm-pic.ur.com.cn/product/UMF632023/image/UMF632023B6A_1.jpg?x-image-process=image/quality,Q_90" />
              <h2 class="card-title ml-3 mt-1">GoodsName!</h2>
              <p class="ml-3 mb-1">￥1000.00</p>
            </div>
          </el-col>
        </el-row>
      </div>

      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="丹宁" />
      <div role="tabpanel" class="tab-content p-10">
        <el-row>
          <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
            <div class="card w-96 bg-base-100 shadow-xl" style="width: 300px">
              <img src="https://ufm-pic.ur.com.cn/product/UMF830008/image/UMF830008LDA_1.jpg?x-image-process=image/quality,Q_90" />
              <h2 class="card-title ml-3 mt-1">GoodsName!</h2>
              <p class="ml-3 mb-1">￥1000.00</p>
            </div>
          </el-col>
        </el-row>
      </div>

      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="全部" />
      <div role="tabpanel" class="tab-content p-10">
        <el-row>
          <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
            <div class="card w-96 bg-base-100 shadow-xl" style="width: 300px">
              <img src="https://ufm-pic.ur.com.cn/product/UMV232017/image/UMV232017W0A_1.jpg?x-image-process=image/quality,Q_90" />
              <h2 class="card-title ml-3 mt-1">GoodsName!</h2>
              <p class="ml-3 mb-1">￥1000.00</p>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { Minus, ArrowDown } from "@element-plus/icons-vue";
import { ref } from "vue";

const drawer = ref(false);
const direction = ref("ttb");
const minPrice = ref("");
const maxPrice = ref("");

function setPriceRange(min, max) {
  minPrice.value = min.toString();
  maxPrice.value = max.toString();
}

function resetPrice() {
  minPrice.value = "";
  maxPrice.value = "";
}

function confirmClick() {
  drawer.value = false;
}
</script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}
</style>
