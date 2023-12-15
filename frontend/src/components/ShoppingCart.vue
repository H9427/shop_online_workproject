<template>
  <Navbar />
  <div class="custom-div">
    <el-table ref="multipleTableRef" :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column label="Date" width="120">
        <template #default="scope">{{ scope.row.date }}</template>
      </el-table-column>
      <el-table-column property="name" label="Name" width="120" />
      <el-table-column property="address" label="Address" show-overflow-tooltip />
    </el-table>
  </div>
</template>

<script setup></script>

<style scoped>
.custom-div {
  width: 75%;
  margin: 0 auto;
}
</style>
