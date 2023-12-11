<template>
  <clothesNav />
  <div class="custom-div">
    <div role="tablist" class="tabs tabs-bordered" style="margin-top: 20px">
      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="Tab 1" checked />
      <div role="tabpanel" class="tab-content p-10">123</div>
      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="Tab 2" />
      <div role="tabpanel" class="tab-content p-10">Tab content 2</div>

      <input type="radio" name="my_tabs_1" role="tab" class="tab" aria-label="Tab 3" />
      <div role="tabpanel" class="tab-content p-10">Tab content 3</div>
    </div>
  </div>
</template>

<script></script>

<style scoped>
.custom-div {
  width: 70%;
  margin: 0 auto; /* 居中 */
}
</style>
