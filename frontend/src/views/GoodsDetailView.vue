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
        <div style="font-size: 17px">￥ 299.00</div>
        <div style="padding-top: 20px">尺码</div>
        <div>
          <div class="border border-black mt-3" style="width: 18.5%">&nbsp;XS(160/80A)</div>
          <div class="border border-black mt-3" style="width: 18.5%">&nbsp;XS(160/80A)</div>
          <div class="border border-black mt-3" style="width: 18.5%">&nbsp;XS(160/80A)</div>
          <div class="border border-black mt-3" style="width: 18.5%">&nbsp;XS(160/80A)</div>
        </div>

        <div style="padding-top: 20px">
          数量
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script></script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}
</style>
