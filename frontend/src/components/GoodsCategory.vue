<template>
  <div class="custom-div">
    <div class="mt-5">
      <button class="btn btn-sm" @click="drawer = true" style="margin-left: 78%">
        筛选条件 <el-icon><ArrowDown /></el-icon>
      </button>
      <!-- sm -->
      <span style="font-size: small" class="ml-2 mr-2">排序</span>
      <select class="select select-bordered select-sm w-30 max-w-xs" v-model="mode">
        <option value="0" selected>综合</option>
        <option value="1">销量</option>
        <option value="2">价格由低到高</option>
        <option value="3">价格由高到低</option>
      </select>
    </div>
    <div role="tablist" class="tabs tabs-bordered" >
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
              <button class="btn btn-sm mr-4" @click="setPriceRange(1500, 99999)">1500+</button>
            </div>

            <div class="mt-8" style="margin-left: 90%">
              <button class="btn" @click="resetPrice">重置</button>
              <button class="btn btn-neutral ml-5" @click="confirmClick">确定</button>
            </div>
          </div>
        </template>
      </el-drawer>
      
      <div class="tabar">
        <div v-for="(item, index) in store.Category" :key="index" @click="click_tabar(index)" :class="store.check == index ? 'check_on' : 'check_off'">
          &nbsp;&nbsp;{{ item.categoryName }}&nbsp;&nbsp;
        </div>
      </div>
      <el-row class="mt-5">
        <el-col v-for="(item, index) in store.Goods" :key="index" :span="6" class="mb-10">
          <!-- <div :to="{ name: 'goodsDetail', params: { id: item.goodsId } }" class="card w-96 bg-base-100 shadow-xl" style="width: 300px"> -->
          <div @click="goDetail(item.goodsId)" class="card w-96 bg-base-100 shadow-xl" style="width: 300px;cursor: pointer;">
            <img :src="item.goodsImg.imgUrl" />

            <h2 class="card-title ml-3 mt-1">{{ item.goodsName }}</h2>
            <p class="ml-3 mb-1">￥{{ item.goodsSku.originalPrice }}&nbsp;&nbsp;销量:{{ item.soldNum }}</p>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { Minus, ArrowDown } from "@element-plus/icons-vue";
import { ref, reactive,watch,onMounted } from "vue";
import router from "@/router";
import { useCategoryStore } from "../stores/category";
const store = useCategoryStore();
const drawer = ref(false);
const direction = ref("ttb");
const minPrice = ref("");
const maxPrice = ref("");
const goods = ref([]);//
const mode = ref(0)
const Data = reactive({
  data: "",
  check: "",
});

watch(mode, (newVal) => {
  q_sort(newVal)
});

function goDetail(id) {
  router.push({path:"/goodsDetail",query:{id:id}});
}

function click_tabar(index) {
  store.check = index;
  store.Goods = store.Category[index].goods;
}

function setPriceRange(min, max) {
  minPrice.value = min.toString();
  maxPrice.value = max.toString();
}

function resetPrice() {
  minPrice.value = "";
  maxPrice.value = "";
  store.Goods = store.Category[store.check].goods;
  q_sort(mode.value);
}

function q_sort(index){
  if(index == 0){
    store.Goods.sort((a,b)=>b.goodsId - a.goodsId)
  }
  if(index == 1){
    store.Goods.sort((a,b)=>b.soldNum - a.soldNum)
  }
  if(index == 2){
    store.Goods.sort((a,b)=>a.goodsSku.originalPrice - b.goodsSku.originalPrice)
  }
  if(index == 3){
    store.Goods.sort((a,b)=>b.goodsSku.originalPrice - a.goodsSku.originalPrice)
  }
}

function confirmClick() {
  store.Goods = store.Category[store.check].goods;
  let ls_sz = new Array(1000);
  let gs = 0;
  for(let i = 0;i < store.Goods.length;i ++){
    if(store.Goods[i].goodsSku.originalPrice <= maxPrice.value&&store.Goods[i].goodsSku.originalPrice >= minPrice.value){
      ls_sz[gs ++] = store.Goods[i];
    }
  }
  let x_sz = new Array(gs);
  for(let i = 0;i < gs;i ++){
    x_sz[i] = ls_sz[i];
  }
  store.Goods = x_sz;
  q_sort(mode.value);
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
