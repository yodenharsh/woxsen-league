<template>
  <div>
    <TheHeader></TheHeader>
    <div class="form-card">
      <Card>
        <Form></Form>
      </Card>
    </div>
    <TheFooter></TheFooter>
  </div>
</template>

<script setup>
import TheHeader from "./views/TheHeader.vue";
import Form from "./components/Form.vue";
import TheFooter from "./views/TheFooter.vue";
import Card from "./views/Card.vue";
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

.form-card {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-right: -50%;
  transform: translate(-50%, -50%);
}
</style>
