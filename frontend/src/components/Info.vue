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
            <div class="mt-5"><a>昵称:</a><input type="text" placeholder="请输入昵称" class="input input-bordered input-sm w-full max-w-xs" v-model="information.nickName" /></div>
            <div class="mt-5"><a>真实姓名:</a><input type="text" placeholder="请输入真实姓名" class="input input-bordered input-sm w-full max-w-xs" v-model="information.realName" /></div>
            <div class="mt-5"><a>手机号:</a><input type="text" placeholder="请输入手机号" class="input input-bordered input-sm w-full max-w-xs" v-model="information.userMobile" /></div>
            <div class="mt-5"><a>重置密码:</a><input type="password" placeholder="请输入新密码（不改请不要输入）" class="input input-bordered input-sm w-full max-w-xs" v-model="information.userPwd" /></div>
            <div class="mt-5">
              <a>性别:</a>
              <a>男&nbsp;&nbsp;</a><input type="radio" name="radio-1" class="radio checked:bg-blue-500" checked />
              <a>女&nbsp;&nbsp;</a><input type="radio" name="radio-1" class="radio checked:bg-red-500" />
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
          <div class="modal-box">
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
              <button @click="submit_address">提交</button>
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
            <span @click="edit_address(item)" class="underline-on-hover" style="font-size: 24px; cursor: pointer; border: 1px black solid; border-radius: 20px">修改</span>
          </div>
        </div>
      </div>

      <!-- 订单信息 -->
      <div v-if="choose == 2">
        <div v-if="orders.length == 0">
          <p>没有订单信息</p>
        </div>
        <div v-for="(item, index) in orders" :key="index">
          {{ item }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { UserFilled, Promotion, List, StarFilled } from "@element-plus/icons-vue";
import { ref, reactive } from "vue";
import { onMounted, onUpdated } from "vue";
import { get, post, internalPost, takeAccessToken } from "../net";

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
    orders.value = data;
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
