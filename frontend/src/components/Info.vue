<template>
  <div class="container">
    <ul class="menu bg-base-200 w-52 rounded-box mt-10" style="height: 600px">
      <li v-for="(item, index) in menuItems" :key="index">
        <a :class="choose == index ? 'active' : ''" @click="choose = index">
          <el-icon style="font-size: 18px"><component :is="item.icon" /></el-icon>
          {{ item.label }}
        </a>
      </li>
    </ul>

    <div class="content mt-10">
      <div class="mt-1">{{ menuItems[choose]?.label }}</div>
      <!-- 个人信息 -->
      <div v-if="choose == 0">
        <div style="height: 1000px; width: 100%; background-color: white">
          <!-- 头像 -->
          <div style="display: flex; justify-content: center; align-items: center">
            <div for="avatar">
              <div tabindex="0" role="button" class="btn btn-ghost btn-circle avatar" style="width: 100px; height: 100px" for="avatar">
                <div class="w-10 rounded-full" style="width: 100%; height: 100%">
                  <img alt="Tailwind CSS Navbar component" :src="information.userImg" />
                </div>
              </div>
              <div id="customFile" style="width: 100px">
                <input type="file" id="avatar" name="avatar" accept="image/*" />
                <label for="avatar">选择头像</label>
              </div>
            </div>
          </div>
          <!-- 表单 -->
          <div class="custom-div">
            <div class="mt-5"><a>昵称:ㅤㅤ</a><input type="text" placeholder="请输入昵称" class="input input-bordered input-sm w-full max-w-xs ml-4" v-model="information.nickName" /></div>
            <div class="mt-5"><a>真实姓名:</a><input type="text" placeholder="请输入真实姓名" class="input input-bordered input-sm w-full max-w-xs ml-4" v-model="information.realName" /></div>
            <div class="mt-5"><a>手机号:ㅤ</a><input type="text" placeholder="请输入手机号" class="input input-bordered input-sm w-full max-w-xs ml-4" v-model="information.userMobile" /></div>
            <div class="mt-5"><a>重置密码:</a><input type="password" placeholder="请输入新密码（不改请不要输入）" class="input input-bordered input-sm w-full max-w-xs ml-4" v-model="information.userPwd" /></div>
            <div class="mt-3">
              <a class="mr-4">性别:ㅤㅤ</a>
              <a>男&nbsp;&nbsp;</a>
              <input type="radio" name="radio-1" class="radio checked:bg-blue-500" value="男" v-model="information.userSex" checked />ㅤ
              <a>女&nbsp;&nbsp;</a>
              <input type="radio" name="radio-1" class="radio checked:bg-red-500" value="女" v-model="information.userSex" />
            </div>
            <div style="text-align: center">
              <button class="btn btn-neutral btn-wide" @click="submit_information">保存</button>
            </div>
          </div>
        </div>
      </div>

      <!-- 地址信息 -->
      <div v-if="choose == 1">
        <span @click="new_address()" class="underline-on-hover" style="font-size: 24px; cursor: pointer; border: 1px black solid; border-radius: 20px">新建地址</span>
        <dialog id="my_modal_2" class="modal">
          <div class="modal-box w-11/12 max-w-5xl">
            <h3 class="font-bold text-lg">填写地址信息</h3>
            <div class="overflow-x-auto">
              <table class="table">
                <tbody>
                  <!-- row 1 -->
                  <tr>
                    <td>姓名：<input type="text" v-model="address_add.receiverName" /></td>
                    <td>电话：<input type="text" v-model="address_add.receiverMobile" /></td>
                  </tr>
                  <!-- row 2 -->
                  <tr>
                    <td>省份：<input type="text" v-model="address_add.province" /></td>
                    <td>地级市：<input type="text" v-model="address_add.city" /></td>
                    <td>区域：<input type="text" v-model="address_add.area" /></td>
                  </tr>
                  <!-- row 3 -->
                  <tr>
                    <td>详细地址：<input type="text" v-model="address_add.address" /></td>
                    <td>邮编：<input type="text" v-model="address_add.postCode" /></td>
                  </tr>
                </tbody>
              </table>
              <button class="btn" @click="submit_address" style="background-color: black; color: white">提交</button>
            </div>
          </div>
          <form method="dialog" class="modal-backdrop">
            <button>close</button>
          </form>
        </dialog>

        <div v-if="address.length == 0">
          <p>没有建立地址</p>
        </div>
        <div v-for="(item, index) in address" :key="index" style="margin-top: 20px">
          <div class="address" style="font-size: 24px">
            <div>
              <h1 v-if="index == 0" style="background-color: black; color: white">默认地址</h1>
              <div style="float: right">
                <button class="btn">
                  默认地址
                  <input type="radio" :id="item.addressId" name="commonAddress" @click="edit_commonAddress(item.addressId)" />
                </button>
              </div>
              <h1>地址{{ index + 1 }}</h1>
            </div>
            <table>
              <tr>
                <td>姓名：{{ item.receiverName }}</td>
                <td>手机号：{{ item.receiverMobile }}</td>
              </tr>
              <tr>
                <td>地区：{{ item.province }} {{ item.city }} {{ item.area }}</td>
              </tr>
              <tr>
                <td>详细地址：{{ item.address }}</td>
              </tr>
            </table>
            <button class="btn" @click="edit_address(item)">修改</button> <button class="btn" @click="edit_address(item)" style="background-color: red">删除</button>
          </div>
        </div>
      </div>

      <!-- 订单信息 -->
      <div v-if="choose == 2">
        <div v-if="orders.length == 0">
          <p>没有订单信息</p>
        </div>
        <div v-else>
          <div role="tablist" class="tabs tabs-lifted">
            <input type="radio" name="my_tabs_2" role="tab" class="tab" aria-label="全部" checked />
            <div role="tabpanel" class="tab-content bg-base-100 border-base-300 rounded-box p-6" style="height: 540px; overflow: auto">
              <table class="table">
                <!-- head -->
                <thead>
                  <tr>
                    <th>商品名称</th>
                    <th>商品单价</th>
                    <th>商品总价</th>
                    <th>订单状态</th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody>
                  <!-- row 1 -->
                  <tr v-for="(item, index) in orders" :key="index">
                    <td style="width: 40%">
                      <div class="flex items-center gap-3" v-for="(item1, index) in item.items" :key="index">
                        <div class="avatar">
                          <div class="mask mask-squircle w-12 h-12">
                            <img :src="item1.goods.goodsImg[0].imgUrl" alt="Avatar Tailwind CSS Component" />
                          </div>
                        </div>
                        <div>
                          <div class="font-bold">{{ item1.goods.goodsName }}</div>
                          <div class="mt-2">
                            <span class="badge badge-ghost badge-xl">xl</span> <a class="text-sm opacity-50">x{{ item1.count }}</a>
                          </div>
                        </div>
                      </div>
                    </td>

                    <td>
                      <div v-for="(item1, index) in item.items" :key="index">￥{{ item1.goodsPrice }}</div>
                    </td>
                    <th>
                      <p>￥{{ item.totalAmount }}</p>
                    </th>
                    <th>
                      <p v-if="item.status == 1">待付款</p>
                      <p v-if="item.status == 2">待收货</p>
                      <p v-if="item.status == 3">待评价</p>
                      <p v-if="item.status == 4">已完成</p>
                    </th>
                    <th>
                      <button class="btn btn-ghost btn-xs" v-if="item.status == 1" @click="payment(item.id)">去付款</button>
                      <button class="btn btn-ghost btn-xs" v-if="item.status == 2" @click="deliveryGoods(item.id)">确定收货</button>
                      <button class="btn btn-ghost btn-xs" v-if="item.status == 3" @click="router.push('/discuss')">去评价</button>
                      <button class="btn btn-ghost btn-xs" v-if="item.status == 1 || item.state == 4" @click="cancel(item.id)">删除订单</button>
                      <button class="btn btn-ghost btn-xs" v-if="item.status == 4">删除订单</button>
                    </th>
                  </tr>
                </tbody>
              </table>
            </div>

            <input type="radio" name="my_tabs_2" role="tab" class="tab" aria-label="待付款" />
            <div role="tabpanel" class="tab-content bg-base-100 border-base-300 rounded-box p-6">Tab content 2</div>

            <input type="radio" name="my_tabs_2" role="tab" class="tab" aria-label="待收货" />
            <div role="tabpanel" class="tab-content bg-base-100 border-base-300 rounded-box p-6">Tab content 3</div>

            <input type="radio" name="my_tabs_2" role="tab" class="tab" aria-label="待评价" />
            <div role="tabpanel" class="tab-content bg-base-100 border-base-300 rounded-box p-6">Tab content 3</div>
          </div>
        </div>

        <!-- <div v-for="(item, index) in orders" :key="index"></div> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { UserFilled, Promotion, List, StarFilled } from "@element-plus/icons-vue";
