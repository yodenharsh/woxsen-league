import { createRouter, createWebHistory } from "vue-router";
import UserFormScreen from "../views/UserFormScreen.vue";
import NotFound from "../views/NotFound.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/", component: UserFormScreen, alias: "/userForm" },
    { path: "/:notFound(.*)", component: NotFound },
  ],
});

export default router;
