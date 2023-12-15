<template>
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
        <div style="padding-top: 30px"><button class="btn btn-wide" style="width: 80%">加入购物车</button></div>
        <!-- <div class="flex items-baseline mt-4 mb-6 pb-6 border-b border-slate-200">
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
        </div> -->
        <div class="mt-10" style="font-size: 90%; color: gray">商品简介</div>

        <div class="demo-collapse mt-5" style="width: 80%">
          <el-collapse v-model="activeNames" @change="handleChange1">
            <el-collapse-item title="面料保养" name="1">
              <div style="font-size: 95%; color: gray">针织： 面层：100%聚酯纤维 底层：100%聚酯纤维 梭织：100%棉 （含微量其他纤维）; (温馨提示：产品分批次生产，按成分实测值标识产品信息，成分差异在标准允许范围内，货品随机发货) 建议深浅颜色分开洗涤，洗涤温度不高于30度，常规程序，不可漂白，通风处自然晾干，不宜长时间浸泡以及暴晒，烫斗底板温度不高于110度。</div>
            </el-collapse-item>
            <el-collapse-item title="价格说明" name="2">
              <div style="font-size: 95%; color: gray">•划线价格</div>
              <div style="font-size: 95%; color: gray">商品的专柜价、吊牌价、正品零售价、厂商指导价或该商品的曾经展示过的销售价等，并非原价，仅供参考</div>
              <div style="font-size: 95%; color: gray">•未划线价格</div>
              <div style="font-size: 95%; color: gray">商品的实时标价，不因表述的差异改变性质。具体成交价格根据商品参加活动，或会员使用优惠券、积分等发生变化，最终以订单单结算页价格为准。</div>
              <div style="font-size: 95%; color: gray">•商家详情页（含主图）以图片或文字形式标注的一口价、促销价、优惠价等价格可能是在使用优惠券、满减或特定优惠活动和时段等情形下的价格，具体请以结算页面的标价、优惠条件或活动规则为准。</div>
            </el-collapse-item>
          </el-collapse>
        </div>
        <div class="mt-2" style="font-size: 90%; color: gray">发货后14天无理由退换</div>
      </el-col>
    </el-row>
  </div>
  <Footer class="mt-10" />
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

const activeNames = ref(["1"]);
const handleChange1 = (val: string[]) => {
  console.log(val);
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
