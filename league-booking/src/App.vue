<template>
  <div>
    <TheHeader></TheHeader>
    <RouterView> </RouterView>
    <TheFooter></TheFooter>
  </div>
</template>

<script setup>
import TheHeader from "./components/UI/TheHeader.vue";
import TheFooter from "./components/UI/TheFooter.vue";
</script>

<style scoped>
.main-header {
  background-color: rgb(42, 42, 42);
  color: rgb(239, 238, 238);
  margin: 0px;
  padding: 5px;
}

.header-div {
  padding-left: 40px;
}
</style>