import { ref, reactive } from "vue";
import { onMounted, onUpdated } from "vue";
import { get, post, internalPost, takeAccessToken } from "../net";
import router from "@/router";

const choose = ref(0);
const information = ref("");
const address = ref("");
const orders = ref("");
const selectedOption = ref("0");
const address_add = reactive({
  addressId: "",
  receiverName: "",
  receiverMobile: "",
  province: "",
  city: "",
  area: "",
  address: "",
  postCode: "",
  commonAddress: "",
});

const menuItems = [
  { icon: UserFilled, label: "个人信息" },
  { icon: Promotion, label: "地址信息" },
  { icon: List, label: "我的订单" },
  { icon: StarFilled, label: "我的收藏" },
];

onMounted(() => {
  //获取个人信息
  get("/api/personal/queryInformation", (data) => {
    data.userPwd = "";
    information.value = data;
  });

  //获取地址列表
  get("/api/address/listAddress", (data) => {
    address.value = data;
  });

  //获取订单列表
  get("/api/order/listOrders", (data) => {
    let exist_address = new Array(1000);
    let length = 0;
    for (let i = 0; i < data.length; i++) {
      let createTime = new Date(data[i].createTime);
      let nowTime = new Date();
      let time = 1800 - Math.abs(nowTime.getTime() - createTime.getTime()) / 1000;
      if (data[i].state == 1 && time < 0) {
        internalPost(
          "/api/order/delete",
          {
            orderId: data[i].id,
          },
          {
            "Content-Type": "application/x-www-form-urlencoded",
            Authorization: `Bearer ${takeAccessToken()}`,
          },
          (data) => {
            console.log("删除成功");
          },
          (message, code, url) => {
            //删除失败
            console.log(message);
            console.log(code);
            console.log(url);
          }
        );
      } else {
        exist_address[length++] = data[i];
      }
    }
    let exist_address1 = new Array(length);
    for (let i = 0; i < length; i++) {
      exist_address1[i] = exist_address[i];
    }
    orders.value = exist_address1;
  });
});

