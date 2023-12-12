<template>
  <goodsNav />
  <div class="custom-div">
    <el-row :gutter="36" style="padding-top: 15px">
      <!-- 左边 -->
      <el-col :span="12" class="mb-10" style="padding-left: 10%; padding-top: 2%">
        <el-card :body-style="{ padding: '0px' }">
          <a>
            <img src="https://shop-online-wujiahao17.oss-cn-beijing.aliyuncs.com/avatar/40531edb-083c-40d3-a547-05ebc6baa2d9.jpg" />
          </a>
        </el-card>
      </el-col>
      <!-- 右边 -->
      <el-col :span="12" class="mb-10" style="padding-top: 2%; padding-left: 10%">
        <div style="font-size: 20px">女装花朵图案棉服</div>
        <div style="font-size: 17px" class="mt-2">￥ 299.00</div>
        <div class="mt-1">
          <div class="badge badge-ghost"><p style="color: gray">满199包邮</p></div>
        </div>
        <div style="padding-top: 30px">
          <span>尺码</span>
          <span onclick="my_modal_2.showModal()" class="underline-on-hover" style="margin-left: 65%; font-size: 12px; cursor: pointer">尺码说明</span>
          <dialog id="my_modal_2" class="modal">
            <div class="modal-box">
              <h3 class="font-bold text-lg">尺码说明</h3>

              <div class="overflow-x-auto">
                <table class="table">
                  <!-- head -->
                  <thead>
                    <tr>
                      <th>尺码</th>
                      <td>号型</td>
                      <td>肩宽</td>
                      <td>胸围</td>
                      <td>袖长</td>
                    </tr>
                  </thead>
                  <tbody>
                    <!-- row 1 -->
                    <tr>
                      <th>XS</th>
                      <td>160/80A</td>
                      <td>40.5</td>
                      <td>104.0</td>
                      <td>58.0</td>
                    </tr>
                    <!-- row 2 -->
                    <tr>
                      <th>S</th>
                      <td>165/84A</td>
                      <td>41.5</td>
                      <td>108.0</td>
                      <td>59.0</td>
                    </tr>
                    <!-- row 3 -->
                    <tr>
                      <th>M</th>
                      <td>170/88A</td>
                      <td>42.5</td>
                      <td>112.0</td>
                      <td>60.0</td>
                    </tr>
                    <!-- row 4 -->
                    <tr>
                      <th>L</th>
                      <td>170/92A</td>
                      <td>43.5</td>
                      <td>116.0</td>
                      <td>61.0</td>
                    </tr>
                    <!-- row 5 -->
                    <tr>
                      <th>XL</th>
                      <td>175/96A</td>
                      <td>44.5</td>
                      <td>120.0</td>
                      <td>62.0</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <form method="dialog" class="modal-backdrop">
              <button>close</button>
            </form>
          </dialog>
        </div>
        <div class="flex-container">
          <button :class="num == 1 ? sizeOn : sizeOff" @click="num = 1">&nbsp;XS(160/80A)</button>
          <button :class="num == 2 ? sizeOn : sizeOff" @click="num = 2">&nbsp;S(165/84A)</button>
          <button :class="num == 3 ? sizeOn : sizeOff" @click="num = 3">&nbsp;M(170/88A)</button>
          <button :class="num == 4 ? sizeOn : sizeOff" @click="num = 4">&nbsp;L(170/92A)</button>
          <button :class="num == 5 ? sizeOn : sizeOff" @click="num = 5">&nbsp;XL(175/96A)</button>
        </div>

        <div style="padding-top: 30px">数量</div>
        <div style="padding-top: 10px"><el-input-number v-model="count" :min="1" :max="999" @change="handleChange" /></div>
        <div style="padding-top: 30px"><button class="btn btn-wide">加入购物车</button></div>
        <div class="flex items-baseline mt-4 mb-6 pb-6 border-b border-slate-200">
          <div class="space-x-2 flex text-sm">
            <label>
              <input class="sr-only peer" name="size" type="radio" value="xs" checked />
              <div class="w-9 h-9 rounded-lg flex items-center justify-center text-slate-700 peer-checked:font-semibold peer-checked:bg-slate-900 peer-checked:text-white">XS</div>
            </label>
            <label>
              <input class="sr-only peer" name="size" type="radio" value="s" />
              <div class="w-9 h-9 rounded-lg flex items-center justify-center text-slate-700 peer-checked:font-semibold peer-checked:bg-slate-900 peer-checked:text-white">S</div>
            </label>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
const sizeOn = ref("button-on");
const sizeOff = ref("button-off");
const num = ref(0);

const count = ref(1);
const handleChange = (value: number) => {
  console.log(value);
};
</script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}
.flex-container {
  margin-top: 10px;
  display: flex;
  gap: 11px;
  flex-wrap: wrap;
}

.button-off {
  font-size: 15px;
  border: 1px solid black;
  width: 18.5%;
  height: 30px;
  padding: 0;
  cursor: pointer;
}

.button-on {
  font-size: 15px;
  background-color: black;
  color: white;
  width: 18.5%;
  height: 30px;
  padding: 0;
  cursor: pointer;
}

.underline-on-hover:hover {
  text-decoration: underline;
}
</style>
