<template>
  <div class="custom-div">
    <div class="mt-10 mb-10 font-bold" style="font-size: 150%">
      <p>购物车({{ Cart.length }}个商品)</p>
    </div>
    <div class="overflow-x-auto">
      <table class="table">
        <!-- head -->
        <thead>
          <tr>
            <th>
              <label>
                <input type="checkbox" class="checkbox" :value="1" v-model="reversal" />
              </label>
            </th>
            <th>商品名称</th>
            <th>商品尺码</th>
            <th>商品数量</th>
            <th>商品单价</th>
            <th>商品总价</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- row 1 -->
          <tr v-for="(item, index) in Cart" :key="index">
            <th>
              <label>
                <input type="checkbox" class="checkbox" :value="index" v-model="selectedItems" />
              </label>
            </th>
            <td style="width: 40%">
              <div class="flex items-center gap-3">
                <div class="avatar">
                  <div class="mask mask-squircle w-12 h-12">
                    <img :src="item.goodsDetails.goodsImg.imgUrl" alt="Avatar Tailwind CSS Component" />
                  </div>
                </div>
                <div>
                  <div class="font-bold">{{ item.goodsDetails.goodsName }}</div>
                  <div class="text-sm opacity-50 mt-1">{{ item.goodsDetails.goodsTrait }}</div>
                </div>
              </div>
            </td>

            <td>
              <span class="badge badge-ghost badge-xl">{{ item.goodsSku.skuName }}</span>
            </td>
            <td>
              <el-input-number v-model="item.cartNum" :min="1" :max="999" @change="handleChange" />
            </td>
            <td>￥{{ item.goodsPrice }}.00</td>
            <th>
              <p>￥{{ item.cartNum * item.goodsPrice }}.00</p>
            </th>
            <th>
              <button class="btn btn-ghost btn-xs" @click="delete_cart(item.cartId)">删除</button>
            </th>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="mt-10 font-bold" style="padding-left: 85%; font-size: 120%">
      <p>总计：￥{{ Cart_item.total_price }}.00</p>
    </div>
    <div class="mt-10">
      <button class="btn btn-wide btn-outline btn-error" @click="deleteAll()">全部删除</button>
      <button class="btn btn-wide btn-ghost" style="margin-left: 23%" @click="$router.push('/')">继续浏览商品</button>
      <button class="btn btn-wide btn-outline" style="margin-left: 23%" @click="checkout()">创建订单</button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, watch } from "vue";
import { onMounted, onUpdated } from "vue";
import router from "@/router";
import { get, post, internalPost, takeAccessToken } from "../net";

const num = ref(1);
const reversal = ref();
const Cart = ref("");
const selectedItems = ref([]);
const Cart_item = reactive({
  goodsDetails: "",
  goodsSku: "",
  goodsPrice: "",
  cartNum: "",
  total_price: 0,
});

const handleChange = (value: number) => {
  Cart_item.total_price = 0;
  selectedItems.value.forEach((element) => {
    Cart_item.total_price += Cart.value[element].goodsPrice * Cart.value[element].cartNum;
  });
};

onMounted(() => {
  //获取购物车列表
  get("/api/shopingCart/listshopingCart", (data) => {
    Cart.value = data;
  });
});

watch(selectedItems, (newVal) => {
  Cart_item.total_price = 0;
  newVal.forEach((element) => {
    Cart_item.total_price += Cart.value[element].goodsPrice * Cart.value[element].cartNum;
  });
});

watch(reversal, (newVal) => {
  if (newVal == false) {
    selectedItems.value = [];
  } else {
    console.log(Cart.value.length);
    let arry = new Array(Cart.value.length);
    for (let i = 0; i < Cart.value.length; i++) {
      arry[i] = i;
    }
    selectedItems.value = arry;
  }
});

function checkout() {
  if (selectedItems.value.length == 0) {
    ElMessage.success("请添加商品");
  } else {
    let Goods = new Array(selectedItems.value.length);
    for (let i = 0; i < selectedItems.value.length; i++) {
      Goods[i] = {
        goodsId: Cart.value[selectedItems.value[i]].goodsId,
        skuId: Cart.value[selectedItems.value[i]].goodsSku.skuId,
        goodsPrice: Cart.value[selectedItems.value[i]].goodsSku.originalPrice,
        count: Cart.value[selectedItems.value[i]].cartNum,
      };
    }
    internalPost(
      "/api/order/addOrder",
      {
        totalAmount: Cart_item.total_price,
        orderRemark: "",
        Goods: Goods,
      },
      {
        "Content-Type": "application/json",
        Authorization: `Bearer ${takeAccessToken()}`,
      },
      (data) => {
        ElMessage.success("订单添加成功");
        console.log(data);
        router.push({path:"/order",query:{id:data.id}});
      },
      (message, code, url) => {
        //删除失败
        console.log(message);
        console.log(code);
        console.log(url);
      }
    );
  }
}

function deleteAll(){
  let deleteItems = new Array(selectedItems.value.length)
  for(let i = 0;i < selectedItems.value.length;i ++){
    deleteItems[i] = Cart.value[selectedItems.value[i]].cartId;
  }
  console.log(deleteItems);
  internalPost(
    "/api/shopingCart/delete",
    {
      "cartIds": deleteItems,
    },
    {
      "Content-Type": "application/json",
      Authorization: `Bearer ${takeAccessToken()}`,
    },
    (data) => {
      ElMessage.success("购物车删除成功");
      setTimeout(() => {
        history.go(0);
      }, 200);
    },
    (message, code, url) => {
      //删除失败
      console.log(message);
      console.log(code);
      console.log(url);
    }
  );

}

function delete_cart(id) {
  internalPost(
    "/api/shopingCart/delete",
    {
      "cartIds": [id],
    },
    {
      "Content-Type": "application/json",
      Authorization: `Bearer ${takeAccessToken()}`,
    },
    (data) => {
      ElMessage.success("购物车删除成功");
      setTimeout(() => {
        history.go(0);
      }, 200);
    },
    (message, code, url) => {
      //删除失败
      console.log(message);
      console.log(code);
      console.log(url);
    }
  );
}
</script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}
</style>
