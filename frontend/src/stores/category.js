import { defineStore } from "pinia";

export const useCategoryStore = defineStore({
  id: "category",
  state: () => ({
    categoryData: null,
  }),
  actions: {
    setCategoryData(data) {
      this.categoryData = data;
    },
  },
});
