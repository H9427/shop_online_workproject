import { defineStore } from "pinia";

export const useCategoryStore = defineStore({
  id: "category",
  TotalGoods: [],
  Category: [],
  Goods: [],
  check: 0,
  state: () => ({
    categoryData: null,
  }),
  actions: {
    setCategoryData(data) {
      this.categoryData = data;
    },
  },
});