onUpdated(() => {
  if (choose.value == 0) {
    document.getElementById("avatar").addEventListener("change", function (event) {
      var file = event.target.files[0];
      uploadFile(file);
    });
  }
});

function deliveryGoods(orderId) {
  internalPost(
    "/api/order/editState",
    {
      orderId: orderId,
      state: 3,
    },
    {
      "Content-Type": "application/x-www-form-urlencoded",
      Authorization: `Bearer ${takeAccessToken()}`,
    },
    (data) => {
      ElMessage.success("收货成功");
    },
    (message, code, url) => {
      //删除失败
      console.log(message);
      console.log(code);
      console.log(url);
    }
  );
}

function cancel(orderId) {
  internalPost(
    "/api/order/delete",
    {
      orderId: orderId,
    },
    {
      "Content-Type": "application/x-www-form-urlencoded",
      Authorization: `Bearer ${takeAccessToken()}`,
    },
    (data) => {
      ElMessage.success("订单删除成功");
    },
    (message, code, url) => {
      //删除失败
      console.log(message);
      console.log(code);
      console.log(url);
    }
  );
}

function payment(orderId) {
  router.push({ path: "/order", query: { id: orderId } });
}

function new_address() {
  address_add.addressId = -1;
  address_add.receiverName = "";
  address_add.receiverMobile = "";
  address_add.province = "";
  address_add.city = "";
  address_add.area = "";
  address_add.address = "";
  address_add.postCode = "";
  address_add.commonAddress = "";
  my_modal_2.showModal();
}

