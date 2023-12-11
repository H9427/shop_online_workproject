import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./css/tailwind.css";
import axios from "axios";
// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'

axios.defaults.baseURL = "http://172.16.3.64:8080";

const app = createApp(App);

// app.use(ElementPlus)
app.use(router);
app.mount("#app");
