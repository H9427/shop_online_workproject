<template>
  <!-- 轮播图 -->
  <div>
    <el-carousel indicator-position="outside" height="865px">
      <el-carousel-item v-for="item in 4" :key="item">
        <h3 text="2xl" justify="center">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  </div>
  <!-- 主体 -->
  <div class="custom-div">
    <!-- 热销商品 -->
    <p style="text-align: center; font-size: 2ch; margin-top: 20px" class="italic ...">新季魅力</p>
    <p style="text-align: center; font-size: 2ch" class="italic ...">New Season’s Charm</p>


    <div class="mt-3">
      <el-row :gutter="36">
        <el-col v-for="(o, index) in 8" :key="o" :span="6" class="mb-10">
          <el-card :body-style="{ padding: '0px' }" shadow="hover">
            <img src="https://www.chanel.cn/images//t_one//w_0.63,h_0.63,c_crop/q_auto:good,f_auto,fl_lossy,dpr_1.1/w_1020/sublimage-l-extrait-de-nuit-l-ecrin-ultimate-repair-night-concentrate-1fl-oz--packshot-default-144878-9536295338014.jpg" class="image" />
            <div style="padding: 20px; display: flex; flex-direction: column; align-items: center; justify-content: center">
              <span style="text-align: center">Goods</span>
              <span style="text-align: center">￥1000.00</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 女士商品 -->
    <div class="divider divider-neutral">Hot Clothes</div>
    <p class="italic ...">The quick brown fox ...</p>
    <div>
      <el-row :gutter="36">
        <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
          <el-card :body-style="{ padding: '0px' }" shadow="hover">
            <img src="https://ufm-pic.ur.com.cn/product/UWL830076/image/UWL830076K3A_1.jpg?x-image-process=image/quality,Q_90" class="image" />
            <div style="padding: 20px; display: flex; flex-direction: column; align-items: center; justify-content: center">
              <span style="text-align: center">Goods</span>
              <span style="text-align: center">￥1000.00</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 男士商品 -->
    <div class="divider divider-neutral">Hot Clothes</div>
    <p class="italic ...">The quick brown fox ...</p>
    <div>
      <el-row :gutter="36">
        <el-col v-for="(o, index) in 4" :key="o" :span="6" class="mb-10">
          <el-card :body-style="{ padding: '0px' }" shadow="hover">
            <img src="https://ufm-pic.ur.com.cn/product/UMB930004/image/UMB930004Z4A_1.jpg?x-image-process=image/quality,Q_90" class="image" />
            <div style="padding: 20px; display: flex; flex-direction: column; align-items: center; justify-content: center">
              <span style="text-align: center">Goods</span>
              <span style="text-align: center">￥1000.00</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.image {
  width: 100%;
  display: block;
}

.custom-div {
  width: 70%;
  margin: 0 auto; /* 居中 */
}

.el-carousel__item h3 {
  display: flex;
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