function edit_address(item) {
  address_add.addressId = item.addressId;
  address_add.receiverName = item.receiverName;
  address_add.receiverMobile = item.receiverMobile;
  address_add.province = item.province;
  address_add.city = item.city;
  address_add.area = item.area;
  address_add.address = item.address;
  address_add.postCode = item.postCode;
  address_add.commonAddress = item.commonAddress;
  my_modal_2.showModal();
}

function edit_commonAddress(addressId) {
  internalPost(
    "/api/address/commonAddress",
    {
      addressId: addressId,
    },
    {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${takeAccessToken()}`,
    },
    (data) => {
      ElMessage.success("默认地址更新成功");
    },
    (message, code, url) => {
      ElMessage.warning(message);
    }
  );
}

function uploadFile(file) {
  internalPost(
    "/api/personal/avatar",
    {
      file: file,
    },
    {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${takeAccessToken()}`,
    },
    (data) => {
      ElMessage.success("头像更新成功");
    },
    (message, code, url) => {
      ElMessage.warning(message);
    }
  );
}

function submit_address() {
  if (address_add.addressId == -1) {
    internalPost(
      "/api/address/addAddress",
      {
        receiverName: address_add.receiverName,
        receiverMobile: address_add.receiverMobile,
        province: address_add.province,
        city: address_add.city,
        area: address_add.area,
        address: address_add.address,
        postCode: address_add.postCode,
        commonAddress: 0,
      },
      {
        "Content-Type": "application/x-www-form-urlencoded",
        Authorization: `Bearer ${takeAccessToken()}`,
      },
      (data) => {
        ElMessage.success("地址添加成功");
      },
      (message, code, url) => {
        ElMessage.warning(message);
      }
    );
  } else {
    internalPost(
      "/api/address/editAddress",
      {
        addressId: address_add.addressId,
        receiverName: address_add.receiverName,
        receiverMobile: address_add.receiverMobile,
        province: address_add.province,
        city: address_add.city,
        area: address_add.area,
        address: address_add.address,
        postCode: address_add.postCode,
      },
      {
        "Content-Type": "application/x-www-form-urlencoded",
        Authorization: `Bearer ${takeAccessToken()}`,
      },
      (data) => {
        ElMessage.success("地址修改成功");
      },
      (message, code, url) => {
        ElMessage.warning(message);
      }
    );
  }
}

function submit_information() {
  internalPost(
    "/api/personal/modifyInformation",
    {
      userPwd: information.value.userPwd,
      nickName: information.value.nickName,
      realName: information.value.realName,
      userMobile: information.value.userMobile,
      userSex: information.value.userSex,
    },
    {
      "Content-Type": "application/x-www-form-urlencoded",
      Authorization: `Bearer ${takeAccessToken()}`,
    },
    (data) => {
      ElMessage.success("信息修改成功");
    },
    (message, code, url) => {
      ElMessage.warning(message);
    }
  );
}
</script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}

.container {
  display: flex;
  justify-content: space-between;
  width: 75%;
  margin: 0 auto;
}

.content {
  flex: 1;
  padding-left: 20px;
}

#customFile {
  position: relative;
  width: 150px;
  height: 30px;
  overflow: hidden;
}
#customFile input[type="file"] {
  position: absolute;
  top: 0;
  right: 0;
  opacity: 0;
  width: 100%;
  height: 100%;
  cursor: pointer;
}
#customFile label {
  display: inline-block;
  width: 100%;
  height: 100%;
  text-align: center;
  line-height: 30px;
  background-color: #f0f0f0;
  border: 1px solid #ddd;
  cursor: pointer;
}

.address {
  border: 1px black solid;
}
</style>
