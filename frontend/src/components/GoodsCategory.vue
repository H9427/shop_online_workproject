<template>
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

      <!-- <div>
          <div v-for="(category, index) in store.categoryData" :key="index">
            <p>{{ category.categoryName }}</p>
            <ul>
              <li v-for="(child, childIndex) in category.children" :key="childIndex">
                {{ child.categoryName }}
              </li>
            </ul>
          </div>
        </div> -->
      <div class="tabar">
        <div v-for="(item, index) in store.categoryData.data" :key="index" @click="click_tabar(index)" :class="store.categoryData.check == index ? 'check_on' : 'check_off'">
          &nbsp;&nbsp;{{ item.categoryName }}&nbsp;&nbsp;
        </div>
      </div>

      <el-row class="mt-5">
        <el-col v-for="(item, index) in store.categoryData.data[store.categoryData.check].goods" :key="index" :span="6" class="mb-10">
          <!-- <div :to="{ name: 'goodsDetail', params: { id: item.goodsId } }" class="card w-96 bg-base-100 shadow-xl" style="width: 300px"> -->
          <div @click="goDetail()" class="card w-96 bg-base-100 shadow-xl" style="width: 300px;cursor: pointer;">
            <img :src="item.goodsImg.imgUrl" />

            <h2 class="card-title ml-3 mt-1">{{ item.goodsName }}</h2>
            <p class="ml-3 mb-1">￥{{ item.goodsSku.originalPrice }}</p>
          </div>
        </el-col>
      </el-row>

      <!-- <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="外套" checked />
        <div role="tabpanel" class="tab-content p-10 pl-15 pr-15">
          <el-row>
            <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
              <router-link :to="{ name: 'goodsDetail', params: { id: o } }" class="card w-96 bg-base-100 shadow-xl" style="width: 300px">
                <img src="https://shop-online-wujiahao17.oss-cn-beijing.aliyuncs.com/avatar/40531edb-083c-40d3-a547-05ebc6baa2d9.jpg" />
                <h2 class="card-title ml-3 mt-1">GoodsName!</h2>
                <p class="ml-3 mb-1">￥1000.00</p>
              </router-link>
            </el-col>
          </el-row>
        </div> -->
    </div>
  </div>
</template>

<script setup>
import { Minus, ArrowDown } from "@element-plus/icons-vue";
import { ref, reactive } from "vue";
import Navbar from "../components/Navbar.vue";
import router from "@/router";
import { useCategoryStore } from "../stores/category";
const store = useCategoryStore();
const categoryStore = useCategoryStore();
const drawer = ref(false);
const direction = ref("ttb");
const minPrice = ref("");
const maxPrice = ref("");
const Data = reactive({
  data: "",
  check: "",
});

function goDetail() {
  router.push("/goodsDetail");
}

function click_tabar(index) {
  Data.data = store.categoryData.data;
  Data.check = index;
  categoryStore.setCategoryData(Data);
}

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
  const data = this.$route.query.id;
  console.log(data);
}
</script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}

.tabar {
  margin-top: 20px;
  margin-left: -20px;
  display: flex;
}

.check_on {
  margin-left: 20px;
  border-bottom: 2px solid gray;
}

.check_off {
  margin-left: 20px;
}

.check:hover {
  cursor: pointer;
}

.check_off:hover {
  cursor: pointer;
}
</style>
