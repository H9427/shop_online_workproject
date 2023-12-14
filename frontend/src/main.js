import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./css/tailwind.css";
import axios from "axios";
import { createPinia } from "pinia";

axios.defaults.baseURL = "http://127.0.0.1:8080";

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);

app.mount("#app");
