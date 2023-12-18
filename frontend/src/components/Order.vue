<template>
  <div class="custom-div">
    <div class="mt-10" style="margin-bottom: 4%">
      <p class="font-bold" style="font-size: 150%">我的订单</p>
      <div style="display: flex">
        <!-- 左边 -->
        <div onclick="my_modal_2.showModal()" style="cursor: pointer">
          <el-icon style="float: left; margin-top: 15px; margin-right: 15px"><Location /></el-icon>
          <el-icon style="float: right; margin-top: 15px; margin-left: 20px"><ArrowRightBold /></el-icon> {{Data.DefaultAddress.province}}省{{Data.DefaultAddress.city}}市{{Data.DefaultAddress.area}}{{ Data.DefaultAddress.address }}
          <div class="text-sm opacity-50 mt-1">{{ Data.DefaultAddress.receiverName }} {{ Data.DefaultAddress.receiverMobile }}</div>
        </div>

        <!-- 右边 -->
        <div class="grid grid-flow-col gap-5 text-center auto-cols-max ml-auto">
          <span class="font-bold mt-3">距订单关闭还剩：</span>
          <div class="flex flex-col">
            <span class="countdown font-mono text-3xl">
              <!-- <span style="--value: "></span> -->
              {{Math.floor(sec/60)}}
            </span>
            min
          </div>
          <div class="flex flex-col">
            <span class="countdown font-mono text-3xl">
              <!-- <span style="--value: "></span> -->
              {{Math.floor(sec%60)}}
            </span>
            sec
          </div>
        </div>
      </div>
    </div>
    <div class="overflow-x-auto">
      <table class="table">
        <!-- head -->
        <thead>
          <tr>
            <th>商品名称</th>
            <th>商品尺码</th>
            <th>商品数量</th>
            <th>商品单价</th>
            <th>商品总价</th>
          </tr>
        </thead>
        <tbody>
          <!-- row 1 -->
          <tr v-for="(item,index) in Data.OrderDetails.items" :key="index">
            <td style="width: 40%">
              <div class="flex items-center gap-3">
                <div class="avatar">
                  <div class="mask mask-squircle w-12 h-12">
                    <img :src="item.goods.goodsImg[0].imgUrl" alt="Avatar Tailwind CSS Component" />
                  </div>
                </div>
                <div>
                  <div class="font-bold">{{item.goods.goodsName}}</div>
                  <div class="text-sm opacity-50 mt-1">{{ item.goods.goodsTrait }}</div>
                </div>
              </div>
            </td>

            <td>
              <span class="badge badge-ghost badge-xl">{{ item.goods.goodsSku[0].skuName }}</span>
            </td>
            <td>{{ item.count }}</td>
            <td>￥{{ item.goods.goodsSku[0].originalPrice }}</td>
            <th>
              <p>￥{{ item.count*item.goods.goodsSku[0].originalPrice }}</p>
            </th>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="mt-10 font-bold" style="padding-left: 85%; font-size: 120%">
      <p>总计：￥{{ Data.OrderDetails.totalAmount }}</p>
    </div>
    <div class="mt-10">
      <button class="btn btn-wide btn-ghost" style="margin-left: 41%" @click="$router.push('/')">稍后结账</button>
      <button class="btn btn-wide btn-outline" style="margin-left: 23%" @click="checkout()">去结账</button>
    </div>
  </div>


  <dialog id="my_modal_2" class="modal">
    <div class="modal-box w-11/12 max-w-5xl">
      <h3 class="font-bold text-lg">修改地址</h3>

      <div v-for="(item,index) in Data.AllAddress" :key="index" style="margin-bottom: 20px;" @click="Data.AddressChose=index" :class="Data.AddressChose==index?'chose':''">
        <el-icon style="float: left; margin-top: 15px; margin-right: 15px"><Location /></el-icon>
        <el-icon style="float: right; margin-top: 15px; margin-left: 20px"><ArrowRightBold /></el-icon> {{item.province}}省{{item.city}}市{{item.area}}{{ item.address }}
        <div class="text-sm opacity-50 mt-1">{{ item.receiverName }} {{ item.receiverMobile }}</div>
        <!-- {{ Data.AddressChose }} -->
      </div>

      <div class="modal-action">
        <form method="dialog">
          <button class="btn">取消</button>
          <button class="btn ml-3" @click="changAddress">修改</button>
        </form>

      </div>
    </div>
    <form method="dialog" class="modal-backdrop">
      <button>close</button>
    </form>
  </dialog>


</template>

<script setup>
import { Location, ArrowRightBold } from "@element-plus/icons-vue";
import { ref,reactive } from "vue";
import { onMounted, onUpdated } from "vue";
import router from "@/router";
import { useRoute } from "vue-router";
import { get, internalPost, takeAccessToken } from "../net";


const sec = ref(1800);
const route = useRoute();
const Data = reactive({
  OrderDetails:'',
  AllAddress:'',
  DefaultAddress:'',
  AddressChose:0
});


onMounted(() => {

  get("/api/order/details?orderId=" + route.query.id, (data) => {
    Data.OrderDetails = data;
    let createTime = new Date(data.createTime);
    let nowTime = new Date(); 
    sec.value = 1800 - (Math.abs(nowTime.getTime() - createTime.getTime())/1000)
  });

  get("/api/address/listAddress", (data) => {
    Data.AllAddress = data;
    if(data[0].commonAddress == 1){
      Data.DefaultAddress = data[0];
    }
  });
  
  setInterval(() => {  
    if (sec.value > 0) {  
      sec.value--;  
    } else {  
      clearInterval();  
    }  
  }, 1000);  
  
});

function changAddress(){
  Data.DefaultAddress = Data.AllAddress[Data.AddressChose];
}

function checkout(){
  internalPost(
      "/api/order/editState",
      {
        orderId: Data.OrderDetails.id,
        state:2
      },
      {
        "Content-Type": "application/x-www-form-urlencoded",
        Authorization: `Bearer ${takeAccessToken()}`,
      },
      (data) => {
        ElMessage.success("支付成功");
        router.push({path:"/info"});
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

.chose{
  background-color: black;
  color:white
}
</style>
