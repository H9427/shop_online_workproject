<template>
  <!-- 轮播图 -->
  <div>
    <el-carousel indicator-position="outside" height="1000px">
    <el-carousel-item v-for="(item, index) in imageLinks" :key="index">
      <img :src="item" alt="carousel-image" style="width: 100%; height: 100%;">
    </el-carousel-item>
  </el-carousel>
  </div>
  <!-- 主体 -->
  <div class="custom-div">
    <p style="text-align: center; font-size: 2ch; margin-top: 80px" class="italic ...">新季魅力</p>
    <p style="text-align: center; font-size: 2ch" class="italic ...">New Season’s Charm</p>

    <!-- 推荐 -->
    <div>
      <el-row :gutter="36" style="padding-top: 15px">
        <!-- 左边 -->
        <el-col :span="12" class="mb-10" style="padding-left: 15%">
          <el-card :body-style="{ padding: '0px' }" shadow="hover" style="width: 100%; position: relative; cursor: pointer">
            <a @click="$router.push('/clothes')">
              <img src="https://ufm-pic.ur.com.cn/image/tmp/1701953293454.jpg" class="image" />
              <div class="title">
                女士精选&nbsp;&nbsp;<el-icon><ArrowRight /></el-icon>
              </div>
            </a>
          </el-card>
        </el-col>
        <!-- 右边 -->
        <el-col :span="12" class="mb-10">
          <div>
            <!-- 右边-上 -->
            <el-card :body-style="{ padding: '0px' }" shadow="hover" style="width: 70%; position: relative; cursor: pointer">
              <a @click="$router.push('/clothes')">
                <img src="https://ufm-pic.ur.com.cn/image/tmp/1701953301625.jpg" class="image" />
                <div class="title">
                  男士精选&nbsp;&nbsp;<el-icon><ArrowRight /></el-icon>
                </div>
              </a>
            </el-card>
            <!-- 右边-下 -->
            <el-card :body-style="{ padding: '0px' }" shadow="hover" style="width: 70%; position: relative; margin-top: 4.1%">
              <a target="_blank" href="">
                <img src="https://ufm-pic.ur.com.cn/image/tmp/1701953310028.jpg" class="image" />
                <div class="title">
                  鞋包配饰&nbsp;&nbsp;<el-icon><ArrowRight /></el-icon>
                </div>
              </a>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 热销商品 -->
    <div class="divider divider-neutral" style="margin-top: 110px">Hot Clothes</div>
    <p class="italic ...">The quick brown fox ...</p>
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
    <button style="display: flex; align-items: center; text-align: center; padding: 8px; margin-left: 46%" class="border-double border-4 border-gray-400 ...">
      查看更多&nbsp;<el-icon><ArrowRight /></el-icon>
    </button>

    <!-- 女士商品 -->
    <div class="divider divider-neutral" style="margin-top: 110px">Hot Clothes</div>
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
      <button style="display: flex; align-items: center; text-align: center; padding: 8px; margin-left: 46%" class="border-double border-4 border-gray-400 ...">
        查看更多&nbsp;<el-icon><ArrowRight /></el-icon>
      </button>
    </div>

    <!-- 男士商品 -->
    <div class="divider divider-neutral" style="margin-top: 110px">Hot Clothes</div>
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
      <button style="display: flex; align-items: center; text-align: center; padding: 8px; margin-left: 46%; margin-bottom: 50px" class="border-double border-4 border-gray-400 ...">
        查看更多&nbsp;<el-icon><ArrowRight /></el-icon>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ArrowRight } from "@element-plus/icons-vue";
const imageLinks = [
      'https://ufm-pic.ur.com.cn/image/tmp/1699261975019.jpg',
      'https://ufm-pic.ur.com.cn/image/tmp/1695299239702.jpg',
      'https://ufm-pic.ur.com.cn/image/tmp/1700021291263.jpg',
      'https://ufm-pic.ur.com.cn/image/tmp/1702004345727.jpg'
    ];
</script>

<style scoped>
.title {
  position: absolute;
  width: 100%;
  height: 60px;
  font-size: 18px;
  line-height: 60px; /* 与height相同，实现垂直居中 */
  text-align: center;
  background-color: rgba(48, 48, 48, 0.486);
  color: rgba(255, 255, 255, 1);
  bottom: 0;
  left: 0;
  display: flex; /* 使用flex布局 */
  justify-content: center; /* 在水平方向上居中 */
  align-items: center; /* 在垂直方向上居中 */
}

.image {
  width: 100%;
  display: block;
}

.custom-div {
  width: 70%;
  margin: 0 auto; /* 居中 */
}
</style>
